function showContent(elementID, buttonID) {
    var button = document.getElementById(buttonID);
    var element = document.getElementById(elementID);
    // console.log(element.style.maxHeight);
    if (element.style.maxHeight == "0px") {
        element.style.maxHeight = element.scrollHeight + "px";
        button.innerHTML = "<i class='fas fa-sort-up'></i>";
        window.scrollBy(0, element.scrollHeight);
    } else {
        element.style.maxHeight = "0px";
        button.innerHTML = "<i class='fas fa-sort-down'></i>";
    }

}

function scrollWindow() {
    window.scrollBy(0, document.getElementById("bmi-bmr-wrapper").scrollHeight);
}