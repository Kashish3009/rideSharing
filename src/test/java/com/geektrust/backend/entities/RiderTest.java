package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class RiderTest {
    @Mock
    private Rider mockRider;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRiderIdGetter() {
        // Arrange
        String riderId = "R1";
        int xCoordinate = 3;
        int yCoordinate = 5;
        Rider rider = new Rider(riderId, xCoordinate, yCoordinate);
        // Act
        String actualRiderId = rider.getId();
        // Assert
        Assertions.assertEquals(riderId, actualRiderId);
    }
    @Test
    public void testxCoordinateGetter() {
        // Arrange
        String riderId = "R1";
        int xCoordinate = 3;
        int yCoordinate = 5;
        Rider rider = new Rider(riderId, xCoordinate, yCoordinate);
        // Act
        int actualXCoordinate = rider.getXCoordinate();
        // Assert
        Assertions.assertEquals(xCoordinate, actualXCoordinate);
    }
    @Test
    public void testyCoordinateGetter() {
        // Arrange
        String riderId = "R1";
        int xCoordinate = 3;
        int yCoordinate = 5;
        Rider rider = new Rider(riderId, xCoordinate, yCoordinate);
        // Act
        int actualYCoordinate = rider.getYCoordinate();
        // Assert
        Assertions.assertEquals(yCoordinate, actualYCoordinate);
    }
    @Test
    public void testRiderIdIsReturned() {
        // Arrange
        String riderId = "R1";
        when(mockRider.getId()).thenReturn(riderId);

        // Act
        String actualRiderId = mockRider.getId();
        // Assert
        Assertions.assertEquals(riderId, actualRiderId);

        // Verify interactions
        verify(mockRider, times(1)).getId();
    }

    @Test
    public void testxCoordinateIsReturned() {
        // Arrange
        int xCoordinate = 3;
        when(mockRider.getXCoordinate()).thenReturn(xCoordinate);
        // Act
        int actualXCoordinate = mockRider.getXCoordinate();
        // Assert
        Assertions.assertEquals(xCoordinate, actualXCoordinate);
        // Verify interactions
        verify(mockRider, times(1)).getXCoordinate();
    }

    @Test
    public void testyCoordinateIsReturned() {
        // Arrange
        int yCoordinate = 5;
        when(mockRider.getYCoordinate()).thenReturn(yCoordinate);
        // Act
        int actualYCoordinate = mockRider.getYCoordinate();
        // Assert
        Assertions.assertEquals(yCoordinate, actualYCoordinate);
        // Verify interactions
        verify(mockRider, times(1)).getYCoordinate();
    }
}
