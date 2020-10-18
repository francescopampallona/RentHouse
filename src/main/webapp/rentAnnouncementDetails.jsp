<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.renthouse.model.RentAnnouncement"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RENT ANNOUNCEMENT DETAILS</title>
</head>
<body>
<%RentAnnouncement announcement = (RentAnnouncement)request.getAttribute("announcement"); %>
  <form action="/">
  <input type="submit" value="BACK">
  </form>
  <div class="card-body">
  <div class="row">
    <div class="col-md-12">
    <h1><%=announcement.getReferenceHouse().getNation()%>   <%=announcement.getReferenceHouse().getCity()%> <%=announcement.getReferenceHouse().getAddress()%> <%=announcement.getReferenceHouse().getCivicNumber()%></h1>
    </div>
   </div>
   <div class="row">
     <div class="col-md-6">
       <h2>Max. number of guests: <%=announcement.getReferenceHouse().getMaxGuests()%></h2>
       <h2>Low season price: <%=announcement.getReferenceHouse().getLowSeasonPrice()%> € per day</h2>
       <h2>Medium season price: <%=announcement.getReferenceHouse().getMediumSeasonPrice()%> € per day</h2>
       <h2>High season price: <%=announcement.getReferenceHouse().getHighSeasonPrice()%> € per day</h2>
       <h2>Discount of 10 % for rents over 3 days</h2> 
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
      <h1>From <%=startDate%> to <%=endDate%> the total price calculated is <%=totalPrice%> € for a total of <%=totalDays%> days</h1>
     
    <%} %>
    </div>
    </div>

</div>
</body>
</html>