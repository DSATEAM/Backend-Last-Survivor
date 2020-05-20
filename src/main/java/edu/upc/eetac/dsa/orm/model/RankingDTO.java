package edu.upc.eetac.dsa.orm.model;

public class RankingDTO extends Player {
    private int gamesPlayed;
    private int kills;
    private int experience;
    private int credits;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {this.credits = credits;}


}
