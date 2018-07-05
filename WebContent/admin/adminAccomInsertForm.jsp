<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/config/path.jspf" %>   
<html>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{margin:0 auto;}
	.admin_accom_insert {width:600px; margin-top:20px; text-align:center;}
</style>
</head>
<body>
	<form method="post" name="adminAccomInsertForm" action="${ctxpath}/admin/adminAccomInsertPro.do" enctype="multipart/form-data">
		<table class="table table-bordered admin_accom_insert">
			<tr>
				<td>숙박 업소 이름</td>
				<td><input type="text" class="form-control" name="accom_name"></td>
			</tr>
			<tr>
				<td>숙박 업소 코드</td>
				<td><input type="text" class="form-control" name="accom_code"></td>
			</tr>
			<tr>
				<td>숙박 업소 주소</td>
				<td><input type="text" class="form-control" name="accom_addr"></td>
			</tr>
			<tr>
				<td>숙박 업소 지역</td>
				<td><input type="text" class="form-control" name="accom_location"></td>
			</tr>
			<tr>
				<td>숙박 업소 전화번호</td>
				<td><input type="text" class="form-control" name="accom_number"></td>
			</tr>
			<tr>
				<td>숙박 업소 예약시간</td>
				<td><input type="text" class="form-control" name="accom_resertime"></td>
			</tr>
			<tr>
				<td>숙박 업소 종류</td>
				<td>
					<select name="accom_kind" class="form-control">
						<option value="H">호텔</option>
						<option value="M">모텔</option>
						<option value="P">펜션</option>
						<option value="Q">게스트하우스</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>숙박 업소 이미지</td>
				<td>
					<input type="file" id="accom_insert_file" name="accom_image">
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
					<input type="button" class="btn btn-default" value="목록보기" onclick="location.href='${ctxpath}/admin/adminAccomListForm.do?pageNum=1'">
				</td>
			</tr>
		</table>
	</form>
	
<script>
	var upload = document.getElementById("accom_insert_file"),
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