





<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>hello ajax</title>
</head>
<body>
[ <a id="sync-req" href="javascript:">동기 방식 요청</a> ]
&nbsp;&nbsp;
[ <a id="async-req" href="javascript:">비동기 방식 요청</a> ]
<hr>
<div id="result" style='border:solid 1px;padding:10px;'>
<%= new Date().toString() %>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript">
$(function() {
	$('#sync-req').on('click', function(event) {
		location.href="sync-req.action";
	});
	
	/* var proxy = null;
	$('#async-req').on('click', function(event) {
		//비동기 요청 처리 객체 만들기 (브라우저의 내장 객체로 제공)
		proxy = new XMLHttpRequest();
		
		//비동기 요청 보내기
		proxy.open('GET',					//요청방식
					'async-req.action', 	//요청 대상
					true)					//비동기 여부
		//비동기 요청이 처리되는 단계별로 호출되는 처리기 등록
		proxy.onreadystatechange = stateChangeHandler;
		proxy.send(null); //요청 보내기
	});
	function stateChangeHandler() {
		//비동기 요청 수신 및 처리
		if (proxy.readyState == 4) {	//비동기 요청에 대한 응답 수신이 완료되었을 때
			$('#result').html(proxy.responseText); //responseText : 수신된 응답 컨텐츠
		}
	} */
	
	$('#async-req').on('click', function(event) {
		$.ajax({
			url: 'async-req.action',				//비동기 요청 대상
			success: function(data, status, xhr) {	//성공했을 때 호출할 함수
				$('#result').html(data);
			},
			error: function(xhr, status, err) {		//실패했을 때 호출할 함수
				alert('fail!!!');
			}
		});
		
	});
	
});
</script>

</body>
</html>