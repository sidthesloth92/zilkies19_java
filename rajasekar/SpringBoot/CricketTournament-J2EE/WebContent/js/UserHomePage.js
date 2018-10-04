window.onbeforeunload = closingCode;
function closingCode(){
	console.log('yes');
	document.location.href = '${pageContext.request.contextPath}/Logout';
   return null;
}

function displayLoginFunctionality() {
	document.getElementById('register-1').style.display = 'none';
	document.getElementById('login-1').style.display = 'flex';
	document.getElementById('login-1').style.left = '0';
	document.getElementById('login-1').style.top = '0';
	document.getElementById('login-1').style.zIndex = '1';
	document.getElementById('login-1').style.position = 'fixed';
	document.getElementById('login-1').style.width = '100%';
}

function displayRegisterFunctionality() {
	document.getElementById('home-container').style.opacity = '0.1';
	document.getElementById('register-1').style.display = 'block';
	document.getElementById('register-1').style.display = 'flex';
	document.getElementById('register-1').style.left = '0';
	document.getElementById('register-1').style.top = '0';
	document.getElementById('register-1').style.zIndex = '1';
	document.getElementById('register-1').style.position = 'fixed';
	document.getElementById('register-1').style.width = '100%';
}

function displayHome() {
	document.getElementById('register-1').style.display = 'none';
	document.getElementById('login-1').style.display = 'none';
	document.getElementById('home-container').style.display = 'block';
	document.getElementById('home-container').style.opacity = '1';
}

function validateFirstName(){
	var firstName = document.querySelector("input[name=first-name]").value;
	var alphabetRegex = /[a-zA-Z]+/;
	if (!alphabetRegex.test(firstName)) {
		var msg='Invalid Only characters are allowed';
		document.getElementById('first-name').innerHTML=msg;
	}
	else{
		var msg='';
		document.getElementById('first-name').innerHTML=msg;
	}
}

function validateLastName(){
	var lastName = document.querySelector("input[name=last-name]").value;
	var alphabetRegex = /[a-zA-Z]+/;
	if (!alphabetRegex.test(lastName)) {
		var msg='Invalid Only characters are allowed';
		document.getElementById('last-name').innerHTML=msg;
	}
	else{
		var msg='';
		document.getElementById('last-name').innerHTML=msg;
	}
}

function validateAge(){
	var age = document.querySelector("input[name=age]").value;
	var numberRegex = /[0-9]+/;
	if (!numberRegex.test(age)) {
		var msg='Invalid Only numerics are allowed';
		document.getElementById('age').innerHTML=msg;
	}
	else{
		var msg='';
		document.getElementById('age').innerHTML=msg;
	}
}

function validatePassword(){
	var password = document.querySelector("input[name=regpassword]").value;
	if(!(password.length>=6 && password.length<=12)){
		var msg='Length should be 6 to 12 charaters';
		document.getElementById('regpassword').innerHTML=msg;
    }
	else{
		var msg='';
		document.getElementById('regpassword').innerHTML=msg;
	}
}

function validateMobile(){
	var mobile = document.querySelector("input[name=mobile]").value;
	var mobileRegex = /^\d{10}$/;
	if (!mobileRegex.test(mobile)) {
		var msg='Invalid! Exactly 10 digits are allowed';
		document.getElementById('mobile').innerHTML=msg;
	}
	else{
		var msg='';
		document.getElementById('mobile').innerHTML=msg;
	}
}

function validateEmail(){
	var email = document.querySelector("input[name=regemail]").value;
	var emailregex =/\S+@\S+\.\S+/;
    if (!emailregex.test(email)) {
    	var msg='Invalid email';
		document.getElementById('regemail').innerHTML=msg;
    }
    else{
		var msg='';
		document.getElementById('regemail').innerHTML=msg;
	}
}

function validateform() {
	var firstName = document.querySelector("input[name=first-name]").value;
	var lastName = document.querySelector("input[name=last-name]").value;
	var age = document.querySelector("input[name=age]").value;
	var mobile = document.querySelector("input[name=mobile]").value;
	var email = document.querySelector("input[name=regemail]").value;
	var password = document.querySelector("input[name=regpassword]").value;
	var alphabetRegex = /[a-zA-Z]+/;
	if (!alphabetRegex.test(firstName)) {
		alert("Invalid Firstname");
		return false;
	}
	if (!alphabetRegex.test(lastName)) {
		alert("Invalid Lastname");
		return false;
	}
	
	if(!(password.length>=6 && password.length<=12)){
    	alert("Password length should be 6 to 12 characters");
    	return false;
    }
	
	var numberRegex = /[0-9]+/;
	if (!numberRegex.test(age)) {
		alert("Invalid age");
		return false;
	}
	
	var mobileRegex = /^\d{10}$/;
	if (!mobileRegex.test(mobile)) {
		alert("Invalid mobile number");
		return false;
	}
	
	var emailregex =/\S+@\S+\.\S+/;
    if (!emailregex.test(email)) {
            alert("Invalid Email");
            return false;
    }
	return true;
}

function isValidUser() {
	console.log('im in');
	$(document).ready(function () {
			$.ajax({
				url: 'http://localhost:8080/CricketTournament/LoginServlet?method=emailvalidation',
				method: 'GET',
				data: {
					email: $('#email').val(),
					method:"emailvalidation"
				},
				success: function (responseText) {
					if(responseText!="Invalid email"){
						console.log('valid');
						$('#somediv').text(responseText);
						$('input[name="password"]').prop("disabled", false);
					}
					else{
						console.log('invalid');
					$('#somediv').text(responseText);
					$('input[name="password"]').prop("disabled", true);
					}
				},
				error: function(jqxhr, status, exception) {
		             alert('Exception:', exception);
		         }
			});
	});

}

function isValidPassword() {
	console.log('im in');
	$(document).ready(function () {
			$.ajax({
				url: 'http://localhost:8080/CricketTournament/LoginServlet?method=credentialsvalidation',
				method: 'GET',
				data: {
					email: $('#email').val(),
					password: $('#password').val()
				},
				success: function (responseText) {
					if(responseText!="Invalid password"){
						console.log('valid');
						$('#somediv1').text(responseText);
						$('button[name="buttonproperty"]').prop("disabled", false);
					}
					else{
						console.log('invalid');
					$('#somediv1').text(responseText);
					$('button[name="buttonproperty"]').prop("disabled", true);
					}
				},
				error: function(jqxhr, status, exception) {
		             alert('Exception:', exception);
		         }
			});
	});

}

function isEmailTaken() {
	console.log('im in');
	$(document).ready(function () {
			$.ajax({
				url: 'http://localhost:8080/CricketTournament/LoginServlet?method=emailtaken',
				method: 'GET',
				data: {
					email: $('#regemail').val()
				},
				success: function (responseText) {
					if(responseText!="This e-mail id already taken"){
						console.log('valid');
						$('#somediv2').text(responseText);
						$('button[name="registerbutton"]').prop("disabled", false);
					}
					else{
						console.log('invalid');
					$('#somediv2').text(responseText);
					$('button[name="registerbutton"]').prop("disabled", true);
					}
				},
				error: function(jqxhr, status, exception) {
		             alert('Exception:', exception);
		         }
			});
	});

}

function getTeam(clicked_id){
	var x=document.getElementById(clicked_id).textContent;
	console.log(x);
	document.location.href = "http://localhost:8080/CricketTournament/RegisterTeam?tourname="+x+"";
}