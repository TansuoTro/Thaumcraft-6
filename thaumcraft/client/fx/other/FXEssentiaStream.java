/*     */ package thaumcraft.client.fx.other;
/*     */ 
/*     */ import com.sasmaster.glelwjgl.java.CoreGLE;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.codechicken.lib.vec.Quat;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FXEssentiaStream
/*     */   extends Particle
/*     */ {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   private double startX;
/*     */   private double startY;
/*     */   private double startZ;
/*  39 */   private int count = 0;
/*  40 */   public int length = 20;
/*  41 */   private String key = "";
/*     */   
/*  43 */   private BlockPos startPos = null;
/*  44 */   private BlockPos endPos = null;
/*     */   
/*  46 */   static HashMap<String, FXEssentiaStream> pt = new HashMap();
/*     */   CoreGLE gle; private static final ResourceLocation TEX0 = new ResourceLocation("thaumcraft", "textures/misc/essentia.png"); int layer;
/*     */   double[][] points;
/*     */   float[][] colours;
/*     */   
/*  51 */   public FXEssentiaStream(World w, double par2, double par4, double par6, double tx, double ty, double tz, int count, int color, float scale, int extend, double my) { super(w, par2, par4, par6, 0.0D, 0.0D, 0.0D);
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
/* 117 */     this.gle = new CoreGLE();
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
/* 147 */     this.layer = 1;
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
/* 161 */     this.growing = -1;
/* 162 */     this.vecs = new ArrayList(); this.field_70544_f = (float)(scale * (1.0D + this.field_187136_p.nextGaussian() * 0.15000000596046448D)); this.length = Math.max(20, extend); this.count = count; this.targetX = tx; this.targetY = ty; this.targetZ = tz; BlockPos bp1 = new BlockPos(this.field_187126_f, this.field_187127_g, this.field_187128_h); BlockPos bp2 = new BlockPos(this.targetX, this.targetY, this.targetZ); IBlockState bs = w.func_180495_p(bp1); if (bs.func_177230_c() instanceof thaumcraft.common.blocks.essentia.BlockEssentiaTransport) {
/*     */       EnumFacing f = BlockStateUtils.getFacing(bs); this.field_187126_f += (f.func_82601_c() * 0.05F); this.field_187127_g += (f.func_96559_d() * 0.05F); this.field_187128_h += (f.func_82599_e() * 0.05F);
/*     */     }  double dx = tx - this.field_187126_f; double dy = ty - this.field_187127_g; double dz = tz - this.field_187128_h; int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 21.0F); if (base < 1)
/*     */       base = 1;  this.field_70547_e = base; String k = bp1.func_177986_g() + "" + bp2.func_177986_g() + "" + color; if (pt.containsKey(k)) {
/*     */       FXEssentiaStream trail2 = (FXEssentiaStream)pt.get(k); if (!trail2.field_187133_m && trail2.vecs.size() < trail2.length) {
/*     */         trail2.length += Math.max(extend, 5); trail2.field_70547_e += Math.max(extend, 5); this.field_70547_e = 0;
/*     */       } 
/*     */     }  if (this.field_70547_e > 0) {
/*     */       pt.put(k, this); this.key = k;
/* 171 */     }  this.field_187129_i = (MathHelper.func_76126_a(count / 4.0F) * 0.015F); this.field_187130_j = my + (MathHelper.func_76126_a(count / 3.0F) * 0.015F); this.field_187131_k = (MathHelper.func_76126_a(count / 2.0F) * 0.015F); Color c = new Color(color); this.field_70552_h = c.getRed() / 255.0F; this.field_70553_i = c.getGreen() / 255.0F; this.field_70551_j = c.getBlue() / 255.0F; this.field_70545_g = 0.2F; this.vecs.add(new Quat(0.0D, 0.0D, 0.0D, 0.001D)); this.vecs.add(new Quat(0.0D, 0.0D, 0.0D, 0.001D)); this.startX = this.field_187126_f; this.startY = this.field_187127_g; this.startZ = this.field_187128_h; this.startPos = new BlockPos(this.startX, this.startY, this.startZ); this.endPos = bp2; } double[] radii; public void func_189213_a() { this.field_187123_c = this.field_187126_f;
/* 172 */     this.field_187124_d = this.field_187127_g;
/* 173 */     this.field_187125_e = this.field_187128_h;
/*     */     
/* 175 */     if (this.field_70546_d++ >= this.field_70547_e || this.length < 1) {
/* 176 */       func_187112_i();
/* 177 */       if (pt.containsKey(this.key) && ((FXEssentiaStream)pt.get(this.key)).field_187133_m) {
/* 178 */         pt.remove(this.key);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 183 */     this.field_187130_j += 0.01D * this.field_70545_g;
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
/* 195 */     func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
/*     */     
/* 197 */     this.field_187129_i *= 0.985D;
/* 198 */     this.field_187130_j *= 0.985D;
/* 199 */     this.field_187131_k *= 0.985D;
/*     */     
/* 201 */     this.field_187129_i = MathHelper.func_76131_a((float)this.field_187129_i, -0.05F, 0.05F);
/* 202 */     this.field_187130_j = MathHelper.func_76131_a((float)this.field_187130_j, -0.05F, 0.05F);
/* 203 */     this.field_187131_k = MathHelper.func_76131_a((float)this.field_187131_k, -0.05F, 0.05F);
/*     */     
/* 205 */     double dx = this.targetX - this.field_187126_f;
/* 206 */     double dy = this.targetY - this.field_187127_g;
/* 207 */     double dz = this.targetZ - this.field_187128_h;
/* 208 */     double d13 = 0.01D;
/* 209 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 211 */     dx /= d11;
/* 212 */     dy /= d11;
/* 213 */     dz /= d11;
/*     */     
/* 215 */     this.field_187129_i += dx * d13 / Math.min(1.0D, d11);
/* 216 */     this.field_187130_j += dy * d13 / Math.min(1.0D, d11);
/* 217 */     this.field_187131_k += dz * d13 / Math.min(1.0D, d11);
/*     */     
/* 219 */     float scale = this.field_70544_f * (0.75F + MathHelper.func_76126_a((this.count + this.field_70546_d) / 2.0F) * 0.25F);
/*     */     
/* 221 */     if (d11 < 1.0D) {
/* 222 */       float f = MathHelper.func_76126_a((float)(d11 * 1.5707963267948966D));
/* 223 */       scale *= f;
/* 224 */       this.field_70544_f *= f;
/*     */     } 
/*     */     
/* 227 */     if (this.field_70544_f > 0.001D) {
/* 228 */       this.vecs.add(new Quat(scale, this.field_187126_f - this.startX, this.field_187127_g - this.startY, this.field_187128_h - this.startZ));
/*     */     } else {
/* 230 */       if (this.growing < 0) this.growing = this.field_70546_d; 
/* 231 */       this.length--;
/* 232 */       FXDispatcher.INSTANCE.essentiaDropFx(this.targetX + this.field_187136_p
/* 233 */           .nextGaussian() * 0.07500000298023224D, this.targetY + this.field_187136_p
/* 234 */           .nextGaussian() * 0.07500000298023224D, this.targetZ + this.field_187136_p
/* 235 */           .nextGaussian() * 0.07500000298023224D, this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5F);
/*     */     } 
/*     */ 
/*     */     
/* 239 */     if (this.vecs.size() > this.length) {
/* 240 */       this.vecs.remove(0);
/*     */     }
/*     */     
/* 243 */     this.points = new double[this.vecs.size()][3];
/* 244 */     this.colours = new float[this.vecs.size()][4];
/* 245 */     this.radii = new double[this.vecs.size()];
/* 246 */     int c = this.vecs.size();
/* 247 */     for (Quat v : this.vecs) {
/* 248 */       c--;
/* 249 */       float variance = 1.0F + MathHelper.func_76126_a((c + this.field_70546_d) / 3.0F) * 0.2F;
/*     */       
/* 251 */       float xx = MathHelper.func_76126_a((c + this.field_70546_d) / 6.0F) * 0.03F;
/* 252 */       float yy = MathHelper.func_76126_a((c + this.field_70546_d) / 7.0F) * 0.03F;
/* 253 */       float zz = MathHelper.func_76126_a((c + this.field_70546_d) / 8.0F) * 0.03F;
/*     */       
/* 255 */       this.points[c][0] = v.x + xx;
/* 256 */       this.points[c][1] = v.y + yy;
/* 257 */       this.points[c][2] = v.z + zz;
/*     */       
/* 259 */       this.radii[c] = v.s * variance;
/*     */       
/* 261 */       if (c > this.vecs.size() - 10) {
/* 262 */         this.radii[c] = this.radii[c] * MathHelper.func_76134_b((float)(((c - this.vecs.size() - 12) / 10.0F) * 1.5707963267948966D));
/*     */       }
/*     */       
/* 265 */       if (c == 0) { this.radii[c] = 0.0D; } else if (c == 1) { this.radii[c] = 0.0D; }
/* 266 */       else if (c == 2) { this.radii[c] = (this.field_70544_f * 0.5D + this.radii[c]) / 2.0D; }
/* 267 */       else if (c == 3) { this.radii[c] = (this.field_70544_f + this.radii[c]) / 2.0D; }
/* 268 */       else if (c == 4) { this.radii[c] = (this.field_70544_f + this.radii[c] * 2.0D) / 3.0D; }
/*     */       
/* 270 */       float v2 = 1.0F - MathHelper.func_76126_a((c + this.field_70546_d) / 2.0F) * 0.1F;
/*     */       
/* 272 */       this.colours[c][0] = this.field_70552_h * v2;
/* 273 */       this.colours[c][1] = this.field_70553_i * v2;
/* 274 */       this.colours[c][2] = this.field_70551_j * v2;
/* 275 */       this.colours[c][3] = 1.0F;
/*     */     } 
/*     */ 
/*     */     
/* 279 */     if (this.vecs.size() > 2 && this.field_187136_p.nextBoolean()) {
/* 280 */       int q = this.field_187136_p.nextInt(3);
/* 281 */       if (this.field_187136_p.nextBoolean()) {
/* 282 */         q = this.vecs.size() - 2;
/*     */       }
/* 284 */       FXDispatcher.INSTANCE.essentiaDropFx(((Quat)this.vecs
/* 285 */           .get(q)).x + this.startX, ((Quat)this.vecs.get(q)).y + this.startY, ((Quat)this.vecs.get(q)).z + this.startZ, this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5F);
/*     */     }  } int growing; ArrayList<Quat> vecs; public void func_180434_a(BufferBuilder wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { Tessellator.func_178181_a().func_78381_a(); GL11.glPushMatrix(); this; double ePX = this.startX - field_70556_an; this; double ePY = this.startY - field_70554_ao; this; double ePZ = this.startZ - field_70555_ap; GL11.glTranslated(ePX, ePY, ePZ); if (this.points != null && this.points.length > 2) {
/*     */       (Minecraft.func_71410_x()).field_71446_o.func_110577_a(TEX0); this.gle.set_POLYCYL_TESS(8); this.gle.set__ROUND_TESS_PIECES(1); this.gle.gleSetJoinStyle(1042); this.gle.glePolyCone(this.points.length, this.points, this.colours, this.radii, 0.075F, (this.growing < 0) ? 0.0F : (0.075F * ((this.field_70546_d - this.growing) + f)));
/*     */     } 
/*     */     GL11.glPopMatrix();
/*     */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/*     */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d); } public void setFXLayer(int l) { this.layer = l; } public int func_70537_b() { return this.layer; }
/* 292 */   public void setGravity(float value) { this.field_70545_g = value; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\other\FXEssentiaStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */