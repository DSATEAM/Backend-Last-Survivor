function getData(ids,parentids){
    var obj = {
        "id": ids,
        "parentId": parentids
    }
    $("#res").text('' + obj)
    return obj;
}