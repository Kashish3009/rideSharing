package com.geektrust.backend.services;

import com.geektrust.backend.exceptions.DriverNotFoundException;
import com.geektrust.backend.exceptions.InvalidRideException;
import com.geektrust.backend.exceptions.MatchNotFoundException;
import com.geektrust.backend.exceptions.RideNotFoundException;

public interface IRideService {
    public String startRide (String rideId, int driverNumber, String riderId ) throws InvalidRideException, DriverNotFoundException, MatchNotFoundException;
    public String stopRide (String rideId, int destinationXCoordinate, 
    int destinationYCoordinate, int timeDuration ) throws InvalidRideException, RideNotFoundException;
}