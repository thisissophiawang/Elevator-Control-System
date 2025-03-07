
# Building Elevator Simulation  

An interactive Java application that simulates a customizable **building elevator system** with dynamic request handling and **real-time status visualization**.  

## About/Overview  

This project simulates a **multi-elevator control system**, allowing customization of key building parameters:  
- **Number of Floors**  
- **Number of Elevators**  
- **Elevator Capacity**  

The application follows the **MVC (Model-View-Controller) design pattern**, providing a user-friendly graphical interface where:  
- **Building Managers** can configure and monitor the elevator system.  
- **Users** can request elevators and track their movements in real time.  

The system implements an **optimized elevator assignment algorithm** to minimize travel time while considering **capacity constraints**.  

## Features  

- **Customizable Building Setup**: Configure the **floor number, elevator number, and capacity number** at startup.  
- **Dynamic Request Handling**: Add or remove floor requests using **Start Floor** and **End Floor** selectors.  
- **Optimized Elevator Assignment**: Assigns the nearest available elevator based on current status.  
- **Real-time Status Updates**: Displays elevator position, direction, and occupancy.  
- **Interactive Controls**: Users can **start, stop, step, or pause the simulation** for better control.  
- **Error Handling**: System messages provide feedback on invalid actions.  

## How to Run  

To launch the simulation, execute the **JAR file** provided in this project.  

### Running the JAR file  

#### Option 1: Run with Default Settings  
```sh
java -jar ElevatorSystem.jar
```

If you want to create a **6-floor** building with **3 elevators**, each having a **capacity of 3 people**.  You can click the main menu.

## How to Use the Program  

1. **Create the Building**  
   - Set the **floor number, elevator number, and capacity number** on the **Welcome Screen**.  
   - Click **Create Building** to initialize the simulation.  

2. **Start the Elevator System**  
   - Click **Start Elevator System** to activate the elevators.  

3. **Make Requests**  
   - Select a **Start Floor** and **End Floor**, then click **Add Request** to call an elevator.  

4. **Monitor Elevator Status**  
   - View **real-time elevator positions** under **System Status**.  
   - Elevators will update their **status, direction, and occupancy** as they move.  

5. **Step Through the Simulation**  
   - Enter a **Step Number** and click **Step** to simulate elevator movement manually.  
   - Click **Return to Welcome Page** to restart with new settings.  

## Design Implementation  

This project follows the **MVC (Model-View-Controller) pattern**:  

- **Model**: Represents the **building, elevators, and request queue**.  
- **View**: The **graphical user interface (GUI)** for user interaction.  
- **Controller**: Handles **user input, processes requests, and updates the view**.  

### **Elevator Assignment Algorithm Considerations**  
- **Current elevator positions**  
- **Travel direction**  
- **Capacity constraints**  
- **Request priority**  

## Assumptions  

- Each floor has **independent request buttons** for selecting **start and end floors**.  
- Elevators have **fixed capacities** and cannot exceed their limit.  
- Floor requests are processed **without passenger transition delays**.  
- Elevators move **at the same speed** across all floors.  
- No emergency scenarios (e.g., power failure, fire) are simulated.  

## Limitations  

- **No real-time load balancing** based on elevator occupancy.  
- **No peak-hour scheduling optimization**.  
- **Simplified error handling**, not covering all real-world faults.  
- **No service elevators or emergency protocols**.  

## Future Enhancements  

- **Advanced scheduling algorithms** for better elevator dispatching.  
- **Support for different elevator types** (e.g., service, express).  
- **Emergency handling mechanisms** for system failures.  
- **Usage analytics & performance reporting**.  
- **Machine learning for predictive elevator dispatching**.  

## License  

This project is for **educational purposes**. All development is original, using only **standard Java libraries**.  

