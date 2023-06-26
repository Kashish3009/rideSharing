package com.geektrust.backend.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.DriverStatus;
import static org.mockito.Mockito.*;

public class DriverRepositoryTest {

    @InjectMocks
    private DriverRepository driverRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        driverRepository = mock(DriverRepository.class);
    }

    @Test
    public void saveShouldReturnSavedDriver() {
        // Arrange
        Driver driver = new Driver("D1", 1,1,DriverStatus.AVAILABLE);
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);
        // Act
        Driver savedDriver = driverRepository.save(driver);
        // Assert
        Assertions.assertEquals(driver, savedDriver);
    }

    @Test
    public void findAllShouldReturnListOfDrivers() {
        // Arrange
        Driver driver1 = new Driver("D1",1,1,DriverStatus.AVAILABLE);
        Driver driver2 = new Driver("D2",2, 2, DriverStatus.AVAILABLE);
        List<Driver> expectedDrivers = List.of(driver1, driver2);
        when(driverRepository.findAll()).thenReturn(expectedDrivers);
        // Act
        List<Driver> actualDrivers = driverRepository.findAll();
        // Assert
        Assertions.assertEquals(expectedDrivers, actualDrivers);
    }

    @Test
    public void ExistingIdShouldReturnDriverOptional() {
        // Arrange
        String driverId = "D1";
        Driver driver = new Driver("D1",1,1,DriverStatus.AVAILABLE);
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);
        when(driverRepository.existsById(driverId)).thenReturn(true);
        // Act
        boolean optionalDriver = driverRepository.existsById(driverId);
        // Assert
        Assertions.assertTrue(optionalDriver);
    }
    @Test
    public void findByIdShouldReturnDriverOptional() {
        // Arrange
        String driverId = "D1";
        Driver driver = new Driver("D1",1,1,DriverStatus.AVAILABLE);
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));
        // Act
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        // Assert
        Assertions.assertEquals(driver, optionalDriver.get());
    }

    @Test
    public void findByIdForNonExistingIdShouldReturnEmptyOptional() {
        // Arrange
        String driverId = "D1";
        when(driverRepository.findById(driverId)).thenReturn(Optional.empty());
        // Act
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        // Assert
        Assertions.assertTrue(optionalDriver.isEmpty());
    }

    @Test
    public void existsByIdForExistingIdShouldReturnTrue() {
        // Arrange
        String driverId = "D1";
        when(driverRepository.existsById(driverId)).thenReturn(true);
        // Act
        boolean exists = driverRepository.existsById(driverId);
        // Assert
        Assertions.assertTrue(exists);
    }

    @Test
    public void existsByIdForNonExistingIdShouldReturnFalse() {
        // Arrange
        String driverId = "D1";
        when(driverRepository.existsById(driverId)).thenReturn(false);
        // Act
        boolean exists = driverRepository.existsById(driverId);
        // Assert
        Assertions.assertFalse(exists);
    }
}
