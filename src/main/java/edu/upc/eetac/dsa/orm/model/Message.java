package edu.upc.eetac.dsa.orm.model;

public class Message {
    String id;
    String parentId;
    String username;
    String avatar;
    String message;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
    //Empty Constructor
    public Message(){}
    public Message(String username, String message, String parentId, String avatar) {
        this.username = username;
        this.parentId=parentId;
        this.message=message;
        this.avatar=avatar;

    }

    @Override
    public String toString() {
        return "Message [username= " + username + ", message= " + message + ", id=" +id + "]";
    }
}
