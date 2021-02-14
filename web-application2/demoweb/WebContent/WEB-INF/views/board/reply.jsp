<%@page import="com.demoweb.vo.Board"%>
<%@page import="com.demoweb.vo.Member"%>
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
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript">
	$(function() {
		$('#write').on('click', function(event) {
			$('#replyform').submit();// <input type="submit" 버튼을 클릭한 것과 같은 효과
		});
	});
	</script>
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시판 글 쓰기</div>
		        <form id="replyform" action="reply.action" 
		        	  method="post" enctype="multipart/form-data">
		        	  <!-- entype을 사용하면 바이너리 파일과 같이 업로드 할 수 있다. --> 
				
		       	<input type="hidden" name="boardno" value="${ board.boardNo }">
		       	<input type="hidden" name="pageno" value="${ pageno }">
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="title" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
                        	${ loginuser.memberId }
                        	<input type="hidden" name="writer" value="${ loginuser.memberId }">
                        	<!-- hidden : 사용자에게 굳이 알릴 필요가 없고 보여줄 필요가 없지만 서버에는 보내주기 위해 사용하는 것. -->
		                </td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>		                    
		                    <input type="file" name="attach">
		                </td>
		            </tr>
		            <tr>
		                <th>내용</th>
		                <td>		                    
		                    <textarea 
		                    	name="content" cols="76" rows="15"></textarea>
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<a id="write" href="javascript:">댓글쓰기</a>
		        	&nbsp;&nbsp;
		        	<a href="/demoweb/board/detail.action?boardno=${ board.boardNo }&pageno=${ pageno }">취소</a>
		        	<a href="/demoweb/board/list.action?pageno=${ pageno }">목록보기</a>
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>