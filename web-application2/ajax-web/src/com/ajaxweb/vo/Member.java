package com.ajaxweb.vo;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class Member implements Serializable {
	
	private String memberId;
	private String passwd;
	private String email;
	private String userType;
	private boolean active;
	private Date regDate;
	
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@XmlJavaTypeAdapter(DateFormatterAdapter.class)
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}	
	private static class DateFormatterAdapter 
		extends XmlAdapter<String, Date> {
		SimpleDateFormat formatter = 
			new SimpleDateFormat("yyyy-MM-dd");
		@Override
		public Date unmarshal(String v) throws Exception {
			return Date.valueOf(v);
		}
		@Override
		public String marshal(Date v) throws Exception {
			return formatter.format(
				new java.util.Date(v.getTime()));
		}
	}
	
	

}
