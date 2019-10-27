/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class FXGenericP2P
/*    */   extends FXGeneric
/*    */ {
/*    */   private double targetX;
/*    */   private double targetY;
/*    */   private double targetZ;
/*    */   
/*    */   public FXGenericP2P(World world, double x, double y, double z, double xx, double yy, double zz) {
/* 15 */     super(world, x, y, z, 0.0D, 0.0D, 0.0D);
/* 16 */     func_187115_a(0.1F, 0.1F);
/* 17 */     this.field_187123_c = this.field_187126_f;
/* 18 */     this.field_187124_d = this.field_187127_g;
/* 19 */     this.field_187125_e = this.field_187128_h;
/*    */     
/* 21 */     this.targetX = xx;
/* 22 */     this.targetY = yy;
/* 23 */     this.targetZ = zz;
/*    */     
/* 25 */     double dx = xx - this.field_187126_f;
/* 26 */     double dy = yy - this.field_187127_g;
/* 27 */     double dz = zz - this.field_187128_h;
/* 28 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 3.0F);
/* 29 */     if (base < 1) base = 1; 
/* 30 */     this.field_70547_e = base / 2 + this.field_187136_p.nextInt(base);
/*    */     
/* 32 */     this.field_187123_c = x;
/* 33 */     this.field_187124_d = y;
/* 34 */     this.field_187125_e = z;
/* 35 */     this.field_70548_b = 0.0F;
/* 36 */     this.field_70549_c = 0.0F;
/* 37 */     float f3 = 0.01F;
/* 38 */     this.field_187129_i = ((float)world.field_73012_v.nextGaussian() * f3);
/* 39 */     this.field_187130_j = ((float)world.field_73012_v.nextGaussian() * f3);
/* 40 */     this.field_187131_k = ((float)world.field_73012_v.nextGaussian() * f3);
/*    */     
/* 42 */     this.field_70545_g = 0.2F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_189213_a() {
/* 51 */     super.func_189213_a();
/*    */     
/* 53 */     double dx = this.targetX - this.field_187126_f;
/* 54 */     double dy = this.targetY - this.field_187127_g;
/* 55 */     double dz = this.targetZ - this.field_187128_h;
/* 56 */     double d13 = 0.3D;
/* 57 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*    */     
/* 59 */     if (d11 < 4.0D) {
/* 60 */       this.field_70544_f *= 0.9F;
/* 61 */       d13 = 0.6D;
/*    */     } 
/*    */     
/* 64 */     dx /= d11;
/* 65 */     dy /= d11;
/* 66 */     dz /= d11;
/*    */     
/* 68 */     this.field_187129_i += dx * d13;
/* 69 */     this.field_187130_j += dy * d13;
/* 70 */     this.field_187131_k += dz * d13;
/*    */     
/* 72 */     this.field_187129_i = MathHelper.func_76131_a((float)this.field_187129_i, -0.35F, 0.35F);
/* 73 */     this.field_187130_j = MathHelper.func_76131_a((float)this.field_187130_j, -0.35F, 0.35F);
/* 74 */     this.field_187131_k = MathHelper.func_76131_a((float)this.field_187131_k, -0.35F, 0.35F);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXGenericP2P.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */