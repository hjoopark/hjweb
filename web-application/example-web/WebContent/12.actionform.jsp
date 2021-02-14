<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Action Demo Form</title>
</head>
<body>
	<br /><br /><br /><br /><br />
	<div style="width:600px;margin:0 auto">
		<%-- <jsp:forward page="02.hello.jsp" /> --%>
	</div>
	
	<hr />
	
	<form action="12.actiontest.jsp" method="post">
	<table width="600px" border="1" align="center">
		<tr>
			<td style="width:150px">아이디</td>
			<td><input type="text" name="memberId" style="width:400px" /></td>
		</tr>
		<tr>
			<td style="width:150px">열쇠글</td>
			<td><input type="password" name="passwd" style="width:400px" /></td>
		</tr>
		<tr>
			<td style="width:150px">이메일</td>
			<td><input type="text" name="email" style="width:400px" /></td>
		</tr>
		<tr>
			<td style="width:150px">전화번호</td>
			<td><input type="text" name="phone" style="width:400px" /></td>
		</tr>
		<tr>
			<td style="width:150px">사용자구분</td>
			<td>
				<input type="radio" name="userType" value="user" checked="checked" />사용자
				<input type="radio" name="userType" value="admin" />관리자
			</td>
		</tr>
		<tr>
			<td style="width:150px">활성화여부</td>
			<td>
				<input type="checkbox" name="active" value="true" checked="checked" />활성사용자
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center">
				<input type="submit" value="전송" />
			</td>
		</tr>
	
	</table>
	</form>

</body>
</html>