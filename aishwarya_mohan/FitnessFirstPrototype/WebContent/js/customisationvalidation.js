function checkInputs() {
	// gender
	var genderOptions = document.getElementsByName("gender");
	var chosenFlag = false;
	var gender=2;

	for (var i = 0; i < genderOptions.length; i++) {
		if (genderOptions[i].checked) {
			chosenFlag = true;
			gender=i+1;
		}
	}
	
	console.log(gender);

	
	
	if (!chosenFlag) {
		document.getElementById("error-text-box").innerHTML = "Choose your gender";
		return;
	}else{
		document.getElementById("error-text-box").innerHTML = "";
	}

	// age
	var age = document.forms["customisationform"]["age"].value;
	if (age < 10 || age > 150) {
		document.getElementById("error-text-box").innerHTML = "Input an appropriate age (5-150) for valid results";
		return;
	}else{
		document.getElementById("error-text-box").innerHTML = "";
	}

	// height cms
	var height = document.forms["customisationform"]["height"].value;
	if (height < 10) {
		document.getElementById("error-text-box").innerHTML = "Input an appropriate height (>10) for valid results";
		return;
	}else{
		document.getElementById("error-text-box").innerHTML = "";
	}

	// weight
	var weight = document.forms["customisationform"]["weight"].value;
	if (weight < 5) {
		document.getElementById("error-text-box").innerHTML = "Input an appropriate weight (>5) for valid results";
		return;
	}else{
		document.getElementById("error-text-box").innerHTML = "";
	}

	//lifestyle
	var lifestyle=document.getElementById("lifestyle").selectedIndex+1;
	//console.log(gender+height+weight+lifestyle);

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			if (this.responseText == "true") {
				window.location.href = "../DashboardServlet";
				return true;
			} else if (this.responseText == "invalidage") {
				document.getElementById("error-text-box").innerHTML = "Input an appropriate age (5-150) for valid results";
				return false;
			} else if (this.responseText == "invalidheight") {
				document.getElementById("error-text-box").innerHTML = "Input an appropriate height (>10) for valid results";
				return false;
			} else if (this.responseText == "invalidweight") {
				document.getElementById("error-text-box").innerHTML = "Input an appropriate weight (>10) for valid results";
				return false;
			} else if (this.responseText == "loggedout") {
				document.getElementById("error-text-box").innerHTML = "You have been logged out. Login and try again";
				return false;
			} else {
				document.getElementById("error-text-box").innerHTML = "Something went wrong. Try again later";
				return false;
			}
		}
	};
	xhttp.open("POST", "../CustomisationServlet", true);
	xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	var parameters = "gender=" + gender + "&age=" + age + "&height=" + height + "&weight=" + weight + "&lifestyle=" + lifestyle;
	console.log(parameters);
	xhttp.send(parameters);
	return true;
}