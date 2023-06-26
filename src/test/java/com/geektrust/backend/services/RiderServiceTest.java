package com.geektrust.backend.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.exceptions.RiderAlreadyPresentException;
import com.geektrust.backend.repositories.IRiderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("RiderServiceTest")
@ExtendWith(MockitoExtension.class)
public class RiderServiceTest {

    @Mock
    private IRiderRepository riderRepositoryMock;
    @InjectMocks
    private RiderService riderService;

    @Test
    @DisplayName("AddRider should add rider")
    public void addRiderShouldReturnSavedRider() throws RiderAlreadyPresentException {
        //Arrange
        Rider expectedRider = new Rider("R1", 3, 5);
        when(riderRepositoryMock.save(any(Rider.class))).thenReturn(expectedRider);

        //Act
        Rider actualRider = riderService.addRider("R1", 3, 5);

        //Assert
        Assertions.assertEquals(expectedRider,actualRider);
        verify(riderRepositoryMock,times(1)).save(any(Rider.class));
    }
    @Test
    void addRiderWhenRiderAlreadyExistsShouldThrowRiderAlreadyPresentException() {
        // Arrange
        String riderId = "R001";
        int xCoordinate = 10;
        int yCoordinate = 20;

        when(riderRepositoryMock.existsById(riderId)).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(RiderAlreadyPresentException.class, () ->
                riderService.addRider(riderId, xCoordinate, yCoordinate));

        verify(riderRepositoryMock, times(1)).existsById(riderId);
        verify(riderRepositoryMock, never()).save(any(Rider.class));
    }
}
