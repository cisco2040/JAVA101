<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->
 <!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/defaultStyles.css"/> "/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Files</title>
</head>
<body>	
	<div id="headerList">
		<h1 align="center">File List</h1>
	</div>
	
	<div style="margin-left:5%;margin-bottom: 1%;">
		<form action=<c:url value="/files/edit"/> method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button name="home" type="submit" >Home</button>
			<button name="addNew" type="submit" >Add New..</button>
		</form>
	</div>
	
	<div id="resultsSection">
	<table id="resultsTable">
		<tr>
			<th align="left">File Name</th>
			<th align="left">File Size (KB)</th>
			<th align="left">Actions</th>
		</tr>
		<c:forEach var="file" items="${ files }">
			<tr>
				<td><a href=<c:url value="${ file.fullFilenameEncoded }"/> target="_blank">${ file.filename }</a></td>
				<td align="right"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${ file.filesize / 1024 }"></fmt:formatNumber></td>
				<td><a href=<c:url value="/files/edit?delete&filename=${ file.filenameEncoded }"/> onclick="javascript:alertDelete(event)">Delete</a></td>
			</tr>
		</c:forEach>		
	</table>
	</div>
</body>
<script>
function alertDelete(event) {
    if (confirm("Do you want to delete this file?")) {
    } else {
    	event.preventDefault();
    }
}
</script>
</html>