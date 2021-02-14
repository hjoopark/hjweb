
<%@page import="com.hjweb.vo.Book"%>
<%@ page language="java" 
		 contentType="text/html; charset=utf-8"
    	 pageEncoding="utf-8" %>
 
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>글쓰기</title>
	<link rel="Stylesheet" href="/hjweb/styles/default.css" />
	<link rel="Stylesheet" href="/hjweb/styles/input2.css" />
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
		        <% Book book = (Book)request.getAttribute("book"); %>
		        <input type="hidden" name="pageno" value='<%= request.getParameter("pageno") %>'>
		        <table>
		        	<tr>
		                <th>글번호</th>
		                <td>
		                	<input type="hidden" name="bookno" value="<%= book.getBookNo() %>">
		                	<%= book.getBookNo() %>
		                </td>
		            </tr>
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="title" style="width:280px"
		                    	 value="<%= book.getTitle() %>" >
		                </td>
		            </tr>
		            <tr>
		                <th>저자</th>
		                <td>
							<input type="text" name="writer" style="width:280px"
		                    	 value="<%= book.getWriter() %>" >
		                </td>
		            </tr>
		            <tr>
		                <th>출판사</th>
		                <td>
							<input type="text" name="publisher" style="width:280px"
		                    	 value="<%= book.getPublisher() %>" >
		                </td>
		            </tr>
		            <tr>
		                <th>출판일</th>
		                <td>
							<input type="text" name="pubdate" style="width:280px"
		                    	 value="<%= book.getPubdate() %>" >
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
		        	<a href='detail.action?bookno=<%= book.getBookNo() %>&pageno=<%= request.getAttribute("pageno") %>'>취소</a>
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>