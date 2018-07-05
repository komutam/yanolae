package accom.action;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accom.accomDAO;
import room.*;

public class AccomReserFormAction implements CommandAction{

	
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String accom_code = request.getParameter("accom_code");
		String room_type = request.getParameter("room_type");
		String accomtype = request.getParameter("accomtype");
		String accomlocation = request.getParameter("accomlocation");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		
		
		
		String ss = startdate.replaceAll("-", ""); //startdate(예약 시작일)의 - 기호를 뺀 결과값을 String 변수에 저장
		String ee = enddate.replaceAll("-", ""); //enddate(예약 종료일)의 - 기호를 뺀 결과값을 String 변수에 저장
		long diffMillis = 0l; //예약 종료일 - 예약 시작일(ex. 밀리세컨드 단위의 시간차이)
        int diff = 0; //예약 종료일 - 예약 시작일(ex. 하루차이면 1, 이틀차이면 2)

		Date endDay = new SimpleDateFormat("yyyyMMddHHmmss").parse(ee+"000000");
		//yyyyMMDD(년월일)형식인 enddate을 HHmmss(시분초)형식으로 바꿔서 Date 타입인 endDay 변수에 저장
        Calendar endDayCal = new GregorianCalendar(); //그레고리안 캘린더 객체 생성
        endDayCal.setTime(endDay); //endDay 날짜를 캘린더 객체에 저장
        
        Date startDay = new SimpleDateFormat("yyyyMMddHHmmss").parse(ss+"000000");
        //yyyyMMDD(년월일)형식인 startdate을 HHmmss(시분초)형식으로 바꿔서 Date 타입인 startDay 변수에 저장
        Calendar startDayCal = new GregorianCalendar(); //그레고리안 캘린더 객체 생성
        startDayCal.setTime(startDay); //startDay 날짜를 캘린더 객체에 저장
        if(startDayCal.getTimeInMillis() < endDayCal.getTimeInMillis()){ //만약 startDayCal의 시간초가 endDayCal의 시간초보다 적으면
        	diffMillis = endDayCal.getTimeInMillis() - startDayCal.getTimeInMillis();
        	//diffMillis 변수 = endDayCal(예약 종료일) 시간초 - startDayCal(예약 시작일) 시간초 
        	diff = (int) (diffMillis/ (24 * 60 * 60 * 1000));
        	//diff 변수 = diffMillis / 시간초(밀리세컨드)
        
        } else { //만약 startDayCal의 시간초가 endDayCal의 시간초보다 많으면
        	Date endDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(ss+"240000");
        	//yyyyMMDD(년월일)형식인 startdate을 HHmmss(시분초)형식으로 바꿔서 Date 타입인 endDate 변수에 저장
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	enddate = sdf.format(endDate).toString(); //Date 타입의 endDate 변수를 yyyy-MM-dd 형식으로 바꿔서 변수에 저장
        	diff=1; //diff(예약 종료일 - 예약 시작일) 값은 1
        }

		
		List roomDetailList = null;
		
		roomDAO roomdao = roomDAO.getDAO();
		roomDetailList = roomdao.getRoomDetailList(accom_code, room_type, diff);
		
		
		List accomList = null;
		
		accomDAO accomdao = accomDAO.getDAO();
		accomList = accomdao.getAccomDetailList(accom_code);
		
		
		request.setAttribute("accomList", accomList);
		request.setAttribute("roomDetailList", roomDetailList);
		request.setAttribute("accomtype", accomtype);
		request.setAttribute("accomlocation", accomlocation);
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		request.setAttribute("diff", diff);
		
		return "/accom/accomReserForm.jsp";
	}
}
