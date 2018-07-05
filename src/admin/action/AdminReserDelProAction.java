package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reservation.reservationDAO;
import accom.action.CommandAction;

public class AdminReserDelProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		int reser_number = Integer.parseInt(request.getParameter("reser_number"));
		
		reservationDAO reserDao = reservationDAO.getDAO();
		reserDao.deleteReservation(reser_number);
		
		
		
		
		return "/admin/adminReserDelPro.jsp";
	}

}
