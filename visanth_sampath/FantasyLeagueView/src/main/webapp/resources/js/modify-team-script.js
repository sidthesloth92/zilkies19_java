

var matchId = document.getElementById("match-id").value;


var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
    var json =  this.responseText;
    var mixedArray = json.split("\n");
   // var mixedArray = responseArray.map(Number);
    console.log(mixedArray);
    for(var index = 0; index < (mixedArray.length)-1 ; index+=2){
    	players.push(mixedArray[index]);
    	credits.push(mixedArray[index+1]);
    	var id = mixedArray[index];
    	//console.log(id);
    	var addPlayerClass = id+"-add";//["document"]["activeElement"]["classList"][0]
    	var player = id+"-player";
    	//console.log(document.getElementsByClassName(player)[0]["childNodes"][2].innerHTML);
    	var playerRole = document.getElementsByClassName(player)[0]["children"][1].innerHTML;
    	var playerCredit = document.getElementsByClassName(player)[0]["children"][2].innerHTML;
    	matchCredits-=playerCredit;
    	var node = "";
    	node += "<div class='row "+id+"-cancel player_seperator'>"+
        "<div class='col-6'>"+document.getElementsByClassName(player)[0]["children"][0].innerHTML+"</div>"+
        "<div class='col-3'>"+document.getElementsByClassName(player)[0]["children"][1].innerHTML+"</div>"+
        "<div class='col-2'>"+document.getElementsByClassName(player)[0]["children"][2].innerHTML+"</div>"+
        "<div class='col-1'><button value='"+id+ "' class='"+id+"' onclick='removePlayer()'>X</button></div>"+
        "</div>";
    	//console.log(node);
    	document.getElementsByClassName(addPlayerClass)[0].disabled = true;
    	document.getElementsByClassName(addPlayerClass)[0].style.backgroundColor = "red";
    	document.getElementsByClassName(addPlayerClass)[0].style.cursor = "not-allowed";
    	//console.log(document.getElementsByClassName("grow")[0]);
    	document.getElementsByClassName("grow")[0].innerHTML +=node;
    	//console.log(players);
    	document.getElementsByClassName("remaining-display_batsman")[0].innerHTML = (4-batsman) +" Batsman Remaining";
    	document.getElementsByClassName("remaining-display_bowler")[0].innerHTML = (3-bowler) +" Bowlers Remaining";
    	document.getElementsByClassName("remaining-display_wicket-keeper")[0].innerHTML = (1-wicketKeeper) +" Keeper Remaining";
    	document.getElementsByClassName("remaining-display_all-rounder")[0].innerHTML = (3-allRounder) +" All Rounder Remaining";
    	document.getElementsByClassName("remaining-credits")[0].innerHTML = "You have " + matchCredits +" Credits remaining";
    	console.log(players);
    }
  }
};
var url ="GetUserTeamServlet?match-id="+matchId;
xhttp.open("GET", url, true);
xhttp.send();







