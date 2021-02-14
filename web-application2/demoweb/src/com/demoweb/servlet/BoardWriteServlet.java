package com.demoweb.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import com.demoweb.common.Util;
import com.demoweb.service.BoardService;
import com.demoweb.vo.Board;
import com.demoweb.vo.BoardAttach;
import com.demoweb.vo.Member;

@WebServlet("/board/write.action")
public class BoardWriteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//아래 인증 코드는 Filter로 이동
		
//		//로그인 여부 확인 -> 로그인 하지 않은 경우 login.action 으로 이동
//		HttpSession session = req.getSession();
//		Member member = (Member)session.getAttribute("loginuser");
//		if (member == null) {	//로그인 하지 않은 경우
//			resp.sendRedirect("/demoweb/account/login.action");
//			return;
//		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/write.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		req.setCharacterEncoding("utf-8");
//		
//		//1. 사용자가 입력한 데이터 읽기
//		String title = req.getParameter("title");
//		String writer = req.getParameter("writer");
//		String content = req.getParameter("content");
//		
//		//2. Board 객체에 데이터 저장
//		Board board = new Board();
//		board.setTitle(title);
//		board.setWriter(writer);
//		board.setContent(content);
		
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
		Board board = new Board(); 
		ArrayList<BoardAttach> list = new ArrayList<>();
		board.setAttachments(list);
		
		for (FileItem item : items) {
			if (item.isFormField()) { //form-data인 경우
				
				if (item.getFieldName().equals("title")) {
					board.setTitle(item.getString("utf-8"));
				} else if (item.getFieldName().equals("writer")) {
					board.setWriter(item.getString("utf-8"));
				} else if (item.getFieldName().equals("content")) {
					board.setContent(item.getString("utf-8"));
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
					
					BoardAttach ba = new BoardAttach();
					ba.setUserFileName(fileName);
					ba.setSavedFileName(uniqueFileName);
					list.add(ba);
					
				}
			}
		}
		
		//3. 데이터베이스에 데이터 저장 (BoardService 사용)
		BoardService boardService = new BoardService();
		boardService.writeBoard(board);
		
		//4. 목록으로 이동
		resp.sendRedirect("list.action");
	}

}







