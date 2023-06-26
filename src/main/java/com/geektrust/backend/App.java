package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.Constants.constants;
import com.geektrust.backend.appConfig.ApplicationConfig;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.exceptions.NoSuchCommandException;

public class App {
	//To run the application:  ./gradlew run --args="Input=Input3.txt"
	public static void main(String[] args) {

		ApplicationConfig applicationConfig = new ApplicationConfig();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
        BufferedReader reader;
        //String inputFile = "sample_input/input1.txt";//args[constants.ZERO];
        String inputFile = "Input.txt";
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(constants.SINGLE_SPACE_STRING));
                commandInvoker.executeCommand(tokens.get(constants.ZERO), tokens);
                line = reader.readLine();
            }
            reader.close();
        } 
        catch (IOException | NoSuchCommandException ex) {
            ex.printStackTrace();
        }
    }
}
