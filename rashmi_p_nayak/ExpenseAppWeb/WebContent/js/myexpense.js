function displaySearchInputField() {
    console.log("aa");
    var searchInputField = document.getElementsByClassName("search-option__input")[0];
    searchInputField.classList.toggle("search-option__input-visible");
}

/*mODAL*/
var modal = document.getElementById("addExpenseModal");
var btn = document.getElementById("addExpenseBtn");
var span = document.getElementsByClassName("close")[0];
btn.onclick = function() {
    modal.style.display = "block";
}
span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
} 

function changeIcon(value) {
    var img = document.getElementsByClassName("expense-type-icon-container__icon")[0];
    img.setAttribute("src","../../ExpenseAppWeb/images/"+value+".png");
}