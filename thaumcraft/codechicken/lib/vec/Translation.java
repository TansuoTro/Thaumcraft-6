/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class Translation
/*    */   extends Transformation
/*    */ {
/*    */   public Vector3 vec;
/*    */   
/* 15 */   public Translation(Vector3 vec) { this.vec = vec; }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public Translation(double x, double y, double z) { this(new Vector3(x, y, z)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public void apply(Vector3 vec) { vec.add(this.vec); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void applyN(Vector3 normal) {}
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void apply(Matrix4 mat) { mat.translate(this.vec); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public Transformation at(Vector3 point) { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 44 */   public void glApply() { GlStateManager.func_179137_b(this.vec.x, this.vec.y, this.vec.z); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public Transformation inverse() { return new Translation(-this.vec.x, -this.vec.y, -this.vec.z); }
/*    */ 
/*    */ 
/*    */   
/*    */   public Transformation merge(Transformation next) {
/* 54 */     if (next instanceof Translation) {
/* 55 */       return new Translation(this.vec.copy().add(((Translation)next).vec));
/*    */     }
/* 57 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 62 */   public boolean isRedundant() { return this.vec.equalsT(Vector3.zero); }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 68 */     return "Translation(" + new BigDecimal(this.vec.x, cont) + ", " + new BigDecimal(this.vec.y, cont) + ", " + new BigDecimal(this.vec.z, cont) + ")";
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\Translation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */