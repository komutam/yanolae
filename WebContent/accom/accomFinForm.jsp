<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/config/path.jspf" %>
<html>
<head>
<title>결제 완료 페이지</title>
<style type="text/css">
	* {margin:0 auto;}
	.accom_fin {width:600px; margin-top:20px; text-align:center;}
</style>
</head>
<body>
	<table class="table table-bordered accom_fin">
		<tr>
			<td>결제가 완료되었습니다.</td>
		</tr>
		<tr>
			<td>
				<input type="button" class="btn btn-default" value="메인으로" onclick="location.href='${ctxpath}/index.jsp'">
 				<input type="button" class="btn btn-default" value="예약목록 확인" onclick="location.href='${ctxpath}/member/myReservationForm.do'">
			</td>
		</tr>
	</table>
	
 	</body>
</html>