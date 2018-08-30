<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->
 <!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/defaultStyles.css"/> "/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>	
	<div id="headerList">
		<h1 align="center">User List</h1>
	</div>
	
	<div style="margin-left:5%;margin-bottom: 1%;">
		<form action=<c:url value="/user/edit"/> method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button name="home" type="submit" >Home</button>
			<button name="addNew" type="submit" >Add New..</button>
		</form>
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
				<td><a href=<c:url value="/user/edit?showDetail&id=${ user.username }"/>>${ user.username }</a></td>
				<td>********</td>
				<td>${ user.name }</td>
				<td>${ user.userRole.description }</td>
				<td>${ user.active }</td>
			</tr>
		</c:forEach>		
	</table>
	</div>
</body>
</html>