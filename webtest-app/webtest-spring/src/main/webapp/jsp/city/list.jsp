<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->
 <!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/defaultStyles.css"/> "/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cities</title>
</head>
<body>
	<div id="headerList">
		<h1 align="center">City List</h1>
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
			<th align="left">City ID</th>
			<th align="left">Description</th>
			<th align="left">State</th>
		</tr>
		<c:forEach var="city" items="${ cities }">
			<tr>
				<td>${ city.cityId }</td>
				<td>${ city.description }</td>
				<td>${ city.state.description }</td>
			</tr>
		</c:forEach>		
	</table>
	</div>
</body>
</html>