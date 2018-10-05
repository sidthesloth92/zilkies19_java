$(document).ready(function() {
	var formElement = document.getElementById("registration-section").elements;
	var nameRegex = /[a-zA-Z]+/;
	var numberOfElement = document.getElementById("registration-section").elements.length;
	if(numberOfElement < 9){
		$(formElement[0]).on('keyup', function() {
			console.log(!nameRegex.test(formElement[0].value));
			if (!nameRegex.test(formElement[0].value)) {
				formElement[0].style.borderBottom = "2px solid red";
			} else {
				formElement[0].style.borderBottom = "2px solid green";
			}
		});
		// Last Name Validation
		$(formElement[1]).on('keyup', function() {
			console.log(!nameRegex.test(formElement[1].value));
			if (!nameRegex.test(formElement[1].value)) {
				formElement[1].style.borderBottom = "2px solid red";
			} else {
				formElement[1].style.borderBottom = "2px solid green";
			}
		});

		// Both Password Validation
		$(formElement[4]).on('keyup', function() {
			console.log(!nameRegex.test(formElement[1].value));
			if (formElement[3].value != formElement[4].value) {
				formElement[4].style.borderBottom = "2px solid red";
			} else {
				formElement[4].style.borderBottom = "2px solid green";
			}
		});

		// Location Validation
		$(formElement[5]).on('keyup', function() {
			console.log(!nameRegex.test(formElement[5].value));
			if (!nameRegex.test(formElement[5].value)) {
				formElement[5].style.borderBottom = "2px solid red";
			} else {
				formElement[5].style.borderBottom = "2px solid green";
			}
		});
	}else{
		var annualRevenue = /^[0-9]+$/;
		var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
//		Contractor Validation 
		$(formElement[0]).on('keyup', function() {
			console.log(!nameRegex.test(formElement[0].value));
			if (!nameRegex.test(formElement[0].value)) {
				formElement[0].style.borderBottom = "2px solid red";
			} else {
				formElement[0].style.borderBottom = "2px solid green";
			}
		});
		
		// Last Name Validation
		$(formElement[1]).on('keyup', function() {
			console.log(!nameRegex.test(formElement[1].value));
			if (!nameRegex.test(formElement[1].value)) {
				formElement[1].style.borderBottom = "2px solid red";
			} else {
				formElement[1].style.borderBottom = "2px solid green";
			}
		});
		
		// Both Password Validation
		$(formElement[4]).on('keyup', function() {
			console.log(!nameRegex.test(formElement[1].value));
			if (formElement[3].value != formElement[4].value) {
				formElement[4].style.borderBottom = "2px solid red";
			} else {
				formElement[4].style.borderBottom = "2px solid green";
			}
		});
		
//		Email Validation
		$(formElement[5]).on('keyup', function() {
			console.log(!emailRegex.test(formElement[5].value));
			if (!emailRegex.test(formElement[5].value)) {
				formElement[5].style.borderBottom = "2px solid red";
			} else {
				formElement[5].style.borderBottom = "2px solid green";
			}
		});
		//Company Name
		$(formElement[6]).on('keyup', function() {
			console.log(!nameRegex.test(formElement[6].value));
			if (!nameRegex.test(formElement[6].value)) {
				formElement[6].style.borderBottom = "2px solid red";
			} else {
				formElement[6].style.borderBottom = "2px solid green";
			}
		});
		$(formElement[7]).on('keyup', function() {
			if (!annualRevenue.test(formElement[7].value)) {
				formElement[7].style.borderBottom = "2px solid red";
			} else {
				formElement[7].style.borderBottom = "2px solid green";
			}
		});
		$(formElement[8]).on('keyup', function() {
			if (!annualRevenue.test(formElement[8].value)) {
				formElement[8].style.borderBottom = "2px solid red";
			} else {
				formElement[8].style.borderBottom = "2px solid green";
			}
		});
		$(formElement[9]).on('keyup', function() {
			if (!nameRegex.test(formElement[9].value)) {
				formElement[9].style.borderBottom = "2px solid red";
			} else {
				formElement[9].style.borderBottom = "2px solid green";
			}
		});
	}
	
});