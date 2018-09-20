var modal = document.getElementById("myModal");

var btn = document.getElementById("myBtn");

var span = document.getElementsByClassName("close")[0];

function displayModal(projectID) {
	document.getElementById(projectID).classList
			.toggle("contractor-project-application-section");
	document.getElementById("icon-" + projectID).classList
			.toggle("fa-rotate-180");
	console.log("SDSD");
	// document.getElementById(projectID).style.display = "block";
}

// btn.onclick = function() {
// modal.style.display = "block";
// var modelId = btn.value;
// document.getElementById(modelId).style.display = "block";
// };

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
	modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
};