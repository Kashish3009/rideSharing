package com.geektrust.backend.entities;

public class Bill extends BaseEntity {
    
    private final String rideId;
    private final String driverId;
    private final double amount;

    public Bill (String rideId, String driverId, double amount){
        this.rideId = rideId;
        this.driverId = driverId;
        this.amount = amount;
    }

    public String getRideId (){
        return rideId;
    }
    public String getDriverId(){
        return driverId;
    }
    public double getAmount(){
        return amount;
    }
}
