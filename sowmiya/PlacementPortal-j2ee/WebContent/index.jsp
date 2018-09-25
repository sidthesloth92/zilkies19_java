<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Placement-portal</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/index.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/home.css" />
<link href="https://fonts.googleapis.com/css?family=Alex+Brush"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
</head>
<body>
<body>
	<div class="row outer-container landing">
		<div class="row header-container">
			<div class="header-title col-lg-8">Placement Portal</div>
			<span class="header--login col-lg-1" id="display-modal">LOGIN</span>
			<span class="col-lg-3"></span>
		</div>
	</div>
	</div>
	<div class="footer">
		<div class="row footer__links">
			<div class="col-lg-1 col-sm-6 col-md-6 links__facebook">
				<a href="#">
					<i class="fab fa-facebook-f footer__icon" alt="facebook icon"></i>
				</a>
			</div>
			<div class="col-lg-1 col-sm-6 col-md-6 links__twitter">
				<a href="#">
					<i class="fab fa-twitter footer__icon" alt="twitter icon"></i>
				</a>
			</div>
			<div class="col-lg-1"></div>
			<div class="col-lg-2 col-sm-12 col-md-2 links__other">
				<a href="#">About us</a>
			</div>
			<div class="col-lg-2 col-sm-12 col-md-2 links__other">
				<a href="#">Features</a>
			</div>
			<div class="col-lg-2 col-sm-12 col-md-2 links__other">
				<a href="#">Contact us</a>
			</div>
			<div class="col-lg-2 col-sm-12 col-md-2 links__other">
				<a href="#">Privacy policys</a>
			</div>
		</div>
		<div class="row footer__copyrights">
			<div class="col-sm-12 col-md-12 col-lg-12 copyrights__content">
				&copy; 2018, Zilker technology.All Rights Reserved. Your use of this
				service is subject to our Terms of Use and Privacy & Cookies Policy.</div>
		</div>
	</div>
	<div class="row modal-container" id="login">
		<form class="col-lg-5 form" id="form1" name="registeration-form"
			onsubmit="return isEmpty()&&validateName()&&validatePassword()"
			method="post">
			<div class="row form__container">
				<div class="close-icon--close" style="backgroud-color: green">
					<i class="far fa-times-circle"
						onclick="document.getElementById('login').style.display='none'"></i>
				</div>
				<span id="empty"></span>
				<div class="username__wrapper">
					<label class="col-lg-2 col-md-1 col-sm-1 username__icon"
						for="username"></label>
					<input type="text" placeholder="Username" name="username"
						class="col-lg-10 col-md-8 col-sm-8 username__input"
						onfocusout="return validateName()" />
				</div>
				<span id="invalidName"></span>
				<div class="password__wrapper">
					<label class="col-lg-2 col-md-1 col-sm-1 password__icon"
						for="password"></label>
					<input type="password" placeholder="password" name="password"
						class="col-lg-5 col-md-8 col-sm-8 password__input"
						onfocusout="return validatePassword()" />
				</div>
				<span id="invalidCredential"></span>
					<button type="button"
						class="col-lg-10 col-md-10 col-sm-10 button--login">LOGIN</button>
			</div>
	</div>
	</form>
	</div>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/js/login-validate.js"></script>
</body>
</html>