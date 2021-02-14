<%@page import="com.demoweb.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% if (request.getParameter("bg-color") != null) { %>
<div id="header"
	style='background-color:<%= request.getParameter("bg-color") %>'>
	<% } else { %>
	<div id="header">
		<% } %>
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
			<a href="/demoweb/account/login.action">로그인</a> <a
				href="/demoweb/account/register.action">회원가입</a>
			<% } %>
		</div>
	</div>
	<div id="menu">
		<div>
			<ul>
				<% if (member != null && member.getUserType().equals("admin")) { %>
                    <li><a href="/demoweb/admin/memberlist.action">사용자관리</a></li>
                <% } %>
				<li><a href="#">메일보내기</a></li>
				<li><a href="#">자료실</a></li>
				<li><a href="/demoweb/board/list.action">게시판</a></li>
			</ul>
		</div>
	</div>
	<div style="text-align:right; padding:5px; border:solid 1px; margin-top:1px">
	[ CURRENT : <%= application.getAttribute("current") %> ]
	[ TOTAL : <%= application.getAttribute("total") %> ]
	</div>
	
	
	
	