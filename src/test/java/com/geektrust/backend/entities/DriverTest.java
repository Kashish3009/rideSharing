package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class DriverTest {
    @Mock
    private Driver mockDriver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockDriver = mock(Driver.class);
    }

    @Test
    public void testIdGetter() {
        // Arrange
        String id = "D1";
        int xCoordinate = 3;
        int yCoordinate = 5;
        DriverStatus driverStatus = DriverStatus.AVAILABLE;
        Driver driver = new Driver(id, xCoordinate, yCoordinate, driverStatus);
        // Act
        String actualId = driver.getId();
        // Assert
        Assertions.assertEquals(id, actualId);
    }

    @Test
    public void testxCoordinateGetter() {
        // Arrange
        String id = "D1";
        int xCoordinate = 3;
        int yCoordinate = 5;
        DriverStatus driverStatus = DriverStatus.AVAILABLE;
        Driver driver = new Driver(id, xCoordinate, yCoordinate, driverStatus);
        // Act
        int actualXCoordinate = driver.getXCoordinate();
        // Assert
        Assertions.assertEquals(xCoordinate, actualXCoordinate);
    }
    @Test
    public void testYCoordinateGetter() {
        // Arrange
        String id = "D1";
        int xCoordinate = 3;
        int yCoordinate = 5;
        DriverStatus driverStatus = DriverStatus.AVAILABLE;
        Driver driver = new Driver(id, xCoordinate, yCoordinate, driverStatus);
        // Act
        int actualYCoordinate = driver.getYCoordinate();
        // Assert
        Assertions.assertEquals(yCoordinate, actualYCoordinate);
    }
    @Test
    public void testDriverStatusGetter() {
        // Arrange
        String id = "D1";
        int xCoordinate = 3;
        int yCoordinate = 5;
        DriverStatus driverStatus = DriverStatus.AVAILABLE;
        Driver driver = new Driver(id, xCoordinate, yCoordinate, driverStatus);
        // Act
        DriverStatus actualDriverStatus = driver.getDriverStatus();
        // Assert
        Assertions.assertEquals(driverStatus, actualDriverStatus);
    }

    @Test
    public void testSetDriverStatus() {
        // Arrange
        DriverStatus initialDriverStatus = DriverStatus.AVAILABLE;
        DriverStatus updatedDriverStatus = DriverStatus.ENGAGED;
        Driver driver = new Driver("D1", 3, 5, initialDriverStatus);
        // Act
        driver.setDriverStatus(updatedDriverStatus);
        DriverStatus actualDriverStatus = driver.getDriverStatus();
        // Assert
        Assertions.assertEquals(updatedDriverStatus, actualDriverStatus);
    }
    
    @Test
    public void testdriverIdisReturned() {
        // Arrange
        String id = "D1";
        when(mockDriver.getId()).thenReturn(id);
        // Act
        String actualId = mockDriver.getId();
        // Assert
        Assertions.assertEquals(id, actualId);
        // Verify interactions
        verify(mockDriver, times(1)).getId();
    }
     
    @Test
    public void testxCoordinateIsReturned() {
        // Arrange
        int xCoordinate = 3;
        when(mockDriver.getXCoordinate()).thenReturn(xCoordinate);
        // Act
        int actualXCoordinate = mockDriver.getXCoordinate();
        // Assert
        Assertions.assertEquals(xCoordinate, actualXCoordinate);
        // Verify interactions
        verify(mockDriver, times(1)).getXCoordinate();
    }
     
    @Test
    public void testyCoordinateisReturned() {
        // Arrange
        int yCoordinate = 5;
        when(mockDriver.getYCoordinate()).thenReturn(yCoordinate);
        // Act
        int actualYCoordinate = mockDriver.getYCoordinate();
        // Assert
        Assertions.assertEquals(yCoordinate, actualYCoordinate);
        // Verify interactions
        verify(mockDriver, times(1)).getYCoordinate();
    }
     
    @Test
    public void testDriverStatusIsReturned() {
        // Arrange
        DriverStatus driverStatus = DriverStatus.AVAILABLE;
        when(mockDriver.getDriverStatus()).thenReturn(driverStatus);
        //Act
        DriverStatus actualDriverStatus = mockDriver.getDriverStatus();
        // Assert
        Assertions.assertEquals(driverStatus, actualDriverStatus);
        // Verify interactions
        verify(mockDriver, times(1)).getDriverStatus();
    }
}
