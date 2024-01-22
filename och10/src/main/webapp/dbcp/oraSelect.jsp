<%@page import="och10.Emp"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../dbError.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
int empno = Integer.parseInt(request.getParameter("empno"));
String sql = "select empno, ename, sal, hiredate from emp where empno="+empno;
// 1. DBCP 연동
Context ctx = new InitialContext();
DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
Connection conn = ds.getConnection();
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);
if(rs.next()){
	empno = rs.getInt(1);
	String ename = rs.getString(2);
	Date hiredate = rs.getDate(4);
	int sal = rs.getInt(3);
	
	// 2. Emp DTO setter
	Emp emp = new Emp();
	emp.setEmpno(empno);
	emp.setEname(ename);
	emp.setHiredate(hiredate);
	emp.setSal(sal);
	request.setAttribute("emp", emp);
	// 3. oraResult.jsp 이동
	RequestDispatcher rd = request.getRequestDispatcher("oraResult.jsp");
	rd.forward(request, response);
	rs.close();
	stmt.close();
	conn.close();
}
stmt.close();
conn.close();



%>
</body>
</html>