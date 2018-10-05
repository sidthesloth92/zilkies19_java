function showMetric() {
	document.getElementById("measurement-system-toggle_metric").classList
			.add("chosen");
	document.getElementById("measurement-system-toggle_imperial").classList
			.remove("chosen");
	document.getElementById("metric-input").classList.add("shown");
	document.getElementById("imperial-input").classList.remove("shown");
}

function showImperial() {
	document.getElementById("measurement-system-toggle_imperial").classList
			.add("chosen");
	document.getElementById("measurement-system-toggle_metric").classList
			.remove("chosen");
	document.getElementById("imperial-input").classList.add("shown");
	document.getElementById("metric-input").classList.remove("shown");
}

function showResultBox() {
	console.log(checkInputs());
	if (checkInputs()) {
		calculateResult();
		document.getElementsByClassName("bmr-result")[0].classList
				.add("result-show");
		document.getElementsByClassName("bmr-comment")[0].classList
				.add("comment-show");
		document.getElementsByClassName("bmr-value")[0].classList
				.add("value-show");
		document.getElementsByClassName("tdee-value")[0].classList
				.add("value-show");
		document.getElementsByClassName("bmr-error-msg")[0].classList
				.remove("value-show");
	} else {
		document.getElementsByClassName("bmr-value")[0].classList
				.remove("value-show");
		document.getElementsByClassName("tdee-value")[0].classList
				.remove("value-show");
		document.getElementsByClassName("bmr-error-msg")[0].classList
				.add("value-show");
		document.getElementsByClassName("bmr-error-msg")[0].innerHTML = "Please enter all fields."
		document.getElementsByClassName("bmr-result")[0].classList
				.add("result-show");
	}
}

function checkInputs() {
	// gender
	var genderOptions = document.getElementsByName("gender");
	var chosenFlag = false;

	for (var i = 0; i < genderOptions.length; i++) {
		if (genderOptions[i].checked) {
			chosenFlag = true;
		}
	}

	if (!chosenFlag) {
		return false;
	}

	// age
	var age = document.forms["bmr-input-form"]["age"];
	if (age <= 0) {
		return false;
	}

	// height and weight
	if ((document.forms["bmr-input-form"]["height-metric"].value == 0 || document.forms["bmr-input-form"]["weight-metric"].value == 0)
			&& ((document.forms["bmr-input-form"]["height-imperial-feet"].value == 0 && document.forms["bmr-input-form"]["height-imperial-inches"].value == 0) || document.forms["bmr-input-form"]["weight-imperial"].value == 0)) {
		return false;
	}
	return true;
}

function calculateResult() {
	var bmr = 0;
	var tdee = 0;

	var gender = 0;
	var height = 0;
	var age = 0;
	var weight = 0;
	var lifestyle = document.getElementById("lifestyle").selectedIndex + 1;

	// gender
	var genderOptions = document.getElementsByName("gender");
	for (var i = 0; i < genderOptions.length; i++) {
		if (genderOptions[i].checked) {
			gender = i;
		}
	}

	// age
	age = document.forms["bmr-input-form"]["age"].value;

	// height and weight
	if (document.getElementById("measurement-system-toggle_metric").classList
			.contains("chosen")) {
		height = document.forms["bmr-input-form"]["height-metric"].value;
		weight = document.forms["bmr-input-form"]["weight-metric"].value;

	} else {
		if (document.forms["bmr-input-form"]["height-imperial-feet"].value != 0) {
			height = parseInt(document.forms["bmr-input-form"]["height-imperial-feet"].value * 12);
		}
		if (document.forms["bmr-input-form"]["height-imperial-inches"].value != 0) {
			height += parseInt(document.forms["bmr-input-form"]["height-imperial-inches"].value);
		}
		height = height * 2.54;
		weight = document.forms["bmr-input-form"]["weight-imperial"].value * 0.453592;
	}

	// bmr
	bmr = (height * 6.25) + (weight * 9.99) - (age * 4.92);

	if (gender == 0) {
		// male
		bmr += 5;
	} else {
		// female
		bmr -= 161;
	}

	// tdee
	tdee = bmr;
	switch (lifestyle) {
	case 1: // sedentary
		tdee *= 1.2;
		break;
	case 2: // light
		tdee *= 1.375;
		break;
	case 3: // moderate
		tdee *= 1.55;
		break;
	case 4: // hard
		tdee *= 1.725;
		break;
	case 5: // very hard
		tdee *= 1.9;
		break;
	}

	console.log(gender);
	console.log(age);
	console.log(height);
	console.log(weight);
	console.log(lifestyle);

	document.getElementsByClassName("bmr-value")[0]
			.getElementsByTagName("span")[1].innerHTML = bmr.toFixed(0);
	document.getElementsByClassName("tdee-value")[0]
			.getElementsByTagName("span")[1].innerHTML = tdee.toFixed(0);

	document.getElementsByClassName("bmr-comment")[0]
			.getElementsByTagName("span")[0].innerHTML = tdee.toFixed(0);
	document.getElementsByClassName("bmr-comment")[0]
			.getElementsByTagName("span")[1].innerHTML = tdee.toFixed(0) - 500;
	document.getElementsByClassName("bmr-comment")[0]
			.getElementsByTagName("span")[2].innerHTML = tdee.toFixed(0) - 1000;
	document.getElementsByClassName("bmr-comment")[0]
			.getElementsByTagName("span")[3].innerHTML = parseInt(tdee
			.toFixed(0)) + 500;
	document.getElementsByClassName("bmr-comment")[0]
			.getElementsByTagName("span")[4].innerHTML = parseInt(tdee
			.toFixed(0)) + 1000;

	document.getElementsByClassName("bmr-comment")[0].innerHTML += "</br></br><a>Sign up</a> and let us help you in your fitness journey.<span>Stay Fit 'n Flair :)</span>";

}

function removeResult() {
	document.getElementsByClassName("bmr-result")[0].classList
			.remove("result-show");
	document.getElementsByClassName("bmr-comment")[0].classList
			.remove("comment-show");
}