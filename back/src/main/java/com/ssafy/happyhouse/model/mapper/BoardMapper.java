package com.ssafy.happyhouse.model.mapper;

import java.util.ArrayList;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BoardDto;

public interface BoardMapper {
	
	public int selectTotalCount(int type);
	public ArrayList<BoardDto> selectPage(Map<String, Integer> map);
	public int updateReadCnt(int bnum);
	public BoardDto selectBoard(int bnum);
	public int insertBoard(BoardDto dto);
	public int updateBoard(BoardDto dto);
	public int deleteBoard(int bnum);
}
