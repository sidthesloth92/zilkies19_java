function expandTeam(element) {
	fetch('/CricAlertFE/TeamPlayers', {
	    method: 'post',
	    headers: {
	        'Accept': 'application/json',
	        'Content-Type': 'application/json'
	    },
	    body: JSON.stringify({teamId: element.id})
	}).then(function(response) {
	    return response.json();
	}).then(function(data) {
	    var toAppend = '';
	    for (var key in data) {
	    	var divToInsertInto = element.querySelector(".content__team-card__container-2");
	    	toAppend += '<h4>' + data[key].firstName + ' ' + data[key].lastName + '</h4><br>';
	    }
	    divToInsertInto.innerHTML = toAppend;
	});
	
    var nodeList = document.querySelectorAll(".content__team-card");

    if (element.classList.contains("expand-team")) {
        var child = element.querySelector(".content__team-card__container-2");
        child.style.display = "none";
        for (var i = 0; i < nodeList.length; i++) {
            nodeList[i].classList.remove("expand-team");
        }
    } else {
        for (i = 0; i < nodeList.length; i++) {
            nodeList[i].classList.remove("expand-team");
            child = nodeList[i].querySelector(".content__team-card__container-2");
            child.style.display = "none";
        }
        element.classList.add("expand-team");
        child = element.querySelector(".content__team-card__container-2");
        child.style.display = "block";
    }
}

function editTeam(ev) {
    ev.preventDefault();
    var id = ev.dataTransfer.getData("text");
    window.location = '/CricAlertFE/EditTeam?id=' + id;
}

function deleteConfirmation(ev) {
    ev.preventDefault();
    var id = ev.dataTransfer.getData("text");
    
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
            	window.location = '/CricAlertFE/DeleteTeam?id=' + id;
            }
        }
    };
}