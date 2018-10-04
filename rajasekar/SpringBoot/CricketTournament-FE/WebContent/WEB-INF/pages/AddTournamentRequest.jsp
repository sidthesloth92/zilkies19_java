<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href=${PageContext.request.contextPath}/CricketTour/css/AddTournamentRequest.css>
<title>Request</title>
</head>
<body>
<%
		String email = null;
		HttpSession sessionVar = request.getSession();
		email = (String) sessionVar.getAttribute("email");
		String userName = null;
		userName = (String) sessionVar.getAttribute("user");
%>
<div class="user-container" id="home-container">
        <div class="header">
            <div class="header__left-option">
                <div class="logo">
                    <h2 class="logo-design">CC</h2>
                </div>
                <div class="explorer">
                    <h2>Cricket Connect</h2>
                </div>
            </div>
            <%
				if (userName != null) {
			%>
            <div class="header__right-option">
				<div class="login">
					<h3><%=userName%></h3>
				</div>
				<div class="login">
					<form class="form-element" name="Logout" method="post"
						action=${pageContext.request.contextPath}/Logout>
						<button>
							Logout
						</button>
					</form>
				</div>
			</div>
			<%
				}
			%>
        </div>
        <div class="tournament-container">
            <div class="tournament-container__sidenav">
            <a href="http://localhost:8080/CricketTour/Home">Home</a>
                <a href="http://localhost:8080/CricketTour/AddTournament">Add Tournament</a>
				<a href="http://localhost:8080/CricketTour/EditPage">Edit</a> 
				<a href="http://localhost:8080/CricketTour/CreateSchedule">Schedule</a> 
				<a href="http://localhost:8080/CricketTour/ViewSchedule">Scorecard</a>
				<a href="http://localhost:8080/CricketTour/Scorecard">Update Scorecard</a>
				<a href="http://localhost:8080/CricketTour/RemoveTournament">Remove Tournament</a>
            </div>
            <div class="container">
                <form name="requestForm" method="post"
				action="http://localhost:8080/CricketTour/AddTournament?email=<%=email%>">
                    <div class="tournament-name">
                        <h2 align="center">Enter the Tournament Details</h2>
                    </div>
                    <div class="tournament-name">
                        <input type="text" placeholder="Tournament Name" size="50" name="tourname">
                    </div>
                    <div class="tournament-format">
                        <select name="tourformat">
                            <option value="odi">ODI</option>
                            <option value="t20">T20</option>
                            <option value="test">TEST</option>
                        </select>
                    </div>
                    <div class="request">
                        <button>Request</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="footer">
            <div class="footer-section">
                <div class="location-div">
                    <div class="location">
                        <i class="fas fa-map-marker-alt"></i>Chennai
                    </div>
                    <div class="phone">
                        <i class="fas fa-phone"></i>8764536271
                    </div>
                    <div class="mail">
                        <i class="far fa-envelope"></i>abc@gmail.com
                    </div>
                </div>
                <div class="facebook-div">
                    <div class="facebook">
                        <a href="#">
                            <i class="fab fa-facebook"></i>
                        </a>
                    </div>
                    <div class="twitter">
                        <a href="#">
                            <i class="fab fa-twitter-square"></i>
                        </a>
                    </div>
                    <div class="linked-in">
                        <a href="#">
                            <i class="fab fa-linkedin"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>