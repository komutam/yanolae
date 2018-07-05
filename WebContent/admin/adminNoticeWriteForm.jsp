<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/config/path.jspf"%>

<html>
<head>
<title>공지사항 작성</title>
<style type="text/css">
	* {margin:0 auto;}
	.admin_notice_write {width:700px; text-align:center; margin-top:20px;}
</style>
</head>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<body>
	<form name="writeForm" method="post" action="${ctxpath}/admin/adminNoticeWritePro.do">
	<input type="hidden" name="pageNum" value="${pageNum }">
		<table class="table table-bordered admin_notice_write">
			<tr>
				<td colspan="2"><h2>공지사항 작성</h2></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" class="form-control" name="noti_subject"></td>
			</tr>
			<tr>
				<td>날짜</td>
				<td><input type="text" class="form-control" name="noti_date" value="${date }" readonly="readonly"></td>

			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" class="form-control" cols="50" name="noti_content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="btn btn-default" value="작성">
					<input type="button" class="btn btn-default" value="목록보기" onclick="document.location.href='${ctxpath}/admin/adminNoticeListForm.do?pageNum=${pageNum}'">
				</td>
			</tr>
		</table>
	</form>
</body>
</c:if>
</html>