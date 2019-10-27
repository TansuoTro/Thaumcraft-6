/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ 
/*    */ public class SwapYZ
/*    */   extends VariableTransformation
/*    */ {
/*  7 */   public SwapYZ() { super(new Matrix4(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D)); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void apply(Vector3 vec) {
/* 17 */     double vz = vec.z;
/* 18 */     vec.z = vec.y;
/* 19 */     vec.y = vz;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public Transformation inverse() { return this; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\SwapYZ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */