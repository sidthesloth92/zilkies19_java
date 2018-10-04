<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>UserHome</title>
<link rel="stylesheet"
	href=${PageContext.request.contextPath}/CricketTour/css/UserHomePage.css>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
<script type="text/javascript"
	src=${PageContext.request.contextPath}/CricketTour/js/UserHomePage.js></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
</head>

<body>

	<%
		String userName = null;
		HttpSession sessionVar = request.getSession();
		userName = (String) sessionVar.getAttribute("user");
	%>

	<%
		if (request.getParameter("message") != "") {
	%>
	<p>${param.message}</p>
	<%
		}
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
				if (userName == null) {
			%>
			<div class="header__right-option">
				<div class="login">
					<button onclick="displayLoginFunctionality()" class="login-button">
						<h2>Login</h2>
					</button>
				</div>
			</div>
			<%
				} else {
			%>
			<div class="header__right-option">
				<div class="login">
					<h3><%=userName%></h3>
				</div>
				<div class="login">
					<form name="Logout" method="post"
						action=${pageContext.request.contextPath}/Logout>
						<button class="login-button">
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
				<a href="http://localhost:8080/CricketTour/Home">Home</a>
				<a href="http://localhost:8080/CricketTour/AddTournament">Add Tournament</a> 
				<a href="http://localhost:8080/CricketTour/EditPage">Edit</a> 
				<a href="http://localhost:8080/CricketTour/CreateSchedule">Schedule</a> 
				<a href="http://localhost:8080/CricketTour/ViewSchedule">Scorecard</a>
				<a href="http://localhost:8080/CricketTour/Scorecard">Update Scorecard</a>
				<a href="http://localhost:8080/CricketTour/RemoveTournament">Remove Tournament</a>
			</div>
			<div class="tournament-container__content">
				<div class="card-container">
				<%
				ArrayList tournamentlist = new ArrayList();
				tournamentlist = (ArrayList)request.getAttribute("tournamentDetails");
					for(int i=0;i<tournamentlist.size();i+=3){
				%>
					<div class="card-border">
						<div class="card-1">
							<div class="card-1__image">
								<img src="/CricketTour/images/ms-raina.jpg" alt="Avatar"
									style="height: 250px;" width="230px">
							</div>
							<div class="tour-1">
								<h3 id="<%=i %>"><%=tournamentlist.get(i+1)%></h3>
							</div>
							<div class="card-1__button">
								<button  id="<%=i %>" onclick="getTeam(this.id)">Register</button>
							</div>
						</div>
					</div>
					<%}%>
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
	<div class="form-container" id="login-1">
		<form name="loginForm" method="post"
			action=${pageContext.request.contextPath}/LoginServlet>
			<div class="form-card">
				<span onclick="displayHome()" class="close" title="Close Modal">&times;
				</span>
				<div class="say-login">
					<h2 class="login-text">Login</h2>
				</div>
				<div class="email">
					<input type="email" onfocusout="isValidUser()" placeholder="Email"
						name="email" id="email">
				</div>
				<p id="somediv"></p>
				<div class="password">
					<input type="password" onfocusout="isValidPassword()"
						placeholder="Password" name="password" id="password">
				</div>
				<p id="somediv1"></p>
				<div class="login-button">
					<button name="buttonproperty" onclick="isUser()">Login</button>
				</div>
				<div class="sign-up__link">
					<div class="plain-text">Don't have an account?</div>
					<div class="link">
						<div class="switch-link" onclick="displayRegisterFunctionality()">SignUp</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="form-container" id="register-1">
		<form name="registerForm" method="post"
			action=${pageContext.request.contextPath}/RegisterServlet>
			<div class="form-card">
				<span onclick="displayHome()" class="close" title="Close Modal">&times;
				</span>
				<div class="say-login">
					<h2 class="login-text">SignUp</h2>
				</div>
				<div class="first-name">
					<input type="text" onfocusout="validateFirstName()" placeholder="FirstName" name="first-name">
					<p id="first-name"></p>
				</div>
				<div class="last-name">
					<input type="text" placeholder="LastName" name="last-name" onfocusout="validateLastName()">
					<p id="last-name"></p>
				</div>
				<div class="password">
					<input type="password" placeholder="Password" name="regpassword" onfocusout="validatePassword()">
					<p id="regpassword"></p>
				</div>
				<div class="age">
					<input type="text" placeholder="Age" name="age" onfocusout="validateAge()">
					<p id="age"></p>
				</div>
				<div class="mobile">
					<input type="text" placeholder="Mobile" name="mobile" onfocusout="validateMobile()">
					<p id="mobile"></p>
				</div>
				<div class="email">
					<input type="email" placeholder="Email" onfocusout="isEmailTaken()" name="regemail" id="regemail" onfocusout="validateEmail()">
					<p id="email"></p>
				</div>
				<p id="somediv2"></p>
				<div class="login-button">
					<button name="registerbutton" type="submit" onclick="return validateform()">Register</button>
				</div>
				<div class="sign-up__link">
					<div class="plain-text">Have an account?</div>
					<div class="link">
						<div class="switch-link" onclick="displayLoginFunctionality()">Log
							in</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>

</html>