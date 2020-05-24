package edu.upc.eetac.dsa.orm.model;

public class Map {

    private String id;
    private String name;
    private int level; //Indicates the level and the precedence of use in Unity
    private String type1MapGrid;
    private String type2PlayerPosition;
    private String type2EntityPositions;//All of the Object and Enemy Position

    //Empty Constructor
    public Map() {}
    //Complete Constructor

    public Map(String id, String name, int level, String type1MapGrid, String type2PlayerPosition, String type2EntityPositions) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.type1MapGrid = type1MapGrid;
        this.type2PlayerPosition = type2PlayerPosition;
        this.type2EntityPositions = type2EntityPositions;
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType1MapGrid() {
        return type1MapGrid;
    }

    public void setType1MapGrid(String type1MapGrid) {
        this.type1MapGrid = type1MapGrid;
    }

    public String getType2PlayerPosition() {
        return type2PlayerPosition;
    }

    public void setType2PlayerPosition(String type2PlayerPosition) {
        this.type2PlayerPosition = type2PlayerPosition;
    }

    public String getType2EntityPositions() {
        return type2EntityPositions;
    }

    public void setType2EntityPositions(String type2EntityPositions) {
        this.type2EntityPositions = type2EntityPositions;
    }
}
