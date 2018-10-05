/**
 * 
 */
var profile_id = 0;
function showModal(category) {

	document.getElementsByClassName("modal-container")[0].style.display = "flex";
	document.getElementsByClassName("profile-title")[0].innerHTML = category
			.toUpperCase()
			+ " TITLE";
	document.getElementsByClassName("profile-description")[0].innerHTML = category
			.toUpperCase()
			+ " DESCRIPTION";
	document.getElementById("button--submit").className = category + "--submit";
	// document.getElementById("submit-button").className=category;
}
function hideModal() {
	document.getElementsByClassName("modal-container")[0].style.display = "none";
	document.getElementsByClassName("profile-title")[0].innerHTML = "";
	document.getElementsByClassName("profile-description")[0].innerHTML = "";
	if (document.getElementById("button--submit").className == "button--edit") {
		document.getElementById("button--submit").className = "button--save";
	}
	/*
	 * document.getElementById("peofile-form").reset();
	 */window.location.reload();
}

function printResume(){
	window.print();
}
document.getElementById("button--submit").onclick = function(event) {
	if (document.getElementsByName("title")[0].value.length > 0
			&& document.getElementsByName("description")[0].value.length > 0) {
		if (document.getElementById("button--submit").className == "project--submit") {
			profileDetails("project", "add");
		} else if (document.getElementById("button--submit").className == "course--submit") {
			profileDetails("course", "add");
		} else if (document.getElementById("button--submit").className == "achievement--submit") {
			profileDetails("achievement", "add");
		} else if (document.getElementById("button--submit").className == "project--edit") {
			profileDetails("project", "update");
		} else if (document.getElementById("button--submit").className == "course--edit") {
			profileDetails("course", "update");
		} else {
			profileDetails("achievement", "update");
		}
	} else {
		document.getElementsByClassName("errormsg")[0].innerHTML = "*All fields are required";
		document.getElementsByClassName("errormsg")[0].style.color = "red";
		return false;
	}
}

function profileDetails(profileType, operation) {
	var xhttp = new XMLHttpRequest();
	var profileTitle = document.getElementsByName("title")[0].value;
	var description = document.getElementsByName("description")[0].value;
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == "success") {
				document.getElementById("msg").style.color = "#270";
				document.getElementById("msg").style.backgroundColor = "#DFF2BF";
				if (operation == "add") {
					document.getElementById("msg").innerHTML = "SUCCESSFULLY REGISTERED";
					setTimeout(function() {
						document.getElementById("msg").style.display = "none";
					}, 2000);

				}
				if (operation == "update") {
					document.getElementById("msg").innerHTML = "UPDATED SUCCESSFULLY";
					setTimeout(function() {
						document.getElementById("msg").style.display = "none";
					}, 2000);

				}
				document.getElementsByName("profile-form")[0].reset();
			} else {

				document.getElementById("msg").style.color = "#D8000C";
				document.getElementById("msg").style.backgroundColor = "#FFD2D2";
				document.getElementById("msg").innerHTML = "SOMETHING WENT WRONG";
				setTimeout(function() {
					document.getElementById("msg")[0].style.display = "none";
				}, 2000);

			}
		}
	};
	console.log(profile_id);
	var object = {
		"title" : profileTitle,
		"description" : description,
		"id" : profile_id
	};
	console.log(operation);
	xhttp.open("POST", "studentProfile", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("profileDetail=" + JSON.stringify(object) + "&operation="
			+ operation + "&profileType=" + profileType);

}

function displayEditForm(id, type) {
	console.log(type);
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var object = JSON.parse(this.responseText);
			document.forms["profile-form"]["title"].value = object.title;
			document.forms["profile-form"]["description"].value = object.description;
			profile_id = object.profile_id;
			console.log(profile_id);
			showModal(type);
			document.getElementById("button--submit").className = type
					+ "--edit";

		} else {
			console.log("error");
		}
	};
	xhttp.open("POST", "updateStudentProfile", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("id=" + id + "&profileType=" + type);

}

function deleteDetails(type, id) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == "success") {
				window.location.reload(true);
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
