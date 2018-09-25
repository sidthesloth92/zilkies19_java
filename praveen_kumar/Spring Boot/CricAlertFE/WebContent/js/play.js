var modal = document.querySelector(".modal");
var modalToss = document.querySelector(".modal__toss");
var modalChoosePlayers = document.querySelector(".modal__choose-players");
var modalNextBowler = document.querySelector(".modal__next-bowler");
var modalNextBatsman = document.querySelector(".modal__next-batsman");
var modalInningsEnd = document.querySelector(".modal__innings-end");
var modalMatchEnd = document.querySelector(".modal__match-end");
var openingForm = document.forms["opening-players"];
var bowlerForm = document.forms["next-bowler"];
var batsmanForm = document.forms["next-batsman"];
var extra;
var pauseFlag = false;
var scorecardFlag = false;

var batsmenTable = document.querySelector(".overview__batsmen");
var rowA = batsmenTable.insertRow(1);
var nameCellA = rowA.insertCell(0);
var runsCellA = rowA.insertCell(1);
var ballsCellA = rowA.insertCell(2);
var foursCellA = rowA.insertCell(3);
var sixesCellA = rowA.insertCell(4);
var srCellA = rowA.insertCell(5);
var rowB = batsmenTable.insertRow(2);
var nameCellB = rowB.insertCell(0);
var runsCellB = rowB.insertCell(1);
var ballsCellB = rowB.insertCell(2);
var foursCellB = rowB.insertCell(3);
var sixesCellB = rowB.insertCell(4);
var srCellB = rowB.insertCell(5);
var bowlerTable = document.querySelector(".overview__bowler");
var row = bowlerTable.insertRow(1);
var nameCell = row.insertCell(0);
var oversCell = row.insertCell(1);
var maidenCell = row.insertCell(2);
var runsCell = row.insertCell(3);
var wicketsCell = row.insertCell(4);
var erCell = row.insertCell(5);

var teamA = document.querySelector(".modal__toss__container__team-A");
var teamB = document.querySelector(".modal__toss__container__team-B");
var bat = document.querySelector(".modal__toss__container__bat");
var bowl = document.querySelector(".modal__toss__container__bowl");
var scorecardOvers = document.querySelector(".scorecard__container-2__overs");
var scorecardTarget = document.querySelector(".scorecard__container-2__target");
var scorecardRunrate = document.querySelector(".scorecard__container-3__run-rate");
var scorecardRequired = document.querySelector(".scorecard__container-3__required");
var scorecardTeamA = document.querySelector(".scorecard__container-1__team-a");
var scorecardTeamB = document.querySelector(".scorecard__container-1__team-b");
var scorecardScore = document.querySelector(".scorecard__container-1__score");
var balls = document.querySelector(".balls");

var url_string = window.location.href;
var url = new URL(url_string);
var match;
var matchId = url.searchParams.get("id");
var matchStats = {
	matchId: matchId,
	battingTeam: "",
	bowlingTeam: "",
	teamAscore: 0,
	teamBscore: 0,
	teamAwickets: 0,
	teamBwickets: 0,
	overs: 0,
	onstrike: "",
	offstrike: "",
	bowler: "",
	inning: 0
};
var playerStatsMap = {};

fetchMatch();

function fetchMatch() {
	if (url.searchParams.get("type") == "live") {
    	modalToss.style.display = "none";
    	modal.style.display = "none";
    }
	fetch('/CricAlertFE/Play?id='+matchId, {
	    method: 'get',
	    headers: {
	        'Accept': 'application/json',
	        'Content-Type': 'application/json'
	    }
	}).then(function(response) {
	    return response.json();
	}).then(function(json) {
	    match = json;
	    if (url.searchParams.get("type") == "live") {
	    	fetchMatchStats();
	    } else {
	    	teamA.innerHTML = match.teamA.abbreviation;
		    teamB.innerHTML = match.teamB.abbreviation;
	    }
	}).catch(function(err) {
		console.log(err);
	});
}

function fetchMatchStats() {
	fetch('/CricAlertFE/FetchMatchStats?id='+matchId, {
	    method: 'get',
	    headers: {
	        'Accept': 'application/json',
	        'Content-Type': 'application/json'
	    }
	}).then(function(response) {
	    return response.json();
	}).then(function(json) {
		console.log("Received response from FetchMatchStats");
	    matchStats = json;
	    fetchPlayerStats();
	}).catch(function(err) {
		console.log(err);
	});
}

