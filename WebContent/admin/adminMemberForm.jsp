<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/config/path.jspf"%>

<html>
<head>
<script type="text/javascript">
	function deleteMember(id , page ) {
		var check = confirm("삭제??");
		if (check == true) {
			location.href="${ctxpath}/admin/adminMemberDeletePro.do?id="+id+"&pageNum="+page;
		}
	}
	
	function search(){
		var ser=document.getElementById("search").value;
		location.href="${ctxpath}/admin/adminMemberForm.do?pageNum=${currentPage}&search="+ser;
	}
</script>
<link type="text/css" href="style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>memberList</title>
</head>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<body>
	<b>글목록(전체글:${count })</b>
	<table class="table table-bordered">
		<tr>
			<td align="center">
				<h2>회 원 목 록</h2>
			</td>
		</tr>
	</table>
	<c:if test="${count==0 }">
		<table class="table table-bordered">
			<tr>
				<td align="center">게시판에 저장된 글이 없습니다.</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${count>0 }">
		<table class="table table-bordered">
		<tr height=30>
			<td colspan="12" align="center"><input type="text" name="search" id="search" value="${search }">&nbsp;
			<input type="button" value="검색" class="btn btn-default" onclick=search()></td>
		</tr>
			<tr height="30">
				<td align="center" width=80>번호</td>
				<td align="center" width=100>아이디</td>
				<td align="center" width=250>이름</td>
				<td align="center" width=150>전화번호</td>
				<td align="center" width=210>지역번호</td>
				<td align="center" width=500>주소</td>
				<td align="center" width=100>생일</td>
				<td align="center" width=200>Email</td>
				<td align="center" width=250>가입일</td>
				<td align="center" width=150>회원등급</td>	
				<td align="center" width=100>삭제</td>	
				<td align="center" width=100>수정</td>	
							
			</tr>

			<c:forEach var="dto" items="${memberList }">

				<tr>
					<td align="center" width=50><c:out value="${number }" /> 
					<c:set var="number" value="${number-1 }" /></td>
					<td>${dto.id }</td>
					<td>${dto.name}</td>
					<td>${dto.phone}</td>
					<td>${dto.zipcode}</td>
					<td>${dto.addr}</td>
					<td>${dto.birth}</td>
					<td>${dto.email}</td>
					<td>${dto.regdate}</td>
					<td>${dto.member_level}</td>	
					<td>						
						<input type="button" value="삭제" class="btn btn-default" onclick="deleteMember('${dto.id}','${currentPage }')">
					</td>
					<td>
						<input type="button" value="수정" class="btn btn-default" onclick="location.href='${ctxpath }/admin/adminMemberUpdateForm.do?id=${dto.id}&pageNum=${currentPage}'">					
					</td>	
				</tr>
			</c:forEach>
	
	<%-- 블럭처리 페이지 처리 --%>
		<tr>
			<td colspan="12" align="center"><c:if test="${count>0 }">
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
						<a href="${ctxpath }/admin/adminMemberForm.do?pageNum=${startPage-10}&search=${search}">
							[이전블럭]</a>
					</c:if>
					<%-- 페이지 처리 --%>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<a href="${ctxpath }/admin/adminMemberForm.do?pageNum=${i}&search=${search}">[${i}]</a>
					</c:forEach>
					<%-- 다음블럭 --%>
					<c:if test="${endPage<Pagecount }">
						<a href="${ctxpath }/admin/adminMemberForm.do?pageNum=${startPage+10}&search=${search}">
							[다음블럭]</a>
					</c:if>

				</c:if></td>
		</tr>
	</table>
	</c:if>


</body>
</c:if>
</html>



