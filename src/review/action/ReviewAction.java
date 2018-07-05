package review.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.ReviewDAO;
import review.ReviewDTO;
import accom.action.CommandAction;

public class ReviewAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		// ���� �ڵ带 Ŭ���ϸ� ���ƿ��� �Ѵ�. 
		String accom_code = request.getParameter("accom_code");
		
		// ���� ������ ���� ���� ������ �����.
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();

		ReviewDAO dao = ReviewDAO.getDAO();
		
		list=dao.getReview(accom_code);
		
		request.setAttribute("reviewList", list);
		request.setAttribute("accom_code", accom_code);
		return "/review/review.jsp";
	}

	
	
}
