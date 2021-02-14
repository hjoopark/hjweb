package com.hjweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hjweb.service.BookService;




@WebServlet("/book/delete.action")
public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 글번호와 페이지 번호 읽기
		String sPageNo = req.getParameter("pageno");
		String sBookNo = req.getParameter("bookno");
		
		//bookNo나 pageNo가 없으면 list로 돌아오기.
		if (sPageNo == null || sBookNo == null) {
			resp.sendRedirect("list.action");
			return;
		}
				
		int pageNo = Integer.parseInt(sPageNo);
		int bookNo = Integer.parseInt(sBookNo);
		
		//2. 데이터베이스의 데이터 삭제
		BookService bookService = new BookService();
		bookService.deleteBook(bookNo);
		
		//3. 목록으로 이동
		resp.sendRedirect("list.action?pageno=" + pageNo);
	}

}
