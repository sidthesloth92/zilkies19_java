<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Fantasy League</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Indie+Flower|Ruslan+Display" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Allan' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value = '/resources/css/commonstylesheet.css' /> " />
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/indexstylesheet.css' />" />
</head>

<body>
    <div class="header">
        <div class="row">
            <div class="col-sm-12 col-md-6 col-lg-6  top-space">
                <img src="${pageContext.request.contextPath}/images/header.png" class="header-image" />
                <h1 class="fantasy-name">Fantasy League<h1>
            </div>
            <div class="col-lg-2"></div>
            <div class="col-sm-12 col-md-6 col-lg-4 ">

                <div class="row">
                    <div class="col-sm-6 col-md-6 col-lg-6 top-space">
                        <button id="signup-button" class="button signup-button">Sign Up</button>
                    </div>
                    <div class="col-sm-6 col-md-6 col-lg-6  top-space">
                        <button id="login-button" class="button login-button">Log In</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div id="modal-box_signup" class="modal-box">
        <div class="modal-inner">
            <span class="close-button" id="close-button_signup">&times;</span>

            <div class="row">
                <div class="col-md-2 col-lg-2"></div>
                <div class="col-sm-12 col-md-8 col-lg-8">

                    <h3>Sign Up</h3>
                    <form action="${pageContext.request.contextPath}/SignupServlet" onsubmit="validateOnSignup()" method="POST" name="signup-form">
                        <input type="text" name="user-name" class="user-name" onchange="checkNameSignup()" placeholder="User Name" />
                        <div class="name-error_signup"></div>
                        <input type="text" name="email" class="email" onchange="checkEmailSignup()" placeholder="Email ID" />
                        <div class="email-error_signup"></div>
                        <input type="password" name="password" class="password" onchange="checkPasswordSignup()" placeholder="Password" />
                        <div class="password-error_signup"></div>
                        <input type="password" name="confirm-password" class="confirm-password" onchange="checkPasswordMatchSignup()" placeholder="Confirm Password" />
                        <div class="match-error_signup"></div>
                        <button class="button login-button_modal" type="submit" >Sign Up</button>
                    </form>

                </div>
                <div class="col-md-2 col-lg-2"></div>
            </div>

        </div>
    </div>


    <div id="modal-box_login" class="modal-box">
        <div class="modal-inner">
            <span class="close-button" id="close-button_login">&times;</span>

            <div class="row">
                <div class="col-md-2 col-lg-2"></div>
                <div class="col-sm-12 col-md-8 col-lg-8">

                    <h3>Login</h3>
                    <form action="TestServlet" onsubmit="validateOnLogin()" method="POST" name="login-form">
                        <input type="text" name="user-name" onchange="checkNameLogin()" placeholder="User Name" />
                        <div class="name-error_login"></div>
                        <input type="password" name="password" onchange="checkPasswordLogin()" placeholder="Password" />
                        <div class="password-error_login"></div>
                        <button class="button login-button_modal" type="submit">Login</button>
                    </form>

                </div>
                <div class="col-md-2 col-lg-2"></div>
            </div>

        </div>
    </div>

    <div class="container">
        <div class="row center-container_outermost">
            <div class="col-md-2 col-lg-2"></div>
            <div class="col-sm-12 col-md-8 col-lg-8 content-display">
                <h2>Welcome to Fantasy Cricket!! </h2>
                <p>Here you can experience the feel of a real league where u can compete among each others along with
                    the cricketers!!</p>
                <p>Get ready for the battle and invite your friends to battle with them...</p>
                <img src="${pageContext.request.contextPath}/images/icon.png" class="content-image" alt="header's image" />
                <p>Come Join us! It'll be more fun than ever</p>
            </div>
            <div class="col-md-2 col-lg-2"></div>
        </div>
    </div>
    <div class="footer">
        <div class="row">
            <div class="col-md-2 col-lg-2"></div>
            <div class="col-sm-12 col-md-8 col-lg-8 footer-content">
                <ul class="footer-list">
                    <li> <a href="#">About Us</a></li>
                    <li><a href="#"> How to Play</a></li>
                    <li><a href="#">Contact Us</a></li>
                </ul>
                <h4>&copy; Zilker Technology,chennai</h4>
            </div>
            <div class="col-md-2 col-lg-2"></div>
        </div>
    </div>
    <script src="<c:url value='resources/js/indexscript.js'/> "></script>
</body>

</html>