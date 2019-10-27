/*     */ package thaumcraft.client.renderers.entity.construct;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.obj.AdvancedModelLoader;
/*     */ import thaumcraft.client.lib.obj.IModelCustom;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderTurretCrossbowAdvanced
/*     */   extends RenderLiving
/*     */ {
/*     */   private IModelCustom model;
/*  26 */   private static final ResourceLocation TURMODEL = new ResourceLocation("thaumcraft", "models/obj/crossbow_advanced.obj");
/*     */ 
/*     */   
/*     */   public RenderTurretCrossbowAdvanced(RenderManager rm) {
/*  30 */     super(rm, null, 0.5F);
/*  31 */     this.model = AdvancedModelLoader.loadModel(TURMODEL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTurret(EntityTurretCrossbow turret, double x, double y, double z, float par8, float pTicks) {
/*  37 */     func_180548_c(turret);
/*  38 */     GL11.glPushMatrix();
/*  39 */     GL11.glEnable(32826);
/*  40 */     GL11.glEnable(3042);
/*  41 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  43 */     GL11.glTranslated(x, y + 0.75D, z);
/*     */     
/*  45 */     GL11.glPushMatrix();
/*  46 */     if (turret.func_184218_aH() && turret.func_184187_bx() != null && turret.func_184187_bx() instanceof net.minecraft.entity.item.EntityMinecart) {
/*  47 */       GL11.glScaled(0.66D, 0.75D, 0.66D);
/*     */     }
/*  49 */     this.model.renderPart("legs");
/*  50 */     GL11.glPopMatrix();
/*     */     
/*  52 */     GL11.glPushMatrix();
/*  53 */     if (turret.field_70737_aN > 0) {
/*  54 */       GlStateManager.func_179131_c(1.0F, 0.5F, 0.5F, 1.0F);
/*  55 */       float jiggle = turret.field_70737_aN / 500.0F;
/*  56 */       GL11.glTranslated(turret.func_70681_au().nextGaussian() * jiggle, turret.func_70681_au().nextGaussian() * jiggle, turret.func_70681_au().nextGaussian() * jiggle);
/*     */     } 
/*     */     
/*  59 */     GL11.glRotatef(turret.field_70758_at + (turret.field_70759_as - turret.field_70758_at) * pTicks, 0.0F, -1.0F, 0.0F);
/*  60 */     GL11.glRotatef(turret.field_70127_C + (turret.field_70125_A - turret.field_70127_C) * pTicks, 1.0F, 0.0F, 0.0F);
/*     */     
/*  62 */     this.model.renderPart("mech");
/*  63 */     this.model.renderPart("box");
/*  64 */     this.model.renderPart("shield");
/*  65 */     this.model.renderPart("brain");
/*     */     
/*  67 */     GL11.glPushMatrix();
/*  68 */     GL11.glTranslated(0.0D, 0.0D, (MathHelper.func_76126_a(MathHelper.func_76129_c(turret.loadProgressForRender) * 3.1415927F * 2.0F) / 12.0F));
/*  69 */     this.model.renderPart("loader");
/*  70 */     GL11.glPopMatrix();
/*     */     
/*  72 */     float sp = 0.0F;
/*  73 */     if (func_77040_d(turret, pTicks) > -9990.0F) {
/*  74 */       sp = MathHelper.func_76126_a(MathHelper.func_76129_c(func_77040_d(turret, pTicks)) * 3.1415927F * 2.0F) * 20.0F;
/*     */     }
/*     */     
/*  77 */     GL11.glTranslated(0.0D, 0.0D, 0.375D);
/*     */     
/*  79 */     GL11.glPushMatrix();
/*  80 */     GL11.glRotatef(sp, 0.0F, 1.0F, 0.0F);
/*  81 */     this.model.renderPart("bow1");
/*  82 */     GL11.glPopMatrix();
/*     */     
/*  84 */     GL11.glPushMatrix();
/*  85 */     GL11.glRotatef(sp, 0.0F, -1.0F, 0.0F);
/*  86 */     this.model.renderPart("bow2");
/*  87 */     GL11.glPopMatrix();
/*     */     
/*  89 */     GL11.glPopMatrix();
/*  90 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  92 */     GL11.glDisable(3042);
/*  93 */     GL11.glDisable(32826);
/*  94 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_77040_d(EntityLivingBase e, float p_77040_2_) {
/*  99 */     ((EntityTurretCrossbow)e).loadProgressForRender = ((EntityTurretCrossbow)e).getLoadProgress(p_77040_2_);
/* 100 */     return super.func_77040_d(e, p_77040_2_);
/*     */   }
/*     */   
/*     */   private void translateFromOrientation(int orientation) {
/* 104 */     GL11.glTranslated(0.0D, 0.5D, 0.0D);
/* 105 */     if (orientation == 0) {
/* 106 */       GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 107 */     } else if (orientation != 1) {
/* 108 */       if (orientation == 2) {
/* 109 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 110 */       } else if (orientation == 3) {
/* 111 */         GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 112 */       } else if (orientation == 4) {
/* 113 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 114 */       } else if (orientation == 5) {
/* 115 */         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/*     */       } 
/* 117 */     }  GL11.glTranslated(0.0D, -0.5D, 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void func_76986_a(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9) { renderTurret((EntityTurretCrossbow)par1Entity, par2, par4, par6, par8, par9); }
/*     */ 
/*     */   
/* 127 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/entity/crossbow_advanced.png");
/*     */ 
/*     */ 
/*     */   
/* 131 */   protected ResourceLocation func_110775_a(Entity entity) { return rl; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\construct\RenderTurretCrossbowAdvanced.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */