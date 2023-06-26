package com.geektrust.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


@DisplayName("App Test")
class AppTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
//    @Test
//    @DisplayName("Integration Test #1")
//    void Application_Test() throws Exception {
//        //Arrange
//        List<String> arguments= new ArrayList<>(List.of("Input.txt"));
//		String expectedOutput = "DRIVERS_MATCHED D2 D3 D1\r\n"+
//        "DRIVERS_MATCHED D1 D2 D3\r\n"+
//        "RIDE_STARTED RIDE-101\r\n"+
//        "RIDE_STARTED RIDE-102\r\n"+
//        "RIDE_STOPPED RIDE-101\r\n" +
//        "RIDE_STOPPED RIDE-102\r\n" +
//        "BILL RIDE-101 D2 234.64\r\n"+
//        "BILL RIDE-102 D1 258.00\r\n";
//        //Act
//       // App.run(arguments);
//        App.main(arguments.toArray(new String[0]));
//        //Assert
//        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
//	}
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
