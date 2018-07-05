<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*"%>

<%
String id=request.getParameter("id");//ajax에서 보내준 것
member.MemberDAO dao = member.MemberDAO.getDAO();//dao객체 얻기
int check = dao.insertCheck(id);//1사용중, -1사용가능 
%>

{
"check":<%=check %>
}