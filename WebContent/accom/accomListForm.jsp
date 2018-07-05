<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/config/path.jspf" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>숙박 업소 리스트 페이지</title>
<style type="text/css">
	body {min-width:1200px}
	.accomlistpage {text-align:center;}
</style>
</head>
<body>
		<c:if test="${count == 0}">
			<div class="accom_total">
				<div class="accom_tle">
					<p>검색된 자료가 없습니다.</p>			
				</div>
			</div>
		</c:if>
		<c:if test="${count > 0 }">
			<form method="post" name="accomListForm" action="${ctxpath }/accomListPro.do">
				<div class="accom_total">
					<c:forEach var="accomdto" items="${accomlist}">
						<div class="accom_tle" class="table table-bordered table-hover">
							<div class="accom_image_tle">
								<a href="${ctxpath }/accom/accomRoomForm.do?accom_code=${accomdto.accom_code}&accomtype=${accomtype }&accomlocation=${accomlocation}&&startdate=${startdate}&enddate=${enddate}"><img class="accom_image" src="${ctxpath }/img/${accomdto.accom_image}" alt="no" target="_blank"></a>
							</div>
							<div class="accom_info">
								<h3><a href="${ctxpath }/accom/accomRoomForm.do?accom_code=${accomdto.accom_code}&accomtype=${accomtype }&accomlocation=${accomlocation}&&startdate=${startdate}&enddate=${enddate}" target="_blank">${accomdto.accom_name }</a></h3>
								<p>주소 ${accomdto.accom_addr }</p>
								<p>숙박 예약 ${accomdto.accom_resertime }</p>
							</div>
						</div>	
					</c:forEach>
					
					<c:if test="${count > 0 }">
						<%-- 초기화 --%>
						<c:set var="pageCount" value="${count/pageSize + (count%pageSize==0 ? 0 : 1)}"/>
						<c:set var="pageBlock" value="${10 }"/>
						<fmt:parseNumber var="result" value="${currentPage/10}" integerOnly="true"/>
						<c:set var="startPage" value="${result * 10 + 1 }"/>
						<c:set var="endPage" value="${startPage + pageBlock - 1 }"/>	
								
						<c:set var="accomtype" value="${accomtype}"/>
						
						<c:if test="${endPage > pageCount}">
							<c:set var="endPage" value="${pageCount}" />
						</c:if>
						
						
						<div class="accomlistpage">	
							<%-- 이전 블럭 --%>
							<c:if test="${startPage > 10 }">
								<a href="${ctxpath }/accom/accomListForm.do?accomtype=${accomtype }&accomlocation=${accomlocation}&startdate=${startdate}&enddate=${enddate}&pageNum=${startPage-10}">[이전 블럭]</a>
							</c:if>
							
							<c:forEach var="i" begin="${startPage }" end="${endPage }">
								<a href="${ctxpath }/accom/accomListForm.do?accomtype=${accomtype }&accomlocation=${accomlocation}&startdate=${startdate}&enddate=${enddate}&pageNum=${i}">[${i}]&nbsp;</a>
							</c:forEach>
							
							<%-- 다음 블럭 --%>
							<c:if test="${endPage < pageCount}">
								<a href="${ctxpath }/accom/accomListForm.do?accomtype=${accomtype }&accomlocation=${accomlocation}&startdate=${startdate}&enddate=${enddate}&pageNum=${startPage+10}">[다음 블럭]</a>
							</c:if>
						</div>	
					</c:if>
				</div>
			</form>
		</c:if>
		
	<div class="accom_map" id="map" style="min-width:600px; width:50%;height:750px;float: right; position: fixed; top:20%; right: 0; z-index:10;"></div>
   
		

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=eb4ab951cf103347da0c14d06244759f&libraries=services"></script>
<script>

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 8 // 지도의 확대 레벨
};  

//지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new daum.maps.services.Geocoder();


<c:forEach var="accomdto" items="${accomlist}">    	
	
//주소로 좌표를 검색합니다
geocoder.addressSearch('${accomdto.accom_addr }', function(result, status) {	
// 정상적으로 검색이 완료됐으면 
 if (status === daum.maps.services.Status.OK) {
    var coords = new daum.maps.LatLng(result[0].y, result[0].x); 
    
    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new daum.maps.Marker({
        map: map,
        position: coords,
        title : '${accomdto.accom_name }'
    });
    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new daum.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;"><a href="${ctxpath }/accom/accomRoomForm.do?accom_code=${accomdto.accom_code}&accomtype=${accomtype }&accomlocation=${accomlocation}&&startdate=${startdate}&enddate=${enddate}" target="_blank">'+'${accomdto.accom_name }'+'</a></div>'
    });
    infowindow.open(map, marker);    
   
    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);
    
} 
});   
</c:forEach>
    

</script>		
</body>
</html>