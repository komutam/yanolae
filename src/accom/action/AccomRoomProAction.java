package accom.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.*;


public class AccomRoomProAction implements CommandAction{

	
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

		
		
		request.setAttribute("accom_code", accom_code);
		request.setAttribute("room_type", room_type);
		request.setAttribute("accomtype", accomtype);
		request.setAttribute("accomlocation", accomlocation);
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		
		
		return "/accom/accomRoomPro.jsp";
	}
}
