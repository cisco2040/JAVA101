<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
.menu-grid {
	margin-top: 10px;
}
.menu-grid-element:hover {
	background-color: dodgerblue;
}
.menu-grid-element {
	color: white;
	background-color: blue;
	padding: 20px;
	margin: 0px;
	border: solid;
	border-color: black;
	border-radius: 5px;
	border-width: 1px;
	text-align: center;
}
</style>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/defaultStyles.css"/> " />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<title>Web App Test</title>
</head>
<body class="container-fluid">
	<form action="<c:url value="/logout"/>" method="post" id="loginForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<h4 align="right">
		Welcome : ${username} | <a href="javascript:formSubmit(event, '${username}')"> ${ username == "Anonymous" ? "Login" : "Logout" }</a>
		<a href="<c:url value="/login"/>" style="display: none" id="loginAction"></a>
	</h4>
	<div class="container">
		<div class="row">
			<div id="headerList">
				<h1 align="center">Maintenance</h1>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1 col-xl-1"></div>
			<div class="col-sm-10 col-md-10 col-lg-10 col-xl-10">
				<div class="row menu-grid">
					<a href="<c:url value="/cart/list"/>">
						<div class="col-sm-4 col-md-4 col-lg-4 col-xl-4 menu-grid-element">
							<p>Carts</p>
						</div>
					</a>
					<a href="<c:url value="/shipTo/list"/>">
						<div class="col-sm-4 col-md-4 col-lg-4 col-xl-4 menu-grid-element">
							<p>Ship To Addresses</p>
						</div>
					</a>
					<a href="<c:url value="/user/list"/>">
						<div class="col-sm-4 col-md-4 col-lg-4 col-xl-4 menu-grid-element">
							<p>Users</p>
						</div>
					</a>
				</div>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1 col-xl-1"></div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div id="headerList">
				<h1 align="center">Catalog</h1>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1 col-xl-1"></div>
			<div class="col-sm-10 col-md-10 col-lg-10 col-xl-10">
				<div class="row menu-grid">
					<a href="<c:url value="/city/list"/>">
						<div class="col-sm-4 col-md-4 col-lg-4 col-xl-4 menu-grid-element">
							<p>Cities</p>
						</div>
					</a> <a href="<c:url value="/state/list"/>">
						<div class="col-sm-4 col-md-4 col-lg-4 col-xl-4 menu-grid-element">
							<p>States</p>
						</div>
					</a> <a href="<c:url value="/shippingZone/list"/>">
						<div class="col-sm-4 col-md-4 col-lg-4 col-xl-4 menu-grid-element">
							<p>Shipping Zones</p>
						</div>
					</a>
					<a href="<c:url value="/userRole/list"/>">
						<div class="col-sm-4 col-md-4 col-lg-4 col-xl-4 menu-grid-element">
							<p>User Roles</p>
						</div>
					</a>
					<a href="<c:url value="/status/list"/>">
						<div class="col-sm-4 col-md-4 col-lg-4 col-xl-4 menu-grid-element">
							<p>Status</p>
						</div>
					</a>
				</div>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1 col-xl-1"></div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div id="headerList">
				<h1 align="center">File Upload</h1>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1 col-xl-1"></div>
			<div class="col-sm-10 col-md-10 col-lg-10 col-xl-10">
				<div class="row menu-grid">
					<a href="<c:url value="/files/list"/>">
						<div class="menu-grid-element">
							<p>Upload Files</p>
						</div>
					</a>
				</div>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1 col-xl-1"></div>
		</div>
	</div>
</body>
	<script>
		function formSubmit(event, username) {
			if (username != 'Anonymous' ) {
				if (confirm("Do you want to finish your session?")) {
					document.getElementById("loginForm").submit();					
				}
			} else {
				document.getElementById("loginAction").click();
			}
		}
	</script>
</html>
