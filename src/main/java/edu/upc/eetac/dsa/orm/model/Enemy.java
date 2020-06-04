package edu.upc.eetac.dsa.orm.model;

public class Enemy {

    //Basic values
    private String id;
    private String avatar;
    private String name;
    private int experience;
    private String description;
    private int damage;
    private int health;

    public Enemy(String name,  String description,int experience, int damage, int health) {
        this.name = name;
        this.experience = experience;
        this.description = description;
        this.damage = damage;
        this.health = health;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    //Empty Constructor
    public Enemy() { }


    @Override
    public String toString() {
        return "Enemy [ID= " + this.getId() + ", name= " + this.getName() + ", description = " + this.getDescription() + ", damage= " + this.getDamage() + ", health= " + this.getHealth() + "]";
    }
}
