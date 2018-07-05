<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/config/path.jspf" %>

<html>
<c:if test="${sessionScope.memLevel != 0 }">
관리자 아이디로 로그인해주세요.
</c:if>
<c:if test="${sessionScope.memLevel == 0 }">
<head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
function openDaumPostcode(){
	new daum.Postcode({
		oncomplete:function(data){
			//document.getElementById('post1').value=data.postcode1;
			//document.getElementById('post2').value=data.postcode2;
			document.getElementById('zipcode').value=data.postcode1+"-"+data.postcode2;
			document.getElementById('addr').value=data.address;
 		}
		
	}).open();
}//openDaumPostcode()---
</script>

<title>회원정보 수정 </title>

</head>
<body>
<h1> 회원정보 수정 </h1>
<div class="container" id="insertContainer">
        <form method="post" name="userForm" action="${ctxpath}/admin/adminMemberUpdatePro.do">
            <table class="table table-bordered table-hover" style="text-align:center; border:1px solid #dddddd;">
                <thead>
                    <tr>
                        <th colspan="3"><h4>회원정보 수정</h4></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td style="width: 110px;"><h5>아이디</h5></td>
                        <td><input class="form-control" type="text" id="id"
                            name="id" maxlength="20" value="${memInfo.id}" readonly="readonly"></td>
                            <!-- 아래에 보면 ID CHECK button이 있고 그 버튼을 누르면 insertCheckFunction이 실행 -->
                        <td style="width: 110px;">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>비밀번호</h5></td>
                        <!-- onkeyup으로 클릭시 스크립트 함수인 passwordCheckFunction을 실행 -->
                        <td colspan="2"><input class="form-control" type="password"
                            onkeyup="passwordCheckFunction();" id="passwd"
                            name="passwd" maxlength="20" placeholder="비밀번호를 입력해 주세요" value="${memInfo.passwd }"></td>
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>비밀번호 확인</h5></td>
                        <!-- password가 일치해야하는 것이기 때문에 아래에도 함수를 추가 -->
                        <td colspan="2" class="pass2"><input class="form-control" type="password"
                            onkeyup="passwordCheckFunction();" id="passwd2"
                            name="passwd2" placeholder="비밀번호 확인" value="${memInfo.passwd }">
                            <!-- passwordCheckMessage id가 있네요 위에 passwordCheckFunction을 보면 이값이 정해진다(현재는 빈공간)-->
                        <h5 style="color: red;" id="passwordCheckMessage" class="pwdch"></h5>
                        </td>
                        
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>이 름</h5></td>
                        <td colspan="2"><input class="form-control" type="text"
                            id="name" name="name" maxlength="20" placeholder="사용할 이름을 입력해 주세요" value="${memInfo.name}"></td>
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>전화번호</h5></td>
                        <td colspan="2"><input class="form-control" type="text"
                            id="phone" name="phone" maxlength="20" placeholder="전화번호를 입력해주세요" value="${memInfo.phone}"></td>
                    </tr>
                     <tr>
						<td style="width: 110px;">우편번호</td>
						<td colspan="2" class="addzip">
							<input class="form-control" type="text" name="zipcode" id="zipcode" placeholder="우편번호" style="width:95px;" value="${memInfo.zipcode}"> 
							<input type="button" value="주소찾기" onclick="openDaumPostcode()" class="btn btn-primary">
						</td>
					</tr>
                    <tr>
                        <td style="width: 110px;"><h5>주 소</h5></td>
                        <td colspan="2">
                        	<input class="form-control" type="text" id="addr" name="addr" placeholder="현재 거주중인 주소를 입력" value="${memInfo.addr}">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>생년월일</h5></td>
                        <td colspan="2"><input class="form-control" type="text"
                            id="birth" name="birth" maxlength="20" placeholder="생년월일을 입력해 주세요" value="${memInfo.birth}"></td>
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>이메일</h5></td>
                        <td colspan="2"><input type="email" class="form-control" type="email" id="email" name="email" maxlength="20" placeholder="현재 사용중인 이메일 주소를 입력해주세요" value="${memInfo.email}"></td>
                    </tr>
                    
                    <tr>
                   <td style="width: 110px;"><h5>회원등급</h5></td>    
                   <td colspan=2>
                   <select name="member_level">
                   <option value="${memInfo.member_level }">${memInfo.member_level }</option>
                   <option value="0">0(관리자)</option>
                   <option value="1">1</option>
                   <option value="2">2</option>
                   <option value="3">3</option>
                   <option value="4">4</option>
                   <option value="5">5</option>
                   <option value="6">6</option>
                   <option value="7">7</option>
                   <option value="8">8</option>
                   <option value="9">9(일반회원)</option>   
                   </select>
                                 
                    </tr>
                    
                </tbody>

            </table>
            <div class="register">
            	<input class="btn btn-primary" type="submit" value="수정완료">
            </div>
            <div class="cansel">
            	<input class="btn btn-primary" type="button" value="취소" onClick="onClick=location.href='${ctxpath}/admin/adminMemberForm.do'">
            </div>
            
        </form>
    </div>



</body>
</c:if>

</html>