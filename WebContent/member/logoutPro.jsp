<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../config/path.jspf" %>

<%---logout.jsp --%>
<c:remove var="memId" scope="session"/>
<c:remove var="memLevel" scope="session"/>

<meta http-equiv="Refresh" content="0;url=${ctxpath}/index.jsp">