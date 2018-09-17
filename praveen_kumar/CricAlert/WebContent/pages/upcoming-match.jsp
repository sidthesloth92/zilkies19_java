<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="io.ztech.cricalert.beans.Team"%>
<%@ page import ="io.ztech.cricalert.beans.Match"%>
<%@ page import ="java.sql.Timestamp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/CricAlert/css/grid-common.css">
    <link rel="stylesheet" href="/CricAlert/css/upcoming-match.css">
    <link rel="stylesheet" href="/CricAlert/css/teamstyle.css">
    <title>Match Info</title>
</head>

<body>
    <header>
        <img class="return-icon" src="/CricAlert/assets/icons/icons8-back-1.png" alt="return" onclick="window.location='/CricAlert/Home'"/>
        <img class="options-icon" src="/CricAlert/assets/icons/icons8-dots.png" alt="options" />
    </header>
    <section class="content">
    	<% 
    	Match match = (Match) request.getAttribute("match");
    	Timestamp ts = match.getMatchDatetime();
    	String date = ts.toString().split(" ")[0];
    	String time = ts.toString().split(" ")[1].split(":")[0] + ":" + ts.toString().split(" ")[1].split(":")[1];
    	%>
        <h3 class="content__schedule">Match Date: <%= ts.toGMTString() + " " + time %>hrs</h3>
	        <div class="content__teams col-sm-12">
            
            <%
            
            Team teamA = match.getTeamA();
            
            out.println("<div id='" + teamA.getTeamId() + "' class='content__team-card col-sm-12'>");
			out.println("<div class='content__team-card__container-1'>");
			out.println("<img class='content__team-card__container-1__photo' draggable='false' src='/CricAlert/assets/icons/icons8-team.png' alt='Photo' />");
			out.println("<h3 class='content__team-card__container-1__name'>" + teamA.getTeamName() + "</h3>");
			out.println("</div>");
			out.println("<div class='content__team-card__container-2'></div>");
			out.println("</div>");
            %>
            
            <div class="content__teams__line-up-container col-sm-12">
                Match Line Up
                <hr>
                <div class="content__teams__line-up"></div>
            </div>
            <div class="content__teams__card col-sm-12"></div>
            <div class="content__teams__line-up-container col-sm-12">
                Match Line Up
                <hr>
                <div class="content__teams__line-up"></div>
            </div>
        </div>
    </section>
    <%= "<footer class='track-match col-sm-12' onclick=\"window.location='/CricAlert/pages/play.jsp?id=" + match.getMatchId() + "&type=upcoming'\">" %>
        Track Match
    </footer>
</body>

</html>