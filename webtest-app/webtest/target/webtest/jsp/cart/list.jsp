<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="com.softtek.javaweb.service.CartService" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="c" class="com.softtek.javaweb.service.CartService"/>
<% 
pageContext.setAttribute("carts", c.getList());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/webtest/css/defaultStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carts</title>
</head>
<body>	
	<div id="headerList">
		<h1 align="center">Cart List</h1>
	</div>
	<div id="resultsSection">
	<table id="resultsTable">
		<tr>
			<th align="left">Cart ID</th>
			<th align="left">Line Amount</th>
			<th align="left">Shipping Amount</th>
			<th align="left">Cart Amomunt</th>
			<th align="left">Ship To Address</th>
			<th align="left">Status</th>
			<th align="left">Created By</th>
			<th align="left">Created On</th>
			<th align="left">Updated By</th>
			<th align="left">Updated On</th>
		</tr>
		<c:forEach var="cart" items="${ carts }">
			<tr>
				<td>${ cart.cartId }</td>
				<td>${ cart.linesAmount }</td>
				<td>${ cart.shippingAmount }</td>
				<td>${ cart.cartAmount }</td>
				<td>${ cart.ShipTo.address }, ${ cart.ShipTo.City.description }, ${ cart.ShipTo.City.State.description }</td>
				<td>${ cart.Status.description}</td>
				<td>${ cart.createUser }</td>
				<td>${ cart.createDate }</td>
				<td>${ cart.updateUser }</td>
				<td>${ cart.updateDate }</td>
			</tr>
		</c:forEach>		
	</table>
	</div>
</body>
</html>