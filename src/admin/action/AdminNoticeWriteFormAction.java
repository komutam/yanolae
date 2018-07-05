package admin.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.SetCharacterEncodingFilter;

import accom.action.CommandAction;

public class AdminNoticeWriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 Date now = new Date();
	 String date = sdf.format(now);
	 
	 request.setAttribute("date", date);
	 request.setAttribute("pageNum", pageNum);
		
		
		
		
		return "/admin/adminNoticeWriteForm.jsp";
	}

}
