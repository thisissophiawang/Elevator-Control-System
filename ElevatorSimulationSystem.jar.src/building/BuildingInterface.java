package building;

import scanerzus.Request;

public interface BuildingInterface {
  void addRequest(Request paramRequest) throws IllegalStateException, IllegalArgumentException;
  
  void startElevatorSystem() throws IllegalStateException;
  
  void stepElevatorSystem();
  
  void stopElevatorSystem();
  
  int getNumberOfFloors();
  
  int getNumberOfElevators();
  
  int getElevatorCapacity();
  
  BuildingReport getElevatorSystemStatus();
}


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/building/BuildingInterface.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */