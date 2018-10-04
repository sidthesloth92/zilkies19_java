window.onload=function() {
	document.getElementById("bmi-value").classList.add("show");
	document.getElementById("bmr-value").classList.add("show");
}

function showBmiContent(contentID, x, y) {
    document.getElementById(contentID).classList.add("show");
    document.getElementById(x).classList.remove("show");
    document.getElementById(y).classList.remove("show");
}

function showBmrContent(contentID, x) {
    document.getElementById(contentID).classList.add("show");
    document.getElementById(x).classList.remove("show");
}

document.getElementsByClassName("user-icon")[0].onclick=function() {
    document.getElementsByClassName("profile-menu")[0].classList.toggle("show-menu");
}


function setTarget() {
	var planOptions = document.getElementsByName("plan");
	var chosenFlag = false;
	var plan=1;

	for (var i = 0; i < planOptions.length; i++) {
		if (planOptions[i].checked) {
			chosenFlag = true;
			plan=i+1;
		}
	}
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			if(this.responseText=='notupdated'){
				document.getElementById("error-text").innerHTML="Check your network connection and try again";
			}
		}
	};
	xhttp.open("POST", "DashboardServlet", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("target="+plan);
}

