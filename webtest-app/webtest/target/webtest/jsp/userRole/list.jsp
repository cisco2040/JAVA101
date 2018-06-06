<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="com.softtek.javaweb.service.UserRoleService"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Roles</title>
</head>
<jsp:useBean id="ur" class="com.softtek.javaweb.service.UserRoleService"/>
<% 
pageContext.setAttribute("userRoles", ur.getList());
%>    
<body>	
	<table>
		<tr>
			<th>User Role ID</th>
			<th>Description</th>
		</tr>
		<c:forEach var="userRole" items="${ userRoles }">
			<tr>
				<td>${ userRole.userRoleId }</td>
				<td>${ userRole.description }</td>
			</tr>
		</c:forEach>		
	</table>
</body>
</html>