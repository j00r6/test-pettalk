
# 환경구성

- spring fw 3.2.0
- java 17
- lombok, h2, jackson, swagger

# 기능

ver 1.0 
- aop 구조를 간단히 확인할 수 있는 filter, interceptor 구조
- 펫시터가 수락한 자신의 산책돌봄의뢰들의 시간대 조회
- 펫시터가 중복 의뢰를 수락하지 못하도록 방어로직이 있는 산책돌봄 생성기능

# 구동방법

- java app 구동 후, http://localhost:8080/swagger-ui/index.html 접속


# 테스트방법

- 시간대 조회 api : http://localhost:8080/swagger-ui/index.html#/time-controller/findTimeListByPetSitter
    - 테스트 데이터 : 펫시터 아이디는 1~5 사이에서 랜덤생성되어 있는 상태
- 산책돌봄 생성 api : http://localhost:8080/swagger-ui/index.html#/time-controller/createBoard
    - 테스트 데이터 : 펫시터 아이디는 1~5 사이 랜덤생성, 산책돌봄 시간대는 9월16일에만 존재
    - 정상거래 테스트 : 인풋시간을 9월15일과 같은 다른 시간대로 신청
    - 중복거래 테스트 : 인풋시간을 9월16일 내 00~23 시 같이 크게 설정
    - 
      `    {
      "memberId": 123,
      "petSitterId": 1,
      "title": "게시물 제목",
      "content": "게시물 내용",
      "status": 1,
      "startTime": "2023-09-15 10:00:01",
      "endTime": "2023-09-15 11:00:01"
      }`