function menu() {
    var x = document.getElementById("nav-bar");
    if (x.className === "body__left-panel") {
        //console.log("yes");
        x.className += " responsive";
    } else {
        //console.log("yes");
        x.className = "body__left-panel";
    }
}
