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
		
		<div id="header">    	
            <div class="title">
                <a href="/demoweb/home.action">DEMO WEBSITE</a>
            </div>
            <div class="links">
            <%-- 로그인 여부 확인  : session에 로그인 데이터 저장 여부 확인--%>
            <% Member member = (Member)session.getAttribute("loginuser"); %>
            <% if (member != null) { // 로그인 상태 %>
            	<%= member.getMemberId() + "님 환영합니다." %>
            	<a href="/demoweb/account/logout.action">로그아웃</a>
            <% } else { // 로그아웃 상태 %>
            	<a href="/demoweb/account/login.action">로그인</a>
                <a href="/demoweb/account/register.action">회원가입</a>
            <% } %>          
            </div>
        </div>        
        <div id="menu">
            <div>
                <ul>
                    <li><a href="#">사용자관리</a></li>
					<li><a href="#">메일보내기</a></li>
					<li><a href="#">자료실</a></li>
					<li><a href="#">게시판</a></li>
                </ul>
            </div>
		</div>
		
		<div id='content'>
			<br /><br /><br />
			<h2 style='text-align:center'>Welcome to Demo WebSite !!!</h2>
		</div>
	</div>

</body>
</html>


