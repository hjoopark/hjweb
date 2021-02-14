package com.demoweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/account/logout.action")
public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
		HttpSession session = req.getSession();
		session.removeAttribute("loginuser");	// 로그아웃: 전체 session 데이터 중에서 한개의 항목
		//session.invalidate();	// 로그아웃 : session 데이터 전체 삭제
		
		resp.sendRedirect("/demoweb/home.action");
	}

}







