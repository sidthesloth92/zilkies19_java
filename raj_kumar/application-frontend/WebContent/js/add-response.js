/**
 * 
 */

function showResponseModal(projectID) {
	modal.style.display = "block"
	alert("asjd");
	document.getElementsByClassName("modal-content__project-id")[0].innerHTML = projectID;
}

// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
// btn.onclick = function() {
// document.getElementsByClassName("modal-content__project-id")[0].innerHTML =
// projectID;
// modal.style.display = "block";
// }

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
	modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
}

function submitResponse(event, form) {
	event.preventDefault();
	var responseDataString = document.getElementById("responseData").value;
    var projectID = document.getElementsByClassName("modal-content__project-id")[0].innerHTML;
    alert(projectID);
    var object = {
    		"responseData": responseDataString,
    		"projectId": projectID,
    		"post-type": "add-response"
    }
   // console.log(object);
	fetch('/TenderApplication/AddResponse', {
      method: 'POST',	
      body: JSON.stringify(object),
      header: {'Content-Type': 'application/json'}
      		
    }).then(function(response) {
    	console.log(response);
    	return response;
    }).then(function(data) {
      // Success code goes here
    	console.log(data);
    	alert('Response Added')
    }).catch(function(err) {
      // Failure
      alert('An Error Occured while adding ')
    });
}







