package com.demoweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demoweb.service.BoardService;
import com.demoweb.vo.Board;
import com.demoweb.vo.BoardComment;

@WebServlet("/board/update-comment.action")
public class CommentUpdateServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		//데이터 읽기
		String sCommentNo = req.getParameter("commentno");
		
		if ( sCommentNo == null) {
		
		}
				
		int commentNo = Integer.parseInt(sCommentNo);
		
		String content = req.getParameter("content");
		
		BoardComment comment = new BoardComment();
		comment.setCommentNo(commentNo);
		comment.setContent(content);
		
		//데이터베이스 데이터 삽입
		BoardService boardService = new BoardService();
		boardService.updateComment(comment);
		
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write("success");
	}

}







