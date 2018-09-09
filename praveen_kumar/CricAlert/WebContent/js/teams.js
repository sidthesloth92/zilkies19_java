function expandTeam(element) {
	fetch('/CricAlert/TeamPlayers', {
	    method: 'post',
	    headers: {
	        'Accept': 'application/json',
	        'Content-Type': 'application/json'
	    },
	    body: JSON.stringify({teamId: element.id})
	}).then(function(response) {
	    return response.json();
	}).then(function(data) {
	    console.log(data);
	    var toAppend = '';
	    for (var key in data) {
	    	console.log(data[key]);
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