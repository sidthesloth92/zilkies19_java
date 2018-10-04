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
	if ((document.forms["bmi-input-form"]["height-metric"].value != 0 && document.forms["bmi-input-form"]["weight-metric"].value != 0)
			|| ((document.forms["bmi-input-form"]["height-imperial-feet"].value != 0 || document.forms["bmi-input-form"]["height-imperial-inches"].value != 0) && document.forms["bmi-input-form"]["weight-imperial"].value != 0)) {
		calculateResult();
		document.getElementsByClassName("bmi-result")[0].classList
				.add("result-show");
		document.getElementsByClassName("bmi-comment")[0].classList
				.add("comment-show");
	}
}

function calculateResult() {
	var result = 0;
	if (document.getElementById("measurement-system-toggle_metric").classList
			.contains("chosen")) {
		var height = document.forms["bmi-input-form"]["height-metric"].value / 100;
		var weight = document.forms["bmi-input-form"]["weight-metric"].value;
		result = weight / (height * height);
	} else {
		var height = 0;
		if (document.forms["bmi-input-form"]["height-imperial-feet"].value != 0) {
			height = parseInt(document.forms["bmi-input-form"]["height-imperial-feet"].value * 12);
		}
		if (document.forms["bmi-input-form"]["height-imperial-inches"].value != 0) {
			height += parseInt(document.forms["bmi-input-form"]["height-imperial-inches"].value);
		}

		var weight = parseInt(document.forms["bmi-input-form"]["weight-imperial"].value);
		result = (weight / (height * height)) * 703;
	}
	result = result.toFixed(2);

	document.getElementsByClassName("bmi-value")[0]
			.getElementsByTagName("span")[1].innerHTML = result;

	var category = "";
	var comment = "";
	if (result < 18.5) {
		category = "Underweight";
		comment = "Low body weight may increase your risk for low bone mineral density (BMD) and osteoporosis, malnutrition and vitamin deficiencies. We advise you to seek medical guidance to increase weight and reach the healthy range"
	} else if (result < 25) {
		category = "Healthy";
		comment = "Congratulations. You are in the healthy range. You have low risk of obesity and heart diseases. Stay Healthy "
	} else if (result < 30) {
		category = "Overweight";
		comment = "Excess weight may increase the risk for many health problems, including type 2 diabetes, high blood pressure, heart disease and strokes, certain types of cancer etc. We advise you to seek medical guidance to lose weight and reach the healthy range"
	} else {
		category = "Obese";
		comment = "Excess weight may increase the risk for many health problems, including type 2 diabetes, high blood pressure, heart disease and strokes, certain types of cancer etc. Obesity is a serious issue and we advise you to seek medical guidance to lose weight and reach the healthy range"
	}
	comment += "</br></br><a>Sign up</a> and let us help you in your fitness journey."

	document.getElementsByClassName("bmi-status")[0]
			.getElementsByTagName("span")[1].innerHTML = category;
	document.getElementsByClassName("bmi-comment")[0].innerHTML = comment
			+ "<span>Stay Fit 'n Flair :)</span>";
}