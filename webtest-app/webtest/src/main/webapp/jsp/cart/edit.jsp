<%@page import="java.text.ParseException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp" %>

<%@ page import="com.softtek.javaweb.domain.model.Cart" %>
<%@ page import="com.softtek.javaweb.domain.model.Status" %>
<%@ page import="com.softtek.javaweb.domain.model.ShipTo" %>
<%@ page import="com.softtek.javaweb.domain.model.User" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
	
<jsp:useBean id="userService" class="com.softtek.javaweb.service.UserService"/>
<jsp:useBean id="cartService" class="com.softtek.javaweb.service.CartService"/>
<jsp:useBean id="shipToService" class="com.softtek.javaweb.service.ShipToService"/>
<jsp:useBean id="statusService" class="com.softtek.javaweb.service.StatusService"/>
<jsp:useBean id="validateService" class="com.softtek.javaweb.domain.dto.ResponseStatus"/>

<%!public Cart makeCart(HttpServletRequest request) {
	Cart cart = new Cart();
	System.out.println(request.getQueryString());	
	Long cartId = StringUtils.isNotEmpty(request.getParameter("frmCartId")) ? new Long(request.getParameter("frmCartId")) : null;
	Float linesAmount = StringUtils.isNotEmpty(request.getParameter("frmLinesAmount")) ? new Float(request.getParameter("frmLinesAmount")) : null;
	Float shippingAmount = StringUtils.isNotEmpty(request.getParameter("frmShippingAmount")) ? new Float(request.getParameter("frmShippingAmount")) : null;
	Float cartAmount = StringUtils.isNotEmpty(request.getParameter("frmCartAmount")) ? new Float(request.getParameter("frmCartAmount")) : null;
	Long shipToId = StringUtils.isNotEmpty(request.getParameter("frmShipToId")) ? new Long(request.getParameter("frmShipToId")) : null;
	Long statusId = StringUtils.isNotEmpty(request.getParameter("frmStatusId")) ? new Long(request.getParameter("frmStatusId")) : null;
	String createUser = StringUtils.isNotEmpty(request.getParameter("frmCreateUser")) ? new String(request.getParameter("frmCreateUser")) : null;
	String createDateString = StringUtils.isNotEmpty(request.getParameter("frmCreateDate")) ? new String(request.getParameter("frmCreateDate")) : null;
	Timestamp createDate = null;
	try {
		createDate = createDateString != null ? new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createDateString).getTime()) : null;
	} catch (ParseException e) {
		e.printStackTrace();
	}
	String updateUser = StringUtils.isNotEmpty(request.getParameter("frmUpdateUser")) ? new String(request.getParameter("frmUpdateUser")) : null;
	String updateDateString = StringUtils.isNotEmpty(request.getParameter("frmUpdateDate")) ? new String(request.getParameter("frmUpdateDate")) : null;
	Timestamp updateDate = null;
	try {
		updateDate = updateDateString != null ? new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updateDateString).getTime()) : null;
	} catch (ParseException e) {
		e.printStackTrace();
	}

	cart.setCartId(cartId);
	cart.setLinesAmount(linesAmount);
	cart.setShippingAmount(shippingAmount);
	cart.setCartAmount(cartAmount);
	cart.setShipTo(new ShipTo(shipToId, null, StringUtils.EMPTY, StringUtils.EMPTY, null, Long.MIN_VALUE, StringUtils.EMPTY, StringUtils.EMPTY));
	cart.setStatus(new Status(statusId, StringUtils.EMPTY, StringUtils.EMPTY));
	cart.setCreateUser(createUser);
	cart.setCreateDate(createDate);
	cart.setUpdateUser(updateUser);
	cart.setUpdateDate(updateDate);
	
	return cart;
}%>
<%
Cart cart = new Cart();
String headerTitle = "Edit Cart";
String frmLblSubmitBtn = "Update";
List<String> frmMsgs = new ArrayList<>();

// if coming from edit link from list, populate form with values for selected cart
if ( request.getParameter("showDetail") != null )
{
	frmLblSubmitBtn = "Update";
	cart = cartService.getOne(new Long(request.getParameter("cartId")));
}

//if coming from add new button from list, use blank form
if (request.getParameter("addNew") != null) {
	frmLblSubmitBtn = "Save";
	headerTitle = "Add New Cart";
}

if ( request.getParameter("Update") != null ) {
	cart = this.makeCart(request);
    validateService = cartService.update(cart);
	if (validateService.isValid()) { // if succesful, redirect to list, otherwise repaint form with error messages
   		response.sendRedirect("/webtest/jsp/cart/list.jsp");		
   	}
}

if (request.getParameter("Save") != null) {
	frmLblSubmitBtn = "Save";
	headerTitle = "Add New Cart";
	cart = this.makeCart(request);
	System.out.println("##################### Cart Again: " + cart);
	validateService = cartService.add(cart);
	if (validateService.isValid()) { // if succesful, redirect to list, otherwise repaint form with error messages
 		response.sendRedirect("/webtest/jsp/cart/list.jsp");
	}
}

if (request.getParameter("delete") != null) {
	Long frmValCartId = StringUtils.isNotEmpty(request.getParameter("frmCartId")) ?
						   new Long(request.getParameter("frmCartId")) : Long.MIN_VALUE;
	validateService = cartService.delete(frmValCartId);
	if (validateService.isValid()) { // if succesful, redirect to list, otherwise repaint form with error messages
		response.sendRedirect("/webtest/jsp/cart/list.jsp");		
	} 
}

if (request.getParameter("cancel") != null) {
	response.sendRedirect("/webtest/jsp/cart/list.jsp");
}
if (request.getParameter("home") != null) {
	response.sendRedirect("/webtest/index.jsp");
}

pageContext.setAttribute("frmValCreateUser", cart.getCreateUser());
pageContext.setAttribute("frmValUpdateUser", cart.getUpdateUser());
pageContext.setAttribute("frmValShipToId", cart.getShipTo() != null ? cart.getShipTo().getShipToId() : null);
pageContext.setAttribute("frmValStatusId", cart.getStatus() != null ? cart.getStatus().getStatusId() : null);
pageContext.setAttribute("frmValMsgs", validateService.getServiceMsg());
pageContext.setAttribute("frmLblSubmitBtn", frmLblSubmitBtn);
pageContext.setAttribute("shipTos", shipToService.getList());
pageContext.setAttribute("statuses", statusService.getList());
pageContext.setAttribute("users", userService.getList());
pageContext.setAttribute("cart", cart);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/webtest/css/defaultStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carts</title>
</head>
<body>	
	<div id="headerList">
		<h1 align="center"><%= headerTitle %></h1>
	</div>
	<div style="margin-left:5%;margin-top: 1%;">
		<form action="/webtest/jsp/cart/edit.jsp" method="get">
			<input type="hidden" name="frmCartId" value="${ cart.cartId }"/><br>		
			<c:if test="${ cart.cartId != null }">
				Cart ID: ${ cart.cartId }<br>
			</c:if>
			Line Amount: <input type="number" name="frmLinesAmount" value="${ cart.linesAmount }"></input><br>
			<c:if test="${ cart.cartId != null }">
				Shipping Amount: ${ cart.shippingAmount }<br>
				Cart Amount: ${ cart.cartAmount }<br>
			</c:if>
			Ship-To Address: 
			<select name="frmShipToId">
				<option value="" selected>Select Address...</option>
				<c:forEach var="shipTo" items="${ shipTos }">
					<option value="${ shipTo.shipToId }" <c:if test="${ shipTo.shipToId == frmValShipToId }">selected</c:if>>${ shipTo.address }, ${ shipTo.city.description }, ${ shipTo.city.state.description }</option>
				</c:forEach>
			</select><br>
			Status: 
			<select name="frmStatusId">
				<option value="" selected>Select Status...</option>
				<c:forEach var="status" items="${ statuses }">
					<c:if test = "${ status.statusType == 'CART' }">
						<option value="${ status.statusId }" <c:if test="${ status.statusId == frmValStatusId }">selected</c:if>>${ status.description }</option>
					</c:if>
				</c:forEach>
			</select><br>
			<c:if test="${ cart.cartId == null }">
				Create User: 
				<select name="frmCreateUser">
					<option value="" selected>Select Username...</option>
					<c:forEach var="user" items="${ users }">
						<option value="${ user.username }" <c:if test="${ user.username == frmValCreateUser }">selected</c:if>>${ user.username }</option>
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
						<option value="${ user.username }" <c:if test="${ user.username == frmValUpdateUser }">selected</c:if>>${ user.username }</option>
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