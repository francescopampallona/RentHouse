<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.renthouse.model.User"
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="/css/stile.css" rel="stylesheet">

<jsp:include page="imports.jsp"></jsp:include>

<title>HOME</title>
</head>
<body>
<%
   User user = (User)request.getAttribute("user");
%>
	<jsp:include page="header.jsp"></jsp:include>
	<h1>Welcome <%=user.getUsername()%></h1>
	
	<h2>Personal data</h2>
	
	<table>
	<tr>
	<th>Email</th>
	<th>Telephone</th>
	<th>Update</th>
	</tr>
	<tr>
	<td><%=user.getEmail()%></td>
	<td><%=user.getTelephone()%></td>
	<td><button class="btn btn-primary" data-toggle="modal" data-target="#updateModal">Update</button></td>
	</tr>
	</table>
	
	<!-- Modal for update-->
	<div class="modal" id="updateModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <!-- Modal header -->
	      <div class="modal-header">
	        <h4 class="modal-title">Update</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <!-- Modal body -->
	    <div class="modal-body">
	      <form action="/user/update/" method="post">
	         <input class="form-control" type="text" name="email" placeholder="email" value="<%=user.getEmail()%>">
	         <input class="form-control" type="text" name="telephone" placeholder="telephone" value="<%=user.getTelephone()%>">
	         <input type="submit" class="btn btn-primary">
	      </form>
	    </div>
	    <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
	    </div>	 
	  </div>
	  
	</div>
	
	
	
		 
</body>
</html>