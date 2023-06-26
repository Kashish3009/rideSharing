package com.geektrust.backend.entities;

public class Destination {
    private int destinationXCoordinate;
    private int destinationYCoordinate;
    private int timeTaken;

    public Destination(int destinationXCoordinate,int destinationYCoordinate, int timeTaken){
        this.destinationXCoordinate = destinationXCoordinate;
        this.destinationYCoordinate = destinationYCoordinate;
        this.timeTaken = timeTaken;
    }
    public Destination (){}

    public void setDestinationXCoordinate(int destinationXCoordinate){
        if(destinationXCoordinate>Integer.MIN_VALUE || destinationXCoordinate<Integer.MAX_VALUE)
        this.destinationXCoordinate= destinationXCoordinate;
    }
    public void setDestinationYCoordinate(int destinationYCoordinate) {
        if(destinationYCoordinate > Integer.MIN_VALUE || destinationYCoordinate < Integer.MAX_VALUE)
        this.destinationYCoordinate= destinationYCoordinate;
    }
    public int getDestinationXCoordinate(){
        return destinationXCoordinate;
    }
    public int getDestinationYCoordinate(){
        return destinationYCoordinate;
    }
    public int getTimeTaken(){
        return timeTaken;
    }
    public void setTimeTaken(int timeDuration){
        if(timeDuration>0)
        this.timeTaken =timeDuration;
    }
}
