/*     */ package thaumcraft.client.fx.other;
/*     */ 
/*     */ import com.sasmaster.glelwjgl.java.CoreGLE;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.codechicken.lib.vec.Quat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FXBoreStream
/*     */   extends Particle
/*     */ {
/*     */   private Entity target;
/*     */   private double startX;
/*     */   private double startY;
/*     */   private double startZ;
/*  30 */   private int count = 0;
/*  31 */   public int length = 5;
/*  32 */   private String key = "";
/*     */   
/*  34 */   private BlockPos startPos = null;
/*     */   
/*     */   CoreGLE gle;
/*     */ 
/*     */   
/*     */   public FXBoreStream(World w, double par2, double par4, double par6, Entity target, int count, int color, float scale, int extend, double my) {
/*  40 */     super(w, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     this.gle = new CoreGLE();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     this.layer = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     this.growing = -1;
/* 126 */     this.vecs = new ArrayList(); this.field_70544_f = (float)(scale * (1.0D + this.field_187136_p.nextGaussian() * 0.15000000596046448D)); this.length = Math.max(5, extend); this.count = count; this.target = target; this.field_70547_e = this.length * 10; this.field_187129_i = (MathHelper.func_76126_a(count / 4.0F) * 0.15F); this.field_187130_j = my + (MathHelper.func_76126_a(count / 3.0F) * 0.15F); this.field_187131_k = (MathHelper.func_76126_a(count / 2.0F) * 0.15F); Color c = new Color(color); this.field_70552_h = c.getRed() / 255.0F;
/*     */     this.field_70553_i = c.getGreen() / 255.0F;
/*     */     this.field_70551_j = c.getBlue() / 255.0F;
/*     */     this.field_70545_g = 0.2F;
/*     */     this.vecs.add(new Quat(0.0D, 0.0D, 0.0D, 0.001D));
/*     */     this.vecs.add(new Quat(0.0D, 0.0D, 0.0D, 0.001D));
/*     */     this.startX = this.field_187126_f;
/*     */     this.startY = this.field_187127_g;
/*     */     this.startZ = this.field_187128_h;
/* 135 */     this.startPos = new BlockPos(this.startX, this.startY, this.startZ); } private static final ResourceLocation TEX0 = new ResourceLocation("thaumcraft", "textures/misc/essentia.png"); int layer; double[][] points; float[][] colours; double[] radii; public void func_189213_a() { this.field_187123_c = this.field_187126_f;
/* 136 */     this.field_187124_d = this.field_187127_g;
/* 137 */     this.field_187125_e = this.field_187128_h;
/*     */     
/* 139 */     if (this.field_70546_d++ >= this.field_70547_e || this.length < 1) {
/* 140 */       func_187112_i();
/*     */       
/*     */       return;
/*     */     } 
/* 144 */     this.field_187130_j += 0.01D * this.field_70545_g;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
/*     */     
/* 151 */     this.field_187129_i *= 0.985D;
/* 152 */     this.field_187130_j *= 0.985D;
/* 153 */     this.field_187131_k *= 0.985D;
/*     */     
/* 155 */     double dx = this.target.field_70165_t - this.field_187126_f;
/* 156 */     double dy = this.target.field_70163_u + this.target.func_70047_e() - this.field_187127_g;
/* 157 */     double dz = this.target.field_70161_v - this.field_187128_h;
/*     */     
/* 159 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 161 */     double clamp = d11 / 10.0D;
/*     */ 
/*     */ 
/*     */     
/* 165 */     this.field_187129_i = MathHelper.func_151237_a((float)this.field_187129_i, -clamp, clamp);
/* 166 */     this.field_187130_j = MathHelper.func_151237_a((float)this.field_187130_j, -clamp, clamp);
/* 167 */     this.field_187131_k = MathHelper.func_151237_a((float)this.field_187131_k, -clamp, clamp);
/*     */     
/* 169 */     dx /= d11;
/* 170 */     dy /= d11;
/* 171 */     dz /= d11;
/*     */     
/* 173 */     this.field_187129_i += dx * clamp / Math.min(1.0D, d11);
/* 174 */     this.field_187130_j += dy * clamp / Math.min(1.0D, d11);
/* 175 */     this.field_187131_k += dz * clamp / Math.min(1.0D, d11);
/*     */     
/* 177 */     float scale = this.field_70544_f * (0.75F + MathHelper.func_76126_a((this.count + this.field_70546_d) / 2.0F) * 0.25F);
/*     */     
/* 179 */     if (d11 < 1.0D) {
/* 180 */       float f = MathHelper.func_76126_a((float)(d11 * 1.5707963267948966D));
/* 181 */       scale *= f;
/* 182 */       this.field_70544_f *= f;
/*     */     } 
/*     */     
/* 185 */     if (this.field_70544_f > 0.001D) {
/* 186 */       this.vecs.add(new Quat(scale, this.field_187126_f - this.startX, this.field_187127_g - this.startY, this.field_187128_h - this.startZ));
/*     */     } else {
/* 188 */       if (this.growing < 0) this.growing = this.field_70546_d; 
/* 189 */       this.length--;
/*     */     } 
/*     */     
/* 192 */     if (this.vecs.size() > this.length) {
/* 193 */       this.vecs.remove(0);
/*     */     }
/*     */     
/* 196 */     this.points = new double[this.vecs.size()][3];
/* 197 */     this.colours = new float[this.vecs.size()][4];
/* 198 */     this.radii = new double[this.vecs.size()];
/* 199 */     int c = this.vecs.size();
/* 200 */     for (Quat v : this.vecs) {
/* 201 */       c--;
/* 202 */       float variance = 1.0F + MathHelper.func_76126_a((c + this.field_70546_d) / 3.0F) * 0.2F;
/*     */       
/* 204 */       float xx = MathHelper.func_76126_a((c + this.field_70546_d) / 6.0F) * 0.03F;
/* 205 */       float yy = MathHelper.func_76126_a((c + this.field_70546_d) / 7.0F) * 0.03F;
/* 206 */       float zz = MathHelper.func_76126_a((c + this.field_70546_d) / 8.0F) * 0.03F;
/*     */       
/* 208 */       this.points[c][0] = v.x + xx;
/* 209 */       this.points[c][1] = v.y + yy;
/* 210 */       this.points[c][2] = v.z + zz;
/*     */       
/* 212 */       this.radii[c] = v.s * variance;
/*     */       
/* 214 */       if (c > this.vecs.size() - 10) {
/* 215 */         this.radii[c] = this.radii[c] * MathHelper.func_76134_b((float)(((c - this.vecs.size() - 12) / 10.0F) * 1.5707963267948966D));
/*     */       }
/*     */       
/* 218 */       if (c == 0) { this.radii[c] = 0.0D; } else if (c == 1) { this.radii[c] = 0.0D; }
/* 219 */       else if (c == 2) { this.radii[c] = (this.field_70544_f * 0.5D + this.radii[c]) / 2.0D; }
/* 220 */       else if (c == 3) { this.radii[c] = (this.field_70544_f + this.radii[c]) / 2.0D; }
/* 221 */       else if (c == 4) { this.radii[c] = (this.field_70544_f + this.radii[c] * 2.0D) / 3.0D; }
/*     */       
/* 223 */       float v2 = 1.0F - MathHelper.func_76126_a((c + this.field_70546_d) / 2.0F) * 0.1F;
/*     */       
/* 225 */       this.colours[c][0] = this.field_70552_h * v2;
/* 226 */       this.colours[c][1] = this.field_70553_i * v2;
/* 227 */       this.colours[c][2] = this.field_70551_j * v2;
/* 228 */       this.colours[c][3] = 1.0F;
/*     */     }  }
/*     */   int growing; ArrayList<Quat> vecs; public void func_180434_a(BufferBuilder wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { Tessellator.func_178181_a().func_78381_a(); GL11.glPushMatrix(); this; double ePX = this.startX - field_70556_an; this; double ePY = this.startY - field_70554_ao; this; double ePZ = this.startZ - field_70555_ap; GL11.glTranslated(ePX, ePY, ePZ); if (this.points != null && this.points.length > 2) {
/*     */       (Minecraft.func_71410_x()).field_71446_o.func_110577_a(TEX0); this.gle.set_POLYCYL_TESS(8); this.gle.set__ROUND_TESS_PIECES(1); this.gle.gleSetJoinStyle(1042); this.gle.glePolyCone(this.points.length, this.points, this.colours, this.radii, 0.075F, (this.growing < 0) ? 0.0F : (0.075F * ((this.field_70546_d - this.growing) + f)));
/*     */     }  GL11.glPopMatrix();
/*     */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/* 234 */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d); } public void setFXLayer(int l) { this.layer = l; } public int func_70537_b() { return this.layer; } public void setGravity(float value) { this.field_70545_g = value; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\other\FXBoreStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */