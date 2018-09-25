var choiceList = [];

document.forms["food-form"]["food-input"].onkeyup = function() {
	console.log("hy");
	document.getElementsByClassName("food-suggestion-list")[0].innerHTML = "";
	var input = document.forms["food-form"]["food-input"].value;
	if (input == "") {
		return;
	}

	var jsonresponse = "";

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			jsonresponse = JSON.parse(this.responseText);

			choiceList = [];

			for (var i = 0; i < jsonresponse.length; i++) {
				console.log(jsonresponse[i]);
				var obj = {
					foodId : jsonresponse[i].foodId,
					foodName : jsonresponse[i].foodName,
					servingsize : jsonresponse[i].servingsize,
					calories : jsonresponse[i].calories
				};

				choiceList.push(obj);
			}

			document.getElementsByClassName("food-suggestion-list")[0].innerHTML = "";
			var obj = document.getElementsByClassName("food-suggestion-list")[0];

//			while (obj.hasChildNodes()) {
//				obj.removeChild(obj.firstChild);
//			}

			for (var i = 0; i < choiceList.length; i++) {
				var listNode = document.createElement("DIV");

				var nodeNameElement = document.createElement("SPAN");
				var nodeNameText = document
						.createTextNode(choiceList[i].foodName);
				nodeNameElement.appendChild(nodeNameText);
				
//				var nodeButton = document.createElement("BUTTON");
//				var nodeButtonText = document.createTextNode("+");
//				nodeButton.appendChild(nodeButtonText);

				listNode.appendChild(nodeNameElement);
//				listNode.appendChild(nodeButton);

				obj.appendChild(listNode);

				listNode.addEventListener("click", function() {
					document.forms["food-form"]["food-input"].value = this.firstChild.innerHTML;
					showServingSize(this.firstChild.innerHTML);
					document.getElementsByClassName("food-suggestion-list")[0].innerHTML = "";
				});

			}
		} else {
			console.log("no response");
		}
	};
	xhttp.open("POST", "/FitnessFirstPrototype/CalorieCalculatorServlet", true);
	xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhttp.send("foodname=" + input);

}


document.forms["food-form"]["add-button"].onclick = function(){
	addToSelectedList(document.forms["food-form"]["food-input"].value);	
}

function showServingSize(foodname){
	var obj;

	for (var i = 0; i < choiceList.length; i++) {
		console.log
		if (choiceList[i].foodName.valueOf() == (foodname).valueOf()) {
			obj = choiceList[i];
		}
	}
	document.forms["food-form"]["serving-input"].value = obj.servingsize;
}

function addToSelectedList(foodname) {
	var obj;

	for (var i = 0; i < choiceList.length; i++) {
		console.log
		if (choiceList[i].foodName.valueOf() == (foodname).valueOf()) {
			obj = choiceList[i];
		}
	}

	var container = document.getElementsByClassName("selected-food-list")[0];
	var listNode = document.createElement("DIV");

	var nodeNameElement = document.createElement("SPAN");
	var nodeNameText = document.createTextNode(obj.foodName);
	nodeNameElement.appendChild(nodeNameText);

	var quantity = document.forms["food-form"]["quantity-input"].value;
	var nodeQuantityElement = document.createElement("SPAN");
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


function calculateTotal(){
	var nodes=document.getElementsByClassName("total-calories");
	var sum=0;
	for(var i=0; i<nodes.length;i++){
		sum+=parseInt(nodes[i].innerHTML);
	}
	
	document.getElementsByClassName("calculator-box_result-wrapper")[0].innerHTML="Total Calories : "+sum;
	console.log(sum);
}
