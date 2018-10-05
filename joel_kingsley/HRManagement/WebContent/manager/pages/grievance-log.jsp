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
    <link rel="stylesheet" href="/HR_Management/general/css/grievance-log.css">
    <title>Leave Tracker | ZTech HRManagement</title>
</head>
<body>
    <header>
        <div id="ztech-logo">
            <img src="/HR_Management/assets/img/hr-logo.png">
        </div>
        <div class="drop">
            <div class="drop-image">
                <img src="/HR_Management/assets/img/joel.jpeg">
            </div>
            <div class="drop-text open-sans-condensed italics">
                Joel Kingsley
            </div>
        </div>
    </header>
    <section class="navbar">
        <div class="open-sans-condensed bold">
            <a href="/HR_Management/manager/pages/dashboard.jsp" class="menu-button">Dashboard</a>
            <div class="dropdown-content dashboard-drop">
                <a href="/HR_Management/general/pages/profile.jsp">Profile</a>
                <a href="/HR_Management/manager/pages/projects.jsp">Project</a>
                <a href="/HR_Management/manager/pages/documents.jsp">Documents</a>
            </div>
        </div>
        <div class="open-sans-condensed bold">
            <a href="/HR_Management/manager/pages/leave-tracker.jsp" class="menu-button">Leave Tracker</a>
            <div class="dropdown-content lt-drop">
                <a href="/HR_Management/manager/pages/apply-leave.jsp">Apply Leave</a>
                <a href="/HR_Management/manager/pages/leave-log.jsp">Leave Log</a>
            </div>
        </div>
        <div class="open-sans-condensed bold">
            <a href="/HR_Management/manager/pages/grievance-tracker.jsp" class="active menu-button">Grievance Tracker</a>
            <div class="dropdown-content gt-drop">
                <a href="/HR_Management/manager/pages/issue-grievance.jsp">Issue Grievance</a>
                <a href="/HR_Management/manager/pages/grievance-log.jsp">Grievance Log</a>
            </div>
        </div>
        <input type="text" placeholder="Search..." class="open-sans-condensed">
    </section>
    <section class="middle">
        <div id="first-container" class="col-sm-12 col-md-12">
            <div id="grievance-log" class="col-sm-12 col-md-11">
                <h1 class="open-sans-condensed bold white">
                    Grievance Log
                </h1>
                <div id="grievance-item" class="open-sans-condensed accordion">
                    <div id="grievance-title" class="bold">
                        Need to Restock TT Balls
                    </div>
                    <div id="date-opened">
                        12/01/2018
                    </div>
                    <div id="grievance-status" class="bold">
                        Open
                    </div>
                </div>
                <div class="panel">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quia consequatur alias nisi illum sunt officia hic expedita placeat accusamus, ex deserunt voluptatum ullam sit eius et. Impedit officia atque obcaecati.</p>
                </div>
                <div id="grievance-item" class="open-sans-condensed accordion">
                    <div id="grievance-title" class="bold">
                        Need to Restock TT Balls
                    </div>
                    <div id="date-opened">
                        12/01/2018
                    </div>
                    <div id="grievance-status" class="bold">
                        Open
                    </div>
                </div>
                <div class="panel">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quia consequatur alias nisi illum sunt officia hic expedita placeat accusamus, ex deserunt voluptatum ullam sit eius et. Impedit officia atque obcaecati.</p>
                </div>
                <div id="grievance-item" class="open-sans-condensed accordion">
                    <div id="grievance-title" class="bold">
                        Need to Restock TT Balls
                    </div>
                    <div id="date-opened">
                        12/01/2018
                    </div>
                    <div id="grievance-status" class="bold">
                        Open
                    </div>
                </div>
                <div class="panel">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quia consequatur alias nisi illum sunt officia hic expedita placeat accusamus, ex deserunt voluptatum ullam sit eius et. Impedit officia atque obcaecati.</p>
                </div>
                <div id="grievance-item" class="open-sans-condensed accordion">
                    <div id="grievance-title" class="bold">
                        Need to Restock TT Balls
                    </div>
                    <div id="date-opened">
                        12/01/2018
                    </div>
                    <div id="grievance-status" class="bold">
                        Open
                    </div>
                </div>
                <div class="panel">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quia consequatur alias nisi illum sunt officia hic expedita placeat accusamus, ex deserunt voluptatum ullam sit eius et. Impedit officia atque obcaecati.</p>
                </div>
                <div id="grievance-item" class="open-sans-condensed accordion">
                    <div id="grievance-title" class="bold">
                        Need to Restock TT Balls
                    </div>
                    <div id="date-opened">
                        12/01/2018
                    </div>
                    <div id="grievance-status" class="bold">
                        Open
                    </div>
                </div>
                <div class="panel">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quia consequatur alias nisi illum sunt officia hic expedita placeat accusamus, ex deserunt voluptatum ullam sit eius et. Impedit officia atque obcaecati.</p>
                </div>
            </div>
        </div>
    </section>
    <footer>
        
    </footer>
    <script type="text/javascript" src="../../general/js/grievance-log.js"></script>
</body>
</html>