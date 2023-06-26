package com.geektrust.backend.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.DriverStatus;
import com.geektrust.backend.entities.Match;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.exceptions.DriverNotFoundException;
import com.geektrust.backend.exceptions.RiderNotFoundException;
import com.geektrust.backend.repositories.IDriverRepository;
import com.geektrust.backend.repositories.IMatchRepository;
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

@DisplayName("MatchServiceTest")
@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {
    @Mock
    private IRiderRepository riderRepositoryMock;
    @Mock
    private IDriverRepository driverRepositoryMock;
    @Mock
    private IMatchRepository matchRepositoryMock;
    @InjectMocks
    private MatchService matchService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        matchService = new MatchService(riderRepositoryMock, driverRepositoryMock, matchRepositoryMock);
    }

    @Test
    @DisplayName("match Rider With Drivers should return list of matched drivers")
    public void matchRiderWithDriversShouldReturnListOfDrivers() throws DriverNotFoundException {
        // Arrange
        String riderId = "R1";
        int riderXCoordinate = 3;
        int riderYCoordinate = 5;

        Rider rider = new Rider(riderId,riderXCoordinate,riderYCoordinate);
        List<String>driverIds = Arrays.asList("D1", "D2");
        Driver driver1 = new Driver("D1",0,1,DriverStatus.AVAILABLE );
        Driver driver2 = new Driver("D2",2,3,DriverStatus.AVAILABLE );
        
        List<Driver> drivers = Arrays.asList(driver1, driver2);

        Match match = new Match(riderId, riderId,driverIds);
        when(riderRepositoryMock.existsById(riderId)).thenReturn(true);
        when(riderRepositoryMock.findById(riderId)).thenReturn(Optional.of(rider));
        when(driverRepositoryMock.findAll()).thenReturn(drivers);
        when(matchRepositoryMock.save(any(Match.class))).thenReturn(match);
        // Act
        Match matched = matchService.matchRiderWithDrivers(riderId);
        
        // Assert
        Assertions.assertEquals(2, matched.driverIdList().size());

        verify(riderRepositoryMock, times(1)).existsById(riderId);
        verify(riderRepositoryMock, times(1)).findById(riderId);
        verify(driverRepositoryMock, times(1)).findAll();
    }

    @Test
    void matchRiderWithDriversForNoAvailableDriversShouldThrowsDriverNotFoundException() throws DriverNotFoundException, RiderNotFoundException {
        // Arrange
        String riderId = "rider123";
        Rider rider = new Rider(riderId, 0, 0);
        List<Driver> drivers = Collections.emptyList();

        when(riderRepositoryMock.existsById(riderId)).thenReturn(true);
        when(riderRepositoryMock.findById(riderId)).thenReturn(Optional.of(rider));
        when(driverRepositoryMock.findAll()).thenReturn(drivers);

        // Act & Assert
        assertThrows(DriverNotFoundException.class, () -> matchService.matchRiderWithDrivers(riderId));
        verify(matchRepositoryMock, never()).save(any(Match.class));
    }
}
