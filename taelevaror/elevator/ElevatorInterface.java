package elevator;

import building.enums.Direction;
import java.util.List;
import scanerzus.Request;

public interface ElevatorInterface {
  int getElevatorId();
  
  int getMaxFloor();
  
  int getMaxOccupancy();
  
  int getCurrentFloor();
  
  Direction getDirection();
  
  boolean isDoorClosed();
  
  boolean[] getFloorRequests();
  
  void start();
  
  void takeOutOfService();
  
  void step();
  
  void processRequests(List<Request> paramList) throws IllegalArgumentException;
  
  boolean isTakingRequests();
  
  ElevatorReport getElevatorStatus();
}


/* Location:              /Users/sophiawang/Desktop/ElevatorSystem.jar!/elevator/ElevatorInterface.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */