function updateScore() {
		console.log(matchNo);
		var match = document.getElementById(matchNo).textContent;
		console.log(match);
		var runs = document.querySelector("input[name=runs]").value;
		var overs = document.querySelector("input[name=overs]").value;
		var wickets = document.querySelector("input[name=wkts]").value;
		var teamname = document.getElementById("dropdown11").value;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
			}
		};
		xhttp.open("GET",
				"http://localhost:8080/CricketTournament/ViewScorecard?runs="
						+ runs + "&overs=" + overs + "&wickets=" + wickets
						+ "&teamname=" + teamname + "&match=" + match + "",
				true);
		xhttp.send();
	}
    
    function displayScorecard() {
        document.getElementById('schedule-1').style.opacity = '0.3';
        document.getElementById('scorecard-1').style.display = 'flex';
        document.getElementById('scorecard-1').style.left = '0px';
        document.getElementById('scorecard-1').style.top = '200px';
        document.getElementById('scorecard-1').style.zIndex = '1';
        document.getElementById('scorecard-1').style.position = 'fixed';
        document.getElementById('scorecard-1').style.width = '100%';
    }

    function displaySchedule() {
        document.getElementById('scorecard-1').style.display = 'none';
        document.getElementById('schedule-1').style.display = 'block';
        document.getElementById('schedule-1').style.opacity = '1';
    }
    
    function btnmanipulation(clicked_id) {
		console.log('inside btnmanipulation');
		console.log(clicked_id);
		matchNo = clicked_id;
		matchNo = "" + matchNo + "" + matchNo;
		console.log(matchNo);
		var match = document.getElementById(clicked_id).textContent;
		var arr = match.split('v');
		/*var match=document.querySelector("h3[id=" \\+ clicked_id + "]").value;*/
		for (i = 0; i < arr.length; i++) {
			arr[i] = arr[i].replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		}
		console.log(arr);
		var contents = '';
		document.getElementById("team1").innerHTML=arr[0];
		document.getElementById("team2").innerHTML=arr[1];
	}

	function fetchScorecard() {
		var teamOneName = document.getElementById("team1").textContent;
		var teamTwoName = document.getElementById("team2").textContent;
		var match = document.getElementById(matchNo).textContent;
		console.log(match);
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var myArr = JSON.parse(this.responseText);
				for (var i = 0; i < myArr.length; i += 6) {
					if(myArr[i+4]==null){
						myArr[i+4]=0;
					}
					if(myArr[i+1]==null){
						myArr[i+1]=0;
					}
					var scorecard=""+myArr[i]+" - "+myArr[i+2]+" ( "+myArr[i+1]+" ) ";
					var scorecardTwo=""+myArr[i+3]+" - "+myArr[i+5]+" ( "+myArr[i+4]+" ) ";
					console.log(scorecard);
					console.log(scorecardTwo);
					document.getElementById("score1").innerHTML=scorecard;
					document.getElementById("score2").innerHTML=scorecardTwo;
				}
			}
		};
		xhttp.open("POST",
				"http://localhost:8080/CricketTournament/ViewScorecard?teamname="
						+ teamOneName + "&match="+match+"&teamname2="+teamTwoName+"", true);
		xhttp.send();
	}


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
				if (myArr.length != 0) {
					messages += '<div class="team-container"><div class="team"><h3><span> MatchNo </span></h3></div><div class="view"><h3><span>Match</span></h3></div><div class="team"><h3><span>Scorecard</span></h3></div></div>';
					for (i = 0; i < myArr.length; i += 5) {
						messages += '<div class="team-container"><div class="team"><h3 id='+i+i+'>'
								+ myArr[i]
								+ '</h3></div><div class="view"><h3 id='+i+'>'
								+ myArr[i + 3]
								+ '</h3></div><div class="team"><button id='
								+ i
								+ ' onclick="displayScorecard();btnmanipulation(this.id);fetchScorecard()">Scorecard</button></div></div>';
					}
				} else {
					var msg = 'No matches have been scheduled';
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
				"http://localhost:8080/CricketTournament/ViewSchedule?tournamentname="
						+ tournamentName + "", true);
		xhttp.send();
	}
