package admin.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import room.roomDAO;
import accom.accomDAO;
import accom.action.CommandAction;

public class AdminAccomUpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String accom_code = request.getParameter("accom_code");
		String pageNum = request.getParameter("pageNum");
		
		List adminAccomlist = null;
		List adminRoomlist = null;
		
		accomDAO accomDao = accomDAO.getDAO();
		adminAccomlist = accomDao.adminAccomUpdateForm(accom_code);
		
		roomDAO roomDao = roomDAO.getDAO();
		adminRoomlist = roomDao.getAdminRoomList(accom_code);
		
		request.setAttribute("adminAccomlist", adminAccomlist);
		request.setAttribute("adminRoomlist", adminRoomlist);
		request.setAttribute("accom_code", accom_code);
		request.setAttribute("pageNum", new Integer(pageNum));
		
		return "/admin/adminAccomUpdateForm.jsp";
	}
	
}
