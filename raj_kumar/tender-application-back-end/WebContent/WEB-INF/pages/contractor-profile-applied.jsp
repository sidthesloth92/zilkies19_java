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
	href=${pageContext.request.contextPath}/css/normalize.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/grid.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/profile-utility.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/utility.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/contractor-profile.css>
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
		<div class="session-checking-modal">
			<div class="session-checking-modal__content">
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
	<c:set var="list" value="${appliedProjectsList}" scope="session" />
	<%@include file="../../includes/header.jsp"%>
	<!-- Navigation Bar Ending -->
	<div class="content-wrapper">
		<!-- user details starting -->
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
						<p class="profile-section__details__inner__items">Name: ${ userSession.getFirstName() } ${ userSession.getLastName() }</p>
						<p class="profile-section__details__inner__items">Username:
							@${userSession.getUserName() }</p>
						<p class="profile-section__details__inner__items">
							<i class="fas fa-map-marker-alt"></i> ${userSession.getLocation() }
						</p>
					</div>
				</div>
			</div>
			<div class="col-lg-2"></div>
		</div>
		<!-- user details ending -->
		<!-- . View My Projects\n2. View Available Projects\n3. View Delayed Projects\n4. Log Out\n5. View Applied Projects \n -->
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-10">
				<div class="tab">
					<a href="Contractor?getType=contractor-home">
						<button class="tab__links">View My Project</button>
					</a> <a href="Contractor?getType=view-available-project">
						<button type="submit" class="tab__links">View Available
							Projects</button>
					</a> 
					<a href="Contractor?getType=view-delayed-project">
						<button class="tab__links">View Delayed Projects</button>
					</a>  
					<a href="Contractor?getType=contractor-applied-project">
						<button class="tab__links" type="submit">View Applied
							Projects</button>
					</a>
				</div>
				<div id="appliedProjects" class="tabcontent">
					<c:choose>
						<c:when test="${ empty list }">
							<div class="card card-2">
								<p class="box__project-name box__item">You have no applied Project Sorry !</p>
							</div>
						</c:when>
						<c:otherwise>
						<div class="row">
							<c:forEach var="currentListItem" items="${list}">
							<div class="col-lg-6">
								<!-- View Applied Projects -->
								<!-- card starting -->
								<div class="card card-2">
									<div class="user-location-projects__card-section">
										<div class="row">
											<div class="col-lg-12">
												<p class="box__project-name box__item">${
                                                                    currentListItem.getProjectName() }</p>
												<p class="box__project-duration box__item">Duration : ${ currentListItem.getStartDate() }
													- ${ currentListItem.getEndDate() }</p>
												<p class="box__project-description box__item">${ currentListItem.getDescription() }</p>
												<p class="box__project-status box__item">${ currentListItem.getRequestedStatus() }</p>
												<div
													class="user-location-projects__card-section__location box__item">
													<p class="box__project-contractor-name">Contractor
														Name: ${userSession.getUserName() }</p>
													<p class="box__project-location">
														<i class="fas fa-map-marker-alt box-item__location-icon"></i>${ currentListItem.getLocation() }
													</p>
												</div>

											</div>
										</div>
									</div>
								</div>
								</div>
							</c:forEach>
							</div>
							<!-- card ending -->
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="col-lg-1"></div>
		</div>

	</div>
	<script src=${pageContext.request.contextPath}/js/main.js async defer></script>
</body>

</html>