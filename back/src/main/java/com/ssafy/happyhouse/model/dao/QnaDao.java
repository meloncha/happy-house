package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.QnaDto;
import com.ssafy.happyhouse.model.mapper.QnaMapper;

@Repository
public class QnaDao {
	@Autowired
	private SqlSessionTemplate template;
	
	public int insert(QnaDto QnaDto) {
		return template.getMapper(QnaMapper.class).insertqna(QnaDto);
	}
	
	public List<QnaDto> selectAll(){
		return template.getMapper(QnaMapper.class).selectAll();
	}
	
	public int qnaModify(QnaDto QnaDto) {   //변경하고 변경 완료 여부 boolean으로
		return template.getMapper(QnaMapper.class).qnaModify(QnaDto);

	}

	public int Delete(int id) {  //글번호 리턴 (id 글 이 삭제되었습니다.)
		return template.getMapper(QnaMapper.class).Delete(id);
	}
	
	public QnaDto selectOne(int id) {  //글번호 리턴 (id 글 이 삭제되었습니다.)
		return template.getMapper(QnaMapper.class).selectOne(id);
	}
}
