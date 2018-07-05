<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../config/path.jspf" %>
회원가입을 축하합니다.

<c:set var="memId" value="${id}" scope="session"/>
<meta http-equiv="Refresh" content="3;url=${ctxpath}/index.jsp">