package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.demoweb.vo.Board;
import com.demoweb.vo.BoardAttach;
import com.demoweb.vo.BoardComment;
import com.demoweb.vo.Member;

public class BoardDao {

	public void insertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Select 명령에서 사용하는 도구
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.16.6.16:1521:xe", "demoweb", "oracle");
			
			//3. SQL 작성 + 명령 만들기
			String sql = "INSERT INTO BOARD" + 
						 "(boardno, title, writer, content, groupno, step)" + 
						 "VALUES (board_sequence.nextval, ?, ?, ?, board_sequence.currval, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			//4. 명령 실행
			pstmt.executeUpdate();//executeUpdate() : for insert, update, delete command
			pstmt.close();
			
			//insert된 board의 boardno를 조회하는 구현
			sql = "SELECT board_sequence.currval FROM DUAL"; //currval : 마지막 nextval 값
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			rs.next();
			board.setBoardNo(rs.getInt(1));
			
			
			//5. 결과가 있다면 (Select Query 수행한 경우) 결과 처리
			// 여기서는 작업 없음
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public void insertBoardAttach(BoardAttach attach) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null; //Select 명령에서 사용하는 도구
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.16.6.16:1521:xe", "demoweb", "oracle");
			
			//3. SQL 작성 + 명령 만들기
			String sql = "INSERT INTO BOARDATTACH" + 
						 "(attachno, boardno, userfilename, savedfilename) " + 
						 "VALUES (boardattach_sequence.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo());
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

	public List<Board> selectAllBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Select 명령에서 사용하는 도구
		ArrayList<Board> boards = new ArrayList<>(); //조회된 데이터를 저장할 컬렉션 객체
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.16.6.16:1521:xe", "demoweb", "oracle");
			
			//3. SQL 작성 + 명령 만들기
			String sql = "SELECT boardno, title, writer, regdate, readcount " + 
						 "FROM board ";
			pstmt = conn.prepareStatement(sql);
			
			//4. 명령 실행
			rs = pstmt.executeQuery();//executeQuery() : for select command
			
			//5. 결과 처리
			while (rs.next()) {
				Board board = new Board(); // 한 행의 데이터를 저장할 객체 만들기
				board.setBoardNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setRegDate(rs.getDate(4));
				board.setReadCount(rs.getInt(5));
				
				boards.add(board); //조회된 한 행의 데이터를 목록에 추가
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return boards; //결과 반환
		
	}

