package com.geektrust.backend.services;

import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.DriverStatus;
import com.geektrust.backend.exceptions.DriverAlreadyPresentException;
import com.geektrust.backend.repositories.IDriverRepository;

public class DriverService implements IDriverService{
    private final IDriverRepository driverRepository;
    public DriverService(IDriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver addDriver(String driverId, int xCoordinate, int yCoordinate) throws DriverAlreadyPresentException{
        if (driverExists(driverId)) {
            throw new DriverAlreadyPresentException();
        }
        Driver driver = new Driver(driverId, xCoordinate, yCoordinate, DriverStatus.AVAILABLE);
        return driverRepository.save(driver);
    }

    // compacted helper method for adding driver functionality defined above
    private boolean driverExists(String driverId){
        if (driverRepository.existsById(driverId)){
            return true;
        }
        return false;
    }
}
