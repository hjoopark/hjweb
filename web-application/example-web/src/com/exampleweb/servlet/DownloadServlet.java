package com.exampleweb.servlet;

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

@WebServlet(value = "/download.action")
public class DownloadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			req.setCharacterEncoding("utf-8");
			String fileName = req.getParameter("filename");
			System.out.println(fileName);
		
			//브라우저가 응답 컨텐츠를 다운로드로 처리하도록 정보 설정
			resp.setContentType("application/octet-stream;charset-utf-8");	//브라우저가 처리할 수 없는 데이터
			resp.addHeader("Content-Disposition",
							"Attachment;filename=" +
							new String(fileName.getBytes("utf-8"), "ISO-8859-1") + "\"");
			
			//ServletContext : JSP의 application객체와 동일한 객체
			ServletContext application = req.getServletContext();
			
			String path = application.getRealPath("/files/" + fileName);
			
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
