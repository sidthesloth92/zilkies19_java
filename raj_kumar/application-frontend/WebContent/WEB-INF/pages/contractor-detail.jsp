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
	href=${pageContext.request.contextPath}/css/contractor-detail.css>
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
	<c:set var="list" value="${contractorDetail}" scope="session" />
	<%@include file="../../includes/header.jsp"%>
	<!-- Navigation Bar Ending -->
	<div class="content-wrapper">
		<c:choose>
			<c:when test="${ empty list }">
				<div class="card card-2">
					<p class="box__project-name box__item">There are no such
						Contractors Sorry !</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row contractor-detail-container contractor-landing">
					<div class="col-lg-6">
						<p
							class="contractor-detail-container__items contractor-detail-container__username">${ list.getUsername() }</p>
						<p
							class="contractor-detail-container__items contractor-detail-container__company-name">Name:
							${ list.getFirstName() } ${ list.getLastName() }</p>
						<p
							class="contractor-detail-container__items contractor-detail-container__company-name">Company
							Name: ${ list.getCompany() }</p>
					</div>
					<div class="col-lg-6">
						<p
							class="contractor-detail-container__items contractor-detail-container__location">Location:
							${ list.getLocation() }Chennai</p>
						<p
							class="contractor-detail-container__items contractor-detail-container__email">Email:
							${ list.getEmail() }</p>

					</div>
				</div>
				<div class="contractor-detail-container">
					<div class="row contracter-stats">
						<div class="col-lg-2 contracter-stats__items__container">
							<div class="contracter-stats__items">29</div>
							<div class="contracter-stats__items--description">Ongoing
								Projects</div>
						</div>
						<div class="col-lg-2 contracter-stats__items__container">
							<div class="contracter-stats__items">9</div>
							<div class="contracter-stats__items--description">Delayed
								Projects</div>
						</div>
						<div class="col-lg-2 contracter-stats__items__container">
							<div class="contracter-stats__items">${ list.getNoOfClient() }</div>
							<div class="contracter-stats__items--description">Number of
								Clients</div>
						</div>
						<div class="col-lg-2 contracter-stats__items__container">
							<div class="contracter-stats__items">$ ${ list.getAnnualRevenue() }
							</div>
							<div class="contracter-stats__items--description">Annual
								Revenue</div>
						</div>

						<div class="col-lg-2"></div>
					</div>
				</div>
				<div class="chart-section">
					<div class="row">
						<div class="col-lg-4">
							<!--  	<div class="row">
								<div class="col-lg-4">
									<div class="chart-section__items-container">
										<div class="chart-section__ongoing"></div>
										<div class="chart-section__ongoing-description">Ongoing</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="chart-section__items-container">
										<div class="chart-section__completed"></div>
										<div class="chart-section__ongoing-description">Completed</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="chart-section__items-container">
										<div class="chart-section__delayed"></div>
										<div class="chart-section__ongoing-description">Delayed</div>
									</div>
								</div>
							</div>
							-->
							<div class="row chart-section__pie">
								<div class="col-lg-12">
									<div class="card card-2">
										<div class="chart-section__pie__inner-container">
											<canvas id="myChart" width="400" height="400"></canvas>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="row chart-section__response-count">
								<div class="col-lg-12">
									<div class="card card-2">
										<div class="chart-section__response-count__container">
											<div class="contracter-stats__items">10</div>
											<div class="contracter-stats__items--description">Responses
												Count</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row chart-section__response-count">
								<div class="col-lg-12">
									<div class="card card-2">
										<div class="chart-section__response-count__container">
											<div class="contracter-stats__items">24</div>
											<div class="contracter-stats__items--description">Pending
												Projects</div>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4"></div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>

	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
	<script src=${pageContext.request.contextPath}/js/contractor-chart.js></script>
	<script src=${pageContext.request.contextPath}/js/main.js async defer></script>
</body>

</html>