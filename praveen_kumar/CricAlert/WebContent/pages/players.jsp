<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="io.ztech.cricalert.beans.Player"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/CricAlert/css/grid-common.css">
    <link rel="stylesheet" href="/CricAlert/css/main-common.css">
    <link rel="stylesheet" href="/CricAlert/css/playerstyle.css">
    <link href="https://fonts.googleapis.com/css?family=Cuprum|Lato|Lobster|Lobster+Two|Pacifico" rel="stylesheet">
    <title>Players</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body>
    <div class="main-container">
        <nav class="side-bar">
            <ul>
                <img class="close-bar" src="/CricAlert/assets/icons/icons8-close.png" alt="close" onclick="sideBarClick()">
                <li><a href="/CricAlert/pages/home.jsp" class="">Home</a></li>
                <li><a href="/CricAlert/pages/teams.jsp" class="">Teams</a></li>
                <li><a href="/CricAlert/pages/players.jsp" class="nav-highlight-2">Players</a></li>
                <li><a href="/CricAlert/pages/index.jsp" class="">Logout</a></li>
            </ul>
        </nav>
        <header class="header">
            <div class="header__title-bar col-sm-12">
                <img class="header__title-bar__hamburger" src="/CricAlert/assets/icons/icons8-menu-15.png" alt="Menu" onclick="sideBarClick()" />
                <h1 class="header__title-bar__title">Players</h1>
            </div>
            <nav class="header__nav-bar col-sm-12">
                <div onclick="window.location='/CricAlert/Home'" class="">Home</div>
                <div onclick="window.location='/CricAlert/Teams'" class="">Teams</div>
                <div onclick="window.location='/CricAlert/Players'" class="nav-highlight">Players</div>
            </nav>
        </header>
        <section class="content">
            
            <% 
				ArrayList<Player> playerList = (ArrayList<Player>) request.getAttribute("playerList");
				for (Player player : playerList) {
					out.println("<div id='" + player.getPlayerId() + "' class='content__player-card' onclick='expandCard(this)' draggable='true' ondragstart='dragStart(event)' ondragend='dragStop(event)'>");
					out.println("<div class='content__player-card-photo'>");
					out.println("<img src='/CricAlert/assets/icons/icons8-dp.png' draggable='false' alt='Display Picture'>");
					out.println("</div>");
					out.println("<div class='content__player-card-name'>" + player.getFirstName() + " " + player.getLastName() + "</div>");
					out.println("</div>");
				}
			%>
            
            <div class="content__modal">
                <div class="content__modal__player-info">
                    <div class="content__modal__player-info__photo">
                        <img src="/CricAlert/assets/icons/icons8-dp.png" alt="">
                    </div>
                    <span class="close">&times;</span>
                    <h3 class="content__modal__player-info__name"></h3>
                    <h3 class="content__modal__player-info__team"></h3>
                </div>
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
            <div class="footer__add-item" onclick="window.location='/CricAlert/AddPlayer'">
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
    <script src="/CricAlert/js/players.js"></script>
</body>

</html>