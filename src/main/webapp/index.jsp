<%@page
	import="com.fasterxml.jackson.databind.ser.std.IterableSerializer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.renthouse.model.RentAnnouncement"
	import="java.util.ArrayList" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="imports.jsp"></jsp:include>
<meta charset="UTF-8">
<title>RentHouse</title>
</head>
<body>
	
	<form action="/search" style="margin: 10px">
		<input class="form-control" type="text" name="nation" placeholder="Search by nation" aria-label="Search"> 
		<input class="form-control" type="text" name="city" placeholder="Search by city" aria-label="Search"> 
		<input class="form-control" type="number" name="maxNumberOfGuests" placeholder="Search by max number of guests" aria-label="Search">
		<input class="form-control" type="text" name="address" placeholder="Address" aria-label="Search">
		<input class="form-control" type="number" name="civicNumber" placeholder="Civic number" aria-label="Search">
		<input type="submit" value="Search by nation and city">
	</form>
	
	<%
		Iterable<RentAnnouncement> announcements = (Iterable<RentAnnouncement>) request.getAttribute("announcements");
	int numberOfElements = (int) request.getAttribute("numberOfElements");
	%>

	<p style="margin: 15px">
		<b><%=numberOfElements%> results</b>
	</p>


	<%
		for (RentAnnouncement announcement : announcements) {
	%>
	<div class="card"
		style="width: 100%; margin: 10px; border: 1px solid black">
		<div class="card-body">
			<div class="row">
				<div class="col-md-12">
					<h1 class="card-title"><%=announcement.getReferenceHouse().getNation()%>
						<%=announcement.getReferenceHouse().getCity()%>
						<%=announcement.getReferenceHouse().getAddress()%>
						<%=announcement.getReferenceHouse().getCivicNumber()%></h1>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h4><b>
					For info and reservation call <%=announcement.getReferenceHouse().getOwner().getTelephone()%> or contact <%=announcement.getReferenceHouse().getOwner().getEmail() %>
					</b></h4>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h5>
						Max. number of guests:
						<%=announcement.getReferenceHouse().getMaxGuests()%></h5>
					<h5>
						Low season price:
						<%=announcement.getReferenceHouse().getLowSeasonPrice()%>
						€ per day
					</h5>
					<h5>
						Medium season price:
						<%=announcement.getReferenceHouse().getMediumSeasonPrice()%>
						€ per day
					</h5>
					<h5>
						High season price:
						<%=announcement.getReferenceHouse().getHighSeasonPrice()%>
						€ per day
					</h5>
					<%if(announcement.getDaysForDiscountValidity()>0){ %>
				    <h3>Discount of <%=announcement.getDiscount()*100 %> % for rents over <%=announcement.getDaysForDiscountValidity()%> days</h3>
				    <%} else{%>
				    <h3>Discount of <%=announcement.getDiscount()*100 %> % </h3>
				    <%} %>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h2>Description</h2>
					<p><%=announcement.getDescription()%></p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h2>Details:</h2>
					<form action="/announcement/<%=announcement.getId()%>" method="GET">
						<input class="btn btn-success" type="submit" value="Details">
					</form>
				</div>
			</div>
			
		</div>
	</div>
	<%
		}
	%>

</body>
</html>