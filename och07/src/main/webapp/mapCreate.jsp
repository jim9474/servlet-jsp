<%@page import="java.util.HashMap"%>
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
	//		key		value
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("Park", "목동");
	map.put("도엽옹", "힙합");
	map.put("김동욱", "힙합전우");
	map.put("김지훈", "시드니");

	request.setAttribute("ADDRESS", map);
	RequestDispatcher dp = request.getRequestDispatcher("mapView.jsp?NAME=Park");
	dp.forward(request, response);
	%>
</body>
</html>