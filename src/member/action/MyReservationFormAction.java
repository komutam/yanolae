package member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.*;
import accom.action.CommandAction;

public class MyReservationFormAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		
		reservationDAO reserDao = reservationDAO.getDAO();
		
		List reserlist = null;
		reserlist = reserDao.getListReservation(memId);
		
		
		request.setAttribute("reserlist", reserlist);
		
		return "/member/myReservationForm.jsp";
	}
	
}
