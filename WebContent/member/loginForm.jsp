<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../config/path.jspf" %>
<html>
<head>

<script type="text/javascript">
function focusIt(){
	document.loginForm.id.focus();
}//focusIt end

function checkIt(){
	inputForm=eval("document.loginForm");
	//eval함수
	/* 1.인자로 받은 문자열을 수치화 한다.
	문자열의 내용이 숫자라면 실제로 숫자로 바뀐다.
	
	인자로 받은 문자열의 내용이 자바스크립트가 인실 할 수 있는
	객체의 형태라면 문자열을 받아서 자바스크립트 객체로 리턴한다.
	eval() 함수는 파싱도 한다.
	*/
	
	if(!document.loginForm.id.value){
		alert("아이디를 입력해주세요");
		document.loginForm.id.focus();
		return false;
	}
	if(!document.loginForm.passwd.value){
		alert("암호를 입력해주세요");
		document.loginForm.password.focus();
		return false;
	}
}//checkIt end

</script>

	<title>loginForm.jsp</title>
</head>
<body onLoad="focusIt();">
	
	<div class="container">
	<!-- 로그인하지 않은상태 -->
	<c:if test="${empty sessionScope.memId}">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top:20px;">
				<form method="post" action="${ctxpath}/member/loginPro.do" name="loginForm" onSubmit="return checkIt();">
					<h3 style="text-align:center;">로그인 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="id" maxlength="20">
					</div>
					
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="passwd" maxlength="20">
					</div>
					
					<input type="submit" class="btn btn-primary form-control" value="로그인">
					
					<input type="button" class="btn btn-primary form-control" value="회원가입" style="margin-top:10px;" onClick="location='${ctxpath}/member/insertForm.do'">
					
					<input type="button" class="btn btn-primary form-control" value="취소" style="margin-top:10px;" onClick="location='${ctxpath}/index.jsp'">
				</form>
			</div>
		</div>
		</c:if>
	<!-- 로그인을 한 상태 임시 테이블-->
	<c:if test="${!empty sessionScope.memId}">
	
		<table width="900" height="90%" align="center" border="1">
		<tr>
		<td width="60%" height="10%" bgcolor="ivory">
		${sessionScope.memId}님 반갑습니다. ${sessionScope.memLevel }
		</td>
		
		<td width="40%" colspan="2" bgcolor="ivory">
	
		${sessionScope.memId}님 방문을 환영합니다.<br>
		
		<%----로그아웃 폼 --%>
		<form method="post" action="${ctxpath}/member/logoutPro.do">
			<input type="submit" value="로그아웃">
		</form>
		<form method="post" action="${ctxpath}/member/updateForm.do">
			<input type="submit" value="회원정보 변경">
		</form>
		</td>
		</tr>
		</table>
		
	</c:if>
		
	</div>

</body>
</html>