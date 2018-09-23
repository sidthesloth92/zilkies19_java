<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Approve Request</title>
<link rel="stylesheet"
	href=${PageContext.request.contextPath}/CricketTournament/css/ApproveRequest.css>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
<script type="text/javascript"
	src=${PageContext.request.contextPath}/CricketTournament/js/ApproveRequest.js></script>
</head>
<body>

	<%
		String userName = null;
		HttpSession sessionVar = request.getSession();
		userName = (String) sessionVar.getAttribute("user");
	%>
	<div class="user-container" id="home-container">
		<div class="header">
			<div class="header__left-option">
				<div class="logo">
					<h2 class="logo-design">CC</h2>
				</div>
				<div class="explorer">
					<h2>Cricket Connect</h2>
				</div>
			</div>
			<%
				if (userName != null) {
			%>
			<div class="header__right-option">
				<div class="login">
					<h3><%=userName%></h3>
				</div>
				<div class="login">
					<form class="form-element" name="Logout" method="post"
						action=${pageContext.request.contextPath}/Logout>
						<button>
							<h2>Logout</h2>
						</button>
					</form>
				</div>
			</div>
			<%
				}
			%>
		</div>
		<div class="tournament-container">
			<div class="tournament-container__sidenav">
				<a
					href="http://localhost:8080/CricketTournament/LoginServlet?method=admin">Home</a>
				<a href="http://localhost:8080/CricketTournament/ViewRequest">View
					Request</a> 
			</div>
			<div class="content-area">
				<div class="content-area__request" id="content_area">
					<div class="content-area__request__datas">
						<div class="content-area__request__datas__Sno">
							<h2>TOUR ID</h2>
						</div>
						<div class="content-area__request__datas__Tournament-name">
							<h2>TOUR NAME</h2>
						</div>
						<div class="content-area__request__datas__Tournament-format">
							<h2>TOUR FORMAT</h2>
						</div>
						<div class="content-area__request__datas__mobile">
							<h2>MOBILE</h2>
						</div>
						<div class="content-area__request__datas__status">
							<h2>APPROVE</h2>
						</div>
						<div class="content-area__request__datas__status-decline">
							<h2>DECLINE</h2>
						</div>
					</div>
				</div>
			</div>
			<div class="footer">
				<div class="footer-section">
					<div class="location-div">
						<div class="location">
							<i class="fas fa-map-marker-alt"></i>Chennai
						</div>
						<div class="phone">
							<i class="fas fa-phone"></i>8764536271
						</div>
						<div class="mail">
							<i class="far fa-envelope"></i>abc@gmail.com
						</div>
					</div>
					<div class="facebook-div">
						<div class="facebook">
							<a href="#"> <i class="fab fa-facebook"></i>
							</a>
						</div>
						<div class="twitter">
							<a href="#"> <i class="fab fa-twitter-square"></i>
							</a>
						</div>
						<div class="linked-in">
							<a href="#"> <i class="fab fa-linkedin"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
	</div>
	</div>
</body>
</html>