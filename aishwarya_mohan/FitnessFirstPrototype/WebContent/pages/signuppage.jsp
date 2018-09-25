<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>GoFit-Sign Up</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Bubblegum+Sans" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gridstylesheet.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerfooterstyle.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signupstylesheet.css">

</head>

<body>
     <header class="row">
        <div class="logo-box col-sm-12 col-md-6 col-lg-3">
            <div class="logo-image-box">
                <img src="${pageContext.request.contextPath}/assets/logo-img1.png" alt="logo">
            </div>
            
            <div class="logo-name-box">
                <div class="logo-text-box row">
                    Fit 'n Flair
                </div>
                <div class="logo-subtext-box row">
                    Your Diet Companion
                </div>
            </div>

        </div>

    </header>

    <div class="wrapper">
        <div class="signup-details-box col-sm-12 col-md-6 col-lg-4">
            <form action="customisationpage.html" class="signup-details-box_wrapper" name="signupform">
                    <input type="text" placeholder="First name" name="firstname" onfocusout="return checkFirstName()">
                    <input type="text" placeholder="Last name" name="lastname" onfocusout="return checkLastName()">
                    <input type="text" placeholder="User name" name="username" onfocusout="return checkUserName()">
                    <input type="password" placeholder="Password" name="password" onfocusout="return checkPassword()">
                    <input type="password" placeholder="Confirm Password" name="confirmedpassword" onfocusout="return checkConfirmedPassword()">
                    <input type="text" placeholder="Email ID" name="email" onfocusout="return checkEmail()">
                    <input type="text" placeholder="Mobile number" name="phone" onfocusout="return checkPhone()">
                    <input type="button" placeholder="sign up" id="signup-submit-button" value="Sign up" onclick="return submitForm()">
                    <div id="error-text"></div>
            </form>
            
        </div>
        
    </div>
    
    <footer class="row">
        <div class="link-box_social col-sm-12 col-md-12 col-lg-12">
            <i class="fab fa-facebook-f"></i>
            <i class="fab fa-twitter"></i>
            <i class="fab fa-instagram"></i>
            <i class="fab fa-blogger"></i>
        </div>
        <div class="copyright-box col-sm-12 col-md-12 col-lg-12">
            <p>2018<i class="far fa-copyright"></i> Fit 'n Flair, Inc.</p>
        </div>
    </footer>
    <script src="${pageContext.request.contextPath}/js/signupvalidation.js"></script>
    

</body>

</html>