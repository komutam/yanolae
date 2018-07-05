<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/config/path.jspf"%>

<html>
<head>
<title>공지사항 수정폼</title>
<style type="text/css">
	* {margin:0 auto;}
	.admin_notice_update {width:700px; margin-top:20px; text-align:center;}
</style>
</head>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<body>
	<form name="updateForm" method="post" action="${ctxpath }/admin/adminNoticeUpdatePro.do" multipart>
		<table class="table table-bordered admin_notice_update">
			<tr>
				<td colspan="2"><h2>글수정</h2></td>
			</tr>
			<tr>
				<td width="20%">제목</td>
				<td width="80%"><input type="text" class="form-control" name="noti_subject" value="${dto.noti_subject }"></td>
			</tr>
			<tr>
				<td>날짜</td>
				<td><input type="text" class="form-control" name="noti_date" value="${dto.noti_date }" readonly="readonly"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea class="form-control" rows="10" cols="50" name="noti_content">${dto.noti_content }</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="hidden" class="btn btn-default" name="noti_num" value="${dto.noti_num }">
					<input type="hidden" class="btn btn-default" name="pageNum" value="${pageNum }">
					<input type="submit" class="btn btn-default" value="글수정">
					<input type="button" class="btn btn-default" value="목록보기" onclick="document.location.href='${ctxpath}/admin/adminNoticeListForm.do?pageNum=${pageNum}'">
				</td>
			</tr>
		</table>
	</form>
</body>
</c:if>
</html>