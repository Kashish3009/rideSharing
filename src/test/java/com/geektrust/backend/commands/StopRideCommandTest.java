package com.geektrust.backend.commands;

import com.geektrust.backend.exceptions.InvalidRideException;
import com.geektrust.backend.services.IRideService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class StopRideCommandTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private IRideService rideService;

    @InjectMocks
    private StopRideCommand stopRideCommand;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
        stopRideCommand = new StopRideCommand(rideService);
    }

    @Test
    void stopRideExecutesSuccessfullyOrNot() throws InvalidRideException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("STOP_RIDE");
        tokens.add("RIDE-001");
        tokens.add("4");
        tokens.add("5");
        tokens.add("32");

        when(rideService.stopRide(anyString(), anyInt(), anyInt(), anyInt())).thenReturn("RIDE-001");
        // Act
        stopRideCommand.execute(tokens);
        // Assert
        verify(rideService).stopRide("RIDE-001", 4, 5, 32);
    }

    @Test
    void stopRidePrintsRideStoppedMessageOrNot() throws InvalidRideException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("STOP_RIDE");
        tokens.add("RIDE-001");
        tokens.add("4");
        tokens.add("5");
        tokens.add("32");

        when(rideService.stopRide(anyString(), anyInt(), anyInt(), anyInt())).thenReturn("RIDE-001");
        // Act
        stopRideCommand.execute(tokens);
        // Assert
        String expectedOutput = "RIDE_STOPPED RIDE-001";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void stopRidePrintsInvalidRideMessageOrNot() throws InvalidRideException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("STOP_RIDE");
        tokens.add("RIDE-001");
        tokens.add("4");
        tokens.add("5");
        tokens.add("32");

        when(rideService.stopRide(anyString(), anyInt(), anyInt(), anyInt()))
                .thenThrow(new InvalidRideException());
        // Act
        stopRideCommand.execute(tokens);
        // Assert
        String expectedOutput = "INVALID_RIDE";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}
