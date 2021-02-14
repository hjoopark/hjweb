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



@WebServlet("/book/update.action")
public class BookUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//브라우저에서 전송한 pageno 읽기(http://.../detail.action?pageno=1)
		String sPageNo = req.getParameter("pageno");
		
		//브라우저에서 전송한 boardno 읽기(http://.../detail.action?boardno=1)
		String sBookNo = req.getParameter("bookno");
		
		//pageno이나 boardno가 넘어오지 않았을때 
		if (sPageNo == null || sBookNo == null) {
			resp.sendRedirect("list.action");
			return;
		}
		
		int pageNo = Integer.parseInt(sPageNo);
		int bookNo = Integer.parseInt(sBookNo);
		
		//데이터베이스에서 데이터 조회 (BoardService 객체 사용)
		BookService bookService = new BookService();
		Book book = bookService.findBookByBookNo(bookNo);
		if (book == null) {
			resp.sendRedirect("list.action");
			return;
		}
		
		
		//JSP에서 읽을 수 있도록 request객체에 조회된 데이터 저장 (forward 이동이기 때문에)
		req.setAttribute("book", book);
		req.setAttribute("pageno", pageNo);
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/book/update.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		String sPageNo = req.getParameter("pageno");
		String sBookNo = req.getParameter("bookno");
		
		if (sPageNo == null || sBookNo == null) {
			resp.sendRedirect("list.action");
			return;
		}
				
		int pageNo = Integer.parseInt(sPageNo);
		int bookNo = Integer.parseInt(sBookNo);
		
		String title = req.getParameter("title");
		String writer = req.getParameter("writer");
		String publisher = req.getParameter("publisher");
		String pubdate = req.getParameter("pubdate");
		
		Book book = new Book();
		book.setBookNo(bookNo);
		book.setTitle(title);
		book.setWriter(writer);
		book.setPublisher(publisher);
		book.setPubdate(pubdate);
		
		
		//데이터베이스 데이터 수정
		BookService bookService = new BookService();
		bookService.updateBook(book);
		
		//상세보기 화면으로 이동
		resp.sendRedirect(String.format("detail.action?bookno=%s&pageno=%s", bookNo, pageNo));
	}

}







