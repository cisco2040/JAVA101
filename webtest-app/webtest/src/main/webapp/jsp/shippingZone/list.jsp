<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="com.softtek.javaweb.service.ShippingZoneService"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/webtest/css/defaultStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shipping Zones</title>
</head>
<jsp:useBean id="sz" class="com.softtek.javaweb.service.ShippingZoneService"/>
<% 
pageContext.setAttribute("shippingZones", sz.getList());
%>
<body>
	<div id="headerList">
		<h1 align="center">Shipping Zone List</h1>
	</div>
	<div style="margin-left:5%;margin-bottom: 1%;">
		<form action="/webtest/index.jsp">
			<button name="home" type="submit" >Home</button>
		</form>
	</div>
	<div id="resultsSection">	
	<table id="resultsTable">
		<tr>
			<th align="left">Shipping Zone ID</th>
			<th align="left">Description</th>
			<th align="left">Delivery Time</th>
			<th align="left">Shipping Cost</th>
		</tr>
		<c:forEach var="shippingZone" items="${ shippingZones }">
			<tr>
				<td>${ shippingZone.shippingZoneId}</td>
				<td>${ shippingZone.description }</td>
				<td>${ shippingZone.deliveryTime }</td>
				<td>${ shippingZone.shippingCost }</td>
			</tr>
		</c:forEach>		
	</table>
	</div>
</body>
</html>