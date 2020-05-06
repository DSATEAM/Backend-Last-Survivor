package edu.upc.eetac.dsa.orm.model;

public class Enemy {

    //Basic values
    private String id;
    private String name;
    private String description;
    private int power;
    private int health;

    //Empty Constructor
    public Enemy() { }

    //Constructor
    public Enemy(String name,String description, int power, int health){
        this.name=name;
        this.description=description;
        this.power=power;
        this.health=health;
    }

    //Setters and Getters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Enemy [ID= " + this.getId() + ", name= " + this.getName() + ", description = " + this.getDescription() + ", power= " + this.getPower() + ", health= " + this.getHealth() + "]";
    }
}
