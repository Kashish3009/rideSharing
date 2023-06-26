package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class BillTest {
    @Mock
    private Bill mockBill;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockBill = mock(Bill.class);
    }

    @Test
    public void testRideIdGetter() {
        // Arrange
        String rideId = "R1";
        String driverId = "D1";
        double amount = 100.0;
        Bill bill = new Bill(rideId, driverId, amount);
        // Act
        String actualRideId = bill.getRideId();
        // Assert
        Assertions.assertEquals(rideId, actualRideId);
    }
    @Test
    public void testDriverIdGetter() {
        // Arrange
        String rideId = "R1";
        String driverId = "D1";
        double amount = 100.0;
        Bill bill = new Bill(rideId, driverId, amount);
        // Act
        String actualDriverId = bill.getDriverId();
        // Assert
        Assertions.assertEquals(driverId, actualDriverId);
    }
    @Test
    public void testAmountGetter() {
        // Arrange
        String rideId = "R1";
        String driverId = "D1";
        double amount = 100.0;
        Bill bill = new Bill(rideId, driverId, amount);
        // Act
        double actualAmount = bill.getAmount();
        // Assert
        Assertions.assertEquals(amount, actualAmount);
    }


    @Test
    public void testRideIdMockito() {
        // Arrange
        String rideId = "R1";
        when(mockBill.getRideId()).thenReturn(rideId);
        // Act
        String actualRideId = mockBill.getRideId();
        // Assert
        Assertions.assertEquals(rideId, actualRideId);
        // Verify interactions
        verify(mockBill, times(1)).getRideId();
    }
    @Test
    public void testDriverIdMockito() {
        // Arrange
        String driverId = "D1";
        when(mockBill.getDriverId()).thenReturn(driverId);
        // Act
        String actualDriverId = mockBill.getDriverId();
        // Assert
        Assertions.assertEquals(driverId, actualDriverId);
        // Verify interactions
        verify(mockBill, times(1)).getDriverId();
    }
    @Test
    public void testAmountMockito() {
        // Arrange
        double amount = 100.0;
        when(mockBill.getAmount()).thenReturn(amount);
        // Act
        double actualAmount = mockBill.getAmount();
        // Assert
        Assertions.assertEquals(amount, actualAmount);  
        // Verify interactions
        verify(mockBill, times(1)).getAmount();
    }
}
