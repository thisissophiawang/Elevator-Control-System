package view;

import controller.BuildingControllerInterface;
import elevator.ElevatorReport;
import java.util.List;
import scanerzus.Request;

public interface BuildingViewInterface {
  void addController(BuildingControllerInterface paramBuildingControllerInterface);
  
  void createBuildingView(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, ElevatorReport[] paramArrayOfElevatorReport);
  
  void switchElevatorSystemView(String paramString);
  
  void updateElevators(ElevatorReport[] paramArrayOfElevatorReport);
  
  void activateRequests(int paramInt);
  
  void addRequestView(List<Request> paramList1, List<Request> paramList2);
  
  void disableRequests();
  
  void disablestep();
}


/* Location:              /Users/sophiawang/Desktop/ElevatorSystem.jar!/view/BuildingViewInterface.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */