<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Admin Home</title>
<link rel="stylesheet"
	href=${PageContext.request.contextPath}/CricketTournament/css/AdminHomePage.css>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
<script type="text/javascript"
	src=${PageContext.request.contextPath}/CricketTournament/js/AdminHomePage.js></script>
</head>

<body>
	<%
		String userName = null;
		HttpSession sessionVar = request.getSession();
		userName = (String) sessionVar.getAttribute("user");
	%>

	<div class=" user-container" id="home-container">
		<div class="header ">
			<div class="header__left-option ">
				<div class="logo ">
					<h2 class="logo-design ">CC</h2>
				</div>
				<div class="explorer ">
					<h2>Cricket Connect</h2>
				</div>
			</div>
			<%
				if (userName != null) {
			%>
			<div class="header__right-option">
				<div class="login">
					<h2><%=userName%></h2>
				</div>
				<div class="login">
					<form name="Logout" method="post"
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
		<div class="tournament-container ">
			<div class="tournament-container__sidenav ">
				<a href="http://localhost:8080/CricketTournament/LoginServlet?method=admin">Home</a>
				<a href="http://localhost:8080/CricketTournament/ViewRequest">View Request</a>
			</div>
			<div class="tournament-container__content ">
				<div class="card-container ">
				<%
				ArrayList tournamentlist = new ArrayList();
				tournamentlist = (ArrayList)request.getAttribute("tournamentDetails");
					for(int i=0;i<tournamentlist.size();i+=3){
				%>
					<div class="card-border ">
						<div class="card-1 ">
							<div class="card-1__image ">
								<img src="/CricketTournament/images/kohli.jpg " alt="Avatar "
									style="height: 250px;" width="230px ">
							</div>
							<div class="tour-1 ">
								<h3><%=tournamentlist.get(i+1)%></h3>
							</div>

						</div>
					</div>
					<%}%>
				</div>
			</div>
		</div>
		<div class="footer ">
			<div class="footer-section ">
				<div class="location-div ">
					<div class="location ">
						<i class="fas fa-map-marker-alt "></i>Chennai
					</div>
					<div class="phone ">
						<i class="fas fa-phone "></i>8764536271
					</div>
					<div class="mail ">
						<i class="far fa-envelope "></i>abc@gmail.com
					</div>
				</div>
				<div class="facebook-div ">
					<div class="facebook ">
						<a href="# "> <i class="fab fa-facebook "></i>
						</a>
					</div>
					<div class="twitter ">
						<a href="# "> <i class="fab fa-twitter-square "></i>
						</a>
					</div>
					<div class="linked-in ">
						<a href="# "> <i class="fab fa-linkedin "></i>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="form-container" id="login-1">
		<div class="form-card ">
			<span onclick="displayHome() " class="close " title="Close Modal ">&times;
			</span>
			<div class="say-login ">
				<h2 class="login-text ">Login</h2>
			</div>
			<div class="password ">
				<input type="password " placeholder="Password ">
			</div>
			<div class="login-button ">
				<button>Login</button>
			</div>
			<div class="sign-up__link ">
				<div class="plain-text ">Don't have an account?</div>
				<div class="link ">
					<a href="# ">Sign Up</a>
				</div>
			</div>
		</div>
	</div>
</body>

</html>