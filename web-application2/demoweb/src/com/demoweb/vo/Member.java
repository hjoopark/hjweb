package com.demoweb.vo;

import java.util.Date;

//VO 클래스 : Database의 대상 테이블(Member)과 구조와 의미가 일치하도록 구현 
public class Member {
	
	//필드는 테이블의 컬럼과 일치하도록 구현
	private String memberId;
	private String passwd;
	private String email;
	private String userType;
	private Date regDate;
	private boolean active;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
