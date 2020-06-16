function getData(itemId,parentId){
    var obj = {
        "id": itemId,
        "parentId": parentId
    }
    $("#res").text('' + obj)
    return obj;
}