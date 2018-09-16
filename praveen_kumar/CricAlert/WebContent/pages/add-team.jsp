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
    <link rel="stylesheet" href="/CricAlert/css/add-team.css">
    <title>Create Team</title>
</head>

<body>
    <header>
        <img class="return-icon" src="/CricAlert/assets/icons/icons8-back-1.png" alt="return" onclick="window.location='/CricAlert/Teams'"
        />
        <img class="options-icon" src="/CricAlert/assets/icons/icons8-dots.png" alt="options" />
    </header>
    <section class="details">
        <div class="details__photo">
            <div class="details__photo-container">
                <img src="/CricAlert/assets/icons/icons8-team.png" alt="avatar">
            </div>
            <img class="details__add-photo" src="/CricAlert/assets/icons/icons8-plus-2.png" alt="Add Image" />
        </div>
        <form class="details__form" action="/CricAlert/AddTeam" method="POST">
            <input class="details__form__input" name="name" type="text" placeholder="Team Name" />
            <button class="details__form__add-players" type="button">Add Players</button>
            <div class="details__form__modal">
                <div class="details__form__modal__players-list">
                    <span class="close">&times;</span>
                    <% 
					ArrayList<Player> playerList = (ArrayList<Player>) request.getAttribute("playerList");
					for (Player player : playerList) {
						out.println("<div class='details__form__modal__players-list__player'><input type='checkbox' name='players' value='" + player.getPlayerId() + "'><span>" + player.getFirstName() + " " + player.getLastName() + "</span></div>");
					}
					%>
                </div>
            </div>
            <input type="submit" class="confirm-details col-sm-12" value="Confirm Details"></input>
        </form>
    </section>
    <script src="/CricAlert/js/add-team.js"></script>
</body>

</html>