<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="col-lg-2 body__left-panel" id="nav-bar">
		<div class="row left-panel__side-nav">
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
					<i class="fas fa-sign-out-alt"></i>
					<span class="col-lg-7 label">LOGOUT</span>
				</a>
		</div>
	</div>
</body>
</html>