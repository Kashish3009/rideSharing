package com.geektrust.backend.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import com.geektrust.backend.dtos.BillGenerationDTOS;
import com.geektrust.backend.entities.Destination;
import com.geektrust.backend.entities.Ride;
import com.geektrust.backend.entities.RideStatus;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.exceptions.InvalidRideException;
import com.geektrust.backend.exceptions.RideNotCompletedException;
import com.geektrust.backend.exceptions.RideNotFoundException;
import com.geektrust.backend.repositories.IRideRepository;
import com.geektrust.backend.repositories.IRiderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("BillServiceTest")
@ExtendWith(MockitoExtension.class)
public class BillServiceTest {
    @Mock
    private IRideRepository rideRepositoryMock;
    @Mock
    private IRiderRepository riderRepositoryMock;


    @InjectMocks
    private BillService billService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        billService = new BillService(rideRepositoryMock, riderRepositoryMock);
    }

    @Test
    @DisplayName("Bill genrartion service should return the bill amount")
    public void generateBillShouldReturnValidBillGenerationDTO() throws RideNotCompletedException, InvalidRideException {
        // Arrange
        String rideId = "RIDE-101";
        Ride ride = new Ride("RIDE-101","D2", "R1",RideStatus.ENDED);
        Destination destination = new Destination(10, 2, 48);
        ride.setDestination(destination); 

        Rider rider = new Rider("R1",3,5);

        double expectedTotalBill = 234.64;

        when(rideRepositoryMock.findById(rideId)).thenReturn(Optional.of(ride));
        when(riderRepositoryMock.findById(ride.getRiderId())).thenReturn(Optional.of(rider));
        // Act
        BillGenerationDTOS billGenerationDTOS = billService.generateBill(rideId);
        // Assert
        Assertions.assertEquals(String.format("%.2f", expectedTotalBill), billGenerationDTOS.getAmount());

        verify(rideRepositoryMock, times(1)).findById(rideId);
        verify(riderRepositoryMock, times(1)).findById(ride.getRiderId());
    }
    @Test
    void generateBillWithInvalidRideIdShouldThrowRideNotFoundException() {
        // Arrange
        String rideId = "invalidRideId";

        when(rideRepositoryMock.findById(rideId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RideNotFoundException.class, () -> billService.generateBill(rideId));

        // Verify that the method was called with the expected argument
        verify(rideRepositoryMock).findById(rideId);
    }

    @Test
    void generateBillWithIncompleteRideShouldThrowRideNotCompletedException() {
        // Arrange
        String rideId = "RIDE-101";
        Ride ride = new Ride("RIDE-101","D2", "R1",RideStatus.IN_PROGRESS);

        when(rideRepositoryMock.findById(rideId)).thenReturn(Optional.of(ride));

        // Act and Assert
        assertThrows(RideNotCompletedException.class, () -> billService.generateBill(rideId));

        // Verify that the method was called with the expected argument
        verify(rideRepositoryMock).findById(rideId);
    }
}
