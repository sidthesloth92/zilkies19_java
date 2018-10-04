<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="io.ztech.cricalertfe.beans.PlayerStats"%>
<%@ page import ="io.ztech.cricalertfe.beans.Match"%>
<%@ page import ="io.ztech.cricalertfe.beans.MatchStats"%>
<%@ page import ="io.ztech.cricalertfe.constants.Paths"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.HashMap"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href=<%= Paths.SCORECARD_CSS %>>
    <link rel="stylesheet" href=<%= Paths.GRID_CSS %>>
    <title>Play Match</title>
</head>

<body>
	<% 
	Match match = (Match) request.getAttribute("match");
	MatchStats matchStats = (MatchStats) request.getAttribute("matchStats");
	%>
    <header class="header">
        <div class="header__title-bar col-sm-12">
            <%= "<img class='header__title-bar__return' src=" + Paths.ICONS_BACK + " alt='return' onclick=\"window.location='" + Paths.PAGES_PLAY + "?id=" + match.getMatchId() + "&type=live'\" />" %>
            <h1 class="header__title-bar__title"><%= match.getTeamA().getAbbreviation() + " vs " + match.getTeamB().getAbbreviation() %></h1>
        </div>
        <nav class="header__nav-bar col-sm-12">
            <a href="<%= Paths.PAGES_PLAY + "?id=" + match.getMatchId() + "&type=live" %>" class="">Live</a>
            <a href="#" class="nav-highlight">Scorecard</a>
            <a href="#" class="">Overs</a>
        </nav>
    </header>
    <section class="team-a" onclick="expandTeam(this)">
        <div class="team-a__container-1">
            <div class="team-a__container-1__name"><%= match.getTeamA().getTeamName() %></div>
            <div class="team-a__container-1__score">
            <%
            if (match.getTeamA().getTeamId() == matchStats.getBattingTeam().getTeamId() || matchStats.getInning() == 1) {
            	out.println(matchStats.getTeamAscore() + " - " + matchStats.getTeamAwickets());
            }
            %>
            </div>
        </div>
        <div class="team-a__container-2">
            <table class="team-a__container-2__batsmen">
                <%
                HashMap<Integer, String> playerMapA = (HashMap<Integer, String>) request.getAttribute("playerMapA");
                HashMap<Integer, String> playerMapB = (HashMap<Integer, String>) request.getAttribute("playerMapB");
                ArrayList<PlayerStats> playerStatsList = (ArrayList<PlayerStats>) request.getAttribute("playerStatsList");
                int iterationFlag = 0;
                for (PlayerStats playerStats : playerStatsList) {
                	if (playerStats.getTeamId() == match.getTeamA().getTeamId() && playerStats.getBatFlag()) {
                		if (iterationFlag == 0) {
                			out.println("<tr><th>Batsman</th><th>R</th><th>B</th><th>4s</th><th>6s</th><th>SR</th></tr>");
                			iterationFlag = 1;
                		}
                		out.println("<tr>");
                			out.println("<td>" + playerMapA.get((Integer) playerStats.getPlayerId()) + "</td>");
                			out.println("<td>" + playerStats.getRunsScored() + "</td>");
                			out.println("<td>" + playerStats.getBallsFaced() + "</td>");
                			out.println("<td>" + playerStats.getFours() + "</td>");
                			out.println("<td>" + playerStats.getSixes() + "</td>");
                			out.println("<td>" + playerStats.getStrikeRate() + "</td>");
                		out.println("</tr>");
                	}
                }
                %>
            </table>
            <table class="team-a__container-2__bowlers">
                <%
                for (PlayerStats playerStats : playerStatsList) {
                	if (playerStats.getTeamId() == match.getTeamA().getTeamId() && playerStats.getBowlFlag()) {
                		if (iterationFlag == 0) {
                			out.println("<tr><th>Bowler</th><th>O</th><th>M</th><th>R</th><th>W</th><th>ER</th></tr>");
                			iterationFlag = 1;
                		}
                		out.println("<tr>");
                			out.println("<td>" + playerMapA.get((Integer) playerStats.getPlayerId()) + "</td>");
                			out.println("<td>" + playerStats.getOvers() + "</td>");
                			out.println("<td>0</td>");
                			out.println("<td>" + playerStats.getRunsGiven() + "</td>");
                			out.println("<td>" + playerStats.getWicketsTaken() + "</td>");
                			out.println("<td>" + playerStats.getEconomy() + "</td>");
                		out.println("</tr>");
                	}
                }
                %>
            </table>
        </div>
    </section>
    <section class="team-b" onclick="expandTeam(this)">
        <div class="team-b__container-1">
            <div class="team-b__container-1__name"><%= match.getTeamB().getTeamName() %></div>
            <div class="team-b__container-1__score">
            <%
            if (match.getTeamB().getTeamId() == matchStats.getBattingTeam().getTeamId() || matchStats.getInning() == 1) {
            	out.println(matchStats.getTeamBscore() + " - " + matchStats.getTeamBwickets());
            }
            %>
			</div>
        </div>
        <div class="team-b__container-2">
            <table class="team-b__container-2__batsmen">
                <%
                iterationFlag = 0;
                for (PlayerStats playerStats : playerStatsList) {
                	if (playerStats.getTeamId() == match.getTeamB().getTeamId() && playerStats.getBatFlag()) {
                		if (iterationFlag == 0) {
                			out.println("<tr><th>Batsman</th><th>R</th><th>B</th><th>4s</th><th>6s</th><th>SR</th></tr>");
                			iterationFlag = 1;
                		}
                		out.println("<tr>");
                			out.println("<td>" + playerMapB.get((Integer) playerStats.getPlayerId()) + "</td>");
                			out.println("<td>" + playerStats.getRunsScored() + "</td>");
                			out.println("<td>" + playerStats.getBallsFaced() + "</td>");
                			out.println("<td>" + playerStats.getFours() + "</td>");
                			out.println("<td>" + playerStats.getSixes() + "</td>");
                			out.println("<td>" + playerStats.getStrikeRate() + "</td>");
                		out.println("</tr>");
                	}
                }
                %>
            </table>
            <table class="team-b__container-2__bowlers">
                <%
                for (PlayerStats playerStats : playerStatsList) {
                	iterationFlag = 0;
                	if (playerStats.getTeamId() == match.getTeamB().getTeamId() && playerStats.getBowlFlag()) {
                		if (iterationFlag == 0) {
                			out.println("<tr><th>Bowler</th><th>O</th><th>M</th><th>R</th><th>W</th><th>ER</th></tr>");
                			iterationFlag = 1;
                		}
                		out.println("<tr>");
                			out.println("<td>" + playerMapA.get((Integer) playerStats.getPlayerId()) + "</td>");
                			out.println("<td>" + playerStats.getOvers() + "</td>");
                			out.println("<td>0</td>");
                			out.println("<td>" + playerStats.getRunsGiven() + "</td>");
                			out.println("<td>" + playerStats.getWicketsTaken() + "</td>");
                			out.println("<td>" + playerStats.getEconomy() + "</td>");
                		out.println("</tr>");
                	}
                }
                %>
                
            </table>
        </div>
    </section>
    <script src=<%= Paths.SCORECARD_SCRIPT %>></script>
</body>

</html>