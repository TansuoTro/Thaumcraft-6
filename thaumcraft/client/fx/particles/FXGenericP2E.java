/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class FXGenericP2E
/*    */   extends FXGeneric
/*    */ {
/*    */   private Entity target;
/*    */   
/*    */   public FXGenericP2E(World world, double x, double y, double z, Entity target) {
/* 13 */     super(world, x, y, z, 0.0D, 0.0D, 0.0D);
/* 14 */     func_187115_a(0.1F, 0.1F);
/* 15 */     this.field_187123_c = this.field_187126_f;
/* 16 */     this.field_187124_d = this.field_187127_g;
/* 17 */     this.field_187125_e = this.field_187128_h;
/*    */     
/* 19 */     this.target = target;
/*    */     
/* 21 */     double dx = target.field_70165_t - this.field_187126_f;
/* 22 */     double dy = target.field_70163_u - this.field_187127_g;
/* 23 */     double dz = target.field_70161_v - this.field_187128_h;
/* 24 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 5.0F);
/* 25 */     if (base < 1) base = 1; 
/* 26 */     this.field_70547_e = base;
/*    */     
/* 28 */     this.field_187123_c = x;
/* 29 */     this.field_187124_d = y;
/* 30 */     this.field_187125_e = z;
/* 31 */     this.field_70548_b = 0.0F;
/* 32 */     this.field_70549_c = 0.0F;
/* 33 */     float f3 = 0.01F;
/* 34 */     this.field_187129_i = ((float)world.field_73012_v.nextGaussian() * f3);
/* 35 */     this.field_187130_j = ((float)world.field_73012_v.nextGaussian() * f3);
/* 36 */     this.field_187131_k = ((float)world.field_73012_v.nextGaussian() * f3);
/*    */     
/* 38 */     this.field_70545_g = 0.2F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_189213_a() {
/* 47 */     super.func_189213_a();
/*    */     
/* 49 */     double dx = this.target.field_70165_t - this.field_187126_f;
/* 50 */     double dy = this.target.field_70163_u - this.field_187127_g;
/* 51 */     double dz = this.target.field_70161_v - this.field_187128_h;
/* 52 */     double d13 = 0.3D;
/* 53 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*    */     
/* 55 */     if (d11 < 4.0D) {
/* 56 */       this.field_70544_f *= 0.9F;
/* 57 */       d13 = 0.6D;
/*    */     } 
/*    */     
/* 60 */     if (d11 < 0.25D) {
/* 61 */       func_187112_i();
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


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXGenericP2E.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */