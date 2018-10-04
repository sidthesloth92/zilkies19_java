/**
 * 
 */
var company_id = 0;

function showModal() {
	document.getElementById("modal").style.display = "flex";
	document.getElementsByClassName("details-table")[0].style.display = "none";
	document.getElementsByClassName("body__left-panel")[0].style.display = "none";
	document.getElementsByClassName("body__content")[0].style.backgroundColor = "#d0f0fe";
	document.getElementsByClassName("button--add")[0].style.display = "none";
}
function hideModal() {
	document.getElementById("modal").style.display = "none";
	document.getElementsByClassName("details-table")[0].style.display = "block";
	document.getElementsByClassName("body__left-panel")[0].style.display = "flex";
	if (document.getElementById("submit-button").className == "button--edit") {
		document.getElementById("submit-button").className = "button--save";
	}
	document.getElementById("company-form").reset();
	window.location.reload();
}
function validateInput(form_name, index, element_name) {
	var name = document.forms[form_name][element_name].value;
	console.log(name);
	if (/^[a-zA-Z ]+$/.test(name)) {
		document.getElementsByClassName("errormsg")[index].innerHTML = "";

		return true;
	} else {
		document.getElementsByClassName("errormsg")[index].innerHTML = "field should contain only alphabets and space";
		return false;
	}
}
function validateDate(form_name, element_name, index) {
	return true;
}
function validatePercentage(element_name, index, form_name) {
	var mark = document.forms[form_name][element_name].value;
	if (/^[0-9]$|^[0-9][0-9]$/.test(mark)) {
		document.getElementsByClassName("errormsg")[index].innerHTML = "";
		return true;
	} else {
		document.getElementsByClassName("errormsg")[index].innerHTML = "Percentage should be less than 100"
		return false;
	}
}

