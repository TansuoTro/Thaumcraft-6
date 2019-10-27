/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import net.minecraft.client.particle.Particle;
/*    */ import net.minecraft.client.renderer.BufferBuilder;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class FXSmokeSpiral
/*    */   extends Particle
/*    */ {
/* 13 */   private float radius = 1.0F;
/* 14 */   private int start = 0;
/* 15 */   private int miny = 0;
/*    */ 
/*    */   
/*    */   public FXSmokeSpiral(World world, double d, double d1, double d2, float radius, int start, int miny) {
/* 19 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*    */     
/* 21 */     this.field_70545_g = -0.01F;
/* 22 */     this.field_187129_i = this.field_187130_j = this.field_187131_k = 0.0D;
/* 23 */     this.field_70544_f *= 1.0F;
/* 24 */     this.field_70547_e = 20 + world.field_73012_v.nextInt(10);
/*    */     
/* 26 */     func_187115_a(0.01F, 0.01F);
/* 27 */     this.field_187123_c = this.field_187126_f;
/* 28 */     this.field_187124_d = this.field_187127_g;
/* 29 */     this.field_187125_e = this.field_187128_h;
/* 30 */     this.radius = radius;
/* 31 */     this.start = start;
/* 32 */     this.miny = miny;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_180434_a(BufferBuilder wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 38 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F * this.field_82339_as);
/*    */     
/* 40 */     int particle = (int)(1.0F + this.field_70546_d / this.field_70547_e * 4.0F);
/*    */     
/* 42 */     float r1 = this.start + 720.0F * (this.field_70546_d + f) / this.field_70547_e;
/* 43 */     float r2 = 90.0F - 180.0F * (this.field_70546_d + f) / this.field_70547_e;
/*    */ 
/*    */     
/* 46 */     float mX = -MathHelper.func_76126_a(r1 / 180.0F * 3.1415927F) * MathHelper.func_76134_b(r2 / 180.0F * 3.1415927F);
/*    */     
/* 48 */     float mZ = MathHelper.func_76134_b(r1 / 180.0F * 3.1415927F) * MathHelper.func_76134_b(r2 / 180.0F * 3.1415927F);
/* 49 */     float mY = -MathHelper.func_76126_a(r2 / 180.0F * 3.1415927F);
/* 50 */     mX *= this.radius;
/* 51 */     mY *= this.radius;
/* 52 */     mZ *= this.radius;
/*    */     
/* 54 */     float var8 = (particle % 16) / 64.0F;
/* 55 */     float var9 = var8 + 0.015625F;
/* 56 */     float var10 = (particle / 16) / 64.0F;
/* 57 */     float var11 = var10 + 0.015625F;
/* 58 */     float var12 = 0.15F * this.field_70544_f;
/* 59 */     float var13 = (float)(this.field_187126_f + mX - field_70556_an);
/* 60 */     float var14 = (float)(Math.max(this.field_187127_g + mY, (this.miny + 0.1F)) - field_70554_ao);
/* 61 */     float var15 = (float)(this.field_187128_h + mZ - field_70555_ap);
/* 62 */     float var16 = 1.0F;
/* 63 */     int i = func_189214_a(f);
/* 64 */     int j = i >> 16 & 0xFFFF;
/* 65 */     int k = i & 0xFFFF;
/* 66 */     wr.func_181662_b((var13 - f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 - f3 * var12 - f5 * var12)).func_187315_a(var9, var11)
/* 67 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.66F * this.field_82339_as).func_187314_a(j, k).func_181675_d();
/* 68 */     wr.func_181662_b((var13 - f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 - f3 * var12 + f5 * var12)).func_187315_a(var9, var10)
/* 69 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.66F * this.field_82339_as).func_187314_a(j, k).func_181675_d();
/* 70 */     wr.func_181662_b((var13 + f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 + f3 * var12 + f5 * var12)).func_187315_a(var8, var10)
/* 71 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.66F * this.field_82339_as).func_187314_a(j, k).func_181675_d();
/* 72 */     wr.func_181662_b((var13 + f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 + f3 * var12 - f5 * var12)).func_187315_a(var8, var11)
/* 73 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.66F * this.field_82339_as).func_187314_a(j, k).func_181675_d();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 79 */   public int func_70537_b() { return 1; }
/*    */ 
/*    */   
/*    */   public void func_189213_a() {
/* 83 */     func_82338_g((this.field_70547_e - this.field_70546_d) / this.field_70547_e);
/* 84 */     if (this.field_70546_d++ >= this.field_70547_e)
/* 85 */       func_187112_i(); 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXSmokeSpiral.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */