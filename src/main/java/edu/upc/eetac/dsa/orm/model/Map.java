package edu.upc.eetac.dsa.orm.model;

public class Map {

    //Basic values
    private String id;
    private int sizeX;
    private int sizeY;
    private String designGrid;

    //Empty Constructor
    public Map() {}

    //Constructor
    public Map(String id, int sizeX, int sizeY, String designGrid) {
        this.id = id;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.designGrid = designGrid;
    }

    //Setters and Getters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getSizeX() {
        return sizeX;
    }
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }
    public int getSizeY() {
        return sizeY;
    }
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
    public String getDesignGrid() {
        return designGrid;
    }
    public void setDesignGrid(String designGrid) {
        this.designGrid = designGrid;
    }



}
