function showModal() {
	document.getElementById("modal").style.display = "flex";
	document.getElementsByClassName("details-table")[0].style.display = "none";
	document.getElementsByClassName("body__left-panel")[0].style.display = "none";
	document.getElementsByClassName("body__content")[0].style.backgroundColor = "#d0f0fe";
	document.getElementsByClassName("button--add")[0].style.display = "none";

}
function hideModal(id) {
	document.getElementById(id).style.display = "none";
	document.getElementsByClassName("details-table")[0].style.display = "flex";
	window.location.reload();

}
document.getElementsByClassName("button--save")[0].onclick = function() {
	if (validateRegisterNumber('register-form', 0)
			&& validateStudentName('register-form', 1)
			&& validatePercentage('hsc', 2, 'register-form')
			&& validatePercentage('sslc', 3, 'register-form')
			&& validateCgpa('register-form', 4)
			&& validateArrearCount('register-form', 5)) {
		register();
		return true;
	}

}

function validateRegisterNumber(form_name, index) {
	var number = document.forms[form_name]["register-number"].value;
	if (/^[0-9]+$/.test(number)) {
		document.getElementsByClassName("errormsg")[index].innerHTML = "";
		return true;
	} else {
		document.getElementsByClassName("errormsg")[index].innerHTML = "Enter a valid Register Number";
		return false;
	}

}

function validateStudentName(form_name, index) {
	var name = document.forms[form_name]["student-name"].value;
	console.log(name);
	if (/^[a-zA-Z0-9 \\_]+$/.test(name)) {
		document.getElementsByClassName("errormsg")[index].innerHTML = "";

		return true;
	} else {
		document.getElementsByClassName("errormsg")[index].innerHTML = "username should not contain only alphanumeric values and space";
		return false;
	}

}

function validatePercentage(element_name, index, form_name) {
	var mark = document.forms[form_name][element_name].value;
	if (/^[0-9]$|^[0-9][0-9](\.\d{1,2})?$/.test(mark)) {
		document.getElementsByClassName("errormsg")[index].innerHTML = "";
		return true;
	} else {
		document.getElementsByClassName("errormsg")[index].innerHTML = "Percentage should be less than 100"
		return false;
	}
}

function validateCgpa(form_name, index) {
	var cgpa = document.forms[form_name]["cgpa"].value;
	if (/^(10|[1-9])(\.\d{1,2})?$/.test(cgpa)) {
		document.getElementsByClassName("errormsg")[index].innerHTML = "";
		return true;
	} else {
		document.getElementsByClassName("errormsg")[index].innerHTML = "cgpa should be greater than 0 and less than 10";
		return false;
	}
}

function validateArrearCount(form_name, index) {
	var arrear_count = document.forms[form_name]["arrear-count"].value;
	if (/^[0-9]$|^1[0-9]$|^2[0-9]$|^3[0-9]$|^4[0-5]$/.test(arrear_count)) {
		document.getElementsByClassName("errormsg")[index].innerHTML = "";
		return true;
	} else {
		document.getElementsByClassName("errormsg")[index].innerHTML = "arrear count should be less than 45";
		return false;
	}

}
function validateEmail() {
	var email = document.getElementsByName("email")[0].value;
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
		document.getElementsByClassName("errormsg")[6].innerHTML = "";
		return true;
	} else {
		document.getElementsByClassName("errormsg")[6].innerHTML = "enter a valid email";
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
	return true;
}

function register() {
	var xhttp = new XMLHttpRequest();
	var registerNumber = document.getElementsByName("register-number")[0].value;
	var studentName = document.getElementsByName("student-name")[0].value;
	var department = document.getElementsByName("department")[0].value;
	var hsc = document.getElementsByName("hsc")[0].value;
	var sslc = document.getElementsByName("sslc")[0].value;
	var cgpa = document.getElementsByName("cgpa")[0].value;
	var arrearCount = document.getElementsByName("arrear-count")[0].value;
	var email = document.getElementsByName("email")[0].value;
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == "success") {
				document.getElementById("msg").style.color = "#270";
				document.getElementById("msg").style.backgroundColor = "#DFF2BF";
				document.getElementById("msg").innerHTML = "SUCCESSFULLY REGISTERED";
				document.getElementById("register-form").reset();
			} else if (this.responseText == "registerNumber_exists") {
				document.getElementsByClassName("errormsg")[0].innerHTML = "Enter a valid Register Number";
			} else if (this.responseText == "registerNumber_error") {
				document.getElementsByClassName("errormsg")[0].innerHTML = "Register Number Already Exists";
			} else if (this.responseText == "nameError") {
				document.getElementsByClassName("errormsg")[1].innerHTML = "username should not contain only alphanumeric values and space";
			} else if (this.responseText == "hscError") {
				document.getElementsByClassName("errormsg")[2].innerHTML = "Percentage should be less than 100";
			} else if (this.responseText == "sslcError") {
				document.getElementsByClassName("errormsg")[3].innerHTML = "Percentage should be less than 100";
			} else if (this.responseText == "cgpaError") {
				document.getElementsByClassName("errormsg")[4].innerHTML = "cgpa should be greater than 0 and less than 10";
			} else if (this.responseText == "arrearError") {
				document.getElementsByClassName("errormsg")[5].innerHTML = "arrear count should be less than 45";
			} else if (this.responseText == "emailError") {
				document.getElementsByClassName("errormsg")[6].innerHTML = "enter a valid email";
			} else {

				document.getElementById("msg").style.color = "#D8000C";
				document.getElementById("msg").style.backgroundColor = "#FFD2D2";
				document.getElementById("msg").innerHTML = "SOMETHING WENT WRONG";
			}
		}
	};
	var object = {
		"studentId" : registerNumber,
		"studentName" : studentName,
		"department" : department,
		"markXII" : hsc,
		"markX" : sslc,
		"cgpa" : cgpa,
		"arrearCount" : arrearCount,
		"email" : email
	};

	console.log(object)
	xhttp.open("POST", "RegisterStudent", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("register=" + JSON.stringify(object));

}

