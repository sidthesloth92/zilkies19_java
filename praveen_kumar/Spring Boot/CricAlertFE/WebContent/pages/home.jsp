<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="io.ztech.cricalertfe.beans.Match"%>
<%@ page import ="io.ztech.cricalertfe.constants.Paths"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href=<%= Paths.GRID_CSS %>>
    <link rel="stylesheet" href=<%= Paths.MAIN_CSS %>>
    <link rel="stylesheet" href=<%= Paths.HOME_CSS %>>
    <link href="https://fonts.googleapis.com/css?family=Cuprum|Lato|Lobster|Lobster+Two|Pacifico" rel="stylesheet">
    <title>Home</title>
</head>

<body>
    <div class="main-container">
        <nav class="side-bar">
        	<img class="close-bar" src=<%= Paths.ICONS_CLOSE %> alt="close" onclick="sideBarClick()"/>
            <ul>
                <li><div onclick="window.location='<%= Paths.HOME_SERVLET %>'" class="nav-highlight-2">Home</div></li>
                <li><div onclick="window.location='<%= Paths.TEAMS_SERVLET %>'" class="">Teams</div></li>
                <li><div onclick="window.location='<%= Paths.PLAYERS_SERVLET %>'" class="">Players</div></li>
                <li><div onclick="window.location='<%= Paths.LOGIN_SERVLET %>'" class="">Logout</div></li>
            </ul>
        </nav>
        <header class="header">
            <div class="header__title-bar col-sm-12">
                <img class="header__title-bar__hamburger" src=<%= Paths.ICONS_MENU %> alt="Menu" onclick="sideBarClick()" />
                <h1 class="header__title-bar__title">CricAlert!</h1>
            </div>
            <nav class="header__nav-bar col-sm-12">
                <div onclick="window.location='<%= Paths.HOME_SERVLET %>'" class="nav-highlight">Home</div>
                <div onclick="window.location='<%= Paths.TEAMS_SERVLET %>'" class="">Teams</div>
                <div onclick="window.location='<%= Paths.PLAYERS_SERVLET %>'" class="">Players</div>
            </nav>
        </header>
        <section class="content">
            <div class="content__matches col-sm-12">
                <div class="content__matches__title">Live Matches</div>
                <div class="content__matches__live">
                	<%
                	ArrayList<Match> liveMatchList = (ArrayList<Match>) request.getAttribute("liveMatchList");
                	if (liveMatchList.size() == 0) {
                		out.println("<div class='content__matches__alert'>No live matches present at the moment</div>");
					}
                	for (Match match : liveMatchList) {
	                	String date = match.getMatchDatetime().toString().split(" ")[0];
	                	String time = match.getMatchDatetime().toString().split(" ")[1];
	                	time = time.split(":")[0] + ":" + time.split(":")[1]; 
	                	out.println("<div class='match-card' onclick=\"window.location='" + Paths.PAGES_PLAY + "?id=" + match.getMatchId() + "&type=live'\">"); //onclick=\"window.location='/CricAlert/MatchInfo?id=" + match.getMatchId() + "&type=live' \">"
	                		out.println("<div class='match-card__schedule'>");
	                			out.println("<div class='match-card__schedule__date'>" + date + "</div>");
	                			out.println("<div class='match-card__schedule__time'>" + time + "</div>");
	                		out.println("</div>");
	                		out.println("<div class='match-card__teams'>");
	                			out.println("<div class='match-card__teams__team-a'>");
	                				out.println("<div class='match-card__teams__team-a__logo'>");
	                					out.println("<img src='" + Paths.IMAGES + match.getTeamA().getTeamName() + ".png' alt='Team A'>");
	                				out.println("</div>");
	                				out.println("<div class='match-card__teams__team-a__score'>" + match.getMatchStats().getTeamAscore() + "-" + match.getMatchStats().getTeamAwickets() + "</div>");
	                			out.println("</div>");
	                			out.println("vs");
	                			out.println("<div class='match-card__teams__team-b'>");
		            				out.println("<div class='match-card__teams__team-b__logo'>");
		            					out.println("<img src='" + Paths.IMAGES + match.getTeamB().getTeamName() + ".png' alt='Team B'>");
		            				out.println("</div>");
		            				out.println("<div class='match-card__teams__team-b__score'>" + match.getMatchStats().getTeamBscore() + "-" + match.getMatchStats().getTeamBwickets() + "</div>");
		            			out.println("</div>");
		            		out.println("</div>");
		            		out.println("<div class='match-card__venue'>" + match.getVenue() + "</div>");
		            	out.println("</div>");
                	}
                	%>
                </div>
            </div>
            <div class="content__matches col-sm-12">
                <div class="content__matches__title">Upcoming Matches</div>
                <div class="content__matches__upcoming">
                    <%
                	ArrayList<Match> upcomingMatchList = (ArrayList<Match>) request.getAttribute("upcomingMatchList");
                    if (upcomingMatchList.size() == 0) {
                		out.println("<div class='content__matches__alert'>No new matches have been scheduled yet!</div>");
					}
                    for (Match match : upcomingMatchList) {
	                	String date = match.getMatchDatetime().toString().split(" ")[0];
	                	String time = match.getMatchDatetime().toString().split(" ")[1];
	                	time = time.split(":")[0] + ":" + time.split(":")[1]; 
	                	out.println("<div class='match-card' onclick=\"window.location='" + Paths.PAGES_PLAY + "?id=" + match.getMatchId() + "&type=upcoming'\">");
	                		out.println("<div class='match-card__schedule'>");
	                			out.println("<div class='match-card__schedule__date'>" + date + "</div>");
	                			out.println("<div class='match-card__schedule__time'>" + time + "</div>");
	                		out.println("</div>");
	                		out.println("<div class='match-card__teams'>");
	                			out.println("<div class='match-card__teams__team-a'>");
	                				out.println("<div class='match-card__teams__team-a__logo'>");
	                					out.println("<img src='" + Paths.IMAGES + match.getTeamA().getTeamName() + ".png' alt='Team A'>");
	                				out.println("</div>");
	                			out.println("</div>");
	                			out.println("vs");
	                			out.println("<div class='match-card__teams__team-b'>");
		            				out.println("<div class='match-card__teams__team-b__logo'>");
		            					out.println("<img src='" + Paths.IMAGES + match.getTeamB().getTeamName() + ".png' alt='Team B'>");
		            				out.println("</div>");
		            			out.println("</div>");
		            		out.println("</div>");
		            		out.println("<div class='match-card__venue'>" + match.getVenue() + "</div>");
		            	out.println("</div>");
                	}
                	%>
                </div>
            </div>
            <div class="content__matches col-sm-12">
                <div class="content__matches__title">Past Matches</div>
                <div class="content__matches__past">
                	<%
                	ArrayList<Match> pastMatchList = (ArrayList<Match>) request.getAttribute("pastMatchList");
                	if (pastMatchList.size() == 0) {
                		out.println("<div class='content__matches__alert'>No matches have been completed or ended yet!</div>");
					}
                	for (Match match : pastMatchList) {
	                	String date = match.getMatchDatetime().toString().split(" ")[0];
	                	String time = match.getMatchDatetime().toString().split(" ")[1];
	                	time = time.split(":")[0] + ":" + time.split(":")[1]; 
	                	out.println("<div class='match-card' onclick=\"window.location='" + Paths.PAGES_PLAY + "?id=" + match.getMatchId() + "&type=past'\">");
	                		out.println("<div class='match-card__schedule'>");
	                			out.println("<div class='match-card__schedule__date'>" + date + "</div>");
	                			out.println("<div class='match-card__schedule__time'>" + time + "</div>");
	                		out.println("</div>");
	                		out.println("<div class='match-card__teams'>");
	                			out.println("<div class='match-card__teams__team-a'>");
	                				out.println("<div class='match-card__teams__team-a__logo'>");
	                					out.println("<img src='" + Paths.IMAGES + match.getTeamA().getTeamName() + ".png' alt='Team A'>");
	                				out.println("</div>");
	                				out.println("<div class='match-card__teams__team-a__score'>" + match.getMatchStats().getTeamAscore() + "-" + match.getMatchStats().getTeamAwickets() + "</div>");
	                			out.println("</div>");
	                			out.println("vs");
	                			out.println("<div class='match-card__teams__team-b'>");
		            				out.println("<div class='match-card__teams__team-b__logo'>");
		            					out.println("<img src='" + Paths.IMAGES + match.getTeamB().getTeamName() + ".png' alt='Team B'>");
		            				out.println("</div>");
		            				out.println("<div class='match-card__teams__team-b__score'>" + match.getMatchStats().getTeamBscore() + "-" + match.getMatchStats().getTeamBwickets() + "</div>");
		            			out.println("</div>");
		            		out.println("</div>");
		            		out.println("<div class='match-card__venue'>" + match.getVenue() + "</div>");
		            	out.println("</div>");
                	}
                	%>
                </div>
            </div>
        </section>
        <footer class="footer">
            <div class="footer__add-item" onclick="window.location='/CricAlert/AddMatch'">
                <img class="footer__add-item__icon" src=<%= Paths.ICONS_PLUS_6 %> alt="Add Match">
            </div>
        </footer>
    </div>
    <script src=<%= Paths.MAIN_SCRIPT %>></script>
</body>

</html>