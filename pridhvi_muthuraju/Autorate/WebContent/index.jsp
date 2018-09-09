<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Autorate</title>
    <link rel="stylesheet" href="./css/index.css">

    <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah|Indie+Flower|Jua|Metamorphous|Permanent+Marker" rel="stylesheet">
</head>

<body onload="getMakes()">
    <div class="container">

        <div class="topbar">

            <div class="logo-container">
                <img src="./images/logo-green.png" alt="Logo" onclick="window.location='./index.jsp';">
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
                            <p onclick="window.location='./pages/user-requests.jsp'">Your Requests</p>
                        </div>
                        <div>
                            <p onclick="window.location='./pages/add-car.jsp'">Add Car Request</p>
                        </div>
                        <form action="LogoutServlet" method="POST">
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
            	<% if(request.getParameter("message") != null){ %>
                    <script>alert("${param.message}");</script>
                <% } %>
                <div class="login">
                    <form action="LoginServlet" method="POST">
                        <input type="text" placeholder="Username" name="username">
                        <input type="password" placeholder="Password" name="password">
                        <button type="submit">Login</button>
                    </form>
                </div>

                <div class="signup">
                    <label>New user? <a href="./pages/signup.jsp">Signup</a></label>
                </div>
            </div>
			<%
  			}
  			%>
        </div>

        <div class="inner-container">

            <div class="inner-container-main">

                <div class="inner-container-main-options">

                    <div class="inner-container-main-options-makes" onclick="getMakes()">

                        <button onclick="getTypes()" class="button--hover"><p>Makes</p></button>

                    </div>

                    <div class="inner-container-main-options-types">

                        <button onclick="getTypes()" class="button--hover"><p>Types</p></button>

                    </div>

                </div>

                <div class="inner-container-main-list">
                    
                </div>

            </div>

            <div class="inner-container-cars">

                <div class="inner-container-cars-car-header">
                    <h2>List of Cars</h2>
                </div>

                <div class="inner-container-cars-info">

                    <div class="inner-container-cars-info-car-name button--hover" onclick="window.location = './pages/car.jsp';">
                        <h2>Renault Duster</h2>
                    </div>

                    <div class="inner-container-cars-info-car-specs">
                        <p>Engine: Petrol</p>
                        <p>Cylinder: 5</p>
                        <p>Displacement: 1299cc</p>
                        <p>Transmission: 8 speed</p>
                        <p>Power: 85PS</p>
                        <p>Torque: 90Nm</p>
                        <p>Fuel Capacity: 45Litres</p>
                        <p>Kerb Weight: 1999Kg</p>
                    </div>

                </div>
                <div class="inner-container-cars-info">

                    <div class="inner-container-cars-info-car-name button--hover" onclick="window.location = './pages/car.jsp';">
                        <h2>Renault Duster</h2>
                    </div>

                    <div class="inner-container-cars-info-car-specs">
                        <p>Engine: Petrol</p>
                        <p>Cylinder: 5</p>
                        <p>Displacement: 1299cc</p>
                        <p>Transmission: 8 speed</p>
                        <p>Power: 85PS</p>
                        <p>Torque: 90Nm</p>
                        <p>Fuel Capacity: 45Litres</p>
                        <p>Kerb Weight: 1999Kg</p>
                    </div>

                </div>
                <div class="inner-container-cars-info">

                    <div class="inner-container-cars-info-car-name button--hover" onclick="window.location = './pages/car.jsp';">
                        <h2>Renault Duster</h2>
                    </div>

                    <div class="inner-container-cars-info-car-specs">
                        <p>Engine: Petrol</p>
                        <p>Cylinder: 5</p>
                        <p>Displacement: 1299cc</p>
                        <p>Transmission: 8 speed</p>
                        <p>Power: 85PS</p>
                        <p>Torque: 90Nm</p>
                        <p>Fuel Capacity: 45Litres</p>
                        <p>Kerb Weight: 1999Kg</p>
                    </div>

                </div>
                <div class="inner-container-cars-info">

                    <div class="inner-container-cars-info-car-name button--hover" onclick="window.location = './pages/car.jsp';">
                        <h2>Renault Duster</h2>
                    </div>

                    <div class="inner-container-cars-info-car-specs">
                        <p>Engine: Petrol</p>
                        <p>Cylinder: 5</p>
                        <p>Displacement: 1299cc</p>
                        <p>Transmission: 8 speed</p>
                        <p>Power: 85PS</p>
                        <p>Torque: 90Nm</p>
                        <p>Fuel Capacity: 45Litres</p>
                        <p>Kerb Weight: 1999Kg</p>
                    </div>

                </div>
                <div class="inner-container-cars-info" onclick="window.location = './pages/car.jsp';">

                    <div class="inner-container-cars-info-car-name button--hover">
                        <h2>Renault Duster</h2>
                    </div>

                    <div class="inner-container-cars-info-car-specs">
                        <p>Engine: Petrol</p>
                        <p>Cylinder: 5</p>
                        <p>Displacement: 1299cc</p>
                        <p>Transmission: 8 speed</p>
                        <p>Power: 85PS</p>
                        <p>Torque: 90Nm</p>
                        <p>Fuel Capacity: 45Litres</p>
                        <p>Kerb Weight: 1999Kg</p>
                    </div>

                </div>

            </div>

            <footer>
                <p>Autorate © 2018</p>
            </footer>

        </div>

    </div>
    <script src="./js/index.js"></script>
</body>

</html>