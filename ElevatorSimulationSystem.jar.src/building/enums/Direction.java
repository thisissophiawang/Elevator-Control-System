/*    */ package building.enums;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum Direction
/*    */ {
/*  7 */   UP("^"),
/*  8 */   DOWN("v"),
/*  9 */   STOPPED("-");
/*    */   
/*    */   private final String display;
/*    */   
/*    */   Direction(String symbol) {
/* 14 */     this.display = symbol;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 19 */     return this.display;
/*    */   }
/*    */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/building/enums/Direction.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */