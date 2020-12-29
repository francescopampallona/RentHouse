<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.renthouse.model.Reservation" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RESERVATION MANAGEMENT</title>

<link href="/css/stile.css" rel="stylesheet">
<jsp:include page="imports.jsp"></jsp:include>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%
List<Reservation> reservations = (List<Reservation>)request.getAttribute("reservations");
long houseId = (Long)request.getAttribute("houseId");
%>
<!-----------------------MESSAGES --------------------------->
	<%
		if (request.getAttribute("errorMessage") != null) {
	%>
	<div class="alert alert-danger" role="alert"><%=request.getAttribute("errorMessage")%></div>
	<%
		} else if (request.getAttribute("successMessage") != null) {
	%>
	<div class="alert alert-success" role="alert">
		<%=request.getAttribute("successMessage")%></div>
	<%
		}
	%>
<div class="row">
  <div class="col-md-4">
    <form action="/reservation/insert/<%=houseId%>/" method="POST">
    <fieldset class="border p-2">
    <legend class="w-auto">REGISTER RESERVATION</legend>
	  <label for="startReservation">Start reservation date</label>
	  <input class="form-control" type="date" name="startReservation">
	  
	  <label for="endReservation">End reservation date</label>
	  <input class="form-control" type="date" name="endReservation">
	  
	  <label for="potentialHostName">Potential host name</label>
	  <input class="form-control" type="text" name="potentialHostName">
	  
	  <label for="potentialHostSurname">Potential host surname</label>
	  <input class="form-control" type="text" name="potentialHostSurname">
	  
	  <label for="potentialHostEmail">Potential host email</label>
	  <input class="form-control" type="text" name="potentialHostEmail">
	  
	  <label for="potentialHostTelephoneNumber">Potential host telephone number</label>
	  <input class="form-control" type="text" name="potentialHostTelephoneNumber">
	  
	  <label for="potentialHostCreditCardNumber">Potential host credit card number</label>
      <input class="form-control" type="text" name="potentialHostCreditCardNumber">
      
	  <input type="submit">
	  </fieldset>
	</form>
    
  </div>
  <div class="col-md-8">
  <table>
  <tr>
    <th>Start reservation date</th>
    <th>End reservation date</th>
    <th>Email</th>
    <th>Details</th>
    <th>Update</th>
    <th>Remove</th>
  </tr>
  <%for(Reservation reservation: reservations){%>
  <tr>
    <td><%=reservation.getStartReservation()%></td>
    <td><%=reservation.getEndReservation()%></td>
    <td><%=reservation.getPotentialHostEmail() %></td>
    <td><button class="btn btn-primary" data-toggle="modal" data-target="#reservationDetails<%=reservation.getId()%>">DETAILS</button></td>
    <td><button class="btn btn-primary" data-toggle="modal" data-target="#updateReservation<%=reservation.getId()%>">UPDATE</button></td>
    <td><button class="btn btn-danger" data-toggle="modal" data-target="#removeReservation<%=reservation.getId()%>">REMOVE</button></td>
    <!-------------------------------------------------------- MODAL FOR RESERVATION DETAILS -------------------------------------------------------------------------->
    <td>
    <div class="modal" id="reservationDetails<%=reservation.getId()%>">
      <div class="modal-dialog">
        <div class="modal-content">
         <!-- Modal header -->
	      <div class="modal-header">
			<h4 class="modal-title">RESERVATION DETAILS</h4>
			<button type="button" class="close" data-dismiss="modal">&times;</button>
		  </div>
		  <!-- Modal body -->
		  <div class="modal-body">
		  <div class="row">
		    <div class="col-md-8">Start reservation:</div>
		    <div class="col-md-4"><%=reservation.getStartReservation()%></div>
		  </div>
		  
		  <div class="row">
		    <div class="col-md-8">End reservation:</div>
		    <div class="col-md-4"><%=reservation.getEndReservation()%></div>
		  </div>
		  
		  <div class="row">
		    <div class="col-md-8">Potential host name:</div>
		    <div class="col-md-4"><%=reservation.getPotentialHostName()%></div>
		  </div>
		  
		  <div class="row">
		    <div class="col-md-8">Potential host surname:</div>
		    <div class="col-md-4"><%=reservation.getPotentialHostSurname()%></div>
		  </div>
		  
		  <div class="row">
		    <div class="col-md-8">Potential host email:</div>
		    <div class="col-md-4"><%=reservation.getPotentialHostEmail() %></div>
		  </div>
		  
		  <div class="row">
		    <div class="col-md-8">Potential host telephone number:</div>
		    <div class="col-md-4"><%=reservation.getPotentialHostTelephoneNumber()%></div>
		  </div>
		  
		  <div class="row">
		    <div class="col-md-8">Potential host credit card number:</div>
		    <div class="col-md-4"><%=reservation.getPotentialHostCreditCardNumber() %></div>
		  </div>
		 </div>
		  <!-- Modal footer -->
		  <div class="modal-footer">
			<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		 </div>
    </div>
    </div>
    </div>
    </td>
    <!---------------------------------------------------------------------UPDATE RESERVATION ----------------------------------------------------------------------->
    <td>
       <div class="modal" id="updateReservation<%=reservation.getId()%>">
      <div class="modal-dialog">
        <div class="modal-content">
         <!-- Modal header -->
	      <div class="modal-header">
			<h4 class="modal-title">UPDATE RESERVATION</h4>
			<button type="button" class="close" data-dismiss="modal">&times;</button>
		  </div>
		  <!-- Modal body -->
		  <div class="modal-body">
		    <form action="/reservation/update/<%=reservation.getId()%>/" method="POST">
		      <fieldset class="border p-2">
              <legend class="w-auto">UPDATE RESERVATION</legend>
	          <label for="startReservation">Start reservation date</label>
	          <input class="form-control" type="date" name="startReservation" value="<%=reservation.getStartReservation() %>">
	  
	          <label for="endReservation">End reservation date</label>
	          <input class="form-control" type="date" name="endReservation" value="<%=reservation.getEndReservation()%>">
	  
	          <label for="potentialHostName">Potential host name</label>
	          <input class="form-control" type="text" name="potentialHostName" value="<%=reservation.getPotentialHostName()%>">
	  
         	  <label for="potentialHostSurname">Potential host surname</label>
        	  <input class="form-control" type="text" name="potentialHostSurname" value="<%=reservation.getPotentialHostSurname()%>">
	  
        	  <label for="potentialHostEmail">Potential host email</label>
        	  <input class="form-control" type="text" name="potentialHostEmail" value="<%=reservation.getPotentialHostEmail() %>">
	  
        	  <label for="potentialHostTelephoneNumber">Potential host telephone number</label>
	          <input class="form-control" type="text" name="potentialHostTelephoneNumber" value="<%=reservation.getPotentialHostTelephoneNumber()%>">
	  
	          <label for="potentialHostCreditCardNumber">Potential host credit card number</label>
              <input class="form-control" type="text" name="potentialHostCreditCardNumber" value="<%=reservation.getPotentialHostCreditCardNumber()%>">
      
         	  <input type="submit">
         	  </fieldset>
		    </form> 
		  </div>
		  <!-- Modal footer -->
		  <div class="modal-footer">
			<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		 </div>
    </div>
    </div>
    </div>
    </td>
    <!-----------------------------------------------DELETE RESERVATION ---------------------------------->
    <td>
     <div class="modal" id="removeReservation<%=reservation.getId()%>">
      <div class="modal-dialog">
        <div class="modal-content">
         <!-- Modal header -->
	      <div class="modal-header">
			<h4 class="modal-title">Are you sure do you want to delete this reservation?</h4>
			<button type="button" class="close" data-dismiss="modal">&times;</button>
		  </div>
		  <!-- Modal body -->
		  <div class="modal-body">
		    <form action="/reservation/delete/<%=reservation.getId()%>/" method="GET">
		               	  <input class="btn btn-danger"type="submit" value="YES">
		               	  <button type="button" data-dismiss="modal">NO</button>
		    </form> 
		  </div>
		  <!-- Modal footer -->
		  <div class="modal-footer">
			<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		 </div>
    </div>
    </div>
    </div>
    </td>
  </tr>
  
  <%} %>
  </table>
  </div>
</div>

</body>
</html>