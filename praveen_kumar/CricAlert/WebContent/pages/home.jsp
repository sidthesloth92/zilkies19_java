<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../css/grid-common.css">
    <link rel="stylesheet" href="../css/main-common.css">
    <link rel="stylesheet" href="../css/homestyle.css">
    <link href="https://fonts.googleapis.com/css?family=Cuprum|Lato|Lobster|Lobster+Two|Pacifico" rel="stylesheet">
    <title>Home</title>
</head>

<body>
    <div class="main-container">
        <nav class="side-bar">
            <ul>
                <img class="close-bar" src="../assets/icons/icons8-close.png" alt="close" onclick="sideBarClick()">
                <li><a href="./home.html" class="nav-highlight-2">Home</a></li>
                <li><a href="./teams.html" class="">Teams</a></li>
                <li><a href="./players.html" class="">Players</a></li>
                <li><a href="../index.html" class="">Logout</a></li>
            </ul>
        </nav>
        <header class="header">
            <div class="header__title-bar col-sm-12">
                <img class="header__title-bar__hamburger" src="../assets/icons/icons8-menu-15.png" alt="Menu" onclick="sideBarClick()" />
                <h1 class="header__title-bar__title">CricAlert!</h1>
            </div>
            <nav class="header__nav-bar col-sm-12">
                <a href="./home.html" class="nav-highlight">Home</a>
                <a href="./teams.html" class="">Teams</a>
                <a href="./players.html" class="">Players</a>
            </nav>
        </header>
        <section class="content">
            <div class="content__live-matches col-sm-12">
                <div class="content__live-matches__title">Live Matches</div>
                <div class="content__live-matches__container">
                    <div class="content__live-matches__container__match" onclick="window.location='./live-match.html'">
                    </div>
                    <div class="content__live-matches__container__match" onclick="window.location='./live-match.html'">
                    </div>
                </div>
            </div>
            <div class="content__upcoming-matches col-sm-12">
                <div class="content__upcoming-matches__title">Upcoming Matches</div>
                <div class="content__upcoming-matches__container">
                    <div class="content__upcoming-matches__container__match" onclick="window.location='./upcoming-match.html'">
                    </div>
                    <div class="content__upcoming-matches__container__match" onclick="window.location='./upcoming-match.html'">
                    </div>
                </div>
            </div>
            <div class="content__past-matches col-sm-12">
                <div class="content__past-matches__title">Past Matches</div>
                <div class="content__past-matches__container">
                    <div class="content__past-matches__container__match" onclick="window.location='./past-match.html'">
                    </div>
                    <div class="content__past-matches__container__match" onclick="window.location='./past-match.html'">
                    </div>
                </div>
            </div>
        </section>
        <footer class="footer">
            <div class="footer__add-item" onclick="window.location='./add-match.html'">
                <img class="footer__add-item__icon" src="../assets/icons/icons8-plus-6.png" alt="Add Match">
            </div>
        </footer>
    </div>
    <script src="../js/main-common.js"></script>
</body>

</html>