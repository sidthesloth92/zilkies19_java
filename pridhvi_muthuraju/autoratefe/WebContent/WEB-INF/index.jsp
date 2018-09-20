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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css">

<link
	href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah|Indie+Flower|Jua|Metamorphous|Permanent+Marker"
	rel="stylesheet">

<body onload="getMakes()">

	<div class="container">

		<div class="topbar">

			<div class="logo-container">
				<img src="${pageContext.request.contextPath}/images/logo-green.png"
					alt="Logo" onclick="window.location='/autoratefe/IndexServlet';">
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
							<p onclick="window.location='/autoratefe/FetchRequestsServlet';">Your
								Requests</p>
						</div>
						<div>
							<p onclick="window.location='/autoratefe/FetchAddCarServlet'">Add
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
					<form action="/autoratefe/LoginServlet" method="POST">
						<input type="text" placeholder="Username" name="username">
						<input type="password" placeholder="Password" name="password">
						<button type="submit">Login</button>
					</form>
				</div>

				<div class="signup">
					<label>New user? <a
						onclick="window.location='/autoratefe/FetchSignupServlet'">Signup</a></label>
				</div>
			</div>
			<%
				}
			%>
		</div>

		<div class="inner-container">

			<div class="inner-container-main">

				<div class="inner-container-main-options">

					<div>

						<button onclick="getMakes()" class="button--hover">
							<p>Makes</p>
						</button>

					</div>

					<div>

						<button onclick="getTypes()" class="button--hover">
							<p>Types</p>
						</button>

					</div>
					
					<div>

						<button onclick="window.location='/autoratefe/FetchStatisticsServlet';" class="button--hover">
							<p>Statistics</p>
						</button>

					</div>


				</div>

				<div class="inner-container-main-list"></div>

			</div>

			<div class="inner-container-cars">

				<div class="inner-container-cars-car-header">
					<h2>List of Cars</h2>
				</div>

			</div>

			<footer>
				<p>Autorate © 2018</p>
			</footer>

		</div>

	</div>
	<script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>

</html>