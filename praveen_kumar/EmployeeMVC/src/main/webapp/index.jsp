<!DOCTYPE html>
<html>

<head>
    <title>Employee Application</title>
    <link rel="stylesheet" href="/EmployeeSystem/css/form.css">
</head>

<body>
    <form action="ValidateForm" method="GET">
    	<h2>Employee Data</h2>
        Name:<br>
        <input type="text" name="fullname" required>
        <br> Designation:
        <br>
        <input type="text" name="designation" required>
        <br> Manager:
        <br>
        <input type="text" name="manager">
        <br>
        <input type="submit" value="Submit">
    </form>
    <div><a href="GetTable">Table View</a></div>
</body>

</html>