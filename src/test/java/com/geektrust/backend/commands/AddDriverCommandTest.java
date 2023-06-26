package com.geektrust.backend.commands;

import com.geektrust.backend.exceptions.DriverAlreadyPresentException;
import com.geektrust.backend.services.IDriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AddDriverCommandTest {
    @Mock
    private IDriverService mockDriverService;

    @InjectMocks
    private AddDriverCommand addDriverCommand;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        addDriverCommand = new AddDriverCommand(mockDriverService);
    }

    @Test
    public void addDriverExecutedSuccessfully() throws DriverAlreadyPresentException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("ADD_DRIVER");
        tokens.add("D1");   
        tokens.add("10");
        tokens.add("20");
        // Act
        addDriverCommand.execute(tokens);
        // Assert
        verify(mockDriverService, times(1)).addDriver("D1", 10, 20);
    }

    @Test
    public void testDriverAlreadyPresentExceptionIsThrown() throws DriverAlreadyPresentException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("ADD_DRIVER");
        tokens.add("D1");
        tokens.add("10");
        tokens.add("20");

        doThrow(DriverAlreadyPresentException.class)
                .when(mockDriverService).addDriver("D1", 10, 20);
        // Act
        addDriverCommand.execute(tokens);
        // Assert
        verify(mockDriverService, times(1)).addDriver("D1", 10, 20);
    }
}
