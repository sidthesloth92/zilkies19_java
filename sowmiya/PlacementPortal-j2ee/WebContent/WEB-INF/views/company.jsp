<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
	href="${pageContext.request.contextPath}/css/sidebar.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/footer.css" />
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
	<!--     WRAPPER    -->
	<!--HEADER -->
	<%@include file="header.jsp"%>
	<!--  BODY CONTENT -->
	<div class="row container__body">
		<!--  SIDE NAV -->
		<%@include file="admin-sidebar.jsp"%>
		<!--  COMPANY CONTENT -->
		<div class="body__content col-lg-10 col-sm-12">
			<div class=" row button--add">
				<button onclick="showModal()">
					<i class="fas fa-plus"></i>
					ADD COMPANY
				</button>
			</div>
			<!--  REGISTER COMPANY FORM -->
			<span class="delete_msg"></span>
			<div class="row form__wrapper">
			<form class="col-lg-5 form" id="company-form">
				<span id="empty"></span>
				<div class="row details__container" id="modal">
					<div class="close-icon--close">
						<i class="far fa-times-circle" onclick="hideModal('modal')"></i>
					</div>
					<span id="msg"></span>
					<div class="details__wrapper">
						<label class="row" for="company-name">COMPANY NAME</label>
						<input type="text" placeholder="COMPANY NAME" name="company-name"
							class="row details__input" />
					</div>
					<div>
						<br>
						<label class="row" for="company-type">COMPANY TYPE</label>
						<select name="company-type" class="row details__input">
							<option>COMPANY TYPE</option>
							<option value="service">SERVICE</option>
							<option value="product">PRODUCT</option>
						</select>
					</div>
					<div class="details__wrapper">
						<label class="row" for="description">COMPANY DESCRIPTION</label>
						<textarea placeholder="COMPANY DESCRIPTION" name="description"
							class="row details__input"></textarea>
					</div>
					<div class="details__wrapper">
						<label class="row" for="location">JOB LOCATION</label>
						<input type="text" name="location" class="row details__input"
							placeholder="LOCATION" />
					</div>
					<div class="details__wrapper">
						<label class="row" for="designation">DESIGNATION</label>
						<input type="text" placeholder="DESIGNATION" name="designation"
							class="row details__input" />
					</div>
					<span class="errormsg"></span>
					<div class="details__wrapper">
						<label class="row" for="package">PACKAGE</label>
						<input type="text" placeholder="PACKAGE" name="package"
							class="row details__input" />
					</div>
					<span class="errormsg"></span>
					<div class="details__wrapper">
						<label class="row" for="recruitment-date">DATE OF
							RECRUITMENT</label>
						<input type="date" placeholder="recruitment-date"
							name="recruitment-date" class="row details__input"
							onfocusout="return validateDate('company-form',2,'recruitment-date')" />
					</div>
					<span class="errormsg"></span>
					<div class="details__wrapper">
						<label class="row" for="registration-date">LAST DATE FOR
							REGISTRATION</label>
						<input type="date" placeholder="registration-date"
							name="registration-date" class="row details__input"
							onfocusout="return validateDate('company-form',3,'registration-date')" />
					</div>
					<span class="errormsg"></span>
					<!--  NOTE -->
					<div class="details__wrapper info">
						<div></div>
						<label>
							<i class="fas fa-info-circle"></i>
							Incase there is no criteria on any one below give zero and for
							arrear count provide 45
						</label>
					</div>
					<div class="details__wrapper">
						<label class="row" for="hsc">HSC PERCENTAGE</label>
						<input type="text" placeholder="HSC Marks" name="hsc"
							class="row details__input"
							onfocusout="return validatePercentage('hsc',4,'company-form');" />
					</div>
					<span class="errormsg"></span>
					<div class="details__wrapper">
						<label class="row" for="department">SSLC PERCENTAGE</label>
						<input type="text" placeholder="SSLC Marks" name="sslc"
							class="row details__input"
							onfocusout="return validatePercentage('sslc',5,'company-form');" />
					</div>
					<span class="errormsg"></span>
					<div class="details__wrapper">
						<label class="row" for="cgpa">CGPA</label>
						<input type="text" placeholder="CGPA" name="cgpa"
							class="row details__input"
							onfocusout="return validateCgpa('company-form',6)" />
					</div>
					<span class="errormsg"></span>
					<div class="details__wrapper">
						<label class="row" for="arrear-count">ARREAR COUNT</label>
						<input type="text" placeholder="arrear-count" name="arrear-count"
							class="row details__input"
							onfocusout="return validateArrearCount('company-form',7)" />
					</div>
					<span class="errormsg"></span>
					<button type="button"
						class="col-lg-8 col-md-8 col-sm-8 button--save" id="submit-button">SAVE</button>
					</a>
				</div>
			</form>
			</div>
			<!--  DISPLAYING COMPANY DETAILS -->
			<div class="row details-container">
				<div class="col-lg-10 details-table">
					<%
						int i = 0;
					%>
					<c:forEach items="${company}" var="company_detail">
						<%
							i = i + 1;
						%>
						<div class="row">
							<div class="row details">
								<div class="row   details__header">
									<p class="col-lg-10 details__company">
										<c:out value="${company_detail.getCompanyName()}" />
									</p>
									<i class="far fa-edit"
										onclick="displayEditForm('<c:out value="${company_detail.getCompanyId()}" />')"
										style="align-self: flex-end"></i>
									<i class="fa fa-trash"
										onclick="deleteDetails('company','<c:out value="${company_detail.getCompanyId()}" />',<%= i%>)"></i>
								</div>
								<div class="col-lg-6">
									<div class="row  details__info">
										<div class="col-lg-3 details__company">COMPANY TYPE</div>
										<p class="details__company">
											<c:out value="${company_detail.getCompanyType()}" />
										</p>
									</div>
									<div class="row  details__info">
										<div class="details__company">DESCRIPTION</div>
										<p class="details__company">
											<c:out value="${company_detail.getCompanyDescription()}" />
										</p>
									</div>
									<div class="row  details__info">
										<div class="col-lg-3 details__company">JOB LOCATION</div>
										<p class="details__company">
											<c:out value="${company_detail.getJobLocation()}" />
										</p>
									</div>
									<div class="row  details__info">
										<div class="col-lg-3 details__company">DESIGNATION</div>
										<p class="details__company">
											<c:out value="${company_detail.getDesignation()}" />
										</p>
									</div>
									<div class="row  details__info">
										<div class="col-lg-3 details__company">PACKAGE</div>
										<p class="details__company">
											<c:out value="${company_detail.getPayment()}" />
										</p>
									</div>
									<div class="row  details__info">
										<div class="col-lg-3 details__company">RECRUITMENT DATE</div>
										<p class="details__company">
											<c:out value="${company_detail.getDayOfRecruitment()}" />
										</p>
									</div>
									<div class="row  details__info">
										<div class="col-lg-3 details__company">LAST DATE FOR
											REGISTRATION</div>
										<c:out value="${company_detail.getLastDateForRegistration()}" />
										</p>
									</div>
								</div>
								<!-- ELIGIBLITY CRITERIA -->
								<div class="col-lg-5">
									<div class="row details__info details__eligiblity">ELIGIBLITY
										CRITERIA</div>
									<div class="row  details__info">
										<div class="col-lg-3 details__company">CGPA</div>
										<p class="details__company">
											<c:out value="${company_detail.getCgpa()}" />
										</p>
									</div>
									<div class="row  details__info">
										<div class="col-lg-3 details__company">HSC PERCENTAGE</div>
										<p class=" details__company">
											<c:out value="${company_detail.getMarkX()}" />
										</p>
									</div>
									<div class="row  details__info">
										<div class="col-lg-3 details__company">SSLC PERCENTAGE</div>
										<p class="details__company">
											<c:out value="${company_detail.getMarkXII()}" />
										</p>
									</div>
									<div class="row  details__info">
										<div class="col-lg-3 details__company">ARREAR COUNT</div>
										<p class=" details__company">
											<c:out value="${company_detail.getArrearCount()}" />
										</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- FOOTER -->
	<%@include file="footer.jsp"%>
	<script src="${pageContext.request.contextPath}/js/company.js"></script>
	<script src="${pageContext.request.contextPath}/js/company.js"></script>
</body>
</html>