<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>

<!-- download는 jsp에서 처리할 수 없음. 반드시 servlet으로 구현 -->

<!-- <a href="download.action">파일 다운로드 테스트</a> -->

<a href="10.file-upload.jsp">파일 업로드</a>

<hr>

<%
// 업로드 폴더에서 파일 목록을 읽고 화면에 표시
String path = application.getRealPath("/files");
File directory = 
	new File(path);//파일 또는 디렉터리(폴더)를 관리하는 클래스(만들기, 이동, 삭제, 정보읽기...)
File[] files = directory.listFiles(); //디렉터리에 포함된 모든 파일과 디렉터리 목록 반환
%>
<h3>[ 업로드 파일 목록 ]</h3>
<% for (File file : files) { %>
	<p><a href="download.action?filename=<%= file.getName() %>"><%= file.getName() %></a></p>
<% } %>

</body>
</html>