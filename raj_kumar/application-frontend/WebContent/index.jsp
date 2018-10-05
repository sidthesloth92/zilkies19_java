
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Tender App</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/main.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/normalize.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/grid.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/utility.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/nav-bar.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/landing-page.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/snack-bar.css>
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
</head>

<body>
	<!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="#">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	<%@include file="./includes/header.jsp"%>
	<div class="content-wrapper">
		<div class="row landing-section">
			<div class="col-lg-8 col-md-7 col-sm-12">
				<div class="landing-section__left">
					<h3>
						Making Building Stuff Easier,<br>Digitize Tender !
					</h3>
				</div>
			</div>
			<div class="col-lg-4 col-md-5 col-sm-12">
				<!-- Login Section  -->
				<div class="landing-section__right">
					<form action="/TenderApplication/Login" method="POST">
						<input type="text" class="form__input" placeholder="Username "
							name="username"> <input type="password"
							class="form__input" placeholder="Password" name="password">
						<button type="submit" class="form__button">Login</button>
					</form>
					<%
						if (request.getParameter("message") != "") {
					%>
					<p>${param.message}</p>
					<%
						}
					%>
				</div>
			</div>
		</div>
		<div class="row about-application-container">
			<div class="col-lg-2"></div>
			<div class="col-lg-8 col-sm-12">
				<div class="about-application">
					<h2 class="about-application__title">WHAT WE ARE</h2>
					<p class="about-application__description">Lorem ipsum dolor sit
						amet consectetur, adipisicing elit.Perspiciatis iste quam laborum
						illo quos,provident accusamus .</p>
					<br>
					<p class="about-application__description">Lorem ipsum dolor sit
						amet consectetur, adipisicing elit. Quibusdam error totam, eaque
						temporibus ducimus obcaecati, incidunt inventore numquam et
						voluptatem fugit accusantium? Labore nemo ipsam ipsa laudantium
						necessitatibus cumque porro?</p>
				</div>
			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>
	<%@include file="./includes/footer.jsp"%>
	</div>
	<c:if test="${param.success eq 1}">
		<div id="snackbar">Registration Successful</div>
		<script src=${pageContext.request.contextPath}/js/snack-bar.js async
			defer></script>
	</c:if>
	<script src=${pageContext.request.contextPath}/js/main.js async defer></script>
</body>

</html>