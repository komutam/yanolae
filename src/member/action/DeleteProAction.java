package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import accom.action.CommandAction;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String id  = request.getParameter("id");
		
		System.out.println(id);
		
		MemberDAO dao = MemberDAO.getDAO();
		dao.deleteMemeberK(id);
		
		return "/member/deletePro.jsp";
	}

}
