<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->
 <!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/defaultStyles.css"/>"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>
	<div id="headerList">
		<h1 align="center">Advanced User Search</h1>
	</div>
	<div style="margin-left: 5%; margin-top: 1%;">
		<form action=<c:url value="/user/list"/> method="get">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<table>
				<tr>
					<td align="right">User Role:</td>
					<td><select name="userRoleId">
							<option value="" selected>Select Role...</option>
							<c:forEach var="userRole" items="${ userRoles }">
								<option value="${ userRole.userRoleId }">
									${ userRole.description }
								</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<div style="margin-top: 1%;">
				<button name="search" type="submit" value="">Search</button>
				<button name="cancel" type="submit" value="">Cancel</button>
			</div>
		</form>
		<c:forEach var="frmValMsg" items="${ frmValMsgs }">
			<p style="color: red">${ frmValMsg }</p>
		</c:forEach>
	</div>
</body>
<script>
	function alertDelete(event) {
		if (confirm("Do you want to delete this entry?")) {
		} else {
			event.preventDefault();
		}
	}
</script>
</html>