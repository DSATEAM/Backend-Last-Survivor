var getData = function (){
    var username = document.getElementById("inputUsername").value;
    var password = document.getElementById("inputPassword").value;
    var json = {
        "id": "string",
        "username": username,
        "password": password
    }
    return json;
}