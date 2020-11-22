package com.ssafy.happyhouse.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.happyhouse.model.dto.KakaoProfile;
import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.dto.OAuthTokenKakao;
import com.ssafy.happyhouse.model.service.JwtService;
import com.ssafy.happyhouse.model.service.MemberService;

@Controller
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private JwtService jwtService;

	@GetMapping("/kakao/callback")
	public ResponseEntity<Map<String, Object>> kakaoCallback(String code) {

		// POST방식으로 key=value 데이터를 요청(카카오쪽으로)
		// Retrofit2, OkHttp, RestTemplate 등을 사용 가능

		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "1477c3cde72d97e6eb6a3fde455fd68a");
		params.add("redirect_uri", "http://localhost:7777/happyhouse/auth/kakao/callback");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - Post 방식으로 and response 변수의 응답 받는다
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthTokenKakao oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthTokenKakao.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token());

		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

		// Http 요청하기 - Post 방식으로 and response 변수의 응답 받는다
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest, String.class);

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// User 오브젝트 : username, password, email
		System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
		System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());

		System.out
				.println("happyhouse id : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
		System.out.println("happyhouse username : " + kakaoProfile.getKakao_account().getProfile().getNickname());
		System.out.println("happyhouse email : " + kakaoProfile.getKakao_account().getEmail());
		UUID tempPassword = UUID.randomUUID();
		System.out.println("happyhouse password : " + tempPassword);

		// 가입자 혹은 비가입자 체크해서 처리
		MemberDto originUser = memberService.detail(kakaoProfile.getId() + "");

		MemberDto kakaoUser = new MemberDto();

		if (originUser == null) {
			kakaoUser.setId(kakaoProfile.getId() + "");
			kakaoUser.setPwd(tempPassword.toString());
			kakaoUser.setEmail(kakaoProfile.getKakao_account().getEmail());
			kakaoUser.setName(kakaoProfile.getKakao_account().getProfile().getNickname());
			memberService.insertMember(kakaoUser);
		} else {
			kakaoUser = originUser;
		}
				
		// 로그인 처리
		String token = jwtService.create(kakaoUser);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("auth-token", token);
		resultMap.put("id", kakaoUser.getId());
		resultMap.put("name", kakaoUser.getName());
		
		HttpHeaders headers3 = new HttpHeaders();
		headers3.setLocation(URI.create("http://localhost:8080"));

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
}
