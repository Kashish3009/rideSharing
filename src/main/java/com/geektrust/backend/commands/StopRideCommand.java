package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.Constants.constants;
import com.geektrust.backend.exceptions.InvalidRideException;
import com.geektrust.backend.exceptions.RideNotFoundException;
import com.geektrust.backend.services.IRideService;

public class StopRideCommand implements ICommand {

    private final IRideService rideService;

    public StopRideCommand(IRideService rideService){
        this.rideService=rideService;
    }
    
    @Override
    public void execute(List<String> tokens) {
        String rideId = tokens.get(constants.ONE);
        int destinationXCoordinate = Integer.parseInt(tokens.get(constants.TWO));
        int destinationYCoordinate = Integer.parseInt(tokens.get(constants.THREE));
        int timeDurationInMinutes = Integer.parseInt(tokens.get(constants.FOUR));
        try 
        {
            String stoppedRideId = rideService.stopRide(rideId, destinationXCoordinate, destinationYCoordinate, timeDurationInMinutes);
            System.out.println(constants.RIDE_STOPPED + stoppedRideId);
        } 
        catch (InvalidRideException | RideNotFoundException ex) {
           System.out.println(constants.INVALID_RIDE);
        }
    }
}
