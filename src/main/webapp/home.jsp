<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.renthouse.model.User"
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>
		Welcome
		<%=SecurityContextHolder.getContext().getAuthentication().getName()%></h1>
</body>
</html>