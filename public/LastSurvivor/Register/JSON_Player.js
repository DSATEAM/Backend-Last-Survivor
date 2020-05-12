var getData = function (){
    var username = document.getElementById("inputUsername").value;
    var password = document.getElementById("inputPassword").value;
    var obj = {
        "id": "string",
        "username": username,
        "password": password,
        "gamesPlayed": 0,
        "kills": 0,
        "deaths": 0,
        "experience": 0,
        "wins": 0,
        "listItems": [
            {
                "id": "string",
                "parentId": "string",
                "name": "string",
                "type": "string",
                "rarity": "string",
                "description": "string",
                "listMaterials": [
                    {
                        "id": "string",
                        "parentId": "string",
                        "name": "string",
                        "description": "string",
                        "quantity": 0
                    }
                ],
                "offense": 0,
                "defense": 0
            }
        ],
        "listMaterials": [
            {
                "id": "string",
                "parentId": "string",
                "name": "string",
                "description": "string",
                "quantity": 0
            }
        ]
    }

    $("#res").text('' + obj);
}