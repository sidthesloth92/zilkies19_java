<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Placement-portal</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="../css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="../css/home.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/header.css" />
<link href="https://fonts.googleapis.com/css?family=Alex+Brush"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
</head>
<body>
	<!-- HEADER -->
	<%@include file="header.jsp"%>
	<!-- BODY -->
	<div class="row body-content">
		<!--  <div class="col-lg-12 col-sm-12 col-md-12 content__image">
        <img src="assets/croppes.png"/>
        </div>-->
	</div>
	<div class="row modal-container" id="login">
		<form class="col-lg-5 form" name="registeration-form"
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
						class="col-lg-5 col-md-8 col-sm-8 password__input" />
				</div>
				<span id="invalidCredential"></span>
				<button type="button"
					class="col-lg-8 col-md-8 col-sm-8 button--login">LOGIN</button>
			</div>
	</div>
	</form>
</body>
<script src="../js/main.js"></script>
<script src="../js/login-validate.js"></script>
</html>