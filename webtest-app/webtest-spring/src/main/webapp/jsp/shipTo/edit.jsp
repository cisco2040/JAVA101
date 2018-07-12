<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/defaultStyles.css"/> " />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ship To Addresses</title>
</head>
<body>
	<div id="headerList">
		<h1 align="center">${ headerTitle }</h1>
	</div>
	<div style="margin-left: 5%; margin-top: 1%;">
		<form action=<c:url value="/shipTo/edit"/> method="post">
			<table>
				<c:if test="${ shipTo.shipToId != null }">
					<tr>
						<td align="right">Ship-To ID:</td>
						<td><input type="text" name="frmShipToId"
							value="${ shipTo.shipToId }" readonly /></td>
					<tr>
				</c:if>
				<tr>
					<td align="right">Username:</td>
					<td><select name="frmUserId">
							<option value="" selected>Select Username...</option>
							<c:forEach var="user" items="${ users }">
								<option value="${ user.username }"
									<c:if test="${ user.username == shipTo.user.username }">selected</c:if>>${ user.username }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right">Name:</td>
					<td><input type="text" name="frmName" value="${ shipTo.name }"></input>
					</td>
				</tr>
				<tr>
					<td align="right">Address:</td>
					<td><input type="text" name="frmAddress"
						value="${ shipTo.address }"></input></td>
				</tr>
				<tr>
					<td align="right">City:</td>
					<td><select name="frmCityId">
							<option value="" selected>Select City...</option>
							<c:forEach var="city" items="${ cities }">
								<option value="${ city.cityId }"
									<c:if test="${ city.cityId == shipTo.city.cityId }">selected</c:if>>${ city.description },
									${ city.state.description }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right">Zip Code:</td>
					<td><input type="number" name="frmZipcode"
						value="${ shipTo.zipcode }"></input></td>
				</tr>
				<tr>
					<td align="right">Phone:</td>
					<td><input type="text" name="frmPhone"
						value="${ shipTo.phone }"></input></td>
				</tr>
				<tr>
					<td align="right">Email:</td>
					<td><input type="email" name="frmEmail"
						value="${ shipTo.email }"></input></td>
				</tr>
			</table>
			<div style="margin-top: 1%;">
				<button name="${ frmLblSubmitBtn }" type="submit" value="">${ frmLblSubmitBtn }</button>
				<button name="cancel" type="submit" value="">Cancel</button>
				<c:if test="${ param.addNew == null }">
					<c:if test="${ param.Save == null }">
						<button id="deleteBtn" onclick="alertDelete()" name="delete"
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
function alertDelete() {
    if (confirm("Do you want to delete this entry?")) {
    } else {
    	event.preventDefault();
    }
}
</script>
</html>