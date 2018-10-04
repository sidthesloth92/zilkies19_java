<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>student profile</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/sidebar.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/print.css" type="text/css"
	media="print" />
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
		<!-- nav-bar -->
		<%@include file="student-sidebar.jsp"%>
		<!-- student-profile -->
		<div class="col-lg-7 col-md-7 col-sm-12">
			<div class="row">
				<div class="col-lg-11 col-md-11 col-sm-11 profile-container">
					<div class="row student-profile">
						<div class="row profile">
							<p>PROFILE</p>
						</div>
						<div class="row image" style="padding: 10px;">
							<div class="col-lg-1 col-md-1 col-sm-3 profile__image">
								<img src="assets/image.png" />
							</div>
							<div class="col-lg-10 col-md-10 col-sm-10 details--container">
								<p class="student-profile__details">
									<label class="title">University RollNumber: </label>
									<span> <c:out value="${student.getStudentId()}" />
									</span>
								</p>
								<p class="student-profile__details">
									<label class="title">NAME: </label>
									<span><c:out value="${student.getName()}" /> </span>
								</p>
								<p class="student-profile__details">
									<label class="title">DEPARTMENT</label>
									<span> <c:out value="${student.getDepartment()}" />
									</span>
								</p>
							</div>
						</div>
					</div>
				</div>
				<!-- row closed in before line -->
				<!--  -->
				<div class="row academic-qualification-printOnly">
					<div class="row qualification__title">
						<p class="col-lg-10 col-md-10">ACADEMIC QUALIFICATION</p>
					</div>
					<div class="col-lg-10 col-md-10 col-sm-12 details--container"
						id="project">
						<p class="qualification__details">
							<label class="title">CGPA: </label>
							<span><c:out value="${student.getCgpa()}" /></span>
						</p>
						<p class="qualification__details">
							<label class="title">HSC: </label>
							<span><c:out value="${student.getMarkXII()}" /></span>
						</p>
						<p class="qualification__details">
							<label class="title">SSLC: </label>
							<span><c:out value="${student.getMarkX()}" /></span>
						</p>
					</div>
				</div>
			</div>
			<!--</div>-->
			<!-- project -->
			<!--<div class="col-lg-7" style="border:2px solid;">-->
			<div class="row">
				<div class="col-lg-11 col-md-11 col-sm-11 profile-container">
					<div class="row student-project">
						<div class="row project">
							<p>PROJECT</p>
							<div class="project__edit">
								<i class="fas fa-plus" onclick="showModal('project')"></i>
							</div>
						</div>
						<c:forEach items="${project}" var="project_detail">
							<div class="row" style="justify-content: center;">
								<div class="col-lg-10 col-md-10 col-sm-12 details--container"
									id="project">
									<div class="icon">
										<i class="far fa-edit"
											onclick="displayEditForm('<c:out value="${project_detail.getProfileDetailId()}" />','project')"
											style="align-self: flex-end"></i>
										<i class="fa fa-trash"
											onclick="deleteDetails('project','<c:out value="${project_detail.getProfileDetailId()}" />')"></i>
									</div>
									<p class="project__details">
										<label class="title">TITLE: </label>
										<span> <c:out value="${project_detail.getTitle()}" />
										</span>
									</p>
									<p class="project__details">
										<label class="title">DESCRIPTION: </label>
										<span><c:out value="${project_detail.getDescription()}" />
										</span>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- row closed -->
				<!-- certifications -->
				<div class="col-lg-11 col-md-11 col-sm-11 profile-container">
					<div class="row student-certifications">
						<div class="row certifications">
							<p>CERTIFICATIONS</p>
							<div class="col-lg-1 col-md-1 col-sm-1 certifications__edit">
								<i class="fas fa-plus" onclick="showModal('course')"></i>
							</div>
						</div>
						<%
							int i = 0;
						%>
						<c:forEach items="${courses}" var="course">
							<%
								i = i + 1;
							%>
							<div class="row details" style="justify-content: center;">
								<div class="col-lg-10 col-md-10 col-sm-12 details--container"
									id="certification">
									<div class="icon">
										<i class="far fa-edit"
											onclick="displayEditForm('<c:out value="${course.getProfileDetailId()}" />','course')"
											style="align-self: flex-end"></i>
										<i class="fa fa-trash"
											onclick="deleteDetails('course','<c:out value="${course.getProfileDetailId()}" />')"></i>
									</div>
									<p class="certifications__details">
										<label class="title">COURSE-TITLE: </label>
										<span> <c:out value="${course.getTitle()}" />
										</span>
									</p>
									<p class="certifications__details">
										<label class="title">INSTITUTION: </label>
										<span> <c:out value="${course.getDescription()}" />
										</span>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- achievements-->
				<div class="col-lg-11 col-md-11 col-sm-11 profile-container">
					<div class="row student-achievements">
						<div class="row achievements">
							<p>ACHIEVEMENTS</p>
							<div class="project__edit">
								<i class="fas fa-plus" onclick="showModal('achievement')"></i>
							</div>
						</div>
						<c:forEach items="${achievement}" var="achievement_detail">
							<div class="row" style="justify-content: center;">
								<div class="col-lg-10 col-md-10 col-sm-12 details--container"
									id="achievement">
									<div class="icon">
										<i class="far fa-edit"
											onclick="displayEditForm('<c:out value="${achievement_detail.getProfileDetailId()}" />','achievement')"
											style="align-self: flex-end"></i>
										<i class="fa fa-trash"
											onclick="deleteDetails('achievement','<c:out value="${achievement_detail.getProfileDetailId()}" />')"></i>
									</div>
									<p class="achievements__details">
										<label class="title">COURSE-TITLE: </label>
										<span> <c:out value="${achievement_detail.getTitle()}" />
										</span>
									</p>
									<p class="achievements__details">
										<label class="title">INSTITUTION: </label>
										<span> <c:out
												value="${achievement_detail.getDescription()}" />
										</span>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!-- personal-detail -->
		<div class="col-lg-2 col-md-4 col-sm-11 profile-container">
			<div class="row academic-qualification">
				<div class="row qualification__title">
					<p class="col-lg-10 col-md-10">ACADEMIC QUALIFICATION</p>
				</div>
				<div class="col-lg-10 col-md-10 col-sm-12 details--container"
					id="academic">
					<p class="qualification__details">
						<label class="title">CGPA: </label>
						<span><c:out value="${student.getCgpa()}" /></span>
					</p>
					<p class="qualification__details">
						<label class="title">HSC: </label>
						<span><c:out value="${student.getMarkXII()}" /></span>
					</p>
					<p class="qualification__details">
						<label class="title">SSLC: </label>
						<span><c:out value="${student.getMarkX()}" /></span>
					</p>
				</div>
			</div>
		</div>
		<div class="col-lg-1 print_resume">
			<i class="fa fa-print" onclick="printResume()"></i>
		</div>
	</div>
	</div>
	<!-- 	footer  -->
	<%@include file="footer.jsp"%>
	<!-- FORM  -->
	<div class="modal-container">
		<form class="col-lg-5 form" name="profile-form">
			<div class="row details__container" style="display: flex">
				<div class="close-icon--close">
					<i class="far fa-times-circle" onclick="hideModal()"></i>
				</div>
				<div class="errormsg-container">
					<span class="errormsg"></span>
				</div>
				<div class="details__wrapper">
					<label class="row profile-title" for="title"></label>
					<input type="text" placeholder="TITLE" name="title"
						class="row details__input" required />
				</div>
				<div class="details__wrapper">
					<label class="row profile-description" for="description"></label>
					<textarea placeholder="DESCRIPTION" name="description"
						class="row details__input" rows="10"></textarea>
				</div>
				<button type="submit" class="col-lg-8 col-md-8 col-sm-8"
					id="button--submit">SAVE</button>
			</div>
		</form>
	</div>
	<script src="${pageContext.request.contextPath}/js/home.js"></script>
	<script src="${pageContext.request.contextPath}/js/student.js"></script>
</body>