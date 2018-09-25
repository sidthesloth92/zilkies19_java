<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>ScheduleMatch</title>
<link rel="stylesheet"
	href=${PageContext.request.contextPath}/CricketTour/css/ScheduleMatch.css>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
	<script type="text/javascript"
	src=${PageContext.request.contextPath}/CricketTour/js/scorecard.js></script>
</head>

<body>
	<%
		String userName = null;
		HttpSession sessionVar = request.getSession();
		userName = (String) sessionVar.getAttribute("user");
	%>
	<div class="scorecard" id="scorecard-1">
		<span onclick="displaySchedule()" class="close" title="Close Modal">&times;</span>
		<div class="card">
			<div class="sub-titles">
				<div class="team-name">
					<h3>Team</h3>
				</div>
				<div class="runs">
					<h3>Runs</h3>
				</div>
				<div class="overs">
					<h3>Overs</h3>
				</div>
				<div class="wkts">
					<h3>Wkts</h3>
				</div>
				<div class="score">
					<h3>Score</h3>
				</div>
			</div>
			<div class="team-1">
				<div class="team-name">
					<select id="dropdown11" onfocusout="fetchScorecard()">
					</select>
				</div>
				<div class="runs">
					<input type="number" name="runs">
				</div>
				<div class="overs">
					<input type="text" name="overs">
				</div>
				<div class="wkts">
					<input type="number" name="wkts">
				</div>
				<div class="score">
					<button onclick="updateScore(this.id);displaySchedule()">Update</button>
				</div>
			</div>
		</div>
	</div>
	<div class="user-container" id="schedule-1">
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
							Logout
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
			<div class="border">
				<div class="team-container-dropdown">
					<div class="team">
						<select id="dropdown1">
							<%
								ArrayList tournamentlist = new ArrayList();
								tournamentlist = (ArrayList) request.getAttribute("tourDetails");
								System.out.println(tournamentlist);
								for (int i = 0; i < tournamentlist.size(); i += 3) {
							%>
							<option value="<%=tournamentlist.get(i + 1)%>"><%=tournamentlist.get(i + 1)%></option>
							<%
								}
								if(tournamentlist.size()==0){%>
									<div> Sorry you haven't added any Tournament to Organise</div>
									
								<% }%>
						</select>
					</div>
					<div class="view">
						<button onclick="filterTournament()">Filter</button>
					</div>
				</div>
				<div class="schedule-container">
					<div class="teams" id="dynamicadder"></div>
					<div class="pic-container"></div>
				</div>
			</div>
		</div>
	</div>
	<script>
			</script>
</body>

</html>