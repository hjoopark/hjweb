<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login Result</title>
</head>
<body>
<%
String id = request.getParameter("id"); // "id" : 입력 요소의 id속성에 지정된 값
String passwd = request.getParameter("passwd");// "pwd" : 입력 요소의 pwd속성에 지정된 값

String message = "";
if(id.equals("goott") && passwd.equals("9922")) {
	message = id + "님 환영합니다.";
}
else {
	message = "존재하지 않는 아이디 또는 패스워드 오류";
}
%>
<h1><%= message %></h1>
</body>
</html>