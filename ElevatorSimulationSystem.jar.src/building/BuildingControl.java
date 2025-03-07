package building;

import scanerzus.Request;

public interface BuildingControl {
  void setView();
  
  void startElevatorSystem();
  
  void stopElevatorSystem();
  
  void stepElevatorSystem();
  
  void addNewRequest(Request paramRequest);
  
  void rebuildElevatorSystem(int paramInt1, int paramInt2, int paramInt3);
  
  void exitProgram();
}


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/building/BuildingControl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */