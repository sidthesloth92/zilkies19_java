var getCurrentRatingButton = document.getElementsByClassName("get-rating_buttton")[0];
var playerDropDown = document.forms["player-name_form"]["player-name"];
function getRating(){	
	getCurrentRatingButton.disabled = true;
	playerDropDown.disabled = true;
	var playerId = playerDropDown.value;
	//ajax request
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      console.log(this.responseText);
	      document.getElementsByClassName("player-rating_updation_block")[0].style.display = "block";
	      document.getElementsByClassName("current-rating")[0].innerHTML = "Current Rating : "+ this.responseText;
	      //Current Rating : 10
	    }
	  };
	  xhttp.open("POST", "CurrentPlayerRating", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("player-id="+playerId);
}

function updateRating(){
	var updatedRating = document.forms["updated-rating_form"]["new-rating"].value;
	//console.log(updatedRating);
	var playerId = playerDropDown.value;
	//console.log(playerId);
	//ajax request
	
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      alert("Updation Sucess!");
	      document.getElementsByClassName("player-rating_updation_block")[0].style.display = "none";
	      document.forms["player-name_form"].reset();
	      document.forms["updated-rating_form"].reset();
	      getCurrentRatingButton.disabled = false;
	  	playerDropDown.disabled = false;
	      //Current Rating : 10
	    }
	  };
	  xhttp.open("POST", "UpdatePlayerRating", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("player-id="+playerId + "&updated-rating=" + updatedRating);
	
}