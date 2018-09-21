<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	href=${pageContext.request.contextPath}/css/utility.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/normalize.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/grid.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/snack-bar.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/profile-utility.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/admin.css>
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:800"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
</head>

<body>
	<c:if test="${empty userSession}">
		<!-- The Modal to Show when the user is not logged in -->
		<div class="modal">
			<div class="modal-content">
				<p>You Must be logged in to Continue</p>
				<form action="../index.jsp" method="POST">
					<div class="row">
						<div class="col-lg-4"></div>
						<div class="col-lg-4">
							<button type="submit" class="form__button">Alright Fine
								!</button>
						</div>
						<div class="col-lg-4"></div>
					</div>
				</form>
			</div>
		</div>
	</c:if>
	<!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="#">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	<!-- nav-bar -->
	<%@include file="../../includes/header.jsp"%>
	<!-- Navigation Bar Ending -->
	<!-- user details starting -->
	<div class="content-wrapper">
		<div class="row profile-section">
			<div class="col-lg-2"></div>
			<div class="col-lg-8 c col-sm-12">
				<div class="profile-section__image__outer">
					<img class="profile-section__image"
						src=${pageContext.request.contextPath}/assets/images/profile-image.png
						alt="">
				</div>
				<div class="profile-section__details ">
					<p
						class="profile-section__details__inner__items profile-section__heading">Welcome,
						${userSession.getUserName() }</p>
					<div class="profile-section__details__inner">
						<p class="profile-section__details__inner__items">Name: Admin</p>
						<p class="profile-section__details__inner__items">Username:
							@${ userSession.getUserName() }</p>
					</div>
				</div>
			</div>
			<div class="col-lg-2"></div>
		</div>
		<!-- user details ending -->
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-10 col-sm-12">
				<div class="tab">
					<a href="Admin?getType=add-new-project">
						<button class="tab__links">Add Project</button>
					</a> <a href="Admin?getType=display-requested-projects">
						<button class="tab__links" type="submit">Display
							Requested Project</button>
					</a> <a href="Admin?getType=view-all-contractors">
						<button type="submit" class="tab__links">View all
							Contractors</button>
					</a>
				</div>
				<div id="addProject" class="tabcontent">
					<!-- Adding a New Project -->
					<div class="row">
						<div class="col-lg-2"></div>
						<div class="col-sm-12 col-lg-8">
							<div class="new-project-container">
								<form action="/TenderApplication/Admin" method="POST">
									<div class="form-section__name">
										<input class="form__input" type="text"
											placeholder="Project Name" name="project-name">
									</div>
									<input class="form__input" type="text"
										placeholder="Project Location" name="project-location">
									<input class="form__input" type="text"
										placeholder="Description" name="project-description">
									<div class="row">
										<div class="col-lg-4"></div>
										<div class="col-lg-4 col-sm-12">
											<button type="submit" class="form__button">Add
												Project !</button>
										</div>
										<div class="col-lg-4"></div>
									</div>
									<input type="hidden" name="post-type"
										value="adding-new-project">
								</form>
							</div>
						</div>
						<div class="col-lg-2"></div>
					</div>
					<!-- Adding a New Project Ending  -->
				</div>
			</div>
			<div class="col-lg-1"></div>
		</div>
	</div>
	<c:if test="${param.success eq 1}">
		<div id="snackbar">Project Added</div>
		<script src=${pageContext.request.contextPath}/js/snack-bar.js async
			defer></script>
	</c:if>
	<script src=${pageContext.request.contextPath}/js/main.js async defer></script>
</body>

</html>