function getMakes() {
    var list= document.getElementsByClassName("inner-container-main-list")[0];
    list.innerHTML="";
    var divs,text;
    var i=100;
    while(i-->0) {
        divs= document.createElement("div");
        text = document.createTextNode("BMW");
        divs.appendChild(text);
        divs.classList.add("button--hover");
        divs.setAttribute("onclick", "getTypes()");
        list.appendChild(divs);
    }
}

function getTypes() {
    var list= document.getElementsByClassName("inner-container-main-list")[0];
    list.innerHTML="";
    var divs,text;
    var i=100;
    while(i-->0) {
        divs= document.createElement("div");
        text = document.createTextNode("Sedan");
        divs.appendChild(text);
        divs.classList.add("button--hover");
        list.appendChild(divs);
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
