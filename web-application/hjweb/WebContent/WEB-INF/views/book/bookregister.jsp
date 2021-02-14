
<%@page import="com.hjweb.vo.Member"%>
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
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript">
	$(function() {
		$('#write').on('click', function(event) {
			$('#writeform').submit();// <input type="submit" 버튼을 클릭한 것과 같은 효과
		});
	});
	</script>
</head>
<body>

	<div id="pageContainer">
	
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">도서 등록</div>
		        <form id="writeform" action="bookregister.action" 
		        	  method="post" enctype="multipart/form-data">
		        	  <!-- entype을 사용하면 바이너리 파일과 같이 업로드 할 수 있다. -->
		        <table>
		            <tr>
		                <th>도서 제목</th>
		                <td>
		                    <input type="text" id="title" name="title" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>저자</th>
		                <td>
                        	<input type="text" id="writer" name="writer" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>		                    
		                    <input type="file" name="attach">
		                </td>
		            </tr>
		            <tr>
		                <th>출판사</th>
		                <td>		                    
		                    <input type="text" id="publisher" name="publisher" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>출판일</th>
		                <td>		                    
		                    <input type="text" id="pubdate" name="pubdate" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
		               		<% Member member = (Member)session.getAttribute("loginuser"); %>
                        	<%= member.getEmail() %>
                        	<input type="hidden" name="memwriter" value="<%= member.getEmail() %>">
                        	<!-- hidden : 사용자에게 굳이 알릴 필요가 없고 보여줄 필요가 없지만 서버에는 보내주기 위해 사용하는 것. -->
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<a id="write" href="#">글쓰기</a>
		        	&nbsp;&nbsp;
		        	<a href="/hjweb/book/list.action">목록보기</a>
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>