var players = [];
var credits = [];
var batsman = 4 , bowler = 3 , allRounder = 3 , wicketKeeper = 1;
var matchCredits = document.getElementById("match-credits").value;
//console.log(matchCredits);
document.getElementsByClassName("remaining-credits")[0].innerHTML = "You have " + matchCredits +" Credits remaining";
//console.log(matchCredits);
function addPlayer(){
	if(players.length >= 11){
		alert("11 players selected already selected");
		return false;
	}
	id = this["document"]["activeElement"]["classList"][0];
	//console.log(id);
	addPlayerClass = id+"-add";//["document"]["activeElement"]["classList"][0]
	player = id+"-player";
	//console.log(document.getElementsByClassName(player)[0]["childNodes"][2].innerHTML);
	var playerRole = document.getElementsByClassName(player)[0]["children"][1].innerHTML;
	var playerCredit = document.getElementsByClassName(player)[0]["children"][2].innerHTML;
	if((matchCredits-playerCredit) < 0 ){
		alert("You does not have enough credits");
		return false;
	}
	if(playerRole == "Batsman"){
		if(batsman >= 4){
			document.getElementsByClassName("remaining-display_batsman")[0].style.color = "brown";
			return false;
		}
		batsman++;	
	}
	else if(playerRole == "Bowler"){
		if(bowler >= 3){
			document.getElementsByClassName("remaining-display_bowler")[0].style.color = "brown";
			return false;
		}
		bowler++;
	}
	else if(playerRole == "Wicket Keeper"){
		if(wicketKeeper >= 1){
			document.getElementsByClassName("remaining-display_wicket-keeper")[0].style.color = "brown";
			return false;
		}
		wicketKeeper++;		
	}
	else if(playerRole == "All Rounder"){
		if(allRounder >= 3){
			document.getElementsByClassName("remaining-display_all-rounder")[0].style.color = "brown";
			return false;
		}
		allRounder++;		
	}
	matchCredits-=playerCredit;
	//console.log(matchCredits);
	//console.log(batsman);	
	players.push(id);
	credits.push(playerCredit);
	var node = "";
	node += "<div class='row "+id+"-cancel player_seperator'>"+
    "<div class='col-6'>"+document.getElementsByClassName(player)[0]["children"][0].innerHTML+"</div>"+
    "<div class='col-3'>"+document.getElementsByClassName(player)[0]["children"][1].innerHTML+"</div>"+
    "<div class='col-2'>"+document.getElementsByClassName(player)[0]["children"][2].innerHTML+"</div>"+
    "<div class='col-1'><button value='"+id+ "' class='"+id+"' onclick='removePlayer()'>X</button></div>"+
    "</div>";
	//console.log(node);
	document.getElementsByClassName(addPlayerClass)[0].disabled = true;
	document.getElementsByClassName(addPlayerClass)[0].style.backgroundColor = "red";
	document.getElementsByClassName(addPlayerClass)[0].style.cursor = "not-allowed";
	//console.log(document.getElementsByClassName("grow")[0]);
	document.getElementsByClassName("grow")[0].innerHTML +=node;
	console.log(players);
	if(players.length >= 11){
		document.getElementsByClassName("players-submit_button")[0].style.display = "block";
	}
	document.getElementsByClassName("remaining-display_batsman")[0].innerHTML = (4-batsman) +" Batsman Remaining";
	document.getElementsByClassName("remaining-display_bowler")[0].innerHTML = (3-bowler) +" Bowlers Remaining";
	document.getElementsByClassName("remaining-display_wicket-keeper")[0].innerHTML = (1-wicketKeeper) +" Keeper Remaining";
	document.getElementsByClassName("remaining-display_all-rounder")[0].innerHTML = (3-allRounder) +" All Rounder Remaining";
	document.getElementsByClassName("remaining-credits")[0].innerHTML = "You have " + matchCredits +" Credits remaining";
}

function removePlayer(){
	//console.log(this);
	var id = this["document"]["activeElement"]["classList"][0];
	var removeClass= id+"-cancel";
	var addPlayerClass = id+"-add";
	console.log(document.getElementsByClassName(id));
	var selected = document.getElementsByClassName(removeClass);
	for(var i=0; i<selected.length ;i++){
		selected[i].style.display = "none";
	}
	document.getElementsByClassName(addPlayerClass)[0].disabled = false;
	document.getElementsByClassName(addPlayerClass)[0].style.backgroundColor = "green";
	document.getElementsByClassName(addPlayerClass)[0].style.cursor = "pointer";
	
	var collectionLength = document.getElementsByClassName(id).length;
	var playerRole = document.getElementsByClassName(id)[collectionLength-2]["children"][1].innerHTML;
	var playerCredit = Number(document.getElementsByClassName(id)[collectionLength-2]["children"][2].innerHTML);
	matchCredits += playerCredit;
	console.log(matchCredits);
	//console.log(playerRole);
	if(playerRole == "Batsman"){
		batsman--;
	}
	else if(playerRole == "Bowler"){
		bowler--;
	}
	else if(playerRole == "Wicket Keeper"){
		wicketKeeper--;
	}
	else if(playerRole == "All Rounder"){
		allRounder--;
	}
	console.log(players);
	for(var j=0;j < players.length;j++){
		if(players[j] == id){
			//console.log("ss");
			players.splice(j,1);
			credits.splice(j,1);
		}
	}
	console.log(players);
	document.getElementsByClassName("remaining-display_batsman")[0].innerHTML = (4-batsman) +" Batsman Remaining";
	document.getElementsByClassName("remaining-display_bowler")[0].innerHTML = (3-bowler) +" Bowlers Remaining";
	document.getElementsByClassName("remaining-display_wicket-keeper")[0].innerHTML = (1-wicketKeeper) +" Keeper Remaining";
	document.getElementsByClassName("remaining-display_all-rounder")[0].innerHTML = (3-allRounder) +" All Rounder Remaining";
	document.getElementsByClassName("remaining-credits")[0].innerHTML = "You have " + matchCredits +" Credits remaining";
	if(players.length < 11){
		document.getElementsByClassName("players-submit_button")[0].style.display = "none";
	}
}

function submitTeam(){
	document.getElementsByClassName("players-submit_button")[0].disabled = true;
	document.getElementsByClassName("players-submit_button")[0].style.cursor = "not-allowed";
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      console.log(this.responseText);
	      alert("sucess");
	      window.location.href="PageRedirectionServlet?page-name=available-matches";
	      
	    }
	  };
	  xhttp.open("POST", "UpdateTeamServlet", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("playing-team="+players+"&credits="+credits+"&match-id="+matchId);
}