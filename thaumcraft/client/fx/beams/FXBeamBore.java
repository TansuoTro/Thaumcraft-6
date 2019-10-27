/*     */ package thaumcraft.client.fx.beams;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.particle.ParticleManager;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ public class FXBeamBore
/*     */   extends Particle
/*     */ {
/*  21 */   public int particle = 16;
/*     */   
/*  23 */   private double offset = 0.0D;
/*     */   
/*  25 */   private double tX = 0.0D;
/*  26 */   private double tY = 0.0D;
/*  27 */   private double tZ = 0.0D;
/*  28 */   private double ptX = 0.0D;
/*  29 */   private double ptY = 0.0D;
/*  30 */   private double ptZ = 0.0D; private float length; private float rotYaw; private float rotPitch; private float prevYaw; private float prevPitch; private Entity targetEntity; private int type; private float endMod; private boolean reverse; private boolean pulse; private int rotationspeed;
/*     */   private float prevSize;
/*     */   public int impact;
/*     */   ResourceLocation beam;
/*     */   ResourceLocation beam1;
/*     */   ResourceLocation beam2;
/*     */   ResourceLocation beam3;
/*     */   
/*  38 */   public FXBeamBore(World par1World, double px, double py, double pz, double tx, double ty, double tz, float red, float green, float blue, int age) { super(par1World, px, py, pz, 0.0D, 0.0D, 0.0D);
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
/* 115 */     this.length = 0.0F;
/* 116 */     this.rotYaw = 0.0F;
/* 117 */     this.rotPitch = 0.0F;
/* 118 */     this.prevYaw = 0.0F;
/* 119 */     this.prevPitch = 0.0F;
/* 120 */     this.targetEntity = null;
/*     */     
/* 122 */     this.type = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     this.endMod = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     this.reverse = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     this.pulse = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     this.rotationspeed = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     this.prevSize = 0.0F;
/*     */ 
/*     */     
/* 150 */     this.beam = new ResourceLocation("thaumcraft", "textures/misc/beam.png");
/* 151 */     this.beam1 = new ResourceLocation("thaumcraft", "textures/misc/beam1.png");
/* 152 */     this.beam2 = new ResourceLocation("thaumcraft", "textures/misc/beam2.png");
/* 153 */     this.beam3 = new ResourceLocation("thaumcraft", "textures/misc/beam3.png"); this.field_70552_h = red; this.field_70553_i = green; this.field_70551_j = blue; func_187115_a(0.02F, 0.02F); this.field_187129_i = 0.0D; this.field_187130_j = 0.0D; this.field_187131_k = 0.0D; this.tX = tx; this.tY = ty; this.tZ = tz; this.prevYaw = this.rotYaw; this.prevPitch = this.rotPitch; this.field_70547_e = age; Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa(); int visibleDistance = 64; if (!(FMLClientHandler.instance().getClient()).field_71474_y.field_74347_j) visibleDistance = 32;  if (renderentity != null && renderentity.func_70011_f(this.field_187126_f, this.field_187127_g, this.field_187128_h) > visibleDistance)
/*     */       this.field_70547_e = 0;  }
/*     */   public void updateBeam(double sx, double sy, double sz, double x, double y, double z) { this.field_187126_f = sx; this.field_187127_g = sy; this.field_187128_h = sz; this.tX = x; this.tY = y; this.tZ = z; for (; this.field_70547_e - this.field_70546_d < 4; this.field_70547_e++); }
/*     */   public void func_189213_a() { this.field_187123_c = this.field_187126_f; this.field_187124_d = this.field_187127_g + this.offset; this.field_187125_e = this.field_187128_h; this.ptX = this.tX; this.ptY = this.tY; this.ptZ = this.tZ; this.prevYaw = this.rotYaw; this.prevPitch = this.rotPitch; float xd = (float)(this.field_187126_f - this.tX); float yd = (float)(this.field_187127_g - this.tY); float zd = (float)(this.field_187128_h - this.tZ); this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd); double var7 = MathHelper.func_76129_c(xd * xd + zd * zd); this.rotYaw = (float)(Math.atan2(xd, zd) * 180.0D / Math.PI); this.rotPitch = (float)(Math.atan2(yd, var7) * 180.0D / Math.PI); this.prevYaw = this.rotYaw; this.prevPitch = this.rotPitch; if (this.impact > 0)
/*     */       this.impact--;  if (this.field_70546_d++ >= this.field_70547_e)
/* 158 */       func_187112_i();  } public void func_180434_a(BufferBuilder wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5) { Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 160 */     GL11.glPushMatrix();
/* 161 */     float var9 = 1.0F;
/* 162 */     float slide = (Minecraft.func_71410_x()).field_71439_g.field_70173_aa;
/* 163 */     float rot = (float)(this.field_187122_b.field_73011_w.getWorldTime() % (360 / this.rotationspeed) * this.rotationspeed) + this.rotationspeed * f;
/*     */     
/* 165 */     float size = 1.0F;
/* 166 */     if (this.pulse) {
/* 167 */       size = Math.min(this.field_70546_d / 4.0F, 1.0F);
/* 168 */       size = (float)(this.prevSize + (size - this.prevSize) * f);
/*     */     } 
/*     */     
/* 171 */     float op = 0.4F;
/* 172 */     if (this.pulse && this.field_70547_e - this.field_70546_d <= 4) {
/* 173 */       op = 0.4F - (4 - this.field_70547_e - this.field_70546_d) * 0.1F;
/*     */     }
/*     */     
/* 176 */     switch (this.type) { default:
/* 177 */         (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam); break;
/* 178 */       case 1: (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam1); break;
/* 179 */       case 2: (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam2); break;
/* 180 */       case 3: (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam3);
/*     */         break; }
/*     */     
/* 183 */     GL11.glTexParameterf(3553, 10242, 10497.0F);
/* 184 */     GL11.glTexParameterf(3553, 10243, 10497.0F);
/*     */     
/* 186 */     GL11.glDisable(2884);
/*     */     
/* 188 */     float var11 = slide + f;
/* 189 */     if (this.reverse) var11 *= -1.0F; 
/* 190 */     float var12 = -var11 * 0.2F - MathHelper.func_76141_d(-var11 * 0.1F);
/*     */     
/* 192 */     GL11.glEnable(3042);
/* 193 */     GL11.glBlendFunc(770, 1);
/* 194 */     GL11.glDepthMask(false);
/*     */     
/* 196 */     float xx = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/* 197 */     float yy = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/* 198 */     float zz = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/* 199 */     GL11.glTranslated(xx, yy, zz);
/*     */     
/* 201 */     float ry = (float)(this.prevYaw + (this.rotYaw - this.prevYaw) * f);
/* 202 */     float rp = (float)(this.prevPitch + (this.rotPitch - this.prevPitch) * f);
/* 203 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 204 */     GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
/* 205 */     GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
/*     */     
/* 207 */     double var44 = -0.15D * size;
/* 208 */     double var17 = 0.15D * size;
/* 209 */     double var44b = -0.15D * size * this.endMod;
/* 210 */     double var17b = 0.15D * size * this.endMod;
/* 211 */     int i = 200;
/* 212 */     int j = i >> 16 & 0xFFFF;
/* 213 */     int k = i & 0xFFFF;
/* 214 */     GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
/* 215 */     for (int t = 0; t < 3; t++) {
/*     */       
/* 217 */       double var29 = (this.length * size * var9);
/* 218 */       double var31 = 0.0D;
/* 219 */       double var33 = 1.0D;
/* 220 */       double var35 = (-1.0F + var12 + t / 3.0F);
/* 221 */       double var37 = (this.length * size * var9) + var35;
/*     */       
/* 223 */       GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/* 224 */       wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 225 */       wr.func_181662_b(var44b, var29, 0.0D).func_187315_a(var33, var37).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/* 226 */       wr.func_181662_b(var44, 0.0D, 0.0D).func_187315_a(var33, var35).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/* 227 */       wr.func_181662_b(var17, 0.0D, 0.0D).func_187315_a(var31, var35).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/* 228 */       wr.func_181662_b(var17b, var29, 0.0D).func_187315_a(var31, var37).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/* 229 */       Tessellator.func_178181_a().func_78381_a();
/*     */     } 
/*     */ 
/*     */     
/* 233 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 234 */     GL11.glDepthMask(true);
/* 235 */     GL11.glBlendFunc(770, 771);
/* 236 */     GL11.glDisable(3042);
/* 237 */     GL11.glEnable(2884);
/* 238 */     GL11.glPopMatrix();
/*     */ 
/*     */     
/* 241 */     if (this.impact > 0) renderImpact(Tessellator.func_178181_a(), f, f1, f2, f3, f4, f5);
/*     */     
/* 243 */     renderSource(Tessellator.func_178181_a(), f, f1, f2, f3, f4, f5);
/*     */     
/* 245 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(ParticleManager.field_110737_b);
/* 246 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 247 */     this.prevSize = size; }
/*     */    public void setRGB(float r, float g, float b) {
/*     */     this.field_70552_h = r;
/*     */     this.field_70553_i = g;
/*     */     this.field_70551_j = b;
/*     */   } public void setType(int type) { this.type = type; } public void setEndMod(float endMod) { this.endMod = endMod; } public void setReverse(boolean reverse) { this.reverse = reverse; } public void setPulse(boolean pulse) { this.pulse = pulse; } public void setRotationspeed(int rotationspeed) { this.rotationspeed = rotationspeed; } public void renderSource(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
/* 253 */     GL11.glPushMatrix();
/* 254 */     GL11.glDepthMask(false);
/* 255 */     GL11.glEnable(3042);
/* 256 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 258 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(UtilsFX.nodeTexture);
/*     */     
/* 260 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/* 261 */     int part = this.field_70546_d % 32;
/*     */     
/* 263 */     float op = 0.8F;
/* 264 */     if (this.pulse && this.field_70547_e - this.field_70546_d <= 4) {
/* 265 */       op = 0.8F - (4 - this.field_70547_e - this.field_70546_d) * 0.2F;
/*     */     }
/* 267 */     float var8 = part / 32.0F;
/* 268 */     float var9 = var8 + 0.03125F;
/* 269 */     float var10 = 0.09375F;
/* 270 */     float var11 = var10 + 0.03125F;
/* 271 */     float var12 = 0.33F;
/*     */     
/* 273 */     float var13 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/* 274 */     float var14 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/* 275 */     float var15 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/* 276 */     int i = 200;
/* 277 */     int j = i >> 16 & 0xFFFF;
/* 278 */     int k = i & 0xFFFF;
/* 279 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 280 */     tessellator.func_178180_c().func_181662_b((var13 - f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 - f3 * var12 - f5 * var12)).func_187315_a(var9, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/* 281 */     tessellator.func_178180_c().func_181662_b((var13 - f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 - f3 * var12 + f5 * var12)).func_187315_a(var9, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/* 282 */     tessellator.func_178180_c().func_181662_b((var13 + f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 + f3 * var12 + f5 * var12)).func_187315_a(var8, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/* 283 */     tessellator.func_178180_c().func_181662_b((var13 + f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 + f3 * var12 - f5 * var12)).func_187315_a(var8, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/*     */     
/* 285 */     tessellator.func_78381_a();
/* 286 */     GL11.glBlendFunc(770, 771);
/* 287 */     GL11.glDisable(3042);
/* 288 */     GL11.glDepthMask(true);
/* 289 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderImpact(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
/* 297 */     GL11.glPushMatrix();
/* 298 */     GL11.glDepthMask(false);
/* 299 */     GL11.glEnable(3042);
/* 300 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 302 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/*     */     
/* 304 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/* 305 */     int part = this.field_70546_d % 16;
/*     */     
/* 307 */     float var8 = part / 16.0F;
/* 308 */     float var9 = var8 + 0.0624375F;
/* 309 */     float var10 = 0.3125F;
/* 310 */     float var11 = var10 + 0.0624375F;
/* 311 */     float var12 = this.endMod / 2.0F / (6 - this.impact);
/*     */     
/* 313 */     float var13 = (float)(this.ptX + (this.tX - this.ptX) * f - field_70556_an);
/* 314 */     float var14 = (float)(this.ptY + (this.tY - this.ptY) * f - field_70554_ao);
/* 315 */     float var15 = (float)(this.ptZ + (this.tZ - this.ptZ) * f - field_70555_ap);
/* 316 */     int i = 200;
/* 317 */     int j = i >> 16 & 0xFFFF;
/* 318 */     int k = i & 0xFFFF;
/* 319 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 320 */     tessellator.func_178180_c().func_181662_b((var13 - f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 - f3 * var12 - f5 * var12)).func_187315_a(var9, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_187314_a(j, k).func_181675_d();
/* 321 */     tessellator.func_178180_c().func_181662_b((var13 - f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 - f3 * var12 + f5 * var12)).func_187315_a(var9, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_187314_a(j, k).func_181675_d();
/* 322 */     tessellator.func_178180_c().func_181662_b((var13 + f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 + f3 * var12 + f5 * var12)).func_187315_a(var8, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_187314_a(j, k).func_181675_d();
/* 323 */     tessellator.func_178180_c().func_181662_b((var13 + f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 + f3 * var12 - f5 * var12)).func_187315_a(var8, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_187314_a(j, k).func_181675_d();
/*     */     
/* 325 */     tessellator.func_78381_a();
/* 326 */     GL11.glBlendFunc(770, 771);
/* 327 */     GL11.glDisable(3042);
/* 328 */     GL11.glDepthMask(true);
/* 329 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\beams\FXBeamBore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */