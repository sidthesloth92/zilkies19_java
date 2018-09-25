<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="io.ztech.autorate.beans.Make"%>
    <%@ page import="io.ztech.autorate.beans.CarType"%>
<!DOCTYPE html>
<%@ page import="io.ztech.autorate.delegates.*"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add Car</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-car.css">
</head>

<body>
	<%if(request.getAttribute("request-id")!=null) {
        	%><script>alert("Your request has been added withthe Request ID: "+<%=request.getAttribute("request-id")%>);</script><%
        }
        %>

    <div class="container">
    <%
    	if(session!=null) {
    		if(session.getAttribute("status")!=null&&session.getAttribute("status").equals("ADMIN")){
    %>
    
    <div class="topbar-admin">
            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo-red.png" alt="Logo" onclick="window.location='/autoratefe/FetchAdminServlet';">
            </div>

            <form action="/autoratefe/LogoutServlet" method="POST">
            	<input type="submit" value="Logout" />
            </form>
    </div>
    
 	<% 
    		}
    		else {
    %>
    
     <div class="topbar">
            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo-green.png" alt="Logo" onclick="window.location='/autoratefe/IndexServlet';">
            </div>
            
            <div class="user-container">

                <div class="user-container-username">

                    <p onclick="showUserDropdown()"><%=session.getAttribute("username") %>&#9662;</p>

                    <div class="user-container-dropdown">
                        <div>
                            <p onclick="window.location='/autoratefe/FetchRequestsServlet';">Your Requests</p>
                        </div>
                        <div>
                            <p onclick="#">Add Car Request</p>
                        </div>
                        <form action="/autoratefe/LogoutServlet" method="POST">
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

            <form action="/autoratefe/AddCarServlet" class="inner-container" onsubmit="return validateAddCarForm()">

                
                <select name="makes">
                	<option value="nil" selected disabled>Choose a make</option>
    				<c:forEach items="${makes}" var="make">
       					 <option value="${make.getMakeId()}">
          					  ${make.getMakeName()}
        				 </option>
    				</c:forEach>
				</select>
				<%if(session.getAttribute("status")!=null&&session.getAttribute("status").equals("ADMIN")) {%>
				<input type="text" placeholder="New Make" name="make-name" value="">
				<%
					}
				%>
				<select name="car-types">
                	<option value="nil" selected disabled>Choose a type</option>
    				<c:forEach items="${carTypes}" var="carType">
       					 <option value="${carType.getCarTypeId()}">
          					  ${carType.getCarTypeName()}
        				 </option>
    				</c:forEach>
				</select>
				
				<%if(session.getAttribute("status")!=null&&session.getAttribute("status").equals("ADMIN")) {%>
				<input type="text" placeholder="New Car Type" name="cartype-name" value="">
				<%
					}
				%>
                <input type="text" placeholder="Car Name" name="car-name">
                <select name="engine-type">
                	<option selected disabled>Choose an Engine Type</option>
                	<option value="PETROL">Petrol</option>
                	<option value="DIESEL">Diesel</option>
                </select>
                <input type="text" placeholder="Cylinders" name="cylinder">
                <input type="number" placeholder="Displacement(cc)" name="displacement">
                <input type="text" placeholder="Transmission" name="transmission">
                <input type="number" placeholder="Power(HP)" name="power" required>
                <input type="number" placeholder="Torque(Nm)" name="torque" required>
                <input type="number" placeholder="Fuel Capacity(Litres)" name="fuel-capacity">
                <input type="text" placeholder="Wheelbase(mm)" name="wheelbase" required>
                <input type="number" placeholder="Kerb Weight(Kg)" name="kerb-weight" required>
                <input type="text" placeholder="Airbag" name="airbag">
                <input type="text" placeholder="ABS" name="abs">
                <select name="drivetrain">
                	<option selected disabled>Choose an Drivetrain</option>
                	<option value="FWD">Front Wheel Drive</option>
                	<option value="RWD">Rear Wheel Drive</option>
                	<option value="AWD">All Wheel Drive</option>
                </select>
                <input type="number" placeholder="Price(&#8377;)" name="price" required>

                <button type="submit">Submit</button>

            </form>

        
    </div>
    
    <script src="${pageContext.request.contextPath}/js/index.js"></script>

</body>

</html>