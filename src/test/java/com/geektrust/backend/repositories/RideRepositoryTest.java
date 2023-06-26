package com.geektrust.backend.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.*;
import com.geektrust.backend.entities.Ride;
import com.geektrust.backend.entities.RideStatus;

public class RideRepositoryTest {
    
    @InjectMocks
    private RideRepository rideRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        rideRepository = mock(RideRepository.class);
    }

    @Test
    public void saveRideShouldReturnSavedRide() {
        // Arrange
       // String rideId, String driverId, String riderId,RideStatus rideStatus
        Ride ride = new Ride("RIDE-101","D2","R1",RideStatus.IN_PROGRESS);
        when(rideRepository.save(any(Ride.class))).thenReturn(ride);
        // Act
        Ride savedRide = rideRepository.save(ride);
        // Assert
        Assertions.assertEquals(ride, savedRide);
    }

    @Test
    public void findAllForRideShouldReturnAllRides() {
        // Arrange
        Ride ride1 = new Ride("RIDE-101","D2","R1",RideStatus.IN_PROGRESS);
        Ride ride2 = new Ride("RIDE-102","D1","R2",RideStatus.IN_PROGRESS);
        
        List<Ride> expectedRides = List.of(ride1, ride2);
        // Act
        when(rideRepository.findAll()).thenReturn(expectedRides);
        List<Ride> rides = rideRepository.findAll();
        // Assert
        Assertions.assertEquals(2, rides.size());
    }

    @Test
    public void findByIdforRideShouldReturnRideforGivenId() {
        // Arrange
        Ride ride = new Ride("RIDE-101","D2","R1",RideStatus.IN_PROGRESS);
        rideRepository.save(ride);
        when(rideRepository.save(any(Ride.class))).thenReturn(ride);
        when(rideRepository.findById("R1")).thenReturn(Optional.of(ride));
        // Act
        Optional<Ride> foundRide = rideRepository.findById("R1");
        // Assert
        Assertions.assertEquals(ride, foundRide.get());
    }

    @Test
    public void findByIdForRideShouldReturnEmptyOptionalForNonExistingId() {
        // Act
        Optional<Ride> foundRide = rideRepository.findById("R1");
        // Assert
        Assertions.assertFalse(foundRide.isPresent());
    }

    @Test
    public void existsByIdForRideShouldReturnTrueForExistingId() {
        // Arrange
        Ride ride = new Ride("RIDE-101","D2","R1",RideStatus.IN_PROGRESS);
        rideRepository.save(ride);
        when(rideRepository.save(any(Ride.class))).thenReturn(ride);
        when(rideRepository.existsById("R1")).thenReturn(true);
        // Act
        boolean exists = rideRepository.existsById("R1");
        // Assert
        Assertions.assertTrue(exists);
    }

    @Test
    public void existsByIdForRideShouldReturnFalseForNonExistingId() {
        // Act
        boolean exists = rideRepository.existsById("R1");
        // Assert
        Assertions.assertFalse(exists);
    }
}