function validateCgpa(form_name, index) {
	var cgpa = document.forms[form_name]["cgpa"].value;
	if (/^(10|[0-9])(\.\d{1,2})?$/.test(cgpa)) {
		document.getElementsByClassName("errormsg")[index].innerHTML = "";
		return true;
	} else {
		document.getElementsByClassName("errormsg")[index].innerHTML = "cgpa should be between 0 and 10";
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

document.getElementById("submit-button").onclick = function() {
	if (validateInput('company-form', 0, 'location')
			&& validateInput('company-form', 1, 'designation')
			&& validateDate('company-form', 3, 'recruitment-date')
			&& validateDate('company-form', 4, 'registration-date')
			&& validatePercentage('hsc', 5, 'company-form')
			&& validatePercentage('sslc', 6, 'company-form')
			&& validateCgpa('company-form', 7)
			&& validateArrearCount('company-form', 7)) {
		if (document.getElementById("submit-button").className == "button--edit") {
			companyDetails("update");
		} else {
			console.log("yes");
			companyDetails("add");
		}
		return true;
	}

}

function displayEditForm(id) {

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var object = JSON.parse(this.responseText);
			document.forms["company-form"]["company-name"].value = object.companyName;
			document.forms["company-form"]["company-type"].value = object.companyType;
			document.forms["company-form"]["description"].value = object.companyDescription;
			document.forms["company-form"]["location"].value = object.jobLocation;
			document.forms["company-form"]["package"].value = object.payment;
			document.forms["company-form"]["designation"].value = object.designation;
			document.forms["company-form"]["recruitment-date"].value = object.dayOfRecruitment;
			document.forms["company-form"]["registration-date"].value = object.lastDateForRegistration;
			document.forms["company-form"]["hsc"].value = object.markXII;
			document.forms["company-form"]["sslc"].value = object.markX;
			document.forms["company-form"]["cgpa"].value = object.cgpa;
			document.forms["company-form"]["arrear-count"].value = object.arrearCount;
			document.forms["company-form"]["package"].value = object.payment;
			company_id = id;
			showModal();
			document.getElementById("submit-button").className = "button--edit";
			document.getElementsByName("company-name")[0].readOnly = true;
		} else {
			console.log("error");
		}
	};
	console.log(id);
	xhttp.open("POST", "admin", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("id=" + id);

}

function companyDetails(operation) {
	var xhttp = new XMLHttpRequest();
	var company_name = document.getElementsByName("company-name")[0].value;
	var company_type = document.getElementsByName("company-type")[0].value;
	var description = document.getElementsByName("description")[0].value;
	var location = document.getElementsByName("location")[0].value;
	var salary = document.getElementsByName("package")[0].value;
	var designation = document.getElementsByName("designation")[0].value;
	var recruitment_date = document.getElementsByName("recruitment-date")[0].value;
	var last_date = document.getElementsByName("registration-date")[0].value;
	var hsc = document.getElementsByName("hsc")[0].value;
	var sslc = document.getElementsByName("sslc")[0].value;
	var cgpa = document.getElementsByName("cgpa")[0].value;
	var arrearCount = document.getElementsByName("arrear-count")[0].value;
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == "success") {
				document.getElementById("msg").style.color = "#270";
				document.getElementById("msg").style.backgroundColor = "#DFF2BF";
				if (operation == "add") {
					document.getElementById("msg").innerHTML = "SUCCESSFULLY REGISTERED";
				}
				if (operation == "update") {
					document.getElementById("msg").innerHTML = "UPDATED SUCCESSFULLY";

				}
				document.getElementById("company-form").reset();
			} else if (this.responseText == "recruitmentdateError") {
				document.getElementsByClassName("errormsg")[2].innerHTML = "Recruitment date is before the current date";
				setTimeout(
						function() {
							document.getElementsByClassName("errormsg")[2].style.display = "none";
						}, 2000);
			} else if (this.responseText == "lastdateError") {
				document.getElementsByClassName("errormsg")[3].innerHTML = "Registration date should before the recruitment date";
				setTimeout(
						function() {
							document.getElementsByClassName("errormsg")[3].style.display = "none";
						}, 2000);

			} else if (this.responseText == "hscError") {
				document.getElementsByClassName("errormsg")[4].innerHTML = "Percentage should be less than 100";
				setTimeout(
						function() {
							document.getElementsByClassName("errormsg")[4].style.display = "none";
						}, 2000);
			} else if (this.responseText == "sslcError") {
				document.getElementsByClassName("errormsg")[5].innerHTML = "Percentage should be less than 100";
				setTimeout(
						function() {
							document.getElementsByClassName("errormsg")[5].style.display = "none";
						}, 2000);
			} else if (this.responseText == "cgpaError") {
				document.getElementsByClassName("errormsg")[6].innerHTML = "cgpa should be greater than 0 and less than 10";
				setTimeout(
						function() {
							document.getElementsByClassName("errormsg")[0].style.display = "none";
						}, 2000);
			} else if (this.responseText == "arrearError") {
				document.getElementsByClassName("errormsg")[7].innerHTML = "arrear count should be less than 45";
				setTimeout(
						function() {
							document.getElementsByClassName("errormsg")[0].style.display = "none";
						}, 2000);
			} else {

				document.getElementById("msg").style.color = "#D8000C";
				document.getElementById("msg").style.backgroundColor = "#FFD2D2";
				document.getElementById("msg").innerHTML = "SOMETHING WENT WRONG";
			}

		}
	};
	var object = {
		"companyName" : company_name,
		"companyType" : company_type,
		"designation" : designation,
		"companyDescription" : description,
		"jobLocation" : location,
		"payment" : salary,
		"dayOfRecruitment" : recruitment_date,
		"lastDateForRegistration" : last_date,
		"markXII" : hsc,
		"markX" : sslc,
		"cgpa" : cgpa,
		"arrearCount" : arrearCount,
		"companyId" : company_id
	};
	console.log(operation);
	console.log(company_id);
	xhttp.open("POST", "company", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("company=" + JSON.stringify(object) + "&operation=" + operation);

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
						}, 2000);
			}
		} else {
			console.log("error");
		}
	};
	xhttp.open("POST", "delete", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("id=" + id + "&type=" + type);

}
