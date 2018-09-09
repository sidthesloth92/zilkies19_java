function validateSignupForm() {
    var username = document.forms["signup-form"]["username"].value;
    var emailId = document.forms["signup-form"]["email-id"].value;
    var password = document.forms["signup-form"]["password"].value;
    var confirmPassword = document.forms["signup-form"]["confirm-password"].value;
    if (username == "") {
        alert("Username must be filled out");
        return false;
    }

    if (emailId == "") {
        alert("EmailID must be filled out");
        return false;
    }

    if (password == "") {
        alert("Password must be filled out");
        return false;
    }

    if (confirmPassword == "") {
        alert("Password must be filled out");
        return false;
    }

    if(password!==confirmPassword){
        alert("Passwords did not match!");
        return false;
    }
    
    var re = /\S+@\S+\.\S+/;
    if(!re.test(emailId)) {
        alert("Enter a valid Email ID");
        return false;
    }
    return true;
}
