package com.hjweb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hjweb.service.BookService;
import com.hjweb.vo.Book;



@WebServlet("/book/detail.action")
public class BookDetailServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//브라우저에서 전송한 pageno 읽기
		String sPageNo = req.getParameter("pageno");
		//브라우저에서 전송한 bookno 읽기
		String sBookNo = req.getParameter("bookno");
		
		//pageno이나 bookno가 넘어오지 않았을때
		if (sPageNo == null || sBookNo == null) {
			resp.sendRedirect("list.action");
			return;
		}
		
		int pageNo = Integer.parseInt(sPageNo);
		int bookNo = Integer.parseInt(sBookNo);
		
		//데이터베이스에서 데이터 조회 (BoardService 객체 사용)
		BookService bookService = new BookService();
		Book book = bookService.findBookByBookNo(bookNo);
		
		//bookNo로 book객체를 찾지 못하면 list.action으로 돌려주세요.
		if (book == null) {
			resp.sendRedirect("list.action");
			return;
		}
		
		//JSP에서 읽을 수 있도록 request객체에 조회된 데이터 저장 (forward 이동이기 때문에)
		req.setAttribute("book", book);
		req.setAttribute("pageno", pageNo);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/book/detail.jsp");
		dispatcher.forward(req, resp);
		
	}

}







