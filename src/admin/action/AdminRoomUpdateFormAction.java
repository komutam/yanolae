package admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.roomDAO;
import accom.action.CommandAction;

public class AdminRoomUpdateFormAction implements CommandAction{

	
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		String accom_code = request.getParameter("accom_code");
		String room_type = request.getParameter("room_type");
		
		List adminRoomList = null;
		roomDAO roomDao = roomDAO.getDAO();
		
		adminRoomList = roomDao.adminRoomUpdateForm(accom_code, room_type);
		
		request.setAttribute("adminRoomList", adminRoomList);
		request.setAttribute("accom_code", accom_code);
		request.setAttribute("room_type", room_type);
		
		return "/admin/adminRoomUpdateForm.jsp";
	}
}
