<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ page import="io.zilker.fantasy.delegate.AdminOperations" %>
    <%@ page import="java.util.*" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Add Match</title>
    <link href='https://fonts.googleapis.com/css?family=Allan' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/commonstylesheet.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/addmatchstylesheet.css" />
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
            <div class="col-sm-0 col-md-4 col-lg-4 left-image">
                <img src="https://cdn2.iconfinder.com/data/icons/cricket-1/117/cricket-game-player-playing-007-512.png" alt="1 vs 1">
                    <!-- https://cdn2.iconfinder.com/data/icons/cricket-1/117/cricket-game-player-playing-007-512.png -->
            </div>
            <div class="col-sm-12 col-md-8 col-lg-8">
                    <!-- centrize the form elements -->
                    <div class="row">
                        <div class="col-1"></div>
                        <div class="col-10 centerize add-match_topic">
                            Add new Match
                        </div>
                        <div class="col-1"></div>
                    </div>
                    <!-- form details -->
                    <form action="#" method="POST" name="add-match_form">
                        <!-- team 1 block -->
                        <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10">
                                <label for="team-one">Team one:</label>
                                    <select name="team-one" class="team-one" onchange="checkTeamNames()">
                                    <% 
                                    ArrayList<String> teamNames = new AdminOperations().getTeamNames();
                                    request.setAttribute("teamNames", teamNames);
                                    %>
                                    <c:forEach items="${teamNames}" var="teamName" >
                                    	<option value="<c:out value="${teamName}"/>"><c:out value="${teamName}"/></option>
                                    </c:forEach>
                                        
                                    </select>
                                </div>
                                <div class="col-1"></div>
                        </div>
                        <!-- team 2 block -->
                        <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10">
                                <label for="team-two">Team Two:</label>
                                        <select name="team-two" class="team-two" onchange="checkTeamNames()">
                                                <c:forEach items="${teamNames}" var="teamName" >
                                    				<option value="<c:out value="${teamName}"/>"><c:out value="${teamName}"/></option>
                                    			</c:forEach>
                                        </select>
                                        <div class="name-error"></div>
                                </div>
                                <div class="col-1"></div>
                        </div>
                        
                        <!-- date block -->
                        <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10">
                                <label for="match-date">Match Date:</label>
                                    <input type="date" name="match-date" class="match-date" onchange="checkDate()" placeholder="Select the date">
                                    <div class="date-error"></div>
                                </div>
                                <div class="col-1"></div>
                        </div>
                        <!-- time block -->
                        <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10">
                                <label for="start-time">Starting Time:</label>
                                    <input type="time" name="start-time" class="start-time" onchange="checkTime()" placeholder="Enter the Starting time">
                                </div>
                                <div class="col-1"></div>
                        </div>
                        
                        <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10">
                                <label for="end-time">Ending Time:</label>
                                    <input type="time" name="end-time" class="end-time" onchange="checkTime()" placeholder="Enter the Ending time" />
                                    <div class="time-error"></div>
                                </div>
                                <div class="col-1"></div>
                        </div>
                        <!-- creadit block -->
                        <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10">
                                <label for="credits">Credits:</label>
                                        <input type="text" name="credits" placeholder="Enter the credits">
                                </div>
                                <div class="col-1"></div>
                        </div>
                        <!-- credit block end -->
                        <!-- submit button -->
                        <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10 centerize">
                                    <button type="button" onclick="evaluateTeam()" class="submit-button">Submit</button>
                                </div>
                                <div class="col-1"></div>
                            </div>
                    </form>
                    <!-- form end -->
                    <!-- centrize block end-->
            </div>
        </div>
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
    <script src="${pageContext.request.contextPath}/js/addmatch-script.js"></script>
    <script src="${pageContext.request.contextPath}/js/navbarscript.js"></script>
</body>
</html>