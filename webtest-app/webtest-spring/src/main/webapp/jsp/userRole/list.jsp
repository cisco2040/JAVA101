<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->
 <!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/defaultStyles.css"/> "/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Roles</title>
</head>
<body>
	<div id="headerList">
		<h1 align="center">User Role List</h1>
	</div>
	<div style="margin-left:5%;margin-bottom: 1%;">
		<form action=<c:url value="/cart/edit"/> method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button name="home" type="submit" >Home</button>
		</form>
	</div>
	<div id="resultsSection">	
	<table id="resultsTable">
		<tr>
			<th align="left">User Role ID</th>
			<th align="left">Description</th>
		</tr>
		<c:forEach var="userRole" items="${ userRoles }">
			<tr>
				<td>${ userRole.userRoleId }</td>
				<td>${ userRole.description }</td>
			</tr>
		</c:forEach>		
	</table>
	</div>
</body>
</html>