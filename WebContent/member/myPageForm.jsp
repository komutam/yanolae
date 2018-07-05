<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../config/path.jspf" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>myPageForm.jsp</title>
<style type="text/css">
	*{margin:0 auto;}
	.mypage {width:300px; text-align:center; margin-top:20px; }
	
</style>
</head>
<body>
	<table class="table table-bordered mypage">
		<tr>
			<td><a href="${ctxpath}/member/updateForm.do?id=${memId}" id="update">회원정보 수정</a></td>
		</tr>
		<tr>
			<td><a href="${ctxpath}/member/deleteForm.do?id=${memId}">회원정보 탈퇴</a></td>
		</tr>
		<tr>
			<td><a href="${ctxpath}/member/myReservationForm.do">예약 현황</a></td>
		</tr>
	</table>
	
	
	

</body>
</html>