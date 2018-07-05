/* accomRoomForm.jsp */
	
	function roomResers(accom_code, room_type) { //방 예약 버튼을 클릭할때 동작하는 함수
		document.roomForm.accom_code.value=accom_code;
		document.roomForm.room_type.value=room_type;
		document.roomForm.submit();
	}


/* header.jsp */
	
	//검색창 예외처리
	function search_clicks(){ //검색 버튼을 클릭했을때 동작하는 함수
		if(document.searchForm.startdate.value == ""){ //시작일 달력의 text값이 공백이면
			alert('예약 시작일을 입력해주세요');
			return false;
		}
		if(document.searchForm.enddate.value == ""){ //종료일 달력의 text값이 공백이면
			alert('예약 종료일을 입력해주세요');
			return false;
		}
		
		//종료일 달력의 text값과 시작일 달력의 text값이 있고, 종료일 달력의 text값이 시작일 달력의 text값보다 크면
		if(!((document.searchForm.startdate.value == "") && (document.searchForm.enddate.value == "")) && (document.searchForm.startdate.value > document.searchForm.enddate.value)){
			alert('예약 시작일이 예약 종료일보다 큽니다');
			return false;
		}
		
		document.searchForm.submit();
		
	}
	
	

	
/* accomReserForm.jsp */	
	

	function reserConfirm(session, path) { //결제하기 버튼을 눌렀을때 동작하는 함수(memId 값을 받아옴)
		if(session==''){
			alert('로그인 후 이용하실 수 있습니다.');
			location.href=path + "/member/loginForm.do";
		} else {
			var check = confirm("예약하시겠습니까?");
			if (check == true) {
				document.roomReserForm.submit();
			}
		}
		
	}
	
	

