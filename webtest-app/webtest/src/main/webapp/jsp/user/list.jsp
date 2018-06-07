<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="com.softtek.javaweb.service.UserService" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="u" class="com.softtek.javaweb.service.UserService"/>
<% 
pageContext.setAttribute("users", u.getList());
pageContext.setAttribute("homePath", pageContext.get);

%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/webtest/css/defaultStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>	
	<table id="results">
		<tr>
			<th align="left">User Name</th>
			<th align="left">Password</th>
			<th align="left">Name</th>
			<th align="left">User Role</th>
			<th align="left">Active</th>
		</tr>
		<c:forEach var="user" items="${ users }">
			<tr>
				<td>${ user.username }</td>
				<td>${ user.password }</td>
				<td>${ user.name }</td>
				<td>${ user.userRole.description }</td>
				<td>${ user.active }</td>
			</tr>
		</c:forEach>		
	</table>
</body>
</html>