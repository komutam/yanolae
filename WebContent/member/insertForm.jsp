<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../config/path.jspf" %>

<html>
<head>
<style type="text/css">
thead h4{
 text-align:center;
}
thead{
	color:white;
	background:#4169E1;
}
.register, .cansel{
	text-align:center;
}
</style>

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
<!-- 유효성 체크 -->
<script type="text/javascript">
	function checkMem(){
		if(document.userForm.id.value == ""){
			alert("아이디를 입력해주세요");
			document.userForm.id.focus();
			return false;
		}
		
		for(i=0; i<document.userForm.id.value.length; i++){
			ch=document.userForm.id.value.charAt(i);
			if(!(ch>="0" && ch<="9") && !(ch>="a" && ch<="z") && !(ch>="A" && ch<="Z")){
				alert("아이디는 대소문자와 숫자만 입력가능합니다");
				document.userForm.id.focus();
				return false;
			}
		}
		if(document.userForm.id.value.length<4 || document.userForm.id.value.length>12){
			alert("아이디를 4~12까지 입력해주세요");
			document.userForm.id.focus();
			return false;
		}
		
		if(document.userForm.passwd.value == ""){
			alert("비밀번호를 입력해주세요");
			document.userForm.passwd.focus();
			return false;
		}
		if(document.userForm.passwd.value.length < 6){
			alert("비밀번호는 6자 이상 입력해야 합니다.");
			document.userForm.passwd.focus();
			return false;
		}
		if(document.userForm.passwd.value == document.userForm.id.value){
			alert("아이디와 비밀번호가 같습니다.");
			document.userForm.passwd.focus();
			return false;
		}
		if(document.userForm.passwd2.vlaue == ""){
			alert("비밀번호를 한번더 입력해주세요");
			document.userForm.passwd2.focus();
			return false;
		}
		if(document.userForm.passwd.value != document.userForm.passwd2.value){
			alert("비밀번호가 일치하지 않습니다");
			document.userForm.passwd2.focus();
			return false;
		}
		if(document.userForm.name.value == ""){
			alert("이름을 입력해주세요");
			document.userForm.name.focus();
			return false;
		}
		if(document.userForm.name.value.length < 2){
			alert("이름을 2자 이상 입력해주세요");
			document.userForm.name.focus();
			return false;
		}
		if(document.userForm.phone.value == ""){
			alert("전화번호를 입력해주세요");
			document.userForm.phone.focus();
			return false;
		}
		for(i=0; i<document.userForm.phone.value.length; i++){
			ch=document.userForm.phone.value.charAt(i);
			if(!(ch>="0" && ch<="9")){
				alert("숫자만 입력할 수 있습니다");
				document.userForm.phone.focus();
				return false;
			}
		}
		if(document.userForm.zipcode.value == ""){
			alert("우편번호를 입력해주세요");
			document.userForm.zipcode.focus();
			return false;
		}
		if(document.userForm.addr.value == ""){
			alert("나머지 주소를 입력해주세요");
			document.userForm.addr.focus();
			return false;
		}
		if(document.userForm.birth.value == ""){
			alert("생년월일을 입력해주세요");
			document.userForm.birth.focus();
			return false;
		}
		for(i=0; i<document.userForm.birth.value.length; i++){
			ch=document.userForm.birth.value.charAt(i);
			if(!(ch>="0" && ch<="9")){
				alert("생년월일은 숫자만 입력해주세요");
				document.userForm.birth.focus();
				return false;
			}
		}
		if(document.userForm.email.value == ""){
			alert("이메일을 입력해주세요");
			document.userForm.email.focus();
			return false;
		}
		return true;
	}//check end
</script>

<!-- 중복체크 ajax 시작 -->
<script type="text/javascript">
//insertCheckFunction->중복체크 버튼을 누르면 실행되는 함수 
function insertCheckFunction() {
	if($('#id').val()==''){
		alert("ID를 입력해주세요");
		$('#id').focus();
	}else{
        //ajax : jquery안에 포함되어있는 것
        $.ajax({
            type:'POST', 
            url:"checkId.jsp", //여기로 가서 function을 실행
            data:"id="+$('#id').val(),
            dataType:'JSON',
            success:function(data) { //data 받아와서 아래와 같이 처리
                if (data.check == 1) { 
                	alert("사용중인 ID");
					$('#id').val("").focus(); //사용중인 아이디가 있으면 없애라
                } else {
                	alert("사용 가능한 ID");
					$('#passwd').focus();
                }
            }//success function
        });
    }//else end
}//insertCheckFunction() end
    //-------------------------------------
    //비밀번호 체크 스크립트
    //passwordCheckFunction -> 비번1,2 실시간 비교하여 뿌려주기
    function passwordCheckFunction(){
        var passwd = $('#passwd').val();
        var passwd2 = $('#passwd2').val();
         
        if(passwd!=passwd2){
            $('#passwordCheckMessage').html("비밀번호 불일치");
        }
        else{
            $('#passwordCheckMessage').html("");
        }
    }
