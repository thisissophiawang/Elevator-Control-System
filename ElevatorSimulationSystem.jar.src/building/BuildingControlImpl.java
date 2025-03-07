/*     */ package building;
/*     */ 
/*     */ import building.enums.ElevatorSystemStatus;
/*     */ import scanerzus.Request;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BuildingControlImpl
/*     */   implements BuildingControl
/*     */ {
/*     */   private BuildingInterface model;
/*     */   private final BuildingView view;
/*     */   
/*     */   public BuildingControlImpl(BuildingView view, BuildingInterface model) {
/*  20 */     this.view = view;
/*  21 */     this.model = model;
/*  22 */     this.view.addControl(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void displayDashboard() {
/*  29 */     this.view.displayBuildingInfo(this.model.getElevatorSystemStatus());
/*  30 */     this.view.displayRequestInfo(this.model.getElevatorSystemStatus());
/*  31 */     this.view.displayElevatorInfo(this.model.getElevatorSystemStatus());
/*  32 */     this.view.displayElevatorSimulation(this.model.getElevatorSystemStatus());
/*  33 */     this.view.setButtonEnabled(this.model.getElevatorSystemStatus());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setView() {
/*  38 */     displayDashboard();
/*     */   }
/*     */ 
/*     */   
/*     */   public void startElevatorSystem() {
/*  43 */     this.model.startElevatorSystem();
/*  44 */     displayDashboard();
/*  45 */     this.view.displaySystemStatus("Elevator system is now running! Read to add requests!");
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopElevatorSystem() {
/*  50 */     this.model.stopElevatorSystem();
/*  51 */     displayDashboard();
/*  52 */     this.view.displaySystemStatus("Elevator system is turning off. Press <Step> to take all elevators to the ground floor.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stepElevatorSystem() {
/*  58 */     this.model.stepElevatorSystem();
/*  59 */     displayDashboard();
/*  60 */     if (this.model.getElevatorSystemStatus().getSystemStatus() == ElevatorSystemStatus.outOfService) {
/*     */       
/*  62 */       this.view.displaySystemStatus("Elevator system is now off.Press <Start> to restart or <Exit> to exit the program. Press <Rebuild> to rebuild the system.");
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  67 */     if (this.model.getElevatorSystemStatus().getSystemStatus() == ElevatorSystemStatus.stopping) {
/*     */       
/*  69 */       this.view.displaySystemStatus("Elevator system is stopping. Press <Step> to continue.");
/*     */       
/*     */       return;
/*     */     } 
/*  73 */     this.view.displaySystemStatus("Elevator system stepped.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void addNewRequest(Request request) {
/*     */     try {
/*  79 */       this.model.addRequest(request);
/*  80 */       displayDashboard();
/*     */ 
/*     */ 
/*     */       
/*  84 */       String sb = "Request added: " + request.getStartFloor() + " to " + request.getEndFloor();
/*  85 */       this.view.displaySystemStatus(sb);
/*  86 */     } catch (IllegalStateException|IllegalArgumentException e) {
/*  87 */       this.view.displaySystemStatus(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void rebuildElevatorSystem(int numFloors, int numElevators, int elevatorCapacity) {
/*     */     try {
/*  94 */       if (numFloors == 0 || numElevators == 0 || elevatorCapacity == 0) {
/*  95 */         throw new IllegalArgumentException("fields cannot be null");
/*     */       }
/*  97 */       this.model = new Building(numFloors, numElevators, elevatorCapacity);
/*  98 */       displayDashboard();
/*  99 */       String sb = "Successful Rebuild! with " + numFloors + " floors, " + numElevators + " elevators, and " + elevatorCapacity + " capacity. Press start to begin.";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 107 */       this.view.displaySystemStatus(sb);
/* 108 */     } catch (IllegalArgumentException e) {
/* 109 */       this.view.displaySystemStatus(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void exitProgram() {
/* 115 */     System.exit(0);
/*     */   }
/*     */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/building/BuildingControlImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */