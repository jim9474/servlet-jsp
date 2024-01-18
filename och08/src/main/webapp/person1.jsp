<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Bean 선언 -->
<!-- 1. Person person = new Person(); -->
<jsp:useBean id="p" class="och08.Person"></jsp:useBean>

<!-- Bean 값 저장 -->
<!-- 2. person.setName(" "); -->
<jsp:setProperty property="*" name="p"/>

<!-- Bean 값 가져오기 -->
<!-- 3. person.getName(); -->
이름 : <jsp:getProperty property="name" name="p"/><p>
성별 : <jsp:getProperty property="gender" name="p"/><p>
나이 : <jsp:getProperty property="age" name="p"/><p>
</body>
</html>