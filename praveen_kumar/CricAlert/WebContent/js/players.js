function expandCard(element) {
	fetch('/CricAlert/PlayerModal', {
	    method: 'post',
	    headers: {
	        'Accept': 'application/json',
	        'Content-Type': 'application/json'
	    },
	    body: JSON.stringify({playerId: element.id})
	}).then(function(response) {
	    return response.json();
	}).then(function(data) {
	    var divPlayerName = document.querySelector(".content__modal__player-info__name");
	    divPlayerName.innerHTML = data.firstName + " " + data.lastName;
	    var divPlayerTeam = document.querySelector(".content__modal__player-info__team");
	    if (data.teamName == 'null') {
	    	divPlayerTeam.innerHTML = 'NA';
	    } else {
	    	divPlayerTeam.innerHTML = data.teamName;
	    }
	});
	
    var modal = document.querySelector(".content__modal");
    var modalPlayerInfo = document.querySelector(".content__modal__player-info");
    var span = document.getElementsByClassName("close")[0];

    modal.style.display = "flex";
    modalPlayerInfo.style.display = "block";

    span.onclick = function () {
        modal.style.display = "none";
        modalPlayerInfo.style.display = "none";
    };

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
            modalPlayerInfo.style.display = "none";
        }
    };
}

function editPlayer(ev) {
    ev.preventDefault();
    var id = ev.dataTransfer.getData("id");
    window.location = '/CricAlert/EditPlayer?id=' + id;
}

function deleteConfirmation(ev) {
    ev.preventDefault();
    var id = ev.dataTransfer.getData("id");
    
    var modal = document.querySelector(".content__modal");
    var modalDeleteConfirmation = document.querySelector(".content__modal__delete-confirmation");
    var confirmYes = document.querySelector(".content__modal__delete-confirmation__response__yes");
    var confirmNo = document.querySelector(".content__modal__delete-confirmation__response__no");
    
    modal.style.display = "flex";
    modalDeleteConfirmation.style.display = "flex";
    
    window.onclick = function (event) {
        if (event.target == modal || event.target == confirmNo || event.target == confirmYes) {
            modal.style.display = "none";
            modalDeleteConfirmation.style.display = "none";
            if (event.target == confirmYes) {
            	window.location = '/CricAlert/DeletePlayer?id=' + id;
            }
        }
    };
}