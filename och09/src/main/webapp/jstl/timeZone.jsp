<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:set var="date" value="<%=new Date()%>"></c:set>
<body>
	서울 :
	<f:formatDate value="${date }" type="both" />
	<br>
	
	<f:timeZone value="Asia/Hong_Kong">
		홍콩: <f:formatDate value="${date }" type="both" />
		<br>
	</f:timeZone>
	
	<f:timeZone value="Europe/London">
		런던: <f:formatDate value="${date }" type="both" />
		<br>
	</f:timeZone>
	
	<f:timeZone value="America/New_York">
		뉴욕: <f:formatDate value="${date }" type="both" />
		<br>
	</f:timeZone>
</body>
</html>