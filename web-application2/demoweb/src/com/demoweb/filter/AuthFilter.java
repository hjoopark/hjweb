package com.demoweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demoweb.vo.Member;

@WebFilter(value = "*.action")
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession();
		Member member = (Member)session.getAttribute("loginuser");
		
		if (uri.contains("/board/") && 
			(uri.contains("/write.action") ||
			uri.contains("/reply.action") ||
			uri.contains("/update.action") ||
			uri.contains("/delete.action"))) {
			
			if (member == null) {
				resp.sendRedirect("/demoweb/account/login.action");
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

}
