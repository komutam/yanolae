package notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import accom.action.CommandAction;

public class NoticeContentFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		NoticeDAO dao=NoticeDAO.getmemberDao(); //dao객체 얻기
		NoticeDTO dto= dao.getNotice(num);// num에 해당하는 레코드
				
		//해당뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("dto", dto);		

		return "/notice/noticeContentForm.jsp";
	}

	
	
	
}
