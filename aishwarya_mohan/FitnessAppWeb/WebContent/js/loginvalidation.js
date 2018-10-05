document.getElementById("loginbutton").onclick = function() {
	var username = document.forms["loginform"]["username"].value;
	if (username.length == 0) {
		document.getElementById("error-text").innerHTML = "Username must be filled";
		return;
	}

	var regex = /^[a-zA-Z0-9_-]{2,30}$/;
	if (!regex.test(username)) {
		document.getElementById("error-text").innerHTML = "Invalid username";
		return;
	}

	var password = document.forms["loginform"]["password"].value;
	if (password.length == 0) {
		document.getElementById("error-text").innerHTML = "Password must be filled";
		return;
	}

	regex = /^\S{6,}$/;
	if (!regex.test(password)) {
		document.getElementById("error-text").innerHTML = "Invalid Password";
		return;
	}

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			if (this.responseText == "validuser") {
				window.location.href = "/FitnessAppWeb/DashboardServlet";
			} else if (this.responseText == "invaliduser") {
				document.getElementById("error-text").innerHTML = "Invalid username/password";
			} else if (this.responseText == "emptyusername") {
				document.getElementById("error-text").innerHTML = "Username must be filled";
			} else if (this.responseText == "emptypassword") {
				document.getElementById("error-text").innerHTML = "Password must be filled";
			} else if (this.responseText == "invalidusername") {
				document.getElementById("error-text").innerHTML = "Invalid username";
			} else if (this.responseText == "invalidpassword") {
				document.getElementById("error-text").innerHTML = "Invalid password";
			} else {
				document.getElementById("error-text").innerHTML = "Something went wrong";
			}
		}
	};
	xhttp.open("POST", "/FitnessAppWeb/LoginServlet", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("username=" + document.forms["loginform"]["username"].value
			+ "&password=" + document.forms["loginform"]["password"].value);

}
