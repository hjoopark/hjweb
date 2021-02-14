<%@page import="com.demoweb.vo.Member"%>
<%@page import="com.demoweb.vo.BoardAttach"%>
<%@page import="com.demoweb.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<!-- c: 라고 쓰면 "http://java.sun.com/jsp/jstl/core"로 등록된 태그라이브러리를 의미합니다. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>

	<meta charset="utf-8" />
	<title>글쓰기</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input.css" />
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript">
	$(function() {
		$('#writecomment').on('click', function(event) {
			
			var content = $('#comment_content').val();
			if (content.length == 0) {
				alert('댓글의 내용을 입력하세요');
				return;
			}
			
			event.preventDefault(); //이벤트를 발생시킨 객체의 기본 동작 수행 차단
			event.stopPropagation(); //상위 객체로의 이벤트 전달 차단
			
			//var data = $('#commentform').serialize(); // "boardno=xxx&writer=yyy&content=zzz"
			var data = $('#commentform').serializeArray(); // [{boardno:'xxx'}, {writer:'yyy'}, ]
			
			$.ajax({
				"url": "write-comment.action",
				"method": "POST",
				"data": data,
				"success": function(data, status, xhr) {
					alert('댓글을 등록했습니다.');
					$('#comment_content').val("");
					
					//목록 갱신 (ajax)
					$.ajax({
						"url": "get-comment-list.action",
						"method": "GET",
						"data": { "boardno": ${ board.boardNo } },
						"success": function(data, status, xhr) {
							var table = $('#comment-list');
							table.empty(); //기존의 tr 제거
							var trs = $(data);
							table.append(trs);
						},
						"error": function(xhr, status, err) {
							alert('댓글 목록 가져오기 실패');
						}
					});
				},
				"error": function(xhr, status, err) {
					
				}
			});
		});
		
		//////////////////
		
		//#comment-list 요소에 이벤트를 연결해서 (현재 또는 미래에 존재하는) .editcomment에 전달하세요!
		//학생한테 전달하는게 아니고 강의실에 전달하는 것.
		$('#comment-list').on('click', '.editcomment', function(event) {
			//현재 클릭된 <a 의 data-commentno 속성 값 읽기
			var commentNo = $(this).attr('data-commentno');
			
			// 편집 누르고 취소 누르고 다시 편집 눌렸을 때 다시 원래대로 돌아오도록 하는것.
			var span = $('#commentview' + commentNo + ' span');
			var content = $('#updateform' + commentNo + ' textarea')
			content.val(span.text().trim());
			
			//view-div는 숨기고, edit-div는 표시하기
			$('#commentview' + commentNo).css('display', 'none');
			$('#commentedit' + commentNo).css('display', 'block');
		});
		
		$('#comment-list').on('click', '.cancel', function(event) {
			//현재 클릭된 <a 의 data-commentno 속성 값 읽기
			var commentNo = $(this).attr('data-commentno');
			//view-div는 숨기고, edit-div는 표시하기	
			$('#commentview' + commentNo).css('display', 'block');
			$('#commentedit' + commentNo).css('display', 'none');
		});
		
		$('#comment-list').on('click', '.deletecomment', function(event) {
			//현재 클릭된 <a 의 data-commentno 속성 값 읽기
			var commentNo = $(this).attr('data-commentno');
			
			var yes = confirm(commentNo + "번 댓글을 삭제 할까요?");
			if (!yes) {
				return;
			}
			
			//ajax 방식으로 데이터 삭제
			$.ajax({
				"url": "delete-comment.action",
				"method": "GET",
				"data": { "commentno": commentNo },
				"success": function(data, status, xhr) {
					$('#tr' + commentNo).remove();
					alert('댓글을 삭제 했습니다.');
				},
				"error": function(xhr, status, err) {
					alert('댓글 삭제 실패');
				}
			});
		});
		
		
		$('#comment-list').on('click', '.updatecomment', function(event) {
			//현재 클릭된 <a 의 data-commentno 속성 값 읽기
			var commentNo = $(this).attr('data-commentno');
			var content = $('#updateform' + commentNo + ' textarea').val();
			var inputData = $('#updateform' + commentNo).serialize();
			//ajax 방식으로 데이터 수정
			$.ajax({
				"url": "update-comment.action",
				"method": "POST",
				"data": inputData,
				"success": function(data, status, xhr) {
					alert('댓글을 수정 했습니다.');
					var span = $('#commentview' + commentNo + ' span');
					span.html(content.replace('\n', '<br>'));
					//view-div는 숨기고, edit-div는 표시하기	
					$('#commentview' + commentNo).css('display', 'block');
					$('#commentedit' + commentNo).css('display', 'none');
				},
				"error": function(xhr, status, err) {
					alert('댓글 수정 실패');
				}
			});
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
		        <div class="inputsubtitle">게시판 글 내용</div>
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>${ board.title }</td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>${ board.writer }</td>
		            </tr>
		            <tr>
		                <th>작성일</th>
		                <td>${ board.regDate }
		                <br>
		                <fmt:formatDate value="${ board.regDate }"
		                				pattern="yyyy년 MM월 dd일"/>
		                </td>
		            </tr>
					<tr>
		                <th>조회수</th>
		                <td>${ board.readCount }</td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>
		                <c:forEach var="attach" items="${ board.attachments }">
		                <a href="download.action?attachno=${ attach.attachNo }">
		                	${ attach.userFileName }
		                </a>
		                </c:forEach>
		                <br>
		                
		                </td>
		            </tr>
		            <tr>
		                <th>내용</th>
		                <td style="height:200px;vertical-align:top">
<c:set var="enter" value="
" /> <% // String enter = "\n"; %>
             				${ fn:replace( board.content, enter, "<br>") }
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
		        	<script type="text/javascript">
		        	$(function() {
		        		$('#delete-btn').on('click', function(event) {							//삭제하기 전에 자바스크립트를 이용해서 확인하기
		        			var ok = confirm('${ board.boardNo }번 글을 삭제할까요?')
		        			if (ok) {
		        				location.href='delete.action?boardno=${ board.boardNo }' + 
		        								'&pageno=${ pageno }';
		        			}
		        		});
		        		$('#update-btn').on('click', function(event) {
		        			location.href='update.action?boardno=${ board.boardNo }' + 
							'&pageno=${ pageno }';
		        		});
		        		$('#reply-btn').on('click', function(event) {
		        			location.href='reply.action?boardno=${ board.boardNo }' + 
							'&pageno=${ pageno }';
		        		});
		        	})
		        	</script>
		        	<%-- <c:if test="${ loginuser != null && board.writer == loginuser.memberId }"> --%>
		        	<c:if test="${ not empty loginuser and board.writer eq loginuser.memberId }">
		        	[&nbsp;<a id='update-btn' href='javascript:'>수정</a>&nbsp;]
		        	[&nbsp;<a id='delete-btn' href='javascript:'>삭제</a>&nbsp;]
		        	</c:if>	        	
		        	
		        	<%-- <c:if test="${ loginuser != null }"> --%>
		        	<c:if test="${ not empty loginuser }">
		        	[&nbsp;<a id='reply-btn' href='javascript:'>댓글쓰기</a>&nbsp;]
		        	</c:if>
		        	[&nbsp;<a href='list.action?pageno=${ pageno }'>목록보기</a>&nbsp;]
		        </div>
		    </div>
		</div>
		<!-- comment 쓰기 영역 -->
		<br><br>
		
		<form id="commentform">
			<input type="hidden" id="comment_boardno" name="boardno" value="${ board.boardNo }" />			
			<input type="hidden" id="comment_writer" name="writer" value='${ loginuser.memberId }' />
			<table style="width:600px;border:solid 1px;margin:0 auto">
	            <tr>
	                <td style="width:550px">	                	
	                    <textarea id="comment_content" name="content" 
	                    	style="width:550px" rows="3"></textarea>	                    
	                </td>
	                <td style="width:50px;vertical-align:middle">
	                	<a id="writecomment" href="#" style="text-decoration:none">
	                		댓글<br />등록
	                	</a>
	                </td>
	            </tr>                    
	        </table>
        </form>
        
        <!-- comment list -->
        <table id="comment-list" style="width:600px;border:solid 1px;margin:0 auto">			
			<c:forEach var="comment" items="${ board.comments }">
			<tr id="tr${ comment.commentNo }">
				<td style="text-align:left;margin:5px;border-bottom: solid 1px">
	        		<div id='commentview${ comment.commentNo }'>
	        			${ comment.writer } &nbsp;&nbsp;
	        			[${ comment.regDate }]
	                    <br /><br />
	                    <span>
	                    ${ comment.content }
	                    </span>
	                    <br /><br />
	                    <div style='display:${ loginuser.memberId eq comment.writer ? "block" : "none" }'>
	                    	<a class="editcomment" data-commentno='${ comment.commentNo }' href="javascript:">편집</a>
	                    	&nbsp;
	                    	<a class="deletecomment" 
	                    	   href="javascript:"
	                    	   data-commentno="${ comment.commentNo }">삭제</a>
	                    </div>
	                </div>
	                
	                <div id='commentedit${ comment.commentNo }' style="display: none">
	                	${ comment.writer } &nbsp;&nbsp;
	        			[${ comment.regDate }]
						<br /><br />
						<form id="updateform${ comment.commentNo }" >
						<input type="hidden" name="commentno" value="${ comment.commentNo }" />
						<textarea name="content" style="width: 600px" rows="3" 
							maxlength="200">${ comment.content }</textarea>
						</form>
						<br />
						<div>
							<a class="updatecomment" href="javascript:" data-commentno="${ comment.commentNo }">수정</a> 
							&nbsp; 
							<a class="cancel" data-commentno="${ comment.commentNo }" href="javascript:">취소</a>
						</div>
					</div>
		
				</td>
        	</tr>
        	</c:forEach>						
	        </table>
	    
		<!-- comment 표시 영역 -->

	
	</div>
	</div>
	
	<br><br><br><br><br>


</body>
</html>