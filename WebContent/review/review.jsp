<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../config/path.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
      $(function(){
    	  $(".reviewUpdate").hide();
    	  
          //리뷰 폼 변화하는 거
          $(".reviewFrom1").click(function(){
        	  $(this).parent().find(".reviewFrom1").hide();
        	  $(this).parent().find(".reviewDelete").hide();
        	  $(this).parent().find(".reviewUpdate").show();
        	  var text = $(this).parent().parent().find(".content").text();
        	  //alert(text);
//              $(this).parent().parent().find(".content").html("<textarea rows='3' cols='50' class='review'>${list.re_content}</textarea>");
              $(this).parent().prev(".content").html("<textarea rows='3' cols='50' class='review'></textarea>");
              $(this).parent().parent().find(".review").text(text);
          });
          
          //댓글 삭제 
          $(".reviewDelete").click(function(){
       	   var re_num =$(this).parent().find('.reviewValRe_num').val();
			location.href="${ctxpath}/review/reviewDelete.do?re_num="+re_num;
        	  
          })
          
          // 원상태로 돌아가는것
           $(".reviewUpdate").click(function(){
        	   $(this).parent().find(".reviewUpdate").hide();
        	   $(this).parent().find(".reviewFrom1").show();
         	  $(this).parent().find(".reviewDelete").show();

        	   var accom_code = $(this).parent().find('.reviewValAccom_code').val();
        	   var re_num =$(this).parent().find('.reviewValRe_num').val();
        	   var id =$(this).parent().find('.reviewValId').val();
        	   var starpoint = $(this).parent().find('.reviewValStarpoint').val();
        	   var re_content =$(this).parent().parent().find('.review').val();
        	   
        	   var reviewUpdateLocation= $(this);
        	    
        	    
		       var result = "";
         	   $.ajax({
        		   type:'POST', 
                   url:"reviewUpdate.jsp", //여기로 가서 function을 실행
                  
                   //아직 테스트를 다시해보아야함 데이터가 잘전달되는디 
                   data:"accom_code="+accom_code+"&re_num="+re_num+"&id="+id+"&starpoint="+starpoint+"&re_content="+re_content,
                   dataType:'JSON',
                   success:function(data) { //data 받아와서 아래와 같이 처리
                	   result=data.reviewContnet;
                	   reviewUpdateLocation.parent().parent().find(".content").text(result); 
                   }//success function
        	   });	///ajax
        	   
              	   $(this).parent().parent().find(".review").remove();
               	   //$(this).parent().parent().find(".content").text(result); 
            });//click
          
      });//function
      
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>숙소 후기 입니다.</h1>
	<table border="1" width="600px">

		<c:forEach var="list" items="${reviewList}">

			<tr>
				<th align="left" width="50%">아이디 : ${list.id }</th>
				<th align="left" width="50%">평점 : ${list.starpoint}</th>
			</tr>
			<tr>
				<td class="content" id="aa" width="500px">${list.re_content}</td>
				<td><input type="hidden" class="reviewValAccom_code"
					value="${list.accom_code}"> <input type="hidden"
					class="reviewValRe_num" value="${list.re_num }"> <!-- content는 자바스크립트에서 처리  -->
					<input type="hidden" class="reviewValId" value="${list.id }">
					<input type="hidden" class="reviewValStarpoint"
					value="${list.starpoint}">
					<button class="reviewFrom1">댓글수정</button>
					<button class="reviewUpdate">수정완료</button>
					<button class="reviewDelete">댓글삭제</button></td>
			</tr>

		</c:forEach>
		<form action="${ctxpath}/review/reviewInsert.do" method="post">
			<tr>
				<th align="left" width="50%">아이디 : <input type="text" name="id"></th>
				<th align="left" width="50%">평점 : <select name="starpoint">
						<option value="5">5점</option>
						<option value="4.5">4.5점</option>
						<option value="4">4점</option>
						<option value="3.5">3.5점</option>
						<option value="3">3점</option>
						<option value="2.5">2.5점</option>
						<option value="2">2점</option>
						<option value="1.5">1.5점</option>
						<option value="1">1점</option>
						<option value="0.5">0.5점</option>
						<option value="0">0점</option>
				</select>
				</th>
			</tr>
			<tr>
				<td class="content" id="aa" width="500px"><textarea rows="3"
						cols="50" name=re_content></textarea></td>
				<td><input type="hidden" name="accom_code"
					value="${accomdto1.accom_code}"> <input type="submit" value="댓글등록">
				</td>
			</tr>

		</form>
	</table>


</body>
</html>