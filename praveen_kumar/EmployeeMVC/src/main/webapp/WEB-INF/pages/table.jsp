<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="io.ztech.employee.Employee"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/EmployeeSystem/css/table.css">
	<title>Employee Table</title>
</head>
<body>
    <table id="employeeTable">
        <tr>
            <th>E.ID</th>
            <th>Name</th>
            <th>Designation</th>
            <th>Manager</th>
            <!-- <th>View</th> -->
        </tr>
        <%
            /* ArrayList<Employee> empList = (ArrayList<Employee>) request.getAttribute("empList"); */
            Employee[] empList = (Employee[]) request.getAttribute("empList");
        	for (Employee emp : empList) {
        		out.println("<tr>");
        		out.println("<td>" + emp.getId() + "</td>");
        		out.println("<td>" + emp.getName() + "</td>");
        		out.println("<td>" + emp.getDesignation() + "</td>");
        		out.println("<td>" + emp.getManager() + "</td>");
        		out.println("</tr>");
        	}
        %>
    </table>
</body>
</html>