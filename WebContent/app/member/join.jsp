<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원 가입</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/join.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700&display=swap" rel="stylesheet">
  </head>
  <body>
    <div class="container">
      <!-- join처리 경로 잡기 -->
      <form action="${pageContext.request.contextPath}/member/joinOk.me" method="POST">
        <h2>회원 가입</h2>
        <div class="form-group">
          <label for="id">아이디</label>
          <input type="text" id="id" name="memberId" placeholder="아이디를 입력하세요" required />
          <span class="check-msg" id="check-id-msg"></span>
        </div>
        <div class="form-group">
          <label for="password">비밀번호</label>
          <input type="password" id="password" name="memberPassword" placeholder="비밀번호를 입력하세요" required />
          <span class="check-msg" id="check-pw-msg"></span>
        </div>
        <div class="form-group">
          <label for="name">이름</label>
          <input type="text" id="name" name="memberName" placeholder="이름을 입력하세요" required />
        </div>
        <div class="form-group">
          <label for="age">나이</label>
          <input type="number" id="age" name="memberAge" placeholder="나이를 입력하세요" required />
        </div>
        <div class="form-group">
          <label>성별</label>
          <div>
            <input type="radio" id="none" name="memberGender" value="N" checked />
            <label for="none">선택안함</label>
            <input type="radio" id="male" name="memberGender" value="M" />
            <label for="male">남성</label>
            <input type="radio" id="female" name="memberGender" value="F" />
            <label for="female">여성</label>
          </div>
        </div>
        <div class="form-group">
          <label for="email">이메일</label>
          <input type="email" id="email" name="memberEmail" placeholder="이메일을 입력하세요" required />
          <span class="check-msg" id="check-email-msg"></span>
        </div>
        <div class="form-group">
          <label for="address">주소</label>
          <input type="text" id="address" name="memberAddress" placeholder="주소를 입력하세요" required />
        </div>
        <div class="form-group">
          <label>약관 동의</label>
          <div class="agree-wrap">
            <label for="agree">회원 가입 약관에 동의합니다.</label>
            <input type="checkbox" id="agree" name="agree"  />
          </div>
        </div>
        <button type="submit">회원 가입</button>
      </form>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/join.js"></script>
  </body>
</html>
    