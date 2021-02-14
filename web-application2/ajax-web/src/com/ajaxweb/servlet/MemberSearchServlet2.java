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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(value = "/ajax/search-member2.action")
public class MemberSearchServlet2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String memberId = req.getParameter("memberid");
		
		MemberDao dao = new MemberDao();
		Member member = dao.getMemberById(memberId);
		
		//원래는 jsp로 보내서 member객체를 바로 보낼 수 있었는데 지금은
		//응답을 브라우저로 바로 보내기 때문에 member객체를 바로 보낼 수 없어서 문자열로 바꿔서 보내야 한다.
		
		String result = null;
		if (member != null) {
			//Member 객체 -> "{ memberid: 'iamusertwo', email: 'iamusertwo@example.com', ...}"
			//result = makeJson(member);
			result = makeJson2(member);
			
		} else {
			result = "{ \"result\" : \"no-result\" }";
		}
		
		//text/plain, text/html, text/xml, application/octet-stream,.....
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.write(result);	//여기서 out.write로 보내주면 html ajax에서 data로 보내진다.
	}

	private String makeJson2(Member member) {
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(member);
		return json;
	}

	private String makeJson(Member member) {
		String result;
		StringBuilder sb = new StringBuilder(256);
		sb.append("{");
		sb.append( String.format("\"memberId\" : \"%s\",", member.getMemberId()) );
		sb.append( String.format("\"email\" : \"%s\",", member.getEmail()) );
		sb.append( String.format("\"regDate\" : \"%s\",", member.getRegDate()) );
		sb.append( String.format("\"userType\" : \"%s\",", member.getUserType()) );
		sb.append( String.format("\"active\" : %b", member.isActive()) );
		sb.append("}");
		result = sb.toString();
		return result;
	}
}





