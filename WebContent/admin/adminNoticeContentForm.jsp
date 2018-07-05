<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/config/path.jspf"%>
<html>

<head>
<link href="style.css" rel="stylesheet" type="text/css">
<title>공지사항</title>
<style type="text/css">
	* {margin:0 auto;}
	.admin_notice_content {width:700px; margin-top:20px; text-align:center}
</style>
</head>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<body>
	<table class="table table-bordered admin_notice_content">
		<tr>
			<td colspan="2" align="center"><h2>공지사항보기</h2></td>
		</tr>
		<tr>
			<td>글번호</td>
			<td>${dto.noti_num }</td>
		</tr>

		<tr>
			<td>작성일</td>
			<td>${dto.noti_date }</td>
		</tr>

		<tr>
			<td>글제목</td>
			<td>${dto.noti_subject }</td>
		</tr>

		<tr>
			<td>글내용</td>
			<td><pre>${dto.noti_content }</pre></td>
		</tr>

		<tr>
			<td colspan="2">
				<input type="button" class="btn btn-default" value="글목록" onclick="document.location.href='${ctxpath}/admin/adminNoticeListForm.do?pageNum=${pageNum}'">
			</td>
		</tr>
	</table>
</body>
</c:if>
</html>