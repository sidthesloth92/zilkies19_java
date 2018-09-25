<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Placement-portal</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="css/style.css" rel="stylesheet">

<link href="https://fonts.googleapis.com/css?family=Alex+Brush"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<style>
.image {
	background-image: url('assets/background.jpeg');
	height: 400px;
}

.container {
	padding-top: 30px;
}

body {
	font: 13px Arial, Helvetica, sans-serif
}

.footer {
	border: 2px solid black;
	background-color: #343642;
	height: 100px;
}

.footer__links {
	display: flex;
	justify-content: center;
	/* border: 2px solid yellow; */
}

.footer__copyrights {
	display: flex;
	justify-content: center;
	padding: 10px;
	font-size: 12px;
	color: white;
	/* border: 2px solid greenyellow; */
}

.copyrights__content {
	padding: 5px;
	display: flex;
	justify-content: center;
	color: #B9B9B9;
}

.footer__icon {
	padding: 10px;
	color: white;
	text-align: center;
	font-size: 30px;
}

.fab {
	border-radius: 50%;
}

.links__facebook, .links__twitter, .links__other {
	display: flex;
	justify-content: center;
	align-self: center;
	padding: 5px;
}

.links__other {
	border-right: 0.5px solid whitesmoke;
}

a {
	color: white;
	text-decoration: none;
	cursor: pointer;
}

.links__other:hover {
	background-color: #5e6670;
}

.feature-container {
	height: 200px;
	background: #6d7993;
}
.header-icon{
width:100px;
height:100px;

}
img{
width:100%;
height:100%;

}
.header-container {
display:flex;
justify-content: center;
margin: 20px;
/* <!--background: #96858f;--> */
}
.header-title {
font-family: 'Alex Brush', cursive;
    font-size: 80px;
    padding: 5px;
    margin-left:10px;
    color: white;
    text-shadow: 3px 3px 5px black;

}
.landing {
background-image:url('assets/emilio-garcia-723189-unsplash.jpg');
background-position: center;
backgreound-size:cover;
}
.outer-container {
	height: 550px;
	display: flex;
	justify-content: center;
}
</style>

</head>
<body>
	<div class="row outer-container landing">
		<div class="header-container">
			<div class="row " style="dispaly:flex;justify-content:center;align-item:center">
				  <%-- <div class="header-icon">
					<img
						src="${pageContext.request.contextPath}/assets/icons8-permanent-job-filled-100.png">
				</div> --%>
				<div class="header-title">
				Placement Portal
				</div>
			</div>
		</div>
	</div>
		
	<div class="footer">
		<div class="row footer__links">
			<div class="col-lg-1 col-sm-6 col-md-6 links__facebook">
				<a href="#"> <i class="fab fa-facebook-f footer__icon"
					alt="facebook icon"></i>
				</a>
			</div>
			<div class="col-lg-1 col-sm-6 col-md-6 links__twitter">
				<a href="#"> <i class="fab fa-twitter footer__icon"
					alt="twitter icon"></i>
				</a>
			</div>
			<div class="col-lg-1"></div>
			<div class="col-lg-2 col-sm-12 col-md-2 links__other">
				<a href="#">About us</a>
			</div>
			<div class="col-lg-2 col-sm-12 col-md-2 links__other">
				<a href="#">Features</a>
			</div>

			<div class="col-lg-2 col-sm-12 col-md-2 links__other">
				<a href="#">Contact us</a>
			</div>
			<div class="col-lg-2 col-sm-12 col-md-2 links__other">
				<a href="#">Privacy policys</a>
			</div>

		</div>
		<div class="row footer__copyrights">
			<div class="col-sm-12 col-md-12 col-lg-12 copyrights__content">
				&copy; 2018, Zilker technology.All Rights Reserved. Your use of this
				service is subject to our Terms of Use and Privacy & Cookies Policy.</div>
		</div>

	</div>

</body>
</html>