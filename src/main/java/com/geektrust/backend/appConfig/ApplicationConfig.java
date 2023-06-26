package com.geektrust.backend.appConfig;

import com.geektrust.backend.Constants.constants;
import com.geektrust.backend.commands.*;
import com.geektrust.backend.repositories.*;
import com.geektrust.backend.services.*;

public class ApplicationConfig {

    //Repository local variables
    private final IDriverRepository driverRepository = new DriverRepository();
    private final IRideRepository rideRepository = new RideRepository();
    private final IRiderRepository riderRepository = new RiderRepository();
    private final IMatchRepository matchRepository = new MatchRepository();

    //Service local variables
    private final IBillService billService = new BillService( rideRepository, riderRepository);
    private final IDriverService driverService = new DriverService(driverRepository);
    private final IMatchService matchService = new MatchService( riderRepository, driverRepository, matchRepository);
    private final IRiderService riderService = new RiderService(riderRepository);
    private final IRideService rideService = new RideService( rideRepository, driverRepository, matchRepository);
    
    //Command local variables
    private final AddDriverCommand addDriverCommand = new AddDriverCommand(driverService);
    private final AddRiderCommand addRiderCommand = new AddRiderCommand(riderService);
    private final MatchCommand matchCommand = new MatchCommand(matchService);
    private final StartRideCommand startRideCommand = new StartRideCommand(rideService);
    private final StopRideCommand stopRideCommand = new StopRideCommand(rideService);
    private final BillCommand billCommand = new BillCommand(billService);
   
    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register(constants.ADD_DRIVER, addDriverCommand);
        commandInvoker.register(constants.ADD_RIDER, addRiderCommand);
        commandInvoker.register(constants.MATCH, matchCommand);
        commandInvoker.register(constants.START_RIDE, startRideCommand);
        commandInvoker.register(constants.STOP_RIDE, stopRideCommand);
        commandInvoker.register(constants.BILL, billCommand);

        return commandInvoker;
    }
}
