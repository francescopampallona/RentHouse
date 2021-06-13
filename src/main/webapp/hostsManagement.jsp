<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" import="com.renthouse.model.Host"%>
 <!DOCTYPE html>
 <html>
 <head>
 <meta charset="UTF-8">
 <link href="/css/stile.css" rel="stylesheet">
 <jsp:include page="imports.jsp"></jsp:include>
 <title>HOSTS MANAGEMENT</title>
 </head>
 
 <body>
 <jsp:include page="header.jsp"></jsp:include>
 <%
   Iterable<Host> hostList = (Iterable<Host>) request.getAttribute("hosts"); 
 %>
<!--------------------------------HOST REGISTRATION--------------------------------------->
<button class="btn btn-primary" data-toggle="modal" data-target="#newHost">REGISTER A NEW HOST</button>

<!-- Modal for registration of a new host -->
<div class="modal" id="newHost">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal header -->
            <div class="modal-header">
              <h4 class="modal-title">HOST REGISTRATION</h4>
			<button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
            <form action="/host/insert" method="POST">
              <label for="name">Name</label>
              <input class="form-control" type="text" name="name" placeholder="Name">
              
              <label for="surname">Surname</label>
              <input class="form-control" type="text" name="surname" placeholder="Surname">
              
              <label for="email">Email</label>
              <input class="form-control" type="text" name="email" placeholder="Email">
              
              <label for="nation">Nation</label>
              <input class="form-control" type="text" name="nation" placeholder="Nation">
              
              <label for="birthCity">Birth city</label>
              <input class="form-control" type="text" name="birthCity" placeholder="Birth city">
              
              <label for="birthDate">Birth date</label>
              <input class="form-control" type="date" name="birthDate" placeholder="Birth date">
              
              <label for="telephoneNumber">Telephone number</label>
              <input class="form-control" type="text" name="telephoneNumber" placeholder="Telephone number">
              
              <label for="creditCardNumber">Credit card number</label>
              <input class="form-control" type="text" name="creditCardNumber" placeholder="Credit card number">
              
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

<!------------------------------------------- HOST MANAGEMENT ----------------------------------->
<table>
<tr>
<th>Name</th>
<th>Surname</th>
<th>Email</th>
<th>Telephone number</th>
<th></th>
<th></th>
</tr>
<%
   for(Host host: hostList){
%>
<tr>
<td><%=host.getName() %></td>
<td><%=host.getSurname() %></td>
<td><%=host.getEmail() %></td>
<td><%=host.getTelephoneNumber() %></td>
<td><button data-toggle="modal" data-target="#detailsModal<%=host.getId()%>">DETAILS</button></td>
<td><button data-toggle="modal" data-target="#updateModal<%=host.getId()%>">UPDATE</button></td>
<!-- ------------------------------MODAL FOR DETAILS ------------------------------------------>
<td>
<div class="modal" id="detailsModal<%=host.getId()%>">
  <div class="modal-dialog">
	<div class="modal-content">
	<!-- Modal header -->
	<div class="modal-header">
		<h4 class="modal-title">Host details</h4>
	    <button type="button" class="close" data-dismiss="modal">&times;</button>
	</div>
	<!-- Modal body -->
	<div class="modal-body">
        <div class="row">
          <div class="col-md-6">Name:</div>
          <div class="col-md-6"><%=host.getName()%></div>
        </div>	
        
        <div class="row">
          <div class="col-md-6">Surname:</div>
          <div class="col-md-6"><%=host.getSurname() %></div>
        </div>
        
        <div class="row">
          <div class="col-md-6">Nation:</div>
          <div class="col-md-6"><%=host.getNation() %></div>
        </div>
        
        <div class="row">
          <div class="col-md-6">Birth city</div>
          <div class="col-md-6"><%=host.getBirthCity() %></div>
        </div>
        
        <div class="row">
          <div class="col-md-6">Birth date</div>
          <div class="col-md-6"><%=host.getBirthDate() %></div>
        </div>
        
        <div class="row">
          <div class="col-md-6">Email:</div>
          <div class="col-md-6"><%=host.getEmail() %></div>
        </div>
        
        <div class="row">
          <div class="col-md-6">Telephone number:</div>
          <div class="col-md-6"><%=host.getTelephoneNumber()%></div>
        </div>
        
        <div class="row">
          <div class="col-md-6">Credit card number</div>
          <div class="col-md-6"><%=host.getCreditCardNumber()%></div>
        </div>
	</div>
</div>
</div>
</div>
</td>
<!---------------------------------MODAL FOR UPDATE ------------------------------------------->
<td>
  <div class="modal" id="updateModal<%=host.getId()%>">
    <div class="modal-dialog">
    <div class="modal-content">
    <!-- Modal header -->
    <div class="modal-header">
      <h4 class="modal-title">HOST UPDATE</h4>
      <button type="button" class="close" data-dismiss="modal">&times;</button>
    </div>
    <!-- Modal body -->
    <div class="modal-body">
      <form action="/host/update/<%=host.getId()%>" method="POST">
      <label for="name">Name</label>
      <input class="form-control" type="text" name="name" placeholder="Name" value="<%=host.getName()%>">
      
      <label for="surname">Surname</label>
      <input class="form-control" type="text" name="surname" placeholder="Surname" value="<%=host.getSurname()%>">
      
      <label for="nation">Nation</label>
      <input class="form-control" type="text" name="nation" placeholder="Nation" value="<%=host.getNation() %>">
      
      <label for="birthCity">Birth city</label>
      <input class="form-control" type="text" name="birthCity" placeholder="birthCity" value="<%=host.getBirthCity()%>" >
      
      <label for="birthDate">Birth date</label>
      <input class="form-control" type="date" name="birthDate" placeholder="birthDate" value="<%=host.getBirthDate() %>" >      
      
      <label for="email">Email</label>
      <input class="form-control" type="text" name="email" placeholder="Email" value="<%=host.getEmail() %>">
      
      <label for="telephoneNumber">Telephone number</label>
      <input class="form-control" type="text" name="telephoneNumber" placeholder="Telephone number" value="<%=host.getTelephoneNumber()%>">
      
      <label for="creditCardNumber">Credit card number</label>
      <input class="form-control" type="text" name="creditCardNumber" placeholder="Credit card number" value="<%=host.getCreditCardNumber()%>">
      
      <input type="submit" value="Update host">
      </form>
    </div>
    </div>
    </div>
    </div>
  </div>
</td>

</tr>
<%
  }
%>
</table>
 </body>
 </html>