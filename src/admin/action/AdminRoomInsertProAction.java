package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.roomDAO;
import accom.action.CommandAction;

public class AdminRoomInsertProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String accom_code = request.getParameter("code");
		
		roomDAO roomDao = roomDAO.getDAO();
		
		roomDao.adminRoomInsert(request, accom_code);
		
		request.setAttribute("accom_code", accom_code);
		
		return "/admin/adminRoomInsertPro.jsp";
	}
	
}
