package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accom.accomDAO;
import accom.action.CommandAction;

public class AdminAccomDeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		String accom_code = request.getParameter("accom_code");

		accomDAO accomDao = accomDAO.getDAO();
		accomDao.adminAccomDelete(request, accom_code);
		
		return "/admin/adminAccomDeletePro.jsp";
	}
	
}
