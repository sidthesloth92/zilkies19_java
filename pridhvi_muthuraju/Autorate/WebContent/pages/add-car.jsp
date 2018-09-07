<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="io.ztech.autorate.services.*"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add Car</title>
    <link rel="stylesheet" href="../css/admin.css">
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/add-car.css">
</head>

<body>

    <div class="container">
    <jsp:useBean id="CheckLoginService" class="io.ztech.autorate.services.CheckLoginService" />
    <%
		CheckLoginService checkLoginService = new CheckLoginService();
    	if(session.getAttribute("loggedInUser")!=null) {
    		if(false){
    %>
    
    <div class="topbar-admin">
            <div class="logo-container">
                <img src="../images/logo-red.png" alt="Logo" onclick="window.location='./admin.html';">
            </div>

            <button onclick="window.location='../index.html'">Logout</button>
    </div>
    
 	<% 
    		}else {
    %>
    
     <div class="topbar">
            <div class="logo-container">
                <img src="../images/logo-green.png" alt="Logo" onclick="window.location='../index.jsp';">
            </div>
            <div class="user-container">

                <div class="user-container-username">

                    <p onclick="showUserDropdown()">pridhvi</p>

                    <div class="user-container-dropdown">
                        <div>
                            <p onclick="window.location='./user-requests.jsp'">Your Requests</p>
                        </div>
                        <div>
                            <p onclick="#">Add Car Request</p>
                        </div>
                        <form action="../LogoutServlet" method="POST">
                            <input type="submit" value="Logout" />
                        </form>
                    </div>
                </div>

            </div>
        </div>
    
    <%
    		}
    	}
    %>

        <!-- <div> -->

            <form action="#" class="inner-container">

                <!-- <h1>Add Car</h1> -->

                <select name="makes" required>
                    <option selected disabled>Choose a make</option>
                    <option value="volvo">Volvo</option>
                    <option value="ferrari">Ferrari</option>
                    <option value="mercedes">Mercedes</option>
                    <option value="audi">Audi</option>
                </select>

                <select name="types" required>
                    <option selected disabled>Choose a type</option>
                    <option value="suv">SUV</option>
                    <option value="sedan">Sedan</option>
                    <option value="sport">Sport</option>
                </select>

                <input type="text" placeholder="Car Name">
                <input type="text" placeholder="Engine Type">
                <input type="text" placeholder="Cylinders">
                <input type="number" placeholder="Displacement(cc)">
                <input type="text" placeholder="Transmission">
                <input type="number" placeholder="Power(HP)">
                <input type="number" placeholder="Torque(Nm)">
                <input type="number" placeholder="Fuel Capacity(Litres)">
                <input type="text" placeholder="Wheelbase">
                <input type="number" placeholder="Kerb Weight(Kg)">
                <input type="text" placeholder="Airbag">
                <input type="text" placeholder="ABS">
                <input type="text" placeholder="Drivetrain">
                <input type="number" placeholder="Price(Rs)">

                <button type="submit">Submit</button>

            </form>

        <!-- </div> -->
    </div>
    
    <script src="../js/index.js"></script>

</body>

</html>