function fetchPlayerStats() {
	fetch('/CricAlertFE/FetchPlayerStats?id='+matchId, {
	    method: 'get',
	    headers: {
	        'Accept': 'application/json',
	        'Content-Type': 'application/json'
	    }
	}).then(function(response) {
	    return response.json();
	}).then(function(playerStatsMapJson) {
	    playerStatsMap = playerStatsMapJson;
	    console.log("Received response from FetchPlayerStats");
	    initializeScorecard();
	    updateTable();
	}).catch(function(err) {
		console.log(err);
	});
}

function selectTeam(element) {
    element.classList.add("user-selection");
    if (element.classList.contains("modal__toss__container__team-A")) {
        teamB.classList.remove("user-selection");
    } else {
        teamA.classList.remove("user-selection");
    }
}

function selectChoice(element) {
    element.classList.add("user-selection");
    if (element.classList.contains("modal__toss__container__bat")) {
        bowl.classList.remove("user-selection");
    } else {
        bat.classList.remove("user-selection");
    }
}

function confirmChoice() {
	if (!((teamA.classList.contains("user-selection") || teamB.classList.contains("user-selection")) && (bat.classList.contains("user-selection") || bowl.classList.contains("user-selection")))) {
		return;
	}
	
	if ((teamA.classList.contains("user-selection") && bat.classList.contains("user-selection")) || (teamB.classList.contains("user-selection") && bowl.classList.contains("user-selection"))) {
    	matchStats.battingTeam = match.teamA;
    	matchStats.bowlingTeam = match.teamB;
    } else if ((teamA.classList.contains("user-selection") && bowl.classList.contains("user-selection")) || (teamB.classList.contains("user-selection") && bat.classList.contains("user-selection"))) {
    	matchStats.battingTeam = match.teamB;
    	matchStats.bowlingTeam = match.teamA;
    }
	modalToss.style.display = "none";
    chooseOpeningPlayers();
}

function chooseOpeningPlayers() {
	modalInningsEnd.style.display = "none";
	modalChoosePlayers.style.display = "block";
    var onstrike = openingForm.querySelector('select[name="on-strike"]');
    var offstrike = openingForm.querySelector('select[name="off-strike"]');
    var bowler = openingForm.querySelector('select[name="bowler"]');
    
    var toAppend;
    for (var key in matchStats.battingTeam.players) {
    	var player = matchStats.battingTeam.players[key];
    	toAppend += '<option value="' + player.playerId + '">' + player.firstName + ' ' + player.lastName + '</option>'; 
    }
    onstrike.innerHTML = toAppend;
    offstrike.innerHTML = toAppend;
    toAppend = '';
    for (var key in matchStats.bowlingTeam.players) {
    	var player = matchStats.bowlingTeam.players[key];
    	toAppend += '<option value="' + player.playerId + '">' + player.firstName + ' ' + player.lastName + '</option>'; 
    }
    bowler.innerHTML = toAppend;
}

function confirmPlayers() {
	modalChoosePlayers.style.display = "none";
	var onstrike = openingForm["on-strike"].value;
	var offstrike = openingForm["off-strike"].value;
	if (onstrike == offstrike) {
		alert("You have chosen the same batsman for onstrike and offstrike! Choose different batsmen");
		chooseOpeningPlayers();
		return;
	}
	modal.style.display = "none";
	var bowler = openingForm["bowler"].value;
	setPlayerStats(matchStats.battingTeam.players);
	setPlayerStats(matchStats.bowlingTeam.players);
	setOnstrike(onstrike);
	setOffstrike(offstrike);
	setBowler(bowler);
	updateTable();
	initializeScorecard();
	match.status = 'ongoing';
	console.log("written match status: " + match.status);
	writeMatch();
}

function setOnstrike(playerId) {
	for (var key in matchStats.battingTeam.players) {
		var player = matchStats.battingTeam.players[key];
		if (player.playerId == playerId) {
			matchStats.onstrike = player;
			playerStatsMap[matchStats.onstrike.playerId].batFlag = true;
		}
	}
}

function setOffstrike(playerId) {
	for (var key in matchStats.battingTeam.players) {
		var player = matchStats.battingTeam.players[key];
		if (player.playerId == playerId) {
			matchStats.offstrike = player;
			playerStatsMap[matchStats.offstrike.playerId].batFlag = "true";
		}
	}
}

