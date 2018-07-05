<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="../config/path.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

a{text-decoration: none;}
a:hover{text-decoration: none;}
img{border:none;}
li{list-style:none;}

#footer{
	background:#292929;
	width:100%;
	height:300px;
	color:#aaaaaa;
}
.footerContent{
	width:1200px;
	height:100%;
	overflow:hidden;
}
.footerMain01{
	height:80px;
	border-bottom:1px solid #aaaaaa;
}
.footerTop{
	line-height:80px;
}
.footerTitle{
	font-size:30px;
	float:left;
}

.footerInfo{
	float:left;
	width:780px;
	margin-left:80px;
}
.footerul{
	float:right;
	margin-top:28px;
}
.footerul a{
	color:#aaaaaa;
	display:block;
	padding:0 15px;
	border:1px solid #aaaaaa;
	border-top:none;
	border-bottom:none;
}
.footerul a:hover{
	color:white;
}
.footerul li{
	float:left;
}
.footerul > li:first-child > a{
	border-left:none;
}
.footerul > li:last-child > a{
	border-right:none;
}
.footerSelect{
	float:right;
	background:#292929;
	padding:5px 10px;
	width:200px;
	height:40px;
	margin-top:20px;
}

.footerul02 li{
	float:left;
}
.footerul02 p{
	font-size:13px;
	color:#aaaaaa;
	display:block;
	padding:0 15px;
	border:1px solid #aaaaaa;
	border-top:none;
	border-bottom:none;
}
.footerul02 > li:first-child > p{
	border-left:none;
	padding-left:0;
}
.footerul02 > li:last-child > p{
	border-right:none;
	padding-right:70px;
}

.footerul03 li{
	float:left;
}
.footerul03 p{
	font-size:13px;
	color:#aaaaaa;
	display:block;
	padding:0 15px;
	border:1px solid #aaaaaa;
	border-top:none;
	border-bottom:none;
}
.footerul03 > li:first-child > p{
	border-left:none;
	padding-left:0;
}
.footerul03 > li:last-child > p{
	border-right:none;
	padding-right:0;
}

.footerInfo04 p{
	float:left;
	font-size:12px;
}
.footerMain02{
	padding-top:20px;
}

.footerul02, .footerul03{
	margin:0;
	padding:0;
}

.sideRight{
	width:280px;
	height:100px;
	float:right;
	padding-top:10px;
	
}
.helper p{
	float:left;
	padding-right:30px;
	color:#767676;
}
.helper p:hover{
	color:#aaaaaa;
}
.public li{
	float:left;
}
.public .fa{
	font-size:25px;
	padding-right:15px;
	color:#767676;
}
.public .fa:hover{
	color:#aaaaaa;
}
</style>

</head>
<body>

<div id="footer">
	<div class="footerContent">
	  <div class="footerMain01">
		<div class="footerTop">
			<p class="footerTitle">yanolrae</p>
		</div>
		<div class="footerInfo">
			<ul class="footerul">
				<li><a href="#">회사소개</a></li>
				<li><a href="#">제휴광고문의</a></li>
				<li><a href="#">인재채용</a></li>
				<li><a href="#">이용약관</a></li>
				<li><a href="#">개인정보처리방침</a></li>
			</ul>
		</div>
		<div>
			<select class="footerSelect" name="cars1">
		      <option value="sel01" selected>패밀리사이트</option>
		      <option value="sel02">스마트프롤로</option>
		      <option value="sel03">야놀래펜션</option>
		      <option value="sel04">프렌차이즈</option>
		      <option value="sel04">호텔업퍼</option>
		      <option value="sel04">모텔나우</option>
		      <option value="sel04">야놀래평생교육원</option>
		    </select>
		</div>
	  </div>
	  <div class="sideRight">
	  	<div class="helper">
	  	  <a href="#">
	  		<p>고객센터</p>
	  		<p>1644-1346(오전9시~익일 새벽3시)</p>
	  	  </a>
	  	</div>
	  	<div>
	  		<ul class="public">
	  			<li><a href=""><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
	  			<li><a href=""><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
	  			<li><a href=""><i class="fa fa-youtube-play" aria-hidden="true"></i></a></li>
	  			<li><a href=""><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
	  			<li><a href=""><i class="fa fa-rss" aria-hidden="true"></i></a></li>
	  		</ul>
	  	</div>
	  </div>
	  <div class="footerMain02">
	  	<div class="footerInfo02">
			<ul class="footerul02">
				<li><p>(주)야놀래</p></li>
				<li><p>대표이사:드래곤볼</p></li>
				<li><p>주소:서울특별시 종로구 드래곤빌딩 427</p></li>
				<li><p>메일:helpme@yanolrae.com</p></li>
			</ul>
		</div>
		<div class="footerInfo03">
			<ul class="footerul03">
				<li><p>사업자등록번호: 212-77-77775</p></li>
				<li><p>통신판매업신고: 마린-17178호</p></li>
				<li><p>관광사업자 등록번호: 제2017-01호</p></li>
				<li><p>호스팅 서비스 제공자: (주)야놀래</p></li>
			</ul>
		</div>
	  </div>
	  
	  <div class="footerMain03">
	  	<div class="footerInfo04">
	  		<p>(주)야놀래는 통신판매중개자로서, 통신판매의 당사자가 아니라는 사실을 고지하며 상품의 예약, 이용 및 환불등과 관련한 의무와 책임은 각 판매자에게 있습니다.</p>
	  	</div>
	  </div>
	  
	  <div class="footerBottom">
	  	<img src="${ctxpath}/img/footer.png">
	  </div>
	   
	</div>
</div>

</body>
</html>