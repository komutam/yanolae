
	//부트스트랩 지원 달력
	$(function(){
		$('.datePicker').datepicker({ //시작일, 종료일 달력 각각 생성
			format: "yyyy-mm-dd",
			language: "kr",
			weekStart: 1,
	        todayBtn:  1,
	   		autoclose: 1,
	  		todayHighlight: 1,
	  		startView: 3,
	  		forceParse: 0,
	        showMeridian: 1,
	        startDate: '+0d',
	        endDate: '+3m'
	        
	        
		});
		
		
		$('.datePicker').change(function(){  //달력 데이터가 바뀔때
			if($("#enddate").val() < $("#startdate").val()){ //종료일 text값이 시작일 text값보다 적으면 
				document.searchForm.enddate.value = document.searchForm.startdate.value; //종료일 text값은 시작일 text값 
				$('#enddate').datepicker('setDate', document.searchForm.enddate.value); //종료일 달력의 날짜를 종료일 text값과 같게함
			}
			
			if($('#startdate').datepicker('update')){ //시작일 달력의 날짜가 바뀔때
				$('#startdate').datepicker('hide'); //시작일 달력을 숨기고
				$('#enddate').datepicker('show'); //종료일 달력을 보여준다
			}
			
		});
	});
	
	
	