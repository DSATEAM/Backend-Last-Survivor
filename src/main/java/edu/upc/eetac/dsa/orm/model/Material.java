package edu.upc.eetac.dsa.orm.model;

import java.util.List;

public class Material {

    private String ID="";
    private String parentID="";
    private String name;
    private String description;
    private int quantity;
    //Empty Constructor
    public Material() {
    }
    public Material( String name, String description, int quantity) {
        //ID WILL BE ASSIGNED WHEN SAVED IN DB
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getParentID() { return parentID;}

    public void setParentID(String parentID) {this.parentID = parentID;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ID "+ this.getID() +" name: "+ this.name;
    }
}
