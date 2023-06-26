package com.geektrust.backend.commands;

import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.Constants.constants;
import com.geektrust.backend.exceptions.DriverNotFoundException;
import com.geektrust.backend.services.IMatchService;

public class MatchCommand implements ICommand {

    private final IMatchService matchService;

    public MatchCommand (IMatchService matchService){
        this.matchService = matchService;
    }
    @Override
    public void execute(List<String> tokens) {
        String riderId = tokens.get(constants.ONE);
        try {
             List<String> driversList = matchService.matchRiderWithDrivers(riderId).driverIdList();
             System.out.println(constants.DRIVERS_MATCHED + driversList.stream().collect(Collectors.joining(constants.SINGLE_SPACE_STRING)));
        } 
        catch (DriverNotFoundException ex) {
             System.out.println(constants.NO_DRIVERS_AVAILABLE);
        }
    }
}
