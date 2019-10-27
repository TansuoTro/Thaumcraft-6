/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RedundantTransformation
/*    */   extends Transformation
/*    */ {
/*    */   public void apply(Vector3 vec) {}
/*    */   
/*    */   public void apply(Matrix4 mat) {}
/*    */   
/*    */   public void applyN(Vector3 normal) {}
/*    */   
/* 20 */   public Transformation at(Vector3 point) { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void glApply() {}
/*    */ 
/*    */ 
/*    */   
/* 30 */   public Transformation inverse() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public Transformation merge(Transformation next) { return next; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public boolean isRedundant() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public String toString() { return "Nothing()"; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\RedundantTransformation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */