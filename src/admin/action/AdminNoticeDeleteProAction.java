package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import accom.action.CommandAction;

public class AdminNoticeDeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		
		NoticeDAO dao = NoticeDAO.getmemberDao();
		
		int check = dao.noticedelete(num);		
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return "/admin/adminNoticeDeletePro.jsp";
	}

}
