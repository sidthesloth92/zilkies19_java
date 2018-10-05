var players = [];

function addPlayer(){
	if(players.length >= 22){
		alert("22 players selected already selected");
		return false;
	}
	var id = this["document"]["activeElement"]["classList"][0];
	//console.log(id);
	var addPlayer = id+"-add";//["document"]["activeElement"]["classList"][0]
	players.push(id);
	var player = id+"-player";
	console.log(document.getElementsByClassName(player));
	var node = "";
	node += "<div class='row "+id+"-cancel'>"+
    "<div class='col-6'>"+document.getElementsByClassName(player)[0]["children"][0].innerHTML+"</div>"+
    "<div class='col-3'>"+document.getElementsByClassName(player)[0]["children"][1].innerHTML+"</div>"+
    "<div class='col-2'>"+document.getElementsByClassName(player)[0]["children"][2].innerHTML+"</div>"+
    "<div class='col-1'><button value='"+id+ "' class='"+id+"' onclick='removePlayer()'>X</button></div>"+
    "</div>";
	//console.log(node);
	document.getElementsByClassName(addPlayer)[0].disabled = true;
	document.getElementsByClassName(addPlayer)[0].style.backgroundColor = "red";
	document.getElementsByClassName(addPlayer)[0].style.cursor = "not-allowed";
	//console.log(document.getElementsByClassName("grow")[0]);
	document.getElementsByClassName("grow")[0].innerHTML +=node;
	//console.log(players);
	if(players.length >= 22){
		document.getElementsByClassName("players-submit_button")[0].style.display = "block";
	}
}

function removePlayer(){
	console.log(this);
	var id = this["document"]["activeElement"]["classList"][0];
	var removeClass= id+"-cancel";
	var addPlayer = id+"-add";
	console.log(document.getElementsByClassName(id)[2]);
	var selected = document.getElementsByClassName(removeClass);
	for(var i=0; i<selected.length ;i++){
		selected[i].style.display = "none";
	}
	document.getElementsByClassName(addPlayer)[0].disabled = false;
	document.getElementsByClassName(addPlayer)[0].style.backgroundColor = "green";
	document.getElementsByClassName(addPlayer)[0].style.cursor = "pointer";
	
	console.log(id);
	for(var j=0;j < players.length;j++){
		if(players[j] == id){
			//console.log("ss");
			players.splice(j,j+1);
		}
	}
}

function updatePlayingTeam(){
	var matchId = document.getElementById("match-id").value;
	document.getElementsByClassName("players-submit_button")[0].disabled = true;
	document.getElementsByClassName("players-submit_button")[0].style.cursor = "not-allowed";
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      console.log(this.responseText);
	      alert("sucess");
	      window.location.href="PageRedirectionServlet?page-name=end-match";
	      //Current Rating : 10
	    }
	  };
	  xhttp.open("POST", "EnterPlayingTeamServlet", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("playing-team="+players+"&match-id="+matchId);
}