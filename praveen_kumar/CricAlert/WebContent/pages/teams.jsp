<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="io.ztech.cricalert.beans.Team"%>
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
        	<img class="close-bar" src="/CricAlert/assets/icons/icons8-close.png" alt="close" onclick="sideBarClick()">
            <ul>
                <li><div onclick="window.location='/CricAlert/Home'" class="">Home</div></li>
                <li><div onclick="window.location='/CricAlert/Teams'" class="nav-highlight-2">Teams</div></li>
                <li><div onclick="window.location='/CricAlert/Players'" class="">Players</div></li>
                <li><div onclick="window.location='/CricAlert/Login'" class="">Logout</div></li>
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

            <% 
			ArrayList<Team> teamList = (ArrayList<Team>) request.getAttribute("teamList");
			for (Team team : teamList) {
				out.println("<div id='" + team.getTeamId() + "' class='content__team-card col-sm-12' onclick='expandTeam(this)' draggable='true' ondragstart='dragStart(event)' ondragend='dragStop(event)'>");
				out.println("<div class='content__team-card__container-1'>");
				out.println("<img class='content__team-card__container-1__photo' draggable='false' src='/CricAlert/assets/images/" + team.getTeamName() + ".png' onerror=\"if (this.src != '/CricAlert/assets/icons/icons8-team.png') this.src = '/CricAlert/assets/icons/icons8-team.png';\" alt='Photo' />");
				out.println("<h3 class='content__team-card__container-1__name'>" + team.getTeamName() + "</h3>");
				out.println("</div>");
				out.println("<div class='content__team-card__container-2'></div>");
				out.println("</div>");
			}
			%>
            
            <div class="content__modal">
				<div class="content__modal__delete-confirmation">
                    <h3>Are you sure you want to delete the team?</h3>
                    <div class="content__modal__delete-confirmation__response">
                        <div class="content__modal__delete-confirmation__response__yes">Yes</div>
                        <div class="content__modal__delete-confirmation__response__no">No</div>
                    </div>
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
            <div class="footer__add-item" onclick="window.location='/CricAlert/AddTeam'">
                <img class="footer__add-item__icon" src="/CricAlert/assets/icons/icons8-plus-6.png" alt="Add player">
            </div>
            <div class="footer__edit" ondrop="editTeam(event)" ondragover="allowDrop(event, this)">
                <img class="footer__edit__icon" src="/CricAlert/assets/icons/icons8-edit-1.png" alt="Edit">
            </div>
            <div class="footer__delete" ondrop="deleteConfirmation(event)" ondragover="allowDrop(event, this)">
                <img class="footer__delete__icon" src="/CricAlert/assets/icons/icons8-trash-1.png" alt="Delete">
            </div>
        </footer>
    </div>
    <script src="/CricAlert/js/main-common.js"></script>
    <script src="/CricAlert/js/teams.js"></script>
</body>

</html>