<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="com.archetipo.model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
			List<User> list = (List<User>) request.getSession().getAttribute("list");
		%>

		<br>

		<table>
			<tr>
				<th>Username</th>
				<th>Email</th>
				<th>Telephone</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for (User u : list) {
			%>
			<tr>
			
			
				<td><%=u.getUsername()%></td>
				<td><%=u.getEmail()%></td>
				<td><%=u.getTelephone()%></td>
			</tr>
			<%
				}
			%>
		</table>
</body>
</html>