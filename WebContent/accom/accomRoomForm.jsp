<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/config/path.jspf"%>


<html>
<head>
<title>방 정보 페이지</title>
<style type="text/css">
	* {margin:0 auto;}
	.roomform {width:700px;}
	.accomform {width:700px; margin-top:20px;}
	.starpoint {width:120px;}
</style>
<script> 
	$(function(){
		
    	  if(${sessionScope.memId == null }){
    		  
    	  $(".reviewFrom1").hide();
    	  $("#reInsert").hide();
    	  $(".reviewDelete").hide();
    	  $("#re_textarea").val('로그인해주세요').attr('readonly', 'readonly');	  
    	  }
    	  
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
			location.href="${ctxpath}/review/reviewDelete.do?re_num="+re_num+"&accom_code=${accom_code}&accomtype=${accomtype }&accomlocation=${accomlocation}&startdate=${startdate}&enddate=${enddate}";
        	  
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
        	   var re_content =$(this).parent().parent().find('.review').val().replace(/(?:\r\n|\r|\n)/g, '<br>');
        	   
        	   var reviewUpdateLocation= $(this);
        	    
        	    
		       var result = "";
         	   $.ajax({
        		   type:'POST', 
                   url:"${ctxpath}/review/reviewUpdate.jsp", //여기로 가서 function을 실행
                  
                   //아직 테스트를 다시해보아야함 데이터가 잘전달되는디 
                   data:"accom_code="+accom_code+"&re_num="+re_num+"&id="+id+"&starpoint="+starpoint+"&re_content="+re_content,
                   dataType:'JSON',
                   success:function(data) { //data 받아와서 아래와 같이 처리
                	   result=data.reviewContnet;
                	   reviewUpdateLocation.parent().parent().find(".content").text(result); 
                   }//success function
        	   });	///ajax
        	   
              	   $(this).parent().parent().find(".review").remove();
        	   	   location.reload();
               	   //$(this).parent().parent().find(".content").text(result); 
            });//click
          
      });//function
      
</script>

</head>
<body>

	<table class="table table-bordered accomform">
		<c:forEach var="accomdto" items="${accomlist }">
			<tr>
				<td colspan="2" align="center"><h1>${accomdto.accom_name }</h1></td>
			</tr>
		</c:forEach>
	</table>
		<c:forEach var="roomdto" items="${roomlist}">
		<table class="table table-bordered roomform">
			<tr>
				<td rowspan="6">
					<img src="${ctxpath }/img/${roomdto.room_image}" class="room_image_size" width="500" height="300">
				</td>
			</tr>

			<tr>
				<td colspan="2">${roomdto.room_name }</td>
			</tr>

			<tr>
				<td colspan="2">기준 : ${roomdto.room_capa }명</td>
			</tr>

			<c:if test="${roomdto.rentable_price != 0 }">
				<tr>
					<td colspan="2">대실 예약 : ${roomdto.rentable_price }</td>
				</tr>
			</c:if>

			<tr>
				<td colspan="2">숙박 예약 : ${roomdto.accom_price }</td>
			</tr>

			<tr>
				<td colspan="2">
					<c:if test="${roomdto.room_count == 0 }">
							매진
					</c:if>
					<c:if test="${roomdto.room_count != 0 }">
						<input type="button" class="btn btn-default" value="예약하기" onclick="roomResers('${roomdto.accom_code }', '${roomdto.room_type}')">
					</c:if>
				</td>
			</tr>
		</table>
		</c:forEach>
	<form name="roomForm" action="${ctxpath }/accom/accomRoomPro.do?accomtype=${accomtype }&accomlocation=${accomlocation}&startdate=${startdate}&enddate=${enddate}" method="post">
		<input type="hidden" name="room_type">
		<input type="hidden" name="accom_code">
	</form>
	
	
	<center><h1>숙소 후기</h1></center>
	<table class="table table-bordered table-hover roomform">

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
					<c:if test="${list.id == sessionScope.memId}">
						<button class="btn btn-default reviewFrom1">댓글수정</button>
						<button class="btn btn-default reviewUpdate">수정완료</button>
						<button class="btn btn-default reviewDelete">댓글삭제</button></td>
					</c:if>
			</tr>

		</c:forEach>
		<form action="${ctxpath}/review/reviewInsert.do" method="post">
			<tr>
				<th align="left" width="50%">아이디 : ${sessionScope.memId }</th>
				<th align="left" width="50%">평점 : <select name="starpoint" class="form-control starpoint">
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
				<td class="re_content" id="aa" width="500px">
					<textarea rows="3" cols="50" name=re_content id="re_textarea" style="resize:none;"></textarea></td>
				<td><input type="hidden" name="accom_code"
					value="${accom_code}"> <input type="submit" class="btn btn-default" id="reInsert" value="댓글등록" >
				</td>
			</tr>
			<input type="hidden" name="accomtype" value="${accomtype }">
			<input type="hidden" name="accomlocation" value="${accomlocation }">
			<input type="hidden" name="startdate" value="${startdate }">
			<input type="hidden" name="enddate" value="${enddate }">
			<input type="hidden" name="id" value="${sessionScope.memId }">
		</form>
	</table>
</body>
</html>