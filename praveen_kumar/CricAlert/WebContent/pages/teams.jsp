<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/CricAlert/css/grid-common.css">
    <link rel="stylesheet" href="/CricAlert/css/main-common.css">
    <link rel="stylesheet" href="/CricAlert/css/teamstyle.css">
    <link href="https://fonts.googleapis.com/css?family=Cuprum|Lato|Lobster|Lobster+Two|Pacifico" rel="stylesheet">
    <title>Teams</title>
</head>

<body>
    <div class="main-container">
        <nav class="side-bar">
            <ul>
                <img class="close-bar" src="/CricAlert/assets/icons/icons8-close.png" alt="close" onclick="sideBarClick()">
                <li><a href="/CricAlert/pages/home.jsp" class="">Home</a></li>
                <li><a href="/CricAlert/pages/teams.jsp" class="nav-highlight-2">Teams</a></li>
                <li><a href="/CricAlert/pages/players.jsp" class="">Players</a></li>
                <li><a href="/CricAlert/pages/index.jsp" class="">Logout</a></li>
            </ul>
        </nav>
        <header class="header">
            <div class="header__title-bar col-sm-12">
                <img class="header__title-bar__hamburger" src="/CricAlert/assets/icons/icons8-menu-15.png" alt="Menu" onclick="sideBarClick()" />
                <h1 class="header__title-bar__title">Teams</h1>
            </div>
            <nav class="header__nav-bar col-sm-12">
                <div onclick="window.location='/CricAlert/Home'" class="">Home</div>
                <div onclick="window.location='/CricAlert/Teams'" class="nav-highlight">Teams</div>
                <div onclick="window.location='/CricAlert/Players'" class="">Players</div>
            </nav>
        </header>
        <section class="content">
            <div class="content__team-card col-sm-12" onclick="expandTeam(this)" draggable="true" ondragstart="dragStart(event)" ondragend="dragStop(event)">
                <div class="content__team-card__container-1">
                    <img class="content__team-card__container-1__photo" draggable="false" src="/CricAlert/assets/icons/icons8-team.png" alt="Photo" />
                    <h3 class="content__team-card__container-1__name">Chennai Super Kings</h3>
                </div>
                <div class="content__team-card__container-2"></div>
            </div>
            <div class="content__team-card col-sm-12" onclick="expandTeam(this)" draggable="true" ondragstart="dragStart(event)" ondragend="dragStop(event)">
                <div class="content__team-card__container-1">
                    <img class="content__team-card__container-1__photo" draggable="false" src="/CricAlert/assets/icons/icons8-team.png" alt="Photo" />
                    <h3 class="content__team-card__container-1__name">Sunrisers Hyderabad</h3>
                </div>
                <div class="content__team-card__container-2"></div>
            </div>
            <div class="content__team-card col-sm-12" onclick="expandTeam(this)" draggable="true" ondragstart="dragStart(event)" ondragend="dragStop(event)">
                <div class="content__team-card__container-1">
                    <img class="content__team-card__container-1__photo" draggable="false" src="/CricAlert/assets/icons/icons8-team.png" alt="Photo" />
                    <h3 class="content__team-card__container-1__name">Royal Challengers Bangalore</h3>
                </div>
                <div class="content__team-card__container-2"></div>
            </div>
            <div class="content__team-card col-sm-12" onclick="expandTeam(this)" draggable="true" ondragstart="dragStart(event)" ondragend="dragStop(event)">
                <div class="content__team-card__container-1">
                    <img class="content__team-card__container-1__photo" draggable="false" src="/CricAlert/assets/icons/icons8-team.png" alt="Photo" />
                    <h3 class="content__team-card__container-1__name">Kolkata Knight Riders</h3>
                </div>
                <div class="content__team-card__container-2"></div>
            </div>
            <div class="content__team-card col-sm-12" onclick="expandTeam(this)" draggable="true" ondragstart="dragStart(event)" ondragend="dragStop(event)">
                <div class="content__team-card__container-1">
                    <img class="content__team-card__container-1__photo" draggable="false" src="/CricAlert/assets/icons/icons8-team.png" alt="Photo" />
                    <h3 class="content__team-card__container-1__name">Mumbai Indians</h3>
                </div>
                <div class="content__team-card__container-2"></div>
            </div>
        </section>
        <div class="minimize" onclick="minimizeSearch()">
            <img class="minimize__icon" src="/CricAlert/assets/icons/icons8-down.png" alt="Minimize Search">
        </div>
        <footer class="footer">
            <div class="footer__search-bar">
                <form action="#">
                    <input type="text" placeholder="Search..."/>
                </form>
            </div>
            <div class="footer__search" onclick="expandSearch()">
                <img class="footer__search__icon" src="/CricAlert/assets/icons/icons8-search-2.png" alt="Search">
            </div>
            <div class="footer__add-item" onclick="window.location='/CricAlert/pages/add-team.jsp'">
                <img class="footer__add-item__icon" src="/CricAlert/assets/icons/icons8-plus-6.png" alt="Add player">
            </div>
            <div class="footer__edit" ondrop="drop(event)" ondragover="allowDrop(event, this)">
                <img class="footer__edit__icon" src="/CricAlert/assets/icons/icons8-edit-1.png" alt="Edit">
            </div>
            <div class="footer__delete" ondrop="drop(event)" ondragover="allowDrop(event, this)">
                <img class="footer__delete__icon" src="/CricAlert/assets/icons/icons8-trash-1.png" alt="Delete">
            </div>
        </footer>
    </div>
    <script src="/CricAlert/js/main-common.js"></script>
    <script src="/CricAlert/js/teams.js"></script>
</body>

</html>