	public Board selectBoardByBoardNo(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Select 명령에서 사용하는 도구
		Board board = null; //조회된 데이터를 저장할 컬렉션 객체 (단일 데이터, PK 검색)
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.16.6.16:1521:xe", "demoweb", "oracle");
			
			//3. SQL 작성 + 명령 만들기
			String sql = "SELECT boardno, title, writer, regdate, readcount, content, groupno, step, depth " + 
						 "FROM board " +
						 "WHERE boardno = ? AND deleted = '0'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			//4. 명령 실행
			rs = pstmt.executeQuery();//executeQuery() : for select command
			
			//5. 결과 처리
			if (rs.next()) {
				board = new Board(); // 한 행의 데이터를 저장할 객체 만들기
				board.setBoardNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setRegDate(rs.getDate(4));
				board.setReadCount(rs.getInt(5));
				board.setContent(rs.getString(6));
				board.setGroupNo(rs.getInt(7));
				board.setStep(rs.getInt(8));
				board.setDepth(rs.getInt(9));
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return board; //결과 반환
	}

	public List<BoardAttach> selectBoardAttachByBoardNo(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //Select 명령에서 사용하는 도구
		List<BoardAttach> attachments = new ArrayList<>(); //조회된 데이터를 저장할 컬렉션 객체 
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.16.6.16:1521:xe", "demoweb", "oracle");
			
			//3. SQL 작성 + 명령 만들기
			String sql = "SELECT attachno, boardno, userfilename, savedfilename " + 
						 "FROM boardattach " +
						 "WHERE boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			//4. 명령 실행
			rs = pstmt.executeQuery();//executeQuery() : for select command
			
			//5. 결과 처리
			while (rs.next()) {
				BoardAttach attach = new BoardAttach(); // 한 행의 데이터를 저장할 객체 만들기
				attach.setAttachNo(rs.getInt(1));
				attach.setBoardNo(rs.getInt(2));
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


	/////////////////////////////////////////////////////////////////////////////
	
	public BoardAttach selectBoardAttachByAttachNo(int attachNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardAttach attachment = null;
		
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.16.6.16:1521:xe", "demoweb", "oracle");
			
			String sql = 
				"SELECT attachno, boardno, savedfilename, userfilename " + 
				"FROM boardattach WHERE attachno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attachNo);
			rs = pstmt.executeQuery();
				
			if (rs.next()) {
				attachment = new BoardAttach();
				attachment.setAttachNo(rs.getInt(1));
				attachment.setBoardNo(rs.getInt(2));
				attachment.setSavedFileName(rs.getString(3));
				attachment.setUserFileName(rs.getString(4));
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		return attachment;
		
	}
	
	public List<Board> selectAllBoardByPage(int first, int last) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> boards = new ArrayList<Board>();
		
		try {
//			//1. 드라이버 등록 (로딩)
//			Class.forName("oracle.jdbc.OracleDriver");
//			
//			//2. 연결 만들기
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@172.16.6.16:1521:xe", "demoweb", "oracle");
			
			conn = ConnectionHelper.getConnection("oracle");
			
			StringBuffer sql = new StringBuffer(500);
			sql.append("SELECT * ");
			sql.append("FROM ");
			
			sql.append("( ");
			sql.append("	SELECT "); 
			sql.append("		rownum idx, s.* "); 
			sql.append("	FROM ");
			
			sql.append("	( ");
			sql.append("		SELECT "); 
			sql.append("			boardno, title, writer, ");
			sql.append("			regdate, readcount, ");
			sql.append("			deleted, groupno, step, depth ");
			sql.append("		FROM ");
			sql.append("			board ");
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
				Board board = new Board();
				board.setBoardNo(rs.getInt(2));
				board.setTitle(rs.getString(3));
				board.setWriter(rs.getString(4));
				board.setRegDate(rs.getDate(5));
				board.setReadCount(rs.getInt(6));
				board.setDeleted(rs.getBoolean(7));
				board.setGroupNo(rs.getInt(8));
				board.setStep(rs.getInt(9));
				board.setDepth(rs.getInt(10));
				
				boards.add(board);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch (Exception ex) {}
			if (conn != null) try { conn.close(); } catch (Exception ex) {}
		}
		
		return boards;
	}

	public int selectBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.16.6.16:1521:xe", "demoweb", "oracle");

			String sql = "SELECT COUNT(*) FROM board";
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

	public void updateBoardReadCount(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			//3. SQL 작성 및 명령 만들기
			String sql = "UPDATE board " +
						 "SET readcount = readcount + 1 " +
						 "WHERE boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
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

	public void deleteBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			//3. SQL 작성 및 명령 만들기
			String sql = "UPDATE board " +
						 "SET deleted = '1' " +
						 "WHERE boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
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

	public void updateBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			//3. SQL 작성 및 명령 만들기
			String sql = "UPDATE board " +
						 "SET title = ?, content = ? " +
						 "WHERE boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());			
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBoardNo());
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

	public void insertReply(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			//3. SQL 작성 및 명령 만들기
			String sql = "INSERT INTO board (boardno, title, writer, content, groupno, step, depth) " +
						 "VALUES (board_sequence.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getGroupNo());
			pstmt.setInt(5, board.getStep());
			pstmt.setInt(6, board.getDepth());
			//4. 명령 실행
			pstmt.executeUpdate();//insert, update, delete 구문 실행 메서드
			pstmt.close();
			
			//insert된 board의 boardno를 조회하는 구현
			sql = "SELECT board_sequence.currval FROM DUAL"; //currval : 마지막 nextval 값
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			rs.next();
			board.setBoardNo(rs.getInt(1));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}		
		
	}

	public void updateStep(int groupNo, int step) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			//3. SQL 작성 및 명령 만들기
			String sql = "UPDATE board " +
						 "SET step = step + 1 " +
						 "WHERE groupno = ? and step > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, step);
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
	
	////////////////////////
	
	public void insertComment(BoardComment comment) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			
			String sql = 
				"INSERT INTO boardcomment " +
				"(commentno, boardno, writer, content) " +
				"VALUES (boardcomment_sequence.nextVal, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getBoardNo());
			pstmt.setString(2, comment.getWriter());
			pstmt.setString(3, comment.getContent());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch (Exception ex) {}
			if (conn != null) try { conn.close(); } catch (Exception ex) {}
		}
	}
	
	public void updateComment(BoardComment comment) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			
			String sql = 
				"UPDATE boardcomment " +
				"SET content = ? " +
				"WHERE commentno = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getContent());
			pstmt.setInt(2, comment.getCommentNo());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch (Exception ex) {}
			if (conn != null) try { conn.close(); } catch (Exception ex) {}
		}
	}
	
	public void deleteComment(int commentNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			
			String sql = 
				"DELETE FROM boardcomment " +				
				"WHERE commentno = ?";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, commentNo);

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch (Exception ex) {}
			if (conn != null) try { conn.close(); } catch (Exception ex) {}
		}
	}

	public ArrayList<BoardComment> selectCommentByBoardNo(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardComment> comments = new ArrayList<>();
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = 
				"SELECT commentno, boardno, writer, content, regdate " + 
				"FROM boardcomment WHERE boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
				
			while (rs.next()) {
				BoardComment comment = new BoardComment();
				comment.setCommentNo(rs.getInt(1));
				comment.setBoardNo(rs.getInt(2));
				comment.setWriter(rs.getString(3));
				comment.setContent(rs.getString(4));
				comment.setRegDate(rs.getDate(5));
				comments.add(comment);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		return comments;
	}
}
