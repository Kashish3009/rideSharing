package com.geektrust.backend.commands;

import com.geektrust.backend.entities.Match;
import com.geektrust.backend.exceptions.DriverNotFoundException;
import com.geektrust.backend.services.IMatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MatchCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private IMatchService mockMatchService;

    @InjectMocks
    private MatchCommand matchCommand;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
        matchCommand = new MatchCommand(mockMatchService);
    }

    @Test
    public void matchRiderWithDriversExecutedSuccessfully() throws DriverNotFoundException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("command");
        tokens.add("riderId");

        List<String> driversList = new ArrayList<>();
        driversList.add("driver1");
        driversList.add("driver2");
        Match match = new Match("riderId", "riderId", driversList);

        when(mockMatchService.matchRiderWithDrivers("riderId")).thenReturn(match);
        // Act
        matchCommand.execute(tokens);
        // Assert
        String expectedOutput = "DRIVERS_MATCHED " + driversList.stream().collect(Collectors.joining(" "));
        verify(mockMatchService, times(1)).matchRiderWithDrivers("riderId");
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testDriverNotFoundExceptionIsThrownOrNot() throws DriverNotFoundException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("command");
        tokens.add("R1");
        when(mockMatchService.matchRiderWithDrivers("R1"))
                .thenThrow(new DriverNotFoundException());
        // Act
        matchCommand.execute(tokens);
        // Assert
        assertEquals("NO_DRIVERS_AVAILABLE", outputStreamCaptor.toString().trim());
        verify(mockMatchService, times(1)).matchRiderWithDrivers("R1");
    }
}

