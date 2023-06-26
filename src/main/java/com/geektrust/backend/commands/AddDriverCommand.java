package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.Constants.constants;
import com.geektrust.backend.exceptions.DriverAlreadyPresentException;
import com.geektrust.backend.services.IDriverService;

public class AddDriverCommand implements ICommand {

    private final IDriverService driverServivce;

    public AddDriverCommand(IDriverService driverService){
      this.driverServivce = driverService;
    }
    
    @Override
    public void execute(List<String> tokens) {
       String driverId = tokens.get(constants.ONE);
       int xCoordinate = Integer.parseInt(tokens.get(constants.TWO));
       int yCoordinate = Integer.parseInt(tokens.get(constants.THREE));
       try {
         driverServivce.addDriver(driverId, xCoordinate, yCoordinate);
       } 
       catch (DriverAlreadyPresentException ex) {
        System.out.println(constants.DRIVER_WITH_ID_PRESENT);
       }
    }
}
