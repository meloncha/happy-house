package com.ssafy.happyhouse.model.dto;

public class BoardDto {

    private int bNum;
    private String bTitle;
    private String bWriter;
    private String bContent;
    private int breadCnt;
    private String bwriteDate;
    private int type;

    public BoardDto() {};

    public BoardDto(String bTitle, String bWriter, String bContent, int breadCnt, String bwriteDate, int type) {
        this.bTitle = bTitle;
        this.bWriter = bWriter;
        this.bContent = bContent;
        this.breadCnt = breadCnt;
        this.bwriteDate = bwriteDate;
        this.type = type;
    }

    public BoardDto(int bNum, String bTitle, String bWriter, String bContent, int breadCnt, String bwriteDate, int type) {
        this.bNum = bNum;
        this.bTitle = bTitle;
        this.bWriter = bWriter;
        this.bContent = bContent;
        this.breadCnt = breadCnt;
        this.bwriteDate = bwriteDate;
        this.type = type;
    }

    public int getbNum() {
        return bNum;
    }

    public void setbNum(int bNum) {
        this.bNum = bNum;
    }

    public String getbTitle() {
        return bTitle;
    }

    public void setbTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    public String getbWriter() {
        return bWriter;
    }

    public void setbWriter(String bWriter) {
        this.bWriter = bWriter;
    }

    public String getbContent() {
        return bContent;
    }

    public void setbContent(String bContent) {
        this.bContent = bContent;
    }

    public int getBreadCnt() {
        return breadCnt;
    }

    public void setBreadCnt(int breadCnt) {
        this.breadCnt = breadCnt;
    }

    public String getBwriteDate() {
        return bwriteDate;
    }

    public void setBwriteDate(String bwriteDate) {
        this.bwriteDate = bwriteDate;
    }
    
    public int getType() {
    	return type;
    }
    
    public void setType(int type) {
    	this.type = type;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bNum=" + bNum +
                ", bTitle='" + bTitle + '\'' +
                ", bWriter='" + bWriter + '\'' +
                ", bContent='" + bContent + '\'' +
                ", breadCnt=" + breadCnt +
                ", bwriteDate='" + bwriteDate + '\'' +
                '}';
    }
}
