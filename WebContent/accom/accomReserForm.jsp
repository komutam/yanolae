<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/config/path.jspf"%>
    


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>숙박 업소 등급별 방 리스트</title>
<style type="text/css">
	* {margin:0 auto;}
	.total {width:700; margin-top:20px;}
	.accom_reser {width:700px;}
	.room_reser_tab {position:relative; width:700px; height:250px;}
	.btn_reser {position: absolute; right:10px; bottom:10px;}
</style>
<script type="text/javascript">
function reserCheck(val, rentable_price, accom_price) { //예약 타입 체크하는 함수(대실, 숙박)
	document.getElementById("reser_type").value = val;
	if(val=="대실"){
		document.getElementById("room_price").value = rentable_price;
		
	} 
	if(val=="숙박"){
		document.getElementById("room_price").value = accom_price;
	}
	
}


</script>
</head>
<body>
	<c:forEach var="list" items="${roomDetailList }">
		<div class="total">
			<table class="table table-bordered accom_reser">
				<tr>
					<td rowspan="5"><img src="${ctxpath }/img/${list.room_image}"
						class="room_image_size"></td>
				</tr>

				<tr>
					<td>${list.room_name }</td>
				</tr>

				<tr>
					<td>기준 : ${list.room_capa }명</td>
				</tr>

				<c:if test="${list.rentable_price != 0 and diff <= 1}">
					<tr>
						<td>대실 금액 : ${list.rentable_price }</td>
					</tr>
				</c:if>

				<tr>
					<td>숙박 금액 : ${list.accom_price }</td>
				</tr>
			</table>


		<article class="room_reser_tle">
			<c:if test="${diff > 1 or list.rentable_price == 0}">
				<input id="room_buttons_sum" type="radio" name="room_tab"
					checked="checked" onclick="reserCheck(this.value, '${list.rentable_price}', '${list.accom_price}')" value="숙박" />
				<section class="room_buttons">
					<label for="room_buttons_sum" class="room_label">숙박 예약</label>
				</section>
				<div class="room_reser_tab">
					<table class="table table-bordered">
						<tr>
							<td>대여 기간</td>
							<td>${startdate } 19:00 이후 ~ ${enddate } 13:00 이전</td>		
						</tr>
					</table>
					<input type="button" class="btn btn-default btn_reser" value="예약하기" onclick="reserConfirm('${sessionScope.memId}', '${ctxpath}')">
				</div>
			</c:if>
			
			<c:if test="${diff == 1 and list.rentable_price != 0}">
				<input id="room_buttons_first" type="radio" name="room_tab"
					checked="checked" onclick="reserCheck(this.value, '${list.rentable_price}', '${list.accom_price}')" value="대실"/>
				<input id="room_buttons_second" type="radio" name="room_tab" onclick="reserCheck(this.value, '${list.rentable_price}', '${list.accom_price}')" value="숙박"/>
				<section class="room_buttons">
					<label for="room_buttons_first">대실 예약</label>
					<label for="room_buttons_second">숙박 예약</label>
				</section>
				
				<div class="room_reser_tab">
					<table class="table table-bordered">
						<tr>
							<td>대여 기간</td>
							<td>${startdate} 19:00 이후 ~ ${startdate} 24:00 이전</td>
						</tr>
					</table>	
					<input type="button" class="btn btn-default btn_reser" value="예약하기" onclick="reserConfirm('${sessionScope.memId}', '${ctxpath}')">
				</div>
				<div class="room_reser_tab">
					<table class="table table-bordered">
						<tr>
							<td>대여 기간</td>
							<td>${startdate} 19:00 이후 ~ ${enddate} 13:00 이전</td>
						</tr>
					</table>
					<input type="button" class="btn btn-default btn_reser" value="예약하기" onclick="reserConfirm('${sessionScope.memId}', '${ctxpath}')">
				</div>		
			</c:if>
		</article>
		
		<form method="post" name="roomReserForm" action="${ctxpath }/accom/accomReserPro.do">
			<input type="hidden" name="accom_code" value="${list.accom_code } ">
			<input type="hidden" name="room_name" value="${list.room_name }">
			<input type="hidden" name="room_capa" value="${list.room_capa }">
			<input type="hidden" name="room_price" id="room_price">
			<input type="hidden" name="reser_type" id="reser_type">
			<input type="hidden" name="startdate" value="${startdate }">
			<input type="hidden" name="enddate" value="${enddate }">
			<input type="hidden" name="diff" value="${diff }">
		</form>
		</div>
	<script type="text/javascript">
		var temp = $(':radio[name="room_tab"]:checked').val();
		if(temp=="대실"){
			document.getElementById("room_price").value = ${list.rentable_price};
		} 
		if(temp=="숙박"){
			document.getElementById("room_price").value = ${list.accom_price};
		}
		document.getElementById("reser_type").value = temp;
		
	</script>	
	</c:forEach>
</body>
</html>