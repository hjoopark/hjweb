<%@page import="com.demoweb.vo.Board"%>
<%@ page language="java" 
		 contentType="text/html; charset=utf-8"
    	 pageEncoding="utf-8" %>
 
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>글쓰기</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input2.css" />
	<style type="text/css">
	a { text-decoration: none }
	</style>
	<script type="text/javascript">
	
	</script>
		
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시판 글 수정</div>
		        <form id="updateform" action="update.action" method="post">
		        <% Board board = (Board)request.getAttribute("board"); %>
		        <input type="hidden" name="pageno" value='<%= request.getParameter("pageno") %>'>
		        <table>
		        	<tr>
		                <th>글번호</th>
		                <td>
		                	<input type="hidden" name="boardno" value="<%= board.getBoardNo() %>">
		                	<%= board.getBoardNo() %>
		                </td>
		            </tr>
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="title" style="width:280px"
		                    	 value="<%= board.getTitle() %>" >
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
							<%= board.getWriter() %>
		                </td>
		            </tr>
		            <tr>
		                <th>내용</th>
		                <td>		                    
		                    <textarea name="content" cols="76" 
		                    	rows="15"><%= board.getContent() %></textarea>
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        
		        	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
			        <script type="text/javascript">
			        	$(function() {
			        		$('#update').on('click', function(event) {
			        			$('#updateform').submit();
			        		});
			        	});
			        </script>
		        	<!-- <form>의 submit 수행 -->	        	
		        	<a id="update" href="javascript:">글수정</a>
		        	&nbsp;&nbsp;
		        	<a href='detail.action?boardno=<%= board.getBoardNo() %>&pageno=<%= request.getAttribute("pageno") %>'>취소</a>
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>