function isExists() {
	var xhttp = new XMLHttpRequest();
	var registerNumber = document.getElementsByName("register-number")[0].value;
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == "exists") {
				document.getElementsByClassName("errormsg")[0].innerHTML = "Register Number Already Exists";
			} else {
				return true;
			}
		} else {
			console.log("error");
		}
	};
	xhttp.open("POST", "checkRegisterNumberExists", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("registerNumber=" + registerNumber);

}

function displayEditForm(index) {
	document.getElementById("edit-form").style.display = "flex";
	document.getElementById("edit-modal").style.display = "flex";
	nodes = document.getElementsByClassName("details")[index - 1].children;
	document.forms["edit-form"]["register-number"].value = nodes[0].innerText;
	document.forms["edit-form"]["student-name"].value = nodes[1].innerText;
	document.forms["edit-form"]["department"].value = nodes[2].innerText;
	document.forms["edit-form"]["hsc"].value = nodes[3].innerText;
	document.forms["edit-form"]["sslc"].value = nodes[4].innerText;
	document.forms["edit-form"]["cgpa"].value = nodes[5].innerText;
	document.forms["edit-form"]["arrear-count"].value = nodes[6].innerText;
	console.log(document.forms["edit-form"]["student-name"].value);
	document.getElementsByClassName("details-table")[0].style.display = "none";
	document.getElementsByClassName("body__content")[0].style.backgroundColor = "rgba(52, 54, 66, .9)";
}

document.getElementsByClassName("button--edit")[0].onclick = function() {
	console.log("yeah");
	if (validateStudentName('edit-form', 8)
			&& validatePercentage('hsc', 9, 'edit-form')
			&& validatePercentage('sslc', 10, 'edit-form')
			&& validateCgpa('edit-form', 11)
			&& validateArrearCount('edit-form', 12)) {
		update();
		return true;
	}

}

function deleteDetails(type, id, index) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == "success") {
				var detail = document.getElementsByClassName("details")[index - 1];
				document.getElementsByClassName("details-table")[0]
						.removeChild(detail);
				document.getElementsByClassName("delete_msg")[0].style.color = "#270";
				document.getElementsByClassName("delete_msg")[0].style.backgroundColor = "#DFF2BF";
				document.getElementsByClassName("delete_msg")[0].innerHTML = "Deleted successfully";
				document.getElementsByClassName("delete_msg")[0].style.margin = "1em";
				setTimeout(
						function() {
							document.getElementsByClassName("delete_msg")[0].style.display = "none";
						}, 6000);
			}
		} else {
			console.log("error");
		}
	};
	xhttp.open("POST", "delete", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("id=" + id + "&type=" + type);

}
function update() {
	console.log("i am in");
	var xhttp = new XMLHttpRequest();
	var form_name = "edit-form";
	var registerNumber = document.forms[form_name]["register-number"].value;
	var studentName = document.forms[form_name]["student-name"].value;
	var department = document.forms[form_name]["department"].value;
	var hsc = document.forms[form_name]["hsc"].value;
	var sslc = document.forms[form_name]["sslc"].value;
	var cgpa = document.forms[form_name]["cgpa"].value;
	var arrearCount = document.forms[form_name]["arrear-count"].value;
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == "success") {
				document.getElementById("edit_msg").style.color = "#270";
				document.getElementById("edit_msg").style.backgroundColor = "#DFF2BF";
				document.getElementById("edit_msg").innerHTML = "SUCCESSFULLY UPDATED";
				document.getElementById("register-form").reset();
			} else if (this.responseText == "registerNumber_exists") {
				document.getElementsByClassName("errormsg")[0].innerHTML = "Enter a valid Register Number";
			} else if (this.responseText == "registerNumber_error") {
				document.getElementsByClassName("errormsg")[0].innerHTML = "Register Number Already Exists";
			} else if (this.responseText == "nameError") {
				document.getElementsByClassName("errormsg")[8].innerHTML = "username should not contain only alphanumeric values and space";
			} else if (this.responseText == "hscError") {
				document.getElementsByClassName("errormsg")[9].innerHTML = "Percentage should be less than 100";
			} else if (this.responseText == "sslcError") {
				document.getElementsByClassName("errormsg")[10].innerHTML = "Percentage should be less than 100";
			} else if (this.responseText == "cgpaError") {
				document.getElementsByClassName("errormsg")[11].innerHTML = "cgpa should be greater than 0 and less than 10";
			} else if (this.responseText == "arrearError") {
				document.getElementsByClassName("errormsg")[12].innerHTML = "arrear count should be less than 45";
			}

			else {

				document.getElementById("edit_msg").style.color = "#D8000C";
				document.getElementById("edit_msg").style.backgroundColor = "#FFD2D2";
				document.getElementById("edit_msg").innerHTML = "SOMETHING WENT WRONG";
			}
		}
	};
	var object = {
		"studentId" : registerNumber,
		"name" : studentName,
		"department" : department,
		"markX" : hsc,
		"markXII" : sslc,
		"cgpa" : cgpa,
		"arrearCount" : arrearCount,
	};

	console.log(object)
	xhttp.open("POST", "updateStudent", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("updateStudent=" + JSON.stringify(object));

}
