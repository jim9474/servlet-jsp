<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 스크립틀릿 --> 자바코딩
		// 파라미터 --> num1, num2
		// 사칙연산
		request.setCharacterEncoding("utf-8");
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		float num3 = (float)num1/num2;
		
		out.println("<h1>사칙연산</h1><p>");
		out.print("덧셈 : "+(num1+num2)+"<p>");
		out.println("뺄셈 : "+(num1-num2)+"<p>");
		out.println("곱셈 : "+(num1*num2)+"<p>");
		out.println("나눗셈 : "+num3+"<p>");
	%>
	<!-- 표현식 -->
	덧셈 : <%=(num1+num2) %><p>
	뺄셈 : <%=(num1-num2) %><p>
	곱셈 : <%=(num1*num2) %><p>
	나눗셈 : <%=num3 %><p>
</body>
</html>