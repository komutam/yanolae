package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;
import accom.action.CommandAction;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
	request.setCharacterEncoding("utf-8");
	
	String id = request.getParameter("id");
	
	MemberDAO dao = MemberDAO.getDAO();// ΩÃ±€≈Ê
	MemberDTO dto = dao.findMemberK(id);
	request.setAttribute("memInfo", dto);
 	
		return "/member/updateForm.jsp";
	}

}
