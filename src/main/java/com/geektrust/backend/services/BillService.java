package com.geektrust.backend.services;

import com.geektrust.backend.dtos.BillGenerationDTOS;
import com.geektrust.backend.entities.Ride;
import com.geektrust.backend.entities.RideStatus;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.exceptions.InvalidRideException;
import com.geektrust.backend.exceptions.RideNotCompletedException;
import com.geektrust.backend.exceptions.RideNotFoundException;
import com.geektrust.backend.exceptions.RiderNotFoundException;
import com.geektrust.backend.repositories.IRideRepository;
import com.geektrust.backend.repositories.IRiderRepository;
import com.geektrust.backend.Constants.constants;
import com.geektrust.backend.Utils.Util;

public class BillService implements IBillService{

    private final IRideRepository rideRepository;
    private final IRiderRepository riderRepository;
    
    public BillService (IRideRepository rideRepository, IRiderRepository riderRepository){
        this.rideRepository=rideRepository;
        this.riderRepository=riderRepository;
    }

    @Override
    public BillGenerationDTOS generateBill(String rideId) throws RideNotCompletedException, InvalidRideException, RideNotFoundException 
    {
      Ride ride = getRide(rideId);
      if (ride.getRideStatus() != RideStatus.ENDED) {
        throw new RideNotCompletedException(constants.RIDE_NOT_COMPLETED);
      }
      Rider rider = getRider(ride.getRiderId());
      double distanceCovered = Util.calculateDistanceForGivenCoordinates(rider.getXCoordinate(), rider.getYCoordinate(), ride.getDestination().getDestinationXCoordinate(), ride.getDestination().getDestinationYCoordinate());
      String finalBill = calculateFinalBill(distanceCovered, ride.getDestination().getTimeTaken());
      BillGenerationDTOS objBillGeneration = new BillGenerationDTOS(rideId, ride.getDriverId(), finalBill);
      return objBillGeneration;
    }

    private Ride getRide(String rideId) throws RideNotFoundException {
      Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RideNotFoundException(constants.INVALID_RIDE));
      return ride;
    }

    private Rider getRider(String riderId) throws RiderNotFoundException {
      Rider rider = riderRepository.findById(riderId).orElseThrow(() -> new RiderNotFoundException(constants.RIDER_NOT_FOUND));
      return rider;
    }

    private String calculateFinalBill( double distanceCovered, int timeDuration ){
      double initialBill = calculateInitialBill(distanceCovered, timeDuration);
      double serviceTax = calculateServiceTax(initialBill);
      double totalBill = calculateTotalBill(initialBill, serviceTax);
      String finalBill = Util.limitTo2DecimalPlace(totalBill);
      return finalBill;
    }

    private double calculateInitialBill (double distanceCovered, int timeDuration) {
      double initialBill = constants.BASE_FARE + (constants.PER_KILOMETER_CHARGE * distanceCovered) 
      + (constants.CHARGE_FOR_TIME_SPENT_IN_CAR * timeDuration);
      return Util.roundUp(initialBill);
    }

    private double calculateServiceTax(double initialBill){
     double serviceTax = (constants.SERVICE_TAX * initialBill) / constants.HUNDRED;
      return Util.roundUp(serviceTax);
    }

    private double calculateTotalBill(double initialBill, double serviceTax){
       double totalBill = serviceTax + initialBill;
       return Util.roundUp(totalBill);
    }
}
