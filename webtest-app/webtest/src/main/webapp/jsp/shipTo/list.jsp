<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="com.softtek.javaweb.service.ShipToService" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="st" class="com.softtek.javaweb.service.ShipToService"/>
<% 
pageContext.setAttribute("shipTos", st.getList());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/webtest/css/defaultStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ship-To Addresses</title>
</head>
<body>	
	<div id="headerList">
		<h1 align="center">Ship-To Address List</h1>
	</div>
	<div id="resultsSection">
	<table id="resultsTable">
		<tr>
			<th align="left">Ship To ID</th>
			<th align="left">User</th>
			<th align="left">Name</th>
			<th align="left">Address</th>
			<th align="left">City/State</th>
			<th align="left">Zip Code</th>
			<th align="left">Phone #</th>
			<th align="left">email Address</th>
		</tr>
		<c:forEach var="shipTo" items="${ shipTos }">
			<tr>
				<td>${ shipTo.shipToId}</td>
				<td>${ shipTo.user.username }</td>
				<td>${ shipTo.name }</td>
				<td>${ shipTo.address }</td>
				<td>${ shipTo.city.description }, ${ shipTo.city.state.description }</td>
				<td>${ shipTo.zipcode }</td>
				<td>${ shipTo.phone }</td>
				<td>${ shipTo.email }</td>
			</tr>
		</c:forEach>		
	</table>
	</div>
</body>
</html>