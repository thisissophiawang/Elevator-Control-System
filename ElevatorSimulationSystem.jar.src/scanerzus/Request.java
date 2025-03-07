/*    */ package scanerzus;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Request
/*    */   implements RequestInterface
/*    */ {
/*    */   private final int startFloor;
/*    */   private final int endFloor;
/*    */   
/*    */   public Request(int startFloor, int endFloor) {
/* 26 */     this.startFloor = startFloor;
/* 27 */     this.endFloor = endFloor;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getStartFloor() {
/* 36 */     return this.startFloor;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEndFloor() {
/* 45 */     return this.endFloor;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     return "" + this.startFloor + "->" + this.startFloor;
/*    */   }
/*    */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/scanerzus/Request.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */