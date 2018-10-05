var dropImage = document.getElementById('drop-image');

dropImage.addEventListener("click", function() {
    var dropMenu = document.getElementById('drop-menu');
    console.log("clicked");
    if(dropMenu.style.display == 'none') {
        dropMenu.style.display = 'flex';
    } else {
        dropMenu.style.display = 'none';
    }
});