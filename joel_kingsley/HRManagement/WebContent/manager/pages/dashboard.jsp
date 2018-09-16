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
    <link rel="stylesheet" href="/HR_Management/general/css/grievance-tracker.css">
    <title>Grievance Tracker | ZTech HRManagement</title>
</head>
<body>
    <header>
        <div id="ztech-logo">
            <img src="/HR_Management/assets/img/hr-logo.png">
        </div>
    </header>
    <section class="navbar">
        <div class="open-sans-condensed bold">
            <a href="/HR_Management/general/pages/generic-login.jsp" class="active menu-button">Dashboard</a>
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
            <a href="/HR_Management/employee/pages/grievance-tracker.jsp" class="menu-button">Grievance Tracker</a>
            <div class="dropdown-content gt-drop">
                <a href="/HR_Management/employee/pages/issue-grievance.jsp">Issue Grievance</a>
                <a href="/HR_Management/employee/pages/grievance-log.jsp">Grievance Log</a>
            </div>
        </div>
        <input type="text" placeholder="Search..." class="open-sans-condensed">
    </section>
    <section class="middle">
        <div id="first-container" class="col-sm-12 col-md-6">
            <div id="apply-grievance" class="col-sm-11 col-md-12">
                <h1 class="open-sans-condensed bold white">
                    Issue Grievance
                </h1>
            </div>
        </div>
        <div id="second-container" class="col-sm-12 col-md-6">
            <div id="grievance-log" class="col-sm-11 col-md-12">
                <h1 class="open-sans-condensed bold white">
                    <a href="../pages/grievance-log.html">Grievance Log</a>
                </h1>
                <div id="grievance-item" class="open-sans-condensed">
                    <div id="grievance-desc" class="bold">
                        Need to restock TT balls
                    </div>
                    <div id="issue-date-time">
                        12/01/2018 12:37
                    </div>
                </div>
                <div id="grievance-item" class="open-sans-condensed">
                    <div id="grievance-desc" class="bold">
                        Office chairs are uncomfortable
                    </div>
                    <div id="issue-date-time">
                        16/01/2018 16:54
                    </div>
                </div>
                <div id="grievance-item" class="open-sans-condensed">
                    <div id="grievance-desc" class="bold">
                        Office chairs are uncomfortable
                    </div>
                    <div id="issue-date-time">
                        16/01/2018 16:54
                    </div>
                </div>
                    
                <div id="more" class="open-sans-condensed italics white">
                    <a href="../pages/grievance-log.html">More</a>
                </div>
            </div>
        </div>
    </section>
    <footer>
        
    </footer>
</body>
</html>