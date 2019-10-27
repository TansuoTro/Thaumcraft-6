/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ public class AxisCycle
/*    */ {
/*  5 */   public static Transformation[] cycles = { new RedundantTransformation(), new VariableTransformation(new Matrix4(0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))
/*    */       {
/*    */         public void apply(Vector3 vec)
/*    */         {
/*  9 */           double d0 = vec.x, d1 = vec.y, d2 = vec.z;
/* 10 */           vec.x = d2; vec.y = d0; vec.z = d1;
/*    */         }
/* 12 */         public Transformation inverse() { return AxisCycle.cycles[2]; }
/*    */       }, new VariableTransformation(new Matrix4(0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))
/*    */       {
/*    */         public void apply(Vector3 vec) {
/* 16 */           double d0 = vec.x, d1 = vec.y, d2 = vec.z;
/* 17 */           vec.x = d1; vec.y = d2; vec.z = d0;
/*    */         }
/* 19 */         public Transformation inverse() { return AxisCycle.cycles[1]; }
/*    */       } };
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\AxisCycle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */