<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>TeamRegistration</title>
<link rel="stylesheet"
	href="${PageContext.request.contextPath}/CricketTournament/css/UserHomePage.css">
<link rel="stylesheet"
	href="${PageContext.request.contextPath}/CricketTournament/css/login.css">
<link rel="stylesheet"
	href="${PageContext.request.contextPath}/CricketTournament/css/TeamRegistration.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
</head>

<body>
	<%
		String tournamentName = request.getParameter("tourname");
		String email = null;
		HttpSession sessionVar = request.getSession();
		email = (String) sessionVar.getAttribute("email");
	%>
	<div class="user-container">
		<div class="header">
			<div class="header__left-option">
				<div class="logo">
					<h2 class="logo-design">CC</h2>
				</div>
				<div class="explorer">
					<h2>Cricket Connect</h2>
				</div>
			</div>
			<div class="header__right-option">
				<div class="login">
					<i class="fas fa-user"></i>
				</div>
				<div class="signup">
					<h2>USER</h2>
				</div>
			</div>
		</div>
		<div class="tournament-container">
			<div class="tournament-container__sidenav">
			<a href="http://localhost:8080/CricketTournament/Home">Home</a>
				<a href="http://localhost:8080/CricketTournament/AddTournament">Add Tournament</a>
				<a href="http://localhost:8080/CricketTournament/EditPage">Edit</a> 
				<a href="http://localhost:8080/CricketTournament/CreateSchedule">Schedule</a> 
				<a href="http://localhost:8080/CricketTournament/ViewSchedule">Scorecard</a>
				<a href="http://localhost:8080/CricketTournament/Scorecard">Update Scorecard</a>
				<a href="http://localhost:8080/CricketTournament/RemoveTournament">Remove Tournament</a>
			</div>
			<form name="registerForm" method="post"
				action="http://localhost:8080/CricketTournament/RegisterTeam?tourname=<%=tournamentName%>&email=<%=email%>">
				<div class="form-card">
					<div class="team-details-container">
						<div class="team-form">
							<div class="form-div">
								<div class="register-quote">
									<h2>Register Your Team</h2>
								</div>
								<div class="player-1">
									<div class="player-name">
										<h2>Enter Team name</h2>
									</div>
									<div class="player-name">
										<input type="text" placeholder="Teamname" name="teamname"
											required>
									</div>
								</div>
								<div class="player-1">
									<div class="player-name">
										<h2>Enter Player Details</h2>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="outer-player">
									<div class="player-1">
										<div class="player-name">
											<input type="text" placeholder="Firstname" name="firstname"
												required>
										</div>
										<div class="player-name">
											<input type="text" placeholder="Lastname" name="lastname"
												required>
										</div>
										<div class="player-role">
											<select name="role">
												<option value="batsman">Batsman</option>
												<option value="bowler">Bowler</option>
												<option value="all-rounder">All-Rounder</option>
												<option value="wk-bat">Wk-Batsman</option>
											</select>
										</div>
									</div>
								</div>
								<div class="player-1">
									<div class="player-name">
										<button>Register</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>