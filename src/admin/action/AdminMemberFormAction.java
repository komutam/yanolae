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
		int pageSize =10;// 한 페이지의 글 갯수
		int currentPage = Integer.parseInt(pageNum);// 현제 페이지
		int startRow=(currentPage-1)*pageSize+1;//한페이지의 시작 글번호
		int endRow=currentPage*pageSize;//한페이지의 마지막 글번호
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
		
		number = count-(currentPage-1)*pageSize;//글번호
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
