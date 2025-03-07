/*     */ package elevator;
/*     */ 
/*     */ import building.enums.Direction;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import scanerzus.Request;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Elevator
/*     */   implements ElevatorInterface
/*     */ {
/*  16 */   private static int newElevatorId = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  21 */   private final int id = newElevatorId++;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int maxFloor;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int maxOccupancy;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   private final int doorOpenTimeTotal = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   private final int stopWaitTimeTotal = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean takingRequests;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int currentFloor;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Direction direction;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private int doorOpenTimeLeft = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean doorClosed = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   private int stopWaitTimeLeft = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean[] floorRequests;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean outOfService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Elevator(int maxFloor, int maxOccupancy) {
/* 100 */     if (maxFloor < 3 || maxFloor > 30) {
/* 101 */       throw new IllegalArgumentException("maxFloor must be between 3 and 30");
/*     */     }
/* 103 */     if (maxOccupancy < 3 || maxOccupancy > 20) {
/* 104 */       throw new IllegalArgumentException("maxOccupancy must be between 3 and 20");
/*     */     }
/*     */     
/* 107 */     this.maxFloor = maxFloor;
/* 108 */     this.maxOccupancy = maxOccupancy;
/* 109 */     this.currentFloor = 0;
/* 110 */     this.direction = Direction.STOPPED;
/* 111 */     this.outOfService = true;
/* 112 */     this.floorRequests = new boolean[maxFloor];
/* 113 */     this.takingRequests = false;
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
/*     */   public int getCurrentFloor() {
/* 128 */     return this.currentFloor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxFloor() {
/* 138 */     return this.maxFloor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxOccupancy() {
/* 148 */     return this.maxOccupancy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Direction getDirection() {
/* 158 */     return this.direction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getElevatorId() {
/* 166 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDoorClosed() {
/* 176 */     return this.doorClosed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean[] getFloorRequests() {
/* 186 */     return this.floorRequests;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/* 208 */     this.outOfService = false;
/* 209 */     this.takingRequests = true;
/* 210 */     clearStopRequests();
/* 211 */     this.doorClosed = true;
/* 212 */     this.doorOpenTimeLeft = 0;
/* 213 */     Objects.requireNonNull(this); this.stopWaitTimeLeft = 5;
/* 214 */     this.direction = Direction.UP;
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
/*     */   public void step() {
/* 227 */     if (this.outOfService) {
/* 228 */       stepOutOfService();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 233 */     if (!this.doorClosed) {
/* 234 */       stepDoorOpen();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 240 */     if (this.stopWaitTimeLeft > 0) {
/* 241 */       stepTopOrBottom();
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 249 */     if (this.floorRequests[this.currentFloor]) {
/* 250 */       this.doorClosed = false;
/* 251 */       Objects.requireNonNull(this); this.doorOpenTimeLeft = 3;
/* 252 */       this.floorRequests[this.currentFloor] = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 266 */     if (this.currentFloor == 0 && this.direction == Direction.DOWN) {
/* 267 */       this.direction = Direction.STOPPED;
/* 268 */       Objects.requireNonNull(this); this.stopWaitTimeLeft = 5;
/* 269 */       this.takingRequests = true;
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 277 */     if (this.currentFloor == this.maxFloor - 1 && this.direction == Direction.UP) {
/* 278 */       this.direction = Direction.STOPPED;
/* 279 */       Objects.requireNonNull(this); this.stopWaitTimeLeft = 5;
/* 280 */       this.takingRequests = true;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 291 */     int floorIncrement = 1;
/* 292 */     if (this.direction == Direction.UP) {
/* 293 */       this.currentFloor += floorIncrement;
/* 294 */     } else if (this.direction == Direction.DOWN) {
/* 295 */       this.currentFloor -= floorIncrement;
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
/*     */   private void stepOutOfService() {
/* 309 */     if (this.currentFloor == 0 && !this.doorClosed) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 314 */     if (this.currentFloor == 0) {
/* 315 */       this.doorClosed = false;
/*     */       
/* 317 */       this.floorRequests[this.currentFloor] = false;
/*     */       
/* 319 */       this.direction = Direction.STOPPED;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 326 */     if (!this.doorClosed) {
/* 327 */       stepDoorOpen();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 333 */     this.direction = Direction.DOWN;
/* 334 */     this.currentFloor--;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void stepDoorOpen() {
/* 341 */     this.doorOpenTimeLeft--;
/* 342 */     if (this.doorOpenTimeLeft == 0) {
/* 343 */       this.doorClosed = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void stepTopOrBottom() {
/* 351 */     this.stopWaitTimeLeft--;
/* 352 */     if (this.stopWaitTimeLeft == 0) {
/* 353 */       this.takingRequests = false;
/* 354 */       if (this.currentFloor == 0) {
/* 355 */         this.direction = Direction.UP;
/* 356 */       } else if (this.currentFloor == this.maxFloor - 1) {
/* 357 */         this.direction = Direction.DOWN;
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
/*     */ 
/*     */   
/*     */   public void processRequests(List<Request> requests) throws IllegalStateException {
/* 373 */     if (this.currentFloor != 0 && this.currentFloor != this.maxFloor - 1) {
/* 374 */       throw new IllegalStateException("Elevator cannot process requests unless it is at the bottom or top floor.");
/*     */     }
/*     */ 
/*     */     
/* 378 */     if (requests.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 382 */     processStopRequests(requests);
/* 383 */     if (this.currentFloor == 0) {
/* 384 */       this.direction = Direction.UP;
/* 385 */     } else if (this.currentFloor == this.maxFloor - 1) {
/* 386 */       this.direction = Direction.DOWN;
/*     */     } 
/* 388 */     this.takingRequests = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeOutOfService() {
/* 397 */     clearStopRequests();
/* 398 */     this.takingRequests = false;
/* 399 */     this.direction = Direction.DOWN;
/*     */     
/* 401 */     this.outOfService = true;
/* 402 */     this.stopWaitTimeLeft = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTakingRequests() {
/* 413 */     return this.takingRequests;
/*     */   }
/*     */ 
/*     */   
/*     */   private void processStopRequests(List<Request> requests) {
/* 418 */     clearStopRequests();
/*     */     
/* 420 */     for (Request request : requests) {
/* 421 */       this.floorRequests[request.getStartFloor()] = true;
/* 422 */       this.floorRequests[request.getEndFloor()] = true;
/*     */     } 
/*     */ 
/*     */     
/* 426 */     this.stopWaitTimeLeft = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearStopRequests() {
/* 433 */     for (int i = 0; i < this.maxFloor; i++) {
/* 434 */       this.floorRequests[i] = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 445 */     ElevatorReport report = new ElevatorReport(this.id, this.currentFloor, this.direction, this.doorClosed, this.floorRequests, this.doorOpenTimeLeft, this.stopWaitTimeLeft, this.outOfService, this.takingRequests);
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
/* 456 */     return report.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElevatorReport getElevatorStatus() {
/* 467 */     return new ElevatorReport(this.id, this.currentFloor, this.direction, this.doorClosed, this.floorRequests, this.doorOpenTimeLeft, this.stopWaitTimeLeft, this.outOfService, this.takingRequests);
/*     */   }
/*     */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/elevator/Elevator.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */