/*     */ package thaumcraft.client.renderers.entity.mob;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.client.renderers.models.entity.ModelTaintSeed;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSeed;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderTaintSeed extends RenderLiving<EntityTaintSeed> {
/*  19 */   public RenderTaintSeed(RenderManager rm) { super(rm, new ModelTaintSeed(), 0.4F); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  24 */   public RenderTaintSeed(RenderManager rm, ModelBase modelbase, float sz) { super(rm, modelbase, sz); }
/*     */ 
/*     */   
/*  27 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/entity/taintseed.png");
/*     */ 
/*     */ 
/*     */   
/*  31 */   protected ResourceLocation getEntityTexture(EntityTaintSeed entity) { return rl; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityTaintSeed entity, double x, double y, double z, float entityYaw, float partialTicks) {
/*  38 */     if (MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Pre(entity, this, x, y, z)))
/*  39 */       return;  GlStateManager.func_179094_E();
/*  40 */     GlStateManager.func_179129_p();
/*  41 */     this.field_77045_g.field_78095_p = func_77040_d(entity, partialTicks);
/*  42 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/*  43 */     this.field_77045_g.field_78093_q = shouldSit;
/*  44 */     this.field_77045_g.field_78091_s = entity.func_70631_g_();
/*     */ 
/*     */     
/*     */     try {
/*  48 */       GlStateManager.func_179094_E();
/*  49 */       float f = 0.0F;
/*  50 */       float f7 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks;
/*     */       
/*  52 */       func_77039_a(entity, x, y, z);
/*     */       
/*  54 */       float f8 = func_77044_a(entity, partialTicks);
/*  55 */       func_77043_a(entity, f8, f, partialTicks);
/*  56 */       float f4 = func_188322_c(entity, partialTicks);
/*  57 */       float f5 = 0.0F;
/*  58 */       float f6 = 0.0F;
/*     */       
/*  60 */       f5 = entity.field_184618_aE + (entity.field_70721_aZ - entity.field_184618_aE) * partialTicks;
/*  61 */       f6 = entity.field_184619_aG - entity.field_70721_aZ * (1.0F - partialTicks);
/*     */       
/*  63 */       if (f5 > 1.0F)
/*     */       {
/*  65 */         f5 = 1.0F;
/*     */       }
/*     */       
/*  68 */       GlStateManager.func_179141_d();
/*     */       
/*  70 */       this.field_77045_g.func_78086_a(entity, f6, f5, partialTicks);
/*  71 */       this.field_77045_g.func_78087_a(f6, f5, f8, f, f7, f4, entity);
/*     */       
/*  73 */       if (this.field_188301_f) {
/*     */         
/*  75 */         boolean flag1 = func_177088_c(entity);
/*  76 */         GlStateManager.func_179142_g();
/*  77 */         GlStateManager.func_187431_e(func_188298_c(entity));
/*     */         
/*  79 */         if (!this.field_188323_j)
/*     */         {
/*  81 */           func_77036_a(entity, f6, f5, f8, f, f7, f4);
/*     */         }
/*     */         
/*  84 */         func_177093_a(entity, f6, f5, partialTicks, f8, f, f7, f4);
/*     */         
/*  86 */         GlStateManager.func_187417_n();
/*  87 */         GlStateManager.func_179119_h();
/*     */         
/*  89 */         if (flag1)
/*     */         {
/*  91 */           func_180565_e();
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/*  96 */         boolean flag = func_177090_c(entity, partialTicks);
/*  97 */         func_77036_a(entity, f6, f5, f8, f, f7, f4);
/*     */         
/*  99 */         if (flag)
/*     */         {
/* 101 */           func_177091_f();
/*     */         }
/*     */         
/* 104 */         GlStateManager.func_179132_a(true);
/*     */         
/* 106 */         func_177093_a(entity, f6, f5, partialTicks, f8, f, f7, f4);
/*     */       } 
/* 108 */       GlStateManager.func_179121_F();
/*     */       
/* 110 */       GlStateManager.func_179101_C();
/*     */     }
/* 112 */     catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
/* 118 */     GlStateManager.func_179098_w();
/* 119 */     GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
/* 120 */     GlStateManager.func_179089_o();
/* 121 */     GlStateManager.func_179121_F();
/*     */     
/* 123 */     if (!this.field_188301_f)
/*     */     {
/* 125 */       func_177067_a(entity, x, y, z);
/*     */     }
/*     */     
/* 128 */     MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post(entity, this, x, y, z));
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderTaintSeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */