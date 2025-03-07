/*     */ package elevator;
/*     */ 
/*     */ import building.enums.Direction;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElevatorReport
/*     */ {
/*     */   private final int elevatorId;
/*     */   private final int currentFloor;
/*     */   private final boolean doorClosed;
/*     */   private final boolean[] floorRequests;
/*     */   private final Direction direction;
/*     */   private final int doorOpenTimer;
/*     */   private final int endWaitTimer;
/*     */   private final boolean outOfService;
/*     */   private final boolean isTakingRequests;
/*     */   
/*     */   public ElevatorReport(int elevatorId, int currentFloor, Direction direction, boolean doorClosed, boolean[] floorRequests, int doorOpenTimer, int endWaitTimer, boolean outOfService, boolean isTakingRequests) {
/*  46 */     this.elevatorId = elevatorId;
/*  47 */     this.currentFloor = currentFloor;
/*  48 */     this.doorClosed = doorClosed;
/*  49 */     this.floorRequests = floorRequests;
/*  50 */     this.direction = direction;
/*  51 */     this.doorOpenTimer = doorOpenTimer;
/*  52 */     this.endWaitTimer = endWaitTimer;
/*  53 */     this.outOfService = outOfService;
/*  54 */     this.isTakingRequests = isTakingRequests;
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
/*     */   public int getElevatorId() {
/*  66 */     return this.elevatorId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentFloor() {
/*  75 */     return this.currentFloor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDoorClosed() {
/*  84 */     return this.doorClosed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean[] getFloorRequests() {
/*  93 */     return this.floorRequests;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Direction getDirection() {
/* 102 */     return this.direction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDoorOpenTimer() {
/* 111 */     return this.doorOpenTimer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEndWaitTimer() {
/* 120 */     return this.endWaitTimer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOutOfService() {
/* 129 */     return this.outOfService;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTakingRequests() {
/* 139 */     return this.isTakingRequests;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 149 */     StringBuilder sb = new StringBuilder();
/*     */ 
/*     */     
/* 152 */     if (this.outOfService && this.currentFloor == 0) {
/* 153 */       sb.append(String.format("Out of Service[Floor %d]", new Object[] { Integer.valueOf(this.currentFloor) }));
/* 154 */       return sb.toString();
/*     */     } 
/*     */     
/* 157 */     if (this.endWaitTimer > 0) {
/* 158 */       sb.append(String.format("Waiting[Floor %d, Time %d]", new Object[] { Integer.valueOf(this.currentFloor), Integer.valueOf(this.endWaitTimer) }));
/* 159 */       return sb.toString();
/*     */     } 
/*     */     
/* 162 */     sb.append(String.format("[%d|%s|", new Object[] {
/* 163 */             Integer.valueOf(this.currentFloor), this.direction
/*     */           }));
/*     */     
/* 166 */     if (this.doorClosed) {
/* 167 */       sb.append("C  ]<");
/*     */     } else {
/* 169 */       sb.append(String.format("O %d]<", new Object[] { Integer.valueOf(this.doorOpenTimer) }));
/*     */     } 
/*     */     
/* 172 */     for (int i = 0; i < this.floorRequests.length; i++) {
/* 173 */       if (this.floorRequests[i]) {
/* 174 */         sb.append(String.format(" %2d", new Object[] { Integer.valueOf(i) }));
/*     */       } else {
/* 176 */         sb.append(" --");
/*     */       } 
/*     */     } 
/* 179 */     sb.append(">");
/*     */     
/* 181 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 192 */     if (this == o) {
/* 193 */       return true;
/*     */     }
/* 195 */     if (!(o instanceof ElevatorReport)) {
/* 196 */       return false;
/*     */     }
/* 198 */     ElevatorReport that = (ElevatorReport)o;
/* 199 */     if (this.elevatorId != that.elevatorId) {
/* 200 */       return false;
/*     */     }
/* 202 */     if (this.currentFloor != that.currentFloor) {
/* 203 */       return false;
/*     */     }
/* 205 */     if (this.doorClosed != that.doorClosed) {
/* 206 */       return false;
/*     */     }
/* 208 */     if (this.doorOpenTimer != that.doorOpenTimer) {
/* 209 */       return false;
/*     */     }
/* 211 */     if (this.endWaitTimer != that.endWaitTimer) {
/* 212 */       return false;
/*     */     }
/* 214 */     if (this.outOfService != that.outOfService) {
/* 215 */       return false;
/*     */     }
/* 217 */     if (this.direction != that.direction) {
/* 218 */       return false;
/*     */     }
/* 220 */     if (this.isTakingRequests != that.isTakingRequests) {
/* 221 */       return false;
/*     */     }
/* 223 */     for (int i = 0; i < this.floorRequests.length; i++) {
/* 224 */       if (this.floorRequests[i] != that.floorRequests[i]) {
/* 225 */         return false;
/*     */       }
/*     */     } 
/* 228 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 236 */     int result = 17;
/* 237 */     result = 31 * result + this.elevatorId;
/* 238 */     result = 31 * result + this.currentFloor;
/* 239 */     result = 31 * result + (this.doorClosed ? 1 : 0);
/* 240 */     result = 31 * result + this.doorOpenTimer;
/* 241 */     result = 31 * result + this.endWaitTimer;
/* 242 */     result = 31 * result + (this.outOfService ? 1 : 0);
/* 243 */     result = 31 * result + (this.isTakingRequests ? 1 : 0);
/* 244 */     result = 31 * result + this.direction.hashCode();
/* 245 */     for (boolean floorRequest : this.floorRequests) {
/* 246 */       result = 31 * result + (floorRequest ? 1 : 0);
/*     */     }
/* 248 */     return result;
/*     */   }
/*     */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/elevator/ElevatorReport.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */