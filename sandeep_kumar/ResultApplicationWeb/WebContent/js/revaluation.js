if(document.body.classList.contains("approve-reval-student")) {
	document.getElementsByClassName("apply-reval")[0].onclick= function(){
		var checkedValue = document.querySelectorAll(".apply-reval-checkbox:checked");
		var selectedCheckBox=[];
		checkedValue.forEach(function(element){
			selectedCheckBox.push(element.value);
		});
		console.log(selectedCheckBox.toString());
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				if(this.responseText=="true") {
					selectedCheckBox.forEach(function(id){
						document.getElementById("row_"+id).parentNode.removeChild(document.getElementById("row_"+id));
					});
				}
				else {
					console.log(this.responseText);
				}
			}
		};
		xhttp.open("POST", "RevaluationController", true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send("selectedCheckBox="+selectedCheckBox.toString()+"&action=applyRevaluation");

	}

}
console.log(document.body.classList.contains("approve-by-faculty"));
if(document.body.classList.contains("approve-by-faculty")) {
	console.log("hiiq");
	document.getElementsByClassName("approve-request")[0].onclick= function(){
		console.log("hii");
		var approvedCheckBox = document.querySelectorAll(".approve-radio:checked");
		var rejectedCheckBox = document.querySelectorAll(".reject-radio:checked");
		var approvedIdList=[], rejectedIdList=[], parameters="action=approveRequestByFaculty";
		if(approvedCheckBox.length>0) {
			approvedCheckBox.forEach(function(element){
				approvedIdList.push(element.value);
			});
			parameters+="&approvedIdList="+approvedIdList.toString();
		}
		if(rejectedCheckBox.length>0) {
			rejectedCheckBox.forEach(function(element){
				rejectedIdList.push(element.value);
			});
			parameters+="&rejectedIdList="+rejectedIdList.toString();
		}
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				if(this.responseText=="true") {
					console.log("success");
				}
				else {
					console.log(this.responseText);
				}
			}
		};
		xhttp.open("POST", "RevaluationController", true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send(parameters);
	}
}