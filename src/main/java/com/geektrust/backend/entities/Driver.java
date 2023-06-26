package com.geektrust.backend.entities;

public class Driver extends BaseEntity {
    private final int xCoordinate;
    private final int yCoordinate;
    private DriverStatus driverStatus;

    public Driver(String id, int xCoordinate, int yCoordinate, DriverStatus driverStatus)
    {
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.driverStatus=driverStatus;
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
    public DriverStatus getDriverStatus(){
        return driverStatus;
    }
    public void setDriverStatus(DriverStatus driverStatus){
       if(driverStatus!=null)
       this.driverStatus = driverStatus;
    }
    public boolean isDriverEngaged () {
        return this.getDriverStatus()==DriverStatus.ENGAGED;
    }
}
