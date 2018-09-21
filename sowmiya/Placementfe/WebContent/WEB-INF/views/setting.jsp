<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>settings</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/setting.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/student.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/sidebar.css" />
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
		<!-- side-nav -->
		<%@include file="student-sidebar.jsp"%>
		<!-- content -->
		<div class="body__content col-lg-10 col-sm-12">
			<div class="row reset-password">
				<form class="col-lg-5 form">
					<div class="row password__container">
						<div class="password__wrapper">
							<label class="row" for="old-password">OLD PASSWORD*</label>
							<input type="password" placeholder="old-password"
								name="old-password" class="row password__input" />
							<span id="errormsg"></span>
						</div>
						<div class="password__wrapper">
							<label class="row" for="old-password">NEW PASSWORD*</label>
							<input type="password" placeholder="new-password"
								name="new-password" class="row password__input" />
							<span id="errormsg"></span>
						</div>
						<div class="password__wrapper">
							<label class="row" for="old-password">CONFIRM PASSWORD*</label>
							<input type="password" placeholder="confirm-password"
								name="confirm-password" class="row password__input" />
						</div>
						<span id="errormsg"></span>
						<button class="col-lg-8 col-md-8 col-sm-8 button--save">SAVE</button>
					</div>
			</div>
			</form>
		</div>
	</div>
	<!-- footer -->
	<%@include file="footer.jsp"%>
	<script src="../js/home.js"></script>
</body>
</html>