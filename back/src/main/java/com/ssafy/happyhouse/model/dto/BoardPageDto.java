package com.ssafy.happyhouse.model.dto;

import java.util.List;

public class BoardPageDto {

    private List<BoardDto> boardList;
    private int curPage;
    private int startPage;
    private int endPage;
    private int totalPage;

    public BoardPageDto() {}

    public BoardPageDto(List<BoardDto> boardList, int curPage, int startPage, int endPage, int totalPage) {
        this.boardList = boardList;
        this.curPage = curPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.totalPage = totalPage;
    }

    public List<BoardDto> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<BoardDto> boardList) {
        this.boardList = boardList;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "NoticePageDto{" +
                "noticeList=" + boardList +
                ", curPage=" + curPage +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", totalPage=" + totalPage +
                '}';
    }
}
