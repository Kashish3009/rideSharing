package com.geektrust.backend.services;

import java.util.List;
import com.geektrust.backend.Constants.constants;
import com.geektrust.backend.entities.Destination;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.DriverStatus;
import com.geektrust.backend.entities.Match;
import com.geektrust.backend.entities.Ride;
import com.geektrust.backend.entities.RideStatus;
import com.geektrust.backend.exceptions.DriverNotFoundException;
import com.geektrust.backend.exceptions.InvalidRideException;
import com.geektrust.backend.exceptions.MatchNotFoundException;
import com.geektrust.backend.exceptions.RideNotFoundException;
import com.geektrust.backend.repositories.IDriverRepository;
import com.geektrust.backend.repositories.IMatchRepository;
import com.geektrust.backend.repositories.IRideRepository;

public class RideService implements IRideService {

    private final IRideRepository rideRepository;
    private final IDriverRepository driverRepository;
    private final IMatchRepository matchRepository;
    
    public RideService (IRideRepository rideRepository, IDriverRepository driverRepository, IMatchRepository matchRepository) {
        this.rideRepository = rideRepository;
        this.driverRepository= driverRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public String startRide(String rideId, int driverNumber, String riderId) throws InvalidRideException, DriverNotFoundException, MatchNotFoundException {
      Match match = getMatch(riderId);
      List<String> drivers = getDrivers(match);
      if (rideExists(rideId) || checkDriverAvailablity(drivers, driverNumber)) {
        throw new InvalidRideException(constants.INVALID_RIDE);
      } 
      else {
        String driverStartingRide = drivers.get(driverNumber - constants.ONE);
        startingRide(driverStartingRide, rideId, riderId);
      }
     return rideId;
    }

    @Override
    public String stopRide(String rideId, int destinationXCoordinate, int destinationYCoordinate,int timeDuration) throws InvalidRideException, RideNotFoundException {
        Ride ride = getRide(rideId);
        if (!rideExists(rideId) || rideEnded(ride)) {
            throw new InvalidRideException(constants.INVALID_RIDE);
        }
        else {
            Ride rideToEnd = setDestinationCoordinates (ride, destinationXCoordinate,destinationYCoordinate, timeDuration);
            rideToEnd.setRideStatus(RideStatus.ENDED);
            saveRide(rideToEnd);
            setDriverAvailable(rideToEnd.getDriverId());
        }
        return rideId;
    }

    // Below are the helper compacted methods for startRide and StopRide methods defined above
    
    private void startingRide(String driverId, String rideId, String riderId) throws DriverNotFoundException {
         Driver driver = getDriver(driverId);
         if (driverNotEngaged(driver)) {
            changeDriverStatus(driver, DriverStatus.ENGAGED);
            Ride ride = new Ride (rideId, driverId, riderId, RideStatus.IN_PROGRESS);
            saveRide(ride);
         }
         else {
            System.out.println(constants.DRIVER_NOT_AVAILABLE);
         }
    }

    private boolean rideEnded(Ride ride) {
       if (ride.getRideStatus() == RideStatus.ENDED) {
           return true;
       }
       return false;
    }

    private boolean rideExists(String rideId){
        if (rideRepository.existsById(rideId)){
            return true;
        }
        return false;
    }

    public static boolean checkDriverAvailablity(List<String> drivers, int nthDriver){
        if (drivers.size() < nthDriver || drivers.size() == constants.ZERO){
            return true;
        }
        return false;
    }

    private boolean driverNotEngaged( Driver driver) {
        if (driver.getDriverStatus() != DriverStatus.ENGAGED){
            return true;
        }
        return false;
    }
    private void changeDriverStatus( Driver driver, DriverStatus status) {
        driver.setDriverStatus(status);
    }

    private Match getMatch(String riderId) throws MatchNotFoundException {
        Match match = matchRepository.findById(riderId).orElseThrow(() -> new MatchNotFoundException(constants.NO_DRIVERS_AVAILABLE));
        return match;
    }

    private Driver getDriver(String driverId) throws DriverNotFoundException{
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new DriverNotFoundException(constants.DRIVER_NOT_FOUND));   
        return driver;
    }

    private Ride getRide(String rideId) throws RideNotFoundException {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RideNotFoundException(constants.RIDE_NOT_FOUND));
        return ride;
    }

    private void setDriverAvailable(String driverId) {
        try {
            Driver driver = getDriver(driverId);
            changeDriverStatus(driver, DriverStatus.AVAILABLE);
            driverRepository.save(driver);
        } 
        catch (DriverNotFoundException e)  {
            System.out.println(constants.DRIVER_NOT_FOUND);
        }
    }
    private Ride saveRide(Ride ride){
        return rideRepository.save(ride);
    }
    private List<String> getDrivers(Match match){
        List<String> drivers = match.driverIdList();
        return drivers;
    }

    private Ride setDestinationCoordinates(Ride ride, int destinationXCoordinate,int destinationYCoordinate, int timeTaken ) throws InvalidRideException
    {
        if(timeTaken<constants.ZERO) {
            throw new InvalidRideException(constants.TIME_CANNOT_BE_NEGATIVE);
        }
        Destination destination = new Destination(destinationXCoordinate, destinationYCoordinate, timeTaken);
        ride.setDestination(destination);
        return ride;
    }
}
