<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="io.ztech.cricalert.beans.Team"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/CricAlert/css/grid-common.css">
    <link rel="stylesheet" href="/CricAlert/css/add-match.css">
    <title>Create Match</title>
</head>

<body>
    <header>
        <img class="return-icon" src="/CricAlert/assets/icons/icons8-back-1.png" alt="return" onclick="window.location='/CricAlert/pages/home.jsp'" />
        <img class="options-icon" src="/CricAlert/assets/icons/icons8-dots.png" alt="options" />
    </header>
    <section class="details">
<form class="details__match-form" action="#">
            <div class="details__match-form__team-container">
                <div class="details__match-form__team">
                    <div class="details__match-form__team__choose">
                        <img src="/CricAlert/assets/icons/icons8-team.png" alt="Team A">
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
                        <img src="/CricAlert/assets/icons/icons8-team.png" alt="Team B">
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
            <div class="details__match-form__schedule">Match Date: <input type="date" name="date" placeholder="Date"/></div>
            <div class="details__match-form__schedule">Match Time: <input type="time" name="time" placeholder="Time"/></div>
            <div class="details__match-form__schedule">Match Venue: <input type="text" name="venue" placeholder="Enter Venue"/></div>
        </form>
    </section>
    <footer class="confirm-details col-sm-12">
        Save Match
    </footer>
    <script src="/CricAlert/js/add-match.js"></script>
</body>

</html>