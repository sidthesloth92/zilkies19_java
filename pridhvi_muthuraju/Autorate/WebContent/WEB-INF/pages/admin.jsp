<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome/css/all.css">
</head>

<body>

    <div class="container">
        <div class="topbar-admin">
            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo-red.png" alt="Logo" onclick="window.location='/Autorate/FetchAdminServlet';">
            </div>

            <form action="/Autorate/LogoutServlet" method="POST">
            	<input type="submit" value="Logout" />
            </form>
        </div>

        <div class="inner-container">

            <div class="inner-container-add-car" onclick="window.location='/Autorate/FetchAddCarServlet';">
                <h2>Add Car</h2>
            </div>

            <div class="inner-container-edit-car" onclick="window.location='/Autorate/FetchEditCarServlet';">
                <h2>Edit Car</h2>
            </div>

            <div class="inner-container-review-requests" onclick="window.location='/Autorate/FetchRequestsServlet';">
                <h2>Review Requests</h2>
            </div>

        </div>
    </div>

</body>

</html>