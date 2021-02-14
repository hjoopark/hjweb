<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>Home</title>
	<link rel='Stylesheet' href='/demoweb/styles/default.css' />
</head>
<body>

	<div id='pageContainer'>
	
		<jsp:include page="/WEB-INF/views/include/header.jsp">
			<jsp:param value="red" name="bg-color" />
		</jsp:include>
		
		<div id='content'>
			<br /><br /><br />
			<h2 style='text-align:center'>여기는 관리자 페이지입니다.</h2>
		</div>
	</div>

</body>
</html>


