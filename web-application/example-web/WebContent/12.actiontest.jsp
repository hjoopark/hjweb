<%@page import="com.exampleweb.vo.Member"%>
<%@ page language="java" 
		 contentType="text/html; charset=utf-8"
    	 pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Action Test</title>
</head>
<body>

<%-- <% Member member = new Member(); %> --%>
<jsp:useBean id="member" class="com.exampleweb.vo.Member" scope="page" />

<%-- 
<%
String memberId = request.getParameter("memberId");
String passwd = request.getParameter("passwd");
// .... 생략
member.setMemberId(memberId);
member.setPasswd(passwd);
// .... 생략
%>
--%>
<%-- <jsp:setProperty property="memberId" name="member" />
<jsp:setProperty property="passwd" name="member" /> --%>

<jsp:setProperty property="*" name="member"/>

<h3>MEMBER ID : <jsp:getProperty name="member" property="memberId" /></h3>
<h3>PASSWORD : <jsp:getProperty name="member" property="passwd" /></h3>
<h3>EMAIL : <jsp:getProperty name="member" property="email" /></h3>
</body>
</html>





