package com.demoweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demoweb.service.BoardService;


@WebServlet("/board/delete.action")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 글번호와 페이지 번호 읽기
		String sPageNo = req.getParameter("pageno");
		String sBoardNo = req.getParameter("boardno");
		
		if (sPageNo == null || sBoardNo == null) {
			resp.sendRedirect("list.action");
			return;
		}
				
		int pageNo = Integer.parseInt(sPageNo);
		int boardNo = Integer.parseInt(sBoardNo);
		
		//2. 데이터베이스의 데이터 삭제
		BoardService boardService = new BoardService();
		boardService.deleteBoard(boardNo);
		
		//3. 목록으로 이동
		resp.sendRedirect("list.action?pageno=" + pageNo);
	}

}
