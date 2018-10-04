<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="io.ztech.cricalertfe.beans.Player"%>
<%@ page import ="io.ztech.cricalertfe.constants.Paths"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href=<%= Paths.GRID_CSS %>>
    <link rel="stylesheet" href=<%= Paths.ADD_TEAM_CSS %>>
    <title>Create Team</title>
</head>

<body>
    <header>
        <img class="return-icon" src=<%= Paths.ICONS_BACK %> alt="return" onclick="window.location='<%= Paths.TEAMS_SERVLET %>'"
        />
        <img class="options-icon" src=<%= Paths.ICONS_DOTS %> alt="options" />
    </header>
    <section class="details">
        <div class="details__photo">
            <div class="details__photo-container">
                <img src=<%= Paths.ICONS_TEAM %> alt="avatar">
            </div>
            <img class="details__add-photo" src=<%= Paths.ICONS_PLUS_2 %> alt="Add Image" />
        </div>
        <form class="details__form" action=<%= Paths.ADD_TEAM_SERVLET %> method="POST">
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
    <script src=<%= Paths.ADD_TEAM_SCRIPT %>></script>
</body>

</html>