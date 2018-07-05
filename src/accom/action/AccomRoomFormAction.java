package accom.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accom.*;
import review.ReviewDAO;
import review.ReviewDTO;
import room.*;

public class AccomRoomFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String accom_code = request.getParameter("accom_code");
		String accomtype = request.getParameter("accomtype");
		String accomlocation = request.getParameter("accomlocation");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		
		
        String ss = startdate.replaceAll("-", ""); //startdate(���� ������)�� - ��ȣ�� �� ������� String ������ ����
		String ee = enddate.replaceAll("-", ""); //enddate(���� ������)�� - ��ȣ�� �� ������� String ������ ����
		long diffMillis = 0l; //���� ������ - ���� ������(ex. �и������� ������ �ð�����)
        int diff = 0; //���� ������ - ���� ������(ex. �Ϸ����̸� 1, ��Ʋ���̸� 2)

		Date endDay = new SimpleDateFormat("yyyyMMddHHmmss").parse(ee+"000000");
		//yyyyMMDD(�����)������ enddate�� HHmmss(�ú���)�������� �ٲ㼭 Date Ÿ���� endDay ������ ����
        Calendar endDayCal = new GregorianCalendar(); //�׷����� Ķ���� ��ü ����
        endDayCal.setTime(endDay); //endDay ��¥�� Ķ���� ��ü�� ����
        
        Date startDay = new SimpleDateFormat("yyyyMMddHHmmss").parse(ss+"000000");
        //yyyyMMDD(�����)������ startdate�� HHmmss(�ú���)�������� �ٲ㼭 Date Ÿ���� startDay ������ ����
        Calendar startDayCal = new GregorianCalendar(); //�׷����� Ķ���� ��ü ����
        startDayCal.setTime(startDay); //startDay ��¥�� Ķ���� ��ü�� ����
        if(startDayCal.getTimeInMillis() < endDayCal.getTimeInMillis()){ //���� startDayCal�� �ð��ʰ� endDayCal�� �ð��ʺ��� ������
        	diffMillis = endDayCal.getTimeInMillis() - startDayCal.getTimeInMillis();
        	//diffMillis ���� = endDayCal(���� ������) �ð��� - startDayCal(���� ������) �ð��� 
        	diff = (int) (diffMillis/ (24 * 60 * 60 * 1000));
        	//diff ���� = diffMillis / �ð���(�и�������)
        
        } else { //���� startDayCal�� �ð��ʰ� endDayCal�� �ð��ʺ��� ������
        	Date endDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(ss+"240000");
        	//yyyyMMDD(�����)������ startdate�� HHmmss(�ú���)�������� �ٲ㼭 Date Ÿ���� endDate ������ ����
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	enddate = sdf.format(endDate).toString(); //Date Ÿ���� endDate ������ yyyy-MM-dd �������� �ٲ㼭 ������ ����
        	diff=1; //diff(���� ������ - ���� ������) ���� 1
        }
		
		roomDAO roomdao = roomDAO.getDAO();
		accomDAO accomdao = accomDAO.getDAO();
		List roomlist = null;
		roomlist = roomdao.getRoomList(accom_code, diff, startdate);
		
		List accomlist = null;
		accomlist = accomdao.getAccomDetailList(accom_code);
		
		// ���� ������ ���� ���� ������ �����.
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();

		ReviewDAO dao = ReviewDAO.getDAO();
				
		list=dao.getReview(accom_code);
				
		request.setAttribute("reviewList", list);
		request.setAttribute("accom_code", accom_code);
		request.setAttribute("roomlist", roomlist);
		request.setAttribute("accomlist", accomlist);
		request.setAttribute("accomtype", accomtype);
		request.setAttribute("accomlocation", accomlocation);
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		
		
		return "/accom/accomRoomForm.jsp";
		
	}
	
}
