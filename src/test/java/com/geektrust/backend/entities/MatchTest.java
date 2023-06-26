package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

public class MatchTest {
    @Mock
    private Match mockMatch;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMatch = mock(Match.class);
    }

    @Test
    public void testRiderIdGetter() {
        // Arrange
        String riderId = "R1";
        List<String> driverIdList = Arrays.asList("D1", "D2", "D3");
        Match match = new Match(riderId, riderId, driverIdList);
        // Act
        String actualRiderId = match.getRiderId();
        // Assert
        Assertions.assertEquals(riderId, actualRiderId);
    }

    @Test
    public void testDriverIdsGetter() {
        // Arrange
        String riderId = "R1";
        List<String> driverIdList = Arrays.asList("D1", "D2", "D3");
        Match match = new Match(riderId, riderId, driverIdList);
        // Act
        List<String> actualDriverIdList = match.driverIdList();
        // Assert
        Assertions.assertEquals(driverIdList, actualDriverIdList);
    }

    @Test
    public void testDriverIdsListisReturned() {
        // Arrange
        List<String> driverIdList = Arrays.asList("D1", "D2", "D3");
        when(mockMatch.driverIdList()).thenReturn(driverIdList);
        // Act
        List<String> actualDriverIdList = mockMatch.driverIdList();
        // Assert
        Assertions.assertEquals(driverIdList, actualDriverIdList);
        // Verify interactions
        verify(mockMatch, times(1)).driverIdList();
    }
    @Test
    public void testRiderIdisReturned() {
        // Arrange
        String riderId = "R1";
        when(mockMatch.getRiderId()).thenReturn(riderId);
        // Act
        String actualRiderId = mockMatch.getRiderId();
        // Assert
        Assertions.assertEquals(riderId, actualRiderId);
        // Verify interactions
        verify(mockMatch, times(1)).getRiderId();
    }
}
