var signupButton = document.getElementById("signup-button");
var loginButton = document.getElementById("login-button");
var closeButton = document.getElementById("close-button_signup");
var modalBoxSignup = document.getElementById("modal-box_signup");
var modalBoxLogin = document.getElementById("modal-box_login");
var closeButtonLogin = document.getElementById("close-button_login");
signupButton.onclick = function() {
    modalBoxSignup.style.display = "block";
};

window.onclick = function(event){
    if(event.target == modalBoxSignup) {
        modalBoxSignup.style.display = "none";
    }
};

closeButton.onclick = function() {
    modalBoxSignup.style.display = "none";
};

loginButton.onclick = function() {
    modalBoxLogin.style.display = "block";
};

window.onclick = function(event){
    if(event.target == modalBoxLogin) {
        modalBoxLogin.style.display = "none";
    }
};

closeButtonLogin.onclick = function() {
    modalBoxLogin.style.display = "none";
};


function checkNameSignup() {
	var name = document.getElementsByClassName("user-name")[0].value;
	//console.log(name);
	if(name == "" || !(/^[a-zA-Z]+$/.test(name))){
		document.getElementsByClassName("name-error_signup")[0].innerHTML = "Enter Valid User Name";
		return false;
	}
	else{
		document.getElementsByClassName("name-error_signup")[0].innerHTML = "";
	}
	return true;
}

function checkEmailSignup() {
	var email = document.getElementsByClassName("email")[0].value;
	//console.log(email);
	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var flag = re.test(String(email).toLowerCase());
	if(email == "" || !(flag)){
		document.getElementsByClassName("email-error_signup")[0].innerHTML = "Enter Valid Email";
		return false;
	}
	else{
		document.getElementsByClassName("email-error_signup")[0].innerHTML = "";
	}
	return true;
}

function checkPasswordSignup(){
	var password = document.forms["signup-form"]["password"].value;
	//console.log(password);
	if(password.length < 8 ){
		document.getElementsByClassName("password-error_signup")[0].innerHTML = "Enter a password of 8 charecters minimum";
		return false;
	}
	else{
		document.getElementsByClassName("password-error_signup")[0].innerHTML = "";
	}
	return true;
}


function checkPasswordMatchSignup() {
	var password = document.forms["signup-form"]["password"].value;
	var confirmPassword = document.forms["signup-form"]["confirm-password"].value;
	if(password != confirmPassword ){
		document.getElementsByClassName("match-error_signup")[0].innerHTML = "Passwords doesn't match";
		return false;
	}
	else{
		document.getElementsByClassName("match-error_signup")[0].innerHTML = "";
	}
	return true;
}

function checkNameLogin(){
	var name = document.forms["login-form"]["user-name"].value;
	//console.log(name);
	if(name == "" || !(/^[a-zA-Z]+$/.test(name))){
		document.getElementsByClassName("name-error_login")[0].innerHTML = "Enter Valid User Name";
		return false;
	}
	else{
		document.getElementsByClassName("name-error_login")[0].innerHTML = "";
	}
	return true;
}

function checkPasswordLogin(){
	var password = document.forms["login-form"]["password"].value;
	//console.log(password);
	if(password.length < 1 ){
		document.getElementsByClassName("password-error_login")[0].innerHTML = "Enter a password of 8 charecters minimum";
		return false;
	}
	else{
		document.getElementsByClassName("password-error_login")[0].innerHTML = "";
	}
	return true;
}

function validateOnSignup(){
	if(checkNameSignup() == false || checkEmailSignup() == false ||checkPasswordSignup() == false || checkPasswordMatchSignup() == false){
		return false;
	}
	return true;
}

function validateOnLogin(){
	if(checkNameLogin()== false || checkPasswordLogin() == false){
		return false;
	}
	return true;
}
