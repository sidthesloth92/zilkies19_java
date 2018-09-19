<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/CricAlert/css/play.css">
    <link rel="stylesheet" href="/CricAlert/css/grid-common.css">
    <title>Play Match</title>
</head>
<body>
	<%-- <c:set var="match" scope="request" value='${requestScope["match"]}'></c:set>
	<c:set var="teamA" scope="request" value='${match.getTeamA()}'></c:set>
	<c:set var="teamB" scope="request" value='${match.getTeamB()}'></c:set> --%>
        <header class="header">
        <div class="header__title-bar col-sm-12">
            <img class="header__title-bar__return" src="/CricAlert/assets/icons/icons8-back-1.png" alt="return" onclick="pauseMatch()" />
            <h1 class="header__title-bar__title"></h1>
        </div>
        <nav class="header__nav-bar col-sm-12">
            <a href="#" class="nav-highlight">Live</a>
            <a href="#" class="">Scorecard</a>
            <a href="#" class="">Overs</a>
        </nav>
    </header>
    <div class="modal">
        <div class="modal__toss">
            <p>Who won the toss?</p>
            <div class="modal__toss__container">
                <div class="modal__toss__container__team-A" onclick="selectTeam(this)"></div>
                <div class="modal__toss__container__team-B" onclick="selectTeam(this)"></div>
            </div>
            <p>Team chose to,</p>
            <div class="modal__toss__container">
                <div class="modal__toss__container__bat" onclick="selectChoice(this)">BAT</div>
                <div class="modal__toss__container__bowl" onclick="selectChoice(this)">BOWL</div>
            </div>
            <div class="modal__toss__confirm" onclick="confirmChoice()">Confirm</div>
        </div>
        <div class="modal__choose-players">
            <form name="opening-players" action="#">
                <span>Choose on-strike batsman:</span><select name="on-strike"></select>
                <span>Choose off-strike batsman:</span><select name="off-strike"></select>
                <span>Choose bowler:</span><select name="bowler"></select>
                <button type="button" onclick="confirmPlayers()">Go!</button>
            </form>
        </div>
        <div class="modal__next-bowler">
            <form name="next-bowler" action="#">
                <span>Current over has finished!<br><br>Choose next bowler:</span><select name="bowler"></select>
                <button type="button" onclick="nextBowler()">Confirm</button>
            </form>
        </div>
        <div class="modal__next-batsman">
            <form name="next-batsman" action="#">
                <span>Batsman is out!<br><br>Choose next batsman:</span><select name="batsman"></select>
                <button type="button" onclick="nextBatsman()">Confirm</button>
            </form>
        </div>
        <div class="modal__innings-end">
            <h3>Innings has ended!</h3>
            <h3 class="modal__innings-end__team"></h3>
            have scored
            <h3 class="modal__innings-end__score"></h3>
            in
            <h3 class="modal__innings-end__overs"></h3>
            <button class="modal__innings-end__continue" type="button" onclick="chooseOpeningPlayers()">Continue Match</button>
        </div>
        <div class="modal__match-end">
            <p>The match has ended!</p>
            <img class="modal__match-end__photo" src="/CricAlert/assets/icons/icons8-team.png" alt="team">
            <p class="modal__match-end__team"></p>
            <p class="modal__match-end__result"></p>
            <p class="modal__match-end__wickets"></p>
            <button class="modal__match-end__finish" type="button" onclick="endMatch()">End Match</button>
        </div>
    </div>
    <section class="overview">
        <table class="overview__batsmen">
            <tr>
                <th></th>
                <th>R</th>
                <th>B</th>
                <th>4s</th>
                <th>6s</th>
                <th>SR</th>
            </tr>
        </table>
        <table class="overview__bowler">
            <tr>
                <th></th>
                <th>O</th>
                <th>M</th>
                <th>R</th>
                <th>W</th>
                <th>ER</th>
            </tr>
        </table>
        <div class="balls"></div>
    </section>
    <section class="input">
        <div class="input__extra">
            <div class="input__extra__wicket" onclick="wicketTaken()">WK</div>
            <div class="input__extra__no-ball" onclick="addExtra(event)">NB</div>
            <div class="input__extra__wide" onclick="addExtra(event)">WD</div>
            <div class="input__extra__byes" onclick="addExtra(event)">B</div>
            <div class="input__extra__leg-byes" onclick="addExtra(event)">LB</div>
            <div class="input__extra__penalty" onclick="addExtra(event)">P</div>
        </div>
        <div class="input__runs">
            <div class="input__runs__dot" onclick="addBall(event)">.</div>
            <div class="input__runs__one" onclick="addBall(event)">1</div>
            <div class="input__runs__two" onclick="addBall(event)">2</div>
            <div class="input__runs__three" onclick="addBall(event)">3</div>
            <div class="input__runs__four" onclick="addBall(event)">4</div>
            <div class="input__runs__five" onclick="addBall(event)">5</div>
            <div class="input__runs__six" onclick="addBall(event)">6</div>
            <div class="input__runs__more" onclick="addBall(event)">+</div>
        </div>
    </section>
    <footer class="scorecard">
        <div class="scorecard__container-1">
            <div class="scorecard__container-1__team-a"></div>
            <div class="scorecard__container-1__team-b"></div>
            <div class="scorecard__container-1__score"></div>
        </div>
        <div class="scorecard__container-2">
            <div class="scorecard__container-2__overs"></div>
            <div class="scorecard__container-2__target"></div>
        </div>
        <div class="scorecard__container-3">
            <div class="scorecard__container-3__run-rate"></div>
            <div class="scorecard__container-3__required"></div>
        </div>
    </footer>
    <script src="/CricAlert/js/play.js"></script>
</body>
</html>