function getCompany() {
	console.log("yes");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var myObject = JSON.parse(this.responseText);
			console.log(myObject);
			var select = document.getElementsByName("company")[0];
			for (var i = 0; i < myObject.length; i++) {
				var option = document.createElement("option");
				option.text = myObject[i].companyName;
				option.value = myObject[i].companyId;
				select.add(option);
			}
		} else {
			console.log("error");
		}
	};
	xhttp.open("GET", "registrationList", true);
	xhttp.send();

}