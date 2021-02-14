package com.exampleweb.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("*.jsp")
public class MyFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String uri = req.getRequestURI();	//요청 경로를 반환하는 메서드
		if (uri.contains("05") || uri.contains("06")) {
			System.out.println("요청이 redirect 됩니다.");
			resp.sendRedirect("01.index.html");
			return;
		}
		
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}

}
