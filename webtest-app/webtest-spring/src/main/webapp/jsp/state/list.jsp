<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="com.softtek.javaweb.service.StateService" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="s" class="com.softtek.javaweb.service.StateService"/>
<% 
pageContext.setAttribute("states", s.getList());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/webtest-spring/css/defaultStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>States</title>
</head>
<body>
	<div id="headerList">
		<h1 align="center">State List</h1>
	</div>
	<div style="margin-left:5%;margin-bottom: 1%;">
		<form action="/webtest-spring/index.jsp">
			<button name="home" type="submit" >Home</button>
		</form>
	</div>
	<div id="resultsSection">
	<table id="resultsTable">
		<tr>
			<th align="left">State ID</th>
			<th align="left">Description</th>
			<th align="left">Shipping Zone</th>
		</tr>
		<c:forEach var="state" items="${ states }">
			<tr>
				<td>${ state.stateId }</td>
				<td>${ state.description }</td>
				<td>${ state.shippingZone.description }</td>
			</tr>
		</c:forEach>		
	</table>
	</div>
</body>
</html>