<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/config/path.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	* {margin:0 auto;}
	.admin_room_update {width:600px; margin-top:20px; text-align:center;}
</style>
</head>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<body>
	<form method="post" name="adminRoomUpdateForm" action="${ctxpath }/admin/adminRoomUpdatePro.do" enctype="multipart/form-data">
		<c:forEach var="adminRoomlist" items="${adminRoomList}">
			<table class="table table-bordered admin_room_update">
				<tr>
					<td colspan="2">
						<img class="accom_image" src="${ctxpath }/img/${adminRoomlist.room_image}" alt="no">
					</td>
				</tr>
				<tr>
					<td>방 이름</td>
					<td><input type="text" class="form-control" name="room_name" value="${adminRoomlist.room_name}"></td>
				</tr>
				<tr>
					<td>방 종류</td>
					<td><input type="text" class="form-control" name="room_type" value="${adminRoomlist.room_type}"></td>
				</tr>
				<tr>
					<td>대실 비용</td>
					<td><input type="text" class="form-control" name="rentable_price" value="${adminRoomlist.rentable_price}"></td>
				</tr>	
				<tr>
					<td>숙박 비용</td>
					<td><input type="text" class="form-control" name="accom_price" value="${adminRoomlist.accom_price}"></td>
				</tr>	
				<tr>
					<td>방 인원수</td>
					<td><input type="text" class="form-control" name="room_capa" value="${adminRoomlist.room_capa}"></td>
				</tr>	
				<tr>
					<td>방 갯수</td>
					<td><input type="text" class="form-control" name="room_count" value="${adminRoomlist.room_count}"></td>
				</tr>	
				<tr>
					<td>이미지 선택</td>
					<td>현재 이미지 : ${adminRoomlist.room_image}
						<input type="file" id="room_update_file" name="room_image">
					</td>
				</tr>
				<tr>
					<td colspan="2"><div id="holder"></div></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" class="btn btn-default" value="수정">
						<input type="reset" class="btn btn-default" value="취소">
						<input type="button" class="btn btn-default" value="돌아가기" onclick="location.href='${ctxpath}/admin/adminAccomUpdateForm.do?accom_code=${adminRoomlist.accom_code}&pageNum=1'">
						<input type="hidden" name="accom_code" value="${adminRoomlist.accom_code}">
					</td>
				</tr>
			</table>
		</c:forEach>
	</form>
	
<script>
	var upload = document.getElementById("room_update_file"),
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