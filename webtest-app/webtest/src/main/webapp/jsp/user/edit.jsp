<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.softtek.javaweb.domain.model.User" %>
<%@ page import="com.softtek.javaweb.domain.model.UserRole" %>
<%@ page import="com.softtek.javaweb.service.UserService" %>
<%@ page import="com.softtek.javaweb.service.UserRoleService" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="u" class="com.softtek.javaweb.service.UserService"/>
<jsp:useBean id="ur" class="com.softtek.javaweb.service.UserRoleService"/>
<% 
pageContext.setAttribute("users", u.getList());
pageContext.setAttribute("userRoles", ur.getList());
%>

<%
String headerTitle = "Edit User";

String frmValUsername = "";
String frmValPassword = "";
String frmValName = "";
String frmValUserRole = "";
String frmValActive = "";

if ( request.getParameter("update") != null ) {
	User user = u.getOne(request.getParameter("username"));
	List<UserRole> userRoles = ur.getList();			
	frmValUsername = user.getUsername();
	frmValPassword = user.getPassword();
	frmValName = user.getName();
	frmValUserRole = user.getUserRole().getUserRoleId();
	pageContext.setAttribute("frmValUserRole", frmValUserRole);
	frmValActive = user.getActive();		
}

if (request.getParameter("addNew") != null) {
	headerTitle = "Add New User";
}

if (request.getParameter("cancel") != null) {
	response.sendRedirect("/webtest/jsp/user/list.jsp");
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/webtest/css/defaultStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>	
	<div id="headerList">
		<h1 align="center"><%= headerTitle %></h1>
	</div>
	
	<div id="resultsSection">
	<table id="resultsTable">
		<tr>
			<th align="left">User Name</th>
			<th align="left">Password</th>
			<th align="left">Name</th>
			<th align="left">User Role</th>
			<th align="left">Active</th>
		</tr>
		<c:forEach var="user" items="${ users }">
			<tr>
				<td><a href="/webtest/jsp/user/edit.jsp?update&username=${ user.username }">${ user.username }</a></td>
				<td>********</td>
				<td>${ user.name }</td>
				<td>${ user.userRole.description }</td>
				<td>${ user.active }</td>
			</tr>
		</c:forEach>		
	</table>
	</div>
	<div style="margin-left:5%;margin-top: 1%;">
		<form action="/webtest/jsp/user/edit.jsp" method="post">
			Username: <input type="text" name="frmUsername" value="<%= frmValUsername %>" <c:if test="${ param.update != null }">disabled</c:if>></input><br>
			Password: <input type="password" name="frmPassword" value="<%= frmValPassword %>"></input><br>
			Name    : <input type="text" name="frmName" value="<%= frmValName %>"></input><br>
			User Role: 
			<select name="frmUserRoleId">
				<c:forEach var="userRole" items="${ userRoles }">
					<option value="${ userRole.userRoleId }" <c:if test="${ userRole.userRoleId == frmValUserRole }">selected</c:if>>${ userRole.description }</option>
				</c:forEach>
			</select><br>
			Active  : <input type="text" name="frmActive" value="<%= frmValActive %>"></input><br>
			
			<div style="margin-top: 1%;">
				<button name="save" type="submit" value="save" >Save</button>
				<button name="cancel" type="submit" value="cancel" >Cancel</button>
				<button name="delete" type="submit" value="delete" >Delete</button>
			</div>
		</form>
	</div>
</body>
</html>