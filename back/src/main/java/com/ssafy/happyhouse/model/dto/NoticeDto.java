package com.ssafy.happyhouse.model.dto;

public class NoticeDto {

	private int num;
	private String title;
	private String content;
	private String writer;
	private String date;
	private int readcnt;
	
	public NoticeDto() {}

	public NoticeDto(int num, String title, String content, String writer, String date, int readcnt) {
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.date = date;
		this.readcnt = readcnt;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int getReadcnt() {
		return readcnt;
	}
	
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
}
