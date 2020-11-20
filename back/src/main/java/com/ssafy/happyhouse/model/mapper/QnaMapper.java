package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.QnaDto;

@Mapper
public interface QnaMapper {
	public List<QnaDto> selectAll();
	public int insertqna(QnaDto dto);
	public int Delete(int id);
	public int qnaModify(QnaDto dto);
	public QnaDto selectOne(int id);
}
