<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>


<%
//forward�� ���� �ȿ��� �����̴� �Ŷ� ������ �ּ�â���� forward�� ���´�.(ȸ�� : ������ȯ)

request.setAttribute("message", "This message is from Forward");

//JSP���� ����� �� �ִ� �ڵ�
//pageContext.forward("08.result.jsp");

//JSP, Servlet ���� ����� �� �ִ� �ڵ�
RequestDispatcher dispatcher = request.getRequestDispatcher("08.result.jsp");
dispatcher.forward(request, response);

%>