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
	String pgm = request.getParameter("pgm")+".jsp";
	int gugu = Integer.parseInt(request.getParameter("gul"));
	RequestDispatcher rd = request.getRequestDispatcher(pgm);
	
	if(pgm.equals("naver.jsp")){
		rd.forward(request, response);
	} else if(pgm.equals("google.jsp")){
		rd.forward(request, response);
	} else {
		for(int i=1; i<10; i++){
			out.print(gugu+" * "+i+" = "+(gugu*i)+"<p>");
		} 
	}
	
	
	
	
%>
</body>
</html>