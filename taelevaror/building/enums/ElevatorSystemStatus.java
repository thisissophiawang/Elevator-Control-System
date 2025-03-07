/*    */ package building.enums;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ElevatorSystemStatus
/*    */ {
/*  7 */   running("Running"),
/*  8 */   stopping("Stopping"),
/*  9 */   outOfService("Out Of Service");
/*    */   final String display;
/*    */   
/*    */   ElevatorSystemStatus(String display) {
/* 13 */     this.display = display;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 18 */     return this.display;
/*    */   }
/*    */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSystem.jar!/building/enums/ElevatorSystemStatus.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */