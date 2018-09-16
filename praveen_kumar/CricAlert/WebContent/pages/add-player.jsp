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
    <link rel="stylesheet" href="/CricAlert/css/add-player.css">
    <title>Create Player</title>
</head>

<body>
    <header>
        <img class="return-icon" src="/CricAlert/assets/icons/icons8-back-1.png" alt="return" onclick="window.location='/CricAlert/Players'" />
        <img class="options-icon" src="/CricAlert/assets/icons/icons8-dots.png" alt="options" />
    </header>
    <section class="details">
        <div class="details__photo">
            <div class="details__photo-container">
                <img src="/CricAlert/assets/icons/icons8-dp.png" alt="avatar">
            </div>
            <img class="details__add-photo" src="/CricAlert/assets/icons/icons8-plus-2.png" alt="Add Image" />
        </div>
        <form class="details__form" action="/CricAlert/AddPlayer" method="POST">
            <input class="details__form__input" name="fname" type="text" placeholder="First Name" />
            <input class="details__form__input" name="lname" type="text" placeholder="Last Name" />
            <select name="team" required>
            	<option value="" selected disabled hidden>Choose Team</option>
            
	            <% 
				ArrayList<Team> teamList = (ArrayList<Team>) request.getAttribute("teamList");
				for (Team team : teamList) {
					out.println("<option value='" + team.getTeamId() + "'>" + team.getTeamName() + "</option>");
				}
				%>

            </select>
            <input type="submit" class="confirm-details col-sm-12" value="Confirm Details"></input>
        </form>
    </section>
</body>

</html>