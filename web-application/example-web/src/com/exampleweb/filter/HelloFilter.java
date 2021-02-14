package com.exampleweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//서블릿 또는 JSP가 실행되기 전에 거쳐가는 공용 처리기
public class HelloFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		System.out.println("beginning of " + req.getRequestURI());
		
		//다음 필터 또는 처리기(서블릿, JSP)로 요청 전달
		//호출하지 않으면 요청이 처리 되지 않습니다.
		chain.doFilter(request, response);
		
		System.out.println("end of " + req.getRequestURI());
		
	}

}
