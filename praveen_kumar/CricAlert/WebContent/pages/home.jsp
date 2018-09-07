<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/CricAlert/css/grid-common.css">
    <link rel="stylesheet" href="/CricAlert/css/main-common.css">
    <link rel="stylesheet" href="/CricAlert/css/homestyle.css">
    <link href="https://fonts.googleapis.com/css?family=Cuprum|Lato|Lobster|Lobster+Two|Pacifico" rel="stylesheet">
    <title>Home</title>
</head>

<body>
    <div class="main-container">
        <nav class="side-bar">
        	<img class="close-bar" src="/CricAlert/assets/icons/icons8-close.png" alt="close" onclick="sideBarClick()"/>
            <ul>
                <li><a href="/CricAlert/pages/home.jsp" class="nav-highlight-2">Home</a></li>
                <li><a href="/CricAlert/pages/teams.jsp" class="">Teams</a></li>
                <li><a href="/CricAlert/pages/players.jsp" class="">Players</a></li>
                <li><a href="/CricAlert/pages/index.jsp" class="">Logout</a></li>
            </ul>
        </nav>
        <header class="header">
            <div class="header__title-bar col-sm-12">
                <img class="header__title-bar__hamburger" src="/CricAlert/assets/icons/icons8-menu-15.png" alt="Menu" onclick="sideBarClick()" />
                <h1 class="header__title-bar__title">CricAlert!</h1>
            </div>
            <nav class="header__nav-bar col-sm-12">
                <div onclick="window.location='/CricAlert/Home'" class="nav-highlight">Home</div>
                <div onclick="window.location='/CricAlert/Teams'" class="">Teams</div>
                <div onclick="window.location='/CricAlert/Players'" class="">Players</div>
            </nav>
        </header>
        <section class="content">
            <div class="content__matches col-sm-12">
                <div class="content__matches__title">Live Matches</div>
                <div class="content__matches__live">
                    <div class="match-card" onclick="window.location='/CricAlert/pages/live-match.jsp'">
                        <div class="match-card__schedule">
                            <div class="match-card__schedule__date">01/11/2017</div>
                            <div class="match-card__schedule__time">16:30</div>
                        </div>
                        <div class="match-card__teams">
                            <div class="match-card__teams__team-a">
                                <div class="match-card__teams__team-a__logo">
                                    <img src="/CricAlert/assets/images/team-a-logo.png" alt="CSK">
                                </div>
                                <div class="match-card__teams__team-a__score">201-9</div>
                            </div>
                            vs
                            <div class="match-card__teams__team-b">
                                <div class="match-card__teams__team-b__logo">
                                    <img src="/CricAlert/assets/images/team-b-logo.png" alt="SRH">
                                </div>
                                <div class="match-card__teams__team-b__score">115-3</div>
                            </div>
                        </div>
                        <div class="match-card__venue">Chepauk Stadium</div>
                    </div>
                </div>
            </div>
            <div class="content__matches col-sm-12">
                <div class="content__matches__title">Upcoming Matches</div>
                <div class="content__matches__upcoming">
                    <div class="match-card" onclick="window.location='/CricAlert/pages/upcoming-match.jsp'">
                        <div class="match-card__schedule">
                            <div class="match-card__schedule__date"></div>
                            <div class="match-card__schedule__time"></div>
                        </div>
                        <div class="match-card__teams">
                            <div class="match-card__teams__team-a">
                                <div class="match-card__teams__team-a__logo">

                                </div>
                                <div class="match-card__teams__team-a__score"></div>
                            </div>
                            <div class="match-card__teams__team-b">
                                <div class="match-card__teams__team-b__logo">

                                </div>
                                <div class="match-card__teams__team-b__score"></div>
                            </div>
                        </div>
                        <div class="match-card__venue"></div>
                    </div>
                </div>
            </div>
            <div class="content__matches col-sm-12">
                <div class="content__matches__title">Past Matches</div>
                <div class="content__matches__past">
                    <div class="match-card" onclick="window.location='/CricAlert/pages/past-match.jsp'">
                        <div class="match-card__schedule">
                            <div class="match-card__schedule__date"></div>
                            <div class="match-card__schedule__time"></div>
                        </div>
                        <div class="match-card__teams">
                            <div class="match-card__teams__team-a">
                                <div class="match-card__teams__team-a__logo">

                                </div>
                                <div class="match-card__teams__team-a__score"></div>
                            </div>
                            <div class="match-card__teams__team-b">
                                <div class="match-card__teams__team-b__logo">

                                </div>
                                <div class="match-card__teams__team-b__score"></div>
                            </div>
                        </div>
                        <div class="match-card__venue"></div>
                    </div>
                </div>
            </div>
        </section>
        <footer class="footer">
            <div class="footer__add-item" onclick="window.location='/CricAlert/pages/add-match.jsp'">
                <img class="footer__add-item__icon" src="/CricAlert/assets/icons/icons8-plus-6.png" alt="Add Match">
            </div>
        </footer>
    </div>
    <script src="/CricAlert/js/main-common.js"></script>
</body>

</html>