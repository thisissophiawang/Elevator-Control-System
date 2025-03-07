package building;

import scanerzus.Request;

public interface BuildingInterface {
  boolean addRequest(Request paramRequest) throws IllegalStateException;
  
  void step();
  
  boolean startElevatorSystem() throws IllegalStateException;
  
  void stopElevatorSystem();
  
  void stopElevator(int paramInt);
  
  BuildingReport getElevatorSystemStatus();
}

