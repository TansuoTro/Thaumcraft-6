/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXVisSparkle extends Particle {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   float sizeMod;
/*     */   
/*     */   public FXVisSparkle(World par1World, double par2, double par4, double par6, double tx, double ty, double tz) {
/*  17 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  40 */     this.sizeMod = 0.0F; this.field_70552_h = this.field_70553_i = this.field_70551_j = 0.6F; this.field_70544_f = 0.0F; this.targetX = tx; this.targetY = ty; this.targetZ = tz; this.field_70547_e = 1000; float f3 = 0.01F; this.field_187129_i = ((float)this.field_187136_p.nextGaussian() * f3);
/*     */     this.field_187130_j = ((float)this.field_187136_p.nextGaussian() * f3);
/*     */     this.field_187131_k = ((float)this.field_187136_p.nextGaussian() * f3);
/*     */     this.sizeMod = (45 + this.field_187136_p.nextInt(15));
/*     */     this.field_70552_h = 0.2F;
/*     */     this.field_70553_i = 0.6F + this.field_187136_p.nextFloat() * 0.3F;
/*     */     this.field_70551_j = 0.2F;
/*  47 */     this.field_70545_g = 0.2F; } public void func_180434_a(BufferBuilder wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5) { float bob = MathHelper.func_76126_a(this.field_70546_d / 3.0F) * 0.3F + 6.0F;
/*     */     
/*  49 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*  50 */     int part = this.field_70546_d % 16;
/*  51 */     float var8 = part / 64.0F;
/*  52 */     float var9 = var8 + 0.015625F;
/*  53 */     float var10 = 0.125F;
/*  54 */     float var11 = var10 + 0.015625F;
/*     */     
/*  56 */     float var12 = 0.1F * this.field_70544_f * bob;
/*     */     
/*  58 */     float var13 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/*  59 */     float var14 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/*  60 */     float var15 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/*  61 */     float var16 = 1.0F;
/*     */     
/*  63 */     int i = 240;
/*  64 */     int j = i >> 16 & 0xFFFF;
/*  65 */     int k = i & 0xFFFF;
/*     */     
/*  67 */     wr.func_181662_b((var13 - f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 - f3 * var12 - f5 * var12)).func_187315_a(var9, var11)
/*  68 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_187314_a(j, k).func_181675_d();
/*  69 */     wr.func_181662_b((var13 - f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 - f3 * var12 + f5 * var12)).func_187315_a(var9, var10)
/*  70 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_187314_a(j, k).func_181675_d();
/*  71 */     wr.func_181662_b((var13 + f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 + f3 * var12 + f5 * var12)).func_187315_a(var8, var10)
/*  72 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_187314_a(j, k).func_181675_d();
/*  73 */     wr.func_181662_b((var13 + f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 + f3 * var12 - f5 * var12)).func_187315_a(var8, var11)
/*  74 */       .func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5F).func_187314_a(j, k).func_181675_d(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189213_a() {
/*  80 */     this.field_187123_c = this.field_187126_f;
/*  81 */     this.field_187124_d = this.field_187127_g;
/*  82 */     this.field_187125_e = this.field_187128_h;
/*     */     
/*  84 */     func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
/*     */     
/*  86 */     this.field_187129_i *= 0.985D;
/*  87 */     this.field_187130_j *= 0.985D;
/*  88 */     this.field_187131_k *= 0.985D;
/*     */     
/*  90 */     double dx = this.targetX - this.field_187126_f;
/*  91 */     double dy = this.targetY - this.field_187127_g;
/*  92 */     double dz = this.targetZ - this.field_187128_h;
/*  93 */     double d13 = 0.10000000149011612D;
/*  94 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*  95 */     if (d11 < 2.0D) this.field_70544_f *= 0.95F; 
/*  96 */     if (d11 < 0.2D) this.field_70547_e = this.field_70546_d; 
/*  97 */     if (this.field_70546_d < 10) this.field_70544_f = this.field_70546_d / this.sizeMod; 
/*  98 */     dx /= d11;
/*  99 */     dy /= d11;
/* 100 */     dz /= d11;
/*     */     
/* 102 */     this.field_187129_i += dx * d13;
/* 103 */     this.field_187130_j += dy * d13;
/* 104 */     this.field_187131_k += dz * d13;
/*     */     
/* 106 */     this.field_187129_i = MathHelper.func_76131_a((float)this.field_187129_i, -0.1F, 0.1F);
/* 107 */     this.field_187130_j = MathHelper.func_76131_a((float)this.field_187130_j, -0.1F, 0.1F);
/* 108 */     this.field_187131_k = MathHelper.func_76131_a((float)this.field_187131_k, -0.1F, 0.1F);
/*     */     
/* 110 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 111 */       func_187112_i();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 116 */   public void setGravity(float value) { this.field_70545_g = value; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXVisSparkle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */