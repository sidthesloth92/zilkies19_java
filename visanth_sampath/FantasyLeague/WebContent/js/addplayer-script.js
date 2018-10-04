function addPlayerRequest(){
	if(checkPlayerName() == false){
		return false;
	}
	var playerName = document.forms["add-player_form"]["player-name"].value;
	var teamName = document.forms["add-player_form"]["team-name"].value;
	var submitButton = document.getElementsByClassName("submit-button")[0];
	if(teamName == "others"){
		teamName = document.forms["add-player_form"]["new-team_name"].value;
	}
	var category = document.forms["add-player_form"]["player-category"].value;
	var rating = document.forms["add-player_form"]["player-rating"].value;
	if(playerName == "" || teamName == ""){
		alert("Wrong values");
		return false;
	}
	submitButton.disabled = true;
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      alert("Player added Sucessfully");
	      document.forms["add-player_form"].reset();
	      submitButton.disabled = false;
	    }
	  };
	  xhttp.open("POST", "http://127.0.0.1:8080/FantasyLeague/AddPlayer", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("player-name="+playerName+"&team="+teamName+"&role="+category+"&rating="+rating);
}

function addNewTeam(){
	var teamName = document.forms["add-player_form"]["team-name"].value;
	if(teamName == "others"){
		document.getElementsByClassName("new-team")[0].style.display = "block";
	}
	else {
		document.getElementsByClassName("new-team")[0].style.display = "none";
	}
}

function checkPlayerName(){
	var playerName = document.getElementsByClassName("player-name")[0].value;
	if(/^[a-zA-Z]+$/.test(playerName)){
		document.getElementsByClassName("name-error")[0].innerHTML = "";
	}
	else{
		document.getElementsByClassName("name-error")[0].innerHTML = "Enter a Valid Name";
		return false;
	}
	return true;
}