<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<f:formatNumber value="1000000" groupingUsed="true" />
	<br>
	<f:formatNumber value="3.1415923" pattern="#.###" />
	<br>
	<f:formatNumber value="3.1" pattern="#.##" />
	<br>
	<f:formatNumber value="3.1" pattern="#.00" />
	<br>
	<f:formatNumber value="12143423453." pattern="#,###.00" />
	<br>
	<f:formatNumber value="250000" type="currency" currencySymbol="\\" />
	<br>
	<f:formatNumber value="0.75" type="percent" />
	<br>
</body>
</html>