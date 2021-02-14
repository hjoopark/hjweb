package com.demoweb.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demoweb.service.BoardService;
import com.demoweb.vo.BoardAttach;

@WebServlet(value = "/board/download.action")
public class DownloadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			req.setCharacterEncoding("utf-8");
			//다운로드할 첨부파일 번호를 요청에서 읽기
			String sAttachNo = req.getParameter("attachno");
			if (sAttachNo == null || sAttachNo.length() == 0) { //요청에 첨부파일 번호가 없다면
				resp.sendRedirect("list.action");
				return;
				
			}
			
			int attachNo = Integer.parseInt(sAttachNo);
			
			//데이터베이스에서 데이터 조회
			BoardService boardService = new BoardService();
			BoardAttach boardAttach = boardService.findBoardAttachByAttachNo(attachNo);
			
			//브라우저가 응답 컨텐츠를 다운로드로 처리하도록 정보 설정
			resp.setContentType("application/octet-stream;charset-utf-8");	//브라우저가 처리할 수 없는 데이터
			resp.addHeader("Content-Disposition",
							"Attachment;filename=" +
							new String(boardAttach.getUserFileName().getBytes("utf-8"), "ISO-8859-1") + "\"");
			
			//ServletContext : JSP의 application객체와 동일한 객체
			ServletContext application = req.getServletContext();
			
			String path = application.getRealPath("/upload/files/" + boardAttach.getSavedFileName());
			
			FileInputStream fis = new FileInputStream(path);		//파일을 읽는 도구
			OutputStream fos = resp.getOutputStream();				//브라우저에게 전송하는 도구
			
			while (true) {
				int data = fis.read();
				if(data == -1) {	//더 이상 읽을 데이터가 없다면 (EOF)
					break;
				}
				fos.write(data);
			}
			
			fis.close();
			//fos.close(); //자동으로 닫힙니다.
			
		
		}
	
}
