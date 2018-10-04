<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page import="io.zilker.fantasy.delegate.UserOperations , io.zilker.fantasy.bean.Player, io.zilker.fantasy.bean.User , javax.servlet.http.HttpServlet , java.util.ArrayList " %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>View Team</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/commonstylesheet.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/viewteamstylesheet.css" />
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
            <div class="col-sm-12 col-md-6 col-lg-4">
                
                
                <%
                //ArrayList<Player> playersList = request.getAttribute("PlayersList");
                
                %>
                <!-- batsman conatainer -->
                <div class="row">
                    <div class="col-sm-4 col-md-3 col-lg-3">
                        <img src="https://image.flaticon.com/icons/png/512/10/10552.png" class="icons" />
                    </div>
                    <div class="col-sm-7 col-md-8 col-lg-8 players-and-role_container batsman-container">
                        
                        <!-- <img src="" class="player-image"/> -->


                    </div>
                    <div class="col-sm-1 col-md-1 col-lg-1"> 
                    </div>
                </div>
            </div>


            <!-- end of batsman block  -->
            <div class="col-sm-12 col-md-6 col-lg-4">
<!-- bowlers block conatainer -->
<div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
            <img src="https://cdn.iconscout.com/public/images/icon/premium/png-512/ball-cricket-3abac1a65666129b-512x512.png" class="icons" />
        </div>
        <div class="col-sm-7 col-md-8 col-lg-8 players-and-role_container bowler-container">
            <div class="row">
                <div class="col-sm-5 col-md-5 col-lg-5">
                    <img src="https://www.thefamouspeople.com/profiles/images/m-s-dhoni-2.jpg" class="player-image"/>
                </div>
                <div class="col-sm-7 col-md-7 col-lg-7">
                    M S Dhoni
                </div>
            </div>
        
        </div>
        <div class="col-sm-1 col-md-1 col-lg-1">
        </div>
    </div>
            </div>
<!-- end of bowlers block conatainer -->
        
            <div class="col-sm-12 col-md-6 col-lg-4">
<!-- alll rounder block container -->
<div class="row">
        <div class="col-sm-4 col-md-3 col-lg-3">
            <img src="http://simpleicon.com/wp-content/uploads/bat_ball_2.png" class="icons" />
        </div>
        <div class="col-sm-7 col-md-8 col-lg-8 players-and-role_container all-rounder-container">
            
            <!-- <img src="" class="player-image"/> -->

           

        </div>
        <div class="col-sm-1 col-md-1 col-lg-1">
           
        </div>
    </div>
            </div>
<!-- end of bowlers block -->
<div class="col-sm-12 col-md-6 col-lg-12">
    <!-- wicket keeper main -->

<div class="row">
<div class="col-lg-4"></div>
<div class="col-lg-4">
        <div class="row">
                <div class="col-sm-4 col-md-3 col-lg-3">
                    <img src="https://cdn1.iconfinder.com/data/icons/cricket-sport/400/a_iconfinder_Iconography_-_Cricket-10-2-512.png" class="icons" />
                </div>
                <div class="col-sm-7 col-md-8 col-lg-8 players-and-role_container wicket-keeper-container">
                    
                    <!-- <img src="" class="player-image"/> -->
        
                </div>
                <div class="col-sm-1 col-md-1 col-lg-1">
                </div>
        </div>
</div>
<div class="col-lg-4"></div>
</div>


   
<!-- wicket keeper main end -->
</div>

        </div>

<!-- end of flex row container -->


<!-- to be removed -->
<div class="row">
    <div class="col-md-2 col-lg-2"></div>
    <div class="col-sm-12 col-md-8 col-lg-8">
        <!-- wicket keeper block -->
        



        <!-- end of wicket keeper block -->
    </div>
    <div class="col-md-2 col-lg-2"></div>
    </div>
<!-- to be removed -->
<!-- modify form -->
<div class="row"><div class="col-6"></div><div class="col-2">
<form action="http://127.0.0.1:8080/FantasyLeague/ModifyTeamServlet" action="get">
<input type="text" name="match-id" value="<% out.println(request.getParameter("match-id")); %>" hidden/>
<button type="submit" class="modify-team_button centerize">Modify team</button>
</form>
</div>
<div class="col-4"></div>
    </div>
    
     
    
    
    <div class="players-hidden_load">
    <c:forEach items="${playersList}" var="player">
     <div class="player-name"><c:out value = "${player.getplayerName()}"/></div>
     <div class="player-role"><c:out value = "${player.getplayerType()}"/></div>
     </c:forEach>
    </div>
    
    
    
    
    <div class="footer">
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
    <script src="${pageContext.request.contextPath}/js/navbarscript.js"></script>
    <script src="${pageContext.request.contextPath}/js/view-team-script.js"></script>
</body>
</html>