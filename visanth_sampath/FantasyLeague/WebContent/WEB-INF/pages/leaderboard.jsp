<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="io.zilker.fantasy.bean.User , io.zilker.fantasy.bean.Match ,io.zilker.fantasy.bean.ResultBoard , java.util.ArrayList , io.zilker.fantasy.delegate.UserOperations" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>LeaderBoard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/commonstylesheet.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/toppicksstylesheet.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/leaderboardstylesheet.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/stylesheet.css" />
</head>
<body>
    <div class="header">
        <div class="topnav" id="myTopnav">
                <a href="http://127.0.0.1:8080/FantasyLeague/PageRedirectionServlet?page-name=user-home">Home</a>
                <a href="http://127.0.0.1:8080/FantasyLeague/PageRedirectionServlet?page-name=matches-upcoming">Pick Team</a>
                <a href="http://127.0.0.1:8080/FantasyLeague/PageRedirectionServlet?page-name=available-matches">View Team</a>
                <a href="http://127.0.0.1:8080/FantasyLeague/PageRedirectionServlet?page-name=leaderboard">LeaderBoard</a>
                <a href="http://127.0.0.1:8080/FantasyLeague/PageRedirectionServlet?page-name=top-picks">Top picks</a>
                <a href="http://127.0.0.1:8080/FantasyLeague/LogOutServlet">Logout</a>
                <a href="javascript:void(0);" class="icon" onclick="myFunction()">
                    <i class="fa fa-bars"></i>
                </a>
        </div>
    </div>
    <div class="container">
        <div class="flex-row">
            <div class="col-sm-12 col-md-6 col-lg-6 team-card_outer-container">
            
            <%
             ArrayList<Match> matchList = new UserOperations().displayCompletedMatches();
             request.setAttribute("matchList", matchList);
             %>
             <c:forEach items="${matchList}"  var="match">
             	<!-- start of match card1 -->
                        <div class="match-card row">
                                <div class="col-sm-2 col-md-3 col-lg-3">
                                   <!--  <img src="../images/csklogo.png" class="team-logo" alt="team1">  -->
                                </div>
                                <div class="col-sm-8 col-md-6 col-lg-6">
		                                <button class="team-names" value="<c:out value="${match.getMatchId()}" />" onclick="updateLeaderBoard()"><h3 class="team-name"> <c:out value="${match.getTeamOne()}" /> vs <c:out value="${match.getTeamTwo()}" /></h3></button>
                                </div>
                                <div class="col-sm-2 col-md-3 col-lg-3">
                                   <!--  <img src="https://upload.wikimedia.org/wikipedia/en/thumb/8/81/Sunrisers_Hyderabad.svg/1200px-Sunrisers_Hyderabad.svg.png" class="team-logo" alt="team1">  -->
                                </div>
                            </div>
                        
                   <!-- end of match card1 -->
             </c:forEach>
            
                 
            </div>
            <div class="col-sm-12 col-md-6 col-lg-6 leaderboard-outer_container">
                <!-- LeaderBoard starting -->
                <div class="leaderboard-heading">Leaderboard</div>
                <div class="row">
                    <div class="col-2">#</div>
                    <div class="col-6">User Name</div>
                    <div class="col-4">Points</div>
                </div>
                
                <div class="records-block">Please select any match!</div>
                
                <%
            /*    User user = (User)session.getAttribute("user");
                ResultBoard result = new UserService().viewLeaderBoardCaller(user , 11);
                ArrayList<Integer> users = result.getUsers();
            	ArrayList<Integer> matchPoints = result.getMatchPoints();
            	ArrayList<String> names = result.getNames();
            	for(int i=0;i< users.size();i++){
            		out.print("<div class='row'>"+
            	    "<div class='col-2'>"+ (i+1) +"</div> "+
            		"<div class='col-6'>"+names.get(i)+"</div>"+
            	    "<div class='col-4'>"+ matchPoints.get(i)+"</div>"+
            		"</div>");
            		//out.println(names.get(i));
            	}*/
                %>
                <!-- LeaderBoard ending -->
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




	<script src="${pageContext.request.contextPath}/js/chatboxscript.js"></script>    
    <script src="${pageContext.request.contextPath}/js/leaderboard-script.js"></script>
    <script src="${pageContext.request.contextPath}/js/navbarscript.js"></script>
</body>
</html>