package accom.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accom.*;
import review.ReviewDAO;
import review.ReviewDTO;
import room.*;

public class AccomRoomFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String accom_code = request.getParameter("accom_code");
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
		
		roomDAO roomdao = roomDAO.getDAO();
		accomDAO accomdao = accomDAO.getDAO();
		List roomlist = null;
		roomlist = roomdao.getRoomList(accom_code, diff, startdate);
		
		List accomlist = null;
		accomlist = accomdao.getAccomDetailList(accom_code);
		
		// 리턴 값을로 받을 저장 공간을 만든다.
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();

		ReviewDAO dao = ReviewDAO.getDAO();
				
		list=dao.getReview(accom_code);
				
		request.setAttribute("reviewList", list);
		request.setAttribute("accom_code", accom_code);
		request.setAttribute("roomlist", roomlist);
		request.setAttribute("accomlist", accomlist);
		request.setAttribute("accomtype", accomtype);
		request.setAttribute("accomlocation", accomlocation);
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		
		
		return "/accom/accomRoomForm.jsp";
		
	}
	
}
