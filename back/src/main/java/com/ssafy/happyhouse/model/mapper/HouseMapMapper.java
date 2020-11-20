package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;

public interface HouseMapMapper {
	
	public List<SidoGugunCodeDto> getSido();
	public List<SidoGugunCodeDto> getGugunInSido(String sido);
	public List<HouseInfoDto> getDongInGugun(String gugun);
	public List<HouseInfoDto> getAptInDong(String dong);
	public List<HouseInfoDto> getAptInName(String aptName) throws Exception;
}
