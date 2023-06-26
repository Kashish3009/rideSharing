package com.geektrust.backend.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.DriverStatus;
import com.geektrust.backend.exceptions.DriverAlreadyPresentException;
import com.geektrust.backend.repositories.IDriverRepository;

@DisplayName("DriverServiceTest")
@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {

    @Mock
    private IDriverRepository driverRepositoryMock;
    @InjectMocks
    private DriverService driverService;

    @Test
    @DisplayName("AddDriver should add driver")
    public void addDriverShouldReturnSavedDriver() throws DriverAlreadyPresentException{
        //Arrange
        Driver expectedDriver = new Driver("D1", 0, 1, DriverStatus.AVAILABLE);
        when(driverRepositoryMock.save(any(Driver.class))).thenReturn(expectedDriver);

        //Act
        Driver actualDriver = driverService.addDriver("D1", 0, 1);

        //Assert
        Assertions.assertEquals(expectedDriver,actualDriver);
        verify(driverRepositoryMock,times(1)).save(any(Driver.class));
    }
    @Test
    void addDriverWhenDriverAlreadyExistsShouldThrowDriverAlreadyPresentException() {
        // Arrange
        String driverId = "D1";
        int xCoordinate = 10;
        int yCoordinate = 20;

        when(driverRepositoryMock.existsById(driverId)).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(DriverAlreadyPresentException.class, () ->
                driverService.addDriver(driverId, xCoordinate, yCoordinate));

        verify(driverRepositoryMock, times(1)).existsById(driverId);
        verify(driverRepositoryMock, never()).save(any(Driver.class));
    }
}