function setBowler(playerId) {
	for (var key in matchStats.bowlingTeam.players) {
		var player = matchStats.bowlingTeam.players[key];
		if (player.playerId == playerId) {
			matchStats.bowler = player;
			playerStatsMap[matchStats.bowler.playerId].bowlFlag = "true";
		}
	}
}

function setPlayerStats(players) {
	for (var key in players) {
		var player = players[key];
		playerStatsMap[player.playerId] = {
				matchId: match.matchId,
				playerId: player.playerId,
				runsScored: 0,
				teamId: player.teamId,
				ballsFaced: 0,
				wicketsTaken: 0,
				runsGiven: 0,
				overs: 0.0,
				batFlag: false,
				bowlFlag: false,
				fours: 0,
				sixes: 0,
				economy: 0.0,
				strikeRate: 0.0
		};
	}
}

function initializeScorecard() {
	scorecardTeamA.innerHTML = match.teamA.abbreviation;
	scorecardTeamB.innerHTML = match.teamB.abbreviation;
	updateScorecard();
}

function addBall(event) {
	
	var ball = event.target;
	var ballRuns;
	if (ball.classList.contains("input__runs__dot")) {
		ballRuns = 0;
	} else if (ball.classList.contains("input__runs__one")) {
		ballRuns = 1;
	} else if (ball.classList.contains("input__runs__two")) {
		ballRuns = 2;
	} else if (ball.classList.contains("input__runs__three")) {
		ballRuns = 3;
	} else if (ball.classList.contains("input__runs__four")) {
		ballRuns = 4;
	} else if (ball.classList.contains("input__runs__five")) {
		ballRuns = 5;
	} else if (ball.classList.contains("input__runs__six")) {
		ballRuns = 6;
	}
	
	var additionalRuns = 0;
	if (extra != null) {
		if (extra.classList.contains("input__extra__byes")) {
			additionalRuns = ballRuns;
			ballRuns = 0;
			updatePlayerStats(0, 'byes', additionalRuns);
			balls.innerHTML += "<div class='balls__played'>" + additionalRuns + "B</div>";
			updateMatchStats(ballRuns + additionalRuns, 'byes');
		} else if (extra.classList.contains("input__extra__leg-byes")) {
			additionalRuns = ballRuns;
			ballRuns = 0;
			updatePlayerStats(0, 'byes', additionalRuns);
			balls.innerHTML += "<div class='balls__played'>" + additionalRuns + "LB</div>";
			updateMatchStats(ballRuns + additionalRuns, 'byes');
		} else if (extra.classList.contains("input__extra__no-ball")) {
			additionalRuns = 1;
			updatePlayerStats(ballRuns, 'noball', additionalRuns);
			balls.innerHTML += "<div class='balls__played'>" + ballRuns + "NB</div>";
			updateMatchStats(ballRuns + additionalRuns, 'noball');
		} else if (extra.classList.contains("input__extra__wide")) {
			additionalRuns = 1;
			updatePlayerStats(ballRuns, 'wide', additionalRuns);
			balls.innerHTML += "<div class='balls__played'>" + ballRuns + "W</div>";
			updateMatchStats(ballRuns + additionalRuns, 'wide');
		} else if (extra.classList.contains("input__extra__penalty")) {
			additionalRuns = ballRuns;
			ballRuns = 0;
			updatePlayerStats(0, 'penalty', additionalRuns);
			balls.innerHTML += "<div class='balls__played'>" + additionalRuns + "P</div>";
			updateMatchStats(ballRuns + additionalRuns, 'penalty');
		}
		extra.classList.remove("user-selection");
		extra = null;
	} else {
		balls.innerHTML += "<div class='balls__played'>" + ballRuns + "</div>";
		updatePlayerStats(ballRuns, 'regular', 0);
		updateMatchStats(ballRuns + additionalRuns, 'regular');
	}
	
	updateScorecard();
	updateTable();
}

function addExtra(event) {
	extra = event.target;
	extra.classList.toggle("user-selection");
	if (!extra.classList.contains("user-selection")) {
		extra = null;
	}
}

