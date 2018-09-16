<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300i,700" rel="stylesheet"> 
    <link rel="stylesheet" href="/HR_Management/general/css/header.css">
    <link rel="stylesheet" href="/HR_Management/general/css/footer.css">
    <link rel="stylesheet" href="/HR_Management/general/css/navbar.css">
    <link rel="stylesheet" href="/HR_Management/general/css/fonts.css">
    <link rel="stylesheet" href="/HR_Management/general/css/buttons.css">
    <link rel="stylesheet" href="/HR_Management/general/css/profile.css">
    <link rel="stylesheet" href="/HR_Management/general/css/call.css">
    <link rel="stylesheet" href="/HR_Management/general/css/body.css">
    <title>Home | ZTech HRManagement</title>
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
        <div class="parent-container col-sm-12 col-md-12">
            <div id="profile" class="container col-sm-10 col-md-6">
                <div id="profile-img">
                    <img src="/HR_Management/assets/img/joel.jpeg">
                </div>
                <div id="profile-text">
                    <div id="profile-name" class="open-sans-condensed bold">
                        Joel Kingsley
                    </div>
                    <div id="profile-designation" class="open-sans-condensed">
                        Trainee Consultant
                    </div>
                    <div id="profile-id" class="open-sans-condensed">
                        ID: 2034
                    </div>
                </div>
            </div>
            <div id="status" class="container col-sm-10 col-md-6">
                <div id="profile-text">
                    <div id="join-date" class="open-sans-condensed">
                        Join Date: 12/01/2018
                    </div>
                    <div id="confirm-date" class="open-sans-condensed">
                        Confirm Date: NA
                    </div>
                    <div id="status" class="open-sans-condensed bold">
                        Status: Unconfirmed
                    </div>
                </div>
            </div>
        </div>
        <div class="parent-container parent-container--big col-sm-12 col-md-12">
            <div id="personal-details" class="container container--medium col-sm-10 col-md-6">
                <div id="details-title" class="open-sans-condensed bold">
                    Personal Details
                </div>
                <div id="profile-text">
                    <div id="marital-status" class="open-sans-condensed">
                        Marital Status: Single
                    </div>
                    <div id="gender" class="open-sans-condensed">
                        Gender: Male
                    </div>
                    <div id="date-of-birth" class="open-sans-condensed">
                        Date of Birth: 24/11/1997
                    </div>
                    <div id="date-of-birth" class="open-sans-condensed">
                        Blood Group: O+ve
                    </div>
                    <div id="date-of-birth" class="open-sans-condensed">
                        Highest Qualification: BE CSE
                    </div>
                </div>
            </div>
            <div id="identification" class="container container--medium col-sm-10 col-md-6">
                <div id="details-title" class="open-sans-condensed bold">
                    Identification
                </div>
                <div id="profile-text">
                    <div id="pan" class="open-sans-condensed">
                        PAN: 123456789012
                    </div>
                    <div id="aadhar" class="open-sans-condensed">
                        Aadhar: 123456789012
                    </div>
                    <div id="uan" class="open-sans-condensed">
                        UAN: 123456789012
                    </div>
                </div>
            </div>
        </div>
        <div class="parent-container parent-container--big col-sm-12 col-md-12">
            <div id="contact-details" class="container container--big col-sm-10 col-md-6">
                <div id="contact-title" class="open-sans-condensed bold">
                    Contact Details
                </div>
                <div id="profile-text">
                    <!-- <div class="space col-sm-hide">...</div> -->
                    <div class="space col-sm-hide"></div>
                    <div id="phone-title" class="open-sans-condensed bold">
                        Phone Numbers:
                    </div>
                    <div id="phone-numbers" class="open-sans-condensed">
                        9043707080
                    </div>
                    <div id="email-title" class="open-sans-condensed bold">
                        Email Addresses:
                    </div>
                    <div id="email" class="open-sans-condensed">
                        joelkingsley.r@gmail.com (HOME)<br>jkingsley@ztech.io (WORK)
                    </div>
                    <div id="present-address-title" class="open-sans-condensed bold">
                        Present Address:
                    </div>
                    <div id="present-address" class="open-sans-condensed">
                        724/A, TSR Nagar, Thiruvottiyur, Chennai 600019
                    </div>
                    <div id="permanent-address-title" class="open-sans-condensed bold">
                        Permanent Address:
                    </div>
                    <div id="permanent-address" class="open-sans-condensed">
                        724/A, TSR Nagar, Thiruvottiyur, Chennai 600019
                    </div>
                </div>
            </div>
            <div id="skills" class="container container--big col-sm-10 col-md-6">
                <div id="skills-title" class="open-sans-condensed bold">
                    Skills
                </div>
                <div class="skills-list">
                    <div class="skill-element">
                            <div class="skill-text open-sans-condensed">HTML</div>
                        <div class="skill-bar">
                            <div class="skills html">
                                    <div class="skills-percentage open-sans-condensed">90%</div>
                            </div>
                        </div>
                    </div>
                    <div class="skill-element">
                        <div class="skill-text open-sans-condensed">CSS</div>
                        <div class="skill-bar">
                            <div class="skills css">
                                    <div class="skills-percentage open-sans-condensed">80%</div>
                            </div>
                        </div>
                    </div>
                    <div class="skill-element">
                            <div class="skill-text open-sans-condensed">JS</div>
                        <div class="skill-bar">
                            <div class="skills js">
                                    <div class="skills-percentage open-sans-condensed">65%</div>
                            </div>
                        </div>
                    </div>
                    <div class="skill-element">
                            <div class="skill-text open-sans-condensed">PHP</div>
                        <div class="skill-bar">
                            <div class="skills php">
                                <div class="skills-percentage open-sans-condensed">60%</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <footer>
        
    </footer>
</body>
</html>