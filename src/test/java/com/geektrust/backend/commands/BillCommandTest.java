package com.geektrust.backend.commands;

import com.geektrust.backend.dtos.BillGenerationDTOS;
import com.geektrust.backend.exceptions.InvalidRideException;
import com.geektrust.backend.exceptions.RideNotCompletedException;
import com.geektrust.backend.services.IBillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BillCommandTest {
    @Mock
    private IBillService mockBillService;

    private BillCommand billCommand;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        billCommand = new BillCommand(mockBillService);
    }

    @Test
    public void billGenerationExecutesSuccessfully() throws RideNotCompletedException, InvalidRideException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("BILL");
        tokens.add("RIDE-101");

        //String rideId, String driverId, String amount
        BillGenerationDTOS generatedBill = new BillGenerationDTOS("RIDE-101", "D3","186.72" );
        when(mockBillService.generateBill("RIDE-101")).thenReturn(generatedBill);
        // Act
        billCommand.execute(tokens);
        // Assert
        verify(mockBillService, times(1)).generateBill("RIDE-101");
    }

    @Test
    public void testRideNotCompletedExceptionIsThrown() throws RideNotCompletedException, InvalidRideException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("BILL");
        tokens.add("RIDE-101");

        doThrow(RideNotCompletedException.class)
                .when(mockBillService).generateBill("RIDE-101");
        // Act
        billCommand.execute(tokens);
        // Assert
        verify(mockBillService, times(1)).generateBill("RIDE-101");
        // Add additional assertions based on the expected behavior
    }

    @Test
    public void testInvalidRideExceptionIsThrown() throws RideNotCompletedException, InvalidRideException {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("BILL");
        tokens.add("RIDE-101");

        doThrow(InvalidRideException.class)
                .when(mockBillService).generateBill("RIDE-101");

        // Act
        billCommand.execute(tokens);

        // Assert
        verify(mockBillService, times(1)).generateBill("RIDE-101");
    }   
}
