package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping
	public ResponseEntity<List<NoticeDto>> selectAll() {
		List<NoticeDto> list = noticeService.selectAll();
		
		if (list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{num}")
	public ResponseEntity<NoticeDto> selectNotice(@PathVariable("num") int num) {
		NoticeDto dto = noticeService.selectNotice(num);
		
		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> insertNotice(@RequestBody NoticeDto dto) {
		boolean result = noticeService.insertNotice(dto);
		
		if (!result) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{num}")
	public ResponseEntity<String> updateNotice(@RequestBody NoticeDto dto, @PathVariable("num") int num) {
		boolean result = noticeService.updateNotice(dto);
		
		if (!result) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{num}")
	public ResponseEntity<String> deleteNotice(@PathVariable("num") int num) {
		boolean result = noticeService.deleteNotice(num);
		
		if (!result) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/search/{title}")
	public ResponseEntity<List<NoticeDto>> searchTitle(@PathVariable("title") String title) {
		List<NoticeDto> list = noticeService.searchTitle(title);
		
		if (list == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}

