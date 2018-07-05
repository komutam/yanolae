<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/config/path.jspf"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>고객 예약 정보 페이지</title>
<script>
	function deleteReser(path, num){
		var a = confirm('예약을 취소하시겠습니까?');
		if(a==true){
			location.href=path + "/member/myReservationPro.do?reser_number=" + num;
		}
	}
</script>
</head>
<body>
	
		<table class="table table-bordered">
			<tr align="center">
				<td colspan="10"><h3>예약 현황 페이지</h3></td>
			</tr>
			<tr>
				<th>숙박 업소</th>
				<th>숙박 업소 주소</th>
				<th>숙박 업소 번호</th>
				<th>방이름</th>
				<th>예약 날짜</th>
				<th>대여 종류</th>
				<th>입실날짜</th>
				<th>퇴실날짜</th>
				<th>방가격</th>
				<th>메뉴</th>
			</tr>
			<c:if test="${!empty reserlist}">
			<c:forEach var="reserlist" items="${reserlist }">
					<tr>
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
							<input type="button" class="btn btn-primary" name="reser_delete" value="취소" onclick="javascript:deleteReser('${ctxpath}', '${reserlist.reser_number}')">
						</td>
					</tr>
			</c:forEach>
			</c:if>
				<c:if test="${empty reserlist}">
					<tr align="center">
						<td colspan="10">예약 내역이 없습니다</td>
					</tr>
				</c:if>
		</table>
	
</body>
</html>