package admin.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import accom.accomDAO;
import accom.action.CommandAction;

public class AdminAccomUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		accomDAO accomDao = accomDAO.getDAO();

		accomDao.adminAccomUpdate(request);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		
		return "/admin/adminAccomUpdatePro.jsp";
	}
}