function updatePlayerStats(ballRuns, type, additionalRuns) {
	// Update batsman stats
	if (type == 'regular' || type == 'noball') {
		playerStatsMap[matchStats.onstrike.playerId].runsScored += ballRuns;
		if (ballRuns == 4) {
			playerStatsMap[matchStats.onstrike.playerId].fours++;
		} else if (ballRuns == 6) {
			playerStatsMap[matchStats.onstrike.playerId].sixes++;
		}
	} else if (type == 'wide') {
		playerStatsMap[matchStats.onstrike.playerId].runsScored += ballRuns;
	}
	playerStatsMap[matchStats.onstrike.playerId].ballsFaced++;
	playerStatsMap[matchStats.onstrike.playerId].strikeRate = (playerStatsMap[matchStats.onstrike.playerId].runsScored / playerStatsMap[matchStats.onstrike.playerId].ballsFaced) * 100;
	
	// Update bowler stats
	if (type == 'regular' || type == 'wide' || type == 'noball') {
		playerStatsMap[matchStats.bowler.playerId].runsGiven += ballRuns;
		playerStatsMap[matchStats.bowler.playerId].runsGiven += additionalRuns;
		playerStatsMap[matchStats.bowler.playerId].overs += 0.1;
	}
	
	playerStatsMap[matchStats.bowler.playerId].economy = playerStatsMap[matchStats.bowler.playerId].runsGiven / playerStatsMap[matchStats.bowler.playerId].overs;
	
	// Switch batsmen
	if (ballRuns % 2 != 0) {
		var tempPlayer = matchStats.onstrike;
		matchStats.onstrike = matchStats.offstrike;
		matchStats.offstrike = tempPlayer;
	}
}

function updateMatchStats(totalRuns, type) {
	if (type != 'noball' && type != 'wide') {
		matchStats.overs += 0.1;
	}
	
	// Checking if over is complete, if it is, then round off but don't call updateBowler()
	if (parseInt(matchStats.overs * 10) % 10 == 6) {
		matchStats.overs += 0.4;
	}
	
	if (match.teamA.teamId == matchStats.battingTeam.teamId) {
		matchStats.teamAscore += totalRuns;
		if ((matchStats.teamAscore > matchStats.teamBscore && matchStats.inning == 1) || parseInt(matchStats.overs) == 20) {
			endInnings();
		} else {
			// Calling updateBowler() after checking whether inning is over, to ensure two modals aren't displayed
			if (parseInt(matchStats.overs * 10) % 10 == 0) {
				updateBowler();
			}
		}
	} else {
		matchStats.teamBscore += totalRuns;
		if ((matchStats.teamBscore > matchStats.teamAscore && matchStats.inning == 1) || parseInt(matchStats.overs) == 20) {
			endInnings();
		} else {
			// Calling updateBowler() after checking whether inning is over, to ensure two modals aren't displayed
			if (parseInt(matchStats.overs * 10) % 10 == 0) {
				updateBowler();
			}
		}
	}
}

function updateScorecard() {
	scorecardOvers.innerHTML = "Overs: " + matchStats.overs.toFixed(1) + " / 20";
	if (matchStats.overs == 0) {
		scorecardRunrate.innerHTML = "RR: 0.0";
	}
	if (match.teamA.teamId == matchStats.battingTeam.teamId) {
		scorecardTeamA.classList.add("scorecard__container-1__team--active");
		scorecardTeamB.classList.remove("scorecard__container-1__team--active");
		scorecardTarget.innerHTML = "Target: " + matchStats.teamBscore;
		scorecardScore.innerHTML = matchStats.teamAscore + " - "  + matchStats.teamAwickets;
		if (matchStats.overs != 0) {
			scorecardRunrate.innerHTML = "RR: " + (matchStats.teamAscore / matchStats.overs).toFixed(1);
		}
		if (matchStats.teamBscore != 0) {
			scorecardRequired.innerHTML = "RQ: " + ((matchStats.teamBscore - matchStats.teamAscore) / (20 - matchStats.overs)).toFixed(1);
		}
	} else {
		scorecardTeamA.classList.remove("scorecard__container-1__team--active");
		scorecardTeamB.classList.add("scorecard__container-1__team--active");
		scorecardTarget.innerHTML = "Target: " + matchStats.teamAscore;
		scorecardScore.innerHTML = matchStats.teamBscore + " - "  + matchStats.teamBwickets;
		if (matchStats.overs != 0){
			scorecardRunrate.innerHTML = "RR: " + (matchStats.teamBscore / matchStats.overs).toFixed(1);
		}
		if (matchStats.teamAscore != 0) {
			scorecardRequired.innerHTML = "RQ: " + ((matchStats.teamAscore - matchStats.teamBscore) / (20 - matchStats.overs)).toFixed(1);
		}
	}
}

