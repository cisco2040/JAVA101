<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/defaultStyles.css"/>"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>
	<div id="headerList">
		<h1 align="center">${ headerTitle }</h1>
	</div>
	<div style="margin-left: 5%; margin-top: 1%;">
		<form action=<c:url value="/user/edit"/> method="post">
			<table>
				<tr>
					<td align="right">Username:</td>
					<td><input type="text" name="frmUsername"
						value="${ user.username }"
						<c:if test="${ param.showDetail != null }">readonly="true"</c:if>
						<c:if test="${ param.Update != null }">readonly="true"</c:if> /></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td><input type="password" name="frmPassword"
						value="${ user.password }"></input></td>
				</tr>
				<tr>
					<td align="right">Confirm Password:</td>
					<td><input type="password" name="frmPasswordConfirm"
						value="${ frmValPasswordConfirm }"></input></td>
				</tr>
				<tr>
					<td align="right">Name:</td>
					<td><input type="text" name="frmName" value="${ user.name }"></input>
					</td>
				</tr>
				<tr>
					<td align="right">User Role:</td>
					<td><select name="frmUserRoleId">
							<option value="" selected>Select Role...</option>
							<c:forEach var="userRole" items="${ userRoles }">
								<option value="${ userRole.userRoleId }"
									<c:if test="${ userRole.userRoleId == user.userRole.userRoleId }">selected</c:if>>${ userRole.description }
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right">Active:</td>
					<td><input type="text" name="frmActive"
						value="${ user.active }"></input></td>
				</tr>
			</table>
			<div style="margin-top: 1%;">
				<button name="${ frmLblSubmitBtn }" type="submit" value="">${ frmLblSubmitBtn }</button>
				<button name="cancel" type="submit" value="">Cancel</button>
				<c:if test="${ param.addNew == null }">
					<c:if test="${ param.Save == null }">
						<button id="deleteBtn" onclick="alertDelete(event)" name="delete"
							type="submit" value="">Delete</button>
					</c:if>
				</c:if>
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