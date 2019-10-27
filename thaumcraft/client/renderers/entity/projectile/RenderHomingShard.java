/*     */ package thaumcraft.client.renderers.entity.projectile;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.projectile.EntityHomingShard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderHomingShard
/*     */   extends Render
/*     */ {
/*     */   private Random random;
/*     */   
/*     */   public RenderHomingShard(RenderManager rm) {
/*  27 */     super(rm);
/*     */ 
/*     */ 
/*     */     
/*  31 */     this.random = new Random();
/*     */     this.field_76989_e = 0.0F;
/*     */   } public void renderEntityAt(EntityHomingShard entity, double x, double y, double z, float fq, float pticks) {
/*  34 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/*  36 */     GL11.glPushMatrix();
/*  37 */     GL11.glDepthMask(false);
/*  38 */     GL11.glEnable(3042);
/*  39 */     GL11.glBlendFunc(770, 1);
/*     */     
/*  41 */     func_110776_a(ParticleEngine.particleTexture);
/*     */     
/*  43 */     float f2 = (8 + entity.field_70173_aa % 8) / 64.0F;
/*  44 */     float f3 = f2 + 0.015625F;
/*  45 */     float f4 = 0.0625F;
/*  46 */     float f5 = f4 + 0.015625F;
/*     */     
/*  48 */     float f6 = 1.0F;
/*  49 */     float f7 = 0.5F;
/*  50 */     float f8 = 0.5F;
/*     */     
/*  52 */     GL11.glColor4f(0.9F, 0.075F, 0.9525F, 1.0F);
/*     */     
/*  54 */     GL11.glPushMatrix();
/*  55 */     GL11.glTranslated(x, y, z);
/*  56 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  57 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/*  58 */     GL11.glScalef(0.4F + 0.1F * entity.getStrength(), 0.4F + 0.1F * entity.getStrength(), 0.4F + 0.1F * entity.getStrength());
/*  59 */     tessellator.func_178180_c().func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/*  60 */     int i = 240;
/*  61 */     int j = i >> 16 & 0xFFFF;
/*  62 */     int k = i & 0xFFFF;
/*  63 */     tessellator.func_178180_c().func_181662_b(-f7, -f8, 0.0D).func_187315_a(f2, f5).func_181666_a(0.9F, 0.075F, 0.9525F, 1.0F).func_187314_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  64 */     tessellator.func_178180_c().func_181662_b((f6 - f7), -f8, 0.0D).func_187315_a(f3, f5).func_181666_a(0.9F, 0.075F, 0.9525F, 1.0F).func_187314_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  65 */     tessellator.func_178180_c().func_181662_b((f6 - f7), (1.0F - f8), 0.0D).func_187315_a(f3, f4).func_181666_a(0.9F, 0.075F, 0.9525F, 1.0F).func_187314_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  66 */     tessellator.func_178180_c().func_181662_b(-f7, (1.0F - f8), 0.0D).func_187315_a(f2, f4).func_181666_a(0.9F, 0.075F, 0.9525F, 1.0F).func_187314_a(j, k).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*  67 */     tessellator.func_78381_a();
/*  68 */     GL11.glPopMatrix();
/*     */     
/*  70 */     GL11.glPushMatrix();
/*  71 */     GL11.glBlendFunc(770, 1);
/*  72 */     func_110776_a(beamTexture);
/*  73 */     Minecraft mc = Minecraft.func_71410_x();
/*  74 */     EntityPlayerSP p = mc.field_71439_g;
/*  75 */     double doubleX = p.field_70142_S + (p.field_70165_t - p.field_70142_S) * pticks;
/*  76 */     double doubleY = p.field_70137_T + (p.field_70163_u - p.field_70137_T) * pticks;
/*  77 */     double doubleZ = p.field_70136_U + (p.field_70161_v - p.field_70136_U) * pticks;
/*  78 */     UtilsFX.Vector player = new UtilsFX.Vector((float)doubleX, (float)doubleY, (float)doubleZ);
/*     */     
/*  80 */     double dX = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * pticks;
/*  81 */     double dY = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * pticks;
/*  82 */     double dZ = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * pticks;
/*  83 */     UtilsFX.Vector start = new UtilsFX.Vector((float)dX, (float)dY, (float)dZ);
/*     */     
/*  85 */     if (entity.vl.size() == 0) {
/*  86 */       entity.vl.add(start);
/*     */     }
/*     */     
/*  89 */     GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
/*  90 */     UtilsFX.Vector vs = new UtilsFX.Vector((float)dX, (float)dY, (float)dZ);
/*     */     
/*  92 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181711_k);
/*  93 */     int c = entity.vl.size();
/*  94 */     for (UtilsFX.Vector nv : entity.vl) {
/*  95 */       UtilsFX.drawBeam(vs, nv, player, 0.25F * c / entity.vl.size(), 240, 0.405F, 0.075F, 0.525F, 0.5F);
/*  96 */       vs = nv;
/*  97 */       c--;
/*     */     } 
/*     */     
/* 100 */     tessellator.func_78381_a();
/*     */     
/* 102 */     GL11.glPopMatrix();
/*     */     
/* 104 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 105 */     GL11.glBlendFunc(770, 771);
/* 106 */     GL11.glDisable(3042);
/* 107 */     GL11.glDisable(32826);
/*     */     
/* 109 */     GL11.glDepthMask(true);
/* 110 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 115 */   private static final ResourceLocation beamTexture = new ResourceLocation("thaumcraft", "textures/misc/beaml.png");
/*     */ 
/*     */ 
/*     */   
/* 119 */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) { renderEntityAt((EntityHomingShard)entity, d, d1, d2, f, f1); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   protected ResourceLocation func_110775_a(Entity entity) { return TextureMap.field_110575_b; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\projectile\RenderHomingShard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */