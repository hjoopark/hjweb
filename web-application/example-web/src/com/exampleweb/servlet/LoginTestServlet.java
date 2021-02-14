package com.exampleweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet : web.xml 파일의 <servlet>, <servlet-mapping>을 대신하는 어노테이션
@WebServlet(value = "/login-test.action")
public class LoginTestServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8"); //요청 데이터를 읽을 때 utf-8 인코딩으로 읽으세요
		
		//HttpServletRequest.getParameter : 클라이언트가 전송한 데이터를 읽는 명령
		String id = req.getParameter("id"); // "id" : 입력 요소의 id속성에 지정된 값
		String passwd = req.getParameter("passwd");// "pwd" : 입력 요소의 pwd속성에 지정된 값
		
		
		if(id.equals("goott") && passwd.equals("9922")) {
			System.out.println("성공");
		}
		else {
			System.out.println("실패");
		}
		
		resp.setContentType("text/plain;charset=utf-8"); //응답 컨텐츠는 utf-8로 작성했어요 라고 브라우저에 알려주는 작업
		PrintWriter out = resp.getWriter();		
		if(id.equals("goott") && passwd.equals("9922")) {
			out.println("성공");	//브라우저 (클라이언트)로 전송
		}
		else {
			out.println("실패");
		}
	}
	
}
