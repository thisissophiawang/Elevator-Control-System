
# Multi-Elevator Control System Simulation

An interactive Java application that simulates a customizable building elevator system with dynamic request handling and real-time status visualization.

## About/Overview

This project simulates a multi-elevator control system that can be applied to any building with elevators. The system allows for customization of key building parameters: number of floors, number of elevators, and the maximum capacity of each elevator. Following the MVC (Model-View-Controller) design pattern, this application provides a user-friendly graphical interface for building managers to control the elevator system and for end users to interact with elevators through floor requests.

The simulation implements an efficient algorithm for elevator assignment, optimizing travel paths while considering capacity constraints. Real-time visualization allows users to monitor the status of all elevators and building operations throughout the simulation.

## List of Features

- **Building Customization**: Configure number of floors (3-30), elevators (1-10), and elevator capacity (3-20)
- **Interactive Control System**: Start, stop, pause, and step through the elevator simulation
- **Dynamic Request Management**: Add and remove floor requests in real-time
- **Optimized Elevator Assignment**: Algorithm that minimizes travel distance and wait times
- **Real-time Status Visualization**: Monitor building status and all elevator positions/states
- **System Reporting**: Generate building reports reflecting current system status
- **User Feedback**: System messages providing feedback on user interactions
- **Robust Error Handling**: Clear error messages for invalid inputs or operations

## How To Run

To launch the simulation, you will need to execute the JAR file provided with this project.

### Running the JAR file

There are two ways to run the application:

#### Option 1: With Default Parameters
```
java -jar final2jar.jar
```

#### Option 2: With Custom Parameters
```
java -jar final2jar.jar <number of floors> <number of elevators> <number of people>
```

Where:
- `<number of floors>`: Integer between 3 and 30 (inclusive)
- `<number of elevators>`: Integer between 1 and 10 (inclusive)
- `<number of people>`: Integer between 3 and 20 (inclusive)

Example:
```
java -jar final2jar.jar 10 4 15
```
This creates a building with 10 floors, 4 elevators, each with a capacity of 15 people.

## How to Use the Program

Once the program is running:

1. The system will initialize with your specified parameters (or defaults if none provided)
2. Use the control panel to:
   - Start the building's elevator system
   - Stop the system
   - Add floor requests by clicking on floor buttons
   - Remove all pending requests
   - Step through the simulation one action at a time
   - Monitor the status of all elevators and the building
3. The simulation will display:
   - Current position of each elevator
   - Direction of travel
   - Current capacity/occupancy
   - Status (idle, moving, loading/unloading)
4. System messages will provide feedback on your actions
5. Click "Quit" to exit the application

## Design Implementation

The project follows the MVC (Model-View-Controller) design pattern:

- **Model**: Represents the building, elevators, and request management system
- **View**: Provides the graphical user interface for interaction and visualization
- **Controller**: Handles user input and coordinates between the model and view

The elevator assignment algorithm considers:
- Current elevator positions
- Travel direction
- Capacity constraints
- Request priority

## Assumptions

The following assumptions were made during development:

- Each floor has independent up and down request buttons
- Elevators have predefined maximum capacities
- Floor requests are processed without considering passenger transition times
- Only one person can enter the request at a time
- All elevators move at the same speed
- No emergency situations are simulated

## Limitations

Known limitations of the current simulation:

- No dynamic load balancing based on real-time elevator occupancy
- No advanced scheduling for peak hours
- Simplified error handling that doesn't cover all potential real-world elevator faults
- No differentiation between service elevators and passenger elevators
- No handling of emergency situations or power outages

## Future Enhancements

Potential improvements for future versions:

- Implement more sophisticated elevator scheduling algorithms
- Add support for different elevator types (service, express, etc.)
- Include emergency protocols and fault handling
- Add statistics collection and performance analysis
- Implement machine learning for predictive elevator positioning

## License

This project is created for educational purposes. All development is original, with no external dependencies beyond standard Java libraries.
