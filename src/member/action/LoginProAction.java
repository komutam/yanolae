package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;

import accom.action.CommandAction;
import member.*;

public class LoginProAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		member.MemberDTO dto = new MemberDTO();
		member.MemberDAO dao = member.MemberDAO.getDAO();
		int logCheck=dao.memberLogin(id, passwd);
		dto = dao.findMemberK(id);
		
		request.setAttribute("dto", dto);
		request.setAttribute("id", id);
		request.setAttribute("logCheck", logCheck);
		return "/member/loginPro.jsp";
	}
}
