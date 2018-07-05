<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/config/path.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function adminAccomDelConfirm(accom_code) { 
		var check = confirm("숙박 업소 정보를 삭제하시겠습니까?");
		if (check == true) {
			location.href="${ctxpath}/admin/adminAccomDeletePro.do?accom_code=" + accom_code;
		}
	}
	
	function adminAccomUpdate(accom_code, pageNum) { 
		location.href="${ctxpath}/admin/adminAccomUpdateForm.do?accom_code=" + accom_code + "&pageNum=" + pageNum;
	}
	
</script>
<style type="text/css">
	*{margin:0 auto;}
	.adminAccomListCon {width:600px; margin-top:20px 0 20px 0;}
	.fixa {margin-left: 50px;}
	.btn_div {position: fixed; width:100%; height:60px; top:80px; left:0;}
	.admin_accom_list {text-align:center;}
	.table_page {margin-bottom:20px;}
</style>
</head>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<body>
		<c:if test="${count == 0}">
			<input type="button" value="숙박 업소 추가" class="btn btn-primary fixa"  onclick="location.href='${ctxpath}/admin/adminAccomInsertForm.do'">
			<div><p>검색된 자료가 없습니다.</p>	</div>		
		</c:if>
		
		<c:if test="${count > 0 }">
		<div class="adminAccomListCon">
			<div class="btn_div">
				<input type="button" value="숙박 업소 추가" class="btn btn-primary fixa"  onclick="location.href='${ctxpath}/admin/adminAccomInsertForm.do'">
			</div>
			<form method="post" name="adminAccomListForm" action="${ctxpath }/adminAccomListForm.do">		
					<c:forEach var="adminAccomdto" items="${adminAccomlist}">
						<table class="table table-bordered admin_accom_list">
							<tr>
								<td colspan="2">
									<img class="accom_image" src="${ctxpath }/img/${adminAccomdto.accom_image}" alt="no">
								</td>
							</tr>
							<tr>
								<td>숙박 업소 이미지</td>
								<td>
									${adminAccomdto.accom_image}
								</td>
							</tr>
							<tr>
								<td>숙박 업소 이름</td>
								<td>${adminAccomdto.accom_name }</td>
							</tr>
							<tr>
								<td>숙박 업소 코드</td>
								<td>${adminAccomdto.accom_code }</td>
							</tr>
							<tr>
								<td>숙박 업소 주소</td>
								<td>${adminAccomdto.accom_addr }</td>
							</tr>
							<tr>
								<td>숙박 업소 지역</td>
								<td>${adminAccomdto.accom_location}</td>
							</tr>
							<tr>
								<td>숙박 업소 전화번호</td>
								<td>${adminAccomdto.accom_number}</td>
							</tr>
							<tr>
								<td>숙박 업소 예약시간</td>
								<td>${adminAccomdto.accom_resertime}</td>
							</tr>
							<tr>
								<td>숙박 업소 종류</td>
								<td>${adminAccomdto.accom_kind}</td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="button" class="btn btn-default" value="수정" onclick="adminAccomUpdate('${adminAccomdto.accom_code}', '${pageNum}')">
									<input type="button" class="btn btn-default" value="삭제" onclick="adminAccomDelConfirm('${adminAccomdto.accom_code}')">
								</td>
							</tr>
						</table>	
					</c:forEach>
					
					<table class="table_page">
						<tr>
							<td>
							<c:if test="${count > 0 }">
								<%-- 초기화 --%>
								<c:set var="pageCount" value="${count/pageSize + (count%pageSize==0 ? 0 : 1)}"/>
								<c:set var="pageBlock" value="${10 }"/>
								<fmt:parseNumber var="result" value="${currentPage/10}" integerOnly="true"/>
								<c:set var="startPage" value="${result * 10 + 1 }"/>
								<c:set var="endPage" value="${startPage + pageBlock - 1 }"/>	
										
								<c:if test="${endPage > pageCount}">
									<c:set var="endPage" value="${pageCount}" />
								</c:if>
												
								<%-- 이전 블럭 --%>
								<c:if test="${startPage > 10 }">
									<a href="${ctxpath }/admin/adminAccomListForm.do?pageNum=${startPage-10}">[이전 블럭]</a>
								</c:if>
								
								<c:forEach var="i" begin="${startPage }" end="${endPage }">
									<a href="${ctxpath }/admin/adminAccomListForm.do?pageNum=${i}">[${i}]&nbsp;</a>
								</c:forEach>
								
								<%-- 다음 블럭 --%>
								<c:if test="${endPage < pageCount}">
									<a href="${ctxpath }/admin/adminAccomListForm.do?pageNum=${startPage+10}">[다음 블럭]</a>
								</c:if>
							</c:if>
							</td>
						</tr>
					</table>
			</form>
		</div>	
		</c:if>
</body>
</c:if>
</html>