function updateTable() {
	document.querySelector(".header__title-bar__title").innerHTML = match.teamA.abbreviation + ' vs ' + match.teamB.abbreviation;
	nameCellA.innerHTML = matchStats.onstrike.lastName;
	nameCellB.innerHTML = matchStats.offstrike.lastName;
	runsCellA.innerHTML = playerStatsMap[matchStats.onstrike.playerId].runsScored;
	runsCellB.innerHTML = playerStatsMap[matchStats.offstrike.playerId].runsScored;
	ballsCellA.innerHTML = playerStatsMap[matchStats.onstrike.playerId].ballsFaced;
	ballsCellB.innerHTML = playerStatsMap[matchStats.offstrike.playerId].ballsFaced;
	foursCellA.innerHTML = playerStatsMap[matchStats.onstrike.playerId].fours;
	foursCellB.innerHTML = playerStatsMap[matchStats.offstrike.playerId].fours;
	sixesCellA.innerHTML = playerStatsMap[matchStats.onstrike.playerId].sixes;
	sixesCellB.innerHTML = playerStatsMap[matchStats.offstrike.playerId].sixes;
	srCellA.innerHTML = playerStatsMap[matchStats.onstrike.playerId].strikeRate.toFixed(1);
	srCellB.innerHTML = playerStatsMap[matchStats.offstrike.playerId].strikeRate.toFixed(1);

	nameCell.innerHTML = matchStats.bowler.lastName;
	oversCell.innerHTML = playerStatsMap[matchStats.bowler.playerId].overs.toFixed(1);
	maidenCell.innerHTML = "0";
	runsCell.innerHTML = playerStatsMap[matchStats.bowler.playerId].runsGiven;
	wicketsCell.innerHTML = playerStatsMap[matchStats.bowler.playerId].wicketsTaken;
	erCell.innerHTML = playerStatsMap[matchStats.bowler.playerId].economy.toFixed(1);
}

function updateBowler() {
	modal.style.display = "flex";
	modalNextBowler.style.display = "flex";
	var bowler = bowlerForm.querySelector('select[name="bowler"]');
	var toAppend;
	for (var key in matchStats.bowlingTeam.players) {
    	var player = matchStats.bowlingTeam.players[key];
    	if (matchStats.bowler.playerId != player.playerId) {
    		toAppend += '<option value="' + player.playerId + '">' + player.firstName + ' ' + player.lastName + '</option>';
    	}
    }
    bowler.innerHTML = toAppend;
    var tempPlayer = matchStats.onstrike;
	matchStats.onstrike = matchStats.offstrike;
	matchStats.offstrike = tempPlayer;
}

function nextBowler() {
	var bowler = bowlerForm["bowler"].value;
	setBowler(bowler);
	modal.style.display = "none";
	modalNextBowler.style.display = "none";
	updateTable();
}

function updateBatsman() {
	var toAppend;
	var availableBatsmen = 0;
	for (var key in matchStats.battingTeam.players) {
    	var player = matchStats.battingTeam.players[key];
    	if (playerStatsMap[player.playerId].batFlag == false) {
    		availableBatsmen++;
    		toAppend += '<option value="' + player.playerId + '">' + player.firstName + ' ' + player.lastName + '</option>';
    	}
    }
	if (availableBatsmen == 0) {
		endInnings();
	} else {
		modal.style.display = "flex";
		modalNextBatsman.style.display = "flex";
		var batsman = batsmanForm.querySelector('select[name="batsman"]');
		batsman.innerHTML = toAppend;
	}
}

function nextBatsman() {
	var batsman = batsmanForm["batsman"].value;
	setOnstrike(batsman);
	modal.style.display = "none";
	modalNextBatsman.style.display = "none";
	var balls = document.querySelector(".balls");
	if ( parseInt(matchStats.overs * 10) % 10 == 6) {
		updateBowler();
	}
	updateTable();
}

