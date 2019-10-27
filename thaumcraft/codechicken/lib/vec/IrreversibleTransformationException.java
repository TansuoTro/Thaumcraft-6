/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IrreversibleTransformationException
/*    */   extends RuntimeException
/*    */ {
/*    */   public ITransformation t;
/*    */   
/* 10 */   public IrreversibleTransformationException(ITransformation t) { this.t = t; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   public String getMessage() { return "The following transformation is irreversible:\n" + this.t; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\IrreversibleTransformationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */