function evaluateTeam(){
	if(checkTeamNames() == false || checkDate() ==false || checkTime() ==false ){
		return false;
	}
	var teamOne = document.forms["add-match_form"]["team-one"].value;
	var teamTwo = document.forms["add-match_form"]["team-two"].value;
	var matchDate = document.forms["add-match_form"]["match-date"].value;
	var startTime = document.forms["add-match_form"]["start-time"].value;
	var endTime = document.forms["add-match_form"]["end-time"].value;
	var credits = document.forms["add-match_form"]["credits"].value;
	if(teamOne == "" || teamTwo == "" || matchDate == "" || startTime == "" || endTime == "" || credits == ""){
		alert("Fill in the Values Correctly!");
		return false;
	}
	var submitButton = document.getElementsByClassName("submit-button")[0];
	submitButton.disabled = true;
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      alert("Match added Sucessfully");
	      document.forms["add-match_form"].reset();
	      submitButton.disabled = false;
	    }
	  };
	  xhttp.open("POST", "http://127.0.0.1:8080/SpringMVCUsingXML/AddMatch", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("team-one="+teamOne+"&team-two="+teamTwo+"&start-date="+matchDate+"&start-time="+startTime+"&end-time="+endTime +"&credits="+credits);
	  //console.log(submitButton);
}

function checkTeamNames(){
	var teamOne = document.getElementsByClassName("team-one")[0].value;
	var teamTwo = document.getElementsByClassName("team-two")[0].value;
	if(teamOne == teamTwo){
		document.getElementsByClassName("name-error")[0].innerHTML = "Names Should Not be Same";
		return false;
	}
	else{
		document.getElementsByClassName("name-error")[0].innerHTML = "";
	}
	return true;
}

function checkDate(){
	var  date = document.getElementsByClassName("match-date")[0].value;
	var matchDate = new Date(date);
	var currentDate = new Date();
	if(matchDate <= currentDate){
		document.getElementsByClassName("date-error")[0].innerHTML = "Invalid date";
		return false;
	}
	else{
		document.getElementsByClassName("date-error")[0].innerHTML = "";
	}
	return true;
}

function checkTime(){
	var startTime = document.getElementsByClassName("start-time")[0].value;
	var endTime = document.getElementsByClassName("end-time")[0].value;
	if(startTime > endTime){
		document.getElementsByClassName("time-error")[0].innerHTML = "Please Check the start and end time";
		return false;
	}
	else{
		document.getElementsByClassName("time-error")[0].innerHTML = "";
	}
	return true;
}