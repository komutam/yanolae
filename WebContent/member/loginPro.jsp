<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../config/path.jspf" %>

<c:if test="${logCheck==1}">
	<c:set var="memId" value="${id}" scope="session"/>
	<c:set var="memLevel" value="${dto.member_level}" scope="session"/>
<%-- 	<meta http-equiv="Refresh" content="0;url=/yanolae/member/loginForm.do">-0초 후에 넘어간다. --%>
	<c:if test="${memLevel == 0 }">
		<meta http-equiv="Refresh" content="0;url=${ctxpath}/admin/adminMain.do"><%---0초 후에 넘어간다. --%>
	</c:if>
	<c:if test="${memLevel != 0 }">
		<meta http-equiv="Refresh" content="0;url=${ctxpath}/index.jsp"><%---0초 후에 넘어간다. --%>
	</c:if>
</c:if>

<c:if test="${logCheck==-1}">
	<script>
		alert("아이디가 맞지 않습니다");
		history.back();
	</script>
</c:if>	

<c:if test="${logCheck==0}">
	<script>
		alert("비밀번호가 틀립니다");
		history.back();
	</script>
</c:if>