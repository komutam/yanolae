<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="../config/path.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style>
	.headerIn{
		padding-top:20px;
		font-size:19px;
		font-weight:bold;
	}
	.headerIn a{
		padding:0 10px;
	}
	.header_search{
		float:left;
		margin:15px 0 0 20px;
	}
	.header_buttons {float:right; min-width:200px;}
</style>

<title>header.jsp</title>
</head>
<body class="template_haeder">
	<%
		String id = null;
		if(session.getAttribute("memId") != null){
			id = (String)session.getAttribute("memId");
			//로그인한 사람이라면 해당 아이디 변수에 세션정보가 담기게 되고 아닌 사람은 널값이 담기게
		}
	%>
	
	<div class="main_header">
		<div class="title">
		<c:if test="${sessionScope.memLevel != 0 }">
			<a href="${ctxpath}/index.jsp"></c:if>
			<c:if test="${sessionScope.memLevel == 0 }"><a href="${ctxpath}/admin/adminMain.do"></c:if>
			<img src="${ctxpath }/img/logo2.png" class="logo_img" width="200px"></a>
		</div>
<!----------------------- ------------------------------>
		
		<c:if test="${sessionScope.memLevel != 0}">
		<div class="header_search">
			<form method="get" name="searchForm" action="${ctxpath }/accom/accomListForm.do" onsubmit="return search_clicks()">
				<select name="accomtype" id="accomtype" class="ccc form-control">
					<option value="H" ${param.accomtype eq "H" ? "selected" :""}>호텔</option>
					<option value="M" ${param.accomtype eq "M" ? "selected" :""}>모텔</option>
					<option value="P" ${param.accomtype eq "P" ? "selected" :""}>팬션</option>
					<option value="Q" ${param.accomtype eq "Q" ? "selected" :""}>게스트하우스</option>
				</select>
				<select name="accomlocation" id="accomlocation" class="ccc form-control">
					<option value="A" ${param.accomlocation eq "A" ? "selected" :""}>서울</option>
					<option value="B" ${param.accomlocation eq "B" ? "selected" :""}>부산</option>
					<option value="C" ${param.accomlocation eq "C" ? "selected" :""}>대전</option>
					<option value="D" ${param.accomlocation eq "D" ? "selected" :""}>광주</option>
					<option value="E" ${param.accomlocation eq "E" ? "selected" :""}>경기도</option>
					<option value="F" ${param.accomlocation eq "F" ? "selected" :""}>전라남도</option>
					<option value="G" ${param.accomlocation eq "G" ? "selected" :""}>충청남도</option>
				</select>
				
				<input type="text" name="startdate" id="startdate" value="${param.startdate }" class="datePicker" placeholder="예약 시작일을 입력하세요" readonly> ~ 
				<input type="text" name="enddate" id="enddate"  value="${param.enddate }" class="datePicker"  placeholder="예약 종료일을 입력하세요" readonly>
				<input type="hidden" name="pageNum" id="pageNum" value="1">
				<input type="button" name="btn" value="검색" onclick="search_clicks()" class="btn btn-default">
			</form>
		</div>
		</c:if>

		<div class="header_buttons">
		
			<%
			if(id == null){ //로그인 혹은 회원가입이 되어있지 않은 사람은 로그인이나 회원가입이 될 수 있도록.
			%>
			<div class="headerIn">
				<a href="${ctxpath}/member/loginForm.do">로그인</a>
				<a href="${ctxpath}/member/insertForm.do">회원가입</a>
			</div>
			<%
			}else{
			%>
			<div class="headerIn">
				${memId}님이 로그인 하셨습니다.
				<a href="${ctxpath}/member/logoutPro.do">로그아웃</a>
				<a href="${ctxpath}/member/myPageForm.do">마이페이지</a>
			</div>
			<%
			}
			%>
		</div>
		
	</div>
	
</body>
</html>