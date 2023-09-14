# Front
# 포인트를 쌓고 적립하는 KarinaPoint

<br>

[![gif](/asset/machine_state_gif.gif)](https://youtu.be/TqG5Wmtujec)
🔼 클릭하면 UCC로 이동합니다 !

<br>

##  💁 간단 소개

### "누구나 쉽게 포인트를 쌓고 적립하는 서비스"
회원이 가입하고 이벤트와 자신의 포인트, 출석과 룰렛을 통한 포인트를 쌓고 유저간 선물 기능으로 주고 받을 수 있는 사이트 (휴대폰, 웹 전용)


- 🔗 LINK : https://smilekarina-point.vercel.app
- 🎼 Period : 2023.07.31 ~ 2023.09.18 (8주간 진행)
- 📑 Notion : https://cold-eel-56c.notion.site/9cb79d2785454b389f4975887cb60727?v=87c07bf232d74e28b8d15dbb880a3abe&pvs=4
<br>

### "작업 파일"
- 📑 Event Stoming : https://miro.com/app/board/uXjVMlIqv7U=/?share_link_id=479010144120
- 📑 ERD : https://www.erdcloud.com/d/tQn8bohpKTm3xmkdy
- 📑 API, 요구사항 정의서, WBS : https://docs.google.com/spreadsheets/d/187mT7rzsiOdJvDZn1QiEwpiCblH_ZM3xRNI8xBltHow/edit?usp=sharing
<br>

## ✨ 기능 및 화면
### 1) 메인 화면
### Frontend  
<div align="space-between">  
  <img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/1.png" width="200" height="400"/>
  <img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/4.png" width="200" height="400"/>
  <img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/5.png" width="200" height="400"/>
</div>

- 모든 서비스를 접근 할수 있는 페이지 입니다.
<br>
<div align="space-between">  
  <img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/2.png" width="200" height="400"/>
  <img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/3.png" width="200" height="400"/>
</div>
- 앱으로 설치해서 PWA를 이용해서 볼수 있는 기능을 제공합니다.

<br>
<img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/6.png" width="200" height="400"/>
- UX를 위해서 다크모드를 지원합니다.
<br>
### 2) 로그인 화면
<img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/7.png" width="200" height="400"/>
- 회원가입에는 아이디 찾기, 비밀번호 찾기, 회원가입, 아이디 저장, 자동 로그인 기능을 지원합니다.
<div align="space-between">  
  <img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/17.png" width="200" height="400"/>
  <img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/18.png" width="200" height="400"/>
</div>
- 또한 네이버와 카카오톡을 통해 로그인을 할 수 있습니다.(단 회원가입한 유저에 의해 가능)

<br>

### 3) 로그인 후 메인 화면
<div align="space-between">  
<img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/19.png" width="200" height="400"/>
<img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/20.png" width="200" height="400"/>
</div>
- 로그인 한 유저는 자신의 포인트를 확인하고 자신의 포인트카드를 연결할 수 있습니다.
- 멤버십 서비스, 출석체크, 로그인, 쿠폰, 이벤트 등 다양한 기능에 접근할 수 있게 됩니다.
- 로그인 하지 않았을 때 접근 한 경우 로그인 페이지로 리다이랙트 됩니다.

<br>

### 4) 출석체크와 룰렛기능
<img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/33.gif" width="200" height="400"/>
- 로그인한 유저는 하루에 한번 룰렛을 할 수 있습니다.
<img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/34.png" width="200" height="400"/>
- 로그인한 유저는 하루에 한번 출석을 할 수 있습니다.
- 10일 연속 출석을 한 경우 추가적인 포인트를 획득할 수 있습니다.
<br>

### 5) 매장 찾기 기능
<div align="space-between">  
<img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/36.png" width="200" height="400"/>
<img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/37.png" width="200" height="400"/>
</div>
- 프랜차이즈 종류마다 분기해서 매장을 찾을 수 있습니다.
- 해당 프랜차이즈 지점을 선택해서 자주 찾는 매장(자신의 매장)으로 등록할 수 있습니다.

<br>

### 6) 이벤트 기능
<div align="space-between">  
  <img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/38.png" width="200" height="400"/>
  <img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/39.png" width="200" height="400"/>
</div>

- 진행 이벤트, 종료 이벤트, 당첨 이벤트를 확인할 수 있습니다.
- 해당 이벤트가 몇일 남았는지 종료되었는지 시각적으로 알려줍니다.

<br>

### 7) 쿠폰 기능
<div align="space-between">  
  <img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/40.png" width="200" height="400"/>
  <img src="https://github.com/ShinsegaePointCloneCode/Back/blob/develop/exec/captureImg/41.png" width="200" height="400"/>
</div>
- 다양한 쿠폰을 제공합니다.
- 해당 쿠폰을 다운했을 경우 바코드를 줍니다. 그리고 전체 다운을 통해 UX를 도와줍니다.

<br>

---

<br>

## 📃 데이터 명세


<br>

## 🏋️‍♂️ 기술 스택
### Frontend  

<div align="center">  
  <a href="https://nextjs.org/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/nextjs.png" alt="NextJS" height="50" /></a> 
  <a href="https://www.typescriptlang.org/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/typescript-original.svg" alt="TypeScript" height="50" /></a>  
  <a href="https://www.w3schools.com/css/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/css3-original-wordmark.svg" alt="CSS3" height="50" /></a>  
  <a href="https://www.javascript.com/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/javascript-original.svg" alt="JavaScript" height="50" /></a>  
  <a href="https://en.wikipedia.org/wiki/HTML5" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/html5-original-wordmark.svg" alt="HTML5" height="50" /></a>   
</div>




### Backend  

<div align="center">  
  <a href="https://docs.spring.io/spring-framework/docs/3.0.x/reference/expressions.html#:~:text=The%20Spring%20Expression%20Language%20(SpEL,and%20basic%20string%20templating%20functionality." target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/springio-icon.svg" alt="Spring Boot" height="50" /></a>  
  <a href="https://redis.io/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/redis-original-wordmark.svg" alt="Redis" height="50" /></a>  
  <a href="https://www.java.com/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/java-original-wordmark.svg" alt="Java" height="50" /></a>  
</div>




### DevOps  

<div align="center">  
  <a href="https://cloud.google.com/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/google_cloud-icon.svg" alt="GCP" height="50" /></a>  
  <a href="https://www.linux.org/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/linux-original.svg" alt="Linux" height="50" /></a>  
  <a href="https://github.com/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/git-scm-icon.svg" alt="Git" height="50" /></a>
  <a href="https://www.mysql.com/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/mysql-original-wordmark.svg" alt="MySQL" height="50" /></a>  
  <a href="https://www.jenkins.io/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/jenkins-icon.svg" alt="Jenkins" height="50" /></a>  
  <a href="https://www.nginx.com/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/nginx-original.svg" alt="Nginx" height="50" /></a>
  <a href="https://www.docker.com/" target="_blank"><img style="margin: 10px" src="https://profilinator.rishav.dev/skills-assets/docker-original-wordmark.svg" alt="Docker" height="50" /></a>  
</div>

<br>

## ⚙ 아키텍처


<br>

## 🖥️ 실행 방법
[포팅 메뉴얼]()에 자세히 작성되어 있습니다😊 참고해 주세요 !

<br>

## 팀원 소개
![image](./exec/팀원.png)

## 개발 참고 자료
[PR 보내는 방법](./Contribute.md)

[Git 참고자료](./Git.md)

[상위 브랜치에서 작업한 경우](./UseUpper.md)
