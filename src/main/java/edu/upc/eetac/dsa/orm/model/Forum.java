package edu.upc.eetac.dsa.orm.model;

import java.util.List;

public class Forum {
    private String id;
    private String admin;
    private String name;
    private String avatar;
    private List<Message> listMessages;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Message> getListMessages() { return this.listMessages; }
    public void setListMessages(List<Message> listMessages) { this.listMessages = listMessages; }
    public void addMessage(Message message) { this.listMessages.add(message); }

    //Empty Constructor
    public Forum(){}
    public Forum(String name) {
        this.name=name;
    }

    @Override
    public String toString() {
        return "Forum [name= " + this.name + ", id=" + this.id + ",creator="+ this.admin+"]";
    }
}
