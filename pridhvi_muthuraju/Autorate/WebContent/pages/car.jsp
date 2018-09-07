<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Car Info</title>
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/car.css">
    <link rel="stylesheet" href="../../../lib/fontawesome-free-5.2.0-web/css/all.css">
</head>

<body>
    <div class="car-container">

        <div class="topbar">

            <div class="logo-container">
                <img src="../images/logo-green.png" alt="Logo" onclick="window.location='../index.jsp';">
            </div>
            
			<%
 			 if(session.getAttribute("loggedInUser")!=null && session.getAttribute("loggedInUser")!="")
  				{ 
  			%>
  			<div class="user-container">

                <div class="user-container-username">

                    <p onclick="showUserDropdown()">pridhvi</p>

                    <div class="user-container-dropdown">
                        <div>
                            <p onclick="window.location='./user-requests.jsp'">Your Requests</p>
                        </div>
                        <div>
                            <p onclick="window.location='./add-car.jsp'">Add Car Request</p>
                        </div>
                        <form action="../LogoutServlet" method="POST">
                            <input type="submit" value="Logout" />
                        </form>
                    </div>
                </div>

            </div>
  			<%
  			}
  			else
  			{
 			 %>
            <div class="login-container">
                <div class="login">
                    <form action="../LoginServlet" method="POST">
                        <input type="text" placeholder="Username" name="username">
                        <input type="password" placeholder="Password" name="password">
                        <button type="submit">Login</button>
                    </form>
                </div>

                <div class="signup">
                    <label>New user? <a href="./signup.jsp">Signup</a></label>
                </div>
            </div>
			<%
  			}
  			%>
        </div>

        <div class="inner-container">
            <div class="inner-container-car">
                <div class="inner-container-car-name">
                    Renault Duster
                </div>

                <div class="inner-container-car-specs">

                    <p>Engine: Petrol</p>
                    <p>Cylinder: 5</p>
                    <p>Displacement: 1299cc</p>
                    <p>Transmission: 8 speed</p>
                    <p>Power: 85PS</p>
                    <p>Torque: 90Nm</p>
                    <p>Fuel Capacity: 45Litres</p>
                    <p>Kerb Weight: 1999Kg</p>

                </div>

			<%
 			 if(session.getAttribute("loggedInUser")!=null && session.getAttribute("loggedInUser")!="")
  				{ 
  			%>
                <form action="#" class="inner-container-write-review">

                    <div class="inner-container-write-review-text">
                        <textarea name="user-review" id="" cols="30" rows="10" placeholder="Write your review"></textarea>
                    </div>

                    <div class="inner-container-write-review-rating">
                        <p>Rating</p>
                        <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>

                    </div>

                    <button type="submit">Submit</button>

                </form>
            <%
				} 
  			%>
            </div>

            <div class="inner-container-header">
                <h2>User Reviews</h2>
            </div>

            <div class="inner-container-review">

                <p>pridhvi</p>

                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam omnis maiores necessitatibus asperiores
                    culpa, aspernatur minima laborum eligendi laudantium optio voluptate cum nesciunt consequatur perferendis
                    esse debitis mollitia? Veritatis molestiae obcaecati qui ipsam neque quas voluptatem delectus, aspernatur
                    esse sapiente voluptates, aliquid suscipit cupiditate rerum harum? Voluptates tempore vel in?
                </p>

                <p>Rating: 4</p>

            </div>

            <div class="inner-container-review">

                <p>pridhvi</p>

                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam omnis maiores necessitatibus asperiores
                    culpa, aspernatur minima laborum eligendi laudantium optio voluptate cum nesciunt consequatur perferendis
                    esse debitis mollitia? Veritatis molestiae obcaecati qui ipsam neque quas voluptatem delectus, aspernatur
                    esse sapiente voluptates, aliquid suscipit cupiditate rerum harum? Voluptates tempore vel in?
                </p>

                <p>Rating: 4</p>

            </div>

            <div class="inner-container-review">

                <p>pridhvi</p>

                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam omnis maiores necessitatibus asperiores
                    culpa, aspernatur minima laborum eligendi laudantium optio voluptate cum nesciunt consequatur perferendis
                    esse debitis mollitia? Veritatis molestiae obcaecati qui ipsam neque quas voluptatem delectus, aspernatur
                    esse sapiente voluptates, aliquid suscipit cupiditate rerum harum? Voluptates tempore vel in?
                </p>

                <p>Rating: 4</p>

            </div>

            <div class="inner-container-review">

                <p>pridhvi</p>

                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam omnis maiores necessitatibus asperiores
                    culpa, aspernatur minima laborum eligendi laudantium optio voluptate cum nesciunt consequatur perferendis
                    esse debitis mollitia? Veritatis molestiae obcaecati qui ipsam neque quas voluptatem delectus, aspernatur
                    esse sapiente voluptates, aliquid suscipit cupiditate rerum harum? Voluptates tempore vel in?
                </p>

                <p>Rating: 4</p>

            </div>

            <div class="inner-container-review">

                <p>pridhvi</p>

                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam omnis maiores necessitatibus asperiores
                    culpa, aspernatur minima laborum eligendi laudantium optio voluptate cum nesciunt consequatur perferendis
                    esse debitis mollitia? Veritatis molestiae obcaecati qui ipsam neque quas voluptatem delectus, aspernatur
                    esse sapiente voluptates, aliquid suscipit cupiditate rerum harum? Voluptates tempore vel in?
                </p>

                <p>Rating: 4</p>

            </div>

            <footer>
                <p>Autorate © 2018</p>
            </footer>

        </div>

    </div>
    <script src="../js/index.js"></script>
</body>

</html>