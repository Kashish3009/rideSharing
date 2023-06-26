
# Pre-requisites
* Java 1.8/1.11/1.15
* Gradle 6

# How to run the code

We have provided scripts to execute the code. 

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows.

Internally both the scripts run the following commands 

 * `gradle clean build -x test --no-daemon` - This will create a jar file `geektrust.jar` in the `build/libs` folder.
 * `java -jar build/libs/geektrust.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

 Use the build.gradle file provided along with this project. Please change the main class entry under the `jar` task

 ```
 manifest {
        attributes 'Main-Class' : 'com.geektrust.backend.App' //Change this to the main class of your program which will be executed
    }
```
in the build.gradle if your main class has changed.

 # How to execute the unit tests

 `gradle clean test --no-daemon` will execute the unit test cases.

# Help

You can refer our help documents [here](https://help.geektrust.in)
You can read build instructions [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java)

# Requirements and Assumptions
	1. ADD_DRIVER <DRIVER_ID> <X_COORDINATE> <Y_COORDINATE>  
 
	The ADD_DRIVER command allows a driver to join the service. The command should take in the driver's id and current location (x_coordinate and y_coordinate) as arguments.
 
	2. ADD_RIDER <RIDER_ID> <X_COORDINATE> <Y_COORDINATE>  
	 
	The ADD_RIDER command allows a rider to request a ride. The command should take in the rider's id, current location (x_coordinate and y_coordinate), as arguments.
	 
	3. MATCH <RIDER_ID>  
	 
	Matches the rider with the nearest available drivers within 5 kms distance. Print nearest 5 drivers ids in ascending order of their distance from the rider in the following format. In the event of multiple drivers being equidistant, print them in lexicographical order.: 
	DRIVERS_MATCHED <DRIVER_ID1> <DRIVER_ID2>… <DRIVER_ID5> 
	If no drivers are available then print ‘NO_DRIVERS_AVAILABLE’
 
	4. START_RIDE <RIDE_ID> <N> <RIDER_ID>  
	 
	Start the ride with the Nth Driver (1 >= N <= 5). If the match has fewer than N number of drivers, driver is not available, or <RIDE_ID> already exists, then print ‘INVALID_RIDE’ otherwise, print ‘RIDE_STARTED <RIDE_ID>’.
	 
	5. STOP_RIDE <RIDE_ID> <DESTINATION_X_COORDINATE> <DESTINATION_Y_COORDINATE> <TIME_TAKEN_IN_MIN>  
	 
	If the <RIDE_ID> does not exist, or the ride is already stopped, then print ‘INVALID_RIDE’, otherwise, Print ‘RIDE_STOPPED <RIDE_ID>’
	 
	6. BILL <RIDE_ID>  
	 
	Print the total bill of the ride in the format ‘BILL <RIDE_ID> <DRIVER_ID> <AMOUNT>’. To calculate the total bill use the following formula: 
	 
	A base fare of ₹50 is charged for every ride. 
	An additional ₹6.5 is charged for every kilometer traveled. 
	An additional ₹2 is charged for every minute spent in the ride. 
	A service tax of 20% is added to the final amount.
			 
		Note: 
		 
	a. If the ride is not completed then print “RIDE_NOT_COMPLETED” 
If the <RIDE_ID> does not exist, then print ‘INVALID_RIDE’

# Assumptions
1. It is guaranteed that no two drivers or riders will have the same id. 
2. Ride can only be started once the match is completed. 
3. Every start ride request will happen after the match request. 
4. Every start ride request will have a valid rider id. 
5. One rider can make multiple match requests. 
6. Bill for the ride will be calculated based on the distance between the rider's location and the destination. 
7. Bill can only be generated after the ride is completed. 
8. The driver will not be available to accept another rider's request after the ride has started. 
9. Time taken for a ride cannot be negative.
10. All floating point numerical values must be rounded to two decimal places.



