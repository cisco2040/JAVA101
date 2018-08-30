<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->
 <!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/defaultStyles.css"/> " />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carts</title>
</head>
<body>
	<div id="headerList">
		<h1 align="center">${ headerTitle }</h1>
	</div>
	<div style="margin-left: 5%; margin-top: 1%;">
		<form action="<c:url value="/cart/edit"/>" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
			<input type="hidden" name="cartId" value="${ cart.cartId }" />
			<input type="hidden" name="shippingAmount" value="${ cart.shippingAmount }" /> 
			<input type="hidden" name="cartAmount" value="${ cart.cartAmount }" /> 
			<input type="hidden" name="createDate" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ cart.createDate }"/>" />
			<table>
				<c:if test="${ cart.cartId != null }">
					<tr>
						<td align="right">Cart ID:</td>
						<td>${ cart.cartId }</td>
					</tr>
				</c:if>
				<tr>
					<td align="right">Line Amount:</td>
					<td><input type="number" name="linesAmount"
						value="${ cart.linesAmount }"></input></td>
				</tr>
				<c:if test="${ cart.cartId != null }">
					<tr>
						<td align="right">Shipping Amount:</td>
						<td>${ cart.shippingAmount }</td>
					</tr>
					<tr>
						<td align="right">Cart Amount:</td>
						<td>${ cart.cartAmount }</td>
					</tr>
				</c:if>
				<tr>
					<td align="right">Ship-To Address:</td>
					<td><select name="shipToId">
							<option value="" selected>Select Address...</option>
							<c:forEach var="shipTo" items="${ shipTos }">
								<option value="${ shipTo.shipToId }"
									<c:if test="${ shipTo.shipToId == cart.shipTo.shipToId }">selected</c:if>>${ shipTo.address },
									${ shipTo.city.description }, ${ shipTo.city.state.description }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right">Status:</td>
					<td><select name="statusId">
							<option value="" selected>Select Status...</option>
							<c:forEach var="status" items="${ statuses }">
								<c:if test="${ status.statusType == 'CART' }">
									<option value="${ status.statusId }"
										<c:if test="${ status.statusId == cart.status.statusId }">selected</c:if>>${ status.description }</option>
								</c:if>
							</c:forEach>
					</select></td>
				</tr>
				<c:if test="${ cart.cartId == null }">
					<tr>
						<td align="right">Create User:</td>
						<td><select name="createUser">
								<option value="" selected>Select Username...</option>
								<c:forEach var="user" items="${ users }">
									<option value="${ user.username }"
										<c:if test="${ user.username == cart.createUser }">selected</c:if>>${ user.username }</option>
								</c:forEach>
						</select></td>
					</tr>
				</c:if>
				<c:if test="${ cart.cartId != null }">
					<input type="hidden" name="createUser" value="${ cart.createUser }" />					
					<tr>
						<td align="right">Create User:</td>
						<td>${ cart.createUser }</td>
					</tr>
					<tr>
						<td align="right">Create Date:</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
								value="${ cart.createDate }" /></td>
					</tr>
					<tr>
						<td align="right">Update User:</td>
						<td><select name="updateUser">
								<option value="" selected>Select Username...</option>
								<c:forEach var="user" items="${ users }">
									<option value="${ user.username }"
										<c:if test="${ user.username == cart.updateUser }">selected</c:if>>${ user.username }</option>
								</c:forEach>
						</select></td>
					</tr>
				</c:if>
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
<script type="application/javascript">
function alertDelete(event) {
    if (confirm("Do you want to delete this entry?")) {
    } else {
    	event.preventDefault();
    }
}
</script>
</html>