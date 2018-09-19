<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="io.ztech.cricalertfe.beans.Team"%>
<%@ page import ="io.ztech.cricalertfe.constants.Paths"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href=<%= Paths.GRID_CSS %>>
    <link rel="stylesheet" href=<%= Paths.ADD_MATCH_CSS %>>
    <title>Create Match</title>
</head>

<body>
    <header>
        <img class="return-icon" src=<%= Paths.ICONS_BACK %> alt="return" onclick="window.location='<%= Paths.HOME_SERVLET %>'" />
        <img class="options-icon" src=<%= Paths.ICONS_DOTS %> alt="options" />
    </header>
    <section class="details">
		<form class="details__match-form" action=<%= Paths.ADD_MATCH_SERVLET %> method="POST">
            <div class="details__match-form__team-container">
                <div class="details__match-form__team">
                    <div class="details__match-form__team__choose">
                        <img src=<%= Paths.ICONS_TEAM %> alt="Team A">
                        <select name="team-a" onchange="listTeamPlayers(this)">
                            <option value="" selected disabled hidden>Choose Team</option>
                            
							<% 
							ArrayList<Team> teamList = (ArrayList<Team>) request.getAttribute("teamList");
							for (Team team : teamList) {
								out.println("<option value='" + team.getTeamId() + "'>" + team.getTeamName() + "</option>");
							}
							%>

                        </select>
                    </div>
                    <div class="details__match-form__team__lineup">
                        Choose Line Up
                    </div>
                    <div class="details__match-form__modal">
                        <div class="details__match-form__modal__players-list-team-a">
                            <span class="close">&times;</span>
                        </div>            
                    </div>
                </div>
                <div class="details__match-form__team">
                    <div class="details__match-form__team__choose">
                        <img src=<%= Paths.ICONS_TEAM %> alt="Team B">
                        <select name="team-b" onchange="listTeamPlayers(this)">
                            <option value="" selected disabled hidden>Choose Team</option>
                            
                            <%
							for (Team team : teamList) {
								out.println("<option value='" + team.getTeamId() + "'>" + team.getTeamName() + "</option>");
							}
							%>
							
                        </select>
                    </div>
                    <div class="details__match-form__team__lineup">
                        Choose Line Up
                    </div>
                    <div class="details__match-form__modal">
                        <div class="details__match-form__modal__players-list-team-b">
                            <span class="close">&times;</span>
                        </div>            
                    </div>
                </div>
            </div>
            <div class="details__match-form__schedule">Match Date: <input class="calendar" type="date" name="date" placeholder="Date"/></div>
            <div class="details__match-form__schedule">Match Time: <input type="time" name="time" placeholder="Time"/></div>
            <div class="details__match-form__schedule">Match Venue: <input type="text" name="venue" placeholder="Enter Venue"/></div>
        	<input type="submit" class="confirm-details col-sm-12" value="Save Match"></input>
        </form>
    </section>
    <script src=<%= Paths.ADD_MATCH_SCRIPT %>></script>
</body>

</html>