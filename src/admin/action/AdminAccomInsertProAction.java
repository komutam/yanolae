package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accom.accomDAO;
import accom.action.CommandAction;

public class AdminAccomInsertProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		accomDAO accomdao = accomDAO.getDAO();
		int count = 0;
		count = accomdao.adminAccomInsert(request);
		
		request.setAttribute("count", count);
		
		return "/admin/adminAccomInsertPro.jsp";
	}
	
	
}
