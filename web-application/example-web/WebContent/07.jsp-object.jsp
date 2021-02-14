<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>

<% out.write("Hello, JSP Object"); %>

<% request.setAttribute("attr1", new Date());
pageContext.setAttribute("attr2", "This message is from pageContext");
%>

<%= request.getAttribute("attr1") %>
<br>
<%= pageContext.getAttribute("attr2") %>

</body>
</html>