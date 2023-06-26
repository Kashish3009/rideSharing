package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.Constants.constants;
import com.geektrust.backend.exceptions.DriverNotFoundException;
import com.geektrust.backend.exceptions.InvalidRideException;
import com.geektrust.backend.exceptions.MatchNotFoundException;
import com.geektrust.backend.services.IRideService;

public class StartRideCommand implements ICommand {

    private final IRideService rideService;

    public StartRideCommand (IRideService rideService){
        this.rideService=rideService;
    }
    
    @Override
    public void execute(List<String> tokens) {
        String rideId = tokens.get(constants.ONE);
        int driverNumber = Integer.parseInt(tokens.get(constants.TWO));
        String riderId = tokens.get(constants.THREE);
        try 
        {
            String startedRideId = rideService.startRide(rideId, driverNumber, riderId);
            System.out.println(constants.RIDE_STARTED + startedRideId);
        } 
        catch (InvalidRideException | DriverNotFoundException | MatchNotFoundException e) {
           System.out.println(constants.INVALID_RIDE);
        }
    }
}
