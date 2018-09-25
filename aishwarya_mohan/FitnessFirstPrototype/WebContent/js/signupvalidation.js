function checkFirstName() {
	var firstname = document.forms["signupform"]["firstname"].value;
	if (firstname.length == 0) {
		document.getElementById("error-text").innerHTML = "Firstname must be filled";
		return false;
	}
	var regex = /^[a-zA-Z ]{2,30}$/;
	if (!regex.test(firstname)) {
		document.getElementById("error-text").innerHTML = "Invalid firstname. Special characters not allowed";
		return false;
	}
	document.getElementById("error-text").innerHTML = "";
	return true;
}

function checkLastName() {
	var lastname = document.forms["signupform"]["lastname"].value;
	if (lastname.length == 0) {
		document.getElementById("error-text").innerHTML = "Lastname must be filled";
		return false;
	}
	var regex = /^[a-zA-Z ]{2,30}$/;
	if (!regex.test(lastname)) {
		document.getElementById("error-text").innerHTML = "Invalid lastname. Special characters not allowed";
		return false;
	}
	document.getElementById("error-text").innerHTML = "";
	return true;
}

function checkPassword() {
	var password = document.forms["signupform"]["password"].value;
	if (password.length == 0) {
		document.getElementById("error-text").innerHTML = "Password must be filled";
		return false;
	}
	var regex = /^[\S]{6,}$/;
	if (!regex.test(password)) {
		document.getElementById("error-text").innerHTML = "Invalid password. No spaces are allowed. Minimum length of 6 characters";
		return false;
	}
	document.getElementById("error-text").innerHTML = "";
	return true;
}

function checkConfirmedPassword() {
	var password = document.forms["signupform"]["password"].value;
	var conpassword = document.forms["signupform"]["confirmedpassword"].value;
	if (conpassword.length == 0) {
		document.getElementById("error-text").innerHTML = "Confirm your password";
		return false;
	}
	if (!(conpassword === password)) {
		document.getElementById("error-text").innerHTML = "Wrong password";
		return false;
	}
	document.getElementById("error-text").innerHTML = "";
	return true;
}

function checkPhone() {
	var phone = document.forms["signupform"]["phone"].value;
	if (phone.length == 0) {
		document.getElementById("error-text").innerHTML = "Mobile number must be filled";
		return false;
	}
	var regex = /^[0-9]{10}$/;
	if (!regex.test(phone)) {
		document.getElementById("error-text").innerHTML = "Invalid Mobile number. Maximum of 10 characters";
		return false;
	}
	document.getElementById("error-text").innerHTML = "";
	return true;
}

function checkUserName() {
	var username = document.forms["signupform"]["username"].value;
	if (username.length == 0) {
		document.getElementById("error-text").innerHTML = "Username must be filled";
		return false;
	}
	var regex = /^[a-zA-Z0-9_]{2,30}$/;
	if (!regex.test(username)) {
		document.getElementById("error-text").innerHTML = "Invalid Username. Only alphanumeric characters and underscore are allowed";
		return false;
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText !== "false") {
				document.getElementById("error-text").innerHTML = "Username already exists";
				return false;
			} else {
				document.getElementById("error-text").innerHTML = "";
			}
		}
	};
	xhttp.open("POST", "UsernameCheckServlet", true);
	xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhttp.send("username=" + username);
	document.getElementById("error-text").innerHTML = "";
	return true;
}

function checkEmail() {
	var email = document.forms["signupform"]["email"].value;
	if (email.length == 0) {
		document.getElementById("error-text").innerHTML = "Email must be filled";
		return false;
	}
	var regex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
	if (!regex.test(String(email))) {
		document.getElementById("error-text").innerHTML = "Email ID is not valid";
		return false;
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText !== "false") {
				document.getElementById("error-text").innerHTML = "EmailID already has an account";
				console.log("emailcheck");
				return false;
			} else {
				document.getElementById("error-text").innerHTML = "";
			}
		}
	};
	xhttp.open("POST", "EmailCheckServlet", true);
	xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhttp.send("email=" + email);
	return true;
}

function submitForm() {
	//console.log(checkEmail());
	//if (checkFirstName() && checkLastName() && checkUserName()
		//	&& checkPassword() && checkConfirmedPassword() && checkEmail()) {
		var firstname = document.forms["signupform"]["firstname"].value;
		var lastname = document.forms["signupform"]["lastname"].value;
		var username = document.forms["signupform"]["username"].value;
		var password = document.forms["signupform"]["password"].value;
		var conpassword = document.forms["signupform"]["confirmedpassword"].value;
		var phone = document.forms["signupform"]["phone"].value;
		var email = document.forms["signupform"]["email"].value;

		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				console.log("response : " + this.responseText);
				if (this.responseText == "true") {
					window.location.href = "/FitnessFirstPrototype/pages/customisationpage.html";
					return true;
				} else if (this.responseText == "invalidfirstname") {
					document.getElementById("error-text").innerHTML = "Invalid firstname. Special characters not allowed";
					return false;
				} else if (this.responseText == "invalidlastname") {
					document.getElementById("error-text").innerHTML = "Invalid lastname. Special characters not allowed";
					return false;
				} else if (this.responseText == "invalidusername") {
					document.getElementById("error-text").innerHTML = "Invalid Username. Only alphanumeric characters and underscore are allowed. Must be unique";
					return false;
				} else if (this.responseText == "invalidpassword") {
					document.getElementById("error-text").innerHTML = "Invalid password. No spaces are allowed. Minimum length of 6 characters";
					return false;
				} else if (this.responseText == "wrongpassword") {
					document.getElementById("error-text").innerHTML = "Wrong password. Confirm again";
					return false;
				} else if (this.responseText == "invalidemail") {
					document.getElementById("error-text").innerHTML = "Invalid emailID. EmailID may already have an account";
					return false;
					
					
				} else if (this.responseText == "invalidphone") {
					document.getElementById("error-text").innerHTML = "Invalid Mobile number. Maximum of 10 characters";
					return false;
				} else {
					document.getElementById("error-text").innerHTML = "Something went wrong. Please try again later";
					return false;
				}
			}
		};
		xhttp.open("POST", "SignupPageServlet", true);
		xhttp.setRequestHeader('Content-type',
				'application/x-www-form-urlencoded');
		var parameters = "firstname=" + firstname + "&lastname=" + lastname
				+ "&username=" + username + "&password=" + password
				+ "&confirmedpassword=" + conpassword + "&phone=" + phone
				+ "&email=" + email;
		xhttp.send(parameters);
	//}
}
