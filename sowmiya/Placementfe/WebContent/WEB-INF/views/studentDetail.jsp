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
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/sidebar.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/job.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student-detail.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/company.css" />
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
	<div class="row container__body">
		<!-- sidenav -->
		<%@include file="admin-sidebar.jsp"%>
		<!-- content -->
		<div class="body__content col-lg-10 col-sm-12">
			<div class="row button--add">
				<button onclick="showModal()">
					<i class="fas fa-plus"></i>
					ADD STUDENT
				</button>
			</div>
			<span class="delete_msg"></span>
			<div class="row form__wrapper">
				<form class="col-lg-5 form" id="register-form"
					style="align-self: center">
					<span id="empty"></span>
					<div class="row details__container" id="modal">
						<div class="close-icon--close">
							<i class="far fa-times-circle" onclick="hideModal('modal')"></i>
						</div>
						<span id="msg"></span>
						<div class="details__wrapper">
							<label class="row" for="registration-number">REGISTER
								NUMBER</label>
							<input type="text" placeholder="Registration Number"
								name="register-number" class="row details__input"
								onfocusout="return validateRegisterNumber('register-form',0)&&isExists()" />
						</div>
						<span class="errormsg"></span>
						<div class="details__wrapper">
							<label class="row" for="student-name">STUDENT-NAME</label>
							<input type="text" placeholder="student-name" name="student-name"
								class="row details__input"
								onfocusout="return validateStudentName('register-form',1)" />
						</div>
						<span class="errormsg"></span>
						<div class="details__wrapper">
							<label class="row" for="department">DEPARTMENT</label>
							<select name="department" class="row details__input">
								<option>DEPARTMENT</option>
								<option value="CSE">CSE</option>
								<option value="ECE">ECE</option>
								<option value="EI">EI</option>
								<option value="EEE">EEE</option>
							</select>
						</div>
						<!-- marks-->
						<div class="details__wrapper">
							<label class="row" for="hsc">HSC PERCENTAGE</label>
							<input type="text" placeholder="HSC Marks" name="hsc"
								class="row details__input"
								onfocusout="return validatePercentage('hsc',2,'register-form');" />
						</div>
						<span class="errormsg"></span>
						<div class="details__wrapper">
							<label class="row" for="department">SSLC PERCENTAGE</label>
							<input type="text" placeholder="SSLC Marks" name="sslc"
								class="row details__input"
								onfocusout="return validatePercentage('sslc',3,'register-form');" />
						</div>
						<span class="errormsg"></span>
						<div class="details__wrapper">
							<label class="row" for="cgpa">CGPA</label>
							<input type="text" placeholder="CGPA" name="cgpa"
								class="row details__input"
								onfocusout="return validateCgpa('register-form',4)" />
						</div>
						<span class="errormsg"></span>
						<div class="details__wrapper">
							<label class="row" for="arrear-count">ARREAR COUNT</label>
							<input type="text" placeholder="arrear-count" name="arrear-count"
								class="row details__input"
								onfocusout="return validateArrearCount('register-form',5)" />
						</div>
						<span class="errormsg"></span>
						<div class="details__wrapper">
							<label class="row" for="arrear-count">EMAIL</label>
							<input type="text" placeholder="email" name="email"
								class="row details__input" onfocusout="return validateEmail()" />
						</div>
						<span class="errormsg"></span>
						<button type="button"
							class="col-lg-8 col-md-8 col-sm-8 button--save">SAVE</button>
					</div>
				</form>
				<!-- edit -->
				<form class="col-lg-5 form" id="edit-form" name="edit-form">
					<span id="empty"></span>
					<div class="row details__container" id="edit-modal">
						<div class="close-icon--close">
							<i class="far fa-times-circle" onclick="hideModal('edit-modal')"></i>
						</div>
						<span id="edit_msg"></span>
						<div class="details__wrapper">
							<label class="row" for="registration-number">REGISTER
								NUMBER</label>
							<input type="text" placeholder="Registration Number"
								name="register-number" class="row details__input" />
						</div>
						<span class="errormsg"></span>
						<div class="details__wrapper">
							<label class="row" for="student-name">STUDENT-NAME</label>
							<input type="text" placeholder="student-name" name="student-name"
								class="row details__input"
								onfocusout="return validateStudentName('edit-form',8)" />
						</div>
						<span class="errormsg"></span>
						<div class="details__wrapper">
							<label class="row" for="department">DEPARTMENT</label>
							<select name="department" class="row details__input">
								<option>DEPARTMENT</option>
								<option value="CSE">CSE</option>
								<option value="ECE">ECE</option>
								<option value="EI">EI</option>
								<option value="EEE">EEE</option>
							</select>
						</div>
						<!-- marks-->
						<div class="details__wrapper">
							<label class="row" for="hsc">HSC PERCENTAGE</label>
							<input type="text" placeholder="HSC Marks" name="hsc"
								class="row details__input"
								onfocusout="return validatePercentage('hsc',9,'edit-form');" />
						</div>
						<span class="errormsg"></span>
						<div class="details__wrapper">
							<label class="row" for="department">SSLC PERCENTAGE</label>
							<input type="text" placeholder="SSLC Marks" name="sslc"
								class="row details__input"
								onfocusout="return validatePercentage('sslc',10,'edit-form');" />
						</div>
						<span class="errormsg"></span>
						<div class="details__wrapper">
							<label class="row" for="cgpa">CGPA</label>
							<input type="text" placeholder="CGPA" name="cgpa"
								class="row details__input"
								onfocusout="return validateCgpa('edit-form',11)" />
						</div>
						<span class="errormsg"></span>
						<div class="details__wrapper">
							<label class="row" for="arrear-count">ARREAR COUNT</label>
							<input type="text" placeholder="arrear-count" name="arrear-count"
								class="row details__input"
								onfocusout="return validateArrearCount('edit-form',12)" />
						</div>
						<span class="errormsg"></span>
						<button type="button"
							class="col-lg-8 col-md-8 col-sm-8 button--edit">SAVE</button>
					</div>
				</form>
			</div>
			<div class="row details-table">
				<div class="row">
					<div class="col-lg-2 student__details">REGISTER NUMBER</div>
					<div class="col-lg-2  student__details">NAME</div>
					<div class="col-lg-2 student__details">DEPARTMENT</div>
					<div class="col-lg-1 student__details">HSC</div>
					<div class="col-lg-1 student__details">SSLC</div>
					<div class="col-lg-1 student__details">CGPA</div>
					<div class="col-lg-1 student__details">ARREAR COUNT</div>
					<div class="col-lg-2 student__details">OPERATIONS</div>
				</div>
				<%
					int i = 0;
				%>
				<c:forEach items="${students}" var="student">
					<%
						i = i + 1;
					%>
					<div class="row details">
						<div class="col-lg-2 col-md-3 col-sm-12 details__info">
							<c:out value="${student.getStudentId()}" />
						</div>
						<div class="col-lg-2 col-md-3 col-sm-12 details__info">
							<c:out value="${student.getName()}" />
						</div>
						<div class="col-lg-2 col-md-3 col-sm-12 details__info">
							<c:out value="${student.getDepartment()}" />
						</div>
						<div class="col-lg-1 col-md-3 col-sm-12 details__info">
							<c:out value="${student.getMarkXII()}" />
						</div>
						<div class="col-lg-1 col-md-3 col-sm-12 details__info">
							<c:out value="${student.getMarkX()}" />
						</div>
						<div class="col-lg-1 col-md-3 col-sm-12 details__info">
							<c:out value="${student.getCgpa()}" />
						</div>
						<div class="col-lg-1 col-md-3 col-sm-12 details__info">
							<c:out value="${student.getArrearCount()}" />
						</div>
						<div class="col-lg-1 col-md-3 col-sm-12 details__info">
							<i class="far fa-edit" onclick="displayEditForm('<%=i%>')"></i>
						</div>
						<div class="col-lg-1 col-md-3 col-sm-12 details__info">
							<i class="fas fa-user-minus"
								onclick="deleteDetails('student','<c:out value="${student.getStudentId()}" />',<%= i%>)"></i>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script src="${pageContext.request.contextPath}/js/registerStudent.js"></script>
</body>
</html>