function getData(){
    var username = document.getElementById("inputUsername").value;
    var password = document.getElementById("inputPassword").value;
    var obj = {
        "id": "string",
        "username": username,
        "password": password
    }

    $("#res").text('' + obj);
    return obj;
}