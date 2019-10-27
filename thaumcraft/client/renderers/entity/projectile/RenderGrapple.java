/*     */ package thaumcraft.client.renderers.entity.projectile;
/*     */ 
/*     */ import com.sasmaster.glelwjgl.java.CoreGLE;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.entity.ModelGrappler;
/*     */ import thaumcraft.common.entities.projectile.EntityGrapple;
/*     */ 
/*     */ public class RenderGrapple
/*     */   extends Render {
/*     */   ResourceLocation beam;
/*     */   ResourceLocation rope;
/*     */   private ModelGrappler model;
/*     */   CoreGLE gle;
/*     */   public ArrayList<Vec3d> points;
/*     */   public float length;
/*     */   
/*     */   public RenderGrapple(RenderManager rm) {
/*  33 */     super(rm);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  38 */     this.beam = new ResourceLocation("thaumcraft", "textures/entity/grappler.png");
/*  39 */     this.rope = new ResourceLocation("thaumcraft", "textures/misc/rope.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     this.gle = new CoreGLE();
/*     */     
/* 127 */     this.points = new ArrayList();
/* 128 */     this.length = 1.0F;
/*     */     this.field_76989_e = 0.0F;
/*     */     this.model = new ModelGrappler(); }
/* 131 */   private void calcPoints(EntityLivingBase thrower, EntityGrapple grapple, float pt) { if (thrower == null || grapple == null)
/* 132 */       return;  double tx = thrower.field_70142_S + (thrower.field_70165_t - thrower.field_70142_S) * pt;
/* 133 */     double ty = thrower.field_70137_T + (thrower.field_70163_u - thrower.field_70137_T) * pt;
/* 134 */     double tz = thrower.field_70136_U + (thrower.field_70161_v - thrower.field_70136_U) * pt;
/*     */ 
/*     */     
/* 137 */     if ((Minecraft.func_71410_x()).field_71474_y.field_74320_O == 0) {
/* 138 */       double yy = thrower.field_70126_B + (thrower.field_70177_z - thrower.field_70126_B) * pt;
/* 139 */       double px = (-MathHelper.func_76134_b((float)((yy - 0.5D) / 180.0D * 3.1415929794311523D)) * 0.1F * ((grapple.hand == EnumHand.MAIN_HAND) ? true : -1));
/* 140 */       double pz = (-MathHelper.func_76126_a((float)((yy - 0.5D) / 180.0D * 3.1415929794311523D)) * 0.1F * ((grapple.hand == EnumHand.MAIN_HAND) ? true : -1));
/* 141 */       Vec3d vl = thrower.func_70040_Z();
/* 142 */       tx += px + vl.field_72450_a / 5.0D;
/* 143 */       ty += thrower.func_70047_e() / 2.6D + vl.field_72448_b / 5.0D;
/* 144 */       tz += pz + vl.field_72449_c / 5.0D;
/*     */     } 
/*     */     
/* 147 */     double gx = grapple.field_70142_S + (grapple.field_70165_t - grapple.field_70142_S) * pt;
/* 148 */     double gy = grapple.field_70137_T + (grapple.field_70163_u - grapple.field_70137_T) * pt;
/* 149 */     double gz = grapple.field_70136_U + (grapple.field_70161_v - grapple.field_70136_U) * pt;
/* 150 */     this.points.clear();
/* 151 */     Vec3d vs = new Vec3d(0.0D, 0.0D, 0.0D);
/* 152 */     Vec3d ve = new Vec3d(tx - gx, ty - gy + (thrower.field_70131_O / 2.0F), tz - gz);
/* 153 */     this.length = (float)(ve.func_72433_c() * 5.0D);
/* 154 */     int steps = (int)this.length;
/* 155 */     this.points.add(vs);
/* 156 */     for (int a = 1; a < steps - 1; a++) {
/* 157 */       float dist = a * this.length / steps;
/* 158 */       float ss = 1.0F - a / steps * 1.25F;
/* 159 */       double dx = (tx - gx) / steps * a + (MathHelper.func_76126_a(dist / 10.0F) * grapple.ampl * ss);
/* 160 */       double dy = (ty - gy + (thrower.field_70131_O / 2.0F)) / steps * a + (MathHelper.func_76126_a(dist / 8.0F) * grapple.ampl * ss);
/* 161 */       double dz = (tz - gz) / steps * a + (MathHelper.func_76126_a(dist / 6.0F) * grapple.ampl * ss);
/* 162 */       Vec3d vp = new Vec3d(dx, dy, dz);
/*     */       
/* 164 */       this.points.add(vp);
/*     */     } 
/* 166 */     this.points.add(ve);
/* 167 */     this.points.add(ve); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) { renderEntityAt(entity, d, d1, d2, f, f1); }
/*     */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks) { Tessellator tessellator = Tessellator.func_178181_a(); GL11.glPushMatrix(); GL11.glTranslated(x, y, z); GL11.glPushMatrix(); func_110776_a(this.beam); GlStateManager.func_179114_b(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * pticks - 90.0F, 0.0F, 1.0F, 0.0F); GlStateManager.func_179114_b(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * pticks, 0.0F, 0.0F, 1.0F); this.model.render(); GL11.glPopMatrix(); GL11.glEnable(3042); GL11.glBlendFunc(770, 1); GL11.glDisable(2884); func_110776_a(ParticleEngine.particleTexture); float f2 = (1 + entity.field_70173_aa % 6) / 32.0F; float f3 = f2 + 0.03125F; float f4 = 0.21875F; float f5 = f4 + 0.03125F; float f7 = 0.5F; GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F); GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F); GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F); float bob = MathHelper.func_76126_a(entity.field_70173_aa / 5.0F) * 0.2F + 0.2F; GL11.glScalef(1.0F + bob, 1.0F + bob, 1.0F + bob); tessellator.func_178180_c().func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO); int i = 220; int j = i >> 16 & 0xFFFF; int k = i & 0xFFFF; tessellator.func_178180_c().func_181662_b(-f7, -f7, 0.0D).func_187315_a(f2, f5).func_181666_a(1.0F, 1.0F, 1.0F, 0.21F).func_187314_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d(); tessellator.func_178180_c().func_181662_b(f7, -f7, 0.0D).func_187315_a(f3, f5).func_181666_a(1.0F, 1.0F, 1.0F, 0.21F).func_187314_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d(); tessellator.func_178180_c().func_181662_b(f7, f7, 0.0D).func_187315_a(f3, f4).func_181666_a(1.0F, 1.0F, 1.0F, 0.21F).func_187314_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d(); tessellator.func_178180_c().func_181662_b(-f7, f7, 0.0D).func_187315_a(f2, f4).func_181666_a(1.0F, 1.0F, 1.0F, 0.21F).func_187314_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d(); tessellator.func_78381_a(); GL11.glEnable(2884); GL11.glBlendFunc(770, 771); GL11.glDisable(3042); GL11.glDisable(32826); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); GL11.glPopMatrix(); calcPoints(((EntityGrapple)entity).func_85052_h(), (EntityGrapple)entity, pticks); GL11.glPushMatrix(); (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam); GL11.glEnable(3042); GL11.glBlendFunc(770, 771); if (this.points != null && this.points.size() > 2) {
/*     */       double[][] pp = new double[this.points.size()][3]; float[][] colours = new float[this.points.size()][4]; double[] radii = new double[this.points.size()]; for (int a = 0; a < this.points.size(); a++) {
/*     */         pp[a][0] = ((Vec3d)this.points.get(a)).field_72450_a + x; pp[a][1] = ((Vec3d)this.points.get(a)).field_72448_b + y; pp[a][2] = ((Vec3d)this.points.get(a)).field_72449_c + z; colours[a][0] = 1.0F; colours[a][1] = 1.0F; colours[a][2] = 1.0F; colours[a][3] = 1.0F; radii[a] = 0.025D;
/*     */       }  (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.rope); this.gle.set_POLYCYL_TESS(4); this.gle.gleSetJoinStyle(1042); this.gle.glePolyCone(pp.length, pp, colours, radii, 2.0F, this.length - this.points.size());
/*     */     }  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); GL11.glBlendFunc(770, 771); GL11.glDisable(3042);
/* 180 */     GL11.glPopMatrix(); } protected ResourceLocation func_110775_a(Entity entity) { return TextureMap.field_110575_b; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\projectile\RenderGrapple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */