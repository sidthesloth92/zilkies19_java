function filterTournament() {
	var list = document.getElementById("dynamicadder");
	while (list.hasChildNodes()) {
		list.removeChild(list.firstChild);
	}
	console.log('inside dropdown');
	var tournamentName = document.getElementById("dropdown1").value;
	console.log(tournamentName);
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var myArr = JSON.parse(this.responseText);
			console.log(myArr);
			var i;
			var messages = "";
			if (myArr.length > 1) {
				messages += '<div class="register-quote"><h2>Edit Your changes</h2></div><div class="player-1"><div class="player-name"><h2>Enter Team name</h2></div><div class="player-name"><input type="text" placeholder="Teamname" name="teamname" value="'
						+ myArr[0]
						+ '"></div></div><div class="player-1"><div class="player-name"><h2>Enter Player Details</h2></div></div>';
				for (i = 1; i < myArr.length; i += 3) {
					var bat="batsman";
					var bowl="bowler";
					var allround="all-rounder";
					var wkbat="wk-bat";
					messages += '<div class="outer-player"><div class="player-1"><div class="player-name"><input type="text" placeholder="Firstname" name="firstname" value="'
							+ myArr[i]
							+ '"></div><div class="player-name"><input type="text" placeholder="Lastname" name="lastname" value="'
							+ myArr[i + 1]
							+ '"></div><div class="player-role">';
					if (myArr[i + 2]==bowl) {
						console.log("bowl");
						console.log(myArr[i+2]);
						messages += '<select name="role"><option value="batsman">Batsman</option><option value="bowler" selected="selected">Bowler</option><option value="all-rounder">All-Rounder</option><option value="wk-bat">Wk-Batsman</option></select>';
					} else if (myArr[i + 2]==bat) {
						console.log("allround");
						messages += '<select name="role"><option value="batsman" selected="selected">Batsman</option><option value="bowler">Bowler</option><option value="all-rounder">All-Rounder</option><option value="wk-bat">Wk-Batsman</option></select>';
					} else if (myArr[i + 2]==allround) {
						console.log("bat");
						messages += '<select name="role"><option value="batsman">Batsman</option><option value="bowler">Bowler</option><option value="all-rounder" selected="selected">All-Rounder</option><option value="wk-bat">Wk-Batsman</option></select>';
					} else if (myArr[i + 2]==wkbat) {
						console.log("wkbat");
						messages += '<select name="role"><option value="batsman">Batsman</option><option value="bowler">Bowler</option><option value="all-rounder">All-Rounder</option><option value="wk-bat" selected="selected">Wk-Batsman</option></select>';
					}
					messages += '</div></div></div>';
				}
				messages+='<div class="player-1"><div class="player-name"><button>Update</button></div></div>';
			} else {
				var msg = 'You have not registered for the tournament';
				var msg1 = 'Nothing to Update';
				messages += '<div class="team-container"><div class="team"><h3>'
						+ msg
						+ '</h3><h3 align="center">'
						+ msg1
						+ '</h3></div></div>';
			}
			document.getElementById("dynamicadder").innerHTML += messages;
		}
	};
	xhttp.open("POST",
			"http://localhost:8080/CricketTournament/EditPage?tournamentname="
					+ tournamentName + "", true);
	xhttp.send();
}