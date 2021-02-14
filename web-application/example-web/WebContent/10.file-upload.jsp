<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>

<form action="10.process-upload.jsp" method="post" enctype="multipart/form-data">
	입력 데이터 : <input type="text" name="my-data">
	<br>
	파일 데이터 : <input type="file" name="my-file">
	<br><br>
	<input type="submit" value="파일 업로드">
</form>

</body>
</html>