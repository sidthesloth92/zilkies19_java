<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ page import=" java.util.ArrayList " %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Edit Player Rating</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value = '/resources/css/commonstylesheet.css ' />" />
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value = '/resources/css/addmatchstylesheet.css ' />" />
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value = '/resources/css/editratingstylesheet.css ' />" />
</head>
<body>
    <div class="header">
        <div class="topnav" id="myTopnav">
                <a href="PageRedirectionServlet?page-name=add-match">Add Match</a>
                <a href="PageRedirectionServlet?page-name=end-match">End Match</a>
                <a href="PageRedirectionServlet?page-name=add-player">Add Player</a>
                <a href="PageRedirectionServlet?page-name=edit-rating">Edit Player Rating</a>
                <a href="LogOutServlet">Logout</a>
                <a href="javascript:void(0);" class="icon" onclick="myFunction()">
                    <i class="fa fa-bars"></i>
                </a>
        </div>
    </div>
    <div class="container">
        <div class="flex-row">
            <div class="col-sm-0 col-md-4 col-lg-4">
                <img src="https://static.thenounproject.com/png/13117-200.png" alt="stump-falling">
            </div>
            <div class="col-sm-12 col-md-8 col-lg-8">
                <div class="centerize rating-block"> Edit player Rating </div>
                <form name="player-name_form" class="player-name_form">
                        <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10">
                                <label for="player-name">Select the Player Name:</label>
                                        <select name="player-name" title="Player-Name">
                                               <% 
                                    			//	ArrayList<String> playerNames = new AdminOperations().getPlayerNames();
                                    			//	request.setAttribute("playerNames", playerNames);
                                    			//	ArrayList<String> playerIds = new AdminOperations().getPlayerIds();
                                    			//	request.setAttribute("playerIds", playerIds);
                                    			%>
                                    	 		<c:forEach items="${playerNames}" var="playerName" varStatus="status">
                                    				<option value="<c:out value="${playerIds[status.index]}"/>"><c:out value="${playerName}"/></option>
                               					</c:forEach> 
                                        </select>
                                </div>
                                <div class="col-1"></div>
                        </div>

                        <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10 centerize">
                                        <button type="button" class="submit-button get-rating_buttton" onclick="getRating()" >Get Rating</button>
                                </div>
                                <div class="col-1"></div>
                        </div>
                </form>
                
                <!-- hidden block starting -->
                <div class="player-rating_updation_block">
                <div class="centerize rating-block current-rating"></div>
                <!-- updation block starting -->
                <form name="updated-rating_form" class="updated-rating_form">
                        <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10">
                                <label for="new-rating">Select the Updated Rating:</label>
                                        <select name="new-rating" title="Updated-rating">
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

                        <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10 centerize">
                                        <button type="button" class="submit-button update-rating_button" onclick="updateRating()">Update Rating</button>
                                </div>
                                <div class="col-1"></div>
                        </div>
                </form>
                <!-- updation block ending -->
                </div>
                <!-- hidden block ending -->
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
    <script src="<c:url value = '/resources/js/editrating-script.js' />"></script>
    <script src="<c:url value = '/resources/js/navbarscript.js ' />"></script>
</body>
</html>