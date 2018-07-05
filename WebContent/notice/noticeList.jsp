<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/config/path.jspf"%>

<html>
<head>
<link type="text/css" href="style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>공지사항</title>
<style type="text/css">
	*{margin:0 auto;}
	.notice_list {width:700px; margin-top:-20px; text-align:center;}
	.notice_list_header {width:700px; margin-top:20px;}
</style>
</head>
<body>
	
	<table class="table table-bordered notice_list_header">
		<tr>
			<td><b>글목록(전체글:${count })</b></td>
		</tr>
		<tr>
			<td align="center">
				<h2>공 지 사 항</h2>
			</td>
		</tr>
	</table>
	<c:if test="${count==0 }">
		<table class="table table-bordered notice_list_header">
			<tr>
				<td align="center">게시판에 저장된 글이 없습니다.</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${count>0 }">
		<table class="table table-bordered notice_list">
			<tr height="30">
				<td width="10%">번호</td>
				<td width="60%">글제목</td>
				<td width="30%">작성일</td>
			</tr>

			<c:forEach var="dto" items="${noticeList }">
				<tr>
					<td><c:out value="${number }" /> <c:set
							var="number" value="${number-1 }" /></td>

					<td><a href=${ctxpath}/notice/noticeContentForm.do?num=${dto.noti_num}&pageNum=${currentPage}>
							 ${dto.noti_subject } </a></td>
					<td>${dto.noti_date }</td>

				</tr>
			</c:forEach>
		
			<tr>
				<td colspan="3">
					<%-- 블럭처리 페이지 처리 --%>
					<c:if test="${count>0 }">
						<%---이전블럭 --%>
						<c:set var="cnt" value="${count%pageSize==0?0:1 }" />
						<c:set var="pageCount"
							value="${count/pageSize+(count%pageSize==0?0:1 )}" />
						<c:set var="pageBlock" value="${10 }" />
						<fmt:parseNumber var="result" value="${currentPage/10 }"
							integerOnly="true" />
						<c:set var="startPage" value="${result*10 +1}" />
						<c:set var="endPage" value="${startPage+pageBlock-1 }" />
						<c:if test="${endPage>pageCount }">
							<c:set var="endPage" value="${pageCount }" />
						</c:if>
						<%-- 이전블럭 --%>
						<c:if test="${startPage>10 }">
							<a href="${ctxpath }/notice/noticeList.do?pageNum=${startPage-10}">
								[이전블럭]</a>
						</c:if>
	
						<%-- 페이지 처리 --%>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<a href="${ctxpath }/notice/noticeList.do?pageNum=${i}">[${i}]</a>
						</c:forEach>
	
	
						<%-- 다음블럭 --%>
						<c:if test="${endPage<Pagecount }">
							<a href="${ctxpath }/notice/noticeList.do?pageNum=${startPage+10}">
								[다음블럭]</a>
						</c:if>
					</c:if>
				</td>
			</tr>
		</table>
	</c:if>



</body>
</html>