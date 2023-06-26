package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.Constants.constants;
import com.geektrust.backend.exceptions.RiderAlreadyPresentException;
import com.geektrust.backend.services.IRiderService;

public class AddRiderCommand implements ICommand {

    private final IRiderService riderService;

    public AddRiderCommand(IRiderService riderService) {
      this.riderService = riderService;
    }

    @Override
    public void execute(List<String> tokens) {
        String riderId = tokens.get(constants.ONE);
        int xCoordinate = Integer.parseInt(tokens.get(constants.TWO));
        int yCoordinate = Integer.parseInt(tokens.get(constants.THREE));
        try {
          riderService.addRider(riderId, xCoordinate, yCoordinate);
        } 
        catch (RiderAlreadyPresentException ex) {
         System.out.println(constants.RIDER_WITH_ID_PRESENT);
        } 
    }
    
}
