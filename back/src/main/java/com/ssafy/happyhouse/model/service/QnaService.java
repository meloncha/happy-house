package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.QnaDao;
import com.ssafy.happyhouse.model.dto.QnaDto;
import com.ssafy.happyhouse.model.mapper.QnaMapper;


@Service
public class QnaService {
	@Autowired
	private QnaDao dao;

	public int insert(QnaDto dto) {
		return dao.insert(dto);
	}
	public List<QnaDto> selectAll(){
	return dao.selectAll();	
	}

	public int qnaModify(QnaDto QnaDto) {
		return dao.qnaModify(QnaDto);
	}

	public int Delete(int id) {
		return dao.Delete(id);
	}
	
	public QnaDto selectOne(int id) {
		return dao.selectOne(id);
	}
}
