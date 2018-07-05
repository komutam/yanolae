package admin.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.reservationDAO;
import member.MemberDAO;
import accom.action.CommandAction;

public class AdminReserListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		
		request.setCharacterEncoding("utf-8");
				
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
		
		MemberDAO dao = MemberDAO.getDAO();		
		
		reservationDAO reserDao = reservationDAO.getDAO();		
		List reserlist = null;	
		count = reserDao.getcountAdminReser();
		reserlist = reserDao.getAdminListReservation(startRow-1, pageSize);
		
		request.setAttribute("reserlist", reserlist);			
		number = count-(currentPage-1)*pageSize;//글번호
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		
		
		return "/admin/adminReserList.jsp";
	}

	
}
