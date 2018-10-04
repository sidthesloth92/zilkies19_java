//get suggestions for food item

var choiceList = [];

document.forms["food-form"]["food-input"].onkeyup = function() {
	document.getElementsByClassName("food-suggestion-list")[0].innerHTML = "";
	var input = document.forms["food-form"]["food-input"].value;
	if (input == "") {
		return;
	}

	var jsonresponse = "";

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			jsonresponse = JSON.parse(this.responseText);

			choiceList = [];

			for (var i = 0; i < jsonresponse.length; i++) {
				var obj = {
					foodId : jsonresponse[i].foodID,
					foodName : jsonresponse[i].foodName,
					servingsize : jsonresponse[i].serving,
					calories : jsonresponse[i].calories
				};

				choiceList.push(obj);
			}

			document.getElementsByClassName("food-suggestion-list")[0].innerHTML = "";
			var obj = document.getElementsByClassName("food-suggestion-list")[0];

			for (var i = 0; i < choiceList.length; i++) {
				var listNode = document.createElement("DIV");

				var nodeNameElement = document.createElement("SPAN");
				var nodeNameText = document
						.createTextNode(choiceList[i].foodName);
				nodeNameElement.appendChild(nodeNameText);

				listNode.appendChild(nodeNameElement);
				obj.appendChild(listNode);

				listNode
						.addEventListener(
								"click",
								function() {
									document.forms["food-form"]["food-input"].value = this.firstChild.innerHTML;
									showServingSize(this.firstChild.innerHTML);
									document
											.getElementsByClassName("food-suggestion-list")[0].innerHTML = "";
								});

			}
		} else {
			// console.log("no response");
		}
	};
	xhttp.open("GET", "/FitnessAppWeb/FoodDetailsServlet?foodname=" + input,
			true);
	xhttp.send();

}

// add foods to selected list for review

document.forms["food-form"]["add-button"].onclick = function() {
	addToSelectedList(document.forms["food-form"]["food-input"].value);
}

function showServingSize(foodname) {
	var obj;

	for (var i = 0; i < choiceList.length; i++) {
		if (choiceList[i].foodName.valueOf() == (foodname).valueOf()) {
			obj = choiceList[i];
		}
	}
	document.forms["food-form"]["serving-input"].value = obj.servingsize;
}

function addToSelectedList(foodname) {
	var obj;

	for (var i = 0; i < choiceList.length; i++) {
		if (choiceList[i].foodName.valueOf() == (foodname).valueOf()) {
			obj = choiceList[i];
		}
	}

	var container = document.getElementsByClassName("selected-food-list")[0];
	var listNode = document.createElement("DIV");
	listNode.classList.add("list-rows");

	var nodeNameElement = document.createElement("SPAN");
	var nodeNameText = document.createTextNode(obj.foodName);
	nodeNameElement.classList.add("foodname");
	nodeNameElement.appendChild(nodeNameText);

	var quantity = document.forms["food-form"]["quantity-input"].value;
	var nodeQuantityElement = document.createElement("SPAN");
	nodeQuantityElement.classList.add("foodquantity")
	var nodeQuantityText = document.createTextNode(quantity);
	nodeQuantityElement.appendChild(nodeQuantityText);

	var nodeServingElement = document.createElement("SPAN");
	var nodeServingText = document.createTextNode(obj.servingsize);
	nodeServingElement.appendChild(nodeServingText);

	var nodeCalorieElement = document.createElement("SPAN");
	var nodeCalorieText = document.createTextNode(obj.calories);
	nodeCalorieElement.appendChild(nodeCalorieText);

	var nodeTotalCalorieElement = document.createElement("SPAN");
	var nodeTotalCalorieText = document.createTextNode(quantity * obj.calories);
	nodeTotalCalorieElement.classList.add("total-calories")
	nodeTotalCalorieElement.appendChild(nodeTotalCalorieText);

	var nodeButton = document.createElement("BUTTON");
	nodeButton.addEventListener("click", function() {
		// remove node
		nodeButton.parentNode.parentNode.removeChild(nodeButton.parentNode);
		
//		//remove the data from DB
//		var foodname=nodeButton.parentNode.getElementsByClassName("foodname").innerHTML;
//		var foodname=nodeButton.parentNode.getElementsByClassName("foodname").innerHTML;
//		var foodname=nodeButton.parentNode.getElementsByClassName("foodname").innerHTML;
//		var foodname=nodeButton.parentNode.getElementsByClassName("foodname").innerHTML;
	});
	var nodeButtonText = document.createTextNode("remove");
	nodeButton.appendChild(nodeButtonText);

	listNode.appendChild(nodeNameElement);
	listNode.appendChild(nodeQuantityElement);
	listNode.appendChild(nodeServingElement);
	listNode.appendChild(nodeCalorieElement);
	listNode.appendChild(nodeTotalCalorieElement);
	listNode.appendChild(nodeButton);

	container.appendChild(listNode);
}

function calculateTotal() {
	var nodes = document.getElementsByClassName("total-calories");
	var sum = 0;
	for (var i = 0; i < nodes.length; i++) {
		sum += parseInt(nodes[i].innerHTML);
	}

	document.getElementsByClassName("calculator-box_result-wrapper")[0].innerHTML = "Total calories this meal : "
			+ sum;
	if (checkInputs()) {
		document.getElementsByClassName("calendar-error-message")[0].classList
				.remove("calendar-error-message_display");
		updateMeal();

	} else {
		document.getElementsByClassName("calendar-error-message")[0].classList
				.add("calendar-error-message_display");
	}

}

