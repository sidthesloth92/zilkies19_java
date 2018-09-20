var carTypeId=0, makeId=0;
function getMakes() {
	clearAll();
	carTypeId=0;
    var list= document.getElementsByClassName("inner-container-main-list")[0];
    list.innerHTML="";
    var divs;   
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
           var makes= JSON.parse(this.responseText);
           for(var i=0;i<makes.length;i++) {
        	   divs= document.createElement("div");
               divs.appendChild(document.createTextNode(makes[i]["makeName"]));
               divs.id=makes[i]["makeId"];
               divs.classList.add("button--hover");
               divs.addEventListener("click", function() {
            	   makeId=this.id;
            	   getTypes();
               });
               list.appendChild(divs);
           }
        }
    };
    xhttp.open("GET", "/autoratefe/FetchMakesServlet?carTypeId="+carTypeId, true);
    xhttp.send();
}

function getTypes() {
    var list= document.getElementsByClassName("inner-container-main-list")[0];
    list.innerHTML="";
    var divs;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
           var carTypes= JSON.parse(this.responseText);
           for(var i=0;i<carTypes.length;i++) {
        	   divs= document.createElement("div");
               divs.appendChild(document.createTextNode(carTypes[i]["carTypeName"]));
               divs.id=carTypes[i]["carTypeId"];
               divs.classList.add("button--hover");
               divs.addEventListener("click", function() {
            	   carTypeId=this.id;
            	   getCars();
               });
               list.appendChild(divs);
           }
        }
    };
    xhttp.open("GET", "/autoratefe/FetchCarTypesServlet?makeId="+makeId, true);
    xhttp.send();
}

function getCars() {
	clearAll();
    var list= document.getElementsByClassName("inner-container-cars")[0];
    var div, name, specs, h2,p;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
           var cars= JSON.parse(this.responseText);
           for(var i=0;i<cars.length;i++) {
	            div= document.createElement("div");
	       	    div.classList.add("inner-container-cars-info");
	       	    name= document.createElement("div");
	       	    name.classList.add("button--hover");
	       	    name.id=cars[i]["carId"];
	       	    name.addEventListener("click", function myFunction() {
	       	    	window.location='/autoratefe/CarServlet?id=' + this.id;
	       	    });
	       	    name.classList.add("inner-container-cars-info-car-name");
	       	    name.appendChild(document.createElement("h2").appendChild(document.createTextNode(cars[i]["carName"])));
	       	    div.appendChild(name);
	       	    specs= document.createElement("div");
	       	    specs.classList.add("inner-container-cars-info-car-specs");
	       	    
		       	p=document.createElement("P");
		       	p.appendChild(document.createTextNode("Power:"+cars[i]["power"]));
	       	    specs.appendChild(p);

	       	    p=document.createElement("P");
		       	p.appendChild(document.createTextNode("ABS:"+cars[i]["abs"]));
	       	    specs.appendChild(p);
	       	    
	       	    p=document.createElement("P");
		       	p.appendChild(document.createTextNode("Cylinder:"+cars[i]["cylinder"]));
	       	    specs.appendChild(p);
	       	    
	       	    p=document.createElement("P");
		       	p.appendChild(document.createTextNode("Displacement:"+cars[i]["displacement"]));
	       	    specs.appendChild(p);
	       	    
	       	    p=document.createElement("P");
		       	p.appendChild(document.createTextNode("Torque:"+cars[i]["torque"]));
	       	    specs.appendChild(p);
	       	    
	       	    p=document.createElement("P");
		       	p.appendChild(document.createTextNode("Fuel Capacity:"+cars[i]["fuelCapacity"]));
	       	    specs.appendChild(p);
	       	    
	       	    p=document.createElement("P");
		       	p.appendChild(document.createTextNode("Kerb Weight:"+cars[i]["kerbWeight"]));
	       	    specs.appendChild(p);
	       	    
	       	    p=document.createElement("P");
		       	p.appendChild(document.createTextNode("Airbag:"+cars[i]["airbag"]));
	       	    specs.appendChild(p);
	       	    
	       	    p=document.createElement("P");
		       	p.appendChild(document.createTextNode("Drivetrain:"+cars[i]["drivetrain"]));
	       	    specs.appendChild(p);
	       	    
	       	    p=document.createElement("P");
		       	p.appendChild(document.createTextNode("Engine Type:"+cars[i]["engineType"]));
	       	    specs.appendChild(p);
	       	    
	       	    p=document.createElement("P");
		       	p.appendChild(document.createTextNode("Wheelbase:"+cars[i]["wheelbase"]));
	       	    specs.appendChild(p);
	       	    
	       	    p=document.createElement("P");
		       	p.appendChild(document.createTextNode("Price:"+cars[i]["price"]));
	       	    specs.appendChild(p);
	       	    
	       	    div.appendChild(specs);
	       	    list.appendChild(div);
           }
        }
    };
    xhttp.open("GET", "/autoratefe/FetchCarsServlet?makeId="+makeId+"&carTypeId="+carTypeId, true);
    xhttp.send();
    
}

function clearAll() {
	var clear= document.getElementsByClassName("inner-container-cars-info");
	if(clear!==undefined){
		var i=clear.length;
		while(i-->0){
			console.log(clear[i]);
			clear[i].remove();
		}
	}
}

function showUserDropdown() {
    var div= document.getElementsByClassName("user-container-dropdown")[0];
    if(div.style.display=="flex") {
        div.style.display="none";
    }
    else {
        div.style.display="flex";
    }
}
