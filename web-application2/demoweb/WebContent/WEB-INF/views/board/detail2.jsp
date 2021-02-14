<%@page import="com.demoweb.vo.Member"%>
<%@page import="com.demoweb.vo.BoardAttach"%>
<%@page import="com.demoweb.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>

	<meta charset="utf-8" />
	<title>글쓰기</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input.css" />
	<script type="text/javascript">
	
	</script>	
	
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시판 글 내용</div>
		        <% Board board = (Board)request.getAttribute("board"); %>
		        <table>
		            <tr>
		                <th>제목</th>
		                <td><%= board.getTitle() %></td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td><%= board.getWriter() %></td>
		            </tr>
		            <tr>
		                <th>작성일</th>
		                <td><%= board.getRegDate() %></td>
		            </tr>
					<tr>
		                <th>조회수</th>
		                <td><%= board.getReadCount() %></td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>
		                <% for (BoardAttach attach : board.getAttachments()) { %>
		                <a href="download.action?attachno=<%= attach.getAttachNo() %>">
		                	<%= attach.getUserFileName() %>
		                </a>
		                <br>
		                <% } %>
		                </td>
		            </tr>
		            <tr>
		                <th>내용</th>
		                <td style="height:200px;vertical-align:top">
             				<%= board.getContent() %>
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
		        	<script type="text/javascript">
		        	$(function() {
		        		$('#delete-btn').on('click', function(event) {							//삭제하기 전에 자바스크립트를 이용해서 확인하기
		        			var ok = confirm('<%= board.getBoardNo() %>번 글을 삭제할까요?')
		        			if (ok) {
		        				location.href='delete.action?boardno=' + 
		        								'<%= board.getBoardNo() %>' + 
		        								'&pageno=' + 
		        								'<%= request.getParameter("pageno") %>';
		        			}
		        		});
		        		$('#update-btn').on('click', function(event) {
		        			location.href='update.action?boardno=<%= board.getBoardNo() %>' + 
							'&pageno=<%= request.getAttribute("pageno") %>';
		        		});
		        		$('#reply-btn').on('click', function(event) {
		        			location.href='reply.action?boardno=<%= board.getBoardNo() %>' + 
							'&pageno=<%= request.getAttribute("pageno") %>';
		        		});
		        	})
		        	</script>
		        	<% Member member = (Member)session.getAttribute("loginuser"); %>
		        	<!-- 작성자와 같은 아이디면 삭제 버튼 추가해준다 -->
		        	<% if (member != null && board.getWriter().equals(member.getMemberId())) { %>
		        	[&nbsp;<a id='update-btn' href='javascript:'>수정</a>&nbsp;]
		        	[&nbsp;<a id='delete-btn' href='javascript:'>삭제</a>&nbsp;]
		        	<% } %>
		        	<% if (member !=null){ %>
                 	[&nbsp; <a id='reply-btn' href='javascript:'>댓글쓰기</a>&nbsp;]
                 	<% } %>
		        	[&nbsp;<a href='list.action?pageno=<%= request.getParameter("pageno") %>'>목록보기</a>&nbsp;]
		        </div>
		    </div>
		</div>


	
	</div>
	</div>

</body>
</html>