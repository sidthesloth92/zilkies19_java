<%@page import="com.ztech.io.fitnessfirstprototype.beans.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="true" isELIgnored="false" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Dashboard</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=Bubblegum+Sans" />


<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/gridstylesheet.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/headerfooterstyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/dashboardstyle.css">

</head>

<body onload="getTarget('${userProfile.getTarget()}')">
	<header class="row">
		<div class="logo-box col-sm-6 col-md-6 col-lg-3">
			<div class="logo-image-box">
				<img src="${pageContext.request.contextPath}/assets/logo-img1.png"
					alt="logo">
			</div>

			<div class="logo-name-box">
				<div class="logo-text-box row">Fit 'n Flair</div>
				<div class="logo-subtext-box row">Your Diet Companion</div>
			</div>
		</div>
		<div class="user-icon col-sm-6 col-md-6 col-lg-1">
			<img
				src="${pageContext.request.contextPath}/assets/userprofilepic1.png"
				alt="profile pic">
		</div>
	</header>
	<div class="profile-menu">
		<ul>
			<li><a href="LoginServlet">Log out</a></li>
			<li>View Account</li>
		</ul>
	</div>

	<div class="main-content">
		<div class="welcome-box row">Welcome ${userName}</div>
		<div class="bmi-bmr-wrapper row">
			<div class="bmi-wrapper col-sm-12 col-md-10 col-lg-5">
				<div class="bmi-nav col-sm-3 col-md-3 col-lg-3">
					<div class="bmi-nav_item row"
						onclick="showBmiContent('bmi-value','bmi-category','bmi-comment')">
						Current BMI</div>

					<div class="bmi-nav_item row"
						onclick="showBmiContent('bmi-category','bmi-comment','bmi-value')">
						Category</div>

					<div class="bmi-nav_item row"
						onclick="showBmiContent('bmi-comment','bmi-value','bmi-category')">
						Status</div>

				</div>

				<div class="bmi-content-wrapper col-sm-9 col-md-9 col-lg-9">
					<div class="bmi-content" id="bmi-value">
						<c:out value="${userProfile.getBmi()}" />
					</div>
					<div class="bmi-content" id="bmi-category">
						<c:out value="${bmiCategory}" />

					</div>
					<div class="bmi-content" id="bmi-comment">
						<c:out value="${bmiStatus}" />

					</div>
				</div>
			</div>

			<div class="bmr-wrapper col-sm-12 col-md-10 col-lg-5">
				<div class="bmr-nav col-sm-3 col-md-3 col-lg-3">
					<div class="bmr-nav_item"
						onclick="showBmrContent('bmr-value','bmr-plans')">Current
						BMR</div>

					<div class="bmr-nav_item"
						onclick="showBmrContent('bmr-plans','bmr-value')">Choose
						plan</div>

				</div>

				<div class="bmr-content-wrapper col-sm-9 col-md-9 col-lg-9">
					<div class="bmr-content" id="bmr-value">
						<c:out value="${userProfile.getBmr()}" />
					</div>
					<div class="bmr-content row" id="bmr-plans">
						<p>Choose your fitness plan.</p>
						<div id="error-text"></div>
						<form>
							<div>
								<input type="radio" name="plan" id="plan1" value="1">
								<label for="plan1">Intake <c:out
										value="${userProfile.getTdee()}" />cal/day to maintain your
									current weight
								</label>
							</div>
							<div>
								<input type="radio" name="plan" id="plan2" value="2">
								<label for="plan2">Intake <c:out
										value="${userProfile.getTdee()}-500" />cal/day to lose 0.5
									kg/week
								</label>
							</div>
							<div>
								<input type="radio" name="plan" id="plan3" value="3">
								<label for="plan3">Intake <c:out
										value="${userProfile.getTdee()}-1000" />cal/day to lose 1
									kg/week
								</label>
							</div>
							<div>
								<input type="radio" name="plan" id="plan4" value="4">
								<label for="plan4">Intake <c:out
										value="${userProfile.getTdee()}+500" />cal/day to gain 0.5
									kg/week
								</label>
							</div>
							<div>
								<input type="radio" name="plan" id="plan5" value="5">
								<label for="plan5">Intake<c:out
										value="${userProfile.getTdee()}+1000" />cal/day to gain 1
									kg/week
								</label>
							</div>
							<div>
								<input type="button" value="Done" onclick="setTarget()">
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
		<div class="features-display row">
			<a
				href="${pageContext.request.contextPath}/pages/trackerpage.jsp"
				class="col-sm-10 col-md-5 col-lg-5">
				<div class="food-tracker_feature">
					<img src="${pageContext.request.contextPath}/assets/food1.jpg"
						alt="food picture">
					<p>Track your food intake to achieve your fitness goal</p>
				</div>
			</a> <a
				href="${pageContext.request.contextPath}/pages/weighttrackerpage.jsp"
				class="col-sm-10 col-md-5 col-lg-5">
				<div class="weight-tracker_feature">
					<img src="${pageContext.request.contextPath}/assets/weight1.jpg"
						alt="weight picture">
					<p>Track your weight and view your progress</p>
				</div>
			</a>
		</div>
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


	<script src="${pageContext.request.contextPath}/js/dashboardscript.js"></script>
	<script>
		<%-- var chosenTarget =
	<%=profile.getTarget()%>
		console.log("chosen already" + chosenTarget); --%>
		
		function getTarget(chosenTarget){
			var planOptions = document.getElementsByName("plan");

			for (var i = 0; i < planOptions.length; i++) {
				if (i + 1 == chosenTarget) {
					planOptions[i].checked = true;
				}
			}
		}
		
	</script>
</body>

</html>