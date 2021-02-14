package com.ajaxweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet(value = "/ajax/get-suggestions.action")
public class GetSuggestionsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String memberId = req.getParameter("id");
		
		MemberDao dao = new MemberDao();
		List<String> idList = dao.getMemberIdListByKey(memberId);
		
		//"iamuserone;iamusertwo;tester;..."
		StringBuilder sb = new StringBuilder(idList.size() * 20);	//문자열을 조합하기 위해 stringbuilder를 사용
		for (int i = 0; i < idList.size(); i++) {
			sb.append(idList.get(i));
			if (i < idList.size() - 1) {		//마지막엔 ; 없게 하기
				sb.append(";");
			}
		}
		
		//text/plain, text/html, text/xml, application/octet-stream,.....
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.write(sb.toString());	//여기서 out.write로 보내주면 html ajax에서 data로 보내진다.
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





