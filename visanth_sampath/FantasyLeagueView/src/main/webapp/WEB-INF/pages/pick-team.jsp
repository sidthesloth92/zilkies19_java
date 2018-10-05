<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ page import=" io.zilker.fantasy.bean.Player , java.util.ArrayList " %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Pick team</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Allan' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value = '/resources/css/commonstylesheet.css '/>" />
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value = '/resources/css/pick-team-stylesheet.css '/>" />
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value = '/resources/css/leaderboardstylesheet.css '/>" />
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value = '/resources/css/stylesheet.css'/>" />
</head>
<body>
    <div class="header">
       <div class="topnav" id="myTopnav">
                <a href="PageRedirectionServlet?page-name=user-home">Home</a>
                <a href="PageRedirectionServlet?page-name=matches-upcoming">Pick Team</a>
                <a href="PageRedirectionServlet?page-name=available-matches">View Team</a>
                <a href="PageRedirectionServlet?page-name=leaderboard">LeaderBoard</a>
                <a href="PageRedirectionServlet?page-name=top-picks">Top picks</a>
                <a href="LogOutServlet">Logout</a>
                <a href="javascript:void(0);" class="icon" onclick="myFunction()">
                    <i class="fa fa-bars"></i>
                </a>
        </div>
    </div>
    <div class="container">
        <div class="flex-row">
            <div class="col-sm-12 col-md-6 col-lg-6 team-card_outer-container">
                  <!-- start of player card1 -->
                  
                  <div class="row">
                  <div class="col-3 remaining-display_batsman"></div>
                  <div class="col-3 remaining-display_bowler"></div>
                  <div class="col-3 remaining-display_all-rounder"></div>
                  <div class="col-3 remaining-display_wicket-keeper"></div>
                  </div>
                  <div class="remaining-credits centerize"></div>
                 
                  <div class="leaderboard-heading">Playing Team</div>
		                <div class="row table_heading">
		                    <div class="col-6">Name</div>
		                    <div class="col-3">Role</div>
		                    <div class="col-2">Rating</div>
		                    <div class="col-1"></div>
                         </div>
                         <div class="grow"></div>
                         <div class="row">
                         <div class="col-4"></div>
                         <div class="col-4">
                         <button type="button" class="players-submit_button centerize" onclick="submitTeam()">Submit</button>
                         </div>
                         <div class="col-4"></div>
                         </div>
                         <input type="text" name="match-credits" id="match-credits" value="<c:out value="${matchCredits}" />" hidden="true" />
                         <input type="text" name="match-id" id="match-id" value="<c:out value="${matchId}" />" hidden="true" />
                      
                   <!-- end of player card1 -->
            </div>
            <div class="col-sm-12 col-md-6 col-lg-6 team-card_outer-container">
                <!-- players starting -->
                <div class="leaderboard-heading">Squad</div>
                
                <!-- card generation dynamically -->
                <div class='team-title'>Team One:</div>
                 <div class="row">
                <c:set var = "counter" scope="page" value = "${0}"/>
                <c:forEach items="${teamOne}"  var="player">  
		                 	<!-- start of match card1 -->
		                 
		                    <div class="col-sm-4 col-md-4 col-lg-4  player-card  <c:out value="${player.getPlayerId()}" /> <c:out value="${player.getPlayerId()}" />-player">
		                   <div class="player-name"><c:out value="${player.getplayerName()}" /></div>
		                   <div class='player-type'><c:out value="${player.getplayerType()}" /></div>
		                   <div class='player-rating'><c:out value="${player.getplayerRating()}" /></div>
		                   <div class='player-button'><button value="<c:out value="${player.getPlayerId()}" />" class="<c:out value="${player.getPlayerId()}" /> <c:out value="${player.getPlayerId()}" />-add add-button" onclick="addPlayer()"> Add</button></div></div>
		                   <c:set var="counter" value="${counter + 1}" scope="page"/>
		                   <c:if test="${counter == 3 }">
			                   	</div><div class='row'>
			                   	<c:set var="counter" value="${0}" scope="page"/>
		                   </c:if>
		               <!-- end of match card1 -->
                 </c:forEach> 
                 </div>
                <div class='team-title'>Team Two:</div>
                <div class="row">
                <c:set var = "counter" scope="page" value = "${0}"/>
                <c:forEach items="${teamTwo}"  var="player">  
		                 	<!-- start of match card1 -->
		                 
		                    <div class="col-sm-4 col-md-4 col-lg-4  player-card  <c:out value="${player.getPlayerId()}" /> <c:out value="${player.getPlayerId()}" />-player">
		                   <div class="player-name"><c:out value="${player.getplayerName()}" /></div>
		                   <div class='player-type'><c:out value="${player.getplayerType()}" /></div>
		                   <div class='player-rating'><c:out value="${player.getplayerRating()}" /></div>
		                   <div class='player-button'><button value="<c:out value="${player.getPlayerId()}" />" class="<c:out value="${player.getPlayerId()}" /> <c:out value="${player.getPlayerId()}" />-add add-button" onclick="addPlayer()"> Add</button></div></div>
		                   <c:set var="counter" value="${counter + 1}" scope="page"/>
		                   <c:if test="${counter == 3 }">
		                   	</div><div class='row'>
		                   	<c:set var="counter" value="${0}" scope="page"/>
		                   </c:if>
		               <!-- end of match card1 -->
                 </c:forEach>  
                
                    </div>
                
                    </div>

                  
                <!-- players ending -->
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
    
    
        <!-- chat block -->    
 <div class="chat-container">
        <div class="chat-header" id="chat-header">CHAT</div>
        <div class="chat-body">
      
       <div class="messages_container"></div>
       
    </div>
    <div class="chat-footer">
        <input type="text" id="status" value="none" hidden/>
            <div class="row">
            <!-- <img class="send-icon" src="../images/smiley.svg" />     -->
            <div class="col-9">
                <input type="text" name="message" class="user-message" placeholder="Enter a Message">
            <!-- <input type="text" id="inputText"  name="message" placeholder="Enter a Message"> -->
        </div>
        <div class="col-3">
            <button class="submit-button" onclick="updateMessage()"><i class="fas fa-paper-plane"></i></button>
            </div>
        </div>
    </div>
</div>
<!--  chat block ending -->



	<script src="<c:url value = '/resources/js/chatboxscript.js '/>"></script>
    <script src="<c:url value = '/resources/js/pick-team-script.js '/>"></script>
    <script src="<c:url value = '/resources/js/navbarscript.js '/>"></script>
</body>
</html>