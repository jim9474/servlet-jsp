<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>연산결과</h2>
<%
try {
	int num1 = Integer.parseInt(request.getParameter("num1"));
	int num2 = Integer.parseInt(request.getParameter("num2"));
	out.print(num1+" + "+num2+" = "+(num1+num2)+"<p>");
	out.print(num1+" - "+num2+" = "+(num1-num2)+"<p>");
	out.print(num1+" * "+num2+" = "+(num1*num2)+"<p>");
	out.print(num1+" / "+num2+" = "+(num1/num2)+"<p>");
	
	
} catch (NumberFormatException e) {
	out.print("연산결과 NumberFormatException");
} catch (ArithmeticException e) {
	out.print("시스템 점검중... JSP");
	// out.print("0으로 나누었어");
} catch (Exception e) {
	out.print("JSP e.getMessage()->"+e.getMessage());
}
%>
</body>
</html>