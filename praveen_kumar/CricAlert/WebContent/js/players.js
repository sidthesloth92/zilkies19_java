function expandCard(element) {
    var modal = document.querySelector(".content__modal");

    var span = document.getElementsByClassName("close")[0];

    modal.style.display = "flex";

    span.onclick = function () {
        modal.style.display = "none";
    };

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
}