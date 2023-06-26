package com.geektrust.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RideTest {
    @InjectMocks
    private Destination destination;

    @Mock
    private Ride mockRide;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        destination = mock(Destination.class);
        mockRide = mock(Ride.class);
    }

    @Test
    void constructorWithDriverIdAndRiderIdAndRideStatusSetsIdCorrectly() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String riderId = "rider789";
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        // Act
        Ride ride = new Ride(rideId, driverId, riderId, rideStatus);
        // Assert
        assertEquals(rideId, ride.getId());
    }
    @Test
    void constructorWithDriverIdAndRiderIdAndRideStatusSetsDriverIdCorrectly() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String riderId = "rider789";
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        // Act
        Ride ride = new Ride(rideId, driverId, riderId, rideStatus);
        // Assert
        assertEquals(driverId, ride.getDriverId());
    }

    @Test
    void constructorWithDriverIdAndRiderIdAndRideStatusSetsRiderIdCorrectly() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String riderId = "rider789";
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        // Act
        Ride ride = new Ride(rideId, driverId, riderId, rideStatus);
        // Assert
        assertEquals(riderId, ride.getRiderId());

    }
    @Test
    void constructorWithDriverIdAndRiderIdAndRideStatusSetsRiderStatusCorrectly() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String riderId = "rider789";
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        // Act
        Ride ride = new Ride(rideId, driverId, riderId, rideStatus);
        // Assert
        assertEquals(rideStatus, ride.getRideStatus());
    }

    @Test
    void constructorWithRideIdDestinationAndRideStatusSetsRideIdCorrectly() {
        // Arrange
        String rideId = "ride123";
        Destination destination = mock(Destination.class);
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        // Act
        Ride ride = new Ride(rideId, destination, rideStatus);
        // Assert
        assertEquals(rideId, ride.getRiderId());
    }

    @Test
    void constructorWithRideIdDestinationAndRideStatusSetsDestinationCorrectly() {
        // Arrange
        String rideId = "ride123";
        Destination destination = mock(Destination.class);
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        // Act
        Ride ride = new Ride(rideId, destination, rideStatus);
        // Assert
        assertEquals(destination, ride.getDestination());
    }
    
    @Test
    void getDestinationReturnsDestination() {
        // Arrange
        String rideId = "ride123";
        Destination destination = mock(Destination.class);
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        Ride ride = new Ride(rideId, destination, rideStatus);
        // Act
        Destination actualDestination = ride.getDestination();
        // Assert
        assertEquals(destination, actualDestination);
    }

    @Test
    void getRiderIdReturnsRiderId() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String riderId = "rider789";
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        Ride ride = new Ride(rideId, driverId, riderId, rideStatus);
        // Act
        String actualRiderId = ride.getRiderId();
        // Assert
        assertEquals(riderId, actualRiderId);
    }
    @Test
    void getRideStatusReturnsRideStatus() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String riderId = "rider789";
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        Ride ride = new Ride(rideId, driverId, riderId, rideStatus);
        // Act
        RideStatus actualRideStatus = ride.getRideStatus();
        // Assert
        assertEquals(rideStatus, actualRideStatus);
    }
    @Test
    void setRideStatusWithValidStatusSetsRideStatus() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String riderId = "rider789";
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        Ride ride = new Ride(rideId, driverId, riderId, rideStatus);
        // Act
        ride.setRideStatus(RideStatus.ENDED);
        // Assert
        assertEquals(RideStatus.ENDED, ride.getRideStatus());
    }
    
    @Test
    void setRideStatusWithNullStatusDoesNotChangeRideStatus() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String riderId = "rider789";
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        Ride ride = new Ride(rideId, driverId, riderId, rideStatus);
        // Act
        ride.setRideStatus(null);
        // Assert
        assertEquals(rideStatus, ride.getRideStatus());
    }

    @Test
    void getDriverIdReturnsDriverId() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String riderId = "rider789";
        RideStatus rideStatus = RideStatus.IN_PROGRESS;
        Ride ride = new Ride(rideId, driverId, riderId, rideStatus);
        // Act
        String actualDriverId = ride.getDriverId();
        // Assert
        assertEquals(driverId, actualDriverId);
    }
}
