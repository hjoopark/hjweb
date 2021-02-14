package com.hjweb.vo;

import java.util.Date;
import java.util.List;

public class Book {

	private int bookNo;			//책 넘버
	private String title;		//책 제목
	private String writer;		//저자
	private String publisher;	//출판사
	private String pubdate;		//출판일
	private String memwriter;	//작성자
	private Date regdate;		//작성일
	private boolean deleted;	//지울까여?
	
	private int groupNo;
	private int step;
	private int depth;
	
	//Book - BookAttach 사이의 1:Many 관계를 구현하는 필드
	private List<BookAttach> attachments;
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String getMemwriter() {
		return memwriter;
	}
	public void setMemwriter(String memwriter) {
		this.memwriter = memwriter;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public List<BookAttach> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<BookAttach> attachments) {
		this.attachments = attachments;
	}
	
	
	
}
