<%@page import="java.sql.ResultSet"%>
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
	<%
	// Home Work 1
	// 조건 1 : deptno가지고 dept Table 조회 -> createStatement
	String deptno = request.getParameter("deptno");
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@172.30.1.84:1521:xe";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, "scott", "tiger");
	String sql = "select * from dept where deptno=" + deptno;
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);

	if (rs.next()) {
		int deptnoInt = rs.getInt(1);
		String dname = rs.getString(2);
		String loc = rs.getString(3);
		out.print("코드 : " + deptnoInt + "<p>");
		out.print("부서명 : " + dname + "<p>");
		out.print("근무지 : " + loc + "<p>");
	} else
		out.print("코드가 없어");
	rs.close();
	stmt.close();
	conn.close();
	%>
</body>
</html>