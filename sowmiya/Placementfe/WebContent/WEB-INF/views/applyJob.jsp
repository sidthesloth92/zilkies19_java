<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>company details</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/applyJob.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/header.css" />

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Alex+Brush"
	rel="stylesheet">
</head>
<body>
	<!-- wrapper -->
	<!-- header -->
	<%@include file="header.jsp"%>
	<!-- body -->
	<div class="row container__body">
		<!-- sidebar -->
		<div class="col-lg-2 body__left-panel" id="nav-bar">
			<div class="row left-panel__side-nav">
				<a class="row side-nav__link" href="../pages/dashboard.html">
					<i class="col-lg-2 fas fa-tachometer-alt"></i>
					<span class="col-lg-7 label">DASHBOARD</span>
				</a>
				<a class="row side-nav__link" href="student">
					<i class="col-lg-2 fas fa-user"></i>
					<span class="col-lg-7 label">PROFILE</span>
				</a>
				<a class="row side-nav__link">
					<i class="col-lg-2 fas fa-cog"></i>
					<span class="col-lg-7 label">SETTINGS</span>
				</a>
				<a class="row apps">APPS</a>
				<a class="row side-nav__link" href="job">
					<i class="fas fa-briefcase"></i>
					<span class="col-lg-7 label">JOBS</span>
				</a>
				<a class="row side-nav__link" href="registrationLog">
					<i class="col-lg-2 far fa-file"></i>
					<span class="col-lg-7 label">REGISTRATION LOG</span>
				</a>
				<a class="row side-nav__link">
					<i class="fas fa-graduation-cap" href="placementRecord"></i>
					<span class="col-lg-7 label">PLACEMENT-RECORD</span>
				</a>
				<a class="row side-nav__link" href="LogoutController">
					<i class="fas fa-sign-out-alt"></i>
					<span class="col-lg-7 label">LOGOUT</span>
				</a>
			</div>
		</div>
		<!-- company-details -->
		<div class="col-lg-8 body__company">
			<div class="row company_name">
				<div>
					<c:out value="${company_detail.getCompanyName()}" />
				</div>
			</div>
			<div class="row">
				<div class="company__description">
					<label>COMPANY DESCRIPTION:</label>
					<p>
						<c:out value="${company_detail.getCompanyDescription()}" />
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 col-sm-10 job-information">
					<label>JOB INFORMATION:</label>
				</div>
				<form>
					<c:choose>
						<c:when test="${not isDisabled}">
							<div class="col-lg-4 col-sm-1 apply">
								<button type="button"
									onclick="applyForJob('<c:out value="${company_detail.getCompanyId()}" />')">REGISTER</button>
							</div>
						</c:when>
						<c:otherwise>
							<div class="col-lg-4 col-sm-1 ">
								<button type="button" disabled>CLOSED</button>
							</div>
						</c:otherwise>
					</c:choose>
				</form>
			</div>
			<div class="row">
				<div class="col-lg-4 job-information">
					<label>JOB TITLE</label>
				</div>
				<div class="col-lg-4 information__content">
					<p>
						<c:out value="${company_detail.getDesignation()}" />
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 job-information">
					<label>SALARY</label>
				</div>
				<div class="col-lg-4 information__content">
					<p>
						<c:out value="${company_detail.getPayment()}" />
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 job-information">
					<label>JOB LOCATION</label>
				</div>
				<div class="col-lg-4 information__content">
					<p>
						<c:out value="${company_detail.getJobLocation()}" />
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 job-information">
					<label>ELIGIBLITY CRITERIA:</label>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 job-information">
					<label>HSC PERCENTAGE</label>
				</div>
				<div class="col-lg-4 information__content">
					<p>
						<c:out value="${company_detail.getMarkX()} Above" />
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 job-information">
					<label>SSLC PERCENTAGE</label>
				</div>
				<div class="col-lg-4 information__content">
					<p>
						<c:out value="${company_detail.getMarkXII()} Above" />
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 job-information">
					<label>CGPA</label>
				</div>
				<div class="col-lg-4 information__content">
					<p>
						<c:out value="${company_detail.getCgpa()} Above" />
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 job-information">
					<label>ARREAR COUNT</label>
				</div>
				<div class="col-lg-4 information__content">
					<p>
						<c:out value="${company_detail.getArrearCount()} Maximum" />
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 job-information">
					<label>CAMPUS DATE</label>
				</div>
				<div class="col-lg-4 information__content">
					<p>
						<c:out value="${company_detail.getDayOfRecruitment()}" />
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 job-information">
					<label>LAST DATE TO REGISTER</label>
				</div>
				<div class="col-lg-4 information__content">
					<p>${company_detail.getLastDateForRegistration()}</p>
				</div>
			</div>
			<span class="row message"></span>
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
				&copy; 2018, Zilker Technology. All Rights Reserved. Your use of
				this service is subject to our Terms of Use and Privacy & Cookies
				Policy.</div>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/job.js"></script>
</div>
</div>