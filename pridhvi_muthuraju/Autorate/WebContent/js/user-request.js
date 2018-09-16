function showSpecs(request) {
    var specs=request.getElementsByClassName("user-request-specs")[0];
    if (specs.style.display === "flex") {
        specs.style.display = "none";
        request.style.backgroundColor="rgba(0, 0, 0, 0.7)";
    } else {
        request.style.backgroundColor="rgba(0, 0, 0, 0.9)";
        specs.style.display = "flex";
    }
}
