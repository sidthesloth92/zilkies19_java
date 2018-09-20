// var menuIcon = document.getElementById("nav__left-option");
// console.log(menuIcon);
// menuIcon.onclick = function () {
//     console.log("sdfhlsh");
//     // console.log("Inside Function ");
//     alert("Hello");
//     // console.log("Iside Somethings ");
//     var outerNav = document.getElementById("outer-nav-section");
//     outerNav.classList.add("responsive");
// };


function classToggle() {
    const navs = document.querySelectorAll(".Navbar__Items");

    navs.forEach(nav => nav.classList.toggle("Navbar__ToggleShow"));
}

document.querySelector(".Navbar__Link-toggle")
    .addEventListener("click", classToggle);

// closeBar.onclick = function () {
// document.getElementById("side-nav-id").style.width = "0";
// document.getElementById("main-body-section-id").style.marginLeft = "0";
// };

// Function to be executed on window to load to display one active tab
window.onload = function () {
    var tabcontent = document.getElementsByClassName("tabcontent");
    for (var i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    document.getElementById(tabcontent[0].id).style.display = "block";
};


function openTab(evt, tabFunction) {
    var i, tabcontent;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    document.getElementById(tabFunction).style.display = "block";
}