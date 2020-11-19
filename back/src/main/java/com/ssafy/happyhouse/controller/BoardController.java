package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.BoardPageDto;
import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.BoardService;

@RestController
@RequestMapping("/notice")
public class BoardController {

	@Autowired
	private BoardService bService;

	@PostMapping(value = "", produces = "application/json;charset:utf-8")
	public ResponseEntity<String> write(@RequestBody String title, String content, HttpSession session) {

		MemberDto loginInfo = (MemberDto) session.getAttribute("loginInfo");
		boolean result = bService.write(title, content, loginInfo, 0);

		if (result) {
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
		}
	}

	// 수정
	@GetMapping(value = "/page/{page}", produces = "application/json;charset:utf-8")
	public ResponseEntity<BoardPageDto> noticeList(@PathVariable("page") int page) {
		BoardPageDto pageDto = bService.makePage(page, 0); // 0 : notice
		if (pageDto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(pageDto, HttpStatus.OK);
	}

	@GetMapping(value = "/{bnum}", produces = "application/json;charset:utf-8")
	public ResponseEntity<BoardDto> read(@PathVariable("bnum") int bnum) {
		BoardDto dto = bService.getBoard(bnum);
		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PutMapping(value = "", produces = "application/json;charset:utf-8")
	public ResponseEntity<String> update(@RequestBody BoardDto dto) {
		int result = bService.updateBoard(dto);

		if (result == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{bnum}", produces = "application/json;charset:utf-8")
	public ResponseEntity<String> delete(@PathVariable("bnum") int bnum) {
		int result = bService.deleteBoard(bnum);

		if (result == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
