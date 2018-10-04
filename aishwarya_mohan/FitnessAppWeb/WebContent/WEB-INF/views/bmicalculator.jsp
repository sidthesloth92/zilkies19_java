<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>FitnessFirst-BMI calculator</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href='https://fonts.googleapis.com/css?family=Kalam'
	rel='stylesheet'>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=Bubblegum+Sans" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/anonpagestyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/gridstylesheet.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bmistylesheet.css">

</head>

<body>
	<header class="row">
		<div class="logo-box col-sm-10 col-md-6 col-lg-3">
			<div class="logo-image-box">
				<img src="${pageContext.request.contextPath}/assets/logo-img1.png"
					alt="logo">
			</div>

			<div class="logo-name-box">
				<div class="logo-text-box row">Fit 'n Flair</div>
				<div class="logo-subtext-box row">Your Diet Companion</div>
			</div>
		</div>

		<div class="login-signup-wrapper col-sm-12 col-md-6 col-lg-3">
			<div class="login-box">
				<button onclick="showLogInBox()">Login</button>
			</div>
			<div class="signup-box">
				<a href="/FitnessFirstPrototype/SignupPageServlet">
					<button>Sign up</button>
				</a>
			</div>
		</div>

	</header>

	<div class="modal row" style="display: none">
		<div class="modal-content-box col-sm-12 col-md-8 col-lg-4">
			<button onclick="closeLoginBox()">&#10006</button>

			<div>LOGIN</div>
			<form name="loginform">
				<div>
					<input type="text" placeholder="Username" name="username">
				</div>

				<div>
					<input type="password" placeholder="Password" name="password">
				</div>

				<div>
					<input type="button" value="Login" id="loginbutton">
				</div>

				<div id="error-text"></div>

			</form>
		</div>
	</div>


	<div class="wrapper row">
		<div class="navbar col-sm-12 col-md-3 col-lg-2">
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="BmiServlet">BMI calculator</a></li>
				<li><a href="BmrServlet">BMR calculator</a></li>
				<li><a href="CalorieCalculatorServlet">Calorie calculator</a></li>
			</ul>

		</div>
		<div class="col-sm-12 col-md-3 col-lg-2"></div>
		<div class="bmi-wrapper col-sm-12 col-md-9 col-lg-10">
			<div class="bmi-description">
				<h3>What is BMI?</h3>
				<p>The body mass index measurement is the calculation of your
					body weight in relation to your height and is commonly used as an
					indicator of whether you might be in a risk category for health
					problems caused by your weight. BMI is used as a screening tool to
					indicate whether a person is underweight, overweight, obese or a
					healthy weight for their height. If a person's BMI is out of the
					healthy BMI range, their health risks may increase significantly.</p>
				<p>Calculate your BMI (body mass index) with this calculator
					tool. By calculating your body mass index, you can get an
					indication of whether your weight may be affecting your health. A
					BMI chart is also available. BMI is commonly used by Doctors and
					health professionals worldwide and is calculated using your weight
					and height.</p>
			</div>

			<div class="bmi-calculate-wrapper row">
				<div class="bmi-input-form-wrapper col-sm-12 col-md-6 col-lg-7">
					<form name="bmi-input-form">
						<div class="measurement-system-toggle">
							<span id="measurement-system-toggle_metric"
								class="measurement-system-toggle_option chosen"
								onclick=showMetric()>Metric</span> <span
								id="measurement-system-toggle_imperial"
								class="measurement-system-toggle_option" onclick=showImperial()>Imperial</span>
						</div>

						<div class="input-box-toggle">
							<div class="input-box-toggle_options shown" id="metric-input">
								<div>
									Enter height <input type="text" name="height-metric">
									cms
								</div>
								<div>
									Enter weight <input type="text" name="weight-metric">
									kgs
								</div>

							</div>
							<div class="input-box-toggle_options" id="imperial-input">
								<div>
									Enter height <input type="text" name="height-imperial-feet">
									ft <input type="text" name="height-imperial-inches"> in
								</div>
								<div>
									Enter weight <input type="text" name="weight-imperial">
									lbs
								</div>

							</div>
						</div>

						<div class="bmi-result">
							<div class="bmi-value">
								<span>Your BMI is </span> <span></span>
							</div>
							<div class="bmi-status">
								<span>You are in the </span> <span></span> <span>category</span>
							</div>
						</div>

						<input type="button" value="Calculate" onclick="showResultBox()">
					</form>
				</div>

				<div class="bmi-analysis-image col-sm-12 col-md-6 col-lg-4">
					<img
						src="${pageContext.request.contextPath}/assets/bmi-category1.gif"
						alt="bmi-analysis-image">
				</div>
			</div>

			<div class="bmi-comment">
				<span></span>
			</div>

			<div class="bmi-significance">
				<h3>Significance Of BMI</h3>
				<p>It is a good gauge of body fat. The most basic definition of
					overweight and obesity is having too much body fat-so much so that
					it âpresents a risk to health.â A reliable way to determine
					whether a person has too much body fat is to calculate the ratio of
					their weight to their height squared. This ratio, called the body
					mass index (BMI), accounts for the fact that taller people have
					more tissue than shorter people, and so they tend to weigh more.</p>
			</div>
			<div class="bmi-shortcomings">
				<h3>Shortcomings Of BMI</h3>
				<p>Although BMI is commonly used as a tool to indicate whether
					someone is a healthy weight, it is not used to definitively
					diagnose obesity. Indeed, people commonly choose to take several
					factors into consideration for this kind of diagnosis, including
					age, muscle-fat ratio, waist circumference, height, sex, and bone
					density.</p>
				<p>It is important to remember that BMI measurements do not make
					a distinction between whether you are male or female, whether you
					have a large or small body frame, whether you have high muscle mass
					and the areas in which fat is distributed around your body. So,
					someone who has a very muscular physique (a body builder, for
					example) may have a high BMI without having excess fat. For this
					reason, BMI is not an indicator of obesity on its own.</p>
				<p>
					You can also find out how much calories you burn during rest with
					our <a href="bmrcalculator.html">BMR calculator</a> to have a more
					customised assessment of your fitness level
				</p>
			</div>

			<div class="note">Please note: *The results given by this bmi
				calculator should be used only as a guide and should not replace
				medical advice. Please bear in mind that, when interpreting the
				results of this body mass index calculator, other factors such as
				muscle structure and ethnic origin should be considered. For
				example, a person with a lot of muscle might have a higher BMI, when
				their body is actually perfectly healthy, due to the fact that
				muscle weighs more than fat. Athletes can have muscular builds.
				Since muscle weighs more than fat, BMI may overestimate body fat for
				these individuals. Older adults may have lost muscle. In this case,
				BMI may underestimate body fat. Always speak to a Doctor or health
				professional for advice and guidance before making any dramatic
				changes to your lifestyle.</div>

		</div>

	</div>

	<footer class="row">
		<div class="link-box row">
			<div class="link-box_social col-sm-12 col-md-12 col-lg-12">
				<i class="fab fa-facebook-f"></i> <i class="fab fa-twitter"></i> <i
					class="fab fa-instagram"></i> <i class="fab fa-blogger"></i>
			</div>
		</div>
		<div class="copyright-box col-sm-12 col-md-12 col-lg-12">
			<p>2018 Â© Fit 'n Flair, Inc.</p>
		</div>
	</footer>

	<script src="${pageContext.request.contextPath}/js/bmipagescript.js"></script>
	<script src="${pageContext.request.contextPath}/js/loginmodalscript.js"></script>
	<script src="${pageContext.request.contextPath}/js/loginvalidation.js"></script>

</body>

</html>