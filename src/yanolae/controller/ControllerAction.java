package yanolae.controller;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;//HashMap

import accom.action.CommandAction;
/// commandAction(�θ𺯼�) = new ListAction() �ڽİ�ü.
//  �θ�� �ڽİ�ü�� �� ���� �� �ִ�.
//
//��Ʈ�ѷ���
public class ControllerAction extends HttpServlet{
 private Map map=new HashMap();
 
 //init():�ʱ�ȭ �۾�
 public void init(ServletConfig config)throws ServletException{
	 String path=config.getServletContext().getRealPath("/");
	 String pros=path+config.getInitParameter("propertyFile");
	 Properties pr = new Properties();
	 FileInputStream f = null;
	 try{
		 f=new FileInputStream(pros);//commandPro.properties �б�
		 pr.load(f);
	 }catch(IOException ie){throw new ServletException(ie);}
	 finally{
		 try{
			 if(f!=null){f.close();}
		 }catch(Exception ex){}
	 }//finally
	//Iterator�� collection �ڷḦ �޾Ƽ� ó���� �� �ִ�.
	 Iterator keyItor=pr.keySet().iterator();
	 
	 while(keyItor.hasNext()){
		 String key=(String)keyItor.next();// /ch19/list.do
		 String className=pr.getProperty(key); // ch19.action.ListAction
		 try{
			 Class commandClass=Class.forName(className);//�ش� ���ڿ��� Ŭ������ �����.
			 Object commandObject=commandClass.newInstance();//Ŭ������ ��ü����
			 map.put(key, commandObject);
		 }catch(Exception ex3){
			 System.out.println("property ���� ������ Ŭ���� ��ü�� ������� ���� �߻�"+ex3);
		 }
	 }//while
 }//init
 
 public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
	 reqPro(request,response);
 }
 public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
	 reqPro(request,response);
 }
 private void reqPro(HttpServletRequest request,HttpServletResponse response)
		 throws IOException,ServletException{
	String view="";//���� ,jsp���� ����
	CommandAction commandAction = null;//����,Action Ŭ���� ���� ����
	
	try{
		String uri=request.getRequestURI();// /������Ʈ��/ch19/list.do
		//request.getContextPath()=> /������Ʈ��
		if(uri.indexOf(request.getContextPath())==0){
			uri=uri.substring(request.getContextPath().length());
			// "/������Ʈ��/ch19/list.do".substring(��ġ(/������Ʈ��), ������)
			// ==>> ��� : /ch19/list.do
			
		}// if
		commandAction=(CommandAction)map.get(uri);// key=uri=/ch19/list.do
		view=commandAction.requestPro(request, response);
		//System.out.println("view:::"+view);
	}catch(Throwable ex){
		throw new ServletException(ex);
	}// catch
	
	request.setAttribute("CONTENT", view);
	
	RequestDispatcher rd=request.getRequestDispatcher("/template/template.jsp");
	rd.forward(request, response);// jsp�� ������
 }//reqPro
 
 

}///class
