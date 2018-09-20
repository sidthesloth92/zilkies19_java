document.getElementsByClassName("user-icon")[0].onclick = function () {
    document.getElementsByClassName("profile-menu")[0].classList.toggle("show-menu");
}

window.onload = function () {
    document.getElementsByClassName("nav-toggle_option")[0].classList.add("nav-toggle_option_chosen");
    document.getElementsByClassName("nav-toggle_option")[1].classList.remove("nav-toggle_option_chosen");

    document.getElementsByClassName("feature-box_food-tracker")[0].classList.add("show-feature");
    document.getElementsByClassName("feature-box_weight-tracker")[0].classList.remove("show-feature");

}

document.getElementsByClassName("nav-toggle_option")[0].onclick = function () {
    console.log("chosen 0");
    document.getElementsByClassName("nav-toggle_option")[0].classList.add("nav-toggle_option_chosen");
    document.getElementsByClassName("nav-toggle_option")[1].classList.remove("nav-toggle_option_chosen");

    document.getElementsByClassName("feature-box_food-tracker")[0].classList.add("show-feature");
    document.getElementsByClassName("feature-box_weight-tracker")[0].classList.remove("show-feature");

}

document.getElementsByClassName("nav-toggle_option")[1].onclick = function () {
    console.log("chosen 1");
    document.getElementsByClassName("nav-toggle_option")[1].classList.add("nav-toggle_option_chosen");
    document.getElementsByClassName("nav-toggle_option")[0].classList.remove("nav-toggle_option_chosen");

    document.getElementsByClassName("feature-box_weight-tracker")[0].classList.add("show-feature");
    document.getElementsByClassName("feature-box_food-tracker")[0].classList.remove("show-feature");

}