<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Autorate</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/statistics.css">
<link
	href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah|Indie+Flower|Jua|Metamorphous|Permanent+Marker"
	rel="stylesheet">
<body>

	<div class="container">

		<div class="topbar">

			<div class="logo-container">
				<img src="${pageContext.request.contextPath}/images/logo-green.png"
					alt="Logo" onclick="window.location='/Autorate/IndexServlet';">
			</div>

			<%
				if (session.getAttribute("status") != null) {
			%>
			<div class="user-container">

				<div class="user-container-username">

					<p onclick="showUserDropdown()"><%=session.getAttribute("username")%>&#9662;
					</p>

					<div class="user-container-dropdown">
						<div>
							<p onclick="window.location='/Autorate/FetchRequestsServlet';">Your
								Requests</p>
						</div>
						<div>
							<p onclick="window.location='/Autorate/FetchAddCarServlet'">Add
								Car Request</p>
						</div>
						<form action="LogoutServlet" method="POST">
							<input type="submit" value="Logout" />
						</form>
					</div>
				</div>

			</div>
			<%
				} else {
			%>
			<div class="login-container">

				<div class="login">
					<form action="/Autorate/LoginServlet" method="POST">
						<input type="text" placeholder="Username" name="username">
						<input type="password" placeholder="Password" name="password">
						<button type="submit">Login</button>
					</form>
				</div>

				<div class="signup">
					<label>New user? <a
						href="${pageContext.request.contextPath}/pages/signup.jsp">Signup</a></label>
				</div>
			</div>
			<%
				}
			%>
		</div>
		
		<div class="inner-container">
		
			<div class="inner-container__filter">
			</div>
			
			<div class="inner-container__stat">
			</div>
		
		</div>
		
	</div>
	<script src="${pageContext.request.contextPath}/js/index.js"></script>
<script src="${pageContext.request.contextPath}/js/statistics.js"></script>
</body>
</html>