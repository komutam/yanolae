package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.ReviewDAO;
import review.ReviewDTO;
import accom.action.CommandAction;

public class ReviewInsertAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String accom_code = request.getParameter("accom_code");
		String id = request.getParameter("id");
		String re_content = request.getParameter("re_content");
		String starpoint = request.getParameter("starpoint");
		String accomtype = request.getParameter("accomtype");
		String accomlocation = request.getParameter("accomlocation");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		
		ReviewDTO dto= new ReviewDTO();
		
		dto.setAccom_code(accom_code);
		dto.setId(id);
		dto.setRe_content(re_content);
		dto.setStarpoint(Float.valueOf(starpoint));
		
		ReviewDAO dao = ReviewDAO.getDAO();
		dao.reviewInsert(dto);
		
		
		request.setAttribute("accom_code", accom_code);
		request.setAttribute("accomtype", accomtype);
		request.setAttribute("accomlocation", accomlocation);
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		
		return "/review/reviewInsert.jsp";
	}


	
}
