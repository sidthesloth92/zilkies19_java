var players = document.getElementsByClassName("players-hidden_load");
var numberOfNodes = document.getElementsByClassName("players-hidden_load")[0]["children"].length;
console.log(players);
var role , name ,node;
var batsman = "" , bowler = "" ,allRounder = "" , wicketKeeper ="";
for(var i=1; i<numberOfNodes ; i+=2){
	role = players[0]["children"][i].innerHTML;
	name = players[0]["children"][i-1].innerHTML;
	node ="<div class='row'>" +
		    "<div class='col-sm-5 col-md-5 col-lg-5'>" +
		    "<!--  <img src='https://www.thefamouspeople.com/profiles/images/m-s-dhoni-2.jpg' class='player-image'/> --> "+
		    "</div>"+
		    "<div class='col-sm-7 col-md-7 col-lg-7'>"+
		     name +"</div>"+"</div>";
	//console.log(role);
	if(role == "Batsman"){
		//console.log("ss")
		batsman += node;
	}
	else if(role == "Bowler"){
		bowler += node;
	}
	else if(role == "All Rounder"){
		allRounder += node;
	}
	else if(role == "Wicket Keeper"){
		wicketKeeper += node;
	}
}
document.getElementsByClassName("batsman-container")[0].innerHTML = batsman;
document.getElementsByClassName("bowler-container")[0].innerHTML = bowler;
document.getElementsByClassName("all-rounder-container")[0].innerHTML = allRounder;
document.getElementsByClassName("wicket-keeper-container")[0].innerHTML = wicketKeeper;
