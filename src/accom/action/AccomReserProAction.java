package accom.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.reservationDAO;
import reservation.reservationDTO;
import room.*;

public class AccomReserProAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		int num; //reservation 테이블의 예약 번호를 저장하는 변수
		
		String accom_code = request.getParameter("accom_code");
		String room_name = request.getParameter("room_name");
		int room_capa = Integer.parseInt(request.getParameter("room_capa"));
		int room_price = Integer.parseInt(request.getParameter("room_price"));
		String reser_type = request.getParameter("reser_type");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		int diff = Integer.parseInt(request.getParameter("diff"));

		reservationDAO reserDao = reservationDAO.getDAO();
		reservationDTO reserDto = new reservationDTO();
		

		reserDto.setAccom_code(accom_code);
		reserDto.setRoom_name(room_name);
		reserDto.setId(id);
		reserDto.setRegdate(new Date());
		reserDto.setReser_type(reser_type);
		reserDto.setReser_startdate(startdate);
		reserDto.setReser_enddate(enddate);
		reserDto.setReser_price(room_price);
		
		
		num = reserDao.insertReservation(reserDto);
		
		
		request.setAttribute("accom_code", new String(accom_code));
		request.setAttribute("room_name", new String(room_name));
		request.setAttribute("room_capa", new Integer(room_capa));
		request.setAttribute("room_price", new Integer(room_price));
		request.setAttribute("reser_type", new String(reser_type));
		request.setAttribute("startdate", new String(startdate));
		request.setAttribute("enddate", new String(enddate));

		
		reserDto.setAccom_code(accom_code);
		reserDto.setRoom_name(room_name);
		reserDto.setId(id);
		reserDto.setRegdate(new Date());
		reserDto.setReser_type(reser_type);
		reserDto.setReser_startdate(startdate);
		reserDto.setReser_enddate(enddate);
		
		reserDao.insertRoomReservation(reserDto, diff, num);
		
		
		
		
		return "/accom/accomReserPro.jsp";
	}

}
