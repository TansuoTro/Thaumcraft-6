/*    */ package thaumcraft.api.crafting;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Part
/*    */ {
/*    */   private Object source;
/*    */   private Object target;
/*    */   private boolean opp;
/*    */   private int priority;
/*    */   private boolean applyPlayerFacing;
/*    */   
/*    */   public Part(Object source, Object target, boolean opp, int priority) {
/* 17 */     setSource(source);
/* 18 */     setTarget(target);
/* 19 */     setOpp(opp);
/* 20 */     setPriority(priority);
/*    */   }
/*    */   
/*    */   public Part(Object source, Object target, boolean opp) {
/* 24 */     setSource(source);
/* 25 */     setTarget(target);
/* 26 */     setOpp(opp);
/* 27 */     setPriority(50);
/*    */   }
/*    */   
/*    */   public Part(Object source, Object target) {
/* 31 */     setSource(source);
/* 32 */     setTarget(target);
/* 33 */     setOpp(false);
/* 34 */     setPriority(50);
/*    */   }
/*    */ 
/*    */   
/* 38 */   public Object getSource() { return this.source; }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public void setSource(Object source) { this.source = source; }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public Object getTarget() { return this.target; }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public void setTarget(Object target) { this.target = target; }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public boolean isOpp() { return this.opp; }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public void setOpp(boolean opp) { this.opp = opp; }
/*    */ 
/*    */ 
/*    */   
/* 62 */   public int getPriority() { return this.priority; }
/*    */ 
/*    */ 
/*    */   
/* 66 */   public void setPriority(int priority) { this.priority = priority; }
/*    */ 
/*    */ 
/*    */   
/* 70 */   public boolean getApplyPlayerFacing() { return this.applyPlayerFacing; }
/*    */ 
/*    */   
/*    */   public Part setApplyPlayerFacing(boolean applyFacing) {
/* 74 */     this.applyPlayerFacing = applyFacing;
/* 75 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\crafting\Part.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */