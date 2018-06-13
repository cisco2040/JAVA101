<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.softtek.javaweb.domain.model.ShipTo" %>
<%@ page import="com.softtek.javaweb.domain.model.User" %>
<%@ page import="com.softtek.javaweb.domain.model.UserRole" %>
<%@ page import="com.softtek.javaweb.domain.model.State" %>
<%@ page import="com.softtek.javaweb.domain.model.ShippingZone" %>
<%@ page import="com.softtek.javaweb.domain.model.City" %>
<%@ page import="com.softtek.javaweb.service.UserService" %>
<%@ page import="com.softtek.javaweb.service.ValidateService" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="userService" class="com.softtek.javaweb.service.UserService"/>
<jsp:useBean id="shipToService" class="com.softtek.javaweb.service.ShipToService"/>
<jsp:useBean id="cityService" class="com.softtek.javaweb.service.CityService"/>
<jsp:useBean id="validateService" class="com.softtek.javaweb.service.ValidateService"/>

<%!public ShipTo makeShipTo(HttpServletRequest request) {
	ShipTo shipTo = new ShipTo();
	
	Long shipToId = StringUtils.isNotEmpty(request.getParameter("frmShipToId")) ? new Long(request.getParameter("frmShipToId")) : null;
	String user = StringUtils.isNotEmpty(request.getParameter("frmUserId")) ? new String(request.getParameter("frmUserId")) : null;
	String name = StringUtils.isNotEmpty(request.getParameter("frmName")) ? new String(request.getParameter("frmName")) : null;
	String address = StringUtils.isNotEmpty(request.getParameter("frmAddress")) ? new String(request.getParameter("frmAddress")) : null;
	Long cityId = StringUtils.isNotEmpty(request.getParameter("frmCityId")) ? new Long(request.getParameter("frmCityId")) : null;
	Long zipcode = StringUtils.isNotEmpty(request.getParameter("frmZipcode")) ? new Long(request.getParameter("frmZipcode")) : null;
	String phone = StringUtils.isNotEmpty(request.getParameter("frmPhone")) ? new String(request.getParameter("frmPhone")) : null;
	String email = StringUtils.isNotEmpty(request.getParameter("frmEmail")) ? new String(request.getParameter("frmEmail")) : null;

	shipTo.setShipToId(shipToId);
	shipTo.setUser(new User(user, StringUtils.EMPTY, StringUtils.EMPTY, null, StringUtils.EMPTY));
	shipTo.setName(name);
	shipTo.setAddress(address);
	shipTo.setCity(new City(cityId, StringUtils.EMPTY, null));
	shipTo.setZipcode(zipcode);
	shipTo.setPhone(phone);
	shipTo.setEmail(email);

	return shipTo;
}%>
<%
ShipTo shipTo = new ShipTo();
String headerTitle = "Edit Ship-To Address";
String frmLblSubmitBtn = "Update";
List<String> frmMsgs = new ArrayList<>();

// if coming from edit link from list, populate form with values for selected shipTo
if ( request.getParameter("showDetail") != null )
{
	frmLblSubmitBtn = "Update";
	shipTo = shipToService.getOne(new Long(request.getParameter("shipToId")));
}

//if coming from add new button from list, use blank form
if (request.getParameter("addNew") != null) {
	frmLblSubmitBtn = "Save";
	headerTitle = "Add New Ship-To Address";
}

if ( request.getParameter("Update") != null ) {
	shipTo = this.makeShipTo(request);
    validateService = shipToService.update(shipTo);
	if (validateService.isValid()) { // if succesful, redirect to list, otherwise repaint form with error messages
   		response.sendRedirect("/webtest/jsp/shipTo/list.jsp");		
   	}
}

if (request.getParameter("Save") != null) {
	frmLblSubmitBtn = "Save";
	headerTitle = "Add New Ship-To Address";
	shipTo = this.makeShipTo(request);
	validateService = shipToService.add(shipTo);
	if (validateService.isValid()) { // if succesful, redirect to list, otherwise repaint form with error messages
 		response.sendRedirect("/webtest/jsp/shipTo/list.jsp");
	}
}

if (request.getParameter("delete") != null) {
	Long frmValShipToId = StringUtils.isNotEmpty(request.getParameter("frmShipToId")) ?
						   new Long(request.getParameter("frmShipToId")) : Long.MIN_VALUE;
	validateService = shipToService.delete(frmValShipToId);
	if (validateService.isValid()) { // if succesful, redirect to list, otherwise repaint form with error messages
		response.sendRedirect("/webtest/jsp/shipTo/list.jsp");		
	} 
}

if (request.getParameter("cancel") != null) {
	response.sendRedirect("/webtest/jsp/shipTo/list.jsp");
}
if (request.getParameter("home") != null) {
	response.sendRedirect("/webtest/index.jsp");
}

pageContext.setAttribute("frmValUserId", shipTo.getUser() != null ? shipTo.getUser().getUsername() : null);
pageContext.setAttribute("frmValCityId", shipTo.getCity() != null ? shipTo.getCity().getCityId() : null);
pageContext.setAttribute("frmValMsgs", validateService.getServiceMsg());
pageContext.setAttribute("frmLblSubmitBtn", frmLblSubmitBtn);
pageContext.setAttribute("users", userService.getList());
pageContext.setAttribute("cities", cityService.getList());
pageContext.setAttribute("shipTo", shipTo);

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
	<div style="margin-left:5%;margin-top: 1%;">
		<form action="/webtest/jsp/shipTo/edit.jsp" method="post">
			<c:if test="${ shipTo.shipToId != null }">
				Ship-To ID: <input type="text" name="frmShipToId" value="${ shipTo.shipToId }" readonly /><br>
			</c:if>
			Username: 
			<select name="frmUserId">
				<option value="" selected>Select Username...</option>
				<c:forEach var="user" items="${ users }">
					<option value="${ user.username }" <c:if test="${ user.username == frmValUserId }">selected</c:if>>${ user.username }</option>
				</c:forEach>
			</select><br>
			Name: <input type="text" name="frmName" value="${ shipTo.name }"></input><br>
			Address: <input type="text" name="frmAddress" value="${ shipTo.address }"></input><br>
			City: 
			<select name="frmCityId">
				<option value="" selected>Select City...</option>
				<c:forEach var="city" items="${ cities }">
					<option value="${ city.cityId }" <c:if test="${ city.cityId == frmValCityId }">selected</c:if>>${ city.description }, ${ city.state.description }</option>
				</c:forEach>
			</select><br>
			Zip Code: <input type="number" name="frmZipcode" value="${ shipTo.zipcode }"></input><br>
			Phone: <input type="text" name="frmPhone" value="${ shipTo.phone }"></input><br>
			Email: <input type="email" name="frmEmail" value="${ shipTo.email }"></input><br>
			
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