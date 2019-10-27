/*    */ package thaumcraft.api.casters;
/*    */ 
/*    */ public interface IFocusElement
/*    */ {
/*    */   String getKey();
/*    */   
/*    */   String getResearch();
/*    */   
/*    */   EnumUnitType getType();
/*    */   
/*    */   public enum EnumUnitType {
/* 12 */     EFFECT, MEDIUM, MOD, PACKAGE;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\IFocusElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */