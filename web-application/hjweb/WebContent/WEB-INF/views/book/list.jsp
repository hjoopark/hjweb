
<%@page import="com.hjweb.vo.BookAttach"%>
<%@page import="com.hjweb.vo.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" 
		 contentType="text/html; charset=utf-8"
    	 pageEncoding="utf-8"%>
    	 

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>게시물 목록</title>
	<link rel="Stylesheet" href="/hjweb/styles/default.css" />
	<style type="text/css">
	a{ text-decoration: none; }
	</style>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript">
	$(function() {
		$('#write').on('click', function(event) {
			location.href="bookregister.action";
			//location.href="/demoweb/board/write.action";
		})
	});
	</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
	<div id="pageContainer">		
		<div style="padding-top:25px;text-align:center">
			
			<input id="write" type="button" value="글쓰기" 
				style="width:300px;height:25px" />
				
			<br /><br />
			
			<table border="1" align="center">
				<tr style="background-color:beige;height:25px">
					<th style="width:50px">번호</th>
					<th style="width:100px">썸네일</th>
					<th style="width:300px">제목</th>
					<th style="width:150px">저자</th>
					<th style="width:200px">출판사</th>
					<th style="width:150px">작성일</th>
				</tr>
				
				<!-- 서블릿에서 request에 객체에 저장한 데이터 읽기 -->
				<% List<Book> books = (List<Book>)request.getAttribute("books"); %>
				<% for(Book book : books) { %>
				<tr style="height:25px">
					<td><%= book.getBookNo() %></td>
					
					<td>
		                <% for (BookAttach attach : book.getAttachments()) { %>
		                <img src="/hjweb/upload/files/<%= attach.getSavedFileName() %>" style="width:100px;height:150px">
		                
		                <% } %>
		            </td>
					
					<td style='text-align:left;margin-left:5px'>
					<% if (book.isDeleted()) { %>
						<span style="color:lightgray"><%= book.getTitle() %> [삭제된 글] </span>
					<% } else { %>
						<!-- 도서 제목을 누르면 href로 bookno와 pageno가 전달된다. -->
						<a href='detail.action?bookno=<%= book.getBookNo() %>&pageno=<%= request.getAttribute("pageno") %>'>
						<%= book.getTitle() %>
						</a>
					<% } %>
					</td>
					<td><%= book.getWriter() %></td>
					<td><%= book.getPublisher() %></td>
					<td><%= book.getRegdate() %></td>
				</tr>
				<% } %>
				
			</table>
			<br><br>
			
			<%= request.getAttribute("pager").toString() %>
			
		</div>
	</div>

</body>
</html>













