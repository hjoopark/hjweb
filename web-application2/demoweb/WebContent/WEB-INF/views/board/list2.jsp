<%@page import="com.demoweb.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" 
		 contentType="text/html; charset=utf-8"
    	 pageEncoding="utf-8"%>
    	 

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>게시물 목록</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<style type="text/css">
	a{ text-decoration: none; }
	</style>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript">
	$(function() {
		$('#write').on('click', function(event) {
			location.href="write.action";
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
					<th style="width:300px">제목</th>
					<th style="width:150px">작성자</th>
					<th style="width:120px">작성일</th>
					<th style="width:80px">조회수</th>
				</tr>
				
				<!-- 서블릿에서 request에 객체에 저장한 데이터 읽기 -->
				<% List<Board> boards = (List<Board>)request.getAttribute("boards"); %>
				<% for(Board board : boards) { %>
				<tr style="height:25px">
					<td><%= board.getBoardNo() %></td>
					<td style='text-align:left;margin-left:5px'>
					
					<!-- 댓글은 들여쓰기 -->
					<% for (int i = 0; i < board.getDepth(); i++) { %>
					&nbsp;&nbsp;
					<% } %>
					<% if (board.getDepth() > 0) { %>
					<img src="/demoweb/image/re.gif">
					<% } %>
					
					<% if (board.isDeleted()) { %>
						<span style="color:lightgray"><%= board.getTitle() %> [삭제된 글] </span>
					<% } else { %>
						<a href='detail.action?boardno=<%= board.getBoardNo() %>&pageno=<%= request.getAttribute("pageno") %>'>
						<%= board.getTitle() %>
						</a>
					<% } %>
					</td>
					<td><%= board.getWriter() %></td>
					<td><%= board.getRegDate() %></td>
					<td><%= board.getReadCount() %></td>
				</tr>
				<% } %>
				
			</table>
			<br><br>
			
			<%= request.getAttribute("pager").toString() %>
			
		</div>
	</div>

</body>
</html>













