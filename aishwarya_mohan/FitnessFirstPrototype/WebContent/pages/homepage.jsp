<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gridstylesheet.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerfooterstyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepagestyle.css">
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Bubblegum+Sans" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
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
        <div class="nav-bar-box col-sm-12 col-md-7 col-lg-7">
            <ul>
                <a href="#main-content">
                    <li>Home</li>
                </a>
                <a href="#about-element_box">
                    <li>About us</li>
                </a>
                <a href="#contact-element_box">
                    <li>Contact us</li>
                </a>
                <a href="#features-box">
                    <li>Features</li>
                </a>
            </ul>
        </div>
        <div class="login-box col-sm-12 col-md-2 col-lg-2">
            <button onclick="showLogInBox()">Login</button>
        </div>

    </header>

    <div class="modal row">
        <div class="modal-content-box col-sm-12 col-md-8 col-lg-4">
            <button onclick="closeLoginBox()">&#10006</button>

            <div>LOGIN</div>
            <form name="loginform">
                <div>
                    <input type="text" placeholder="Username" name="username">
                </div>

                <div>
                    <input type="password" placeholder="Password" name="password">
                </div>

                <div>
                    <input type="button" value="Login" id="loginbutton">
                </div>

                <div id="error-text">
                    
                </div>

            </form>
        </div>
    </div>

    <div class="main-content row" id="main-content">
        <div class="slider-box col-sm-12 col-md-12 col-lg-12">
            <div class="slides row">
                <img src="${pageContext.request.contextPath}/assets/i1.jpg" alt="pic1">
                <span>TRACK YOUR FOOD INTAKE</span>
                <span>Track your food intake to calculate calories and follow your diet plan</span>
            </div>
            <div class="slides row">
                <img src="${pageContext.request.contextPath}/assets/i2.jpg" alt="pic2">
                <span>TRACK YOUR WEIGHT</span>
                <span>Track your weight to view your progress and achieve your fitness goal</span>
            </div>
            <div class="slides row">
                <img src="${pageContext.request.contextPath}/assets/i3.jpeg" alt="pic3">
                <span>START TODAY</span>
                <span>Sign up and customise to let us assist you in your fitness journey</span>
            </div>
        </div>

        <div class="signup-box row">
            <button id="signup-button"><span>Sign up and get started on your fitness journey</span></button>
        </div>



        <div class="features-box row" id="features-box">
            <div class="features-box_text row">Our features &#x21E3;</div>
            <a href="${pageContext.request.contextPath}/pages/bmicalculator.html" class="col-sm-12 col-md-4 col-lg-4">
                <div id="bmi-feature row">
                    <img src="${pageContext.request.contextPath}/assets/bmi.jpg" alt="bmi-image">
                </div>
            </a>

            <a href="${pageContext.request.contextPath}/pages/bmrcalculator.html" class="col-sm-12 col-md-4 col-lg-4">
                <div id="bmr-feature row">
                    <img src="${pageContext.request.contextPath}/assets/bmr.jpg" alt="bmr-image">
                </div>
            </a>

            <a href="${pageContext.request.contextPath}/pages/caloriecalculator.html" class="col-sm-12 col-md-4 col-lg-4">
                <div id="calorie-calculator-feature row">
                    <img src="${pageContext.request.contextPath}/assets/diet.jpg" alt="calorie-image">
                </div>
            </a>


        </div>
    </div>
    <footer>
        <div id="about-element_box" name="about-element_box">
            <h2>About Us</h2>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt ut labore
            et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
            ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit
            esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident,
            sunt in culpa qui officia deserunt mollit anim id est laborum.
        </div>
        <div id="contact-element_box" name="contact-element_box">
            <h2>Contact Us</h2>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt ut labore
            et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
            ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit
            esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident,
            sunt in culpa qui officia deserunt mollit anim id est laborum.
        </div>
        <div class="link-box_social col-sm-12 col-md-12 col-lg-12">
            <i class="fab fa-facebook-f"></i>
            <i class="fab fa-twitter"></i>
            <i class="fab fa-instagram"></i>
            <i class="fab fa-blogger"></i>
        </div>
        <div class="copyright-box col-sm-12 col-md-12 col-lg-12">
            <p>2018 &copy Fit 'n Flair, Inc.</p>
        </div>

    </footer>

    <script src="${pageContext.request.contextPath}/js/homepagescript.js"></script>
	<script src="${pageContext.request.contextPath}/js/loginmodalscript.js"></script>
	<script src="${pageContext.request.contextPath}/js/loginvalidation.js"></script>

</body>

</html>