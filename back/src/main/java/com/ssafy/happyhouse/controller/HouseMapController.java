package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

@RestController
@RequestMapping("/map")
public class HouseMapController {

	@Autowired
	private HouseMapService hmService;
	
	@GetMapping(value="/sido", produces="application/json;charset=utf-8")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() {
		List<SidoGugunCodeDto> list = hmService.getSido();
		
		if (list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/gugun", produces="application/json;charset=utf-8")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestBody String sido) {
		List<SidoGugunCodeDto> list = hmService.getGugunInSido(sido);
		
		if (list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/dong", produces="application/json;charset=utf-8")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestBody String gugun) {
		List<HouseInfoDto> list = hmService.getDongInGugun(gugun);
		
		if (list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(value="/apt", produces="application/json;charset=utf-8")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestBody String dong) {
		List<HouseInfoDto> list = hmService.getAptInDong(dong);
		
		if (list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
