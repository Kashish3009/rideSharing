package com.geektrust.backend.services;

import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.exceptions.DriverAlreadyPresentException;

public interface IDriverService {
    public Driver addDriver(String driverId, int xCoordinate, int yCoordinate) throws DriverAlreadyPresentException;
}
