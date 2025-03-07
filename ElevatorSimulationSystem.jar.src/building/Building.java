/*     */ package building;
/*     */ 
/*     */ import building.enums.ElevatorSystemStatus;
/*     */ import elevator.Elevator;
/*     */ import elevator.ElevatorInterface;
/*     */ import elevator.ElevatorReport;
/*     */ import java.util.ArrayList;
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
/*     */ public class Building
/*     */   implements BuildingInterface
/*     */ {
/*     */   private ElevatorSystemStatus elevatorSystemStatus;
/*     */   private final int numberOfFloors;
/*     */   private final int numberOfElevators;
/*     */   private final int elevatorCapacity;
/*     */   private final ElevatorInterface[] arrayOfElevators;
/*     */   private final List<Request> upRequests;
/*     */   private final List<Request> downRequests;
/*     */   
/*     */   public Building(int numberOfFloors, int numberOfElevators, int elevatorCapacity) {
/*  32 */     System.out.println("Building constructor called");
/*  33 */     System.out.println("numberOfFloors: " + numberOfFloors);
/*  34 */     System.out.println("numberOfElevators: " + numberOfElevators);
/*  35 */     System.out.println("elevatorCapacity: " + elevatorCapacity);
/*     */     
/*  37 */     System.out.println("\nBuilding is initialized as above");
/*     */     
/*  39 */     if (numberOfFloors < 3 || numberOfFloors > 30)
/*  40 */       throw new IllegalArgumentException("The number of floors must be between 3 and 30."); 
/*  41 */     if (numberOfElevators < 1)
/*  42 */       throw new IllegalArgumentException("The number of elevators must be at least 1."); 
/*  43 */     if (elevatorCapacity < 3 || elevatorCapacity > 20) {
/*  44 */       throw new IllegalArgumentException("The elevator capacity must be between 3 and 20");
/*     */     }
/*     */ 
/*     */     
/*  48 */     this.numberOfFloors = numberOfFloors;
/*  49 */     this.numberOfElevators = numberOfElevators;
/*  50 */     this.elevatorCapacity = elevatorCapacity;
/*     */ 
/*     */     
/*  53 */     this.arrayOfElevators = new ElevatorInterface[numberOfElevators];
/*  54 */     for (int i = 0; i < numberOfElevators; i++) {
/*  55 */       this.arrayOfElevators[i] = (ElevatorInterface)new Elevator(this.numberOfFloors, this.elevatorCapacity);
/*     */     }
/*     */     
/*  58 */     this.upRequests = new ArrayList<>();
/*  59 */     this.downRequests = new ArrayList<>();
/*     */ 
/*     */     
/*  62 */     this.elevatorSystemStatus = ElevatorSystemStatus.outOfService;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRequest(Request request) throws IllegalStateException, IllegalArgumentException {
/*  67 */     if (this.elevatorSystemStatus != ElevatorSystemStatus.running) {
/*  68 */       throw new IllegalStateException("The elevator system is not running. Cannot accept request.");
/*     */     }
/*  70 */     if (request == null)
/*  71 */       throw new IllegalArgumentException("The request is null."); 
/*  72 */     if (request.getStartFloor() < 0 || request.getEndFloor() > this.numberOfFloors) {
/*  73 */       throw new IllegalArgumentException("The request is invalid. It must between 0 and " + this.numberOfFloors - 1 + ".");
/*     */     }
/*  75 */     if (request.getStartFloor() == request.getEndFloor()) {
/*  76 */       throw new IllegalArgumentException("The request cannot start and end on the same floor.");
/*     */     }
/*  78 */     if (request.getStartFloor() < request.getEndFloor()) {
/*  79 */       this.upRequests.add(request);
/*     */     } else {
/*  81 */       this.downRequests.add(request);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElevatorSystem() throws IllegalStateException {
/*  89 */     if (this.elevatorSystemStatus == ElevatorSystemStatus.stopping)
/*  90 */       throw new IllegalStateException("Cannot start the elevator system while it is stopping."); 
/*  91 */     if (this.elevatorSystemStatus == ElevatorSystemStatus.running)
/*     */       return; 
/*  93 */     if (this.elevatorSystemStatus == ElevatorSystemStatus.outOfService) {
/*     */       
/*  95 */       this.elevatorSystemStatus = ElevatorSystemStatus.running;
/*     */       
/*  97 */       for (ElevatorInterface elevator : this.arrayOfElevators) {
/*  98 */         elevator.start();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void stepElevatorSystem() {
/* 105 */     if (this.elevatorSystemStatus == ElevatorSystemStatus.running) {
/*     */       
/* 107 */       allocateRequest();
/*     */       
/* 109 */       for (ElevatorInterface elevator : this.arrayOfElevators) {
/* 110 */         elevator.step();
/*     */       }
/* 112 */     } else if (this.elevatorSystemStatus == ElevatorSystemStatus.stopping) {
/*     */ 
/*     */       
/* 115 */       boolean allElevatorsStopped = true;
/* 116 */       for (ElevatorInterface elevator : this.arrayOfElevators) {
/* 117 */         if (elevator.getCurrentFloor() != 0 || elevator.isDoorClosed()) {
/* 118 */           allElevatorsStopped = false;
/*     */           
/* 120 */           elevator.step();
/*     */         } 
/*     */       } 
/* 123 */       if (allElevatorsStopped)
/*     */       {
/*     */         
/* 126 */         this.elevatorSystemStatus = ElevatorSystemStatus.outOfService;
/*     */       }
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */   }
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
/*     */   private void allocateRequest() {
/* 145 */     if (!this.upRequests.isEmpty() || !this.downRequests.isEmpty())
/*     */     {
/*     */       
/* 148 */       for (ElevatorInterface elevator : this.arrayOfElevators) {
/*     */         
/* 150 */         if (elevator.isTakingRequests() && elevator.getCurrentFloor() == 0) {
/*     */           
/* 152 */           List<Request> upRequestsForAnElevator = getRequestsList(this.upRequests);
/*     */           
/* 154 */           elevator.processRequests(upRequestsForAnElevator);
/*     */         
/*     */         }
/* 157 */         else if (elevator.isTakingRequests() && elevator
/* 158 */           .getCurrentFloor() == this.numberOfFloors - 1) {
/*     */           
/* 160 */           List<Request> downRequestsForAnElevator = getRequestsList(this.downRequests);
/*     */           
/* 162 */           elevator.processRequests(downRequestsForAnElevator);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Request> getRequestsList(List<Request> requestList) {
/* 177 */     List<Request> suitableRequests = new ArrayList<>();
/* 178 */     for (int i = 0; i < this.elevatorCapacity; i++) {
/* 179 */       if (!requestList.isEmpty()) {
/* 180 */         suitableRequests.add(requestList.remove(0));
/*     */       }
/*     */     } 
/* 183 */     return suitableRequests;
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopElevatorSystem() {
/* 188 */     if (this.elevatorSystemStatus == ElevatorSystemStatus.running) {
/*     */       
/* 190 */       this.elevatorSystemStatus = ElevatorSystemStatus.stopping;
/*     */       
/* 192 */       for (ElevatorInterface elevator : this.arrayOfElevators) {
/* 193 */         elevator.takeOutOfService();
/*     */       }
/*     */       
/* 196 */       this.upRequests.clear();
/* 197 */       this.downRequests.clear();
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfFloors() {
/* 206 */     return this.numberOfFloors;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumberOfElevators() {
/* 211 */     return this.numberOfElevators;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getElevatorCapacity() {
/* 216 */     return this.elevatorCapacity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BuildingReport getElevatorSystemStatus() {
/* 222 */     ElevatorReport[] elevatorReports = new ElevatorReport[this.numberOfElevators];
/* 223 */     for (int i = 0; i < this.numberOfElevators; i++) {
/* 224 */       elevatorReports[i] = this.arrayOfElevators[i].getElevatorStatus();
/*     */     }
/* 226 */     return new BuildingReport(this.numberOfFloors, this.numberOfElevators, this.elevatorCapacity, elevatorReports, this.upRequests, this.downRequests, this.elevatorSystemStatus);
/*     */   }
/*     */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/building/Building.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */