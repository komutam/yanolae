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
<script type="text/javascript">
	function deleteNotice(num , page ) {
		var check = confirm("글을 삭제하시겠습니까?");
		if (check == true) {
			location.href="${ctxpath}/admin/adminNoticeDeletePro.do?num="+num+"&pageNum="+page;
		}
	}
</script>
<style type="text/css">
	* {margin:0 auto;}
	.admin_notict_list {width:700px;}
</style>
</head>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<body>
	<b>글목록(전체글:${count })</b>
	<table class="table table-bordered admin_notict_list">
		<tr>
			<td align="center" width="90%">
				<h2>공 지 사 항</h2>
			</td>
			<td align="right" width="10%">
				<a href="${ctxpath}/admin/adminNoticeWriteForm.do?pageNum=${currentPage}">글쓰기</a>
			</td>
		</tr>
	</table>
	<c:if test="${count==0 }">
		<table class="table table-bordered admin_notict_list">
			<tr>
				<td align="center">게시판에 저장된 글이 없습니다.</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${count>0 }">
		<table class="table table-bordered admin_notict_list">
			<tr height="30">
				<td align="center" width="10%">번호</td>
				<td align="center" width="40%">글제목</td>
				<td align="center" width="30%">작성일</td>
				<td align="center" width="20%">메뉴</td>
			</tr>

			<c:forEach var="dto" items="${noticeList }">
				<tr>
					<td align="center" width=50><c:out value="${number }" /> <c:set
							var="number" value="${number-1 }" /></td>

					<td><a href=${ctxpath}/admin/adminNoticeContentForm.do?num=${dto.noti_num}&pageNum=${currentPage}>
							 ${dto.noti_subject } </a></td>
					<td>${dto.noti_date }</td>
					<td>						
						<input type="button" class="btn btn-default" value="삭제" onclick="deleteNotice('${dto.noti_num}','${currentPage }')">
						<input type="button" class="btn btn-default" value="수정" onclick="location.href='${ctxpath }/admin/adminNoticeUpdateForm.do?num=${dto.noti_num}&pageNum=${currentPage}'">
					</td>
				</tr>
			</c:forEach>
	
			<tr>
				<td colspan="4" align="center">
				
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
							<a href="${ctxpath }/admin/adminNoticeListForm.do?pageNum=${startPage-10}">
								[이전블럭]</a>
						</c:if>
	
						<%-- 페이지 처리 --%>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<a href="${ctxpath }/admin/adminNoticeListForm.do?pageNum=${i}">[${i}]</a>
						</c:forEach>
	
	
						<%-- 다음블럭 --%>
						<c:if test="${endPage<Pagecount }">
							<a href="${ctxpath }/admin/adminNoticeListForm.do?pageNum=${startPage+10}">
								[다음블럭]</a>
						</c:if>
					</c:if>
				</td>
			</tr>
		</table>
	</c:if>


</body>
</c:if>
</html>