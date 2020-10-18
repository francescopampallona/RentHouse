<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.renthouse.model.House"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="/css/stile.css" rel="stylesheet">

<jsp:include page="imports.jsp"></jsp:include>
<title>HOUSE MANAGEMENT</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%
Iterable<House> houseList = (Iterable<House>)request.getAttribute("houses"); 
%>
<!-----------------------MESSAGES --------------------------->
<%if(request.getAttribute("errorMessage")!=null){ %>
<div class="alert alert-danger" role="alert"><%=request.getAttribute("errorMessage")%></div>
<%}else if(request.getAttribute("successMessage")!=null){ %>
<div class="alert alert-success" role="alert"> <%=request.getAttribute("successMessage")%></div>
<%} %>
<!---------------------------------------------------------------------------- HOUSE REGISTRATION ---------------------------------------------------------------------------->
<button class="btn btn-primary" data-toggle="modal" data-target="#newHouse">REGISTER A NEW HOUSE</button>

<!-- Modal for registration of a new house-->
<div class="modal" id="newHouse">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <!-- Modal header -->
	      <div class="modal-header">
	        <h4 class="modal-title">HOUSE REGISTRATION</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <!-- Modal body -->
	    <div class="modal-body">
	      <form action="/house/insert" method="post">
	      
	         <label for="nation">Nation</label>
	         <input class="form-control" type="text" name="nation" placeholder="Nation">
	         
	         <label for="city">City</label>
	         <input class="form-control" type="text" name="city" placeholder="City">
	         
	         <label for="address">Address</label>
	         <input class="form-control" type="text" name="address" placeholder="Address">
	         
	         <label for="civicNumber">Civic number</label>
	         <input class="form-control" type="number" name="civicNumber" placeholder="Civic number">
	         
	         <label for="maxGuests">Max number of guests</label>
	         <input class="form-control" type="number" name="maxGuests" placeholder="Max number of guests">
	         
	         <label for="lowSeasonPrice">Low season daily price</label>
	         <input class="form-control" type="number" min="0.00" max="1000000.00" step="0.01" name="lowSeasonPrice" placeholder="Low season price">
	        
	         <label for="mediumSeasonPrice">Medium season daily price</label>
	         <input class="form-control" type="number" min="0.00" max="1000000.00" step="0.01" name="mediumSeasonPrice" placeholder="Medium season  price">
	         
	         <label for="highSeasonPrice">High season daily price</label>
	         <input class="form-control" type="number"   min="0.00" max="1000000.00" step="0.01" name="highSeasonPrice" placeholder="High season price">
	      
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
<!---------------------------------------------------------------------------- HOUSE MANAGEMENT ---------------------------------------------------------------------------->

