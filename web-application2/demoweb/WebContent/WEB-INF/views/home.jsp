<%@page import="com.demoweb.vo.Member"%>
<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>Home</title>
	<link rel='Stylesheet' href='/demoweb/styles/default.css' />
	<link rel="Stylesheet" href="/demoweb/styles/input.css" />
	
</head>
<body>

	<div id='pageContainer'>
		<!-- 둘 다 사용 가능하지만 위에 구문이 이클립스 이외에 다른 툴에서 해석되어 사용하기 어렵다 -->
		<%-- <% pageContext.include("/WEB-INF/views/include/header.jsp"); %> --%>
		<jsp:include page="/WEB-INF/views/include/header.jsp">
			<jsp:param value="red" name="bgcolor" />
		</jsp:include>
		
		<div id='content'>
			<br /><br /><br />
			<h2 style='text-align:center'>Welcome to Demo WebSite !!!</h2>
		</div>
	</div>

</body>
</html>


