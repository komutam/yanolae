package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;
import accom.action.CommandAction;

public class AdminMemberUpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pageNum = request.getParameter("pageNum");
		MemberDAO dao = MemberDAO.getDAO();// ΩÃ±€≈Ê
		MemberDTO dto = dao.findMemberK(id);
		request.setAttribute("memInfo", dto);
		request.setAttribute("pageNum", pageNum);
		
		return "/admin/adminMemberUpdateForm.jsp";
	}

}
