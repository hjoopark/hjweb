package com.hjweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hjweb.service.AccountService;
import com.hjweb.vo.Member;

@WebServlet("/account/login.action")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청 처리
		
		//2. 이동 (jsp : forward, servlet : redirect)
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/WEB-INF/views/account/login.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청 데이터 읽기
		req.setCharacterEncoding("utf-8");
		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");
		
		//VO 객체에 요청 데이터 저장
		Member member = new Member();
		member.setEmail(email);
		member.setPasswd(passwd);
		
		//데이터 처리 (데이터베이스에 데이터 저장 - Service 객체를 사용해서 처리)
		AccountService accountService = new AccountService();
		Member member2 = accountService.findMemberByIdAndPasswd(member);
		
		if (member2 != null) {	//로그인 가능한 사용자라면 (DB에 사용자 정보가 존재한다면) 
			//로그인 처리 (세션과 같은 상태 유지 객체에 로그인 데이터 저장)
			HttpSession session = req.getSession();	//Session 객체 가져오기
			session.setAttribute("loginuser", member2); //Session에 데이터 저장
			
			//2. 이동 (forward : to .jsp, redirect : to servlet)
			resp.sendRedirect("/hjweb/home.action");
		} else { //로그인 실패
			req.setAttribute("loginfail", email); //JSP에서 읽을 수 있도록 데이터 저장
			
			RequestDispatcher dispatcher = 
					req.getRequestDispatcher("/WEB-INF/views/account/login.jsp");
			dispatcher.forward(req, resp);
		}
		
	}

}







