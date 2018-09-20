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
<title></title>
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
	<!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="#">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	<!-- nav-bar -->
	<%@include file="../../includes/header.jsp"%>
	<!-- Navigation Bar Ending -->
	<!-- user details starting -->
	<c:set var="list" value="${contractorsList}" scope="session" />
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
							@admin</p>
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
				<div id="allContractors" class="tabcontent">
					<!-- To Display All Contractors -->
					<div class="row">
						<div class="col-lg-12 col-sm-12">
							<div class="search-bar">
								<form action="#">
									<input class="form__input" type="text"
										placeholder="Search Contractors">
								</form>
							</div>
							<div class="all-contractor-display">
								<c:choose>
									<c:when test="${empty list}">
										<div class="card card-2">
											<p class="box__project-name box__item">There are no
												contractors !</p>
										</div>
									</c:when>
									<c:otherwise>
										<!-- card starting -->
										<div class="row">
											<c:forEach var="currentListItem" items="${list}">
												<div class="col-lg-6">
													<div class="card card-2">
														<div class="user-location-projects__card-section">
															<div class="row">
																<div class="col-lg-12">
																	<p class="box__project-name box__item">
																		<a href="Admin?getType=contractor-detail&contractor-id=${ currentListItem.getContrId()}">Mr. ${ currentListItem.getFirstName() }
																		</a>
																	</p>
																	<p class="box__project-duration box__item">${ currentListItem.getCompany() }</p>
																</div>
															</div>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
										<!-- card Ending -->
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<!-- All Contractors Ending -->
				</div>
			</div>
			<div class="col-lg-1"></div>
		</div>
	</div>
	<script src=${pageContext.request.contextPath}/js/main.js async defer></script>
</body>

</html>