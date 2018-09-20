var slideIndex = 0;
showSlides();

function showSlides() {
    var i;
    var slides = document.getElementsByClassName("slides");
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none"; 
    }
    if (slideIndex >= slides.length) {slideIndex = 0} 
    slides[slideIndex].style.display = "block"; 
    slideIndex++;
    setTimeout(showSlides, 2000); // Change image every 2 seconds
}