function validate() {
    var empId = document.getElementById("emp-id");
    var password = document.getElementById("password");
    if(isNaN(empId)){
        return false;
    }
    return true;
}