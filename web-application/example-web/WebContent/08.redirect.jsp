<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>


<%
//result�������� ��û�� �ٽ� ��

request.setAttribute("message", "This message is from Redirect");

response.sendRedirect("08.result.jsp");

%>