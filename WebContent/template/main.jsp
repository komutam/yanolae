<%@page import="javax.naming.NoInitialContextException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="../config/path.jspf"%>
 <%@ page import="notice.*" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
 

 
 <%
 NoticeDAO noticeDao = NoticeDAO.getmemberDao();
 NoticeDTO dto = new NoticeDTO();
List list1 = noticeDao.getTotalNotice();

 %>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

<title>main.jsp</title>

<style>
*{
	box-sizing:border-box;
}
a{text-decoration: none;}
a:hover{text-decoration: none;}
img{border:none;}

#video {
	 top:70px;
     position: absolute;
     width: 100%;
     height: auto;
     z-index: -1;
     overflow: hidden;
}

.mainTitle{
	width:100%;

}

.mainTitle > img{
	width:800px;
	margin:0 auto;
	z-index:2;
	padding:120px 0;
}
.mainMiddle{
	width:100%;
	margin:0;
	padding:0;
	position:relative;
	height:300px;
	margin-top:30px;
}
.mainBottom{
	width:100%;
	height:600px;
	background:#fafafa;
}

.middleIcon{
	margin:0;
	padding:0;
	position:absolute;
	top:50%;
	left:50%;
	transform:translate(-50%, -50%);
	display:flex;
}

.middleIcon li{
	position:relative;
	list-style:none;
	width:120px;
	height:120px;
	background:#fff;
	margin:0 30px;
	border:4px solid #f857a6; 
	border-radius:50%;
	transition: .5s;
	overflow:hidden;
}
.middleIcon li:hover{
  background: #f857a6; /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #f857a6, #ff5858); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to right, #f857a6, #ff5858); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

}

.middleIcon li .fa{
	position:absolute;
	top:50%;
	left:50%;
	transform:translate(-50%, -50%);
	font-size:48px;
	color:#f857a6;
	transition: .5s;
}
.middleIcon li .fa:nth-child(1){
	left:-50%;
	opacity:0;
}
.middleIcon li:hover .fa:nth-child(1){
	left:50%;
	opacity:1;
	color:white;
}

.middleIcon li:hover .fa:nth-child(2){
	left:150%;
	opacity:1;
}
.middleIcon p{
	padding-top:10px;
	color:black;
	font-size:20px;
	font-weight: bold;
}
.middleAdd .fa{
	position:absolute;
	top:12%;
	left:50%;
	transform:translate(-50%, -50%);
	display:flex;
	font-size:17px;
}
.couponImg{
	position:absolute;
	left:45%;
	top:65px;
	z-index:7;
}

.mainSlide{
	width:100%;
	heigt:600px;
}

.mainSlide .carousel-inner{
	width:700px;
	height:600px;
	margin:0 auto;
}
.mainSlide .item{
	padding-top:70px;
}
.mainSlide .carousel-indicators li{
	 background-color:#ffd2d8;
}
.mainNotice{
	border-top:1px solid #b7b7b7;
	width:100%;
	height:70px;
}
.notice_content{
	width:1300px;
	margin:0 auto;
	height:70px;
	overflow:hidden;
	position:relative;
}
.notice{
	height:70px; overflow:hidden;
}
.rolling{
	position:relative;
	height:auto;
	position:relative; height:auto;
}
.rolling li{
	height:70px;
	line-height:70px;
	display:block;
	overflow:hidden;
	white-space:nowrap;
	text-overflow:ellipsis;
}
.notice_content p{
	font-size:18px;
	display:inline-block;
	height:70px;
	float:left;
	line-height:70px;
	font: Arial, Helvetica, sans-serif; font-weight:bold; text-shadow:1px 1px #cbcbcb;
}
.notice_content .fafa{
	position:absolute;
	top:20px;
	right:0;
	font-size:30px;
	color:gray;
}
.notice_content .fafa:hover .fa{
	color:black;
}
</style>

<script>
$(function () {
    var height = $('.notice').height();
    var num = $('.rolling li').length;
    var max = num * height;
    var move = 0;
    function noticeRolling() {
        move += height;
        $('.rolling').animate({ 'top': -move }, 800, function () {
            if (move >= max)
            {
                $(this).css('top', 0);
                move = 0;
            }
        });
    };
    noticeRollingOff = setInterval(noticeRolling, 3000);
    $('.rolling').append($('.rolling li').first().clone());
});
</script>

</head>

