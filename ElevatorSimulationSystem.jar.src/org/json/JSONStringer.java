/*    */ package org.json;
/*    */ 
/*    */ import java.io.StringWriter;
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
/*    */ 
/*    */ 
/*    */ public class JSONStringer
/*    */   extends JSONWriter
/*    */ {
/*    */   public JSONStringer() {
/* 44 */     super(new StringWriter());
/*    */   }
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
/*    */   public String toString() {
/* 57 */     return (this.mode == 'd') ? this.writer.toString() : null;
/*    */   }
/*    */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/org/json/JSONStringer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */