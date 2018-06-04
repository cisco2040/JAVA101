<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*"%>
<%@ page import="com.softtek.academy.servlet.domain.model.ShippingZone"%>
<%@ page import="com.softtek.academy.servlet.repository.ShippingZoneRepository"%>
<%@ page import="com.softtek.academy.servlet.repository.DatabaseConnection"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>States</title>
</head>
<body>
<jsp:useBean id="sz" class="com.softtek.academy.servlet.repository.ShippingZoneRepository"/>
<% 
List<ShippingZone> shippingZones = sz.list();
pageContext.setAttribute("shippingZones", shippingZones);
%>    
	<h3>New State</h3>
	
	<form action="/servlet-app/states" method="post">
		id: <input type="text" name="id"><br>
		Description: <input type="text" name="description"><br>
		Shipping Zone: 
		<select name="shipping_zone_id">
			<option value="0" selected>- Select -</option>
			<c:forEach var="sz" items="${ shippingZones }">
				<option value="${ sz.shippingZoneId }">${ sz.description }</option>
			</c:forEach>
		</select>
		
		<input type="submit" name="save" value="Save">
	</form>
	
	<br>
	<a href="/servlet-app/index.html">Return to Index</a>
</body>
</html>