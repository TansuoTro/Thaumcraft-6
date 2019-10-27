/*     */ package thaumcraft.client.fx.other;
/*     */ 
/*     */ import com.sasmaster.glelwjgl.java.CoreGLE;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.ARBShaderObjects;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.lib.ender.ShaderCallback;
/*     */ import thaumcraft.client.lib.ender.ShaderHelper;
/*     */ import thaumcraft.codechicken.lib.vec.Quat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FXVoidStream
/*     */   extends Particle
/*     */ {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   private double startX;
/*     */   private double startY;
/*     */   private double startZ;
/*  43 */   private int seed = 0;
/*  44 */   public int length = 20;
/*     */   
/*     */   private final ShaderCallback shaderCallback;
/*  47 */   private static final ResourceLocation starsTexture = new ResourceLocation("textures/entity/end_portal.png"); CoreGLE gle; int layer;
/*     */   double[][] points;
/*     */   float[][] colours;
/*     */   double[] radii;
/*     */   int growing;
/*     */   ArrayList<Quat> vecs;
/*     */   
/*  54 */   public FXVoidStream(World w, double par2, double par4, double par6, double tx, double ty, double tz, int seed, float scale) { super(w, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     this.gle = new CoreGLE();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     this.layer = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     this.growing = -1;
/* 164 */     this.vecs = new ArrayList(); this.shaderCallback = new ShaderCallback() { public void call(int shader) { Minecraft mc = Minecraft.func_71410_x(); int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw"); ARBShaderObjects.glUniform1fARB(x, (float)((mc.field_71439_g.field_70177_z * 2.0F) * Math.PI / 360.0D)); int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch"); ARBShaderObjects.glUniform1fARB(z, -((float)((mc.field_71439_g.field_70125_A * 2.0F) * Math.PI / 360.0D))); } }
/*     */       ; this.field_70544_f = (float)(scale * (1.0D + this.field_187136_p.nextGaussian() * 0.15000000596046448D)); this.length = 40; this.seed = seed; this.targetX = tx; this.targetY = ty; this.targetZ = tz; double dx = tx - this.field_187126_f; double dy = ty - this.field_187127_g; double dz = tz - this.field_187128_h; int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 21.0F); if (base < 1)
/*     */       base = 1;  this.field_70547_e = base * 2; this.field_187129_i = (MathHelper.func_76126_a(seed / 4.0F) * 0.025F); this.field_187130_j = (MathHelper.func_76126_a(seed / 3.0F) * 0.025F); this.field_187131_k = (MathHelper.func_76126_a(seed / 2.0F) * 0.025F); this.field_70545_g = 0.2F; this.vecs.add(new Quat(0.0D, 0.0D, 0.0D, 0.001D)); this.vecs.add(new Quat(0.0D, 0.0D, 0.0D, 0.001D)); this.startX = this.field_187126_f; this.startY = this.field_187127_g;
/* 167 */     this.startZ = this.field_187128_h; } public void func_189213_a() { this.field_187123_c = this.field_187126_f;
/* 168 */     this.field_187124_d = this.field_187127_g;
/* 169 */     this.field_187125_e = this.field_187128_h;
/*     */     
/* 171 */     if (this.field_70546_d++ >= this.field_70547_e || this.length < 1) {
/* 172 */       func_187112_i();
/*     */       
/*     */       return;
/*     */     } 
/* 176 */     this.field_187130_j += 0.01D * this.field_70545_g;
/*     */     
/* 178 */     func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
/*     */     
/* 180 */     this.field_187129_i *= 0.985D;
/* 181 */     this.field_187130_j *= 0.985D;
/* 182 */     this.field_187131_k *= 0.985D;
/*     */     
/* 184 */     this.field_187129_i = MathHelper.func_76131_a((float)this.field_187129_i, -0.04F, 0.04F);
/* 185 */     this.field_187130_j = MathHelper.func_76131_a((float)this.field_187130_j, -0.04F, 0.04F);
/* 186 */     this.field_187131_k = MathHelper.func_76131_a((float)this.field_187131_k, -0.04F, 0.04F);
/*     */     
/* 188 */     double dx = this.targetX - this.field_187126_f;
/* 189 */     double dy = this.targetY - this.field_187127_g;
/* 190 */     double dz = this.targetZ - this.field_187128_h;
/* 191 */     double d13 = 0.01D;
/* 192 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 194 */     dx /= d11;
/* 195 */     dy /= d11;
/* 196 */     dz /= d11;
/*     */     
/* 198 */     this.field_187129_i += dx * d13 / Math.min(1.0D, d11) + this.field_187136_p.nextGaussian() * 0.014999999664723873D;
/* 199 */     this.field_187130_j += dy * d13 / Math.min(1.0D, d11) + this.field_187136_p.nextGaussian() * 0.014999999664723873D;
/* 200 */     this.field_187131_k += dz * d13 / Math.min(1.0D, d11) + this.field_187136_p.nextGaussian() * 0.014999999664723873D;
/*     */     
/* 202 */     float scale = this.field_70544_f * (0.75F + MathHelper.func_76126_a((this.seed + this.field_70546_d) / 2.0F) * 0.25F);
/*     */     
/* 204 */     if (d11 < 0.5D) {
/* 205 */       float f = MathHelper.func_76126_a((float)(d11 * 1.5707963267948966D));
/* 206 */       scale *= f;
/* 207 */       this.field_70544_f *= f;
/*     */     } 
/*     */     
/* 210 */     if (this.field_70544_f > 0.001D) {
/* 211 */       this.vecs.add(new Quat(scale, this.field_187126_f - this.startX, this.field_187127_g - this.startY, this.field_187128_h - this.startZ));
/*     */     } else {
/* 213 */       if (this.growing < 0) this.growing = this.field_70546_d; 
/* 214 */       this.length--;
/*     */     } 
/*     */     
/* 217 */     if (this.vecs.size() > this.length) {
/* 218 */       this.vecs.remove(0);
/*     */     }
/*     */     
/* 221 */     this.points = new double[this.vecs.size()][3];
/* 222 */     this.colours = new float[this.vecs.size()][4];
/* 223 */     this.radii = new double[this.vecs.size()];
/* 224 */     int c = this.vecs.size();
/* 225 */     for (Quat v : this.vecs) {
/* 226 */       c--;
/* 227 */       float variance = 1.0F + MathHelper.func_76126_a((c + this.field_70546_d) / 3.0F) * 0.2F;
/*     */       
/* 229 */       float xx = MathHelper.func_76126_a((c + this.field_70546_d) / 6.0F) * 0.01F;
/* 230 */       float yy = MathHelper.func_76126_a((c + this.field_70546_d) / 7.0F) * 0.01F;
/* 231 */       float zz = MathHelper.func_76126_a((c + this.field_70546_d) / 8.0F) * 0.01F;
/*     */       
/* 233 */       this.points[c][0] = v.x + xx;
/* 234 */       this.points[c][1] = v.y + yy;
/* 235 */       this.points[c][2] = v.z + zz;
/*     */       
/* 237 */       this.radii[c] = v.s * variance;
/*     */       
/* 239 */       if (c > this.vecs.size() - 10) {
/* 240 */         this.radii[c] = this.radii[c] * MathHelper.func_76134_b((float)(((c - this.vecs.size() - 12) / 10.0F) * 1.5707963267948966D));
/*     */       }
/*     */       
/* 243 */       if (c == 0) { this.radii[c] = 0.0D; } else if (c == 1) { this.radii[c] = 0.0D; }
/* 244 */       else if (c == 2) { this.radii[c] = (this.field_70544_f * 0.5D + this.radii[c]) / 2.0D; }
/* 245 */       else if (c == 3) { this.radii[c] = (this.field_70544_f + this.radii[c]) / 2.0D; }
/* 246 */       else if (c == 4) { this.radii[c] = (this.field_70544_f + this.radii[c] * 2.0D) / 3.0D; }
/*     */       
/* 248 */       this.colours[c][0] = 1.0F;
/* 249 */       this.colours[c][1] = 1.0F;
/* 250 */       this.colours[c][2] = 1.0F;
/* 251 */       this.colours[c][3] = 1.0F;
/*     */     } 
/*     */ 
/*     */     
/* 255 */     if (this.vecs.size() > 2 && this.field_187136_p.nextBoolean()) {
/* 256 */       int q = this.field_187136_p.nextInt(3);
/* 257 */       if (this.field_187136_p.nextBoolean())
/* 258 */         q = this.vecs.size() - 2; 
/*     */     }  }
/*     */   public void func_180434_a(BufferBuilder wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { Tessellator.func_178181_a().func_78381_a(); GL11.glPushMatrix(); this; double ePX = this.startX - field_70556_an; this; double ePY = this.startY - field_70554_ao; this; double ePZ = this.startZ - field_70555_ap; GL11.glTranslated(ePX, ePY, ePZ); for (int q = 0; q <= 1; q++) { if (q < 1)
/*     */         GlStateManager.func_179132_a(false);  GL11.glBlendFunc(770, (q < 1) ? 1 : 771); if (this.points != null && this.points.length > 2) { (Minecraft.func_71410_x()).field_71446_o.func_110577_a(starsTexture); ShaderHelper.useShader(ShaderHelper.endShader, this.shaderCallback); double[] r2 = new double[this.radii.length]; int ri = 0; float m = (1.5F - q) / 1.0F; for (double d : this.radii) { r2[ri] = this.radii[ri] * m; ri++; }
/*     */          this.gle.set_POLYCYL_TESS(3); this.gle.set__ROUND_TESS_PIECES(1); this.gle.gleSetJoinStyle(1042); this.gle.glePolyCone(this.points.length, this.points, this.colours, r2, 0.075F, (this.growing < 0) ? 0.0F : (0.075F * ((this.field_70546_d - this.growing) + f))); ShaderHelper.releaseShader(); }
/*     */        if (q < 1)
/*     */         GlStateManager.func_179132_a(true);  }
/* 265 */      GlStateManager.func_179132_a(false); GL11.glBlendFunc(770, 771); GL11.glPopMatrix(); (Minecraft.func_71410_x()).field_71446_o.func_110577_a(ParticleEngine.particleTexture); wr.func_181668_a(7, DefaultVertexFormats.field_181704_d); } public void setFXLayer(int l) { this.layer = l; } public int func_70537_b() { return this.layer; } public void setGravity(float value) { this.field_70545_g = value; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\other\FXVoidStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */