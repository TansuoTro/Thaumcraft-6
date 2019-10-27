/*     */ package thaumcraft.client.renderers.entity.projectile;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderEldritchOrb
/*     */   extends Render
/*     */ {
/*     */   private Random random;
/*     */   
/*     */   public RenderEldritchOrb(RenderManager renderManager) {
/*  22 */     super(renderManager);
/*     */ 
/*     */ 
/*     */     
/*  26 */     this.random = new Random();
/*     */     this.field_76989_e = 0.0F;
/*     */   } public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks) {
/*  29 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/*  31 */     this.random.setSeed(187L);
/*     */     
/*  33 */     GL11.glPushMatrix();
/*     */     
/*  35 */     RenderHelper.func_74518_a();
/*  36 */     float f1 = entity.field_70173_aa / 80.0F;
/*  37 */     float f3 = 0.9F;
/*  38 */     float f2 = 0.0F;
/*     */     
/*  40 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  41 */     GL11.glDisable(3553);
/*  42 */     GL11.glShadeModel(7425);
/*  43 */     GL11.glEnable(3042);
/*  44 */     GL11.glBlendFunc(770, 1);
/*  45 */     GL11.glDisable(3008);
/*  46 */     GL11.glEnable(2884);
/*  47 */     GL11.glDepthMask(false);
/*  48 */     GL11.glPushMatrix();
/*  49 */     for (int i = 0; i < 12; i++) {
/*  50 */       GL11.glRotatef(this.random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  51 */       GL11.glRotatef(this.random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  52 */       GL11.glRotatef(this.random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
/*  53 */       GL11.glRotatef(this.random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  54 */       GL11.glRotatef(this.random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  55 */       GL11.glRotatef(this.random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
/*     */       
/*  57 */       tessellator.func_178180_c().func_181668_a(6, DefaultVertexFormats.field_181706_f);
/*  58 */       float fa = this.random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
/*  59 */       float f4 = this.random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
/*  60 */       fa /= 30.0F / Math.min(entity.field_70173_aa, 10) / 10.0F;
/*  61 */       f4 /= 30.0F / Math.min(entity.field_70173_aa, 10) / 10.0F;
/*  62 */       tessellator.func_178180_c().func_181662_b(0.0D, 0.0D, 0.0D).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F - f2).func_181675_d();
/*  63 */       tessellator.func_178180_c().func_181662_b(-0.866D * f4, fa, (-0.5F * f4)).func_181666_a(64.0F, 64.0F, 64.0F, 255.0F * (1.0F - f2)).func_181675_d();
/*  64 */       tessellator.func_178180_c().func_181662_b(0.866D * f4, fa, (-0.5F * f4)).func_181666_a(64.0F, 64.0F, 64.0F, 255.0F * (1.0F - f2)).func_181675_d();
/*  65 */       tessellator.func_178180_c().func_181662_b(0.0D, fa, (1.0F * f4)).func_181666_a(64.0F, 64.0F, 64.0F, 255.0F * (1.0F - f2)).func_181675_d();
/*  66 */       tessellator.func_178180_c().func_181662_b(-0.866D * f4, fa, (-0.5F * f4)).func_181666_a(64.0F, 64.0F, 64.0F, 255.0F * (1.0F - f2)).func_181675_d();
/*  67 */       tessellator.func_78381_a();
/*     */     } 
/*     */     
/*  70 */     GL11.glPopMatrix();
/*  71 */     GL11.glDepthMask(true);
/*  72 */     GL11.glDisable(2884);
/*  73 */     GL11.glDisable(3042);
/*  74 */     GL11.glShadeModel(7424);
/*  75 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  76 */     GL11.glEnable(3553);
/*  77 */     GL11.glEnable(3008);
/*  78 */     RenderHelper.func_74519_b();
/*     */     
/*  80 */     GL11.glPopMatrix();
/*     */     
/*  82 */     GL11.glPushMatrix();
/*  83 */     GL11.glTranslated(x, y, z);
/*  84 */     GL11.glEnable(3042);
/*  85 */     GL11.glBlendFunc(770, 771);
/*  86 */     GL11.glDepthMask(false);
/*  87 */     func_110776_a(ParticleEngine.particleTexture);
/*  88 */     f2 = (entity.field_70173_aa % 13) / 64.0F;
/*  89 */     f3 = f2 + 0.015625F;
/*  90 */     float f4 = 0.046875F;
/*  91 */     float f5 = f4 + 0.015625F;
/*  92 */     float f6 = 1.0F;
/*  93 */     float f7 = 0.5F;
/*  94 */     float f8 = 0.5F;
/*  95 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  96 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  97 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/*  98 */     GL11.glScaled(0.75D, 0.75D, 0.75D);
/*  99 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181710_j);
/* 100 */     tessellator.func_178180_c();
/* 101 */     tessellator.func_178180_c().func_181662_b((0.0F - f7), (0.0F - f8), 0.0D).func_187315_a(f2, f5).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 102 */     tessellator.func_178180_c().func_181662_b((f6 - f7), (0.0F - f8), 0.0D).func_187315_a(f3, f5).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 103 */     tessellator.func_178180_c().func_181662_b((f6 - f7), (1.0F - f8), 0.0D).func_187315_a(f3, f4).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 104 */     tessellator.func_178180_c().func_181662_b((0.0F - f7), (1.0F - f8), 0.0D).func_187315_a(f2, f4).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 105 */     tessellator.func_78381_a();
/* 106 */     GL11.glDepthMask(true);
/* 107 */     GL11.glDisable(3042);
/* 108 */     GL11.glDisable(32826);
/* 109 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 110 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) { renderEntityAt(entity, d, d1, d2, f, f1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   protected ResourceLocation func_110775_a(Entity entity) { return ParticleEngine.particleTexture; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\projectile\RenderEldritchOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */