package admin.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accom.accomDAO;
import accom.action.CommandAction;

public class AdminAccomListFormAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		
		accomDAO accomdao = accomDAO.getDAO();
		
		if(pageNum==null){ //�������� ������ �׻� 1
			pageNum="1";
		}
		
		
		int pageSize=10; //�� ������ �� ���� ����
		int currentPage = Integer.parseInt(pageNum); //���� ������
		int startNum = (currentPage-1) * pageSize + 1; //�� ������ �� ���� ���� �����ڷ�
		int endNum = (currentPage) * pageSize; //�� ������ �� ������ ���� �����ڷ�
		int count = 0; //�� ���� ���� ���� ���� ����
		
		List adminAccomlist = null;
		
		accomDAO accomDao = accomDAO.getDAO();
		
		count = accomDao.getAdminAccomCount();
		
		if(count > 0 ){
			adminAccomlist = accomDao.getAdminAccomList(startNum, pageSize);
		} else {
			adminAccomlist = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("adminAccomlist", adminAccomlist);
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startNum", new Integer(startNum));
		request.setAttribute("endNum", new Integer(endNum));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageNum", new Integer(pageNum));
		
		
		return "/admin/adminAccomListForm.jsp";
	}
	
}
