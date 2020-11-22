package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.dto.MemberDto;

public interface MemberMapper {
	public MemberDto login(MemberDto dto);  //파라미터  같은 이름으로 받고
	public int insertMember(MemberDto dto);
	public int updateMember(MemberDto dto);
	public int deleteMember(String id);
	public List<MemberDto> memberList();
	public MemberDto detail(String userid);
}
