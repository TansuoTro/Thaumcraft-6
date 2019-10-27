/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ public abstract class VariableTransformation
/*    */   extends Transformation
/*    */ {
/*    */   public Matrix4 mat;
/*    */   
/* 12 */   public VariableTransformation(Matrix4 mat) { this.mat = mat; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public void applyN(Vector3 normal) { apply(normal); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public void apply(Matrix4 mat) { mat.multiply(this.mat); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 31 */   public void glApply() { this.mat.glApply(); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\VariableTransformation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */