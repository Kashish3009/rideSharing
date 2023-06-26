package com.geektrust.backend.entities;

public class Ride extends BaseEntity {
    private String riderId;
    private RideStatus rideStatus;
    private Destination destination;
    private String driverId;

    public Ride (String rideId, String driverId, String riderId,RideStatus rideStatus){
        this.driverId = driverId;
        this.id = rideId;
        this.rideStatus = rideStatus;
        this.riderId =riderId;
    }
    public Ride(String rideId,Destination destination,RideStatus rideStatus){
        this.riderId = rideId;
        this.destination = destination;
        this.rideStatus = rideStatus;
    }
    public Destination getDestination(){
        return destination;
    }
    public void setDestination(Destination destination){
        if (destination!=null)
         this.destination = destination;
    }
    
    public String getRiderId(){
        return riderId;
    }
    public RideStatus getRideStatus(){
        return rideStatus;
    }
    public void setRideStatus(RideStatus status){
        if(status!=null)
          this.rideStatus = status;
    }
    public String getDriverId(){
        return driverId;
    }
}