function updateMeal() {
	var dateinput = document.getElementsByClassName("fooddate")[0].value;
	var mealtimeinput = document.getElementsByClassName("meal-time")[0].selectedIndex + 1;

	console.log("dateinput" + dateinput + " mealtimeinput" + mealtimeinput);

	var foods = document.getElementsByClassName("foodname");
	console.log(foods);
	var quantities = document.getElementsByClassName("foodquantity");

	var foodslist = [];

	for (var i = 0; i < foods.length; i++) {
		var obj = {
			name : foods[i].innerHTML,
			quantity : quantities[i].innerHTML,
			date : dateinput,
			mealtime : mealtimeinput
		};
		foodslist.push(obj);
	}

	for (var i = 0; i < foodslist.length; i++) {
		console.log(foodslist[i].name + " " + foodslist[i].quantity);
	}

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			if (this.responseText == "true") {
				document.getElementsByClassName("done-message")[0].innerHTML = "<img src='/FitnessAppWeb/assets/done.png'>";
				setTimeout(
						function() {
							document.getElementsByClassName("done-message")[0].innerHTML = "";
						}, 1500);
			} else {
				document.getElementsByClassName("done-message")[0].innerHTML = "<img src='/FitnessAppWeb/assets/notdone.jpg'>";
				setTimeout(
						function() {
							document.getElementsByClassName("done-message")[0].innerHTML = "";
						}, 1500);
			}

		} else {
			console.log("no response");
		}
	};
	xhttp.open("POST", "/FitnessAppWeb/FoodTrackerServlet", true);
	xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhttp.send("foodslist=" + JSON.stringify(foodslist));
}

function getFoodLogs() {
	var dateinput = document.getElementsByClassName("fooddate")[0].value;
	var mealtimeinput = document.getElementsByClassName("meal-time")[0].selectedIndex + 1;

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log("response in js for getting foodlogs"
					+ this.responseText);

			jsonresponse = JSON.parse(this.responseText);

			var foodList = [];

			for (var i = 0; i < jsonresponse.length; i++) {
				var obj = {
					foodId : jsonresponse[i].foodID,
					foodName : jsonresponse[i].foodName,
					servingsize : jsonresponse[i].serving,
					calories : jsonresponse[i].calories,
					quantity : jsonresponse[i].quantity
				};
				foodList.push(obj);
				showExistingFoodList(obj);
			}
		} else {
			console.log("no response");
		}
	};
	xhttp.open("GET", "/FitnessAppWeb/FoodTrackerServlet?date=" + dateinput
			+ "&mealtime=" + mealtimeinput, true);
	xhttp.send();
	
	
}

function checkInputs() {
	var dateinput = document.getElementsByClassName("fooddate")[0].value;
	if (dateinput == "") {
		return false;
	} else {
		return true;
	}
}

document.getElementsByClassName("fooddate")[0].onchange = function() {
	console.log("date entered");
	var container = document.getElementsByClassName("selected-food-list")[0];
	if (container.hasChildNodes()) {
		container.removeChild(container.childNodes[0]);
	}
	getFoodLogs();
}


document.getElementsByClassName("meal-time")[0].onchange = function() {
	console.log("meal-time entered");
	var container = document.getElementsByClassName("selected-food-list")[0];
	if (container.hasChildNodes()) {
		container.removeChild(container.childNodes[0]);
	}
	getFoodLogs();
}

function showExistingFoodList(obj) {
	var obj;

	var container = document.getElementsByClassName("selected-food-list")[0];
	var listNode = document.createElement("DIV");
	listNode.classList.add("list-rows");

	var nodeNameElement = document.createElement("SPAN");
	var nodeNameText = document.createTextNode(obj.foodName);
	nodeNameElement.classList.add("foodname");
	nodeNameElement.appendChild(nodeNameText);

	var quantity = document.forms["food-form"]["quantity-input"].value;
	var nodeQuantityElement = document.createElement("SPAN");
	nodeQuantityElement.classList.add("foodquantity")
	var nodeQuantityText = document.createTextNode(obj.quantity);
	nodeQuantityElement.appendChild(nodeQuantityText);

	var nodeServingElement = document.createElement("SPAN");
	var nodeServingText = document.createTextNode(obj.servingsize);
	nodeServingElement.appendChild(nodeServingText);

	var nodeCalorieElement = document.createElement("SPAN");
	var nodeCalorieText = document.createTextNode(obj.calories);
	nodeCalorieElement.appendChild(nodeCalorieText);

	var nodeTotalCalorieElement = document.createElement("SPAN");
	var nodeTotalCalorieText = document.createTextNode(obj.quantity * obj.calories);
	nodeTotalCalorieElement.classList.add("total-calories")
	nodeTotalCalorieElement.appendChild(nodeTotalCalorieText);

	var nodeButton = document.createElement("BUTTON");
	nodeButton.addEventListener("click", function() {
		// remove node
		nodeButton.parentNode.parentNode.removeChild(nodeButton.parentNode);
	});
	var nodeButtonText = document.createTextNode("remove");
	nodeButton.appendChild(nodeButtonText);

	listNode.appendChild(nodeNameElement);
	listNode.appendChild(nodeQuantityElement);
	listNode.appendChild(nodeServingElement);
	listNode.appendChild(nodeCalorieElement);
	listNode.appendChild(nodeTotalCalorieElement);
	listNode.appendChild(nodeButton);

	container.appendChild(listNode);
}
