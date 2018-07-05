<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/config/path.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function roomUpdate(accom_code, room_type){
		location.href="${ctxpath}/admin/adminRoomUpdateForm.do?accom_code=" + accom_code + "&room_type=" + room_type;
	}
	
	function roomDelete(accom_code, room_type){
		var check = confirm('방 정보를 삭제하시겠습니까?');
		if(check==true){
			location.href="${ctxpath}/admin/adminRoomDeletePro.do?accom_code=" + accom_code + "&room_type=" + room_type;
		}
	}

</script>
<style type="text/css">
	* {margin:0 auto;}
	.admin_accom_update {width:600px; margin-top:20px; text-align:center;}
</style>
</head>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<body>
	<form method="post" name="adminAccomUpdateForm" action="${ctxpath }/admin/adminAccomUpdatePro.do?accom_code=${accom_code}&pageNum=${pageNum}" enctype="multipart/form-data">
		<c:forEach var="adminAccomUpdateList" items="${adminAccomlist}">
		<table class="table table-bordered admin_accom_update">
			<tr>
				<td colspan="2">
					<img class="accom_image" src="${ctxpath }/img/${adminAccomUpdateList.accom_image}" alt="no">
				</td>
			</tr>
			<tr>
				<td>숙박 업소 이름</td>
				<td><input type="text" class="form-control" name="accom_name" value="${adminAccomUpdateList.accom_name }"></td>
			</tr>
			<tr>
				<td>숙박 업소 주소</td>
				<td><input type="text" class="form-control" name="accom_addr" value="${adminAccomUpdateList.accom_addr }"></td>
			</tr>
			<tr>
				<td>숙박 업소 지역</td>
				<td><input type="text" class="form-control" name="accom_location" value="${adminAccomUpdateList.accom_location}"></td>
			</tr>
			<tr>
				<td>숙박 업소 전화번호</td>
				<td><input type="text" class="form-control" name="accom_number" value="${adminAccomUpdateList.accom_number}"></td>
			</tr>
			<tr>
				<td>숙박 업소 예약시간</td>
				<td><input type="text" class="form-control" name="accom_resertime" value="${adminAccomUpdateList.accom_resertime}"></td>
			</tr>
			<tr>
				<td>숙박 업소 종류</td>
				<td>
					<select name="accom_kind" class="form-control">
						<option value="H" ${adminAccomUpdateList.accom_kind eq "H" ? "selected" : ""}>호텔</option>
						<option value="M" ${adminAccomUpdateList.accom_kind eq "M" ? "selected" : ""}>모텔</option>
						<option value="P" ${adminAccomUpdateList.accom_kind eq "P" ? "selected" : ""}>펜션</option>
						<option value="Q" ${adminAccomUpdateList.accom_kind eq "Q" ? "selected" : ""}>게스트하우스</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>이미지 선택</td>
				<td>현재 이미지 : ${adminAccomUpdateList.accom_image}
					<input type="file" id="accom_update_file" name="accom_image">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="holder" style="{width:300; height: 300}"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="btn btn-default" value="수정">
					<input type="reset" class="btn btn-default" value="취소">
					<input type="button" class="btn btn-default" value="방 추가하기" onclick="location.href='${ctxpath}/admin/adminRoomInsertForm.do?accom_code=${adminAccomUpdateList.accom_code}'">
					<input type="button" class="btn btn-default" value="목록보기" onclick="location.href='${ctxpath}/admin/adminAccomListForm.do?pageNum=1'">
				</td>
			</tr>
		</table>
		</c:forEach>
	</form>
	
	
	
	<form method="post" name="adminRoomUpdateForm">
		<c:forEach var="adminRoomList" items="${adminRoomlist}">
			<table class="table table-bordered admin_accom_update">
				<tr>
					<td colspan="2">
						<img class="accom_image" src="${ctxpath }/img/${adminRoomList.room_image}" alt="no">
					</td>
				</tr>
				<tr>
					<td>숙박 업소 코드</td>
					<td>${adminRoomList.accom_code}</td>
				</tr>
				<tr>
					<td>방 이름</td>
					<td>${adminRoomList.room_name}</td>
				</tr>
				<tr>
					<td>방 종류</td>
					<td>${adminRoomList.room_type}</td>
				</tr>	
				<tr>
					<td>대실 비용</td>
					<td>${adminRoomList.rentable_price}</td>
				</tr>	
				<tr>
					<td>숙박 비용</td>
					<td>${adminRoomList.accom_price}</td>
				</tr>	
				<tr>
					<td>방 인원수</td>
					<td>${adminRoomList.room_capa}</td>
				</tr>	
				<tr>
					<td>방 갯수</td>
					<td>${adminRoomList.room_count}</td>
				</tr>	
				<tr>
					<td colspan="2">
						<input type="button" class="btn btn-default" value="수정" onclick="roomUpdate('${adminRoomList.accom_code}', '${adminRoomList.room_type}')">
						<input type="button" class="btn btn-default" value="삭제" onclick="roomDelete('${adminRoomList.accom_code}', '${adminRoomList.room_type}')">
					</td>
				</tr>
			</table>
		</c:forEach>
	</form>
	
	
	
<script>
	var upload = document.getElementById("accom_update_file"),
	holder = document.getElementById('holder')
	upload.onchange = function(e) {
		e.preventDefault();

		var file = upload.files[0], reader = new FileReader();
		reader.onload = function(event) {
			var img = new Image();
			img.src = event.target.result;
			img.width = 300;
			img.height = 300;
			holder.innerHTML = '';
			holder.appendChild(img);
		};
		reader.readAsDataURL(file);

		return false;
	};
</script>
</body>
</c:if>
</html>
