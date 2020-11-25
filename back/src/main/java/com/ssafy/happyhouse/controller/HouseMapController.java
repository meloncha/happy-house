package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SchoolInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dto.SubwayInfoDto;
import com.ssafy.happyhouse.model.service.HouseMapService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {

	@Autowired
	private HouseMapService hmService;

	@GetMapping(value = "/sido", produces = "application/json;charset=utf-8")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() {
		List<SidoGugunCodeDto> list = hmService.getSido();

		if (list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/gugun/{sido}", produces = "application/json;charset=utf-8")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@PathVariable("sido") String sido) {
		List<SidoGugunCodeDto> list = hmService.getGugunInSido(sido);

		if (list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/dong/{gugun}", produces = "application/json;charset=utf-8")
	public ResponseEntity<List<HouseInfoDto>> dong(@PathVariable("gugun") String gugun) {
		List<HouseInfoDto> list = hmService.getDongInGugun(gugun);

		if (list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/school", produces = "application/json;charset=utf-8")
	public ResponseEntity<List<SchoolInfoDto>> school(@RequestBody Map<String, Object> school) {// checkedSchool:초중고 dong:동이름
		System.out.println(school.toString());

//		ArrayList<String> list = (ArrayList<String>) school.get("checkedSchool");

		List<SchoolInfoDto> result = hmService.getSchool(school);

		if (result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/station", produces = "application/json;charset=utf-8")
	public ResponseEntity<List<SubwayInfoDto>> subway(@RequestBody Map<String, Object> checkstation) {// check: TF, dong:동이름
																								
		System.out.println(checkstation.toString());
//		if(checkstation.containsKey(dong)==false) {
//			
//		}
		List<SubwayInfoDto> list = hmService.getStation(checkstation);
		
		if (list == null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/apt/{dong}", produces = "application/json;charset=utf-8")
	public ResponseEntity<List<HouseInfoDto>> apt(@PathVariable("dong") String dong) {
		List<HouseInfoDto> list = hmService.getAptInDong(dong);
		if (list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/apt/search/{aptName}")
	public ResponseEntity<List<HouseInfoDto>> searchApts(@PathVariable("aptName") String aptName) throws Exception {
		System.out.println(aptName);
		List<HouseInfoDto> list = hmService.getAptInName(aptName);

		if (list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
