function getData(){
    var username = document.getElementById("inputUsername").value;
    var password = document.getElementById("inputPassword").value;
    var obj = {
        "username": username,
        "password": password
    }

    $("#res").text('' + obj)
    return obj;
}