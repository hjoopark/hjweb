package com.hjweb.service;

import java.util.List;

import com.hjweb.dao.BookDao;
import com.hjweb.vo.Book;
import com.hjweb.vo.BookAttach;

public class BookService {

	public void registerBook(Book book) {
		
		BookDao bookDao = new BookDao();
		bookDao.insertBook(book);
		
		//등록된 bookno와 bookattach를 연결해준다
		//첨부파일 등록하는 구현
		for (BookAttach attach : book.getAttachments()) {
			attach.setBookNo(book.getBookNo()); //위에서 등록한 글번호 저장
			bookDao.insertBookAttach(attach);
		}
	}

	public List<Book> findAllBook() {
		BookDao bookDao = new BookDao();
		List<Book> books = bookDao.selectAllBook();
		return books;
	}

	public Book findBookByBookNo(int bookNo) {
		BookDao bookDao = new BookDao();
		Book book = bookDao.selectBookByBookNo(bookNo);
		
		// bookno(게시판 번호)를 찾을 수 없으면 attach(첨부파일)에 관한 서비스해주지 마세요.
		if (book != null) {			
			List<BookAttach> attachments = bookDao.selectBookAttachByBookNo(bookNo);
			book.setAttachments(attachments);
		}
		return book;
	}
	

	public List<Book> findAllBookByPage(int from, int to) {
		BookDao bookDao = new BookDao();
		List<Book> books = bookDao.selectAllBookByPage(from, to);
		
		for(int i=0; i<books.size(); i++) {
	         List<BookAttach> attachs = bookDao.selectBookAttachByBookNo(books.get(i).getBookNo());
	         books.get(i).setAttachments(attachs);
	         }
		
		return books;
		
	}

	public int findBookCount() {
		BookDao bookDao = new BookDao();
		int count = bookDao.selectBookCount();
		return count;
	}

	public void deleteBook(int bookNo) {
		BookDao bookDao = new BookDao();
		bookDao.deleteBook(bookNo);
	}

	public void updateBook(Book book) {
		
		BookDao bookDao = new BookDao();
		bookDao.updateBook(book);
	}


}
