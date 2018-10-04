function expandTeam(element) {
    if (element.classList.contains("team-a")) {
        var info = document.querySelector(".team-a__container-2");
        var title = document.querySelector(".team-a__container-1");
        info.classList.toggle("team__container-2--active");
        title.classList.toggle("team__container-1--active");
    } else {
        var info = document.querySelector(".team-b__container-2");
        var title = document.querySelector(".team-b__container-1");
        info.classList.toggle("team__container-2--active");
        title.classList.toggle("team__container-1--active");
    }
}