</script>
<style type="text/css">
	.container1 {padding-top:70px;}
</style>
<title>insertForm.jsp</title>
</head>
<body>

	<div class="container1" id="insertContainer">
        <form method="post" name="userForm" action="${ctxpath}/member/insertPro.do" onSubmit="return checkMem();">
            <table class="table table-bordered table-hover" style="text-align:center; border:1px solid #dddddd;">
                <thead>
                    <tr>
                        <th colspan="3"><h4>회원가입</h4></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td style="width: 110px;"><h5>아이디</h5></td>
                        <td><input class="form-control" type="text" id="id"
                            name="id" maxlength="20" placeholder="사용할 아이디를 입력해주세요"></td>
                            <!-- 아래에 보면 ID CHECK button이 있고 그 버튼을 누르면 insertCheckFunction이 실행 -->
                        <td style="width: 110px;"><button class="btn btn-primary" onClick="insertCheckFunction();" type="button">
                        	중복확인</button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>비밀번호</h5></td>
                        <!-- onkeyup으로 클릭시 스크립트 함수인 passwordCheckFunction을 실행 -->
                        <td colspan="2"><input class="form-control" type="password"
                            onkeyup="passwordCheckFunction();" id="passwd"
                            name="passwd" maxlength="20" placeholder="비밀번호를 입력해 주세요"></td>
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>비밀번호 확인</h5></td>
                        <!-- password가 일치해야하는 것이기 때문에 아래에도 함수를 추가 -->
                        <td colspan="2" class="pass2"><input class="form-control" type="password"
                            onkeyup="passwordCheckFunction();" id="passwd2"
                            name="passwd2" placeholder="비밀번호 확인">
                            <!-- passwordCheckMessage id가 있네요 위에 passwordCheckFunction을 보면 이값이 정해진다(현재는 빈공간)-->
                        <h5 style="color: red;" id="passwordCheckMessage" class="pwdch"></h5>
                        </td>
                        
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>이 름</h5></td>
                        <td colspan="2"><input class="form-control" type="text"
                            id="name" name="name" maxlength="20" placeholder="사용할 이름을 입력해 주세요"></td>
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>전화번호</h5></td>
                        <td colspan="2"><input class="form-control" type="text"
                            id="phone" name="phone" maxlength="20" placeholder="전화번호를 입력해주세요"></td>
                    </tr>
                     <tr>
						<td style="width: 110px;">우편번호</td>
						<td colspan="2" class="addzip">
							<input class="form-control" type="text" name="zipcode" id="zipcode" placeholder="우편번호" style="width:95px;">
							<input type="button" value="주소찾기" onclick="openDaumPostcode()" class="btn btn-primary">
						</td>
					</tr>
                    <tr>
                        <td style="width: 110px;"><h5>주 소</h5></td>
                        <td colspan="2">
                        	<input class="form-control" type="text" id="addr" name="addr" placeholder="현재 거주중인 주소를 입력">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>생년월일</h5></td>
                        <td colspan="2"><input class="form-control" type="text"
                            id="birth" name="birth" maxlength="20" placeholder="생년월일을 입력해 주세요"></td>
                    </tr>
                    <tr>
                        <td style="width: 110px;"><h5>이메일</h5></td>
                        <td colspan="2"><input type="email" class="form-control" type="email" id="email" name="email" maxlength="20" placeholder="현재 사용중인 이메일 주소를 입력해주세요"></td>
                    </tr>
                    
                </tbody>

            </table>
            <div class="register">
            	<input class="btn btn-primary" type="submit" value="가입완료">
            </div>
            <div class="cansel">
            	<input class="btn btn-primary" type="button" value="취소" onClick="location.href='${ctxpath}/index.jsp'">
            </div>
            
        </form>
    </div>
</body>
</html>