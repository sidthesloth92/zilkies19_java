<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>student-details</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/job.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student-detail.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/PlacedStudentDetails.css" />
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
	<!-- body content -->
	<div class="row container__body">
		<div class="col-lg-2 body__left-panel" id="nav-bar">
			<div class="row left-panel__side-nav">
				<c:choose>
					<c:when test="${role=='student'}">
						<a class="row side-nav__link" href="#">
							<i class="col-lg-2 fas fa-tachometer-alt"></i>
							<span class="col-lg-7 label">DASHBOARD</span>
						</a>
						<a class="row side-nav__link" href="studentProfile">
							<i class="col-lg-2 fas fa-user"></i>
							<span class="col-lg-7 label">PROFILE</span>
						</a>
						<a class="row side-nav__link" href="settings">
							<i class="col-lg-2 fas fa-cog"></i>
							<span class="col-lg-7 label">SETTINGS</span>
						</a>
						<p class="row apps">APPS</p>
						<a class="row side-nav__link" href="job">
							<i class="fas fa-briefcase"></i>
							<span class="col-lg-7 label">JOBS</span>
						</a>
						<a class="row side-nav__link" href="registrationLog">
							<i class="col-lg-2 far fa-file"></i>
							<span class="col-lg-7 label">REGISTRATION LOG</span>
						</a>
						<a class="row side-nav__link" href="placementRecord">
							<i class="fas fa-graduation-cap"></i>
							<span class="col-lg-7 label">PLACEMENT-RECORD</span>
						</a>
						<a class="row side-nav__link" href="LogoutController">
							<i class="fas fa-sign-out-alt"></i>
							<span class="col-lg-7 label">LOGOUT</span>
						</a>
					</c:when>
					<c:otherwise>
						<a class="row side-nav__link" href="student">
							<i class="col-lg-2 fas fa-user"></i>
							<span class="col-lg-7 label">STUDENT</span>
							<a class="row side-nav__link" href="admin">
								<i class="col-lg-2 far fa-building"></i>
								<span class="col-lg-7 label">COMPANY</span>
								<a class="row side-nav__link" href="placementRecord">
									<i class="fas fa-graduation-cap"></i>
									<span class="col-lg-7 label">PLACEMENT-RECORD</span>
								</a>
							</a>
							<a class="row side-nav__link" href="LogoutController">
								<i class="fa fa-sign-out"></i>
								<span class="col-lg-7 label">LOGOUT</span>
							</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- content -->
		<div class="body__content col-lg-10 col-md-10 col-sm-12">
			<div class="row details-table">
				<div class="row">
					<div class="col-lg-2 student__details">S.NO</div>
					<div class="col-lg-8  student__details">NAME</div>
					<div class="col-lg-2 student__details">DEPARTMENT</div>
				</div>
				<%
					int i = 0;
				%>
				<c:forEach items="${placement}" var="student">
					<%
						i = i + 1;
					%>
					<div class="row details">
						<div class="col-lg-2 col-md-3 col-sm-12 details__info">
							<%=i%>
						</div>
						<div class="col-lg-8 col-md-3 col-sm-12 details__info">
							<c:out value="${student.getName()}" />
						</div>
						<div class="col-lg-2 col-md-3 col-sm-12 details__info">
							<c:out value="${student.getDepartment()}" />
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- FOOTER -->
	<%@include file="footer.jsp"%>
</body>
</html>