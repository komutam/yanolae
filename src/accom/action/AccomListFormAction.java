package accom.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accom.*;
import java.util.*;


public class AccomListFormAction implements CommandAction{
	
	
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		String accomtype = request.getParameter("accomtype");
		String accomlocation = request.getParameter("accomlocation");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		
		
		if(pageNum==null){ //페이지가 없으면 항상 1
			pageNum="1";
		}
		
		
		int pageSize=10; //한 페이지 당 업소 갯수
		int currentPage = Integer.parseInt(pageNum); //현재 페이지
		int startNum = (currentPage-1) * pageSize + 1; //한 페이지 당 시작 숙박 업소자료
		int endNum = (currentPage) * pageSize; //한 페이지 당 마지막 숙박 업소자료
		int count = 0; //총 숙박 업소 갯수 넣을 변수
		
		List accomlist = null;
		
		accomDAO accomDao = accomDAO.getDAO();
		
		count = accomDao.getAccomCount(accomtype, accomlocation);
		
		if(count > 0 ){
			accomlist = accomDao.getAccomList(accomtype, accomlocation, startNum, pageSize);
		} else {
			accomlist = Collections.EMPTY_LIST;
		}

		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startNum", new Integer(startNum));
		request.setAttribute("endNum", new Integer(endNum));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("accomlist", accomlist);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("accomlocation", accomlocation);
		request.setAttribute("accomtype", accomtype);
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		
		
		return "/accom/accomListForm.jsp";
	}
}
