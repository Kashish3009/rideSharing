package com.geektrust.backend.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BillGenerationDTOSTest {
    @Mock
    private BillGenerationDTOS mockBillGeneration;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockBillGeneration = mock(BillGenerationDTOS.class);
    }

    @Test
    void toStringinBillGenerationDTOSReturnsFormattedString() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String amount = "10.00";
        BillGenerationDTOS billDTO = new BillGenerationDTOS(rideId, driverId, amount);
        String expectedString = "BILL ride123 driver456 10.00";
        // Act
        String actualString = billDTO.toString();
        // Assert
        assertEquals(expectedString, actualString);
    }

    @Test
    void getAmountReturnsAmountInBillGeneration() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String amount = "10.00";
        BillGenerationDTOS billDTO = new BillGenerationDTOS(rideId, driverId, amount);

        // Act
        String actualAmount = billDTO.getAmount();

        // Assert
        assertEquals(amount, actualAmount);
    }

    @Test
    void getDriverIdReturnsDriverId() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String amount = "10.00";
        BillGenerationDTOS billDTO = new BillGenerationDTOS(rideId, driverId, amount);
        // Act
        String actualDriverId = billDTO.getDriverId();
        // Assert
        assertEquals(driverId, actualDriverId);
    }

    @Test
    void getRideIdReturnsRideId() {
        // Arrange
        String rideId = "ride123";
        String driverId = "driver456";
        String amount = "10.00";
        BillGenerationDTOS billDTO = new BillGenerationDTOS(rideId, driverId, amount);
        // Act
        String actualRideId = billDTO.getRideId();
        // Assert
        assertEquals(rideId, actualRideId);
    }
}
