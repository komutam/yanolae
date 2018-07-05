package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import accom.action.CommandAction;

public class AdminNoticeWriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		
		NoticeDTO dto = new NoticeDTO();
		
		dto.setNoti_content(request.getParameter("noti_content"));
		dto.setNoti_subject(request.getParameter("noti_subject"));
		
		NoticeDAO dao = NoticeDAO.getmemberDao();
		
		dao.insertnotice(dto);
		request.setAttribute("pageNum", pageNum);
		
		return "/admin/adminNoticeWritePro.jsp";
	}

}
