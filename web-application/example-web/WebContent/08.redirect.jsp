<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>


<%
//result페이지로 요청을 다시 줌

request.setAttribute("message", "This message is from Redirect");

response.sendRedirect("08.result.jsp");

%>