<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="function"
	uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Registration Log</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/sidebar.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link href="${ pageContext.request.contextPath}/css/student.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="${ pageContext.request.contextPath}/css/job.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${ pageContext.request.contextPath}/css/registerLog.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/header.css" />
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Alex+Brush"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
</head>
<body>
	<!-- wrapper -->
	<!-- header -->
	<%@include file="header.jsp"%>
	<!-- body content -->
	<div class="row container__body">
		<!-- SIDE BAR -->
		<%@include file="student-sidebar.jsp"%>
		<!--  REGISTRATION DETAILS -->
		<div class="col-lg-8 body__content">
			<c:choose>
				<c:when test="${function:length(registered_company) gt 0}">
					<div class="row content">
						<div class="row content__jobs">
							<div class="col-lg-3 col-md-3 jobs__details">COMPANY-NAME</div>
							<div class="col-lg-3 col-md-3 jobs__details">PACKAGE</div>
							<div class="col-lg-3 col-md-3 jobs__details">CAMPUS-DATE</div>
							<div class="col-lg-3 col-md-3 jobs__details">APPLIED-DATE</div>
						</div>
						<c:forEach items="${registered_company}" var="company_detail">
							<div class="row details">
								<!-- COMPANY NAME -->
								<div class="col-lg-3 col-md-3 col-sm-12 details__company">
									<div class="row company__salary">
										<label class="col-lg-7">COMPANY NAME:</label>
										<p class="col-lg-5">
											<c:out value="${company_detail.getCompanyName()}" />
										</p>
									</div>
								</div>
								<!-- SALARY -->
								<div class="col-lg-3 col-md-3 col-sm-12 details__company">
									<div class="row company__salary">
										<label class="col-lg-7">SALARY:</label>
										<p class="col-lg-5">
											<c:out value="${company_detail.getPayment()}" />
										</p>
									</div>
								</div>
								<!--  CAMPUS DATE -->
								<div class="col-lg-3 col-md-3 col-sm-12 details__company">
									<div class="row company__campus-date">
										<label class="col-lg-7">CAMPUS-DATE:</label>
										<p class="col-lg-5">
											<c:out value="${company_detail.getDayOfRecruitment()}" />
										</p>
									</div>
								</div>
								<!-- APPLIED DATE -->
								<div class="col-lg-3 col-md-3 col-sm-12 details__company">
									<div class="row company__registered-date">
										<label class="col-lg-7  registered-date">REGISTERED
											DATE</label>
										<p class="col-lg-5 ">
											<c:out value="${company_detail.getRegisteredDate()}" />
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="row empty">
						<p>NO RECORD FOUND</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	</div>
	<!-- footer -->
	<%@include file="footer.jsp"%>
</body>
</html>