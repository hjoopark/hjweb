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
import com.demoweb.ui.ThePager;
import com.demoweb.vo.Board;

@WebServlet("/board/list.action")
public class BoardListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String sPageNo = req.getParameter("pageno");
		if(sPageNo == null || sPageNo.length() == 0) {
			sPageNo = "1";
		}
		
		int pageNo = Integer.parseInt(sPageNo);
		int pageSize = 3;	//한 페이지에 표시되는 데이터 개수
		int from =(pageNo - 1) * pageSize + 1; //해당 페이지에 포함된 시작 글번호
		int to = from + pageSize;				//해당 페이지에 포함된 마지막 글번호 + 1
		int pagerSize = 2;	//한 번에 표시되는 페이지 번호 개수
		String linkUrl = "list.action"; //페이지 번호를 눌렀을 때 이동할 경로
		
		//데이터베이스에서 데이터 조회 (BoardService 객체 사용)
		BoardService boardService = new BoardService();
		List<Board> boards = boardService.findAllBoardByPage(from,to);
		
		int boardCount = boardService.findBoardCount();
		
		ThePager pager = new ThePager(boardCount, pageNo, pageSize, pagerSize, linkUrl);
		
		//JSP에서 읽을 수 있도록 request객체에 조회된 데이터 저장 (forward 이동이기 때문에)
		req.setAttribute("boards", boards);
		req.setAttribute("pager", pager);
		req.setAttribute("pageno", pageNo);
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);
		
	}

}







