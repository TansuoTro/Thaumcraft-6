/*     */ package thaumcraft.client.renderers.entity.projectile;
/*     */ 
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderDart
/*     */   extends Render
/*     */ {
/*     */   public RenderDart(RenderManager renderManager) {
/*  23 */     super(renderManager);
/*     */ 
/*     */ 
/*     */     
/*  27 */     this.size1 = 0;
/*  28 */     this.size2 = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderArrow(EntityArrow arrow, double x, double y, double z, float ns, float prt) {
/*  33 */     func_180548_c(arrow);
/*  34 */     GL11.glPushMatrix();
/*     */     
/*  36 */     GL11.glEnable(3042);
/*  37 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  39 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  40 */     GL11.glRotatef(arrow.field_70126_B + (arrow.field_70177_z - arrow.field_70126_B) * prt - 90.0F, 0.0F, 1.0F, 0.0F);
/*  41 */     GL11.glRotatef(arrow.field_70127_C + (arrow.field_70125_A - arrow.field_70127_C) * prt, 0.0F, 0.0F, 1.0F);
/*  42 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  43 */     byte b0 = 0;
/*  44 */     float f2 = 0.0F;
/*  45 */     float f3 = 0.5F;
/*  46 */     float f4 = (0 + b0 * 10) / 32.0F;
/*  47 */     float f5 = (5 + b0 * 10) / 32.0F;
/*  48 */     float f6 = 0.0F;
/*  49 */     float f7 = 0.15625F;
/*  50 */     float f8 = (5 + b0 * 10) / 32.0F;
/*  51 */     float f9 = (10 + b0 * 10) / 32.0F;
/*  52 */     float f10 = 0.033F;
/*  53 */     GL11.glEnable(32826);
/*  54 */     float f11 = arrow.field_70249_b - prt;
/*     */     
/*  56 */     if (f11 > 0.0F) {
/*     */       
/*  58 */       float f12 = -MathHelper.func_76126_a(f11 * 3.0F) * f11;
/*  59 */       GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
/*     */     } 
/*     */     
/*  62 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  64 */     GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
/*  65 */     GL11.glScalef(f10, f10, f10);
/*  66 */     GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
/*  67 */     GL11.glNormal3f(f10, 0.0F, 0.0F);
/*  68 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
/*  69 */     tessellator.func_178180_c().func_181662_b(-7.0D, -2.0D, -2.0D).func_187315_a(f6, f8).func_181675_d();
/*  70 */     tessellator.func_178180_c().func_181662_b(-7.0D, -2.0D, 2.0D).func_187315_a(f7, f8).func_181675_d();
/*  71 */     tessellator.func_178180_c().func_181662_b(-7.0D, 2.0D, 2.0D).func_187315_a(f7, f9).func_181675_d();
/*  72 */     tessellator.func_178180_c().func_181662_b(-7.0D, 2.0D, -2.0D).func_187315_a(f6, f9).func_181675_d();
/*  73 */     tessellator.func_78381_a();
/*  74 */     GL11.glNormal3f(-f10, 0.0F, 0.0F);
/*  75 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
/*  76 */     tessellator.func_178180_c().func_181662_b(-7.0D, 2.0D, -2.0D).func_187315_a(f6, f8).func_181675_d();
/*  77 */     tessellator.func_178180_c().func_181662_b(-7.0D, 2.0D, 2.0D).func_187315_a(f7, f8).func_181675_d();
/*  78 */     tessellator.func_178180_c().func_181662_b(-7.0D, -2.0D, 2.0D).func_187315_a(f7, f9).func_181675_d();
/*  79 */     tessellator.func_178180_c().func_181662_b(-7.0D, -2.0D, -2.0D).func_187315_a(f6, f9).func_181675_d();
/*  80 */     tessellator.func_78381_a();
/*     */     
/*  82 */     for (int i = 0; i < 4; i++) {
/*     */       
/*  84 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  85 */       GL11.glNormal3f(0.0F, 0.0F, f10);
/*  86 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
/*  87 */       tessellator.func_178180_c().func_181662_b(-8.0D, -2.0D, 0.0D).func_187315_a(f2, f4).func_181675_d();
/*  88 */       tessellator.func_178180_c().func_181662_b(8.0D, -2.0D, 0.0D).func_187315_a(f3, f4).func_181675_d();
/*  89 */       tessellator.func_178180_c().func_181662_b(8.0D, 2.0D, 0.0D).func_187315_a(f3, f5).func_181675_d();
/*  90 */       tessellator.func_178180_c().func_181662_b(-8.0D, 2.0D, 0.0D).func_187315_a(f2, f5).func_181675_d();
/*  91 */       tessellator.func_78381_a();
/*     */     } 
/*     */     
/*  94 */     GL11.glDisable(32826);
/*     */     
/*  96 */     GL11.glDisable(3042);
/*  97 */     GL11.glPopMatrix();
/*     */     
/*  99 */     GL11.glPushMatrix();
/*     */     
/* 101 */     func_110776_a(ParticleEngine.particleTexture);
/*     */     
/* 103 */     GL11.glPopMatrix();
/*     */   }
/*     */   private static final ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");
/*     */   int size1;
/*     */   int size2;
/*     */   
/* 109 */   protected ResourceLocation getArrowTextures(EntityArrow par1EntityArrow) { return arrowTextures; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   protected ResourceLocation func_110775_a(Entity par1Entity) { return getArrowTextures((EntityArrow)par1Entity); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) { renderArrow((EntityArrow)par1Entity, par2, par4, par6, par8, par9); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\projectile\RenderDart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */