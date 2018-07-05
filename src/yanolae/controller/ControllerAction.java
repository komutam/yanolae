package yanolae.controller;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;//HashMap

import accom.action.CommandAction;
/// commandAction(부모변수) = new ListAction() 자식객체.
//  부모는 자식객체를 다 받을 수 있다.
//
//컨트롤러다
public class ControllerAction extends HttpServlet{
 private Map map=new HashMap();
 
 //init():초기화 작업
 public void init(ServletConfig config)throws ServletException{
	 String path=config.getServletContext().getRealPath("/");
	 String pros=path+config.getInitParameter("propertyFile");
	 Properties pr = new Properties();
	 FileInputStream f = null;
	 try{
		 f=new FileInputStream(pros);//commandPro.properties 읽기
		 pr.load(f);
	 }catch(IOException ie){throw new ServletException(ie);}
	 finally{
		 try{
			 if(f!=null){f.close();}
		 }catch(Exception ex){}
	 }//finally
	//Iterator는 collection 자료를 받아서 처리할 수 있다.
	 Iterator keyItor=pr.keySet().iterator();
	 
	 while(keyItor.hasNext()){
		 String key=(String)keyItor.next();// /ch19/list.do
		 String className=pr.getProperty(key); // ch19.action.ListAction
		 try{
			 Class commandClass=Class.forName(className);//해당 문자열을 클래스로 만든다.
			 Object commandObject=commandClass.newInstance();//클래스를 객체생성
			 map.put(key, commandObject);
		 }catch(Exception ex3){
			 System.out.println("property 파일 내용을 클래스 객체로 만드는중 예외 발생"+ex3);
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
	String view="";//변수 ,jsp넣을 변수
	CommandAction commandAction = null;//변수,Action 클래스 넣을 변수
	
	try{
		String uri=request.getRequestURI();// /프로젝트명/ch19/list.do
		//request.getContextPath()=> /프로젝트명
		if(uri.indexOf(request.getContextPath())==0){
			uri=uri.substring(request.getContextPath().length());
			// "/프로젝트명/ch19/list.do".substring(위치(/프로젝트명), 끝까지)
			// ==>> 결과 : /ch19/list.do
			
		}// if
		commandAction=(CommandAction)map.get(uri);// key=uri=/ch19/list.do
		view=commandAction.requestPro(request, response);
		//System.out.println("view:::"+view);
	}catch(Throwable ex){
		throw new ServletException(ex);
	}// catch
	
	request.setAttribute("CONTENT", view);
	
	RequestDispatcher rd=request.getRequestDispatcher("/template/template.jsp");
	rd.forward(request, response);// jsp로 포워딩
 }//reqPro
 
 

}///class
