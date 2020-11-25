package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.QnaDto;
import com.ssafy.happyhouse.model.service.QnaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/qna")
public class QnaController {
	@Autowired
	private QnaService service;

	@GetMapping("/{id}")
	public QnaDto selectOne(@PathVariable("id") int id) {
		return service.selectOne(id);
	}
	
	@GetMapping("/list")
	public List<QnaDto> list(){
		return service.selectAll();
	}  // QnaDto 타입 리스트로 리턴 함
	
	@PostMapping("/add")
	public String add(@RequestBody QnaDto dto) {  //qnadto 객체로 받아서 전달
		return service.insert(dto)==1?"true":"false";   //글 등록 성공여부
	}// true false 스트링으로 리턴 
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		return service.Delete(id)==1?"success":"fail"; //글 삭제 성공여부 
	}// true false 스트링으로 리턴 
	
	@PutMapping("/modify")
	public String qnaModify(@RequestBody QnaDto dto) {	
		return service.qnaModify(dto) ==1?"success":"fail";
	}
}
