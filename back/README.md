관통 프로젝트 제출 방법

# 관통프로젝트: HappyHouse*Vue*대전*03반*차민석, 황영준

### 제출일: 2020.11.16일 23시 59분

### 교수님 안녕하세요, 오늘 첫번째로 웹엑스 코칭받았던 차민석, 황영준조 입니다. 저희가 저번 프로젝트때 회원관리 rest server 부분밖에 구현하지 못했습니다. 
### 그리고 오늘은 vuetify를 적용시켜보려고 시도하다 시간을 허비하고 오늘의 기본 프로젝트 목표인 QnA 게시판 (vue, server)완성을 목표로 삼았습니다.
### 오늘 프로젝트의 기본 요구사항을 충족한 후 부족한 부분을 보강하기위해 지난 시간에 구현하지 못했던 부분을 구현하고자 시도했습니다.
### 최종적으로 이번 프로젝트의 기본사항과 지난 프로젝트의 기본사항 일부를 완성할 수 있었습니다.
### 남은 최종 프로젝트 기간동안 부족한 만큼 더 열심히 참여하여 스스로 만족할 만한 결과를 얻을 수 있도록 노력하겠습니다. 감사합니다.

### 참여 페어

- 차민석, 황영준

### 처리된 요구사항 목록 - Spring

| 난이도 | 구현기능                         | 세부                           | 작성여부(O/X) |
| :----: | -------------------------------- | ------------------------------ | :-----------: |
|  기본  | 메인페이지                       |                                |       O       |
|  기본  | 회원관리페이지                   | 회원정보 등록화면              |       O       |
|  기본  | 회원관리페이지                   | 회원정보 수정화면              |       O       |
|  기본  | 회원관리페이지                   | 회원정보 삭제화면              |       O       |
|  기본  | 회원관리페이지                   | 회원정보 검색화면              |       O       |
|  기본  | 로그인/로그아웃 페이지           |                                |       O       |
|  기본  | 아파트 실거래가 검색,결과 페이지 | 전체검색화면                   |       O       |
|  기본  | 아파트 실거래가 검색,결과 페이지 | 상세검색화면                   |       O       |
|  기본  | 아파트 실거래가 검색,결과 페이지 | 동별화면                       |       X       |
|  기본  | 아파트 실거래가 검색,결과 페이지 | 아파트별검색화면               |       X       |
|  추가  |                                  | 비밀번호찾기/사이트맵/메뉴구성 |       X       |
|  추가  | 관심지역 동네 업종 정보          |                                |       X       |
|  추가  | 관심지역 대기 오염 정보          |                                |       X       |
|  심화  |                                  | 웹사이트 소개                  |       O       |
|  심화  |                                  | 공지사항                       |       X       |

### 처리된 요구사항 목록 - Vue

| 난이도 | 구현기능                 | 세부            | 작성여부(O/X) |
| :----: | ------------------------ | --------------- | :-----------: |
|  기본  | QnA 게시판 Vue           | 게시글 목록     |       O       |
|  기본  | QnA 게시판 Vue           | 게시글 등록     |       O       |
|  기본  | QnA 게시판 Vue           | 게시글 수정     |       O       |
|  기본  | QnA 게시판 Vue           | 게시글상세 조회 |       O       |
|  기본  | 서버측 구현              | 게시글 등록     |       O       |
|  기본  | 서버측 구현              | 게시글 수정     |       O       |
|  기본  | 서버측 구현              | 게시글 삭제     |       O       |
|  기본  | 서버측 구현              | 게시글 상세조회 |       O       |
|  기본  | 서버측 구현              | 게시글 목록     |       O       |
|  추가  | 동네 업종 정보 조회      |                 |       X       |
|  추가  | 동네 환경 점검 정보 조회 |                 |       X       |
|  심화  | 메인 페이지 or 추가기능  |                 |       X       |


<span style="color:red">
* 작성된 기능은 반드시 캡쳐되어야 합니다.<br>
* 추가로 구현한 기능을 표에 추가시키세요.
</span>

### 실행화면 캡쳐 -

TODO: 요구사항 목록에서 완료 처리된 사항의 캡쳐 이미지를 등록하세요.

구현 기능: Spring (Controller, Model(Dao, Service, Dto)
![실행화면캡쳐](./화면캡쳐/화면캡쳐_0001_주택정보_web.png)

# Controller

- BoardController 
  ![BoardController](./readme_img/BoardController.png)

- HouseMapController 
    ![HouseMapController](./readme_img/HouseMapController.png)

- MemberController 
     ![MemberController1](./readme_img/MemberController1.png)
  ![MemberController2](./readme_img/MemberController2.png)

- QnaController
  ![QnaController](./readme_img/QnaController.png)

  # dto

  - BoardDto 
    ![BoardDto](./readme_img/BoardDto.png)

 - BoardPageDto 
   ![BoardPageDto](./readme_img/BoardPageDto.png)

 - HouseInfoDto 
    ![HouseInfoDto](./readme_img/HouseInfoDto.png)

 - MemberDto 
    ![MemberDto](./readme_img/MemberDto.png)

 - QnaDto 
    ![QnaDto](./readme_img/QnaDto.png)

 - SidoGugunCodeDto 
 ![SidoGugunCodeDto](./readme_img/SidoGugunCodeDto.png)

# mapper

- BoardMapper 
  ![HouseMapMapper](./readme_img/BoardMapper.png)

- HouseMapMapper 
  ![HouseMapMapper](./readme_img/HouseMapMapper.png)

- MemberMapper 
  ![MemberMapper](./readme_img/MemberMapper.png)

- QnaMapper 
  ![QnaMapper](./readme_img/QnaMapper.png)

# service

  - BoardService
  ![BoardService1](./readme_img/BoardService1.png)
  
  - BoardService
  ![BoardService2](./readme_img/BoardService2.png)
  
  - HouseMapService 
    ![HouseMapService](./readme_img/HouseMapService.png)

  - MemberService 
    ![MemberService](./readme_img/MemberService.png)

  - QnaService 
    ![QnaService](./readme_img/QnaService.png)


  구현 기능: 전체 프로그램 동작/오류 없이 전체 동작 1

# front

- Notice
![Notice](./readme_img/Notice.png)

- QnaSearch
![QnaSearch](./readme_img/QnaSearch.png)

- QnaComment
![QnaComment](./readme_img/QnaComment.png)

- QnaUpdate
![QnaUpdate](./readme_img/QnaUpdate.png)

- QnaUpdate2
![QnaUpdate2](./readme_img/QnaUpdate2.png)

- QnaUpdate3
![QnaUpdate3](./readme_img/QnaUpdate3.png)

- QnaDetail
![QnaDetail](./readme_img/QnaDetail.png)

- QnaMain
![QnaMain](./readme_img/QnaMain.png)

- AptSearchByDongNumber
![AptSearchByDongNumber](./readme_img/AptSearchByDongNumber.png)

- Main
![Main](./readme_img/Main.png)

- About
![About](./readme_img/About.png)

- Members(Admin)
![Members(Admin)](./readme_img/Members(Admin).png)

- DeleteMember
![DeleteMember](./readme_img/DeleteMember.png)

- MemberLogin
![MemberLogin](./readme_img/MemberLogin.png)

- memberRegist
![memberRegist](./readme_img/memberRegist.png)

- updateMember
![updateMember](./readme_img/updateMember.png)

- updateMember2
![updateMember2](./readme_img/updateMember2.png)

- memberdetail
![memberdetail](./readme_img/memberdetail.png)

- loginstatus
![loginstatus](./readme_img/loginstatus.png)















