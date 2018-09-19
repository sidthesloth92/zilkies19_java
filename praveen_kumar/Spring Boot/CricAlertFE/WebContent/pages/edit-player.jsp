<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="io.ztech.cricalertfe.beans.Team"%>
<%@ page import ="io.ztech.cricalertfe.beans.Player"%>
<%@ page import ="io.ztech.cricalertfe.constants.Paths"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href=<%= Paths.GRID_CSS %>>
    <link rel="stylesheet" href=<%= Paths.ADD_PLAYER_CSS %>>
    <title>Edit Player</title>
</head>

<body>
    <header>
        <img class="return-icon" src=<%= Paths.ICONS_BACK %> alt="return" onclick="window.location='<%= Paths.PLAYERS_SERVLET %>'" />
        <img class="options-icon" src=<%= Paths.ICONS_DOTS %> alt="options" />
    </header>
    <section class="details">
        <div class="details__photo">
            <div class="details__photo-container">
                <img src=<%= Paths.ICONS_DP %> alt="avatar">
            </div>
            <img class="details__add-photo" src=<%= Paths.ICONS_PLUS_2 %> alt="Add Image" />
        </div>
        <form class="details__form" action=<%= Paths.EDIT_PLAYER_SERVLET %> method="POST">
        	<%
       		Player player = (Player) request.getAttribute("player");
        	out.println("<input class='details__form__input' name='playerId' type='hidden' value='" + player.getPlayerId() +"'/>");
        	out.println("<input class='details__form__input' name='fname' type='text' placeholder='First Name' value='" + player.getFirstName() +"'/>");
            out.println("<input class='details__form__input' name='lname' type='text' placeholder='Last Name' value='" + player.getLastName() +"'/>");
        	%>

            <select name="team">
            
	            <% 
				ArrayList<Team> teamList = (ArrayList<Team>) request.getAttribute("teamList");
				for (Team team : teamList) {
					if (team.getTeamId() == player.getTeamId()) {
						out.println("<option value='" + team.getTeamId() + "' selected='selected'>" + team.getTeamName() + "</option>");
					} else {
						out.println("<option value='" + team.getTeamId() + "'>" + team.getTeamName() + "</option>");
					}
				}
				%>

            </select>
            <input type="submit" class="confirm-details col-sm-12" value="Confirm Details"></input>
        </form>
    </section>
</body>

</html>