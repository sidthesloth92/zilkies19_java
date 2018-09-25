<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weight Tracker</title>


<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=Bubblegum+Sans" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gridstylesheet.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/userpagestyle.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/weighttrackerstyle.css">

</head>
<body>
	<header class="row">
		<div class="logo-box col-sm-12 col-md-6 col-lg-3">
			<div class="logo-image-box">
				<img src="${pageContext.request.contextPath}/assets/logo-img1.png" alt="logo">
			</div>
			.
			<div class="logo-name-box">
				<div class="logo-text-box row">FitnFlair</div>
				<div class="logo-subtext-box row">Your Diet Companion</div>
			</div>
		</div>
		<div class="user-icon col-sm-4 col-md-6 col-lg-1">
			<img src="${pageContext.request.contextPath}/assets/userprofilepic1.png" alt="profile pic">
		</div>
	</header>

	<div class="profile-menu">
		<ul>
			<li><a href="LoginServlet">Log out</a></li>
			<li>View Account</li>
		</ul>
	</div>

	<div class="chart-wrapper">
		<canvas id="chart" width="400" height="400"></canvas>
	</div>

	<div class="input-wrapper">
		<form name="weight-form">
			Choose date<input type="date" name="weightdate"> Enter weight
			<input type="number" name="weightvalue"> <input type="submit"
				value="Add to tracker">
		</form>
	</div>

	<div class="calender-date">
		Date picked: <span data-calendar-label="picked"></span>
	</div>


	<footer class="row">
		<div class="link-box_social col-sm-12 col-md-12 col-lg-12">
			<i class="fab fa-facebook-f"></i> <i class="fab fa-twitter"></i> <i
				class="fab fa-instagram"></i> <i class="fab fa-blogger"></i>
		</div>
		<div class="copyright-box col-sm-12 col-md-12 col-lg-12">
			<p>2018 © Fit 'n Flair, Inc.</p>
		</div>
	</footer>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
	<script src="${pageContext.request.contextPath}/js/weighttrackerscript.js"></script>

	<script>
		document.getElementsByClassName("user-icon")[0].onclick = function() {
			document.getElementsByClassName("profile-menu")[0].classList
					.toggle("show-menu");
		}
	</script>

</body>
</html>