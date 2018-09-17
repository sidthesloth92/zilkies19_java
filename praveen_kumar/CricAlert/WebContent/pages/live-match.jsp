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
    <link rel="stylesheet" href="/CricAlert/css/match-info.css">
    <title>Match Info</title>
</head>
<body>
    <header>
        <img class="return-icon" src="/CricAlert/assets/icons/icons8-back-1.png" alt="return" onclick="window.location='/CricAlert/Home'"/>
        <img class="options-icon" src="/CricAlert/assets/icons/icons8-dots.png" alt="options"/>
    </header>
    <section class="details">
    	<% 
    	Match match = (Match) request.getAttribute("match");
    	Timestamp ts = match.getMatchDatetime();
    	String date = ts.toString().split(" ")[0];
    	String time = ts.toString().split(" ")[1].split(":")[0] + ":" + ts.toString().split(" ")[1].split(":")[1];
    	%>
        <div class="details__teams col-sm-12">
            <div class="details__teams__card col-sm-6"></div>
            <div class="details__teams__card col-sm-6"></div>
            <div class="details__team__score col-sm-6">102-8</div>
            <div class="details__team__score col-sm-6">169-10</div>
        </div>
        <h2 class="details__stats-title">Match Stats</h2><hr>
        <div class="details__stats"></div>
    </section>
    <%= "<footer class='track-match col-sm-12' onclick=\"window.location='/CricAlert/pages/play.jsp?id=" + match.getMatchId() + "&type=live'\">" %>
        Track Match
    </footer>
</body>
</html>