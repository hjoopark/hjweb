<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%!
public String getDateString() {
	return new Date().toString();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	
	<%-- JSP 주석 : 서블릿 컨테이너에게 주석, 서버에서 처리 X, 브라우저에 전송X --%>
	<!-- HTML 주석 : Browser에게 주석, 서버에서 처리 o, 브라우저에 전송 o -->
	
	<h2 style="text-align:center;color:blue"><% out.write(new Date().toString()); %></h2>
	<h2 style="text-align:center;color:blue"><%= new Date().toString() %></h2>
	<h2 style="text-align:center;color:blue"><%= getDateString() %></h2>
	
</body>
</html>