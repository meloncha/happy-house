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

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;

@RestController // Json 형태로 객체 데이터를 반환 하기위해 @RestController 사용하자
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberController {

	@Autowired
	private MemberService mService;

	@PostMapping(value = "/login", produces = "application/json;charset:utf-8") // produces
	public ResponseEntity<String> login(@RequestBody MemberDto dto) { // 그냥 객체를 반환하지 말고 ResponseEntity로 상태값과 함께 반환하자
		// @RequestParam 으로 map형식으로 파라미터 받아오고, 쿠키사용 위해 HttpServletResponsee 쓰자 쿠키는 아이디
		// 저장에 세션은 아이디 유지에 사용할거

		
		MemberDto mDto = mService.login(dto); // service() -mapper(dao 기능 같은) 를 통해 같은거
																				// 있나 찾아보고 userinfoDto객체 생성

		if (mDto == null) { 
			return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST); // 받아온 userid userpwd 값이 없으면 아이디 비번둘 중 한개가 널
																			// -> 실패 (view단에서 확인하세요 띄우자)
		}
		return new ResponseEntity<>("success", HttpStatus.OK); // id, pw 같은거 찾아서 세션 쿠키 처리하고 받아 왔음!

	} // (optional을 사용해서 널포인트익셉션 처리 축약할 수도 있다 https://mangkyu.tistory.com/70)ㅇㅇ
	
	
	
	@GetMapping(value = "/detail/{userid}", produces = "application/json;charset:utf-8") 
	public ResponseEntity<MemberDto> detail(@PathVariable("userid") String userid) { 

		MemberDto mDto = mService.detail(userid);

		if (mDto == null) { 
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(mDto, HttpStatus.OK);

	} // (optional을 사용해서 널포인트익셉션 처리 축약할 수도 있다 https://mangkyu.tistory.com/70)
	
	

	@GetMapping(value = "/logout", produces = "application/json;charset:utf-8")
	public String logout() {
//		session.invalidate();  //로그아웃 남아있는 세션 죽여
		return "redirect:/";  //이건 컨트롤러에서 해당 뷰를 보여주는 것이 아니라 redirect 오른쪽 주소로 url 요청 다시하는거(새로고침)
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////로그아웃 로그인 기능
	
	@PostMapping(value="/insert", produces="application/json;charset:utf-8")
	public ResponseEntity<String> insertMember(@RequestBody MemberDto dto) {
		dto.setUserinfo(0);
		int result = mService.insertMember(dto);
		if (result == 0) {
			return new ResponseEntity<>("failure", HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
	}
	
	@PutMapping(value="/update", produces="application/json;charset:utf-8")
	public ResponseEntity<String> updateMember(@RequestBody MemberDto dto) {
		int result = mService.updateMember(dto);
		if (result == 0) {
			return new ResponseEntity<>("failure", HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/delete", produces="application/json;charset:utf-8")
	public ResponseEntity<String> deleteMember(@RequestBody MemberDto dto) {
		System.out.println(dto.getId() + " " + dto.getPwd());
		int result = mService.deleteMember(dto);
		if (result == 0) {
			return new ResponseEntity<>("failure", HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
	}
	
	// admin 용
	@GetMapping(value="/list", produces="application/json;charset:utf-8")
	public ResponseEntity<List<MemberDto>> memberList() {
		List<MemberDto> list = mService.memberList();
		if (list == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		
	}
}
