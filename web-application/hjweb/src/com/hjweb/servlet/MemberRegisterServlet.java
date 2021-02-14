package com.hjweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hjweb.service.AccountService;
import com.hjweb.vo.Member;



@WebServlet("/account/register.action")
public class MemberRegisterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청 처리
		
		//2. 이동 (jsp : forward, servlet : redirect)
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/WEB-INF/views/account/register.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청 데이터 읽기
		req.setCharacterEncoding("utf-8");
		String email = req.getParameter("email");		
		String passwd = req.getParameter("passwd");
		//System.out.printf("[%s][%s][%s]", memberId, passwd, email);
		
		//VO 객체에 요청 데이터 저장
		Member member = new Member();
		member.setEmail(email);
		member.setPasswd(passwd);
		
		//데이터 처리 (데이터베이스에 데이터 저장 - Service 객체를 사용해서 처리)
		AccountService accountService = new AccountService();
		accountService.registerMember(member);
		
		//2. 이동 (forward : to .jsp, redirect : to servlet)
		resp.sendRedirect("/hjweb/home.action");
		
//		RequestDispatcher dispatcher = 
//				req.getRequestDispatcher("/WEB-INF/views/home.jsp");
//		dispatcher.forward(req, resp);
		
		
	}

}







