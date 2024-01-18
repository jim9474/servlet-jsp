<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>연산결과 오류시 Java Script이용 처리</h2>
	<%
	try {
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		out.print(num1 + " + " + num2 + " = " + (num1 + num2) + "<p>");
		out.print(num1 + " - " + num2 + " = " + (num1 - num2) + "<p>");
		out.print(num1 + " * " + num2 + " = " + (num1 * num2) + "<p>");
		out.print(num1 + " / " + num2 + " = " + (num1 / num2) + "<p>");

	} catch (NumberFormatException e) {
		
	
	%>
	<script type="text/javascript">
		alert("그게 숫자냐");
		history.go(-1);	// 전페이지로 가라
	</script>
	<% } catch(ArithmeticException e) { %>
	<script type="text/javascript">
		alert("0으로 못나눠");
		history.back();	// 전페이지로 가라
	</script>
	<% } catch(Exception e) { 
		out.print(e.getMessage());
	%>
	<script type="text/javascript">
		alert("하여튼 에라이");
		location.href="num2.jsp";
	</script>
	<% } %>
</body>
</html>