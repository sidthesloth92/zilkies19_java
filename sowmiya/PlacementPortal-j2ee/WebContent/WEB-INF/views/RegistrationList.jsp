<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="function"
	uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>company</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--             STYLESHEETS                     -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/company.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student-detail.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/RegistrationList.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/job.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/sidebar.css" />
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
<body onload="getCompany()">
	<!--     WRAPPER    -->
	<!--HEADER -->
	<%@include file="header.jsp"%>
	<!--  BODY CONTENT -->
	<div class="row container__body">
		<!--  SIDE NAV -->
		<%@include file="admin-sidebar.jsp"%>
		<!--  COMPANY CONTENT -->
		<div class="body__content col-lg-8 col-sm-12">
			<div class="row details-container">
				<div class="col-lg-7 details-table">
					<form class="row record-form" method="post" action="registrationList">
						<select name="company" class="col-lg-8 col-md-8 col-sm-8" onchange="this.form.submit()">
							<option value="">COMPANY NAME</option>
							<c:forEach items="${company}" var="company_detail">
								<option value="${company_detail.getCompanyId()}">
									<c:out value="${company_detail.getCompanyName()}" />
								</option>
							</c:forEach>
						</select>
					</form>
				</div>
			</div>
			<c:choose>
				<c:when test="${function:length(students) gt 0}">
					<div class="row">
						<div class="col-lg-4 student__details">REGISTER NUMBER</div>
						<div class="col-lg-4  student__details">NAME</div>
						<div class="col-lg-4 student__details">DEPARTMENT</div>
					</div>
					<c:forEach items="${students}" var="student">
						<div class="row details">
							<div class="col-lg-4 col-md-4 col-sm-12 details__info">
								<p>
									<c:out value="${student.getStudentId()}" />
								</p>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-12 details__info">
								<p>
									<c:out value="${student.getName()}" />
								</p>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-12 details__info">
								<p>
									<c:out value="${student.getDepartment()}" />
								</p>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="details__info">
						<p><c:out value="${notFound}"/></p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- 	footer  -->
	<%@include file="footer.jsp"%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/registrationList.js"></script>
</body>
</html>