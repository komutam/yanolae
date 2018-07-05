<%@ page import="review.ReviewDAO" %>
<%@ page import="review.ReviewDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");

	String accom_code = request.getParameter("accom_code");
	String re_num = request.getParameter("re_num");
	String id = request.getParameter("id");
	String starpoint = request.getParameter("starpoint");
	String re_content = request.getParameter("re_content");
	
	ReviewDTO dto = new ReviewDTO();
	dto.setAccom_code(accom_code);
	dto.setRe_num(re_num);
	dto.setId(id);
	dto.setStarpoint(Float.valueOf(starpoint));
	dto.setRe_content(re_content);
	//dao 호출
	
	ReviewDAO dao = ReviewDAO.getDAO();
	dao.reviewUpdate(dto);
	
	
	
%>
{ "reviewContnet":"<%=dto.getRe_content()%>"}
