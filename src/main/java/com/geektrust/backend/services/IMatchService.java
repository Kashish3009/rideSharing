package com.geektrust.backend.services;

import com.geektrust.backend.entities.Match;
import com.geektrust.backend.exceptions.DriverNotFoundException;

public interface IMatchService {
  public Match matchRiderWithDrivers(String riderId) throws DriverNotFoundException;
}