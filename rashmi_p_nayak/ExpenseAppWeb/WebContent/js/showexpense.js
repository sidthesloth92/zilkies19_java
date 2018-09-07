// document.addEventListener("click", function(e){
//     e = e || window.event;
//     var target = e.target || e.srcElement;
//     console.log(e);
//     console.log(target);
//     target.innerHTML = "in";
// });

function showExpenseDetails(event) {
    var previewCards = document.getElementsByClassName("expense-list-container__expense-preview-card");
    for (var i = 0; i < previewCards.length; i++) {
        if (event.target != previewCards[i])
            previewCards[i].classList.remove("expense-list-container__expense-preview-card-reveal")
    }
    event.target.classList.toggle("expense-list-container__expense-preview-card-reveal");
}