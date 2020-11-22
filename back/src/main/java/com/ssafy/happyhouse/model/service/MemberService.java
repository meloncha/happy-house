package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;
@Service
public class MemberService {
	
	@Autowired
	private SqlSession sqlSession;
	
	public MemberDto login(MemberDto dto) {
		return sqlSession.getMapper(MemberMapper.class).login(dto);   //둘다 뭐라도 있으면 아이디 패스워드 같은거 찾아봐 mapper야!
	}
	
	public int insertMember(MemberDto dto) {
		return sqlSession.getMapper(MemberMapper.class).insertMember(dto);
	}
	public int updateMember(MemberDto dto) {
		return sqlSession.getMapper(MemberMapper.class).updateMember(dto);
	}
	public int deleteMember(String id) {
		return sqlSession.getMapper(MemberMapper.class).deleteMember(id);
	}
	public List<MemberDto> memberList() {
		return sqlSession.getMapper(MemberMapper.class).memberList();
	}

	public MemberDto detail(String id) {
		return sqlSession.getMapper(MemberMapper.class).detail(id);
	}
}
