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
		int pageSize =10;// �� �������� �� ����
		int currentPage = Integer.parseInt(pageNum);// ���� ������
		int startRow=(currentPage-1)*pageSize+1;//���������� ���� �۹�ȣ
		int endRow=currentPage*pageSize;//���������� ������ �۹�ȣ
		int count=0;
		int number=0;
		
		MemberDAO dao = MemberDAO.getDAO();		
		
		reservationDAO reserDao = reservationDAO.getDAO();		
		List reserlist = null;	
		count = reserDao.getcountAdminReser();
		reserlist = reserDao.getAdminListReservation(startRow-1, pageSize);
		
		request.setAttribute("reserlist", reserlist);			
		number = count-(currentPage-1)*pageSize;//�۹�ȣ
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		
		
		return "/admin/adminReserList.jsp";
	}

	
}
