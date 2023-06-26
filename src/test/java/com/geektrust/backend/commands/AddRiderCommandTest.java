package com.geektrust.backend.commands;

import com.geektrust.backend.exceptions.RiderAlreadyPresentException;
import com.geektrust.backend.services.IRiderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AddRiderCommandTest {
    @Mock
    private IRiderService mockRiderService;

    @InjectMocks
    private AddRiderCommand addRiderCommand;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        addRiderCommand = new AddRiderCommand(mockRiderService);
    }

    @Test
    public void addRiderExecutesSuccessfully() throws RiderAlreadyPresentException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("ADD_RIDER");
        tokens.add("R1");
        tokens.add("10");
        tokens.add("20");

        // Act
        addRiderCommand.execute(tokens);

        // Assert
        verify(mockRiderService, times(1)).addRider("R1", 10, 20);
    }

    @Test
    public void testRiderAlreadyPresentExceptionIsThrown() throws RiderAlreadyPresentException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("ADD_RIDER");
        tokens.add("R1");
        tokens.add("10");
        tokens.add("20");

        doThrow(RiderAlreadyPresentException.class)
                .when(mockRiderService).addRider("R1", 10, 20);

        // Act
        addRiderCommand.execute(tokens);

        // Assert
        verify(mockRiderService, times(1)).addRider("R1", 10, 20);
    } 
}
