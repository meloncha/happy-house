package com.ssafy.happyhouse.model.dto;

public class QnaDto {

	private int id;
	private String title;
	private String writer;
	private String content;
	private String comment;
	private String date;
	
	public QnaDto(int id, String title, String writer, String content, String comment, String date) {
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.comment = comment;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
