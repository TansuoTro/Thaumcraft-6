/*    */ package thaumcraft.api.casters;
/*    */ 
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FocusMod
/*    */   extends FocusNode
/*    */ {
/* 10 */   public IFocusElement.EnumUnitType getType() { return IFocusElement.EnumUnitType.MOD; }
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract boolean execute();
/*    */ 
/*    */ 
/*    */   
/* 18 */   public Aspect getAspect() { return null; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\FocusMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */