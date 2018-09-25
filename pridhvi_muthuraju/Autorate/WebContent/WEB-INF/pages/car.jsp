<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="io.ztech.autorate.beans.Specification"%>
<%@ page import="io.ztech.autorate.beans.User"%>
<%@ page import="io.ztech.autorate.beans.Rating"%>
<%@ page import="io.ztech.autorate.services.AddRatingService"%>
<%@page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Car Info</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/car.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fontawesome/css/all.css">
</head>

<body>
	<div class="car-container">

		<div class="topbar">

			<div class="logo-container">
				<img src="${pageContext.request.contextPath}/images/logo-green.png"
					alt="Logo" onclick="window.location='/Autorate/index.jsp';">
			</div>

			<%
				if (session.getAttribute("status") != null) {
			%>
			<div class="user-container">

				<div class="user-container-username">

					<p onclick="showUserDropdown()"><%=session.getAttribute("username")%>&#9662;
					</p>

					<div class="user-container-dropdown">
						<div>
							<p onclick="window.location='/Autorate/FetchRequestsServlet';">Your
								Requests</p>
						</div>
						<div>
							<p onclick="window.location='/Autorate/FetchAddCarServlet'">Add
								Car Request</p>
						</div>
						<form action="/Autorate/LogoutServlet" method="POST">
							<input type="submit" value="Logout" />
						</form>
					</div>
				</div>

			</div>
			<%
				} else {
			%>
			<div class="login-container">
				<%
					if (request.getParameter("message") != null) {
				%>
				<script>
					alert("${param.message}");
				</script>
				<%
					}
				%>
				<div class="login">
					<form action="/Autorate/LoginServlet" method="POST">
						<input type="text" placeholder="Username" name="username">
						<input type="password" placeholder="Password" name="password">
						<button type="submit">Login</button>
					</form>
				</div>

				<div class="signup">
					<label>New user? <a
						href="${pageContext.request.contextPath}/pages/signup.jsp">Signup</a></label>
				</div>
			</div>
			<%
				}
			%>
		</div>
		<%
			Specification car = (Specification) request.getAttribute("carBean");
		%>

		<div class="inner-container">
			<div class="inner-container-car">
				<div class="inner-container-car-name">
					<%=car.getCarName()%>
				</div>

				<div class="inner-container-car-specs">

					<p>
						ABS:
						<%=car.getAbs()%></p>
					<p>
						Cylinder:
						<%=car.getCylinder()%></p>
					<p>
						Displacement:
						<%=car.getDisplacement()%>cc
					</p>
					<p>
						Transmission:
						<%=car.getTransmission()%>
						speed
					</p>
					<p>
						Power:
						<%=car.getPower()%>PS
					</p>
					<p>
						Torque:
						<%=car.getTorque()%>Nm
					</p>
					<p>
						Fuel Capacity:
						<%=car.getFuelCapacity()%>Litres
					</p>
					<p>
						Kerb Weight:
						<%=car.getKerbWeight()%>Kg
					</p>
					<p>
						Airbag:
						<%=car.getAirbag()%></p>
					<p>
						Drivetrain:
						<%=car.getDrivetrain()%></p>
					<p>
						Engine Type:
						<%=car.getEngineType()%></p>
					<p>
						Wheel Base:
						<%=car.getWheelbase()%>mm
					</p>
					<p>
						Price: &#8377;<%=car.getPrice()%></p>

				</div>



				<%
					if (session.getAttribute("status") != null) {
						if (request.getAttribute("rating") != null) {
							Rating rating = (Rating) request.getAttribute("rating");
				%>
				<form action="/Autorate/EditRatingServlet"
					class="inner-container-write-review">
					<h4>Your Review</h4>
					<input type="hidden" value="<%=car.getCarId()%>" name="car-id"></input>

					<div class="inner-container-write-review-subject">
						<textarea name="subject"><%=rating.getSubject()%></textarea>
					</div>

					<div class="inner-container-write-review-text">
						<textarea name="review"><%=rating.getReview()%></textarea>
					</div>

					<div class="inner-container-write-review-rating">
						<p>Rating</p>
						<select name="rating" required>
							<option value="" disabled selected><%=rating.getRating()%></option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>

					</div>

					<button type="submit">Edit</button>

				</form>

				<%
					} else {
				%>
				<form action="/Autorate/AddReviewRatingServlet"
					class="inner-container-write-review">
					<input type="hidden" name="car-id" value="<%=car.getCarId()%>"></input>
					<input type="hidden" name="username"
						value="<%=session.getAttribute("username")%>"></input>

					<div class="inner-container-write-review-subject">
						<textarea name="subject" placeholder="Subject of review"></textarea>
					</div>

					<div class="inner-container-write-review-text">
						<textarea name="user-review" placeholder="Write your review"></textarea>
					</div>

					<div class="inner-container-write-review-rating">
						<p>Rating</p>
						<select name="rating" required>
							<option value="" disabled selected></option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>

					</div>

					<button type="submit">Submit</button>

				</form>
				<%
					}
					}
				%>
			</div>

			<div class="inner-container-header">
				<h2>User Reviews</h2>
			</div>
			<c:forEach items="${ratings}" var="entry">

				<div class="inner-container-review">

					<p class="inner-container-review__username">
						By <i>${entry.key.getUsername()}</i>
					</p>

					<p class="inner-container-review__subject">
						<strong>${entry.value.getSubject()}</strong>
					</p>
					
					<p class="inner-container-review__rating">Rating:
						${entry.value.getRating()}</p>

					<p class="inner-container-review__review">${entry.value.getReview()}</p>

				</div>
			</c:forEach>

			<footer>
				<p>Autorate © 2018</p>
			</footer>

		</div>

	</div>
	<script src="${pageContext.request.contextPath}/js/index.js"></script>
	<script src="${pageContext.request.contextPath}/js/car.js"></script>
</body>

</html>