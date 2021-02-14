<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

<h3>서블릿에게 HTML 결과 요청</h3>
<form action="request-html-test.action" method="post" >
	ID : <input type="text" name="id" id="id">
	<br>
	PASSWORD : <input type="password" name="passwd" id="passwd">
	<br>
	<input type="submit" value="LOGIN">
</form>

<hr>

<h3>JSP에게 HTML 결과 요청</h3>
<form action="request-html-test.jsp" method="post" >
	ID : <input type="text" name="id" id="id">
	<br>
	PASSWORD : <input type="password" name="passwd" id="passwd">
	<br>
	<input type="submit" value="LOGIN">
</form>
</body>
</html>