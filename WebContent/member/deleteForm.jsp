<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../config/path.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{margin:0 auto;}
	.delete_member {width:300px; margin-top:20px; text-align:center; padding:0; !important;}
	.delete_member a{width:100%; height:100%; display:block; }
</style>
</head>
<body>
	<table class="table table-bordered delete_member">
		<tr>
			<td colspan="2">
				정말로 탈퇴하시겠습니까?
			</td>
		</tr>
		<tr>
			<td>
				<a href="${ctxpath}/member/deletePro.do?id=${memId}">예</a>
			</td>
			<td>
				<a href="${ctxpath}/member/myPageForm.do">아니오</a>
			</td>
		</tr>
	</table>
	
	
</body>
</html>