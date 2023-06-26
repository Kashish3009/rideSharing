package com.geektrust.backend.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

@DisplayName("RideServiceTest")
@ExtendWith(MockitoExtension.class)
public class RideServiceTest {

    @Mock
    private IMatchService matchServiceMock;
    @Mock
    private IRideRepository rideRepositoryMock;
    @Mock
    private IDriverRepository driverRepositoryMock;
    @Mock
    private IMatchRepository matchRepositoryMock;
    @Mock
    Destination destination;
    
    @InjectMocks
    private RideService rideService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        destination = mock(Destination.class);
        rideService =new RideService(rideRepositoryMock, driverRepositoryMock, matchRepositoryMock);
    }
    @Test
    @DisplayName("StartRide should return rideId of ride")
    public void testStartRideExecutesSuccessfully() throws InvalidRideException, DriverNotFoundException, MatchNotFoundException {
        // Given
        String rideId = "RIDE-101";
        int driverNumber = 1;
        String riderId = "R1";

        List<String> drivers = Arrays.asList("D1", "D2", "D3");
        Match match = new Match(riderId, riderId,drivers);
        
        when(matchRepositoryMock.findById(riderId)).thenReturn(Optional.ofNullable(match));
        //String id, int xCoordinate, int yCoordinate, DriverStatus driverStatus
        Driver driver = new Driver("D2",2, 3, DriverStatus.AVAILABLE);

        when(driverRepositoryMock.findById(anyString())).thenReturn(Optional.ofNullable(driver));

        //rideId, driverIdStartingRide,riderId ,RideStatus.IN_PROGRESS)
        Ride ride = new Ride("RIDE-101","D2","R1",RideStatus.IN_PROGRESS);
        when(rideRepositoryMock.save(any(Ride.class))).thenReturn(ride);

        // When
        String actualRideId = rideService.startRide(rideId, driverNumber, riderId);

        // Then
        assertEquals(rideId, actualRideId);
    }
    @Test
    void startRideForInvalidRideIdShouldThrowInvalidRideException() throws InvalidRideException, DriverNotFoundException, MatchNotFoundException {
        // Arrange
        String rideId = "ride123";
        int driverNumber = 1;
        String riderId = "rider123";
        List<String> driverIdList = Collections.singletonList("driver1");
        Match match = new Match(riderId, riderId, driverIdList);

        when(matchRepositoryMock.findById(riderId)).thenReturn(Optional.of(match));
        when(rideRepositoryMock.existsById(rideId)).thenReturn(true);

        // Act & Assert
        assertThrows(InvalidRideException.class, () -> rideService.startRide(rideId, driverNumber, riderId));
        verify(driverRepositoryMock, never()).findById(anyString());
        verify(rideRepositoryMock, never()).save(any(Ride.class));
    }
    @Test
    void startRideForInvalidDriverNumberShouldThrowInvalidRideException() throws InvalidRideException, DriverNotFoundException, MatchNotFoundException {
        // Arrange
        String rideId = "ride123";
        int driverNumber = 2;
        String riderId = "rider123";
        List<String> driverIdList = Collections.singletonList("driver1");
        Match match = new Match(riderId, riderId, driverIdList);

        when(matchRepositoryMock.findById(riderId)).thenReturn(Optional.of(match));

        // Act & Assert
        assertThrows(InvalidRideException.class, () -> rideService.startRide(rideId, driverNumber, riderId));
        
        //verify
        verify(driverRepositoryMock, never()).findById(anyString());
        verify(rideRepositoryMock, never()).save(any(Ride.class));
    }
    @Test
    @DisplayName("StopRide should return rideId of ride")
    public void testStopRideExecutedSuccessFullyAndReturnsRideId() throws InvalidRideException {

        // Given
        String rideId = "RIDE-101";
        int destinationXCoordinate = 10;
        int destinationYCoordinate = 2;
        int timeDuration = 48;

        Ride ride = new Ride("RIDE-101","D2","R1",RideStatus.IN_PROGRESS);
        Driver driver = new Driver("D2",2, 3, DriverStatus.ENGAGED);
        
        
        when(rideRepositoryMock.findById(anyString())).thenReturn(Optional.ofNullable(ride));
        when(rideRepositoryMock.existsById(anyString())).thenReturn(true);
        when(driverRepositoryMock.findById(anyString())).thenReturn(Optional.ofNullable(driver));
        when(rideRepositoryMock.save(any(Ride.class))).thenReturn(ride);
        
        // When
        String actualRideId = rideService.stopRide(rideId, destinationXCoordinate, destinationYCoordinate, timeDuration);
        
        // Then
       assertEquals(ride.getId(), actualRideId);
    }

    @Test
    void stopRideForInvalidRideIdShouldThrowRideNotFoundException() throws InvalidRideException, RideNotFoundException {
        // Arrange
        String rideId = "ride123";
        int destinationXCoordinate = 10;
        int destinationYCoordinate = 20;
        int timeDuration = 30;

        when(rideRepositoryMock.findById(rideId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RideNotFoundException.class, () -> rideService.stopRide(rideId, destinationXCoordinate, destinationYCoordinate, timeDuration));
        //verify
        verify(driverRepositoryMock, never()).save(any(Driver.class));
        verify(rideRepositoryMock, never()).save(any(Ride.class));
    }
    @Test
    void stopRideForInvalidRideStatusShouldThrowInvalidRideException() throws InvalidRideException, RideNotFoundException {
        // Arrange
        String rideId = "ride123";
        int destinationXCoordinate = 10;
        int destinationYCoordinate = 20;
        int timeDuration = 30;
        Ride ride = new Ride(rideId, "driver123", "rider123", RideStatus.ENDED);

        when(rideRepositoryMock.findById(rideId)).thenReturn(Optional.of(ride));
        when(rideRepositoryMock.existsById(rideId)).thenReturn(true);

        // Act & Assert
        assertThrows(InvalidRideException.class, () -> rideService.stopRide(rideId, destinationXCoordinate, destinationYCoordinate, timeDuration));
        
        verify(driverRepositoryMock, never()).save(any(Driver.class));
        verify(rideRepositoryMock, never()).save(any(Ride.class));
    }
}
