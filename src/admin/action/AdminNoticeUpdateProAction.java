package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import accom.action.CommandAction;

public class AdminNoticeUpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int noti_num=Integer.parseInt(request.getParameter("noti_num"));		
		String pageNum=request.getParameter("pageNum");
		
		NoticeDTO dto = new NoticeDTO();
		dto.setNoti_content(request.getParameter("noti_content"));
		dto.setNoti_subject(request.getParameter("noti_subject"));
		dto.setNoti_num(noti_num);
		
		NoticeDAO dao = NoticeDAO.getmemberDao();
		
		int check = dao.updateNotice(dto);
		
		request.setAttribute("check", check);
		request.setAttribute("pageNum", pageNum);
		
		return "/admin/adminNoticeUpdatePro.jsp";
	}

}
