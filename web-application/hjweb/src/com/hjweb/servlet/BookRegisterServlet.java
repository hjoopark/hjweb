package com.hjweb.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hjweb.vo.Member;
import com.hjweb.common.Util;
import com.hjweb.service.BookService;
import com.hjweb.vo.Book;
import com.hjweb.vo.BookAttach;

@WebServlet("/book/bookregister.action")
public class BookRegisterServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 여부 확인 -> 로그인 하지 않은 경우 login.action 으로 이동
		HttpSession session = req.getSession();
		Member member = (Member)session.getAttribute("loginuser");
		if (member == null) {	//로그인 하지 않은 경우
			resp.sendRedirect("/hjweb/account/login.action");
			return;
		}
				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/book/bookregister.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
//		//1. 사용자가 입력한 데이터 읽기 (브라우저에서 가져온다)
//		String title = req.getParameter("title");	// getParameter()안에 있는 값은 bookregister.jsp 에 있는 name값과 일치
//		String writer = req.getParameter("writer");
//		String publisher = req.getParameter("publisher");
//		String pubdate = req.getParameter("pubdate");
//		String memwriter = req.getParameter("memwriter");
//		
//		
//		//2. Book 객체에 데이터 저장
//		Book book = new Book();
//		book.setTitle(title);
//		book.setWriter(writer);
//		book.setPublisher(publisher);
//		book.setPubdate(pubdate);
//		book.setMemwriter(memwriter);
		
		//읽어오는 부분만 바꿔줌 (위엔 바이너리와 파일이 같이 들어올 수 없는데 밑으로 사용하면 할 수 있다.)
		req.setCharacterEncoding("utf-8");
		
		//파일 업로드를 포함한 요청인지 확인 (multipart/form-data 형식 확인)
		
		if (ServletFileUpload.isMultipartContent(req) == false) {
			resp.sendRedirect("list.action");
			return;
		}

		
		ServletContext application = req.getServletContext(); //JSP의 내장객체 application과 같은 객체
		//경로 문자열을 저장할 변수
		//application.getRealPath('웹경로')
		//--> 가상경로(웹경로) -> 물리경로(컴퓨터경로)
		//--> http://..... -> C:/......
		String path = application.getRealPath("/upload/files");//최종 파일 저장 경로
		String tempPath = application.getRealPath("/upload/temp");//임시 파일 저장 경로

		//전송 데이터 각 요소를 분리해서 개별 객체를 만들때 사용할 처리기
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 2);//임시 파일을 만들지 결정하는 기준
		factory.setRepository(new File(tempPath));	//임시파일을 어디에 저장할지 경로

		//요청 정보를 읽을 파서(Parser) 생성 (요청을 읽고 요소별로 분리)
		ServletFileUpload uploader = new ServletFileUpload(factory);
		uploader.setFileSizeMax(1024 * 1024 * 10);//최대 파일 크기

		//요청 정보를 파싱하고 개별 객체의 목록을 반환
		List<FileItem> items = null;
		try {
			items = uploader.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		/////////////////////////////////////////////////////////////////////////////////////
		//여기서 부터 데이터 읽기
		
		//데이터를 저장할 VO 객체 만들기
		Book book = new Book(); 
		ArrayList<BookAttach> list = new ArrayList<>();
		book.setAttachments(list);
		
		for (FileItem item : items) {
			if (item.isFormField()) { //form-data인 경우
				
				if (item.getFieldName().equals("title")) {
					book.setTitle(item.getString("utf-8"));
				} else if (item.getFieldName().equals("writer")) {
					book.setWriter(item.getString("utf-8"));
				} else if (item.getFieldName().equals("publisher")) {
					book.setPublisher(item.getString("utf-8"));
				} else if (item.getFieldName().equals("pubdate")) {
					book.setPubdate(item.getString("utf-8"));
				} else if (item.getFieldName().equals("memwriter")) {
					book.setMemwriter(item.getString("utf-8"));
				} 
				
			} else { //file인 경우
				String fileName = item.getName();
				if (fileName != null && fileName.length() > 0) { //내용이 있는 경우
					if (fileName.contains("\\")) {		// iexplore 경우
						//C:\AAA\BBB\CCC.png -> CCC.png
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					}
					String uniqueFileName = Util.makeUniqueFileName(fileName);
					try {
						item.write(new File(path, uniqueFileName)); //파일 저장
					} catch (Exception e) {
						e.printStackTrace();
					} //파일 저장
					item.delete(); //임시 파일 삭제
					
					BookAttach ba = new BookAttach();
					ba.setUserFileName(fileName);
					ba.setSavedFileName(uniqueFileName);
					list.add(ba);
					
				}
			}
		}
		
		//3. 데이터베이스에 데이터 저장 (BookService 사용)
		BookService bookService = new BookService();
		bookService.registerBook(book);	
		
		//4. 목록으로 이동
		resp.sendRedirect("list.action");
	}

}
