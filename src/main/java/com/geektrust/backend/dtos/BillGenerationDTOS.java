package com.geektrust.backend.dtos;

import com.geektrust.backend.Constants.constants;

public class BillGenerationDTOS {
   private final String rideId;
   private final String driverId;
   private final String amount;

   public BillGenerationDTOS (String rideId, String driverId, String amount){
    this.amount = amount;
    this.driverId = driverId;
    this.rideId = rideId;
   }
   public String getAmount(){
    return amount;
   }
   public String getDriverId(){
    return driverId;
   }
   public String getRideId(){
    return rideId;
   }
   @Override
   public String toString() {
       return constants.BILL +constants.SINGLE_SPACE_STRING + getRideId() +constants.SINGLE_SPACE_STRING + getDriverId()+constants.SINGLE_SPACE_STRING + getAmount();
   }
}
