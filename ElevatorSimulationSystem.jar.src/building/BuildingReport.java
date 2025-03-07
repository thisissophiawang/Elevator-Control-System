/*     */ package building;
/*     */ 
/*     */ import building.enums.ElevatorSystemStatus;
/*     */ import elevator.ElevatorReport;
/*     */ import java.util.List;
/*     */ import scanerzus.Request;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BuildingReport
/*     */ {
/*     */   int numFloors;
/*     */   int numElevators;
/*     */   int elevatorCapacity;
/*     */   ElevatorReport[] elevatorReports;
/*     */   List<Request> upRequests;
/*     */   List<Request> downRequests;
/*     */   ElevatorSystemStatus systemStatus;
/*     */   
/*     */   public BuildingReport(int numFloors, int numElevators, int elevatorCapacity, ElevatorReport[] elevatorsReports, List<Request> upRequests, List<Request> downRequests, ElevatorSystemStatus systemStatus) {
/*  44 */     this.numFloors = numFloors;
/*  45 */     this.numElevators = numElevators;
/*  46 */     this.elevatorCapacity = elevatorCapacity;
/*  47 */     this.elevatorReports = elevatorsReports;
/*  48 */     this.upRequests = upRequests;
/*  49 */     this.downRequests = downRequests;
/*  50 */     this.systemStatus = systemStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumFloors() {
/*  59 */     return this.numFloors;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumElevators() {
/*  68 */     return this.numElevators;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getElevatorCapacity() {
/*  77 */     return this.elevatorCapacity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElevatorReport[] getElevatorReports() {
/*  86 */     return this.elevatorReports;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Request> getUpRequests() {
/*  95 */     return this.upRequests;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Request> getDownRequests() {
/* 104 */     return this.downRequests;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElevatorSystemStatus getSystemStatus() {
/* 113 */     return this.systemStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 119 */     StringBuilder elevatorReportsString = new StringBuilder();
/* 120 */     int index = 0;
/* 121 */     for (ElevatorReport elevatorReport : this.elevatorReports) {
/* 122 */       elevatorReportsString
/* 123 */         .append("\n")
/* 124 */         .append("Elevator_")
/* 125 */         .append(index)
/* 126 */         .append("[Door Closed: ").append(elevatorReport.isDoorClosed()).append("]: ")
/* 127 */         .append(elevatorReport);
/*     */     }
/*     */ 
/*     */     
/* 131 */     return "\nnumFloors=" + this.numFloors + ", \nnumElevators=" + this.numElevators + ", \nelevatorCapacity=" + this.elevatorCapacity + ", \nelevatorReports=" + elevatorReportsString + ", \nupRequests=" + this.upRequests + ", \ndownRequests=" + this.downRequests + ", \nsystemStatus=" + this.systemStatus;
/*     */   }
/*     */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/building/BuildingReport.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */