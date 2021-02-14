package com.hjweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hjweb.vo.Member;

public class AccountDao {

public void insertMember(Member member) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null; //Select 명령에서 사용하는 도구
		try {
			//1. 드라이버 등록 (로딩)
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. 연결 만들기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hyunjoo", "hyunjoo");
			
			//3. SQL 작성 + 명령 만들기
			String sql = "INSERT INTO MEMBER (email, passwd) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPasswd());
			
			//4. 명령 실행
			pstmt.executeUpdate();//executeUpdate() : for insert, update, delete command
			
			//5. 결과가 있다면 (Select Query 수행한 경우) 결과 처리
			// 여기서는 작업 없음
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//연결 종료
			//try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public Member selectMemberByIdAndPasswd(Member member) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; //Select 명령에서 사용하는 도구
	Member member2 = null;
	
	try {
		//1. 드라이버 등록 (로딩)
		Class.forName("oracle.jdbc.OracleDriver");
		
		//2. 연결 만들기
		conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", "hyunjoo", "hyunjoo");
		
		//3. SQL 작성 + 명령 만들기
		String sql = "SELECT email " +
					 "FROM member " +
					 "WHERE email = ? AND passwd = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getEmail());
		pstmt.setString(2, member.getPasswd());
		
		//4. 명령 실행
		//pstmt.executeUpdate();//executeUpdate() : for insert, update, delete command
		rs = pstmt.executeQuery(); //executeQuery() : for select command
		
		//5. 결과가 있다면 (Select Query 수행한 경우) 결과 처리
		if(rs.next()) {
			member2 = new Member();
			member2.setEmail(rs.getString(1));
		}
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		//연결 종료
		try { rs.close(); } catch (Exception ex) {}
		try { pstmt.close(); } catch (Exception ex) {}
		try { conn.close(); } catch (Exception ex) {}
	}
	
	return member2;
}
}
