var todayDate = new Date();
var day = todayDate.getDate();
var month = todayDate.getMonth() + 1;
var year = todayDate.getFullYear();

if (day < 10) {
	day = '0' + day.toString();
}

if (month < 10) {
	month = '0' + month.toString();
}

var minDate = year + "-" + month + "-" + day; 

var calendar = document.querySelector(".calendar");
calendar.setAttribute("min", minDate);

var modals = document.getElementsByClassName("details__match-form__modal");
var btns = document.getElementsByClassName("details__match-form__team__lineup");
var spans = document.getElementsByClassName("close");

btns[0].onclick = function() {
    modals[0].style.display = "block";
};

spans[0].onclick = function() {
    modals[0].style.display = "none";
};

btns[1].onclick = function() {
    modals[1].style.display = "block";
};

spans[1].onclick = function() {
    modals[1].style.display = "none";
};

// window.onclick = function(event) {
//     if (event.target == modal) {
//         modal.style.display = "none";
//     }
// };

function closeModal() {
	modals[0].style.display = "none";
	modals[1].style.display = "none";
}

function listTeamPlayers(element) {
	console.log("Inside listTeamPlayers");
	console.log(element.value);
	
	fetch('/CricAlertFE/TeamPlayers', {
	    method: 'post',
	    headers: {
	        'Accept': 'application/json',
	        'Content-Type': 'application/json'
	    },
	    body: JSON.stringify({teamId: element.value})
	}).then(function(response) {
	    return response.json();
	}).then(function(data) {
	    var toAppend = '<span class="close" onclick="closeModal()">&times;</span>';
	    var divToInsertInto = '';
	    if (element.getAttribute("name") == "team-a") {
    		divToInsertInto = document.querySelector(".details__match-form__modal__players-list-team-a");
    	} else if (element.getAttribute("name") == "team-b") {
    		divToInsertInto = document.querySelector(".details__match-form__modal__players-list-team-b");
    	}
	    for (var key in data) {
	    	if (element.getAttribute("name") == "team-a") {
	    		toAppend += "<div class='details__match-form__modal__players-list__player'><input type='checkbox' name='team-a-lineup' value='" + data[key].playerId + "'><span>" + data[key].firstName + " " + data[key].lastName + "</span></div>";
	    	} else if (element.getAttribute("name") == "team-b") {
	    		toAppend += "<div class='details__match-form__modal__players-list__player'><input type='checkbox' name='team-b-lineup' value='" + data[key].playerId + "'><span>" + data[key].firstName + " " + data[key].lastName + "</span></div>";
	    	}
	    }
	    console.log(toAppend);
	    divToInsertInto.innerHTML = toAppend;
	});
	
}