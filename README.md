프로젝트 기본 정보
=================
- JAVA VERSION
  - 1.8
- Plugin
  - lombok
  - mapstruct
- swagger url
  - http://localhost:8088/swagger-ui.html
- local db
  - H2 console url
    - http://localhost:8088/test_db/login.jsp
  - H2 consle 접속 정보
     - http://localhost:8088/test_db
     - Id : sa
     - pw : (패스워드 없습니다.)
     - driver : org.h2.Driver
     - url : jdbc:h2:file:./SampleDB/test_db
     > 인텔리제이 local DB 연동시
      url을 아래 처럼 절대경로로 잡아주셔야합니다.
      jdbc:h2:file:C:\Users\kgh\Desktop\upchain\commonss-api\SampleDB\test_db;AUTO_SERVER=TRUE