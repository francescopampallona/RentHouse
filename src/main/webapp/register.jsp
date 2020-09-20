<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<form method="POST" action="/users/register">
		<input type="text" name="username" placeholder="username"> 
		<input type="text" name="telephone" placeholder="telephone"> 
		<input type="email" name="email" placeholder="email">
		<input type="password" name="password" placeholder="password"> 
		<input type="submit" value="login">
	</form>
</body>
</html>