function wicketTaken() {
	balls.innerHTML += "<div class='balls__played'>WK</div>";
	if (match.teamA.teamId == matchStats.battingTeam.teamId) {
		matchStats.teamAwickets++;
	} else {
		matchStats.teamBwickets++;
	}
	playerStatsMap[matchStats.bowler.playerId].wicketsTaken++;
	updatePlayerStats(0, 'regular', 0);
	updateMatchStats(0, 'regular');
	updateScorecard();
	updateTable();
	updateBatsman();
	// check if overs are over!
}

function endInnings() {
	document.querySelector(".modal__innings-end__team").innerHTML = matchStats.battingTeam.teamName;
	document.querySelector(".modal__innings-end__overs").innerHTML = matchStats.overs.toFixed(1);
	if (match.teamA.teamId == matchStats.battingTeam.teamId) {
		document.querySelector(".modal__innings-end__score").innerHTML = matchStats.teamAscore + ' - ' + matchStats.teamAwickets;
	} else {
		document.querySelector(".modal__innings-end__score").innerHTML = matchStats.teamBscore + ' - ' + matchStats.teamBwickets;
	}
	
	modal.style.display = "flex";
	modalInningsEnd.style.display = "flex";
	if (matchStats.inning == 0) {
		var tempTeam = matchStats.battingTeam;
		matchStats.battingTeam = matchStats.bowlingTeam;
		matchStats.bowlingTeam = tempTeam;
		matchStats.overs = 0;
		matchStats.inning = 1;
	} else {
		document.querySelector(".modal__innings-end__continue").onclick = displayResult;
	}
}

function displayResult() {
	if (matchStats.teamAscore > matchStats.teamBscore) {
		match.matchResult = 'WIN';
		document.querySelector(".modal__match-end__team").innerHTML = match.teamA.teamName;
		var wicketsLeft = match.teamA.players.length - matchStats.teamAwickets;
		document.querySelector(".modal__match-end__wickets").innerHTML = wicketsLeft + ' wicket(s)';
	} else if (matchStats.teamAscore < matchStats.teamBscore) {
		match.matchResult = 'LOSE';
		document.querySelector(".modal__match-end__team").innerHTML = match.teamB.teamName;
		var wicketsLeft = match.teamB.players.length - matchStats.teamBwickets;
		document.querySelector(".modal__match-end__wickets").innerHTML = wicketsLeft + ' wicket(s)';
	} else {
		match.matchResult = 'DRAW';
		document.querySelector(".modal__match-end__team").innerHTML = match.teamB.teamName;
		var wicketsLeft = match.teamB.players.length - matchStats.teamBwickets;
		document.querySelector(".modal__match-end__wickets").innerHTML = wicketsLeft + ' wicket(s)';
	}
	document.querySelector(".modal__match-end__result").innerHTML = match.matchResult + ' the match by';
	modalInningsEnd.style.display = "none";
	modal.display = "flex";
	modalMatchEnd.style.display = "flex"; 
}

function endMatch() {
	match.status = 'completed';
	modal.style.display = "none";
	modalMatchEnd.style.display = "none";
	writeMatch();
	writeMatchStats();
}

function writeMatch() {
	fetch('/CricAlertFE/WriteMatch', {
		method: 'post',
		body: JSON.stringify(match)
	}).then(function (response) {
		console.log("Response received from WriteMatch");
	}).catch(function(err) {
		console.log(err);
	});
}

function writeMatchStats() {
	fetch('/CricAlertFE/WriteMatchStats', {
		method: 'post',
		body: JSON.stringify(matchStats)
	}).then(function (response) {
		console.log("Response received from WriteMatchStats");
		writePlayerStats();
	}).catch(function(err) {
		console.log(err);
	});
}

function writePlayerStats() {
	fetch('/CricAlertFE/WritePlayerStats', {
		method: 'post',
		body: JSON.stringify(playerStatsMap)
	}).then(function (response) {
		console.log("Response received from WritePlayerStats");
		if (pauseFlag == true) {
			window.location = '/CricAlertFE/Home';
			pauseFlag = false;
		} else if (scorecardFlag == true) {
			window.location = '/CricAlertFE/Scorecard?id=' + match.matchId;
			scorecardFlag = false;
		}
	}).catch(function(err) {
		console.log(err);
	});
}

function pauseMatch() {
	pauseFlag = true;
	writeMatchStats();
//	writeBallStats();
}

function openScorecard() {
	scorecardFlag = true;
	writeMatchStats();
}