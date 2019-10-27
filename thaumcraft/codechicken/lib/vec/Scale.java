/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class Scale
/*    */   extends Transformation
/*    */ {
/*    */   public Vector3 factor;
/*    */   
/* 15 */   public Scale(Vector3 factor) { this.factor = factor; }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public Scale(double factor) { this(new Vector3(factor, factor, factor)); }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public Scale(double x, double y, double z) { this(new Vector3(x, y, z)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void apply(Vector3 vec) { vec.multiply(this.factor); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void applyN(Vector3 normal) {}
/*    */ 
/*    */ 
/*    */   
/* 37 */   public void apply(Matrix4 mat) { mat.scale(this.factor); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 43 */   public void glApply() { GlStateManager.func_179139_a(this.factor.x, this.factor.y, this.factor.z); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public Transformation inverse() { return new Scale(1.0D / this.factor.x, 1.0D / this.factor.y, 1.0D / this.factor.z); }
/*    */ 
/*    */ 
/*    */   
/*    */   public Transformation merge(Transformation next) {
/* 53 */     if (next instanceof Scale) {
/* 54 */       return new Scale(this.factor.copy().multiply(((Scale)next).factor));
/*    */     }
/* 56 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public boolean isRedundant() { return this.factor.equalsT(Vector3.one); }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 67 */     return "Scale(" + new BigDecimal(this.factor.x, cont) + ", " + new BigDecimal(this.factor.y, cont) + ", " + new BigDecimal(this.factor.z, cont) + ")";
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\Scale.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */