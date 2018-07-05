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
		int pageSize =10;// �� �������� �� ����
		int currentPage = Integer.parseInt(pageNum);// ���� ������
		int startRow=(currentPage-1)*pageSize+1;//���������� ���� �۹�ȣ
		int endRow=currentPage*pageSize;//���������� ������ �۹�ȣ
		int count=0;
		int number=0;
		
		List noticeList = null;
		
		NoticeDAO dao = NoticeDAO.getmemberDao();
		
		count = dao.getnoticeCount();
		
		if(count>0){// ���� �����ϸ�
			noticeList=dao.getNoticeListN(startRow, pageSize);
		}else{// ���� ������ 
			noticeList=Collections.EMPTY_LIST;// ���°� �־��
		}//else
		
		number=count-(currentPage-1)*pageSize;// �۸�Ͽ� ǥ���� �۹�ȣ
		
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
