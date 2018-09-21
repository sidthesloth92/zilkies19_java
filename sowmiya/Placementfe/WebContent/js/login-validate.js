document.getElementsByClassName("button--login")[0].onclick = function() {
	if (isEmpty() && validateName()&& validatePassword()) {
		login();
	}

}

function validateName() {
	var name = document.getElementsByName("username")[0].value;
	if (name.length > 0) {
		if (/^[a-zA-Z0-9\\_]+$/.test(name)) {
			document.getElementById("invalidName").innerHTML = "";
			return true;
		} else {
			document.getElementById("invalidName").innerHTML = "Enter a valid name without spaces";
			return false;
		}
	}
}

function validatePassword() {
	var password = document.getElementsByName("password")[0].value;
	if (!password.includes(" ")) {
		document.getElementById("invalidCredential").innerHTML = "";
		return true;
	} else {
		document.getElementById("invalidCredential").innerHTML = "Password should not contain spaces";
		return false;
	}
}

function isEmpty() {
	nodes = document.querySelectorAll("input");
	document.getElementById("empty").innerHTML = "";
	for (var i = 0; i < nodes.length; i++) {
		if (nodes[i].value.length == 0) {
			document.getElementById("empty").innerHTML = "*All feilds are mandatory";
			return false;
		}
	}
	// document.getElementById("form1").reset();
	return true;
}

function login() {
console.log("in ajax");
	var xhttp = new XMLHttpRequest();
	var username = document.getElementsByName("username")[0].value;
	var password = document.getElementsByName("password")[0].value;
	console.log(username);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == "student") {
				window.location.href = "studentProfile";
			} else if (this.responseText == "admin") {
				window.location.href = "admin";
			} else if (this.responseText == "username_error") {
				document.getElementById("invalidCredential").innerHTML = "Enter a valid name without spaces";

			} else if (this.responseText == "password_error") {
				document.getElementById("invalidCredential").innerHTML = "Password should not contain spaces";
			} else {
				document.getElementById("invalidCredential").innerHTML = "Invalid Credentials";
			}
		} else {
			console.log("error");
		}
	};
	xhttp.open("POST", "Login", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("username=" + username + "&password=" + password);
}
