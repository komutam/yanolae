package admin.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import accom.action.CommandAction;

public class AdminMemberFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}		
		int pageSize =10;// �� �������� �� ����
		int currentPage = Integer.parseInt(pageNum);// ���� ������
		int startRow=(currentPage-1)*pageSize+1;//���������� ���� �۹�ȣ
		int endRow=currentPage*pageSize;//���������� ������ �۹�ȣ
		int count=0;
		int number=0;
		
		List memberList = null;
		
		
		MemberDAO dao = MemberDAO.getDAO();		
		
		String search = request.getParameter("search");
		if(search==null){
			search="";
		count = dao.getmemberCount();		
		if(count>0){
			memberList = dao.memberlist(startRow, pageSize);
		}else{
			memberList=Collections.EMPTY_LIST;
		}//else 
		}else{
		count = dao.getmemberCount(search);
		if(count>0){
			memberList = dao.getsearchN(search, startRow, pageSize);
		}else{
			memberList=Collections.EMPTY_LIST;
		}
		}
		
		number = count-(currentPage-1)*pageSize;//�۹�ȣ
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("memberList", memberList);
		request.setAttribute("search", search);
			
		
		return "/admin/adminMemberForm.jsp";
	}

}
