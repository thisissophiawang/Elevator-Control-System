package building;

public interface BuildingView {
  void setButtonEnabled(BuildingReport paramBuildingReport);
  
  void displayBuildingInfo(BuildingReport paramBuildingReport);
  
  void displayRequestInfo(BuildingReport paramBuildingReport);
  
  void displayElevatorInfo(BuildingReport paramBuildingReport);
  
  void displayElevatorSimulation(BuildingReport paramBuildingReport);
  
  void addControl(BuildingControl paramBuildingControl);
  
  void displaySystemStatus(String paramString);
}


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/building/BuildingView.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */