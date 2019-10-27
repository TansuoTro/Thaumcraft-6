/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class FXFireMote extends Particle {
/*  11 */   float baseScale = 0.0F;
/*  12 */   float baseAlpha = 1.0F;
/*  13 */   int glowlayer = 0;
/*     */   
/*     */   public FXFireMote(World worldIn, double x, double y, double z, double vx, double vy, double vz, float r, float g, float b, float scale, int layer) {
/*  16 */     super(worldIn, x, y, z, 0.0D, 0.0D, 0.0D);
/*  17 */     float colorR = r;
/*  18 */     float colorG = g;
/*  19 */     float colorB = b;
/*  20 */     if (colorR > 1.0D) {
/*  21 */       colorR /= 255.0F;
/*     */     }
/*  23 */     if (colorG > 1.0D) {
/*  24 */       colorG /= 255.0F;
/*     */     }
/*  26 */     if (colorB > 1.0D) {
/*  27 */       colorB /= 255.0F;
/*     */     }
/*  29 */     this.glowlayer = layer;
/*  30 */     func_70538_b(colorR, colorG, colorB);
/*  31 */     this.field_70547_e = 16;
/*  32 */     this.field_70544_f = scale;
/*  33 */     this.baseScale = scale;
/*  34 */     this.field_187129_i = vx;
/*  35 */     this.field_187130_j = vy;
/*  36 */     this.field_187131_k = vz;
/*  37 */     this.field_190014_F = 6.2831855F;
/*  38 */     func_70536_a(7);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70536_a(int particleTextureIndex) {
/*  44 */     this.field_94054_b = particleTextureIndex % 64;
/*  45 */     this.field_94055_c = particleTextureIndex / 64;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180434_a(BufferBuilder worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
/*  50 */     float f = this.field_94054_b / 64.0F;
/*  51 */     float f1 = f + 0.015625F;
/*  52 */     float f2 = this.field_94055_c / 64.0F;
/*  53 */     float f3 = f2 + 0.015625F;
/*  54 */     float f4 = 0.1F * this.field_70544_f;
/*     */     
/*  56 */     if (this.field_187119_C != null) {
/*     */       
/*  58 */       f = this.field_187119_C.func_94209_e();
/*  59 */       f1 = this.field_187119_C.func_94212_f();
/*  60 */       f2 = this.field_187119_C.func_94206_g();
/*  61 */       f3 = this.field_187119_C.func_94210_h();
/*     */     } 
/*     */     
/*  64 */     float f5 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * partialTicks - field_70556_an);
/*  65 */     float f6 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * partialTicks - field_70554_ao);
/*  66 */     float f7 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * partialTicks - field_70555_ap);
/*  67 */     int i = func_189214_a(partialTicks);
/*  68 */     int j = i >> 16 & 0xFFFF;
/*  69 */     int k = i & 0xFFFF;
/*  70 */     Vec3d[] avec3d = { new Vec3d((-rotationX * f4 - rotationXY * f4), (-rotationZ * f4), (-rotationYZ * f4 - rotationXZ * f4)), new Vec3d((-rotationX * f4 + rotationXY * f4), (rotationZ * f4), (-rotationYZ * f4 + rotationXZ * f4)), new Vec3d((rotationX * f4 + rotationXY * f4), (rotationZ * f4), (rotationYZ * f4 + rotationXZ * f4)), new Vec3d((rotationX * f4 - rotationXY * f4), (-rotationZ * f4), (rotationYZ * f4 - rotationXZ * f4)) };
/*     */     
/*  72 */     if (this.field_190014_F != 0.0F) {
/*     */       
/*  74 */       float f8 = this.field_190014_F + (this.field_190014_F - this.field_190015_G) * partialTicks;
/*  75 */       float f9 = MathHelper.func_76134_b(f8 * 0.5F);
/*  76 */       float f10 = MathHelper.func_76126_a(f8 * 0.5F) * (float)field_190016_K.field_72450_a;
/*  77 */       float f11 = MathHelper.func_76126_a(f8 * 0.5F) * (float)field_190016_K.field_72448_b;
/*  78 */       float f12 = MathHelper.func_76126_a(f8 * 0.5F) * (float)field_190016_K.field_72449_c;
/*  79 */       Vec3d vec3d = new Vec3d(f10, f11, f12);
/*     */       
/*  81 */       for (int l = 0; l < 4; l++)
/*     */       {
/*  83 */         avec3d[l] = vec3d.func_186678_a(2.0D * avec3d[l].func_72430_b(vec3d)).func_178787_e(avec3d[l].func_186678_a((f9 * f9) - vec3d.func_72430_b(vec3d))).func_178787_e(vec3d.func_72431_c(avec3d[l]).func_186678_a((2.0F * f9)));
/*     */       }
/*     */     } 
/*     */     
/*  87 */     worldRendererIn.func_181662_b(f5 + (avec3d[0]).field_72450_a, f6 + (avec3d[0]).field_72448_b, f7 + (avec3d[0]).field_72449_c).func_187315_a(f1, f3).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * this.baseAlpha).func_187314_a(j, k).func_181675_d();
/*  88 */     worldRendererIn.func_181662_b(f5 + (avec3d[1]).field_72450_a, f6 + (avec3d[1]).field_72448_b, f7 + (avec3d[1]).field_72449_c).func_187315_a(f1, f2).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * this.baseAlpha).func_187314_a(j, k).func_181675_d();
/*  89 */     worldRendererIn.func_181662_b(f5 + (avec3d[2]).field_72450_a, f6 + (avec3d[2]).field_72448_b, f7 + (avec3d[2]).field_72449_c).func_187315_a(f, f2).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * this.baseAlpha).func_187314_a(j, k).func_181675_d();
/*  90 */     worldRendererIn.func_181662_b(f5 + (avec3d[3]).field_72450_a, f6 + (avec3d[3]).field_72448_b, f7 + (avec3d[3]).field_72449_c).func_187315_a(f, f3).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as * this.baseAlpha).func_187314_a(j, k).func_181675_d();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  95 */   public int func_189214_a(float pTicks) { return 255; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public int func_70537_b() { return this.glowlayer; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189213_a() {
/* 107 */     super.func_189213_a();
/* 108 */     if (this.field_187122_b.field_73012_v.nextInt(6) == 0) {
/* 109 */       this.field_70546_d++;
/*     */     }
/* 111 */     if (this.field_70546_d >= this.field_70547_e) func_187112_i(); 
/* 112 */     float lifespan = this.field_70546_d / this.field_70547_e;
/* 113 */     this.field_70544_f = this.baseScale - this.baseScale * lifespan;
/* 114 */     this.baseAlpha = 1.0F - lifespan;
/* 115 */     this.field_190015_G = this.field_190014_F;
/* 116 */     this.field_190014_F++;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXFireMote.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */