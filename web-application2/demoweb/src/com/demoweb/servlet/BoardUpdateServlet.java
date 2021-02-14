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

@WebServlet("/board/update.action")
public class BoardUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//브라우저에서 전송한 pageno 읽기(http://.../detail.action?pageno=1)
		String sPageNo = req.getParameter("pageno");
		
		//브라우저에서 전송한 boardno 읽기(http://.../detail.action?boardno=1)
		String sBoardNo = req.getParameter("boardno");
		
		//pageno이나 boardno가 넘어오지 않았을때 
		if (sPageNo == null || sBoardNo == null) {
			resp.sendRedirect("list.action");
			return;
		}
		
		int pageNo = Integer.parseInt(sPageNo);
		int boardNo = Integer.parseInt(sBoardNo);
		
		//데이터베이스에서 데이터 조회 (BoardService 객체 사용)
		BoardService boardService = new BoardService();
		Board board = boardService.findBoardByBoardNo(boardNo);
		if (board == null) {
			resp.sendRedirect("list.action");
			return;
		}
		
		
		//JSP에서 읽을 수 있도록 request객체에 조회된 데이터 저장 (forward 이동이기 때문에)
		req.setAttribute("board", board);
		req.setAttribute("pageno", pageNo);
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/update.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
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
		resp.sendRedirect(String.format("detail.action?boardno=%s&pageno=%s", boardNo, pageNo));
	}

}







