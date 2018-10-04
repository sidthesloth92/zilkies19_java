<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>FitnessFirst-bmr calculator</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href='https://fonts.googleapis.com/css?family=Kalam'
	rel='stylesheet'>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=Bubblegum+Sans" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/anonpagestyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gridstylesheet.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bmrstylesheet.css">

</head>

<body>
	<header class="row">
		<div class="logo-box col-sm-10 col-md-6 col-lg-3">
			<div class="logo-image-box">
				<img src="${pageContext.request.contextPath}/assets/logo-img1.png" alt="logo">
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
		<div class="bmr-wrapper col-sm-12 col-md-12 col-lg-10">
			<div class="bmr-description">
				<h3>What is BMR?</h3>
				<p>The basal metabolic rate (BMR) is the amount of energy needed
					while resting in a temperate environment when the digestive system
					is inactive. It is the equivalent of figuring out how much gas an
					idle car consumes while parked. In such a state, energy will be
					used only to maintain vital organs, which include the heart, lungs,
					kidneys, nervous system, intestines, liver, lungs, sex organs,
					muscles, and skin. For most people, upwards of ~70% of total energy
					(calories) burned each day is due to upkeep. Physical activity
					makes up ~20% of expenditure and ~10% is used for the digestion of
					food, also known as thermogenesis.</p>
				<h3>Calculating TDEE</h3>
				<p>Your Total Daily Energy Expenditure (TDEE) is an estimation
					of how many calories you burn per day when exercise is taken into
					account. It is calculated by first figuring out your Basal
					Metabolic Rate, then multiplying that value by an activity
					multiplier. Since your BMR represents how many calories your body
					burns when at rest, it is necessary to adjust the numbers upwards
					to account for the calories you burn during the day. This is true
					even for those with a sedentary lifestyle. Our TDEE calculator uses
					the best formulas and displays your score in a way that's easy to
					read and meaningful.</p>
				<h3>TDEE and Weight Loss</h3>
				<p>There are a phenomenal number of weight-loss programs out
					there; however, the fundamental requirement to losing weight
					remains simple: To lose weight, you need to burn more calories than
					you consume. By calculating your TDEE, you can identify the maximum
					number of calories you can consume if you wish to lose weight. If
					you consistently consume fewer calories than the TDEE, you will
					enter negative calories, and your body will find the energy it
					needs to sustain your activities from your fat stores.</p>

			</div>

			<div class="bmr-calculate-wrapper row">
				<div class="bmr-input-form-wrapper col-sm-12 col-md-12 col-lg-6">
					<form name="bmr-input-form">
						<div class="measurement-system-toggle">
							<span id="measurement-system-toggle_metric"
								class="measurement-system-toggle_option chosen"
								onclick=showMetric()>Metric</span> <span
								id="measurement-system-toggle_imperial"
								class="measurement-system-toggle_option" onclick=showImperial()>Imperial</span>
						</div>

						<div class="input-box-toggle">
							<div class="input-box-wrapper">
								<div class="input-box-toggle_options shown" id="metric-input">
									<div>
										Enter height&nbsp; <input type="text" name="height-metric"
											onfocus="removeResult()"> cms
									</div>
									<div>
										Enter weight <input type="text" name="weight-metric">
										kgs
									</div>
								</div>
								<div class="input-box-toggle_options" id="imperial-input">
									<div>
										Enter height&nbsp; <input type="text"
											name="height-imperial-feet" onfocus="removeResult()">
										ft <input type="text" name="height-imperial-inches">
										in
									</div>
									<div>
										Enter weight <input type="text" name="weight-imperial">
										lbs
									</div>
								</div>
								<div>
									Enter age&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="number" name="age"> years
								</div>
								<div>
									Choose gender <input type="radio" name="gender">Male <input
										type="radio" name="gender">Female
								</div>
								<div>
									Choose your daily lifestyle <select id="lifestyle">
										<option value="Sedentary">Sedentary (little to no
											exercise)</option>
										<option value="Light">Light Activity (little
											exercise/sports 1-3 days a week)</option>
										<option value="Moderate">Moderate Activity (moderate
											exercise/sports 3-5 days a week)</option>
										<option value="Hard">Hard Activity (hard
											exercise/sports 6-7 days a week)</option>
										<option value="Very hard">Very hard Activity (very
											hard exercise/sports and a physical job)</option>
									</select>
								</div>
							</div>

						</div>

						<div class="bmr-result">
							<div class="bmr-value">
								<span>Your BMR is </span> <span></span>
							</div>
							<div class="tdee-value">
								<span>Your TDEE is</span> <span></span>
							</div>
							<div class="bmr-error-msg"></div>
						</div>

						<input type="button" value="Calculate" onclick="showResultBox()">
					</form>
				</div>

				<div class="bmr-analysis-image col-sm-12 col-md-12 col-lg-5">
					<img src="${pageContext.request.contextPath}/assets/bmr1.png" alt="bmr-analysis-image">
				</div>
			</div>

			<div class="bmr-comment">
				<p>Calorie Intake For Healthy Fitness Plans</p>
				<p>
					&nbsp;&nbsp;<span></span> cal/day to maintain weight
				</p>
				<p>
					&nbsp;&nbsp;<span></span> cal/day to lose 0.5kg per week
				</p>
				<p>
					&nbsp;&nbsp;<span></span> cal/day to lose 1kg per week
				</p>
				<p>
					&nbsp;&nbsp;<span></span> cal/day to gain 0.5kg per week
				</p>
				<p>
					&nbsp;&nbsp;<span></span> cal/day to gain 1kg per week
				</p>
			</div>

			<div class="bmr-significance">
				<h3>Significance Of BMR</h3>
				<p>
					1. The determination of BMR is the principal guide for diagnosis
					and treatment of thyroid disorders. <br> 2. If BMR is less
					than 10% of the normal, it indicates moderate hypothyroidism. In
					severe hypothyroidism, the BMR may be decreased to 40 to 50 percent
					below normal. <br> 3. BMR aids to know the total amount of
					food or calories required to maintain body weight. <br> 4. The
					BMR is low in starvation, under nutrition, hypothalamic disorders,
					Addison’s disease and lipoid nephrosis. <br> 5. The BMR is
					above normal in fever, diabetes insipidus, leukemia and
					polycythemia.
				</p>
			</div>
			<div class="bmrvsbmi">
				<h3>BMR vs BMI</h3>
				<p>The abbreviations BMI and BMR sound similar, but they stand
					for two separate things. Your BMI, or body mass index, is a number
					calculated from your height and weight which is then used to assess
					your body composition. Your BMR, or basal metabolic rate, is the
					number of calories you burn when your body is at rest. Although the
					two terms are independent of each other, your BMI may indirectly
					affect your BMR.</p>
				<p>Several factors affect your basal metabolic rate and body fat
					composition is one of them. Those with more muscle mass tend to
					burn more calories at rest because muscle tissue requires more
					calories to maintain than fat tissue. Although BMI and BMR are not
					directly related, if you have a high BMI because of a high body fat
					percentage, your BMR may be lower. If you have a high BMI because
					of a large amount of muscle mass, your BMR may be increased.
					Additionally, if you are overweight, but very active, that doesn't
					mean you'll necessarily have a low BMR. BMI and BMR are guidelines
					that allow nutrition and medical professionals to make educated
					determinations about your body composition and calorie burn, but
					every person should still be viewed individually. Check with your
					doctor or a dietitian to if you have questions about your own BMI
					and BMR.</p>
				<p>
					You can also find out your BMI using our <a
						href="bmicalculator.html">BMI calculator</a>
				</p>
			</div>

			<div class="note">Please note: *The results given by this bmr
				calculator should be used only as a guide and should not replace
				medical advice. Please bear in mind that, when interpreting the
				results of this body mass index calculator, other factors such as
				muscle structure and ethnic origin should be considered. For
				example, a person with a lot of muscle might have a higher bmr, when
				their body is actually perfectly healthy, due to the fact that
				muscle weighs more than fat. Athletes can have muscular builds.
				Since muscle weighs more than fat, bmr may overestimate body fat for
				these individuals. Older adults may have lost muscle. In this case,
				bmr may underestimate body fat. Always speak to a Doctor or health
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
			<p>2018 © Fit 'n Flair, Inc.</p>
		</div>
	</footer>

	<script src="${pageContext.request.contextPath}/js/bmrpagescript.js"></script>
	<script src="${pageContext.request.contextPath}/js/loginmodalscript.js"></script>
	<script src="${pageContext.request.contextPath}/js/loginvalidation.js"></script>

</body>

</html>