package com.demoweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demoweb.service.BoardService;


@WebServlet("/board/delete-comment.action")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 댓글번호와 읽기
		String sCommentNo = req.getParameter("commentno");
		
		if (sCommentNo == null) {
		}
				
		int commentNo = Integer.parseInt(sCommentNo);
		
		//2. 데이터베이스의 데이터 삭제
		BoardService boardService = new BoardService();
		boardService.deleteComment(commentNo);
		
		//3. 결과 응답
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write("success");
	}

}
