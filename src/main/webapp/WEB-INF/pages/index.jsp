<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books Shop Online</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />
	
	<div class="page-title">Shopping Cart Demo</div>
	<div class="demo-container">
		<h3>Demo Content</h3>
	<ul>
		<li>Buy Online</li>
		<li>Admin Pages</li>
		<li>Reports</li>
	</ul>
	</div>
	
	<jsp:include page="_footer.jsp" />
</body>
</html>