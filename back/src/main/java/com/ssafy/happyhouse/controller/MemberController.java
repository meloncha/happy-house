package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.JwtService;
import com.ssafy.happyhouse.model.service.MemberService;

@RestController // Json 형태로 객체 데이터를 반환 하기위해 @RestController 사용하자
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberController {

	@Autowired
	private MemberService mService;
	
	@Autowired
	private JwtService jwtService;
	
	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@PostMapping(value = "/login", produces = "application/json;charset:utf-8") // produces
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto dto) { // 그냥 객체를 반환하지 말고 ResponseEntity로 상태값과 함께 반환하자
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		MemberDto loginUser = mService.login(dto); // service() -mapper(dao 기능 같은) 를 통해 같은거
																				// 있나 찾아보고 userinfoDto객체 생성
		if (loginUser == null) { 
			resultMap.put("message", "로그인 실패");
			return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST); // 받아온 userid userpwd 값이 없으면 아이디 비번둘 중 한개가 널
																			// -> 실패 (view단에서 확인하세요 띄우자)
		}
		
		String token = jwtService.create(loginUser);
		logger.trace("로그인 토큰정보 : {}", token);
		
		// 토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
		resultMap.put("auth-token", token);
		resultMap.put("id", loginUser.getId());
		resultMap.put("name", loginUser.getName());
		status = HttpStatus.ACCEPTED;
		
		return new ResponseEntity<>(resultMap, status); // id, pw 같은거 찾아서 세션 쿠키 처리하고 받아 왔음!

	} // (optional을 사용해서 널포인트익셉션 처리 축약할 수도 있다 https://mangkyu.tistory.com/70)ㅇㅇ
	
	@GetMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(">>>>>> " + jwtService.get(req.getHeader("auth-token")));
		try {
			// 사용자에게 전달할 정보이다.
//			String info = memberService.getServerInfo();
			
			resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
//
//			resultMap.put("status", true);
//			resultMap.put("info", info);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/detail/{userid}") 
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
		System.out.println(dto);
		int result = mService.updateMember(dto);
		if (result == 0) {
			return new ResponseEntity<>("failure", HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/delete/{id}", produces="application/json;charset:utf-8")
	public ResponseEntity<String> deleteMember(@PathVariable("id") String id) {
		int result = mService.deleteMember(id);
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
	
	@PostMapping("/kakao")
	public ResponseEntity<Map<String, Object>> kakaoLogin(@RequestBody MemberDto dto) { 
		MemberDto user = mService.detail(dto.getId());
		
		if (user == null) {
			mService.insertMember(dto);
			user = dto;
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		String token = jwtService.create(user);
		logger.trace("로그인 토큰정보 : {}", token);
		
		// 토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
		resultMap.put("auth-token", token);
		resultMap.put("id", user.getId());
		resultMap.put("name", user.getName());
		HttpStatus status = HttpStatus.ACCEPTED;
		
		return new ResponseEntity<>(resultMap, status);
	}
}
