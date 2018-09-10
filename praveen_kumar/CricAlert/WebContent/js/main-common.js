function expandSearch() {
    var searchBtn = document.querySelector(".footer__search");
    var addBtn = document.querySelector(".footer__add-item");
    var searchBar = document.querySelector(".footer__search-bar");
    var minimizeIcon = document.querySelector(".minimize");

    searchBtn.style.opacity = "0";
    addBtn.style.opacity = "0";
    searchBar.style.visibility = "visible";
    searchBar.style.opacity = "1";
    setTimeout(function hideSearch() {
        searchBtn.style.visibility = "hidden";
        addBtn.style.visibility = "hidden";
    }, 300);
    minimizeIcon.style.display = "inline-block";
}

function minimizeSearch() {
    var searchBtn = document.querySelector(".footer__search");
    var addBtn = document.querySelector(".footer__add-item");
    var searchBar = document.querySelector(".footer__search-bar");
    var minimizeIcon = document.querySelector(".minimize");

    searchBtn.style.visibility = "visible";
    addBtn.style.visibility = "visible";
    searchBtn.style.opacity = "1";
    addBtn.style.opacity = "1";
    searchBar.style.opacity = "0";
    setTimeout(function hideSearch() {
        searchBar.style.visibility = "hidden";
    }, 300);
    minimizeIcon.style.display = "none";
}

function sideBarClick() {
    var sideBar = document.querySelector(".side-bar").classList.toggle("active");
}

function allowDrop(ev, el) {
    ev.preventDefault();
    el.style.height = "120px";
    el.style.width = "120px";
}

function dragStart(ev) {
    ev.dataTransfer.setData("id", ev.target.id);
    var search = document.getElementsByClassName("footer__search")[0];
    var addItem = document.getElementsByClassName("footer__add-item")[0];
    var editItem = document.getElementsByClassName("footer__edit")[0];
    var deleteItem = document.getElementsByClassName("footer__delete")[0];
    editItem.style.display = "inline-block";
    deleteItem.style.display = "inline-block";
    search.style.display = "none";
    addItem.style.display = "none";
}

function dragStop(ev) {
    var search = document.getElementsByClassName("footer__search")[0];
    var addItem = document.getElementsByClassName("footer__add-item")[0];
    var editItem = document.getElementsByClassName("footer__edit")[0];
    var deleteItem = document.getElementsByClassName("footer__delete")[0];
    
    editItem.style.height = "100px";
    deleteItem.style.height = "100px";
    editItem.style.width = "100px";
    deleteItem.style.width = "100px";

    editItem.style.display = "none";
    deleteItem.style.display = "none";
    search.style.display = "inline-block";
    addItem.style.display = "inline-block";
}