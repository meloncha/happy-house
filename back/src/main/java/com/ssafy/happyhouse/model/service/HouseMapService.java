package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HouseMapService {

	@Autowired
	private SqlSession sqlSession;
	
	public List<SidoGugunCodeDto> getSido() {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}
	public List<SidoGugunCodeDto> getGugunInSido(String sido) {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}
	public List<HouseInfoDto> getDongInGugun(String gugun) {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}
	public List<HouseInfoDto> getAptInDong(String dong) {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}
	public List<HouseInfoDto> getAptInName(String aptName) throws Exception{
		return sqlSession.getMapper(HouseMapMapper.class).getAptInName(aptName);
	}
}
