<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Signup</title>
    <link rel="stylesheet" href=${pageContext.request.contextPath}/css/signup.css>    
    <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah|Indie+Flower|Jua|Metamorphous|Permanent+Marker" rel="stylesheet">
</head>

<body>
    <div class="signup-container">

        <div class="signup-inner-container">

            <div class="signup-inner-container-info">

                <div class="logo-container">
                    <img src="${pageContext.request.contextPath}/images/logo-blue.png" alt="Logo" class="logo" onclick="window.location='/autoratefe/IndexServlet';">
                </div>

                <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Harum earum non fugiat maxime blanditiis adipisci
                    asperiores soluta culpa nihil, neque aliquam ducimus a molestias ipsum ipsam consequatur architecto animi
                    similique sit sunt eum quasi inventore tempora? Quia velit alias minus ducimus eius ipsam nulla, vel
                    consequatur expedita cupiditate sit temporibus magni rerum blanditiis, earum voluptas corrupti nam qui
                    porro iure ea, placeat quod similique. Distinctio quibusdam dignissimos laboriosam consequuntur reprehenderit
                    exercitationem neque alias esse culpa. Fugiat veritatis laborum magnam magni libero tenetur.</p>

                <footer>
                    <p class="copyright">Autorate © 2018</p>
                </footer>

            </div>

            <div class="signup-inner-container-main">
            	<% if(request.getParameter("message") != null){ %>
                    <script>alert("${param.message}");</script>
                <% } %>

                <form action="/autoratefe/SignupServlet" onsubmit="return validateSignupForm()" name="signup-form" method="POST">
                    <h2>Signup</h2>
                    <c:set var="list" value="${errorMessages}" scope="session" />
                    <input type="text" placeholder="First Name" name="firstname" required onfocusout="validateFirstName()">
                    <p id="firstname-error">${list.get(0) }</p>
                    <input type="text" placeholder="Last Name" name="lastname" required onfocusout="validateLastName()">
                    <p id="lastname-error">${list.get(1) }</p>
                    <input type="text" placeholder="Username" name="username" required onfocusout="validateUsername()">
                    <p id="username-error">${list.get(2) }</p>
                    <input type="text" placeholder="Email ID" name="email-id" required onfocusout="validateEmailId()">
                    <p id="email-id-error">${list.get(3) }</p>
                    <input type="password" placeholder="Password" name="password" required onfocusout="validatePassword()">
                    <p id="password-error">${list.get(4) }</p>
                    <input type="password" placeholder="Confirm Password" name="confirm-password" required onfocusout="validateConfirmPassword()">
                    <p id="confirm-password-error">${list.get(5) }</p>
                    <button type="submit">Signup</button>
                </form>

            </div>

        </div>

    </div>
    <script src="${pageContext.request.contextPath}/js/signup.js"></script>
</body>

</html>