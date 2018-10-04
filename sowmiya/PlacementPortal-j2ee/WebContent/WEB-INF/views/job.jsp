<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>job details</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${ pageContext.request.contextPath}/css/sidebar.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link href="${ pageContext.request.contextPath}/css/student.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="${ pageContext.request.contextPath}/css/job.css" />
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Alex+Brush"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/header.css" />
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
	<!-- side bar -->
		<%@include file="student-sidebar.jsp"%>
	
		<!-- job content-->
		<div class="col-lg-8 body__content">
			<div class="row content">
				<div class="row content__jobs">
					<div class="col-lg-3 col-md-3  jobs__details">JOB-DETAILS</div>
					<div class="col-lg-3 col-md-3  jobs__details">CAMPUS-DATE</div>
					<div class="col-lg-3 col-md-3  jobs__details">APPLY-DATE</div>
					<div class="col-lg-3 col-md-3 jobs__details">STATUS</div>
				</div>
				<c:forEach items="${company}" var="company_detail">
					<div class="row details">
						<div class="col-lg-3 col-md-3 col-sm-12 details__company">
							<span class="row"> <c:out
									value="${company_detail.getCompanyName()}" />
							</span>
							<p class="row">
								<c:out value="${company_detail.getDesignation()}" />
							</p>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 details__company">
							<div class="row company__campus-date">
								<label class="col-lg-7">CAMPUS-DATE:</label>
								<p class="col-lg-5">
									<c:out value="${company_detail.getDayOfRecruitment()}" />
								</p>
							</div>
							<div class="row company__salary">
								<label class="col-lg-7">SALARY:</label>
								<p class="col-lg-5">
									<c:out value="${company_detail.getPayment()}" />
								</p>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 details__company">
							<label class="col-lg-7  apply-date">LAST DATE TO REGISTER</label>
							<p class="col-lg-5 ">
								<c:out value="${company_detail.getLastDateForRegistration()}" />
							</p>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 details__company">
							<form action="job" method="POST">
								<input type="hidden" name="id"
									value="<c:out value="${company_detail.getCompanyId()}"/>" />
								<input type="submit" value="APPLY">
							</form>
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