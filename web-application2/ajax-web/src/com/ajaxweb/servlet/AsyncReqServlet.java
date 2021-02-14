package com.ajaxweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/ajax/async-req.action")
public class AsyncReqServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(new Date().toString());
	}
}
