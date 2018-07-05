package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import room.roomDAO;
import accom.action.CommandAction;

public class AdminRoomUpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		roomDAO roomDao = roomDAO.getDAO();
		
		String accom_code = "";
		accom_code = roomDao.adminRoomUpdatePro(request);
		request.setAttribute("accom_code", accom_code);
		
		return "/admin/adminRoomUpdatePro.jsp";
	}
}