<body>

  <div class="mainTitle">
  	<img src="${ctxpath}/img/ttt.png" class="img-responsive" alt="Responsive image">
	<div id="video">
      <img src="${ctxpath}/img/yanolmain01ss.jpg">
	</div>
	
  </div>
  <div class="mainMiddle">
  	<div class="middleAdd">
  		<i class="fa fa-location-arrow" aria-hidden="true"><p>서울특별시 종로구 드래곤빌딩 11-11</p></i>
  	</div>
  	<img src="${ctxpath}/img/coupon.png" class="couponImg">
  	<ul class="middleIcon">
  		<a href="#">
	  		<li>
	  			<i class="fa fa-ticket" aria-hidden="true"></i>
	  			<i class="fa fa-ticket" aria-hidden="true"></i>
	  		</li>
	  		<p align=center>내 주변 쿠폰</p>
  		</a>
  		<a href="#">
	  		<li>
		  		<i class="fa fa-bed" aria-hidden="true"></i>
		  		<i class="fa fa-bed" aria-hidden="true"></i>	
	  		</li>
	  		<p align=center>모텔</p>
  		</a>
  		<a href="#">
	  		<li>
		  		<i class="fa fa-home" aria-hidden="true"></i>
		  		<i class="fa fa-home" aria-hidden="true"></i>
	  		</li>
	  		<p align=center>MY ROOM</p>
  		</a>
  		<a href="#">
	  		<li>
		  		<i class="fa fa-hospital-o" aria-hidden="true"></i>
		  		<i class="fa fa-hospital-o" aria-hidden="true"></i>
	  		</li>
	  		<p align=center>호텔/리조트</p>
  		</a>
  		<a href="#">
	  		<li>
		  		<i class="fa fa-university" aria-hidden="true"></i>
		  		<i class="fa fa-university" aria-hidden="true"></i>
	  		</li>
	  		<p align=center>펜션/풀빌라</p>
  		</a>
  		<a href="#">
	  		<li>
		  		<i class="fa fa-suitcase" aria-hidden="true"></i>
		  		<i class="fa fa-suitcase" aria-hidden="true"></i>
	  		</li>
	  		<p align=center>게스트하우스</p>
  		</a>
  	</ul>
  </div>
  
  <div class="mainBottom">
  	<div class="mainSlide">
	 <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
	  <!-- Indicators -->
	  <ol class="carousel-indicators">
	    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
	  </ol>
	
	  <!-- Wrapper for slides -->
	  <div class="carousel-inner" role="listbox">
	    <div class="item active">
	      <img src="${ctxpath}/img/mainbottom01s.png" alt="...">
	      <div class="carousel-caption">
	        ...
	      </div>
	    </div>
	    <div class="item">
	      <img src="${ctxpath}/img/mainbottom02s.png" alt="...">
	      <div class="carousel-caption">
	        ...
	      </div>
	    </div>
	  </div>
	
	  <!-- Controls -->
	  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
	    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
	    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	</div>
	</div>
  </div>
  
  <div class="mainNotice">
   <div class="notice_content">
   <p>Notice : &nbsp</p>
     <div class="notice">
            <ul class="rolling">
           		<c:set var="list" value="<%=list1%>"/>
            	<c:forEach var="listx" items="${list}" >
            		<li><a href="${ctxpath}/notice/noticeContentForm.do?num=${listx.noti_num}&pageNum=1"> ${listx.noti_subject}</a></li>
            	</c:forEach>
            	<%-- 
               <li><a href="#"> 랜섬웨어 주의보! 인터넷 이용시 가급적이면</a></li>
               <li><a href="#"> 연휴 일정에 대해서 공지합니다.</a></li>
               <li><a href="#"> 운영자들 리스트를 공개합니다.</a></li>
               <li><a href="#"> 해외 취재 인원 모집중! 자세한 사항은 내용을 봐주세요.</a></li>
               <li><a href="#"> 회원 가입시 꼭 알아야할 사항들과 커뮤니티 공강 매너에 대해</a></li>
               <li><a href="#"> 서버점검 일시를 알려드립니다.</a></li>
               --%>
           </ul>
     </div>
     <a href="${ctxpath}/notice/noticeList.do" class="fafa">
   		<i class="fa fa-bars" aria-hidden="true"></i>
	 </a>
   </div>
  </div>
 
  
</body>
</html>