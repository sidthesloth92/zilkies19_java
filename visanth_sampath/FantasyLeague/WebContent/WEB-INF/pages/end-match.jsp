<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ page import="io.zilker.fantasy.delegate.AdminOperations , io.zilker.fantasy.bean.Match , java.util.ArrayList " %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>End Match</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/commonstylesheet.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/toppicksstylesheet.css" />
    <!-- <link rel="stylesheet" type="text/css" media="screen" href="../css/leaderboardstylesheet.css" /> -->
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
            <div class="col-sm-12 col-md-4 col-lg-4 left-image">
                <img src="https://cdn2.iconfinder.com/data/icons/cricket-gyph/512/cricket__championship__wicket_-512.png" alt="ball-icon">
            </div>
            <div class="col-sm-12 col-md-8 col-lg-8 team-card_outer-container">
                <div class="centerize select-match_to-end">Select the match you want to end</div>
                 
                 
                 <%
                 ArrayList<Match> matchList = new AdminOperations().displayOngoingMatches();
                 request.setAttribute("matchList", matchList);
                 %>
                 <c:forEach items="${matchList}"  var="match">  
		                 	<!-- start of match card1 -->
		                 
		                    <div class="match-card row">
		                            <div class="col-sm-2 col-md-3 col-lg-3">
		                              <!--   <img src="../images/csklogo.png" class="team-logo" alt="team1">  -->
		                            </div>
		                            <div class="col-sm-8 col-md-6 col-lg-6">
		                            	<form action="http://127.0.0.1:8080/FantasyLeague/EndMatchServlet" method="get">
		                            	<input type="text" name="match-id" value="<c:out value="${match.getMatchId()}" />" hidden />
		                                <button type="submit" class="team-names"><h3 class="team-name"> <c:out value="${match.getTeamOne()}" /> vs <c:out value="${match.getTeamTwo()}" /></h3></button>
		                                </form>
		                            </div>
		                            <div class="col-sm-2 col-md-3 col-lg-3">
									<!-- <img src="https://upload.wikimedia.org/wikipedia/en/thumb/8/81/Sunrisers_Hyderabad.svg/1200px-Sunrisers_Hyderabad.svg.png" class="team-logo" alt="team1">  -->
		                            </div>
		                        </div>
		                   
		               <!-- end of match card1 -->
                 </c:forEach>  
                 
                 
               
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
    <script src="${pageContext.request.contextPath}/js/navbarscript.js"></script>
</body>
</html>