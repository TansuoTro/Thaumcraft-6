/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXPlane
/*     */   extends Particle {
/*     */   float angle;
/*     */   float angleYaw;
/*     */   float anglePitch;
/*     */   
/*     */   public FXPlane(World world, double d, double d1, double d2, double m, double m1, double m2, int life) {
/*  19 */     super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     
/*  21 */     this.field_70552_h = 1.0F;
/*  22 */     this.field_70553_i = 1.0F;
/*  23 */     this.field_70551_j = 1.0F;
/*  24 */     this.field_70545_g = 0.0F;
/*  25 */     this.field_187129_i = this.field_187130_j = this.field_187131_k = 0.0D;
/*  26 */     this.field_70547_e = life;
/*     */     
/*  28 */     func_187115_a(0.01F, 0.01F);
/*  29 */     this.field_187123_c = this.field_187126_f;
/*  30 */     this.field_187124_d = this.field_187127_g;
/*  31 */     this.field_187125_e = this.field_187128_h;
/*     */     
/*  33 */     this.field_70544_f = 1.0F;
/*  34 */     this.field_82339_as = 0.0F;
/*     */ 
/*     */     
/*  37 */     double dx = m - this.field_187126_f;
/*  38 */     double dy = m1 - this.field_187127_g;
/*  39 */     double dz = m2 - this.field_187128_h;
/*     */     
/*  41 */     this.field_187129_i = dx / this.field_70547_e;
/*  42 */     this.field_187130_j = dy / this.field_70547_e;
/*  43 */     this.field_187131_k = dz / this.field_70547_e;
/*     */     
/*  45 */     this.field_94054_b = 22;
/*  46 */     this.field_94055_c = 10;
/*     */     
/*  48 */     double d3 = MathHelper.func_76133_a(dx * dx + dz * dz);
/*  49 */     this.angleYaw = 0.0F;
/*  50 */     this.anglePitch = 0.0F;
/*  51 */     if (d3 >= 1.0E-7D) {
/*     */       
/*  53 */       this.angleYaw = (float)(MathHelper.func_181159_b(dz, dx) * 180.0D / Math.PI) - 90.0F;
/*  54 */       this.anglePitch = (float)-(MathHelper.func_181159_b(dy, d3) * 180.0D / Math.PI);
/*     */     } 
/*     */     
/*  57 */     this.angle = (float)(this.field_187136_p.nextGaussian() * 20.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public int func_70537_b() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180434_a(BufferBuilder wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5) {
/*  75 */     Tessellator.func_178181_a().func_78381_a();
/*  76 */     GL11.glPushMatrix();
/*  77 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as / 2.0F);
/*     */     
/*  79 */     this; float var13 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/*  80 */     this; float var14 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/*  81 */     this; float var15 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/*     */     
/*  83 */     GL11.glTranslated(var13, var14, var15);
/*  84 */     GL11.glRotatef(-this.angleYaw + 90.0F, 0.0F, 1.0F, 0.0F);
/*  85 */     GL11.glRotatef(this.anglePitch + 90.0F, 0.0F, 0.0F, 1.0F);
/*  86 */     GL11.glRotatef(this.angle, 0.0F, 1.0F, 0.0F);
/*     */ 
/*     */     
/*  89 */     this.field_94054_b = 22 + Math.round((this.field_70546_d + f) / this.field_70547_e * 8.0F);
/*     */     
/*  91 */     float var8 = this.field_94054_b / 32.0F;
/*  92 */     float var9 = var8 + 0.03125F;
/*  93 */     float var10 = this.field_94055_c / 32.0F;
/*  94 */     float var11 = var10 + 0.03125F;
/*     */     
/*  96 */     float var12 = this.field_70544_f * (0.5F + (this.field_70546_d + f) / this.field_70547_e);
/*     */     
/*  98 */     float var16 = 1.0F;
/*     */ 
/*     */ 
/*     */     
/* 102 */     int i = 240;
/* 103 */     int j = i >> 16 & 0xFFFF;
/* 104 */     int k = i & 0xFFFF;
/*     */     
/* 106 */     GL11.glDisable(2884);
/*     */     
/* 108 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 109 */     wr.func_181662_b(-0.5D * var12, 0.5D * var12, 0.0D).func_187315_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/* 110 */     wr.func_181662_b(0.5D * var12, 0.5D * var12, 0.0D).func_187315_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/* 111 */     wr.func_181662_b(0.5D * var12, -0.5D * var12, 0.0D).func_187315_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/* 112 */     wr.func_181662_b(-0.5D * var12, -0.5D * var12, 0.0D).func_187315_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/* 113 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 115 */     GL11.glEnable(2884);
/*     */     
/* 117 */     GL11.glPopMatrix();
/* 118 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189213_a() {
/* 125 */     this.field_187123_c = this.field_187126_f;
/* 126 */     this.field_187124_d = this.field_187127_g;
/* 127 */     this.field_187125_e = this.field_187128_h;
/* 128 */     float threshold = this.field_70547_e / 5.0F;
/*     */     
/* 130 */     if (this.field_70546_d <= threshold) {
/* 131 */       this.field_82339_as = this.field_70546_d / threshold;
/*     */     } else {
/* 133 */       this.field_82339_as = (this.field_70547_e - this.field_70546_d) / this.field_70547_e;
/*     */     } 
/*     */     
/* 136 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 137 */       func_187112_i();
/*     */     }
/*     */     
/* 140 */     this.field_187126_f += this.field_187129_i;
/* 141 */     this.field_187127_g += this.field_187130_j;
/* 142 */     this.field_187128_h += this.field_187131_k;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 147 */   public void setGravity(float value) { this.field_70545_g = value; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXPlane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */