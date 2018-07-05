package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.roomDAO;
import accom.action.CommandAction;

public class AdminRoomDeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String accom_code = request.getParameter("accom_code");
		String room_type = request.getParameter("room_type");
		
		roomDAO roomDao = roomDAO.getDAO();
		
		roomDao.adminRoomDelete(request, accom_code, room_type);
		
		request.setAttribute("accom_code", accom_code);
		
		return "/admin/adminRoomDeletePro.jsp";
	}
	
}
