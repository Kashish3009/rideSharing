package com.geektrust.backend.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import com.geektrust.backend.Constants.constants;
import com.geektrust.backend.Utils.Util;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.DriverStatus;
import com.geektrust.backend.entities.Match;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.exceptions.DriverNotFoundException;
import com.geektrust.backend.exceptions.RiderNotFoundException;
import com.geektrust.backend.repositories.IDriverRepository;
import com.geektrust.backend.repositories.IMatchRepository;
import com.geektrust.backend.repositories.IRiderRepository;

public class MatchService implements IMatchService{

    private final IRiderRepository riderRepository;
    private final IDriverRepository driverRepository;
    private final IMatchRepository matchRepository;

    public MatchService( IRiderRepository riderRepository, IDriverRepository driverRepository, IMatchRepository matchRepository) {
      this.riderRepository = riderRepository;
      this.driverRepository = driverRepository;
      this.matchRepository = matchRepository;
    }
    
    @Override
    public Match matchRiderWithDrivers(String riderId) throws DriverNotFoundException, RiderNotFoundException {
        Rider rider = new Rider();
        if (riderExists(riderId)) {
          rider = getRider(riderId);
        }
        List<Driver> drivers = getAllDrivers();
        if(!driversAvailable(drivers)) {
          throw new DriverNotFoundException(constants.NO_DRIVERS_AVAILABLE);
        }
        HashMap<String,Double> mapDistances = getNearestDriversWithDistance(drivers,rider.getXCoordinate(), rider.getYCoordinate());
        List<Entry<String, Double>> entryList = Util.sortEntryList(mapDistances);
        List<String> matchedListOfDrivers = getMatchedDriversIn5KmsRange(entryList);
        Match match = new Match(riderId, riderId, matchedListOfDrivers);
        return matchRepository.save(match);
    }

    // Helper compacted methods are below for matching rider with drivers as given above

    private List<String> getMatchedDriversIn5KmsRange(List<Entry<String, Double>> entryList) throws DriverNotFoundException{
        List<String> matchedDrivers = new ArrayList<String>();
        for (int i = constants.ZERO; i < Math.min(constants.MIN_NUMBER_DRIVERS, entryList.size()); i++) {
            matchedDrivers.add(entryList.get(i).getKey());
        }
        if (!driversMatched(matchedDrivers)){
            throw new DriverNotFoundException();
        }
        return matchedDrivers;
    }

    private HashMap<String,Double> getNearestDriversWithDistance (List<Driver> drivers, int riderXCoordinate, int riderYCoordinate){
        HashMap<String,Double> mapDistances = new HashMap<String,Double>();
        for (Driver driver: drivers) 
        {
            int driverXCoordinate = driver.getXCoordinate();
            int driverYCoordinate = driver.getYCoordinate();
            double distance = Util.calculateDistanceForGivenCoordinates(riderXCoordinate,riderYCoordinate,driverXCoordinate, driverYCoordinate); 
            if (driverIn5KmRange(distance) && !driver.isDriverEngaged()) {
                mapDistances.put(driver.getId(), distance);
            }
        }
        return mapDistances;
    }

    private boolean riderExists (String riderId){
        if (riderRepository.existsById(riderId)){
            return true;
        }
        return false;
    }

    private Rider getRider(String riderId) throws RiderNotFoundException{
        Rider rider = riderRepository.findById(riderId).orElseThrow(() -> new RiderNotFoundException(constants.RIDER_NOT_FOUND));
        return rider;
    }

    private List<Driver> getAllDrivers(){
        List<Driver> drivers = driverRepository.findAll();
        return drivers;
    }

    private boolean driversAvailable(List<Driver> drivers){
        if (drivers.size()<=constants.ZERO){
            return false;
        }
        return true;
    }

//    private boolean driverEngaged (Driver driver){
//        if (driver.getDriverStatus() != DriverStatus.ENGAGED){
//            return false;
//        }
//        return true;
//    }

    private boolean driverIn5KmRange(double distance){
        if (distance <= constants.RANGE_OF_DISTANCE){
            return true;
        }
        return false;
    }

    private boolean driversMatched(List<String> listOfDrivers){
        if (listOfDrivers.size() <= constants.ZERO){
            return false;
        }
        return true;
    }
}
