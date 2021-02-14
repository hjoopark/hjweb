package com.ajaxweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxweb.dao.MemberDao;
import com.ajaxweb.vo.Member;

@WebServlet(value = "/ajax/search-member.action")
public class MemberSearchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) { }
		
		String memberId = req.getParameter("memberid");
		System.out.println(memberId);
		
		MemberDao dao = new MemberDao();
		Member member = dao.getMemberById(memberId);
		
		//원래는 jsp로 보내서 member객체를 바로 보낼 수 있었는데 지금은
		//응답을 브라우저로 바로 보내기 때문에 member객체를 바로 보낼 수 없어서 문자열로 바꿔서 보내야 한다.
		
		String result = null;
		if (member != null) {
			//Member 객체 -> "hjoo;hjoopark@naver.com;2018-11-13;user;true"
			result = String.format("%s;%s;%s;%s;%b;",
					member.getMemberId(), member.getEmail(),
					member.getRegDate().toString(),
					member.getUserType(), member.isActive());
		} else {
			result = "no-result";
		}
		
		//text/plain, text/html, text/xml, application/octet-stream,.....
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.write(result);	//여기서 out.write로 보내주면 html ajax에서 data로 보내진다.
	}
}





