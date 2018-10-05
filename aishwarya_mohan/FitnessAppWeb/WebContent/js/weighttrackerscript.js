//window.onload = function() {
//	console.log("hey");
//
//	var jsonresponse = "";
//
//	var xhttp = new XMLHttpRequest();
//	xhttp.onreadystatechange = function() {
//		if (this.readyState == 4 && this.status == 200) {
//			jsonresponse = JSON.parse(this.responseText);
//
//			console.log("response : " + jsonresponse);
//
//			var weights = [];
//			var dates = [];
//
//			for (var i = 0; i < jsonresponse.length; i++) {
//				console
//						.log(jsonresponse[i].date + " "
//								+ jsonresponse[i].weight);
//				weights.push(jsonresponse[i].weight);
//				dates.push(jsonresponse[i].date);
//			}
//
//			for (var i = 0; i < weights.length; i++) {
//				console.log(weights[i]);
//			}
//
//			var ctx = document.getElementById("chart").getContext("2d");
//
//			var myLineChart = new Chart(ctx, {
//				type : 'line',
//				label : dates,
//				data : weights,
//				options : {
//					responsive : true,
//					maintainAspectRatio : false
//				// scales : {
//				// yAxes : [ {
//				// ticks : {
//				// beginAtZero : true
//				// }
//				// } ]
//				// }
//				}
//			});
//
//		}
//	};
//	xhttp.open("GET", "/FitnessFirstPrototype/WeightTrackerServlet", true);
//	xhttp.send();
//
//}

document.getElementsByClassName("user-icon")[0].onclick = function() {
	document.getElementsByClassName("profile-menu")[0].classList
			.toggle("show-menu");
}

function updateWeight() {
	var dateinput = document.getElementsByClassName("weightdate")[0].value;
	console.log(dateinput);
	var weightinput = document.getElementsByClassName("weightvalue")[0].value;
	console.log(weightinput);

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			if(this.responseText=="true"){
				document.getElementsByClassName("response-msg")[0].innerHTML="Updated Successfully"
			}else{
				document.getElementsByClassName("response-msg")[0].innerHTML="Update Failed"
			}
		}
	};
	xhttp.open("POST", "/FitnessFirstPrototype/WeightTrackerServlet", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("date=" + dateinput + "&weight=" + weightinput);
}