function showSpecs(request) {
    var specs=request.getElementsByClassName("user-request-specs")[0];
    if (specs.style.display === "flex") {
        specs.style.display = "none";
        request.style.backgroundColor="rgba(0, 0, 0, 0.7)";
    } else {
        request.style.backgroundColor="rgba(0, 0, 0, 0.9)";
        specs.style.display = "flex";
    }
}
String.fromHtmlEntities = function(string) {
    return (string+"").replace(/&#\d+;/gm,function(s) {
        return String.fromCharCode(s.match(/\d+/gm)[0]);
    })
};

function getSpecs(request) {
	var specs=request.getElementsByClassName("user-request-specs")[0];
	specs.innerHTML="";
	var p;
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        	   var spec= JSON.parse(this.responseText);
        	   p= document.createElement("p");
               p.appendChild(document.createTextNode("Car Name: "+spec["carName"]));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("ABS: "+spec["abs"]));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("Airbag: "+spec["airbag"]));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("Cylinders: "+spec["cylinder"]));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("Displacement: "+spec["displacement"]+"cc"));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("Engine Type: "+spec["engineType"]));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("Fuel Capacity: "+spec["fuelCapacity"]+"litres"));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("Kerb Weight: "+spec["kerbWeight"]+"kg"));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("Power: "+spec["power"]+"BHP"));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("Torque: "+spec["torque"]+"Nm"));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("Transmission: "+spec["transmission"]));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("Wheelbase: "+spec["wheelbase"]+"mm"));
               specs.appendChild(p);
               
               p= document.createElement("p");
               p.appendChild(document.createTextNode("Price: "+String.fromHtmlEntities("&#8377;")+spec["price"]));
               specs.appendChild(p);
           
        }
    };
    xhttp.open("GET", "/Autorate/FetchCarSpecsServlet?id="+specs.id, true);
    xhttp.send();
}

function approveRequest(request) {
	var req=request.parentNode.parentNode;
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        	   req.remove();
        }
    };
    xhttp.open("GET", "/Autorate/ApproveRequestServlet?carId="+req.getElementsByClassName("user-request-specs")[0].id+"&requestId="+req.getElementsByClassName("user-request-specs")[0].parentNode.id, true);
    xhttp.send();
}

function declineRequest(request) {
	var req=request.parentNode.parentNode;
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        	   req.remove();
        }
    };
    xhttp.open("GET", "/Autorate/DeclineRequestServlet?carId="+req.getElementsByClassName("user-request-specs")[0].id+"&requestId="+req.getElementsByClassName("user-request-specs")[0].parentNode.id, true);
    xhttp.send();
}
