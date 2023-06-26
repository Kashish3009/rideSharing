package com.geektrust.backend.services;

import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.exceptions.RiderAlreadyPresentException;
import com.geektrust.backend.repositories.IRiderRepository;

public class RiderService implements IRiderService {

    private final IRiderRepository riderRepository;

    public RiderService(IRiderRepository riderRepository){
        this.riderRepository = riderRepository;
    }

    @Override
    public Rider addRider(String riderId, int xCoordinate, int yCoordinate) throws RiderAlreadyPresentException{
      if(riderExists(riderId)) {
         throw new RiderAlreadyPresentException();
      }
      Rider rider = new Rider (riderId, xCoordinate, yCoordinate);
      return riderRepository.save(rider);
    }

    // helper compacted method for adding Rider functionality
    private boolean riderExists(String riderId) {
      if(riderRepository.existsById(riderId)) {
        return true;
      }
      return false;
    }
    
}
