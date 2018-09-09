function expandCard(element) {
	/*$.post("/CricAlert/PlayerModal", 
	{
		playerId: element.id
	}, 
	function(responseJson, status) {
		$(".content__modal__player-info__name").text(responseJson.firstName + " " + responseJson.lastName);
		$(".content__modal__player-info__team").text(responseJson.teamName);
	});*/
	
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
	    divPlayerTeam.innerHTML = data.teamName;
	});
	
    var modal = document.querySelector(".content__modal");

    var span = document.getElementsByClassName("close")[0];

    modal.style.display = "flex";

    span.onclick = function () {
        modal.style.display = "none";
    };

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
}	