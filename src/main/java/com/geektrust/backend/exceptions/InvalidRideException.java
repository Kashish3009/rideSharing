package com.geektrust.backend.exceptions;

public class InvalidRideException extends Exception {
    public InvalidRideException() {
      super();
    }
       
    public InvalidRideException(String msg) {
      super(msg);
    }
}
