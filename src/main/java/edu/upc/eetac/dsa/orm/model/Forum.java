package edu.upc.eetac.dsa.orm.model;

import java.util.List;

public class Forum {
    private String id;
    private String parentId;
    private String name;
    private List<Message> listMessages;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getParentId() { return this.parentId; }
    public void setParentId(String parentId) { this.parentId = parentId; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
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
        return "Forum [name= " + this.name + ", id=" + this.id + ",creator="+ this.parentId+"]";
    }
}
