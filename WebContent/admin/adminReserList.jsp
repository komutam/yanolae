<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/config/path.jspf"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 확인 패이지</title>
<script>
	function deleteReser(path, num){
		var a = confirm('예약을 취소하시겠습니까?');
		if(a==true){
			location.href=path + "/admin/adminReserDelPro.do?reser_number=" + num;
		}
	}
</script>
</head>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<body>
	
		<table class="table table-bordered">
			<tr align="center">
				<td colspan="13"><h3>예약 확인 페이지</h3></td>
			</tr>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>숙박업소</th>
				<th>숙박업소주소</th>
				<th>숙박업소번호</th>
				<th>방이름</th>
				<th>예약날짜</th>
				<th>대여종류</th>
				<th>입실날짜</th>
				<th>퇴실날짜</th>
				<th>방가격</th>
				<th>메뉴</th>
			</tr>			
			<c:if test="${!empty reserlist}">
			<c:forEach var="reserlist" items="${reserlist }">
					<tr>
						<td>${reserlist.id }</td>
						<td>${reserlist.name }</td>
						<td>${reserlist.phone }</td>
						<td>${reserlist.accom_name }</td>
						<td>${reserlist.accom_addr }</td>
						<td>${reserlist.accom_number }</td>
						<td>${reserlist.room_name }</td>
						<td>${reserlist.regdate }</td>
						<td>${reserlist.reser_type }</td>
						<td>${reserlist.reser_startdate }</td>
						<td>${reserlist.reser_enddate }</td>
						<td>${reserlist.reser_price }</td>
						<td>
							<input type="button" class="btn btn-default" name="reser_delete" value="취소" onclick="javascript:deleteReser('${ctxpath}', '${reserlist.reser_number}')">
						</td>
					</tr>
			</c:forEach>
			</c:if>
				<c:if test="${empty reserlist}">
					<tr align="center">
						<td colspan="10">예약 내역이 없습니다</td>
					</tr>
				</c:if>
			<tr>
				<td colspan="13" align="center">
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
							<a href="${ctxpath }/admin/adminReserList.do?pageNum=${startPage-10}">
								[이전블럭]</a>
						</c:if>
	
						<%-- 페이지 처리 --%>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<a href="${ctxpath }/admin/adminReserList.do?pageNum=${i}">[${i}]</a>
						</c:forEach>
	
	
						<%-- 다음블럭 --%>
						<c:if test="${endPage<Pagecount }">
							<a href="${ctxpath }/admin/adminReserList.do?pageNum=${startPage+10}">[다음블럭]</a>
						</c:if>
					</c:if>
				</td>
			</tr>
		</table>
	
</body>
</c:if>
</html>