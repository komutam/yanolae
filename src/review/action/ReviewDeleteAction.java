package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.ReviewDAO;
import accom.action.CommandAction;

public class ReviewDeleteAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		int re_num = Integer.valueOf(request.getParameter("re_num"));
		String accom_code = request.getParameter("accom_code");
		String accomtype = request.getParameter("accomtype");
		String accomlocation = request.getParameter("accomlocation");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		
		
		ReviewDAO dao = ReviewDAO.getDAO();
		
		dao.reviewDelet(re_num);
		
		request.setAttribute("accom_code", accom_code);
		request.setAttribute("accomtype", accomtype);
		request.setAttribute("accomlocation", accomlocation);
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		
		
		return "/review/reviewDelete.jsp";
	}

}
