<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Tracker</title>
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=Bubblegum+Sans" />


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/gridstylesheet.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/userpagestyle.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/trackerpagestyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/foodtrackerstyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/weighttrackerstyle.css">

</head>

<body>

	<header class="row">
		<div class="logo-box col-sm-12 col-md-6 col-lg-3">
			<div class="logo-image-box">
				<img src="${pageContext.request.contextPath}/assets/logo-img1.png"
					alt="logo">
			</div>

			<div class="logo-name-box">
				<div class="logo-text-box row">FitnFlair</div>
				<div class="logo-subtext-box row">Your Diet Companion</div>
			</div>
		</div>
		<div class="user-icon col-sm-4 col-md-6 col-lg-1">
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

	<div class="wrapper row">
		<div class="feature-nav-bar row">
			<div class="nav-toggle_option col-sm-6 col-md-6 col-lg-6">Food
				Tracker</div>
			<div class="nav-toggle_option col-sm-6 col-md-6 col-lg-6">Weight
				Tracker</div>
		</div>
		<div class="feature-wrapper row">
			<div class="feature-box_food-tracker row">
				<div
					class="feature-box_food-tracker_wrapper1 col-sm-12 col-md-6 col-lg-6">
					<div class="title">
						Track Your Daily Intake
						<div class="calendar-error-message">Please enter date before
							continuing</div>
					</div>
					<div class="date-time-wrapper row">
						<form name="time-form " class="time-form row">
							<div class="input-box">
								<span>Choose date</span>
								<span>
									<input type="date" name="fooddate" class="fooddate">
								</span>

							</div>
							<div class="input-box">
								<span> Choose meal time </span>
								<span>
									<select name="meal-time" class="meal-time">
										<option value="breakfast">Breakfast</option>
										<option value="morningsnack">Morning Snack</option>
										<option value="lunch">Lunch</option>
										<option value="eveningsnack">Evening Snack</option>
										<option value="dinner">Dinner</option>
									</select>
								</span>
							</div>
						</form>
					</div>

					<div class="calculator-box_input row">
						<form name="food-form" class="food-form row">
							<div class="food-input-box_wrapper input-box">
								<div>
									<span>Enter food name</span>
								</div>
								<div>
									<input type="text" name="food-input" class="food-input-box"
										placeholder="Enter keywords to search">
									<div class="food-suggestion-list"></div>
								</div>
							</div>

							<div>
								<div class="input-box">
									<span>Serving size</span>
									<span>
										<input type="text" name="serving-input">
									</span>
								</div>
								<div class="input-box">
									<span>Enter quantity </span>
									<span>
										<input type="number" name="quantity-input">
									</span>
								</div>
								<div>
									<input type="button" value="Add To List" name="add-button">
								</div>
							</div>
						</form>
					</div>
				</div>
				<div
					class="feature-box_food-tracker_wrapper2 col-sm-12 col-md-6 col-lg-6">
					<div class="title">View Your Food Tracks per meal</div>
					<div class="calculator-box_input-list">
						<div class="calculator-box_input-list_column-names">
							<span>FoodItem</span>
							<span>Quantity</span>
							<span>Serving size</span>
							<span>Cal per serve</span>
							<span>Total Calories</span>
						</div>
						<div class="selected-food-list"></div>
						<div class="done-box">
							<button onclick=calculateTotal()>Done</button>
							<div class="done-message"></div>
						</div>
					</div>
					<div class="calculator-box_result-wrapper"></div>
				</div>
			</div>
			<div class="feature-box_weight-tracker row">
				<div class="chart-wrapper col-sm-12 col-md-6 col-lg-6">
					<div>View Your Progress</div>
					<canvas id="myChart" width="200" height="200"></canvas>
				</div>

				<div class="calender-wrapper col-sm-12 col-md-6 col-lg-6">
					<div>Track Your Progress</div>
					<form name="weight-form">
						<div>
							Choose date <input type="date" name="weightdate"
								class="weightdate">
						</div>
						<div>
							Enter weight <input type="number" name="weightvalue"
								class="weightvalue">
						</div>
						<div class="done-box">
							<input type="button" value="Add to tracker"
								onclick="updateWeight()">
							<div class="done-message"></div>
						</div>
					</form>
				</div>
			</div>
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

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/weighttrackerscript.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/foodtrackerscript.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/trackerpagescript.js"></script>

</body>

</html>