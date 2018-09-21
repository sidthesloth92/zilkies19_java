<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="io.zilker.fantasy.bean.User , java.util.ArrayList , io.zilker.fantasy.bean.Match" %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Matches Upcoming</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css ' />">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value = '/resources/css/commonstylesheet.css '/>" />
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value = '/resources/css/userhomestyle.css '/>" />
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value = '/resources/css/stylesheet.css '/>" />
</head>

<body>

    <!-- header -->
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

    <!-- end of header -->


    <div class="container">
        <div class="flex-row">
            <div class="col-sm-12 col-md-4 col-lg-4 left-image">
                <img src="http://cavancricket.com/admin/uploads/settings/values.png" alt="cricketing-icon">
            </div>
            <div class="col-sm-12 col-md-8 col-lg-8 upcomming-matches_container"> 
                <h3>Upcoming Matches:</h3>
                
                	
                	
                	<%
                	/*User user = (User)request.getAttribute("userObject");
                    //session.setAttribute("user", user);
                    */
                	%>
          
                 	<c:forEach items="${matchList}"  var="matches" varStatus="status">
                		<div class='match-card row'>
	                		<div class='col-sm-2 col-md-3 col-lg-3'>
		                		 <div class='team-logo'>
		         					<c:if test="${pickedStatus[status.index]== true}">
		         					<img src='https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Flat_tick_icon.svg/2000px-Flat_tick_icon.svg.png' alt='team-taken' />
		         					</c:if>
		         					<c:if test="${pickedStatus[status.index] == false}">
		         					<img src='https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Flat_cross_icon.svg/2000px-Flat_cross_icon.svg.png' alt='yet to be picked' />
		         					</c:if>
		                		 </div>
		                	</div>
              	
                    <div class='col-sm-8 col-md-6 col-lg-6'>
                        <h3 class='team-name'>
                        <c:out value="${matches.getTeamOne()} " /> vs 
                        <c:out value="${matches.getTeamTwo()} " />
						</h3>
                    </div> 
                    <div class='col-sm-2 col-md-3 col-lg-3 button-container'>
	                    <c:if test="${pickedStatus[status.index]== true}">
		         					<form action='ViewTeamServlet' method='get'>
		         					<input type='text' name='match-id' value='<c:out value="${matches.getMatchId()}" />' hidden />
		         					<button type='submit' class='team-name view-team_button'> View Team</button>
		         					</form>
		         					</c:if>
		         					<c:if test="${pickedStatus[status.index] == false}">
		         					<form action='PickTeamServlet' method='get'>
		         					<input type='text' name='match-id' value='<c:out value="${matches.getMatchId()}" />' hidden />
		         					<button type='submit' class='team-name pick-team_button'> Pick Team</button>
		         					</form>
		         					</c:if>
                    </div>
                    </div>
                		 
                	</c:forEach>
               
                	                    
		                                
             <!--    <a href="#">
                <div class="match-card row">
                    <div class="col-sm-2 col-md-3 col-lg-3">
                        <div class="team-logo">
                           <img src="../images/csklogo.png" alt="team1">
                        </div>
                    </div>
                    <div class="col-sm-8 col-md-6 col-lg-6">
                        <h3 class="team-name">team1 vs team2</h3>
                    </div>
                    <div class="col-sm-2 col-md-3 col-lg-3">
                      <!--   <img src="https://upload.wikimedia.org/wikipedia/en/thumb/8/81/Sunrisers_Hyderabad.svg/1200px-Sunrisers_Hyderabad.svg.png" class="team-logo" alt="team1">  
                    </div>
                </div>
            </a>
            <a href="#">
                <div class="match-card row">
                        <div class="col-sm-2 col-md-3 col-lg-3">
                            <img src="http://a2.espncdn.com/combiner/i?img=%2Fi%2Fteamlogos%2Fcricket%2F500%2F335975.png" class="team-logo" alt="team1">
                        </div>
                        <div class="col-sm-8 col-md-6 col-lg-6">
                            <h3 class="team-name">team1 vs team2</h3>
                        </div>
                        <div class="col-sm-2 col-md-3 col-lg-3">
                            <img src="https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Royal_Challengers_Bangalore_Logo_2016.svg/1200px-Royal_Challengers_Bangalore_Logo_2016.svg.png" class="team-logo" alt="team1">
                        </div>
                </div>
            </a>
            <a href="#">
                <div class="match-card row">
                        <div class="col-sm-2 col-md-3 col-lg-3">
                            <img src="../images/csklogo.png" class="team-logo" alt="team1">
                        </div>
                        <div class="col-sm-8 col-md-6 col-lg-6">
                            <h3 class="team-name">team1 vs team2</h3>
                        </div>
                        <div class="col-sm-2 col-md-3 col-lg-3">
                            <img src="../images/csklogo.png" class="team-logo" alt="team1">
                        </div>
                </div>
            </a>--> 
            </div>
        </div>
    </div>


    <!-- footer starting -->
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
    <!-- footer ending -->
    
    
    
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



	<script src="<c:url value = '/resources/js/chatboxscript.js' />"></script>
    <script src="<c:url value = '/resources/js/navbarscript.js' />"></script>
</body>

</html>