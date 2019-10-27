/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXBlockRunes extends Particle {
/*     */   double ofx;
/*     */   double ofy;
/*     */   float rotation;
/*     */   int runeIndex;
/*     */   
/*  15 */   public FXBlockRunes(World world, double d, double d1, double d2, float f1, float f2, float f3, int m) { super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     this.ofx = 0.0D;
/*  48 */     this.ofy = 0.0D;
/*  49 */     this.rotation = 0.0F;
/*  50 */     this.runeIndex = 0; if (f1 == 0.0F)
/*     */       f1 = 1.0F;  this.rotation = (this.field_187136_p.nextInt(4) * 90); this.field_70552_h = f1; this.field_70553_i = f2; this.field_70551_j = f3; this.field_70545_g = 0.0F; this.field_187129_i = this.field_187130_j = this.field_187131_k = 0.0D; this.field_70547_e = 3 * m; func_187115_a(0.01F, 0.01F); this.field_187123_c = this.field_187126_f; this.field_187124_d = this.field_187127_g; this.field_187125_e = this.field_187128_h; this.runeIndex = (int)(Math.random() * 16.0D + 224.0D);
/*     */     this.ofx = this.field_187136_p.nextFloat() * 0.2D;
/*     */     this.ofy = -0.3D + this.field_187136_p.nextFloat() * 0.6D;
/*     */     this.field_70544_f = (float)(1.0D + this.field_187136_p.nextGaussian() * 0.10000000149011612D);
/*  55 */     this.field_82339_as = 0.0F; } public void func_180434_a(BufferBuilder wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5) { Tessellator.func_178181_a().func_78381_a();
/*  56 */     GL11.glPushMatrix();
/*     */     
/*  58 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as / 2.0F);
/*     */     
/*  60 */     this; float var13 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/*  61 */     this; float var14 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/*  62 */     this; float var15 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/*     */     
/*  64 */     GL11.glTranslated(var13, var14, var15);
/*  65 */     GL11.glRotatef(this.rotation, 0.0F, 1.0F, 0.0F);
/*  66 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  67 */     GL11.glTranslated(this.ofx, this.ofy, -0.51D);
/*     */     
/*  69 */     float var8 = (this.runeIndex % 16) / 64.0F;
/*  70 */     float var9 = var8 + 0.015625F;
/*  71 */     float var10 = 0.09375F;
/*  72 */     float var11 = var10 + 0.015625F;
/*  73 */     float var12 = 0.3F * this.field_70544_f;
/*     */     
/*  75 */     float var16 = 1.0F;
/*     */     
/*  77 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */     
/*  79 */     int i = 240;
/*  80 */     int j = i >> 16 & 0xFFFF;
/*  81 */     int k = i & 0xFFFF;
/*     */     
/*  83 */     wr.func_181662_b(-0.5D * var12, 0.5D * var12, 0.0D).func_187315_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/*  84 */     wr.func_181662_b(0.5D * var12, 0.5D * var12, 0.0D).func_187315_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/*  85 */     wr.func_181662_b(0.5D * var12, -0.5D * var12, 0.0D).func_187315_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/*  86 */     wr.func_181662_b(-0.5D * var12, -0.5D * var12, 0.0D).func_187315_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/*  87 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/*  89 */     GL11.glPopMatrix();
/*  90 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d); }
/*     */   
/*     */   public void setScale(float s) { this.field_70544_f = s; }
/*     */   
/*     */   public void setOffsetX(double f) { this.ofx = f; }
/*     */   
/*     */   public void func_189213_a() {
/*  97 */     this.field_187123_c = this.field_187126_f;
/*  98 */     this.field_187124_d = this.field_187127_g;
/*  99 */     this.field_187125_e = this.field_187128_h;
/* 100 */     float threshold = this.field_70547_e / 5.0F;
/*     */     
/* 102 */     if (this.field_70546_d <= threshold) {
/* 103 */       this.field_82339_as = this.field_70546_d / threshold;
/*     */     } else {
/* 105 */       this.field_82339_as = (this.field_70547_e - this.field_70546_d) / this.field_70547_e;
/*     */     } 
/*     */     
/* 108 */     if (this.field_70546_d++ >= this.field_70547_e) {
/* 109 */       func_187112_i();
/*     */     }
/*     */     
/* 112 */     this.field_187130_j -= 0.04D * this.field_70545_g;
/*     */     
/* 114 */     this.field_187126_f += this.field_187129_i;
/* 115 */     this.field_187127_g += this.field_187130_j;
/* 116 */     this.field_187128_h += this.field_187131_k;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void setGravity(float value) { this.field_70545_g = value; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXBlockRunes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */