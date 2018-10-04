var makeId = 0, carTypeId = 0, car;
function getMakes() {
	var list = document.getElementById("select-makes");
	list.innerHTML = "";
	var option;
	option = document.createElement("option");
	option.value = "nil";
	option.disabled=true;
	option.selected=true;
	option.text = "Choose a Make";
	list.appendChild(option);
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var makes = JSON.parse(this.responseText);
			for (var i = 0; i < makes.length; i++) {
				option = document.createElement("option");
				option.value = makes[i]["makeId"];
				option.text = makes[i]["makeName"];
				list.appendChild(option);
			}
		}
	};
	xhttp.open("GET", "/Autorate/FetchMakesServlet?carTypeId=" + carTypeId,
			true);
	xhttp.send();
}

function getTypes() {
	var sel = document.getElementById("select-makes");
	makeId = sel.options[sel.selectedIndex].value;
	var list = document.getElementById("select-car-types");
	list.innerHTML = "";
	var option;
	option = document.createElement("option");
	option.value = "nil";
	option.disabled=true;
	option.selected=true;
	option.text = "Choose a Type";
	list.appendChild(option);
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var carTypes = JSON.parse(this.responseText);
			for (var i = 0; i < carTypes.length; i++) {
				option = document.createElement("option");
				option.value = carTypes[i]["carTypeId"];
				option.text = carTypes[i]["carTypeName"];
				list.appendChild(option);
			}
		}
	};
	xhttp.open("GET", "/Autorate/FetchCarTypesServlet?makeId=" + makeId, true);
	xhttp.send();
}

function getCars() {
	var sel = document.getElementById("select-car-types");
	carTypeId = sel.options[sel.selectedIndex].value;
	var list = document.getElementById("select-cars");
	list.innerHTML = "";
	var option;
	option = document.createElement("option");
	option.value = "nil";
	option.disabled=true;
	option.selected=true;
	option.text = "Choose a Car";
	list.appendChild(option);
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var cars = JSON.parse(this.responseText);
			for (var i = 0; i < cars.length; i++) {
				option = document.createElement("option");
				option.value = cars[i]["carId"];
				option.text = cars[i]["carName"];
				list.appendChild(option);
			}
		}
	};
	xhttp.open("GET", "/Autorate/FetchCarsServlet?makeId=" + makeId
			+ "&carTypeId=" + carTypeId, true);
	xhttp.send();

}

function displayCar() {
	var input,label,option;
	var sel = document.getElementById("select-cars");
	var container = document
			.getElementsByClassName("inner-container-edit-car__edit")[0];
	container.innerHTML = "";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var car = JSON.parse(this.responseText);
			input = document.createElement("input");
			input.type = "hidden";
			input.name= "car-id";
			input.value= car["carId"];
			container.appendChild(input);

			label = document.createElement("label");
			label.innerHTML="Car Name";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["carName"];
			input.name= "car-name";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="Engine Type";
			input = document.createElement("select");
			input.name="engine-type";
			option = document.createElement("option");
			option.value=car["engineType"];
			option.text=car["engineType"];
			option.disabled=true;
			option.selected=true;
			input.appendChild(option);
			option = document.createElement("option");
			option.value="petrol";
			option.text="Petrol";
			input.appendChild(option);
			option = document.createElement("option");
			option.value="diesel";
			option.text="Diesel";
			input.appendChild(option);
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="Power";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["power"];
			input.name= "power";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="Torque";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["torque"];
			input.name= "torque";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="ABS";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["abs"];
			input.name= "abs";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="Airbag";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["airbag"];
			input.name= "airbag";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);

			label = document.createElement("label");
			label.innerHTML="Cylinder";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["cylinder"];
			input.name= "cylinder";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="Displacement";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["displacement"];
			input.name= "displacement";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="Fuel Capacity";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["fuelCapacity"];
			input.name= "fuel-capacity";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="Kerb Weight";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["kerbWeight"];
			input.name= "kerb-weight";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="Transimission";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["transmission"];
			input.name= "transmission";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="Drivetrain";
			input = document.createElement("select");
			input.name="drivetrain";
			option = document.createElement("option");
			option.value=car["drivetrain"];
			option.text=car["drivetrain"];
			option.disabled=true;
			option.selected=true;
			input.appendChild(option);
			option = document.createElement("option");
			option.value="FWD";
			option.text="Front Wheel Drive";
			input.appendChild(option);
			option = document.createElement("option");
			option.value="RWD";
			option.text="Rear Wheel Drive";
			input.appendChild(option);
			option = document.createElement("option");
			option.value="AWD";
			option.text="All Wheel Drive";
			input.appendChild(option);
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="Wheelbase";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["wheelbase"];
			input.name= "wheelbase";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			label = document.createElement("label");
			label.innerHTML="Price";
			input = document.createElement("input");
			input.type = "text";
			input.value= car["price"];
			input.name= "price";
			label.setAttribute("for",input.name);
			container.appendChild(label);
			container.appendChild(input);
			
			input = document.createElement("button");
			input.type = "submit";
			input.innerHTML= "Submit";
			container.appendChild(input);
		}
	};
	xhttp.open("GET", "/Autorate/FetchCarSpecsServlet?id="
			+ sel.options[sel.selectedIndex].value, true);
	xhttp.send();
}
