package com.geektrust.backend.services;

import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.exceptions.RiderAlreadyPresentException;

public interface IRiderService {
   public Rider addRider(String riderId, int xCoordinate, int yCoordinate) throws RiderAlreadyPresentException; 
}
