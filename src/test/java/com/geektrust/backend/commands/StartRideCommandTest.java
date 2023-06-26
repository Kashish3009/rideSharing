package com.geektrust.backend.commands;

import com.geektrust.backend.exceptions.DriverNotFoundException;
import com.geektrust.backend.exceptions.InvalidRideException;
import com.geektrust.backend.exceptions.MatchNotFoundException;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class StartRideCommandTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @Mock
    private IRideService rideService;

    @InjectMocks
    private StartRideCommand startRideCommand;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
        startRideCommand = new StartRideCommand(rideService);
    }

    @Test
    void startRideExecutedSuccessfullyOrNot() throws InvalidRideException, DriverNotFoundException, MatchNotFoundException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("START_RIDE");
        tokens.add("RIDE-001");
        tokens.add("2");
        tokens.add("R1");
        when(rideService.startRide(anyString(), anyInt(), anyString())).thenReturn("RIDE_STARTED");
        // Act
        startRideCommand.execute(tokens);
        // Assert
        verify(rideService).startRide("RIDE-001", 2, "R1");
    }

    @Test
    void doesStartRidePrintRideStartedMessage() throws InvalidRideException, DriverNotFoundException, MatchNotFoundException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("START_RIDE");
        tokens.add("RIDE-001");
        tokens.add("2");
        tokens.add("R1");

        when(rideService.startRide(anyString(), anyInt(), anyString())).thenReturn("RIDE-001");
        // Act
        startRideCommand.execute(tokens);
        // Assert
        String expectedOutput = "RIDE_STARTED RIDE-001";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void doesStartRidePrintInvalidRideMessage() throws InvalidRideException, DriverNotFoundException, MatchNotFoundException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("START_RIDE");
        tokens.add("RIDE-001");
        tokens.add("2");
        tokens.add("R1");

        when(rideService.startRide(anyString(), anyInt(), anyString()))
                .thenThrow(new InvalidRideException());
        // Act
        startRideCommand.execute(tokens);
        // Assert
        String expectedOutput = "INVALID_RIDE";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}
