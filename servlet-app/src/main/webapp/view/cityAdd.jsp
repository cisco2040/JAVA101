<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*"%>
<%@ page import="com.softtek.academy.servlet.domain.model.State"%>
<%@ page import="com.softtek.academy.servlet.repository.StateRepository"%>
<%@ page import="com.softtek.academy.servlet.repository.DatabaseConnection"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>States</title>
</head>
<body>
<jsp:useBean id="st" class="com.softtek.academy.servlet.repository.StateRepository"/>
<% 
List<State> states = st.list();
pageContext.setAttribute("states", states);
%>    
	<h3>New City</h3>
	
	<form action="/servlet-app/cities" method="post">
		id: <input type="text" name="id"><br>
		Description: <input type="text" name="description"><br>
		State: 
		<select name="stateId">
			<option value="0" selected>- Select -</option>
			<c:forEach var="st" items="${ states }">
				<option value="${ st.id }">${ st.description }</option>
			</c:forEach>
		</select>
		<br>
		
		<input type="submit" name="save" value="Save">
	</form>
	
	<br>
	<a href="/servlet-app/index.html">Return to Index</a>
</body>

</html>