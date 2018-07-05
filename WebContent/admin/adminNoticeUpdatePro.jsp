<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/config/path.jspf" %>

<c:if test="${check==1}">
    <meta http-equiv="Refresh" content="0;url=${ctxpath }/admin/adminNoticeListForm.do?pageNum=${pageNum}">
    </c:if>
    
    <c:if test="${check!=1 }">
    수정되지 않았습니다. 
    <a href="javascript:history.back()">돌아가기</a>
    </c:if>