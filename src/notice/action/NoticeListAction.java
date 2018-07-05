package notice.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import accom.action.CommandAction;

public class NoticeListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}		
		int pageSize =10;// 한 페이지의 글 갯수
		int currentPage = Integer.parseInt(pageNum);// 현제 페이지
		int startRow=(currentPage-1)*pageSize+1;//한페이지의 시작 글번호
		int endRow=currentPage*pageSize;//한페이지의 마지막 글번호
		int count=0;
		int number=0;
		
		List noticeList = null;
		
		NoticeDAO dao = NoticeDAO.getmemberDao();
		
		count = dao.getnoticeCount();
		
		if(count>0){// 글이 존재하면
			noticeList=dao.getNoticeListN(startRow, pageSize);
		}else{// 글이 없으면 
			noticeList=Collections.EMPTY_LIST;// 없는걸 넣어라
		}//else
		
		number=count-(currentPage-1)*pageSize;// 글목록에 표시할 글번호
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("noticeList", noticeList);
		
		
		return  "/notice/noticeList.jsp";
	}

}
