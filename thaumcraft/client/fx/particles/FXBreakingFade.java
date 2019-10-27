/*    */ package thaumcraft.client.fx.particles;
/*    */ 
/*    */ import net.minecraft.client.particle.ParticleBreaking;
/*    */ import net.minecraft.client.renderer.BufferBuilder;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class FXBreakingFade
/*    */   extends ParticleBreaking
/*    */ {
/* 20 */   public FXBreakingFade(World worldIn, double p_i1197_2_, double p_i1197_4_, double p_i1197_6_, double p_i1197_8_, double p_i1197_10_, double p_i1197_12_, Item p_i1197_14_, int p_i1197_15_) { super(worldIn, p_i1197_2_, p_i1197_4_, p_i1197_6_, p_i1197_8_, p_i1197_10_, p_i1197_12_, p_i1197_14_, p_i1197_15_); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public FXBreakingFade(World worldIn, double p_i1196_2_, double p_i1196_4_, double p_i1196_6_, Item p_i1196_8_, int p_i1196_9_) { super(worldIn, p_i1196_2_, p_i1196_4_, p_i1196_6_, p_i1196_8_, p_i1196_9_); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public FXBreakingFade(World worldIn, double p_i1195_2_, double p_i1195_4_, double p_i1195_6_, Item p_i1195_8_) { super(worldIn, p_i1195_2_, p_i1195_4_, p_i1195_6_, p_i1195_8_); }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public void setParticleMaxAge(int particleMaxAge) { this.field_70547_e = particleMaxAge; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public void setParticleGravity(float f) { this.field_70545_g = f; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public int func_70537_b() { return 1; }
/*    */ 
/*    */   
/*    */   public void setSpeed(double x, double y, double z) {
/* 49 */     this.field_187129_i = x;
/* 50 */     this.field_187130_j = y;
/* 51 */     this.field_187131_k = z;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_180434_a(BufferBuilder p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 57 */     GlStateManager.func_179132_a(false);
/* 58 */     float f6 = (this.field_94054_b + this.field_70548_b / 4.0F) / 16.0F;
/* 59 */     float f7 = f6 + 0.015609375F;
/* 60 */     float f8 = (this.field_94055_c + this.field_70549_c / 4.0F) / 16.0F;
/* 61 */     float f9 = f8 + 0.015609375F;
/* 62 */     float f10 = 0.1F * this.field_70544_f;
/* 63 */     float fade = 1.0F - this.field_70546_d / this.field_70547_e;
/*    */     
/* 65 */     if (this.field_187119_C != null) {
/*    */       
/* 67 */       f6 = this.field_187119_C.func_94214_a((this.field_70548_b / 4.0F * 16.0F));
/* 68 */       f7 = this.field_187119_C.func_94214_a(((this.field_70548_b + 1.0F) / 4.0F * 16.0F));
/* 69 */       f8 = this.field_187119_C.func_94207_b((this.field_70549_c / 4.0F * 16.0F));
/* 70 */       f9 = this.field_187119_C.func_94207_b(((this.field_70549_c + 1.0F) / 4.0F * 16.0F));
/*    */     } 
/* 72 */     int i = func_189214_a(p_180434_3_);
/* 73 */     int j = i >> 16 & 0xFFFF;
/* 74 */     int k = i & 0xFFFF;
/* 75 */     float f11 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * p_180434_3_ - field_70556_an);
/* 76 */     float f12 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * p_180434_3_ - field_70554_ao);
/* 77 */     float f13 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * p_180434_3_ - field_70555_ap);
/* 78 */     p_180434_1_.func_181662_b((f11 - p_180434_4_ * f10 - p_180434_7_ * f10), (f12 - p_180434_5_ * f10), (f13 - p_180434_6_ * f10 - p_180434_8_ * f10))
/* 79 */       .func_187315_a(f6, f9).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * fade).func_187314_a(j, k).func_181675_d();
/* 80 */     p_180434_1_.func_181662_b((f11 - p_180434_4_ * f10 + p_180434_7_ * f10), (f12 + p_180434_5_ * f10), (f13 - p_180434_6_ * f10 + p_180434_8_ * f10))
/* 81 */       .func_187315_a(f6, f8).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * fade).func_187314_a(j, k).func_181675_d();
/* 82 */     p_180434_1_.func_181662_b((f11 + p_180434_4_ * f10 + p_180434_7_ * f10), (f12 + p_180434_5_ * f10), (f13 + p_180434_6_ * f10 + p_180434_8_ * f10))
/* 83 */       .func_187315_a(f7, f8).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * fade).func_187314_a(j, k).func_181675_d();
/* 84 */     p_180434_1_.func_181662_b((f11 + p_180434_4_ * f10 - p_180434_7_ * f10), (f12 - p_180434_5_ * f10), (f13 + p_180434_6_ * f10 - p_180434_8_ * f10))
/* 85 */       .func_187315_a(f7, f9).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * fade).func_187314_a(j, k).func_181675_d();
/* 86 */     GlStateManager.func_179132_a(true);
/*    */   }
/*    */   
/*    */   public void boom() {
/* 90 */     float f = (float)(Math.random() + Math.random() + 1.0D) * 0.15F;
/* 91 */     float f1 = MathHelper.func_76133_a(this.field_187129_i * this.field_187129_i + this.field_187130_j * this.field_187130_j + this.field_187131_k * this.field_187131_k);
/* 92 */     this.field_187129_i = this.field_187129_i / f1 * f * 0.9640000000596046D;
/* 93 */     this.field_187130_j = this.field_187130_j / f1 * f * 0.9640000000596046D + 0.10000000149011612D;
/* 94 */     this.field_187131_k = this.field_187131_k / f1 * f * 0.9640000000596046D;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXBreakingFade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */