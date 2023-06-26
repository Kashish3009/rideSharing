package com.geektrust.backend.services;

import com.geektrust.backend.dtos.BillGenerationDTOS;
import com.geektrust.backend.exceptions.InvalidRideException;
import com.geektrust.backend.exceptions.RideNotCompletedException;
import com.geektrust.backend.exceptions.RideNotFoundException;

public interface IBillService {
    public BillGenerationDTOS generateBill (String rideId) throws  RideNotCompletedException, InvalidRideException, RideNotFoundException ;
}
