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
/*     */ public class Elevator
/*     */   implements ElevatorInterface
/*     */ {
/*  14 */   private static int newElevatorId = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  19 */   private final int id = newElevatorId++;
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
/*  34 */   private final int doorOpenTimeTotal = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   private final int stopWaitTimeTotal = 5;
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
/*  62 */   private int doorOpenTimeLeft = 0;
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
/*  73 */   private int stopWaitTimeLeft = 0;
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
/*  98 */     if (maxFloor < 3 || maxFloor > 30) {
/*  99 */       throw new IllegalArgumentException("maxFloor must be between 3 and 30");
/*     */     }
/* 101 */     if (maxOccupancy < 3 || maxOccupancy > 20) {
/* 102 */       throw new IllegalArgumentException("maxOccupancy must be between 3 and 20");
/*     */     }
/*     */     
/* 105 */     this.maxFloor = maxFloor;
/* 106 */     this.maxOccupancy = maxOccupancy;
/* 107 */     this.currentFloor = 0;
/* 108 */     this.direction = Direction.STOPPED;
/* 109 */     this.outOfService = true;
/* 110 */     this.floorRequests = new boolean[maxFloor];
/* 111 */     this.takingRequests = false;
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
/* 126 */     return this.currentFloor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxFloor() {
/* 136 */     return this.maxFloor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxOccupancy() {
/* 146 */     return this.maxOccupancy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Direction getDirection() {
/* 156 */     return this.direction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getElevatorId() {
/* 164 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDoorClosed() {
/* 174 */     return this.doorClosed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean[] getFloorRequests() {
/* 184 */     return this.floorRequests;
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
/* 206 */     this.outOfService = false;
/* 207 */     this.takingRequests = true;
/* 208 */     clearStopRequests();
/* 209 */     this.doorClosed = true;
/* 210 */     this.doorOpenTimeLeft = 0;
/* 211 */     Objects.requireNonNull(this); this.stopWaitTimeLeft = 5;
/* 212 */     this.direction = Direction.UP;
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
/* 225 */     if (this.outOfService) {
/* 226 */       stepOutOfService();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 231 */     if (!this.doorClosed) {
/* 232 */       stepDoorOpen();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 238 */     if (this.stopWaitTimeLeft > 0) {
/* 239 */       stepTopOrBottom();
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 247 */     if (this.floorRequests[this.currentFloor]) {
/* 248 */       this.doorClosed = false;
/* 249 */       Objects.requireNonNull(this); this.doorOpenTimeLeft = 3;
/* 250 */       this.floorRequests[this.currentFloor] = false;
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
/* 264 */     if (this.currentFloor == 0 && this.direction == Direction.DOWN) {
/* 265 */       this.direction = Direction.STOPPED;
/* 266 */       Objects.requireNonNull(this); this.stopWaitTimeLeft = 5;
/* 267 */       this.takingRequests = true;
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 275 */     if (this.currentFloor == this.maxFloor - 1 && this.direction == Direction.UP) {
/* 276 */       this.direction = Direction.STOPPED;
/* 277 */       Objects.requireNonNull(this); this.stopWaitTimeLeft = 5;
/* 278 */       this.takingRequests = true;
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
/* 289 */     int floorIncrement = 1;
/* 290 */     if (this.direction == Direction.UP) {
/* 291 */       this.currentFloor += floorIncrement;
/* 292 */     } else if (this.direction == Direction.DOWN) {
/* 293 */       this.currentFloor -= floorIncrement;
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
/* 307 */     if (this.currentFloor == 0 && !this.doorClosed) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 312 */     if (this.currentFloor == 0) {
/* 313 */       this.doorClosed = false;
/*     */       
/* 315 */       this.floorRequests[this.currentFloor] = false;
/*     */       
/* 317 */       this.direction = Direction.STOPPED;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 324 */     if (!this.doorClosed) {
/* 325 */       stepDoorOpen();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 331 */     this.direction = Direction.DOWN;
/* 332 */     this.currentFloor--;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void stepDoorOpen() {
/* 339 */     this.doorOpenTimeLeft--;
/* 340 */     if (this.doorOpenTimeLeft == 0) {
/* 341 */       this.doorClosed = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void stepTopOrBottom() {
/* 349 */     this.stopWaitTimeLeft--;
/* 350 */     if (this.stopWaitTimeLeft == 0) {
/* 351 */       this.takingRequests = false;
/* 352 */       if (this.currentFloor == 0) {
/* 353 */         this.direction = Direction.UP;
/* 354 */       } else if (this.currentFloor == this.maxFloor - 1) {
/* 355 */         this.direction = Direction.DOWN;
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
/* 371 */     if (this.currentFloor != 0 && this.currentFloor != this.maxFloor - 1) {
/* 372 */       throw new IllegalStateException("Elevator cannot process requests unless it is at the bottom or top floor.");
/*     */     }
/*     */ 
/*     */     
/* 376 */     if (requests.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 380 */     processStopRequests(requests);
/* 381 */     if (this.currentFloor == 0) {
/* 382 */       this.direction = Direction.UP;
/* 383 */     } else if (this.currentFloor == this.maxFloor - 1) {
/* 384 */       this.direction = Direction.DOWN;
/*     */     } 
/* 386 */     this.takingRequests = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeOutOfService() {
/* 394 */     clearStopRequests();
/* 395 */     this.takingRequests = false;
/* 396 */     this.direction = Direction.DOWN;
/*     */     
/* 398 */     this.outOfService = true;
/* 399 */     this.stopWaitTimeLeft = 0;
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
/* 410 */     return this.takingRequests;
/*     */   }
/*     */ 
/*     */   
/*     */   private void processStopRequests(List<Request> requests) {
/* 415 */     clearStopRequests();
/*     */     
/* 417 */     for (Request request : requests) {
/* 418 */       this.floorRequests[request.getStartFloor()] = true;
/* 419 */       this.floorRequests[request.getEndFloor()] = true;
/*     */     } 
/*     */ 
/*     */     
/* 423 */     this.stopWaitTimeLeft = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearStopRequests() {
/* 430 */     for (int i = 0; i < this.maxFloor; i++) {
/* 431 */       this.floorRequests[i] = false;
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
/* 442 */     ElevatorReport report = new ElevatorReport(this.id, this.currentFloor, this.direction, this.doorClosed, this.floorRequests, this.doorOpenTimeLeft, this.stopWaitTimeLeft, this.outOfService, this.takingRequests);
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
/* 453 */     return report.toString();
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
/* 464 */     return new ElevatorReport(this.id, this.currentFloor, this.direction, this.doorClosed, this.floorRequests, this.doorOpenTimeLeft, this.stopWaitTimeLeft, this.outOfService, this.takingRequests);
/*     */   }
/*     */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSystem.jar!/elevator/Elevator.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */