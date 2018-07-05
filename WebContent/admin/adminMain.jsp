<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/config/path.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.gg{
		border-bottom:5px solid orange;
	}
</style>
</head>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<body>
<center><h2>관리자 페이지 메인 입니다. </h2></center><br>
<hr size="5" color="orange" align="center"><br>
<div align="center">

 <a href="${ctxpath }/admin/adminMemberForm.do" >회원정보 관리</a>
 <br><hr class=gg  size="5" color="orange" align="center"><br>
 
 <a href="${ctxpath}/admin/adminReserList.do">예약정보 보기</a><br>
 <hr class=gg  size="5" color="orange" align="center"><br>
 
 <a href="${ctxpath }/admin/adminNoticeListForm.do">공지사항 업데이트</a><br>
 <hr class=gg  size="5" color="orange" align="center"><br>
 
 <a href="${ctxpath}/admin/adminAccomListForm.do">숙박 업소 관리</a><br>
 <hr class=gg  size="5" color="orange" align="center"><br>
</div>

</body>
</c:if>
</html>