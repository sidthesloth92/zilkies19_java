/**
 * 
 */
function applyForJob(id) {
	document.getElementsByClassName("message")[0].style.display = "block";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == "applied") {
				document.getElementsByClassName("message")[0].innerHTML = "Already Registered!!!";
				document.getElementsByClassName("message")[0].style.color = "#270";
				document.getElementsByClassName("message")[0].style.backgroundColor = "#DFF2BF";
				
				setTimeout(
						function() {
							document.getElementsByClassName("message")[0].style.display = "none";
						}, 2000);
			}
			else if(this.responseText=="non-eligible"){
				document.getElementsByClassName("message")[0].innerHTML = "YOU ARE NOT ELIGIBLE FOR THIS COMPANY";
				document.getElementsByClassName("message")[0].style.color = "#D8000C";
				document.getElementsByClassName("message")[0].style.backgroundColor = "#FFD2D2";
				
				setTimeout(
						function() {
							document.getElementsByClassName("message")[0].style.display = "none";
						}, 2000);
			}
			else if(this.responseText=="success"){
				document.getElementsByClassName("message")[0].innerHTML = "SUCCESSFULLY REGISTERED!!!";
				document.getElementsByClassName("message")[0].style.color = "#270";
				document.getElementsByClassName("message")[0].style.backgroundColor = "#DFF2BF";
				
				setTimeout(
						function() {
							document.getElementsByClassName("message")[0].style.display = "none";
						}, 2000);
			}
			else {
				document.getElementsByClassName("message")[0].innerHTML = "SOMETHING WENT WRONG!";
				document.getElementsByClassName("message")[0].style.color = "#D8000C";
				document.getElementsByClassName("message")[0].style.backgroundColor = "#FFD2D2";
				
				setTimeout(
						function() {
							document.getElementsByClassName("message")[0].style.display = "none";
						}, 2000);
			}
			
		} else {
			console.log("error");
		}
	};
	xhttp.open("POST", "job", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.send("id=" + id + "&type=" + "applyJob");

}