package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.NoticeDto;

@Mapper
public interface NoticeDao {

	public List<NoticeDto> selectAll();
	public NoticeDto selectNotice(int num);
	public int insertNotice(NoticeDto dto);
	public int updateNotice(NoticeDto dto);
	public int deleteNotice(int num);
	public int updateNoticeCnt(int num);
}
