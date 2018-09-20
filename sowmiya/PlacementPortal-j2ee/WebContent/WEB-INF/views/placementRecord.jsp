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
<title>Placement Record</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link href="${ pageContext.request.contextPath}/css/student.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/sidebar.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${ pageContext.request.contextPath}/css/job.css" />
<link href="${ pageContext.request.contextPath}/css/placementRecord.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${ pageContext.request.contextPath}/css/registerLog.css" />
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
	<!-- BODY CONTENT -->
	<div class="row container__body">
		<!--  SIDE NAv -->
		<c:choose>
			<c:when test="${role=='student'}">
				<%@include file="student-sidebar.jsp"%>
			</c:when>
			<c:otherwise>
				<%@include file="admin-sidebar.jsp"%>
			</c:otherwise>
		</c:choose>
		<!-- job content-->
		<div class="col-lg-8 body__content">
			<!-- TABLE HEADER -->
			<div class="row content">
				<div class="row content__header">
					<h1>PLACEMENT RECORD 2018</h1>
				</div>
				<c:choose>
					<c:when test="${function:length(placements) gt 0}">
						<div class="row content__jobs">
							<div class="col-lg-4 col-md-3  jobs__details">COMPANY NAME</div>
							<div class="col-lg-1 col-md-3  jobs__details">CSE</div>
							<div class="col-lg-1 col-md-3  jobs__details">ECE</div>
							<div class="col-lg-1 col-md-3 jobs__details">EEE</div>
							<div class="col-lg-1 col-md-3 jobs__details">EI</div>
							<div class="col-lg-1 col-md-3 jobs__details">MECH</div>
							<div class="col-lg-2 col-md-3 jobs__details">TOTAL</div>
						</div>
						<c:forEach items="${placement}" var="placement_detail">
							<div class="row details">
								<!-- COMPANY NAME -->
								<div class="col-lg-4 col-md-3 col-sm-12 details__company">
									<span class="row"> <c:out
											value="${placement_detail.getCompanyName()}" />
									</span>
								</div>
								<!-- CSE -->
								<div class="col-lg-1 col-md-3 col-sm-12 details__company">
									<div class="row department">
										<label class="col-lg-7">CSE:</label>
										<p class="col-lg-5">
											<c:out value="${placement_detail.getCse()}" />
										</p>
									</div>
								</div>
								<!-- ECE -->
								<div class="col-lg-1 col-md-3 col-sm-12 details__company">
									<div class="row department">
										<label class="col-lg-7">ECE:</label>
										<p class="col-lg-5">
											<c:out value="${placement_detail.getEce()}" />
										</p>
									</div>
								</div>
								<!-- EEE -->
								<div class="col-lg-1 col-md-3 col-sm-12 details__company">
									<div class="row department">
										<label class="col-lg-7">EEE:</label>
										<p class="col-lg-5 ">
											<c:out value="${placement_detail.getEee()}" />
										</p>
									</div>
								</div>
								<!-- EI -->
								<div class="col-lg-1 col-md-3 col-sm-12 details__company">
									<div class="row department">
										<label class="col-lg-7">EI:</label>
										<p class="col-lg-5">
											<c:out value="${placement_detail.getEi()}" />
										</p>
									</div>
								</div>
								<!-- MECH -->
								<div class="col-lg-1 col-md-3 col-sm-12 details__company">
									<div class="row department">
										<label class="col-lg-7">MECH:</label>
										<p class="col-lg-5">
											<c:out value="${placement_detail.getMech()}" />
										</p>
									</div>
								</div>
								<!-- TOTAL COUNT -->
								<div class="col-lg-2 col-md-3 col-sm-12 details__company">
									<div class="row department">
										<label class="col-lg-5">TOTAL:</label>
										<p class="col-lg-5">
											<c:out value="${placement_detail.getTotalCount()}" />
										</p>
									</div>
								</div>
								<div class="col-lg-1 col-md-3 col-sm-12 details__company">
									<div class="row button-container">
										<form action="placedStudents" method="POST" class="col-lg-2">
											<input type="hidden" name="id"
												value="<c:out value="${placement_detail.getCompanyId()}"/>" />
											<button type="submit" class="button--submit">
												<i class="fa fa-eye" aria-hidden="true"></i>
											</button>
										</form>
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
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>