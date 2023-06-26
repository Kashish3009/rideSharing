package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.Constants.constants;
import com.geektrust.backend.dtos.BillGenerationDTOS;
import com.geektrust.backend.exceptions.InvalidRideException;
import com.geektrust.backend.exceptions.RideNotCompletedException;
import com.geektrust.backend.exceptions.RideNotFoundException;
import com.geektrust.backend.services.IBillService;

public class BillCommand implements ICommand {

    private final IBillService billService;

    public BillCommand (IBillService billService) {
        this.billService = billService;
    }
    @Override
    public void execute(List<String> tokens) {
       String rideId = tokens.get(constants.ONE);
    try {
        BillGenerationDTOS generatedBill = billService.generateBill(rideId);
        System.out.println(generatedBill.toString());
    } 
    catch (RideNotCompletedException e) {
        System.out.println(constants.RIDE_NOT_COMPLETED);
    }
    catch(InvalidRideException | RideNotFoundException ex) {
        System.out.println(constants.INVALID_RIDE);
    }
  }   
}
