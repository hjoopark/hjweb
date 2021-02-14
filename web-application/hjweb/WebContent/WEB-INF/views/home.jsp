<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>Home</title>
	<link rel='Stylesheet' href='/hjweb/styles/default.css' />
	<link rel='Stylesheet' href='/hjweb/styles/input.css' />
	<style>
	
	.container {
		margin: 0 auto;
		text-align: center;
	}
	.members {
		height: 300px;
		width: 250px;
		margin: 0 30px;
		display: inline-block;
	}
	.members img {
		height: 300px;
		width: 250px;
	}
	.members img:hover {
		transform:scale(1.1);
		-o-transform:scale(1.1);
		-moz-transform:scale(1.1);
		-webkit-transform:scale(1.1);
		transition:transform .35s;
		-o-transition:transform .35s;
		-moz-transition:transform .35s;
		-webkit-transition:transform .35s;
	}
</style>
</head>
<body>

	<div id='pageContainer'>
		
		<jsp:include page="/WEB-INF/views/include/header.jsp">
			<jsp:param value="green" name="bg-color" />
		</jsp:include>
		
		<div id='content'>
			<br /><br /><br />
			<h1 style='text-align:center;font-size:2.5em'>이 달의 도서</h1>
			<br /><br />
			<div class='container' style="margin: 0 auto; text-align: center">
				<div class='members' style="display:inline-block;"><img src='image/book1.jpg' alt='1'></div>
				<div class='members' style="display:inline-block;"><img src='image/book2.jpg' alt='2'></div>
				<div class='members' style="display:inline-block;"><img src='image/book3.jpg' alt='3'></div>
				<div class='members' style="display:inline-block;"><img src='image/book4.jpg' alt='4'></div>
			</div>
		</div>
	</div>

</body>
</html>


