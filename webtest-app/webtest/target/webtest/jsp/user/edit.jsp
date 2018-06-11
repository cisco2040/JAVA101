<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.softtek.javaweb.domain.model.User" %>
<%@ page import="com.softtek.javaweb.domain.model.UserRole" %>
<%@ page import="com.softtek.javaweb.service.ValidateService" %>
<%@ page import="com.softtek.javaweb.service.ValidateService.ValidateServiceMsg" %>
<%@ page import="com.softtek.javaweb.service.UserService" %>
<%@ page import="com.softtek.javaweb.service.UserRoleService" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="userService" class="com.softtek.javaweb.service.UserService"/>
<jsp:useBean id="userRoleService" class="com.softtek.javaweb.service.UserRoleService"/>
<%
	pageContext.setAttribute("users", userService.getList());
	pageContext.setAttribute("userRoles", userRoleService.getList());
%>
<%!public User makeUser(HttpServletRequest request) {
	User user = new User();
	
	String username = StringUtils.isNotEmpty(request.getParameter("frmUsername")) ? new String(request.getParameter("frmUsername")) : null;
	String password = StringUtils.isNotEmpty(request.getParameter("frmPassword")) ? new String(request.getParameter("frmPassword")) : null;
	String name = StringUtils.isNotEmpty(request.getParameter("frmName")) ? new String(request.getParameter("frmName")) : null;
	String userRoleId = StringUtils.isNotEmpty(request.getParameter("frmUserRoleId")) ? new String(request.getParameter("frmUserRoleId")) : null;
	String active = StringUtils.isNotEmpty(request.getParameter("frmActive")) ? new String(request.getParameter("frmActive")) : null;
	
	user.setUsername(username);
	user.setPassword(password);
	user.setName(name);
	user.setUserRole(new UserRole(userRoleId, StringUtils.EMPTY));
	user.setActive(active);
	
	return user;
}%>
<%
	String headerTitle = "Edit User";
	String frmValUsername = "";
	String frmValPassword = "";
	String frmValPasswordConfirm = "";
	String frmValName = "";
	String frmValUserRole = "";
	String frmValActive = "";
	String frmLblSubmitBtn = "";
	List<ValidateServiceMsg> frmMsgs = new ArrayList<>();
	User user = new User();

// if coming from edit link from list, populate form with values for selected user
if ( request.getParameter("showDetail") != null )
{
	frmLblSubmitBtn = "Update";
	user = userService.getOne(request.getParameter("username"));
	List<UserRole> userRoles = userRoleService.getList();			
	pageContext.setAttribute("frmValUserRole", user.getUserRole().getUserRoleId());
	pageContext.setAttribute("frmValPasswordConfirm", user.getPassword());
}

//if coming from add new button from list, use blank form
if (request.getParameter("addNew") != null) {
	frmLblSubmitBtn = "Save";
	headerTitle = "Add New User";
}

if ( request.getParameter("Update") != null ) {
	frmLblSubmitBtn = "Update";
	user = this.makeUser(request);
	String passwordConfirm = StringUtils.isNotEmpty(request.getParameter("frmPasswordConfirm")) ?
		       new String(request.getParameter("frmPasswordConfirm")) : StringUtils.EMPTY;
	
	System.out.println("##################### Update:" + user);
	
    ValidateService updated = userService.update(user, passwordConfirm);

	System.out.println("##################### Updated?:" + updated);

	if (updated.isValid()) { // if succesful, redirect to list, otherwise repaint form
   		response.sendRedirect("/webtest/jsp/user/list.jsp");		
   	}
	frmMsgs = updated.getServiceMsg();
	pageContext.setAttribute("frmValUserRole", user.getUserRole().getUserRoleId());
	pageContext.setAttribute("frmValPasswordConfirm", passwordConfirm);
}

if (request.getParameter("Save") != null) {
	frmLblSubmitBtn = "Save";
	headerTitle = "Add New User";
	user = this.makeUser(request);
	String passwordConfirm = StringUtils.isNotEmpty(request.getParameter("frmPasswordConfirm")) ?
		       new String(request.getParameter("frmPasswordConfirm")) : StringUtils.EMPTY;
	
	System.out.println("##################### Add: " + user.toString());
	
    ValidateService added = userService.add(user, passwordConfirm);
	
	if (added.isValid()) {
 		response.sendRedirect("/webtest/jsp/user/list.jsp");
	}
	frmMsgs = added.getServiceMsg();
	pageContext.setAttribute("frmValUserRole", user.getUserRole().getUserRoleId());
	pageContext.setAttribute("frmValPasswordConfirm", passwordConfirm);
}

if (request.getParameter("delete") != null) {
	frmValUsername = StringUtils.isNotEmpty(request.getParameter("frmUsername")) ?
	   new String(request.getParameter("frmUsername")) : StringUtils.EMPTY;
	Boolean deleted = userService.delete(frmValUsername);
	if (deleted) {
		response.sendRedirect("/webtest/jsp/user/list.jsp");		
	} else {
		// handle false result
	}
}

if (request.getParameter("cancel") != null) {
	response.sendRedirect("/webtest/jsp/user/list.jsp");
}
if (request.getParameter("home") != null) {
	response.sendRedirect("/webtest/index.jsp");
}

pageContext.setAttribute("frmValMsgs", frmMsgs);
pageContext.setAttribute("frmLblSubmitBtn", frmLblSubmitBtn);
pageContext.setAttribute("user", user);
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
		<form action="/webtest/jsp/user/edit.jsp" method="post">
			Username: <input type="text" name="frmUsername" value="${ user.username }" 
				<c:if test="${ param.showDetail != null }">readonly="true"</c:if>
				<c:if test="${ param.Update != null }">readonly="true"</c:if>/>
			<br>
			Password: <input type="password" name="frmPassword" value="${ user.password }"></input><br>
			Confirm Password: <input type="password" name="frmPasswordConfirm" value="${ frmValPasswordConfirm }"></input><br>
			Name    : <input type="text" name="frmName" value="${ user.name }"></input><br>
			User Role: 
			<select name="frmUserRoleId">
				<option value="" selected>Select Role...</option>
				<c:forEach var="userRole" items="${ userRoles }">
					<option value="${ userRole.userRoleId }" <c:if test="${ userRole.userRoleId == frmValUserRole }">selected</c:if>>${ userRole.description }</option>
				</c:forEach>
			</select><br>
			Active  : <input type="text" name="frmActive" value="${ user.active }"></input><br>
			
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
			<p style="color: red">${ frmValMsg.message }</p>
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