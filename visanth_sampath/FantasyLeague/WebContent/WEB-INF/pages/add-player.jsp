<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ page import="io.zilker.fantasy.delegate.AdminOperations , io.zilker.fantasy.bean.Match , java.util.ArrayList " %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Add Player</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Allan' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/commonstylesheet.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/addplayerstylesheet.css" />
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
            <div class="col-sm-0 col-md-4 col-lg-4 left image">
                <img src="https://s3.amazonaws.com/peoplepng/wp-content/uploads/2018/06/12145416/Cricket-PNG-File.png" alt="">
            </div>
            <div class="col-sm-12 col-md-8 col-lg-8">
                <!-- add player block -->
                <div class="centerize add-player_block">Add new Player</div>
                <form method="post" name="add-player_form">
                    <!-- player name starting  -->
                    <div class="row">
                            <div class="col-1"></div>
                            <div class="col-10">
                            <label for="player-name">Player Name:</label>
                                <input type="text" name="player-name" class="player-name" onchange="checkPlayerName()" placeholder="Enter the player name" required/>
                                <div class="name-error"></div>
                            </div>
                            <div class="col-1"></div>
                    </div>
                    <!-- player team starting -->
                    <div class="row">
                            <div class="col-1"></div>
                            <div class="col-10">
                            <label for="team-name">Team Name:</label>
                                <select name="team-name" onchange = "addNewTeam()">
                                <% 
                                    ArrayList<String> teamNames = new AdminOperations().getTeamNames();
                                    request.setAttribute("teamNames", teamNames);
                                    %>
                                    <c:forEach items="${teamNames}" var="teamName" >
                                    	<option value="<c:out value="${teamName}"/>"><c:out value="${teamName}"/></option>
                                    </c:forEach>
                                    <option value="others">Others</option>
                                </select>
                            </div>
                            <div class="col-1"></div>
                    </div>
                    <!-- new team block in case of others -->
                    <div class="row">
                            <div class="col-1"></div>
                            <div class="col-10 new-team">
                            <label for="new-team_name">Enter Team Name:</label>
                                <input type="text" name="new-team_name" placeholder="Enter the team name" required/>
                            </div>
                            <div class="col-1"></div>
                    </div>
                    <!-- player role block into categories -->
                    <div class="row">
                            <div class="col-1"></div>
                            <div class="col-10">
                            <label for="player-category">Player Role:</label>
                                <select name="player-category">
                                    <option value="Batsman">Batsman</option>
                                    <option value="Bowler">Bowler</option>
                                    <option value="All Rounder">All Rounder</option>
                                    <option value="Wicket Keeper">Wicket Keeper</option>
                                </select>
                                
                            </div>
                            <div class="col-1"></div>
                    </div>
                    <!-- player rating -->
                    <div class="row">
                            <div class="col-1"></div>
                            <div class="col-10">
                            <label for="player-rating">Player Rating:</label>
                                <select name="player-rating">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                </select>
                            </div>
                            <div class="col-1"></div>
                    </div>
                    <!-- submit  button block -->
                    <div class="row">
                            <div class="col-1"></div>
                            <div class="col-10 centerize">
                                <button type="button" onclick="addPlayerRequest()" class="submit-button centerize">Add Player</button>
                            </div>
                            <div class="col-1"></div>
                    </div>
                </form>
                <!-- end of add player block -->
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
    <script src="${pageContext.request.contextPath}/js/addplayer-script.js"></script>
    <script src="${pageContext.request.contextPath}/js/navbarscript.js"></script>
</body>
</html>