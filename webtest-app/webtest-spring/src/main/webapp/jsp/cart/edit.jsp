<%@page import="java.text.ParseException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/defaultStyles.css"/> "/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carts</title>
</head>
<body>	
	<div id="headerList">
		<h1 align="center">${ headerTitle }</h1>
	</div>
	<div style="margin-left:5%;margin-top: 1%;">
		<form action="<c:url value="/cart/edit"/>" method="get">
			<input type="hidden" name="frmCartId" value="${ cart.cartId }"/><br>		
			<c:if test="${ cart.cartId != null }">
				Cart ID: ${ cart.cartId }<br>
			</c:if>
			Line Amount: <input type="number" name="frmLinesAmount" value="${ cart.linesAmount }"></input><br>
			<c:if test="${ cart.cartId != null }">
				<input type="hidden" name="frmShippingAmount" value="${ cart.shippingAmount }"/>		
				Shipping Amount: ${ cart.shippingAmount }<br>
				<input type="hidden" name="frmCartAmount" value="${ cart.cartAmount }"/>	
				Cart Amount: ${ cart.cartAmount }<br>
			</c:if>
			Ship-To Address: 
			<select name="frmShipToId">
				<option value="" selected>Select Address...</option>
				<c:forEach var="shipTo" items="${ shipTos }">
					<option value="${ shipTo.shipToId }" <c:if test="${ shipTo.shipToId == cart.shipTo.shipToId }">selected</c:if>>${ shipTo.address }, ${ shipTo.city.description }, ${ shipTo.city.state.description }</option>
				</c:forEach>
			</select><br>
			Status: 
			<select name="frmStatusId">
				<option value="" selected>Select Status...</option>
				<c:forEach var="status" items="${ statuses }">
					<c:if test = "${ status.statusType == 'CART' }">
						<option value="${ status.statusId }" <c:if test="${ status.statusId == cart.status.statusId }">selected</c:if>>${ status.description }</option>
					</c:if>
				</c:forEach>
			</select><br>
			<c:if test="${ cart.cartId == null }">
				Create User: 
				<select name="frmCreateUser">
					<option value="" selected>Select Username...</option>
					<c:forEach var="user" items="${ users }">
						<option value="${ user.username }" <c:if test="${ user.username == cart.createUser }">selected</c:if>>${ user.username }</option>
					</c:forEach>
				</select><br>
				Create Date: ${ cart.createDate }
			</c:if>
			<c:if test="${ cart.cartId != null }">
				<input type="hidden" name="frmCreateUser" value="${ cart.createUser }"/>		
				Create User: ${ cart.createUser }<br>
				<input type="hidden" name="frmCreateDate" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ cart.createDate }"/>"/>		
				Create Date: <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ cart.createDate }"/><br>
				Update User: 
				<select name="frmUpdateUser">
					<option value="" selected>Select Username...</option>
					<c:forEach var="user" items="${ users }">
						<option value="${ user.username }" <c:if test="${ user.username == cart.updateUser }">selected</c:if>>${ user.username }</option>
					</c:forEach>
				</select><br>
			</c:if>
			<div style="margin-top: 1%;">
				<button name="${ frmLblSubmitBtn }" type="submit" value="" >${ frmLblSubmitBtn }</button>
				<button name="cancel" type="submit" value="" >Cancel</button>
				<c:if test="${ param.addNew == null }">
					<c:if test="${ param.Save == null }">
						<button id="deleteBtn" onclick="alertDelete()" name="delete" type="submit" value="" >Delete</button>
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