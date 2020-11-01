<%@page import="com.fasterxml.jackson.databind.ser.std.IterableSerializer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.renthouse.model.RentAnnouncement" 
                                                                import="java.util.ArrayList" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="imports.jsp"></jsp:include>
<meta charset="UTF-8">
<title>RentHouse</title>
</head>
<body>
<!-----------------------MESSAGES --------------------------->
<%if(request.getAttribute("errorMessage")!=null){ %>
<div class="alert alert-danger" role="alert"><%=request.getAttribute("errorMessage")%></div>
<%}else if(request.getAttribute("successMessage")!=null){ %>
<div class="alert alert-success" role="alert"> <%=request.getAttribute("successMessage")%></div>
<%} %>
<form action="/"  style="margin: 10px">
<input class="form-control" type="text" name="nation" placeholder="Search by nation" aria-label="Search">
<input class="form-control" type="text" name="city" placeholder="Search by city" aria-label="Search">
<input class="form-control" type="number" name="maxNumberOfGuests" placeholder="Search by max number of guests" aria-label="Search">
<input type="submit" value="Search by nation and city">
</form>
<form>

</form>
<%
ArrayList<RentAnnouncement> announcements =(ArrayList<RentAnnouncement>) request.getAttribute("announcements");
int numberOfElements = (int)request.getAttribute("numberOfElements");
%>

<p style="margin: 15px"><b><%=numberOfElements%> results</b></p>


<%for(RentAnnouncement announcement: announcements){ %>
<div class="card" style="width: 100%; margin: 10px; border: 1px solid black">
  <div class="card-body">
  <div class="row">
    <div class="col-md-12">
    <h1 class="card-title"><%=announcement.getReferenceHouse().getNation()%>   <%=announcement.getReferenceHouse().getCity()%> <%=announcement.getReferenceHouse().getAddress()%> <%=announcement.getReferenceHouse().getCivicNumber()%></h1>
    </div>
   </div>
   <div class="row">
     <div class="col-md-12">
       <h5> Max. number of guests: <%=announcement.getReferenceHouse().getMaxGuests()%></h5> 
       <h5>Low season price: <%=announcement.getReferenceHouse().getLowSeasonPrice()%> € per day</h5>
       <h5>Medium season price: <%=announcement.getReferenceHouse().getMediumSeasonPrice()%> € per day</h5>
       <h5>High season price: <%=announcement.getReferenceHouse().getHighSeasonPrice()%> € per day</h5>
       <h5>Discount of 10 % for rents over 3 days</h5> 
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
    <h2>Calculate price:</h2>
    <form action="/calculatePrice/<%=announcement.getId()%>" method="POST">
      <label for="startDate">Start date</label>
      <input type="date" name="startDate">
      <label for="endDate">End date</label>
      <input type="date"  name="endDate">
      <input type="submit" value="CALCULATE PRICE">
    </form>
    </div>
   </div>
   <div class="row">
    <div class="col-md-12">
    <%
    String totalPriceKey = "totalPrice"+announcement.getId();
    String totalDaysKey = "totalDays"+announcement.getId();
    if(request.getAttribute(totalPriceKey)!=null && request.getAttribute(totalDaysKey)!=null && request.getAttribute("startDate")!=null && request.getAttribute("endDate")!=null){ 
    	float totalPrice = (float)request.getAttribute(totalPriceKey);
    	long totalDays = (long) request.getAttribute(totalDaysKey);
    	String startDate =(String) request.getAttribute("startDate");
    	String endDate = (String)request.getAttribute("endDate");
    %>
      <p>From <%=startDate%> to <%=endDate%> the total price calculated is <%=totalPrice%> € for a total of <%=totalDays%> days</p>
     
    <%} %>
    </div>
    </div>
  </div>
</div>
  <%} %>

</body>
</html>