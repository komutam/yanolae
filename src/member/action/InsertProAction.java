package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;

import accom.action.CommandAction;
import member.*;

public class InsertProAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		
		request.setCharacterEncoding("UTF-8");

		MemberDTO dto = new MemberDTO();
		String id = request.getParameter("id");
		
		dto.setId(request.getParameter("id"));
		dto.setPasswd(request.getParameter("passwd"));
		dto.setName(request.getParameter("name"));
		dto.setPhone(request.getParameter("phone"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddr(request.getParameter("addr"));
		dto.setBirth(request.getParameter("birth"));
		dto.setEmail(request.getParameter("email"));
		dto.setRegdate(new Timestamp(System.currentTimeMillis()));
		dto.setMember_level(9);
		
		MemberDAO dao=MemberDAO.getDAO();
		dao.insertMember(dto);
		
		request.setAttribute("id", id);
		return "/member/insertPro.jsp";
	}	
}

