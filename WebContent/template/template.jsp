<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/config/path.jspf" %>

<html>
<head>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${ctxpath }/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="${ctxpath }/css/datepicker3.css" rel="stylesheet"
	type="text/css">
<link href="${ctxpath }/css/css.css?ver=1.8" rel="stylesheet" type="text/css">
<script src="${ctxpath }/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctxpath }/js/bootstrap-datepicker.js"
	type="text/javascript"></script>
<script src="${ctxpath }/js/bootstrap-datepicker.kr.js"
	type="text/javascript"></script>
<script type="text/javascript" src="${ctxpath }/js/javascript1.js"></script>
<script type="text/javascript" src="${ctxpath }/js/jquery.js"></script>


<title>template.jsp</title>
</head>
<body>

	<table width="100%">
		<tr>
			<td width="1500" valign="top" height="70px"><jsp:include
					page="/template/header.jsp" flush="false" /></td>
		</tr>

		<tr>
			<td width="100%" valign="body" height="800px">
			<c:if test="${empty CONTENT}">
					<jsp:include page="/template/main.jsp" flush="false" />
				</c:if> 				
				<jsp:include page="${CONTENT}" flush="false" />
				
				</td>
		</tr>

		<tr>
			<td width="100%" valign="bottom" align="center" height=""><jsp:include
					page="/template/footer.jsp" flush="false" /></td>
		</tr>
	</table>

</body>
</html>