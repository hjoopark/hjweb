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

@WebServlet("/board/list2.action")
public class BoardListServlet2 extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//데이터베이스에서 데이터 조회 (BoardService 객체 사용)
		BoardService boardService = new BoardService();
		List<Board> boards = boardService.findAllBoard();
		
		//JSP에서 읽을 수 있도록 request객체에 조회된 데이터 저장 (forward 이동이기 때문에)
		req.setAttribute("boards", boards);
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);
		
	}

}







