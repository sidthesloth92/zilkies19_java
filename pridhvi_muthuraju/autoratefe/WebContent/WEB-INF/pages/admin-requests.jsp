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
    <title>Requests</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-requests.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-requests.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome/css/all.css">
</head>

<body>

    <div class="user-request-container">
        <div class="topbar-admin">
            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo-red.png" alt="Logo" onclick="window.location='/autoratefe/FetchAdminServlet';">
            </div>

            <form action="/autoratefe/LogoutServlet" method="POST">
                <input type="submit" value="Logout" />
            </form>
        </div>

        <div class="inner-container">
        <c:forEach items="${requests}" var="req">
 			
		

            <div class="user-request" onclick="getSpecs(this);showSpecs(this);" id=${req.getRequestId()}>

                <div class="user-request-car-name">
                    Request ID: ${req.getRequestId()}
                    <br/>
                    Username: ${req.getUserName()}
                </div>

                <div class="user-request-status">
                    <i class="fas fa-check-circle approved" onclick="approveRequest(this)"></i>
                    <p>Approve</p>
                    <i class="fas fa-times-circle declined" onclick="declineRequest(this)"></i>
                    <p>Decline</p>
                </div>
                

                <div class="user-request-specs" id=${req.getCarId()}>
                    
                </div>
            </div>

		</c:forEach>
      </div>
        

    </div>

    <script src="${pageContext.request.contextPath}/js/user-request.js"></script>
</body>

</html>