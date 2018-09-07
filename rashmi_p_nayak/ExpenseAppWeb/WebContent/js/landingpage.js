function signupOnclick() {
    var signupContainer = document.querySelector(".signup-container").classList.add("reveal");
    var loginCircleButton = document.querySelector(".login-circle-button").classList.add("moveup");
}

function loginOnclick() {
    var signupContainer = document.querySelector(".signup-container").classList.remove("reveal");
    var loginCircleButton = document.querySelector(".login-circle-button").classList.remove("moveup");
}

function formValidate() {
    console.log("In func");
    var username = document.forms["signup-form"]["username"].value;
    if (username == null || username == "") {
        alert("Enter a username!");
        return false;
    }

    var emailid = document.forms["signup-form"]["email-id"].value;
    if (!/^[a-zA-Z0-9_+&*-]+(?:\." + "[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-z" + "A-Z]{2,7}$/.test(emailid) || emailid == null || emailid == "") {
        alert("Enter a valid email-id!");
        return false;
    }

    var password = document.forms["signup-form"]["password"].value;
    var confirmPassword = document.forms["signup-form"]["confirm-password"].value;
    if (password == null || password == "" || confirmPassword == null || confirmPassword == "") {
        alert("Enter a password!");
        return false;
    }
    if (password != confirmPassword) {
        alert("Passwords dont match!");
        return false;
    }
    return true;
}