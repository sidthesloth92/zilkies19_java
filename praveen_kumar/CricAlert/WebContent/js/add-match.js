var modals = document.getElementsByClassName("details__match-form__modal");

var btns = document.getElementsByClassName("details__match-form__team-lineup");

var spans = document.getElementsByClassName("close");

btns[0].onclick = function() {
    modals[0].style.display = "block";
};

spans[0].onclick = function() {
    modals[0].style.display = "none";
};

btns[1].onclick = function() {
    modals[1].style.display = "block";
};

spans[1].onclick = function() {
    modals[1].style.display = "none";
};

// window.onclick = function(event) {
//     if (event.target == modal) {
//         modal.style.display = "none";
//     }
// };