package edu.upc.eetac.dsa.orm.model;

import java.util.List;

public class Player implements Comparable<Player> {
    private String id;
    private String username;
    private String password;
    private int gamesPlayed;
    private int kills;
    private int experience;
    private int credits;
    //TODO Recursive Mapping of Objects Item and Material
    List<Item> listItems;
    //Empty Constructor
    public Player(){}
    public Player(String username, String password, int gamesPlayed, int kills, int experience, int credits) {
        this.username = username;
        this.password = password;
        this.gamesPlayed = gamesPlayed;
        this.kills = kills;
        this.experience = experience;
        this.credits = credits;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public List<Item> getListItems() {
        return listItems;
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {this.credits = credits;}

    @Override
    public String toString() {
        return "Player [username= " + username + ", password= " + password + ", id=" +id +"]";
    }

    @Override
    public int compareTo(Player o) {
        int expResult=o.getExperience()-this.getExperience();
        if (expResult!=0){
            return expResult;
        }
        int killsResult=o.getKills()-this.getKills();
        if (killsResult!=0){
            return killsResult;
        }
        return this.getGamesPlayed()-o.getGamesPlayed();
    }
}
