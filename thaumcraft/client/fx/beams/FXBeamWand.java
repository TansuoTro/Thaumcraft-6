/*     */ package thaumcraft.client.fx.beams;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.particle.ParticleManager;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ 
/*     */ public class FXBeamWand
/*     */   extends Particle
/*     */ {
/*  22 */   public int particle = 16;
/*  23 */   EntityLivingBase sourceEntity = null;
/*  24 */   private double offset = 0.0D; private float length; private float rotYaw; private float rotPitch; private float prevYaw; private float prevPitch; private Entity targetEntity; private double tX; private double tY; private double tZ; private double ptX; private double ptY; private double ptZ; private int type; private float endMod; private boolean reverse; private boolean pulse; private int rotationspeed;
/*     */   private float prevSize;
/*     */   public int impact;
/*     */   ResourceLocation beam;
/*     */   ResourceLocation beam1;
/*     */   ResourceLocation beam2;
/*     */   ResourceLocation beam3;
/*     */   
/*  32 */   public FXBeamWand(World par1World, EntityLivingBase p, double tx, double ty, double tz, float red, float green, float blue, int age) { super(par1World, p.field_70165_t, p.field_70163_u, p.field_70161_v, 0.0D, 0.0D, 0.0D);
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
/* 139 */     this.length = 0.0F;
/* 140 */     this.rotYaw = 0.0F;
/* 141 */     this.rotPitch = 0.0F;
/* 142 */     this.prevYaw = 0.0F;
/* 143 */     this.prevPitch = 0.0F;
/* 144 */     this.targetEntity = null;
/* 145 */     this.tX = 0.0D;
/* 146 */     this.tY = 0.0D;
/* 147 */     this.tZ = 0.0D;
/* 148 */     this.ptX = 0.0D;
/* 149 */     this.ptY = 0.0D;
/* 150 */     this.ptZ = 0.0D;
/*     */     
/* 152 */     this.type = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     this.endMod = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     this.reverse = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     this.pulse = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     this.rotationspeed = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     this.prevSize = 0.0F;
/*     */ 
/*     */     
/* 180 */     this.beam = new ResourceLocation("thaumcraft", "textures/misc/beam.png");
/* 181 */     this.beam1 = new ResourceLocation("thaumcraft", "textures/misc/beam1.png");
/* 182 */     this.beam2 = new ResourceLocation("thaumcraft", "textures/misc/beam2.png");
/* 183 */     this.beam3 = new ResourceLocation("thaumcraft", "textures/misc/beam3.png"); this.offset = (p.field_70131_O / 2.0F) + 0.25D; this.field_70552_h = red; this.field_70553_i = green; this.field_70551_j = blue; this.sourceEntity = p; func_187115_a(0.02F, 0.02F); this.field_187129_i = 0.0D; this.field_187130_j = 0.0D; this.field_187131_k = 0.0D; this.tX = tx; this.tY = ty; this.tZ = tz; float xd = (float)(p.field_70165_t - this.tX); float yd = (float)(p.field_70163_u + this.offset - this.tY); float zd = (float)(p.field_70161_v - this.tZ); this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd); double var7 = MathHelper.func_76129_c(xd * xd + zd * zd); this.rotYaw = (float)(Math.atan2(xd, zd) * 180.0D / Math.PI); this.rotPitch = (float)(Math.atan2(yd, var7) * 180.0D / Math.PI); this.prevYaw = this.rotYaw; this.prevPitch = this.rotPitch; this.field_70547_e = age; Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa(); int visibleDistance = 50; if (!(FMLClientHandler.instance().getClient()).field_71474_y.field_74347_j) visibleDistance = 25;  if (renderentity.func_70011_f(p.field_70165_t, p.field_70163_u, p.field_70161_v) > visibleDistance) this.field_70547_e = 0;  }
/*     */   public void updateBeam(double x, double y, double z) { this.tX = x; this.tY = y; this.tZ = z; for (; this.field_70547_e - this.field_70546_d < 4; this.field_70547_e++); }
/*     */   public void func_189213_a() { this.field_187123_c = this.sourceEntity.field_70165_t; this.field_187124_d = this.sourceEntity.field_70163_u + this.offset; this.field_187125_e = this.sourceEntity.field_70161_v; this.ptX = this.tX; this.ptY = this.tY; this.ptZ = this.tZ; this.prevYaw = this.rotYaw; this.prevPitch = this.rotPitch; float xd = (float)(this.sourceEntity.field_70165_t - this.tX); float yd = (float)(this.sourceEntity.field_70163_u + this.offset - this.tY); float zd = (float)(this.sourceEntity.field_70161_v - this.tZ); this.length = MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd); double var7 = MathHelper.func_76129_c(xd * xd + zd * zd); this.rotYaw = (float)(Math.atan2(xd, zd) * 180.0D / Math.PI); for (this.rotPitch = (float)(Math.atan2(yd, var7) * 180.0D / Math.PI); this.rotPitch - this.prevPitch < -180.0F; this.prevPitch -= 360.0F); while (this.rotPitch - this.prevPitch >= 180.0F) this.prevPitch += 360.0F;  while (this.rotYaw - this.prevYaw < -180.0F) this.prevYaw -= 360.0F;  while (this.rotYaw - this.prevYaw >= 180.0F)
/*     */       this.prevYaw += 360.0F;  if (this.impact > 0)
/*     */       this.impact--;  if (this.field_70546_d++ >= this.field_70547_e)
/* 188 */       func_187112_i();  } public void func_180434_a(BufferBuilder wr, Entity p_180434_2_, float f, float f1, float f2, float f3, float f4, float f5) { Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 190 */     GL11.glPushMatrix();
/* 191 */     float var9 = 1.0F;
/* 192 */     float slide = (Minecraft.func_71410_x()).field_71439_g.field_70173_aa;
/* 193 */     float rot = (float)(this.field_187122_b.field_73011_w.getWorldTime() % (360 / this.rotationspeed) * this.rotationspeed) + this.rotationspeed * f;
/*     */     
/* 195 */     float size = 1.0F;
/* 196 */     if (this.pulse) {
/* 197 */       size = Math.min(this.field_70546_d / 4.0F, 1.0F);
/* 198 */       size = (float)(this.prevSize + (size - this.prevSize) * f);
/*     */     } 
/*     */     
/* 201 */     float op = 0.4F;
/* 202 */     if (this.pulse && this.field_70547_e - this.field_70546_d <= 4) {
/* 203 */       op = 0.4F - (4 - this.field_70547_e - this.field_70546_d) * 0.1F;
/*     */     }
/* 205 */     switch (this.type) { default:
/* 206 */         (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam); break;
/* 207 */       case 1: (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam1); break;
/* 208 */       case 2: (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam2); break;
/* 209 */       case 3: (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam3);
/*     */         break; }
/*     */     
/* 212 */     GL11.glTexParameterf(3553, 10242, 10497.0F);
/* 213 */     GL11.glTexParameterf(3553, 10243, 10497.0F);
/*     */     
/* 215 */     GL11.glDisable(2884);
/*     */     
/* 217 */     float var11 = slide + f;
/* 218 */     if (this.reverse) var11 *= -1.0F; 
/* 219 */     float var12 = -var11 * 0.2F - MathHelper.func_76141_d(-var11 * 0.1F);
/*     */     
/* 221 */     GL11.glEnable(3042);
/* 222 */     GL11.glBlendFunc(770, 1);
/* 223 */     GL11.glDepthMask(false);
/*     */     
/* 225 */     double prex = this.sourceEntity.field_70169_q;
/* 226 */     double prey = this.sourceEntity.field_70167_r + this.offset;
/* 227 */     double prez = this.sourceEntity.field_70166_s;
/* 228 */     double px = this.sourceEntity.field_70165_t;
/* 229 */     double py = this.sourceEntity.field_70163_u + this.offset;
/* 230 */     double pz = this.sourceEntity.field_70161_v;
/*     */     
/* 232 */     prex -= (MathHelper.func_76134_b(this.sourceEntity.field_70126_B / 180.0F * 3.141593F) * 0.066F);
/* 233 */     prey -= 0.06D;
/* 234 */     prez -= (MathHelper.func_76126_a(this.sourceEntity.field_70126_B / 180.0F * 3.141593F) * 0.04F);
/* 235 */     Vec3d vec3d = this.sourceEntity.func_70676_i(1.0F);
/* 236 */     prex += vec3d.field_72450_a * 0.3D;
/* 237 */     prey += vec3d.field_72448_b * 0.3D;
/* 238 */     prez += vec3d.field_72449_c * 0.3D;
/*     */     
/* 240 */     px -= (MathHelper.func_76134_b(this.sourceEntity.field_70177_z / 180.0F * 3.141593F) * 0.066F);
/* 241 */     py -= 0.06D;
/* 242 */     pz -= (MathHelper.func_76126_a(this.sourceEntity.field_70177_z / 180.0F * 3.141593F) * 0.04F);
/* 243 */     vec3d = this.sourceEntity.func_70676_i(1.0F);
/* 244 */     px += vec3d.field_72450_a * 0.3D;
/* 245 */     py += vec3d.field_72448_b * 0.3D;
/* 246 */     pz += vec3d.field_72449_c * 0.3D;
/*     */     
/* 248 */     float xx = (float)(prex + (px - prex) * f - field_70556_an);
/* 249 */     float yy = (float)(prey + (py - prey) * f - field_70554_ao);
/* 250 */     float zz = (float)(prez + (pz - prez) * f - field_70555_ap);
/* 251 */     GL11.glTranslated(xx, yy, zz);
/*     */     
/* 253 */     float ry = (float)(this.prevYaw + (this.rotYaw - this.prevYaw) * f);
/* 254 */     float rp = (float)(this.prevPitch + (this.rotPitch - this.prevPitch) * f);
/* 255 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 256 */     GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
/* 257 */     GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
/*     */     
/* 259 */     double var44 = -0.15D * size;
/* 260 */     double var17 = 0.15D * size;
/* 261 */     double var44b = -0.15D * size * this.endMod;
/* 262 */     double var17b = 0.15D * size * this.endMod;
/* 263 */     int i = 200;
/* 264 */     int j = i >> 16 & 0xFFFF;
/* 265 */     int k = i & 0xFFFF;
/*     */     
/* 267 */     GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
/* 268 */     for (int t = 0; t < 3; t++) {
/*     */       
/* 270 */       double var29 = (this.length * size * var9);
/* 271 */       double var31 = 0.0D;
/* 272 */       double var33 = 1.0D;
/* 273 */       double var35 = (-1.0F + var12 + t / 3.0F);
/* 274 */       double var37 = (this.length * size * var9) + var35;
/*     */       
/* 276 */       GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/* 277 */       wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 278 */       wr.func_181662_b(var44b, var29, 0.0D).func_187315_a(var33, var37).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/* 279 */       wr.func_181662_b(var44, 0.0D, 0.0D).func_187315_a(var33, var35).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/* 280 */       wr.func_181662_b(var17, 0.0D, 0.0D).func_187315_a(var31, var35).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/* 281 */       wr.func_181662_b(var17b, var29, 0.0D).func_187315_a(var31, var37).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op).func_187314_a(j, k).func_181675_d();
/* 282 */       Tessellator.func_178181_a().func_78381_a();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 287 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 288 */     GL11.glDepthMask(true);
/* 289 */     GL11.glBlendFunc(770, 771);
/* 290 */     GL11.glDisable(3042);
/* 291 */     GL11.glEnable(2884);
/*     */     
/* 293 */     GL11.glPopMatrix();
/*     */ 
/*     */     
/* 296 */     if (this.impact > 0) renderImpact(Tessellator.func_178181_a(), f, f1, f2, f3, f4, f5);
/*     */     
/* 298 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(ParticleManager.field_110737_b);
/* 299 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 300 */     this.prevSize = size; }
/*     */    public void setRGB(float r, float g, float b) {
/*     */     this.field_70552_h = r;
/*     */     this.field_70553_i = g;
/*     */     this.field_70551_j = b;
/*     */   } public void setType(int type) { this.type = type; } public void setEndMod(float endMod) { this.endMod = endMod; } public void setReverse(boolean reverse) { this.reverse = reverse; } public void setPulse(boolean pulse) { this.pulse = pulse; } public void setRotationspeed(int rotationspeed) { this.rotationspeed = rotationspeed; }
/*     */   public void renderImpact(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
/* 307 */     GL11.glPushMatrix();
/* 308 */     GL11.glDepthMask(false);
/* 309 */     GL11.glEnable(3042);
/* 310 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 312 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/*     */     
/* 314 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/* 315 */     int part = this.field_70546_d % 16;
/*     */     
/* 317 */     float var8 = part / 16.0F;
/* 318 */     float var9 = var8 + 0.0624375F;
/* 319 */     float var10 = 0.3125F;
/* 320 */     float var11 = var10 + 0.0624375F;
/* 321 */     float var12 = this.endMod / 2.0F / (6 - this.impact);
/*     */     
/* 323 */     float var13 = (float)(this.ptX + (this.tX - this.ptX) * f - field_70556_an);
/* 324 */     float var14 = (float)(this.ptY + (this.tY - this.ptY) * f - field_70554_ao);
/* 325 */     float var15 = (float)(this.ptZ + (this.tZ - this.ptZ) * f - field_70555_ap);
/* 326 */     float var16 = 1.0F;
/*     */     
/* 328 */     int i = 200;
/* 329 */     int j = i >> 16 & 0xFFFF;
/* 330 */     int k = i & 0xFFFF;
/*     */     
/* 332 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 333 */     tessellator.func_178180_c().func_181662_b((var13 - f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 - f3 * var12 - f5 * var12)).func_187315_a(var9, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_187314_a(j, k).func_181675_d();
/* 334 */     tessellator.func_178180_c().func_181662_b((var13 - f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 - f3 * var12 + f5 * var12)).func_187315_a(var9, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_187314_a(j, k).func_181675_d();
/* 335 */     tessellator.func_178180_c().func_181662_b((var13 + f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 + f3 * var12 + f5 * var12)).func_187315_a(var8, var10).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_187314_a(j, k).func_181675_d();
/* 336 */     tessellator.func_178180_c().func_181662_b((var13 + f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 + f3 * var12 - f5 * var12)).func_187315_a(var8, var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.66F).func_187314_a(j, k).func_181675_d();
/*     */     
/* 338 */     tessellator.func_78381_a();
/* 339 */     GL11.glBlendFunc(770, 771);
/* 340 */     GL11.glDisable(3042);
/* 341 */     GL11.glDepthMask(true);
/* 342 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\beams\FXBeamWand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */