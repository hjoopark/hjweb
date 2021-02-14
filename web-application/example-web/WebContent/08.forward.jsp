<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>


<%
//forward는 서버 안에서 움직이는 거라서 브라우저 주소창에는 forward로 남는다.(회사 : 내선순환)

request.setAttribute("message", "This message is from Forward");

//JSP에서 사용할 수 있는 코드
//pageContext.forward("08.result.jsp");

//JSP, Servlet 에서 사용할 수 있는 코드
RequestDispatcher dispatcher = request.getRequestDispatcher("08.result.jsp");
dispatcher.forward(request, response);

%>