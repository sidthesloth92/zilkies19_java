<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>EditPage</title>
<link rel="stylesheet"
	href="${PageContext.request.contextPath}/CricketTour/css/UserHomePage.css">
<link rel="stylesheet"
	href="${PageContext.request.contextPath}/CricketTour/css/login.css">
<link rel="stylesheet"
	href="${PageContext.request.contextPath}/CricketTour/css/EditPage.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
<script type="text/javascript"
	src=${PageContext.request.contextPath}/CricketTour/js/EditPage.js></script>
</head>

<body>
	<%
		String email = null;
		HttpSession sessionVar = request.getSession();
		email = (String) sessionVar.getAttribute("email");
		String userName = null;
		userName = (String) sessionVar.getAttribute("user");
	%>
	<div class="user-container">
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
			<a href="http://localhost:8080/CricketTour/Home">Home</a>
				<a href="http://localhost:8080/CricketTour/AddTournament">Add Tournament</a>
				<a href="http://localhost:8080/CricketTour/EditPage">Edit</a> 
				<a href="http://localhost:8080/CricketTour/CreateSchedule">Schedule</a> 
				<a href="http://localhost:8080/CricketTour/ViewSchedule">Scorecard</a>
				<a href="http://localhost:8080/CricketTour/Scorecard">Update Scorecard</a>
				<a href="http://localhost:8080/CricketTour/RemoveTournament">Remove Tournament</a>
			</div> 
			<form name="registerForm" method="post"
				action="http://localhost:8080/CricketTour/RegisterTeam">
			<div class="form-card">
				<div class="team">
					<select name="dropdown1" id="dropdown1">
						<%
							ArrayList tournamentlist = new ArrayList();
							tournamentlist = (ArrayList) request.getAttribute("tournamentDetails");
							System.out.println(tournamentlist);
							for (int i = 0; i < tournamentlist.size(); i += 3) {
						%>
						<option value="<%=tournamentlist.get(i + 1)%>"><%=tournamentlist.get(i + 1)%></option>
						<%
							}
						%>
					</select>
				</div>
				<div class="view">
					<button type="button" onclick="filterTournament()">Filter</button>
				</div>
				<div class="team-details-container">
					<div class="team-form">
						<div class="form-div" id="dynamicadder"></div>
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</body>

</html>