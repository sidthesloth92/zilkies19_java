<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/CricAlert/css/sign-in.css">
    <title>Login</title>
</head>
<body>
    <div class="header">
        <img src="/CricAlert/assets/images/logo-1_burned.png" alt="CricAlert!"/>
        <%--  --%>
        <div class="header__alert" style="<%= request.getAttribute("visibility") %>"><%= request.getAttribute("alertMessage") %></div>
    </div>
    <div class="credentials">
        <div class="credentials__title">
            <div id="credentials__title__login-id" class="credentials__title__login credentials__title__active" onclick="activeToggle('login')">SIGN IN</div>
            <div id="credentials__title__signup-id" class="credentials__title__signup credentials__title__inactive" onclick="activeToggle('signUp')">SIGN UP</div>
        </div>
        <div id="credentials__login-id" class="credentials__login">
            <form name="login-form" action="/CricAlert/Login" onsubmit="return validateLoginForm()" method="POST">
                <input name="username" type="text" placeholder="Username"/>
                <input name="password" type="password" placeholder="Password"/>
                <input class="credentials__login__submit" type="submit" value="LOGIN"/>
            </form>
        </div>
        <div id="credentials__signup-id" class="credentials__signup">
            <form name="signup-form" action="/CricAlert/Registration" onsubmit="return validateSignUpForm()" method="POST">
                <input name="name" type="text" placeholder="Name"/>
                <input name="email-id" type="email" placeholder="Email"/>
                <input name="username" type="text" placeholder="Username"/>
                <input name="password" type="password" placeholder="Password" required/>
                <input class="credentials__signup__submit" type="submit" value="REGISTER"/>
            </form>
        </div>
    </div>
    <!-- <div class="dummy"></div> -->
    <script src="/CricAlert/js/sign-in.js"></script>
</body>
</html>