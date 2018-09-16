<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300i,700" rel="stylesheet"> 
    <link rel="stylesheet" href="/HR_Management/general/css/fonts.css">
    <link rel="stylesheet" href="/HR_Management/general/css/header.css">
    <link rel="stylesheet" href="/HR_Management/general/css/footer.css">
    <link rel="stylesheet" href="/HR_Management/general/css/buttons.css">
    <link rel="stylesheet" href="/HR_Management/general/css/navbar.css">
    <link rel="stylesheet" href="/HR_Management/general/css/call.css">
    <link rel="stylesheet" href="/HR_Management/general/css/issue-grievance.css">
    <title>Leave Tracker | ZTech HRManagement</title>
</head>
<body>
    <header>
        <div id="ztech-logo">
            <img src="/HR_Management/assets/img/hr-logo.png">
        </div>
    </header>
    <section class="navbar">
        <div class="open-sans-condensed bold">
            <a href="/HR_Management/general/pages/generic-login.jsp" class="menu-button">Dashboard</a>
            <div class="dropdown-content dashboard-drop">
                <a href="/HR_Management/general/pages/profile.jsp">Profile</a>
                <a href="/HR_Management/employee/pages/projects.jsp">Project</a>
                <a href="/HR_Management/employee/pages/documents.jsp">Documents</a>
            </div>
        </div>
        <div class="open-sans-condensed bold">
            <a href="/HR_Management/employee/pages/leave-tracker.jsp" class="menu-button">Leave Tracker</a>
            <div class="dropdown-content lt-drop">
                <a href="/HR_Management/employee/pages/apply-leave.jsp">Apply Leave</a>
                <a href="/HR_Management/employee/pages/leave-log.jsp">Leave Log</a>
            </div>
        </div>
        <div class="open-sans-condensed bold">
            <a href="/HR_Management/employee/pages/grievance-tracker.jsp" class="active menu-button">Grievance Tracker</a>
            <div class="dropdown-content gt-drop">
                <a href="/HR_Management/employee/pages/issue-grievance.jsp">Issue Grievance</a>
                <a href="/HR_Management/employee/pages/grievance-log.jsp">Grievance Log</a>
            </div>
        </div>
        <input type="text" placeholder="Search..." class="open-sans-condensed">
    </section>
    <section class="middle">
        <div id="first-container" class="col-sm-12">
            <div id="grievance-subject-div" class="col-sm-11">
                <input type="text" id="grievance-subject" name="grievance-subject" class="open-sans-condensed bold white" placeholder="Grievance Subject">
            </div>
            <div id="grievance-text-div" class="col-sm-11">
                <textarea id="grievance-text" class="open-sans-condensed bold white" placeholder="Write the grievance"></textarea>
            </div>

            <div id="leave-submit" class="col-sm-12">
                <button class="pressed-button center-button open-sans-condensed bold">
                    Submit Grievance
                </button>
            </div>
        </div>
    </section>
    <footer>
            
    </footer>
</body>
</html>