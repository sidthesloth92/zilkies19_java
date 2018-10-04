var modal = document.querySelector(".details__form__modal");

var btn = document.querySelector(".details__form__add-players");

var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
    modal.style.display = "flex";
};

span.onclick = function() {
    modal.style.display = "none";
};

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};