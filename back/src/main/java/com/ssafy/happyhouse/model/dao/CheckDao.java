package com.ssafy.happyhouse.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.SchoolInfoDto;
import com.ssafy.happyhouse.model.dto.SubwayInfoDto;

@Mapper
public interface CheckDao {
	public List<SchoolInfoDto> selectSchool(Map<String, Object> school);
	public List<SubwayInfoDto> selectSubway(Map<String, Object> checkstation);
}
