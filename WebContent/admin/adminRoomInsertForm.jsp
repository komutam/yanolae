<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/config/path.jspf" %>   
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>adminRoominsert.jsp</title>
<style type="text/css">
	* {margin:0 auto;}
	.admin_room_insert {width:600px; margin-top: 20px; text-align:center;}
</style>
</head>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<body>
	<form method="post" name="adminRoomInsertForm" action="${ctxpath}/admin/adminRoomInsertPro.do?code=${accom_code}" enctype="multipart/form-data">
		<table class="table table-bordered admin_room_insert">
			<tr>
				<td>방 이름</td>
				<td><input type="text" class="form-control" name="room_name"></td>
			</tr>
			<tr>
				<td>방 종류</td>
				<td><input type="text" class="form-control" name="room_type"></td>
			</tr>
			<tr>
				<td>대실 비용</td>
				<td><input type="text" class="form-control" name="rentable_price"></td>
			</tr>
			<tr>
				<td>숙박 비용</td>
				<td><input type="text" class="form-control" name="accom_price"></td>
			</tr>
			<tr>
				<td>방 인원수</td>
				<td><input type="text" class="form-control" name="room_capa"></td>
			</tr>
			<tr>
				<td>방 갯수</td>
				<td><input type="text" class="form-control" name="room_count"></td>
			</tr>
			<tr>
				<td>방 이미지</td>
				<td>
					<input type="file" id="room_insert_file" name="room_image">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="holder"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="btn btn-default" value="추가">
					<input type="reset" class="btn btn-default" value="취소">
					<input type="button" class="btn btn-default" value="목록보기" onclick="location.href='${ctxpath}/admin/adminAccomUpdateForm.do?accom_code=${accom_code}&pageNum=1'">
				</td>
			</tr>
		</table>
	</form>
	
<script>
	var upload = document.getElementById("room_insert_file"),
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