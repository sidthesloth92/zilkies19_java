<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Schedule</title>
    <link rel="stylesheet" href="${PageContext.request.contextPath}/CricketTour/css/schedule.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
        crossorigin="anonymous">
        <script type="text/javascript"
	src=${PageContext.request.contextPath}/CricketTour/js/schedule.js></script>
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
           <div class="team-1">
                <div class="team-name">
                    <h3 id="team1">Team1</h3>
                </div>
                <div class="score">
                    <h3 id="score1">220-3(36)</h3>
                </div>
            </div>
            <div class="team-2">
                <div class="team-name">
                    <h3 id="team2">Team2</h3>
                </div>
                <div class="score">
                    <h3 id="score2">220-3(36)</h3>
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
							%>
                        </select>
                    </div>
                    <div class="view">
                        <button onclick="filterTournament()">Filter</button>
                    </div>
                </div>
                <div class="teams" id="dynamicadder">
                </div>
            </div>
        </div>
    </div>
    <script>
    
        </script>
</body>

</html>