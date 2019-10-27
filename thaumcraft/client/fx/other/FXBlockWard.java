/*     */ package thaumcraft.client.fx.other;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.ParticleManager;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class FXBlockWard extends Particle {
/*     */   ResourceLocation[] tex1;
/*     */   EnumFacing side;
/*     */   int rotation;
/*     */   float sx;
/*     */   float sy;
/*     */   float sz;
/*     */   
/*  22 */   public FXBlockWard(World world, double d, double d1, double d2, EnumFacing side, float f, float f1, float f2) { super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
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
/*  48 */     this.tex1 = new ResourceLocation[15];
/*     */ 
/*     */     
/*  51 */     this.rotation = 0;
/*  52 */     this.sx = 0.0F;
/*  53 */     this.sy = 0.0F;
/*  54 */     this.sz = 0.0F; this.side = side; this.field_70545_g = 0.0F; this.field_187129_i = this.field_187130_j = this.field_187131_k = 0.0D; this.field_70547_e = 12 + this.field_187136_p.nextInt(5); func_187115_a(0.01F, 0.01F); this.field_187123_c = this.field_187126_f; this.field_187124_d = this.field_187127_g; this.field_187125_e = this.field_187128_h; this.field_70544_f = (float)(1.4D + this.field_187136_p.nextGaussian() * 0.30000001192092896D); this.rotation = this.field_187136_p.nextInt(360); this.sx = MathHelper.func_76131_a(f - 0.6F + this.field_187136_p.nextFloat() * 0.2F, -0.4F, 0.4F); this.sy = MathHelper.func_76131_a(f1 - 0.6F + this.field_187136_p.nextFloat() * 0.2F, -0.4F, 0.4F); this.sz = MathHelper.func_76131_a(f2 - 0.6F + this.field_187136_p.nextFloat() * 0.2F, -0.4F, 0.4F); if (side.func_82601_c() != 0)
/*     */       this.sx = 0.0F;  if (side.func_96559_d() != 0)
/*     */       this.sy = 0.0F; 
/*     */     if (side.func_82599_e() != 0)
/*     */       this.sz = 0.0F; 
/*     */     for (int a = 0; a < 15; a++)
/*  60 */       this.tex1[a] = new ResourceLocation("thaumcraft", "textures/models/hemis" + (a + 1) + ".png");  } public void func_180434_a(BufferBuilder wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5) { Tessellator.func_178181_a().func_78381_a();
/*  61 */     GL11.glPushMatrix();
/*  62 */     float fade = (this.field_70546_d + f) / this.field_70547_e;
/*  63 */     int frame = Math.min(15, (int)(15.0F * fade));
/*  64 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.tex1[frame - 1]);
/*     */     
/*  66 */     GL11.glDepthMask(false);
/*  67 */     GL11.glEnable(3042);
/*  68 */     GL11.glBlendFunc(770, 1);
/*     */     
/*  70 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.field_82339_as / 2.0F);
/*     */     
/*  72 */     this; float var13 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/*  73 */     this; float var14 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/*  74 */     this; float var15 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/*     */     
/*  76 */     GL11.glTranslated((var13 + this.sx), (var14 + this.sy), (var15 + this.sz));
/*     */     
/*  78 */     GL11.glRotatef(90.0F, this.side.func_96559_d(), -this.side.func_82601_c(), this.side.func_82599_e());
/*  79 */     GL11.glRotatef(this.rotation, 0.0F, 0.0F, 1.0F);
/*  80 */     if (this.side.func_82599_e() > 0) {
/*  81 */       GL11.glTranslated(0.0D, 0.0D, 0.5049999952316284D);
/*  82 */       GL11.glRotatef(180.0F, 0.0F, -1.0F, 0.0F);
/*     */     } else {
/*  84 */       GL11.glTranslated(0.0D, 0.0D, -0.5049999952316284D);
/*     */     } 
/*     */     
/*  87 */     float var12 = this.field_70544_f;
/*     */     
/*  89 */     float var16 = 1.0F;
/*     */     
/*  91 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*  92 */     int i = 240;
/*  93 */     int j = i >> 16 & 0xFFFF;
/*  94 */     int k = i & 0xFFFF;
/*  95 */     wr.func_181662_b(-0.5D * var12, 0.5D * var12, 0.0D).func_187315_a(0.0D, 1.0D).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/*  96 */     wr.func_181662_b(0.5D * var12, 0.5D * var12, 0.0D).func_187315_a(1.0D, 1.0D).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/*  97 */     wr.func_181662_b(0.5D * var12, -0.5D * var12, 0.0D).func_187315_a(1.0D, 0.0D).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/*  98 */     wr.func_181662_b(-0.5D * var12, -0.5D * var12, 0.0D).func_187315_a(0.0D, 0.0D).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0F).func_187314_a(j, k).func_181675_d();
/*  99 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 101 */     GL11.glDisable(3042);
/* 102 */     GL11.glDepthMask(true);
/*     */     
/* 104 */     GL11.glPopMatrix();
/* 105 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(ParticleManager.field_110737_b);
/* 106 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189213_a() {
/* 113 */     this.field_187123_c = this.field_187126_f;
/* 114 */     this.field_187124_d = this.field_187127_g;
/* 115 */     this.field_187125_e = this.field_187128_h;
/* 116 */     float threshold = this.field_70547_e / 5.0F;
/* 117 */     if (this.field_70546_d <= threshold) {
/* 118 */       this.field_82339_as = this.field_70546_d / threshold;
/*     */     } else {
/* 120 */       this.field_82339_as = (this.field_70547_e - this.field_70546_d) / this.field_70547_e;
/*     */     } 
/* 122 */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */     {
/* 124 */       func_187112_i();
/*     */     }
/*     */     
/* 127 */     this.field_187130_j -= 0.04D * this.field_70545_g;
/*     */     
/* 129 */     this.field_187126_f += this.field_187129_i;
/* 130 */     this.field_187127_g += this.field_187130_j;
/* 131 */     this.field_187128_h += this.field_187131_k;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 136 */   public void setGravity(float value) { this.field_70545_g = value; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\other\FXBlockWard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */