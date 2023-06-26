package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DestinationTest {
    @Mock
    private Destination mockDestination;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockDestination = mock(Destination.class);
    }

    @Test
    void constructorWithCoordinatesAndTimeTakenSetsxCoordinateCorrectly() {
        // Arrange
        int destinationXCoordinate = 10;
        int destinationYCoordinate = 20;
        int timeTaken = 30;
        // Act
        Destination destination = new Destination(destinationXCoordinate, destinationYCoordinate, timeTaken);
        // Assert
        assertEquals(destinationXCoordinate, destination.getDestinationXCoordinate());
    }


    @Test
    void constructorWithCoordinatesAndTimeTakenSetsYCoordinateCorrectly() {
        // Arrange
        int destinationXCoordinate = 10;
        int destinationYCoordinate = 20;
        int timeTaken = 30;
        // Act
        Destination destination = new Destination(destinationXCoordinate, destinationYCoordinate, timeTaken);
        // Assert
        assertEquals(destinationYCoordinate, destination.getDestinationYCoordinate());
    }

    @Test
    void constructorWithCoordinatesAndTimeTakenSetstimeTakenCorrectly() {
        // Arrange
        int destinationXCoordinate = 10;
        int destinationYCoordinate = 20;
        int timeTaken = 30;
        // Act
        Destination destination = new Destination(destinationXCoordinate, destinationYCoordinate, timeTaken);
        // Assert
        assertEquals(timeTaken, destination.getTimeTaken());
    }

    @Test
    void setDestinationXCoordinateWithValidCoordinateSetsDestinationXCoordinate() {
        // Arrange
        int destinationXCoordinate = 10;
        Destination destination = new Destination();
        // Act
        destination.setDestinationXCoordinate(destinationXCoordinate);
        // Assert
        assertEquals(destinationXCoordinate, destination.getDestinationXCoordinate());
    }

    @Test
    void setDestinationYCoordinateWithValidCoordinateSetsDestinationYCoordinate() {
        // Arrange
        int destinationYCoordinate = 20;
        Destination destination = new Destination();
        // Act
        destination.setDestinationYCoordinate(destinationYCoordinate);
        // Assert
        assertEquals(destinationYCoordinate, destination.getDestinationYCoordinate());
    }

    @Test
    void setTimeTakenWithPositiveTimeDurationSetsTimeTaken() {
        // Arrange
        int timeDuration = 30;
        Destination destination = new Destination();
        // Act
        destination.setTimeTaken(timeDuration);
        // Assert
        assertEquals(timeDuration, destination.getTimeTaken());
    }
}
