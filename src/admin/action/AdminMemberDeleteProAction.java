package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import notice.NoticeDAO;
import accom.action.CommandAction;

public class AdminMemberDeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String id  = request.getParameter("id");				
		String pageNum = request.getParameter("pageNum");		
		
		MemberDAO dao = MemberDAO.getDAO();		
		dao.deleteMemeberK(id);				
		request.setAttribute("pageNum", pageNum);
		
		
		return "/admin/adminMemberDeletePro.jsp";
	}

}