<table>
<tr>
<th>Nation</th>
<th>City</th>
<th>Address</th>
<th>Civic number</th>
<th>Max number of guests</th>
<th></th>
<th></th>
<th></th>
</tr>
<%for(House house: houseList){ %>
 <tr>
 <td><%=house.getNation()%></td>
 <td><%=house.getCity()%></td>
 <td><%=house.getAddress()%></td>
 <td><%=house.getCivicNumber()%></td>
 <td><%=house.getMaxGuests()%></td>
 <td><button data-toggle="modal" data-target="#detailsModal<%=house.getId()%>">DETAILS</button></td>
 <td><button data-toggle="modal" data-target="#updateModal<%=house.getId()%>">UPDATE</button></td>
 <% if(house.getAnnouncement()==null){%>
<td><button class="btn btn-primary" data-toggle="modal" data-target="#newRentAnnouncement<%=house.getId()%>">NEW RENT ANNOUNCEMENT</button></td>
<%} 
 else{
%>
<td><button class="btn btn-primary" data-toggle="modal" data-target="#manageRentAnnouncement<%=house.getId()%>">MANAGE RENT ANNOUNCEMENT</button></td>
<%} %>
 
 <!------------------------------------------------------------------------------------------ MODAL FOR DETAILS ------------------------------------------------------------------------------------------>
 <td>
<div class="modal" id="detailsModal<%=house.getId()%>">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <!-- Modal header -->
	      <div class="modal-header">
	        <h4 class="modal-title">House details</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <!-- Modal body -->
	    <div class="modal-body">
	       <div class="row">
	         <div class="col-md-6">Nation: </div>
	         <div class="col-md-6"><%=house.getNation()%></div>
	       </div>
	       <div class="row">
	         <div class="col-md-6">City: </div>
	         <div class="col-md-6"><%=house.getCity()%></div>
	       </div>
	       <div class="row">
	         <div class="col-md-6">Address: </div>
	         <div class="col-md-6"><%=house.getAddress()%></div>
	       </div>
	       <div class="row">
	         <div class="col-md-6">Civic number: </div>
	         <div class="col-md-6"><%=house.getCivicNumber()%></div>
	       </div>
	       <div class="row">
	         <div class="col-md-6">Max number of guests: </div>
	         <div class="col-md-6"><%=house.getCivicNumber()%></div>
	       </div>
	       <div class="row">
	         <div class="col-md-6">Low season daily price: </div>
	         <div class="col-md-6"><%=house.getLowSeasonPrice()%></div>
	       </div>

	       <div class="row">
	         <div class="col-md-6">Medium season daily price: </div>
	         <div class="col-md-6"><%=house.getMediumSeasonPrice()%></div>
	       </div>
	     
	       <div class="row">
	         <div class="col-md-6">High season daily price: </div>
	         <div class="col-md-6"><%=house.getHighSeasonPrice()%></div>
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
 <!--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
 <!------------------------------------------------------------------------------------------ MODAL FOR UPDATE ------------------------------------------------------------------------------------------>
 <!-- Modal for update -->
 <td>
<div class="modal" id="updateModal<%=house.getId()%>">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <!-- Modal header -->
	      <div class="modal-header">
	        <h4 class="modal-title">HOUSE UPDATE</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <!-- Modal body -->
	    <div class="modal-body">
	      <form action="/house/update/<%=house.getId()%>" method="post">
	      
	         <label for="nation">Nation</label>
	         <input class="form-control" type="text" name="nation" placeholder="Nation" value="<%=house.getNation()%>">
	         
	         <label for="city">City</label>
	         <input class="form-control" type="text" name="city" placeholder="City" value="<%=house.getCity()%>">
	         
	         <label for="address">Address</label>
	         <input class="form-control" type="text" name="address" placeholder="Address" value="<%=house.getAddress()%>">
	         
	         <label for="civicNumber">Civic number</label>
	         <input class="form-control" type="number" name="civicNumber" placeholder="Civic number" value="<%=house.getCivicNumber()%>">
	         
	         <label for="maxGuests">Max number of guests</label>
	         <input class="form-control" type="number" name="maxGuests" placeholder="Max number of guests" value="<%=house.getMaxGuests()%>">
	         
	         <label for="lowSeasonPrice">Low season daily price</label>
	         <input class="form-control" type="number" min="0.00" max="1000000.00" step="0.01" name="lowSeasonPrice" placeholder="Low season price" value="<%=house.getLowSeasonPrice()%>">
	         
	         
	         <label for="mediumSeasonPrice">Medium season daily price</label>
	         <input class="form-control" type="number" min="0.00" max="1000000.00" step="0.01" name="mediumSeasonPrice" placeholder="Medium season  price" value="<%=house.getMediumSeasonPrice()%>">
	         
	        
	         <label for="highSeasonPrice">High season daily price</label>
	         <input class="form-control" type="number"   min="0.00" max="1000000.00" step="0.01" name="highSeasonPrice" placeholder="High season price" value="<%=house.getHighSeasonPrice()%>">
	         
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
	</td>
<!--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
<!------------------------------------------------------------------------------------------ NEW RENT ANNOUNCEMENT ------------------------------------------------------------------------------------------>

<td>
<div class="modal" id="newRentAnnouncement<%=house.getId()%>">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <!-- Modal header -->
	      <div class="modal-header">
	        <h4 class="modal-title">NEW RENT ANNOUNCEMENT</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <!-- Modal body -->
	    <div class="modal-body">
	      <form action="/house/<%=house.getId()%>/rentAnnouncement/insert" method="post">
	        <input class="form-control" type="text" name="description" placeholder="Description">
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
</td>
<!--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
<!------------------------------------------------------------------------------------------ MANAGE RENT ANNOUNCEMENT ------------------------------------------------------------------------------------------>
<%if(house.getAnnouncement()!=null){%>
<td>
  <div class="modal" id="manageRentAnnouncement<%=house.getId()%>">
  <div class="modal-dialog">
	    <div class="modal-content">
	      <!-- Modal header -->
	      <div class="modal-header">
	        <h4 class="modal-title">MANAGE RENT ANNOUNCEMENT</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <!-- Modal body -->
	    <div class="modal-body">
	      <form action="/rentAnnouncement/update/<%=house.getAnnouncement().getId()%>" method="post">
	         <input class="form-control" type="text" name="description" placeholder="Description" value="<%=house.getAnnouncement().getDescription()%>">
	         <input type="submit" class="btn btn-primary" value="Edit">
	      </form>
	      <form action="/rentAnnouncement/delete/<%=house.getAnnouncement().getId()%>" method="post">
	      <input type="submit" class="btn btn-danger" value="Delete announcement">
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
<%} %>
<%}  %>
 </tr>
</table>

</body>
</html>