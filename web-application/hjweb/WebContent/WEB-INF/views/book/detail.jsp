
<%@page import="com.hjweb.vo.BookAttach"%>
<%@page import="com.hjweb.vo.Member"%>
<%@page import="com.hjweb.vo.Book"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>

	<meta charset="utf-8" />
	<title>글쓰기</title>
	<link rel="Stylesheet" href="/hjweb/styles/default.css" />
	<link rel="Stylesheet" href="/hjweb/styles/input.css" />
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
		        <% Book book = (Book)request.getAttribute("book"); %>
		        <table>
		            <tr>
		                <th>도서 제목</th>
		                <td><%= book.getTitle() %></td>
		            </tr>
		            <tr>
		                <th>저자</th>
		                <td><%= book.getWriter() %></td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>
		                <% for (BookAttach attach : book.getAttachments()) { %>
		                <img src="/hjweb/upload/files/<%= attach.getSavedFileName() %>" style="width:250px;height:300px">
		                
		                <br>
		                <% } %>
		                </td>
		            </tr>
		            <tr>
		                <th>출판사</th>
		                <td><%= book.getPublisher() %></td>
		            </tr>
					<tr>
		                <th>출판일</th>
		                <td><%= book.getPubdate() %></td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td><%= book.getMemwriter() %></td>
		            </tr>
		            <tr>
		                <th>작성일</th>
		                <td><%= book.getRegdate() %></td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
		        	<script type="text/javascript">
		        	$(function() {
		        		$('#delete-btn').on('click', function(event) {							//삭제하기 전에 자바스크립트를 이용해서 확인하기
		        			var ok = confirm('<%= book.getBookNo() %>번 글을 삭제할까요?')
		        			if (ok) {
		        				location.href='delete.action?bookno=' + 
		        								'<%= book.getBookNo() %>' + 
		        								'&pageno=' + 
		        								'<%= request.getParameter("pageno") %>';
		        			}
		        		});
		        		$('#update-btn').on('click', function(event) {
		        			location.href='update.action?bookno=<%= book.getBookNo() %>' + 
							'&pageno=<%= request.getAttribute("pageno") %>';
		        		});
		        	})
		        	</script>
		        	<% Member member = (Member)session.getAttribute("loginuser"); %>
		        	<!-- 작성자와 같은 아이디면 삭제 버튼 추가해준다 -->
		        	<% if (member != null && book.getMemwriter().equals(member.getEmail())) { %>
		        	[&nbsp;<a id='update-btn' href='javascript:'>수정</a>&nbsp;]
		        	[&nbsp;<a id='delete-btn' href='javascript:'>삭제</a>&nbsp;]
		        	<% } %>
		        	[&nbsp;<a href='list.action?pageno=<%= request.getParameter("pageno") %>'>목록보기</a>&nbsp;]
		        </div>
		    </div>
		</div>


	
	</div>
	</div>

</body>
</html>