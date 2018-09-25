<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Autorate</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/edit-car.css">
<link
	href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah|Indie+Flower|Jua|Metamorphous|Permanent+Marker"
	rel="stylesheet">
</head>

<body onload="getMakes()">

	<div class="container">
        <div class="topbar-admin">
            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo-red.png" alt="Logo" onclick="window.location='/Autorate/FetchAdminServlet';">
            </div>

            <form action="/Autorate/LogoutServlet" method="POST">
            	<input type="submit" value="Logout" />
            </form>
        </div>

        <div class="inner-container-edit-car">

			<div class="inner-container-edit-car__select">
				
				<div>
				
					<select name="makes" onchange="getTypes()" id="select-makes">
						
					</select>
				
				</div>
				
				<div>
				
					<select name="car-types" onchange="getCars()" id="select-car-types">
						
					</select>
				
				</div>
				
				<div>
				
					<select name="cars" onchange="displayCar()" id="select-cars">
						
					</select>
				
				</div>
				
			</div>
			
			<form action="/Autorate/EditCarServlet" class="inner-container-edit-car__edit">
        		
        	</form>

        </div>
        
        
    </div>

<script src="${pageContext.request.contextPath}/js/edit-car.js"></script>
</body>
</html>