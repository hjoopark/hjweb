package com.exampleweb.vo;

import java.io.Serializable;
import java.sql.Date;

/**
 * Member 테이블의 데이터를 저장하기 위한 클래스
 * @author Administrator
 *
 */
public class Member implements Serializable {
	
	//Property : memberId
	private String id;
	
	public String getMemberId() {
		return id;
	}
	public void setMemberId(String id) { //-> memberId
		this.id = id;
	}
	
	private String passwd;
	private String userType;
	private String email;
	private boolean active;
	private Date regDate;
	
	public Member() {		
	}
	
	
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
