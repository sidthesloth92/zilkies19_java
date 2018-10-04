<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="io.ztech.autorate.beans.Specification"%>
<%@ page import="io.ztech.autorate.beans.Request"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Your Requests</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-requests.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-requests.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome/css/all.css">
</head>

<body>

    <div class="user-request-container">
        <div class="topbar">

            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo-green.png" alt="Logo" class="logo" onclick="window.location='${pageContext.request.contextPath}/index.jsp';">
            </div>

            <%
 			 if(session.getAttribute("status")!=null)
  				{ 
  			%>
  			<div class="user-container">

                <div class="user-container-username">

                    <p onclick="showUserDropdown()"><%=session.getAttribute("username") %>&#9662;</p>

                    <div class="user-container-dropdown">
                        <div>
                            <p onclick="#">Your Requests</p>
                        </div>
                        <div>
                            <p onclick="window.location='/Autorate/FetchAddCarServlet'">Add Car Request</p>
                        </div>
                        <form action="/Autorate/LogoutServlet" method="POST">
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
                    <form action="/Autorate/LoginServlet" method="POST">
                        <input type="text" placeholder="Username" name="username">
                        <input type="password" placeholder="Password" name="password">
                        <button type="submit">Login</button>
                    </form>
                </div>

                <div class="signup">
                    <label>New user? <a href="${pageContext.request.contextPath}/pages/signup.jsp">Signup</a></label>
                </div>
            </div>
			<%
  			}
  			%>

        </div>

        <div class="inner-container">

			 <c:forEach items="${requests}" var="req">
 			
		

            <div class="user-request" onclick="getSpecs(this);showSpecs(this);" id="${req.getRequestId()}">

                <div class="user-request-car-name">
                    Request ID: ${req.getRequestId()}
                </div>

                <div class="user-request-status">
                    <i class="fas fa-exclamation-triangle pending"></i>
                    <p>Pending</p>
                    <i class="fas fa-times-circle declined" onclick="declineRequest(this)"></i>
                    <p>Delete</p>
                </div>
                

                <div class="user-request-specs" id="${req.getCarId()}">
                    
                </div>
            </div>

		</c:forEach>

        </div>

    </div>

    <script src="${pageContext.request.contextPath}/js/user-request.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>

</html>