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
		
		
		if(pageNum==null){ //�������� ������ �׻� 1
			pageNum="1";
		}
		
		
		int pageSize=10; //�� ������ �� ���� ����
		int currentPage = Integer.parseInt(pageNum); //���� ������
		int startNum = (currentPage-1) * pageSize + 1; //�� ������ �� ���� ���� �����ڷ�
		int endNum = (currentPage) * pageSize; //�� ������ �� ������ ���� �����ڷ�
		int count = 0; //�� ���� ���� ���� ���� ����
		
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
