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
		padding: 40px;
		margin: 0px;
		border: solid;
		border-color: black;
		border-radius: 5px;
		border-width: 1px;
		text-align: center;
	}
</style>	
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/webtest-spring/css/defaultStyles.css"/>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
		<title>Web App Test</title>
	</head>
	<body class="container-fluid">
		<div id="headerList">
			<h1 align="center">Maintenance</h1>
		</div>
		<div class="container">
			<div class="row menu-grid">
				<a href="/webtest-spring/jsp/cart/list.jsp">
					<div class="col-sm-4 menu-grid-element">
						<p>Carts</p>
					</div>
				</a>
				<a href="/webtest-spring/jsp/shipTo/list.jsp">
					<div class="col-sm-4 menu-grid-element">
						<p>Ship To Addresses</p>
					</div>
				</a>
				<a href="/webtest-spring/jsp/user/list.jsp">
					<div class="col-sm-4 menu-grid-element">
						<p>Users</p>
					</div>
				</a>
			</div>
		</div> 
		<div id="headerList">
			<h1 align="center">Catalog</h1>
		</div>	
		<div class="container">
			<div class="row menu-grid">
				<a href="/webtest-spring/jsp/city/list.jsp">
					<div class="col-sm-4 menu-grid-element">
						<p>Cities</p>
					</div>
				</a>
				<a href="/webtest-spring/jsp/state/list.jsp">
					<div class="col-sm-4 menu-grid-element">
						<p>States</p>
					</div>
				</a>
				<a href="/webtest-spring/jsp/shippingZone/list.jsp">
					<div class="col-sm-4 menu-grid-element">
						<p>Shipping Zones</p>
					</div>
				</a>
			</div>
			<div class="row menu-grid">
				<a href="/webtest-spring/jsp/userRole/list.jsp">
					<div class="col-sm-4 menu-grid-element">
						<p>User Roles</p>
					</div>
				</a>
				<a href="/webtest-spring/jsp/status/list.jsp">
					<div class="col-sm-4 menu-grid-element">
						<p>Status</p>
					</div>
				</a>
			</div>
		</div> 
	</body>
</html>
