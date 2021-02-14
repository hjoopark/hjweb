package com.hjweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hjweb.dao.ConnectionHelper;
import com.hjweb.vo.Book;
import com.hjweb.vo.BookAttach;

public class BookDao {

	public void insertBook(Book book) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Select 명령에서 사용하는 도구
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hyunjoo", "hyunjoo");
			
			//3. SQL 작성 + 명령 만들기
			String sql = "INSERT INTO BOOK" + 
						 "(bookno, title, writer, publisher, pubdate, memwriter, groupno, step)" + 
						 "VALUES (book_sequence.nextval, ?, ?, ?, ?, ?, book_sequence.currval, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getWriter());
			pstmt.setString(3, book.getPublisher());
			pstmt.setString(4, book.getPubdate());
			pstmt.setString(5, book.getMemwriter());
			
			//4. 명령 실행
			pstmt.executeUpdate();//executeUpdate() : for insert, update, delete command
			pstmt.close();
			
			//5. 결과가 있다면 (Select Query 수행한 경우) 결과 처리
			//insert된 book의 bookno를 조회하는 구현
			sql = "SELECT book_sequence.currval FROM DUAL"; //currval : 마지막 nextval 값
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			rs.next();
			book.setBookNo(rs.getInt(1));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}

	public List<Book> selectAllBook() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Select 명령에서 사용하는 도구
		ArrayList<Book> books = new ArrayList<>(); //조회된 데이터를 저장할 컬렉션 객체
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hyunjoo", "hyunjoo");
			
			//3. SQL 작성 + 명령 만들기
			String sql = "SELECT bookno, title, writer, publisher, regdate " + 
						 "FROM book ";
			pstmt = conn.prepareStatement(sql);
			
			//4. 명령 실행
			rs = pstmt.executeQuery();//executeQuery() : for select command
			
			//5. 결과 처리
			while (rs.next()) {
				Book book = new Book(); // 한 행의 데이터를 저장할 객체 만들기
				book.setBookNo(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setWriter(rs.getString(3));
				book.setPublisher(rs.getString(4));
				book.setRegdate(rs.getDate(5));
				
				books.add(book); //조회된 한 행의 데이터를 목록에 추가
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return books; //결과 반환
	}

	public Book selectBookByBookNo(int bookNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Select 명령에서 사용하는 도구
		Book book = null; //조회된 데이터를 저장할 컬렉션 객체 (단일 데이터, PK 검색)
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hyunjoo", "hyunjoo");
			
			//3. SQL 작성 + 명령 만들기
			String sql = "SELECT bookno, title, writer, publisher, pubdate, memwriter, regdate, groupno, step, depth " + 
						 "FROM book " +
						 "WHERE bookno = ? AND deleted = '0'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNo);
			
			//4. 명령 실행
			rs = pstmt.executeQuery();//executeQuery() : for select command
			
			//5. 결과 처리
			if (rs.next()) {
				book = new Book(); // 한 행의 데이터를 저장할 객체 만들기
				book.setBookNo(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setWriter(rs.getString(3));
				book.setPublisher(rs.getString(4));
				book.setPubdate(rs.getString(5));
				book.setMemwriter(rs.getString(6));
				book.setRegdate(rs.getDate(7));
				book.setGroupNo(rs.getInt(8));
				book.setStep(rs.getInt(9));
				book.setDepth(rs.getInt(10));
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return book; //결과 반환
	}

	public List<Book> selectAllBookByPage(int first, int last) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Book> books = new ArrayList<Book>();
		
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hyunjoo", "hyunjoo");
			
			//conn = ConnectionHelper.getConnection("oracle");
			
			StringBuffer sql = new StringBuffer(500);
			sql.append("SELECT * ");
			sql.append("FROM ");
			
			sql.append("( ");
			sql.append("	SELECT "); 
			sql.append("		rownum idx, s.* "); 
			sql.append("	FROM ");
			
			sql.append("	( ");
			sql.append("		SELECT "); 
			sql.append("			bookno, title, writer, publisher, ");
			sql.append("			pubdate, memwriter, regdate, ");
			sql.append("			deleted, groupno, step, depth ");
			sql.append("		FROM ");
			sql.append("			book ");
//			sql.append("		WHERE ");
//			sql.append("			deleted = '0' ");
			sql.append("		ORDER BY groupno DESC, step ASC ");
			sql.append("	) s ");
			
			sql.append(") ");
			
			sql.append("WHERE idx >= ? AND idx < ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, first);
			pstmt.setInt(2, last);
			rs = pstmt.executeQuery();			
			
			while (rs.next()) {
				Book book = new Book();
				book.setBookNo(rs.getInt(2));
				book.setTitle(rs.getString(3));
				book.setWriter(rs.getString(4));
				book.setPublisher(rs.getString(5));
				book.setPubdate(rs.getString(6));
				book.setMemwriter(rs.getString(7));
				book.setRegdate(rs.getDate(8));
				book.setDeleted(rs.getBoolean(9));
				book.setGroupNo(rs.getInt(10));
				book.setStep(rs.getInt(11));
				book.setDepth(rs.getInt(12));
				
				books.add(book);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch (Exception ex) {}
			if (conn != null) try { conn.close(); } catch (Exception ex) {}
		}
		
		return books;
	}

	public int selectBookCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hyunjoo", "hyunjoo");

			String sql = "SELECT COUNT(*) FROM book";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
			
		} catch (Exception ex) {
			count = 0;
			ex.printStackTrace();			
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception ex) { }
			try { if (pstmt != null) pstmt.close(); } catch (Exception ex) { }
			try { if (conn != null) conn.close(); } catch (Exception ex) { }
		}
		return count;
	}

	public void deleteBook(int bookNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hyunjoo", "hyunjoo");
			
			//3. SQL 작성 및 명령 만들기
			String sql = "UPDATE book " +
						 "SET deleted = '1' " +
						 "WHERE bookno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNo);
			//4. 명령 실행
			pstmt.executeUpdate();//insert, update, delete 구문 실행 메서드
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public void updateBook(Book book) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			//3. SQL 작성 및 명령 만들기
			String sql = "UPDATE book " +
						 "SET title = ?, writer = ?, publisher = ?, pubdate = ? " +
						 "WHERE bookno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());			
			pstmt.setString(2, book.getWriter());
			pstmt.setString(3, book.getPublisher());
			pstmt.setString(4, book.getPubdate());
			pstmt.setInt(5, book.getBookNo());
			//4. 명령 실행
			pstmt.executeUpdate();//insert, update, delete 구문 실행 메서드
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}

	public void insertBookAttach(BookAttach attach) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null; //Select 명령에서 사용하는 도구
		try {
			
			conn = ConnectionHelper.getConnection("oracle");
			
			//3. SQL 작성 + 명령 만들기
			String sql = "INSERT INTO BOOKATTACH" + 
						 "(attachno, bookno, userfilename, savedfilename) " + 
						 "VALUES (bookattach_sequence.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBookNo());
			pstmt.setString(2, attach.getUserFileName());
			pstmt.setString(3, attach.getSavedFileName());
			
			//4. 명령 실행
			pstmt.executeUpdate();//executeUpdate() : for insert, update, delete command
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//연결 종료
			//try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public List<BookAttach> selectBookAttachByBookNo(int bookNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Select 명령에서 사용하는 도구
		List<BookAttach> attachments = new ArrayList<>(); //조회된 데이터를 저장할 컬렉션 객체 
		try {
			
			conn = ConnectionHelper.getConnection("oracle");
			
			//3. SQL 작성 + 명령 만들기
			String sql = "SELECT attachno, bookno, userfilename, savedfilename " + 
						 "FROM bookattach " +
						 "WHERE bookno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNo);
			
			//4. 명령 실행
			rs = pstmt.executeQuery();//executeQuery() : for select command
			
			//5. 결과 처리
			while (rs.next()) {
				BookAttach attach = new BookAttach(); // 한 행의 데이터를 저장할 객체 만들기
				attach.setAttachNo(rs.getInt(1));
				attach.setBookNo(rs.getInt(2));
				attach.setUserFileName(rs.getString(3));
				attach.setSavedFileName(rs.getString(4));
				
				attachments.add(attach);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return attachments; //결과 반환
	}


	

}
