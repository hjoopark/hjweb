package com.demoweb.servlet;

import java.io.IOException;
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

@WebServlet("/board/get-comment-list.action")
public class CommentListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		String sBoardNo = req.getParameter("boardno");		
		if (sBoardNo == null) {
		}
		
		int boardNo = Integer.parseInt(sBoardNo);
		
		//데이터베이스에서 데이터 조회 (BoardService 객체 사용)
		BoardService boardService = new BoardService();
		List<BoardComment> comments = boardService.findCommentListByBoardNo(boardNo);
		
		//JSP에서 읽을 수 있도록 request객체에 조회된 데이터 저장 (forward 이동이기 때문에)
		req.setAttribute("comments", comments);
		
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/WEB-INF/views/board/comments.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		
		//데이터 읽기
		String sPageNo = req.getParameter("pageno");		
		String sBoardNo = req.getParameter("boardno");		
		if (sPageNo == null || sBoardNo == null) {
			resp.sendRedirect("list.action");
			return;
		}
		
		int pageNo = Integer.parseInt(sPageNo);
		int boardNo = Integer.parseInt(sBoardNo);
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setTitle(title);
		board.setContent(content);
		
		//데이터베이스 데이터 수정
		BoardService boardService = new BoardService();
		boardService.updateBoard(board);
		
		//상세보기 화면으로 이동
		resp.sendRedirect(
				String.format("detail.action?boardno=%s&pageno=%s", boardNo, pageNo));
	}

}







