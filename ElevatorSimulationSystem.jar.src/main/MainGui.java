/*    */ package main;
/*    */ 
/*    */ import building.Building;
/*    */ import building.BuildingControlImpl;
/*    */ import building.BuildingInterface;
/*    */ import building.BuildingView;
/*    */ import building.SwingBuildingView;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MainGui
/*    */ {
/*    */   public static void main(String[] args) {
/* 21 */     int numFloors = 11;
/* 22 */     int numElevators = 2;
/* 23 */     int numPeople = 3;
/*    */ 
/*    */     
/* 26 */     Building building = new Building(11, 2, 3);
/*    */ 
/*    */     
/* 29 */     SwingBuildingView swingBuildingView = new SwingBuildingView("Building System");
/*    */ 
/*    */     
/* 32 */     BuildingControlImpl buildingControlImpl = new BuildingControlImpl((BuildingView)swingBuildingView, (BuildingInterface)building);
/*    */     
/* 34 */     buildingControlImpl.setView();
/*    */   }
/*    */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/main/MainGui.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */