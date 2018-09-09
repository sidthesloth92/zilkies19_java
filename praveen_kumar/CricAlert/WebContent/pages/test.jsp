<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.Iterator"%>
<%@ page import ="io.ztech.cricalert.beans.Player"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(document).on("click", ".info", function() {
		$.post("/CricAlert/PlayerModal", 
		{
			playerId: "19"
		}, 
		function(responseJson, status) {
			$(".info").text(responseJson.firstName + " " + responseJson.lastName + " " + responseJson.teamName);
		});
	});
</script>
</head>
<body>
	<jsp:useBean id="user" class="io.ztech.cricalert.beans.User" scope="session" />
	<jsp:getProperty name="user" property="userName" />
	<jsp:getProperty name="user" property="name" />
	<br><br>
	<%-- <% 
		ArrayList<Player> playerList = (ArrayList<Player>) request.getAttribute("playerList");
		for (Player player : playerList) {
			out.println("<div id='" + player.getPlayerId() + "' class='content__player-card' onclick='expandCard(event)' draggable='true' ondragstart='dragStart(event)' ondragend='dragStop(event)'>");
			out.println("<div class='content__player-card-photo'>");
			out.println("<img src='/CricAlert/assets/icons/icons8-dp.png' draggable='false' alt='Display Picture'>");
			out.println("</div>");
			out.println("<div class='content__player-card-name'>" + player.getFirstName() + " " + player.getLastName() + "</div>");
			out.println("</div>");
		}
	%> --%>
	<div id="19" class="info" style="border: 1px solid hotpink; width: 500px; height: 500px;"></div>
</body>
</html>