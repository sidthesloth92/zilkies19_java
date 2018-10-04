function validateSignupForm() {

	return validateFirstName() && validateLastName() && validateUserName()
			&& validateEmailId() && validatePassword()
			&& validateConfirmPassword();
}

function validateFirstName() {
	var firstName = document.forms["signup-form"]["firstname"];
	var nameRegex = /[a-zA-Z]+/;
	var firstnameError = document.getElementById("firstname-error");
	if (!nameRegex.test(firstName.value)) {
		firstnameError.style.display = "block";
		firstnameError.innerHTML = "Invalid First Name";
		return false;
	} else {
		firstnameError.style.display = "none";
	}
	return true;
}

function validateLastName() {
	var lastName = document.forms["signup-form"]["lastname"];
	var nameRegex = /[a-zA-Z]+/;
	var lastnameError = document.getElementById("lastname-error");
	if (!nameRegex.test(lastName.value)) {
		lastnameError.style.display = "block";
		lastnameError.innerHTML = "Invalid Last Name";
		return false;
	} else {
		lastnameError.style.display = "none";
	}
	return true;
}

function validateUsername() {
	var username = document.forms["signup-form"]["username"];
	var userNameRegex = /^[a-zA-Z0-9._-]{3,}$/;
	var usernameError = document.getElementById("username-error");
	if (!userNameRegex.test(username.value)) {
		usernameError.style.display = "block";
		usernameError.innerHTML = "Invalid Username";
		return false;
	} else {
		usernameError.style.display = "none";
	}
	return true;
}

function validateEmailId() {
	var emailId = document.forms["signup-form"]["email-id"];
	var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var emailIdError = document.getElementById("email-id-error");
	if (!emailRegex.test(emailId.value)) {
		emailIdError.style.display = "block";
		emailIdError.innerHTML = "Invalid Email ID";
		return false;
	} else {
		emailIdError.style.display = "none";
	}
	return true;
}

function validatePassword() {
	var password = document.forms["signup-form"]["password"];
	var passwordRegex = /((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})/;
	var passwordError = document.getElementById("password-error");
	if (!passwordRegex.test(password.value)) {
		passwordError.style.display = "block";
		passwordError.innerHTML = "Invalid Password";
		return false;
	} else {
		passwordError.style.display = "none";
	}
	return true;
}

function validateConfirmPassword() {
	var confirmPassword = document.forms["signup-form"]["confirm-password"];
	var password = document.forms["signup-form"]["password"];
	var confirmPasswordError = document
			.getElementById("confirm-password-error");
	if (password.value != confirmPassword.value) {
		confirmPasswordError.style.display = "block";
		confirmPasswordError.innerHTML = "Passwords did not match";
		return false;
	} else {
		confirmPasswordError.style.display = "none";
	}
	return true;
}
