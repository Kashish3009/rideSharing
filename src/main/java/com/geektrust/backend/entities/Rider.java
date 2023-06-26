package com.geektrust.backend.entities;

public class Rider extends BaseEntity {
    private int xCoordinate;
    private int yCoordinate;

    public Rider(){
        
    }
    public Rider(String id, int xCoordinate, int yCoordinate)
    {
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
    public String getId(){
        return id;
    }
    public int getXCoordinate(){
        return xCoordinate;
    }
    public int getYCoordinate(){
        return yCoordinate;
    }
}

