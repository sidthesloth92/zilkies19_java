var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		var myArr = JSON.parse(this.responseText);
		var messages = "";
		for (var i = 0; i < myArr.length; i += 4) {
			messages += '<div class="content-area__request__datas"><div class="content-area__request__datas__Sno"><h2>'
					+ myArr[i]
					+ '</h2></div><div class="content-area__request__datas__Tournament-name"><h2 id='+i+'>'
					+ myArr[i + 1]
					+ '</h2></div><div class="content-area__request__datas__Tournament-format"><h2>'
					+ myArr[i + 2]
					+ '</h2></div><div class="content-area__request__datas__mobile"><h2>'
					+ myArr[i + 3]
					+ '</h2></div><div class="content-area__request__datas__status" id='+i+' onclick="accepted(this.id)"><i class="fas fa-check"></i></div><div class="content-area__request__datas__status-decline" id='+i+' onclick="rejected(this.id)"><i class="fas fa-times-circle"></i></div></div>';
		}
		document.getElementById("content_area").innerHTML += messages;
	}
};
xhttp.open("POST", "http://localhost:8080/CricketTournament/ViewRequest", true);
xhttp.send();

function accepted(clicked_id){
	var status='accepted';
	var match = document.getElementById(clicked_id).textContent;
	console.log(match);
	var list = document.getElementById("content_area");
	while (list.hasChildNodes()) {
		list.removeChild(list.firstChild);
	}
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var myArr = JSON.parse(this.responseText);
			var messages = "";
			for (var i = 0; i < myArr.length; i += 4) {
				messages += '<div class="content-area__request__datas"><div class="content-area__request__datas__Sno"><h2>'
						+ myArr[i]
						+ '</h2></div><div class="content-area__request__datas__Tournament-name"><h2 id='+i+'>'
						+ myArr[i + 1]
						+ '</h2></div><div class="content-area__request__datas__Tournament-format"><h2>'
						+ myArr[i + 2]
						+ '</h2></div><div class="content-area__request__datas__mobile"><h2>'
						+ myArr[i + 3]
						+ '</h2></div><div class="content-area__request__datas__status" id='+i+' onclick="accepted(this.id)"><i class="fas fa-check"></i></div><div class="content-area__request__datas__status-decline" id='+i+' onclick="rejected(this.id)"><i class="fas fa-times-circle"></i></div></div>';
			}
			document.getElementById("content_area").innerHTML += messages;
		}
	};
	xhttp.open("POST", "http://localhost:8080/CricketTournament/ApproveRequest?tourname="
							+ match + "&status="+status+"", true);
	xhttp.send();
}

function rejected(clicked_id){
	var status='rejected';
	console.log('rejected');
	var match = document.getElementById(clicked_id).textContent;
	console.log(match);
	var list = document.getElementById("content_area");
	while (list.hasChildNodes()) {
		list.removeChild(list.firstChild);
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var myArr = JSON.parse(this.responseText);
			var messages = "";
			for (var i = 0; i < myArr.length; i += 4) {
				messages += '<div class="content-area__request__datas"><div class="content-area__request__datas__Sno"><h2>'
						+ myArr[i]
						+ '</h2></div><div class="content-area__request__datas__Tournament-name"><h2 id='+i+'>'
						+ myArr[i + 1]
						+ '</h2></div><div class="content-area__request__datas__Tournament-format"><h2>'
						+ myArr[i + 2]
						+ '</h2></div><div class="content-area__request__datas__mobile"><h2>'
						+ myArr[i + 3]
						+ '</h2></div><div class="content-area__request__datas__status" id='+i+' onclick="accepted(this.id)"><i class="fas fa-check"></i></div><div class="content-area__request__datas__status-decline" id='+i+' onclick="rejected(this.id)"><i class="fas fa-times-circle"></i></div></div>';
			}
			document.getElementById("content_area").innerHTML += messages;
		}
	};
	xhttp.open("POST", "http://localhost:8080/CricketTournament/ApproveRequest?tourname="
							+ match + "&status="+status+"", true);
	xhttp.send();
}