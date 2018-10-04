<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ page import="io.zilker.fantasy.delegate.AdminOperations , io.zilker.fantasy.bean.Player , java.util.ArrayList " %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Pick Team</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/commonstylesheet.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/pick-team-stylesheet.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/leaderboardstylesheet.css" />
</head>
<body>
    <div class="header">
        <div class="topnav" id="myTopnav">
                <a href="http://127.0.0.1:8080/FantasyLeague/PageRedirectionServlet?page-name=add-match">Add Match</a>
                <a href="http://127.0.0.1:8080/FantasyLeague/PageRedirectionServlet?page-name=end-match">End Match</a>
                <a href="http://127.0.0.1:8080/FantasyLeague/PageRedirectionServlet?page-name=add-player">Add Player</a>
                <a href="http://127.0.0.1:8080/FantasyLeague/PageRedirectionServlet?page-name=edit-rating">Edit Player Rating</a>
                <a href="http://127.0.0.1:8080/FantasyLeague/LogOutServlet">Logout</a>
                <a href="javascript:void(0);" class="icon" onclick="myFunction()">
                    <i class="fa fa-bars"></i>
                </a>
        </div>
    </div>
    <div class="container">
        <div class="flex-row">
            <div class="col-sm-12 col-md-6 col-lg-6 team-card_outer-container">
                  <!-- start of player card1 -->
                  <div class="leaderboard-heading">Playing Team</div>
		                <div class="row">
		                    <div class="col-6">Name</div>
		                    <div class="col-3">Role</div>
		                    <div class="col-2">Rating</div>
		                    <div class="col-1"></div>
                         </div>
                         <div class="grow"></div>
                         <div class="row">
                         <div class="col-4"></div>
                         <div class="col-4">
                         <button type="button" class="players-submit_button centerize" onclick="updatePlayingTeam()">Submit</button>
                         </div>
                         <div class="col-4"></div>
                         </div>
                         <input type="text" name="match-id" id="match-id" value="<% int matchId = (int)request.getAttribute("matchId");  out.println(matchId);%>" hidden />
                      
                   <!-- end of player card1 -->
            </div>
            <div class="col-sm-12 col-md-6 col-lg-6 team-card_outer-container">
                <!-- players starting -->
                <div class="leaderboard-heading">Squad</div>
                
                <!-- card generation dynamically -->
                <div class='team-title'>Team One:</div>
                 <div class="row">
                <% 
                //matchId = (int)request.getAttribute("matchId");
                //out.println(matcd);
                int counter = 0;
                ArrayList<Player> teamOne = new AdminOperations().getTeamOne(matchId);
                for(int i=0;i< teamOne.size();i++){
                	out.println("<div class='col-sm-4 col-md-4 col-lg-4  player-card "+teamOne.get(i).getPlayerId()+" "+teamOne.get(i).getPlayerId()+"-player'> "+
                "<div class='player-name'>" +
                		teamOne.get(i).getplayerName() +
                "</div>" +
                "<div class='player-type'>" +
                teamOne.get(i).getplayerType()+
                "</div>" +
                "<div class='player-rating'>" +
				teamOne.get(i).getplayerRating()	+
                "</div>"+
                "<div class='player-button'>"
                +"<button value='"+teamOne.get(i).getPlayerId() +"' class='"+teamOne.get(i).getPlayerId()+" "+teamOne.get(i).getPlayerId()+"-add add-button' onclick='addPlayer()'>Add</button>"
                +"</div></div>");
                counter++;
                if(counter == 3){
                	out.print("</div><div class='row'>");
                	counter = 0;
                }
                }
                out.print("</div>");
                counter=0;
                out.println("<div class='team-title'>Team Two:</div>");
                out.print("<div class='row'>");
                
                //team 2
                ArrayList<Player> teamTwo = new AdminOperations().getTeamTwo(matchId);
                for(int i=0;i< teamTwo.size();i++){
                	out.println("<div class='col-sm-4 col-md-4 col-lg-4  player-card "+teamTwo.get(i).getPlayerId()+" "+teamTwo.get(i).getPlayerId()+"-player'> "+
                "<div class='player-name'>" +
                		teamTwo.get(i).getplayerName() +
                "</div>" +
                "<div class='player-type'>" +
                		teamTwo.get(i).getplayerType()+
                "</div>" +
                "<div class='player-rating'>" +
                		teamTwo.get(i).getplayerRating()	+
                "</div>"+
                "<div class='player-button'>"
                +"<button value='"+teamTwo.get(i).getPlayerId() +"' class='"+teamTwo.get(i).getPlayerId()+" "+teamTwo.get(i).getPlayerId()+"-add add-button' onclick='addPlayer()'>Add</button>"
                +"</div></div>");
                counter++;
                if(counter == 3){
                	out.print("</div><div class='row'>");
                	counter = 0;
                }
                }
                %>
                
                
                <c:forEach items="${teamOne}"  var="player">  
		                 	<!-- start of match card1 -->
		                 
		                    <div class="col-sm-4 col-md-4 col-lg-4  player-card "> 
                        <div class="player-image">
                            <img src="../images/csklogo.png" alt="team1">
                        </div>
                        <div class="player-name">
                            <c:out value="${player.getplayerName()}" />
                        </div>
                        <div class="player-name">
                                <c:out value="${player.getPlayerType()}" />
                        </div>
                        <div class="player-name">
                                <c:out value="${player.getPlayerRating()}" />
                        </div>
                     </div>
		                   
		               <!-- end of match card1 -->
                 </c:forEach>  
                
                
                
                
                
                
                
                
                
                
                
                
               
                
                    </div>

                  
                <!-- players ending -->
            </div>
        </div>
    </div>
    <div class="footer">
        <!-- footer starting -->
        <div class="row">
                <div class="col-md-2 col-lg-2"></div>
                <div class="col-sm-12 col-md-8 col-lg-8 footer-content">
                    <ul class="footer-list">
                        <li> <a href="#">About Us</a></li>
                        <li><a href="#"> How to Play</a></li>
                        <li><a href="#">Contact Us</a></li>
                    </ul>
                    <h4>&copy; Zilker Technology,chennai</h4>
                </div>
                <div class="col-md-2 col-lg-2"></div>
            </div>
        <!-- footer ending -->
    </div>
    <script src="${pageContext.request.contextPath}/js/enter-players-script.js"></script>
    <script src="${pageContext.request.contextPath}/js/navbarscript.js"></script>
</body>
</html>