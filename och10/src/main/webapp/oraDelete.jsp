<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>스크립틀릿 + Statement 부서 삭제 Hw2</h2>
	<%
	String deptno = request.getParameter("deptno");
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@172.30.1.84:1521:xe";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, "scott", "tiger");
	String sql = "delete from dept where deptno=" + deptno;
	Statement stmt = conn.createStatement();
	int result = stmt.executeUpdate(sql);
	if (result > 0)
		out.print("코드 " + deptno + " 행이 삭제되었습니다");
	else
		out.print("없는 코드야");
	%>
</body>
</html>