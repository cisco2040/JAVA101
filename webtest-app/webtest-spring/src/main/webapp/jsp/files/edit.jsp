<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/defaultStyles.css"/> " />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
</head>
<body>
	<div id="headerList">
		<h1 align="center">File Upload</h1>
	</div>
	<div style="margin-left: 5%; margin-top: 1%;">
		<form action=<c:url value="/files/edit"/> method="post"	enctype="multipart/form-data">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<table>
				<tr>
					<td align="right">File:</td>
					<td><input type="file" name="file" /></td>
				<tr>
			</table>
			<div style="margin-top: 1%;">
				<button name="upload" type="submit">Upload File</button>
				<button name="cancel" type="submit">Cancel</button>
			</div>
		</form>
		<c:forEach var="frmValMsg" items="${ frmValMsgs }">
			<p style="color: red">${ frmValMsg }</p>
		</c:forEach>
	</div>
</body>
</html>