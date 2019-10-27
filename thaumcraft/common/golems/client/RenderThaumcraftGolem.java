/*     */ package thaumcraft.common.golems.client;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.entity.RenderBiped;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.IGolemProperties;
/*     */ import thaumcraft.api.golems.parts.PartModel;
/*     */ import thaumcraft.client.lib.obj.AdvancedModelLoader;
/*     */ import thaumcraft.client.lib.obj.IModelCustom;
/*     */ import thaumcraft.common.golems.EntityThaumcraftGolem;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderThaumcraftGolem
/*     */   extends RenderBiped
/*     */ {
/*  44 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*  46 */   private HashMap<String, IModelCustom> models = new HashMap();
/*     */   
/*  48 */   private HashMap<Integer, HashMap<PartModel.EnumAttachPoint, ArrayList<PartModel>>> partsCache = new HashMap();
/*     */   private IModelCustom baseModel;
/*     */   float swingProgress;
/*     */   
/*     */   public RenderThaumcraftGolem(RenderManager p_i46127_1_)
/*     */   {
/*  54 */     super(p_i46127_1_, new ModelBiped(), 0.3F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 338 */     this.swingProgress = 0.0F; this.field_177097_h.clear(); this.baseModel = AdvancedModelLoader.loadModel(new ResourceLocation("thaumcraft", "models/obj/golem_base.obj")); }
/*     */   private void renderModel(EntityThaumcraftGolem entity, float p1, float p2, float p3, float p4, float p5, float p6, float partialTicks) { boolean flag = !entity.func_82150_aj(); boolean flag1 = (!flag && !entity.func_98034_c((Minecraft.func_71410_x()).field_71439_g)); if (flag || flag1) { if (flag1) { GlStateManager.func_179094_E(); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.15F); GlStateManager.func_179132_a(false); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GlStateManager.func_179092_a(516, 0.003921569F); }  renderParts(entity, p1, p2, p3, p4, p5, p6, partialTicks); if (flag1) { GlStateManager.func_179084_k(); GlStateManager.func_179092_a(516, 0.1F); GlStateManager.func_179121_F(); GlStateManager.func_179132_a(true); }  }  EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g; if (entityPlayerSP.func_70093_af() && ((entityPlayerSP.func_184614_ca() != null && entityPlayerSP.func_184614_ca().func_77973_b() instanceof thaumcraft.api.golems.ISealDisplayer) || (entityPlayerSP.func_184592_cb() != null && entityPlayerSP.func_184592_cb().func_77973_b() instanceof thaumcraft.api.golems.ISealDisplayer)) && !EntityUtils.canEntityBeSeen(entityPlayerSP, entity)) { GlStateManager.func_179094_E(); GlStateManager.func_179131_c(0.25F, 0.25F, 0.25F, 0.25F); GlStateManager.func_179132_a(false); GL11.glDisable(2929); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GlStateManager.func_179092_a(516, 0.003921569F); renderParts(entity, p1, p2, p3, p4, p5, p6, partialTicks); GlStateManager.func_179084_k(); GlStateManager.func_179092_a(516, 0.1F); GlStateManager.func_179121_F(); GL11.glEnable(2929); GlStateManager.func_179132_a(true); }  }
/*     */   private void renderParts(EntityThaumcraftGolem entity, float limbSwing, float prevLimbSwing, float rotFloat, float headPitch, float headYaw, float p_78087_6_, float partialTicks) { ResourceLocation matTexture = (entity.getProperties().getMaterial()).texture; boolean holding = !entity.func_184614_ca().func_190926_b(); boolean aflag = (entity.getProperties().hasTrait(EnumGolemTrait.WHEELED) || entity.getProperties().hasTrait(EnumGolemTrait.FLYER)); Vec3d v1 = new Vec3d(entity.field_70165_t, 0.0D, entity.field_70161_v); Vec3d v2 = new Vec3d(entity.field_70169_q, 0.0D, entity.field_70166_s); double speed = v1.func_72436_e(v2); if (entity.redrawParts || !this.partsCache.containsKey(Integer.valueOf(entity.func_145782_y()))) { entity.redrawParts = false; createPartsCache(entity); }  float f1 = 0.0F; float bry = 0.0F; float rx = (float)Math.toDegrees((MathHelper.func_76126_a(rotFloat * 0.067F) * 0.03F)); float rz = (float)Math.toDegrees((MathHelper.func_76134_b(rotFloat * 0.09F) * 0.05F + 0.05F)); float rrx = 0.0F; float rry = 0.0F; float rrz = 0.0F; float rlx = 0.0F; float rly = 0.0F; float rlz = 0.0F; if (holding) { rrx = 90.0F - rz / 2.0F; rrz = -2.0F; rlx = 90.0F - rz / 2.0F; rlz = 2.0F; } else { if (aflag) { rrx = rx * 2.0F; rlx = -rx * 2.0F; } else { f1 = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 2.0F * prevLimbSwing * 0.5F; rrx = (float)(Math.toDegrees(f1) + rx); f1 = MathHelper.func_76134_b(limbSwing * 0.6662F) * 2.0F * prevLimbSwing * 0.5F; rlx = (float)(Math.toDegrees(f1) - rx); }  rrz += rz + 2.0F; rlz -= rz + 2.0F; }  if (this.swingProgress > 0.0F) { float wiggle = -MathHelper.func_76126_a(MathHelper.func_76129_c(this.swingProgress) * 3.1415927F * 2.0F) * 0.2F; bry = (float)Math.toDegrees(wiggle); rrz = -((float)Math.toDegrees((MathHelper.func_76126_a(wiggle) * 3.0F))); rrx = (float)Math.toDegrees((-MathHelper.func_76134_b(wiggle) * 5.0F)); rry += bry; }  GL11.glEnable(3042); GL11.glBlendFunc(770, 771); GlStateManager.func_179114_b(bry, 0.0F, 1.0F, 0.0F); float lean = 25.0F; if (aflag) lean = 75.0F;  GlStateManager.func_179114_b((float)(speed * lean), -1.0F, 0.0F, 0.0F); GlStateManager.func_179114_b((float)(speed * lean * 0.06D * (entity.field_70177_z - entity.field_70126_B)), 0.0F, 0.0F, -1.0F); GlStateManager.func_179094_E(); GlStateManager.func_179137_b(0.0D, 0.5D, 0.0D); func_110776_a(matTexture); this.baseModel.renderPart("chest"); this.baseModel.renderPart("waist"); if (entity.getGolemColor() > 0) { Color c = new Color(EnumDyeColor.func_176764_b(entity.getGolemColor() - 1).func_193350_e()); GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F); this.baseModel.renderPart("flag"); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); }  for (PartModel part : (ArrayList)((HashMap)this.partsCache.get(Integer.valueOf(entity.func_145782_y()))).get(PartModel.EnumAttachPoint.BODY)) renderPart(entity, part.getObjModel().toString(), part, matTexture, partialTicks, PartModel.EnumLimbSide.MIDDLE);  GlStateManager.func_179121_F(); GlStateManager.func_179094_E(); GlStateManager.func_179137_b(0.0D, 0.75D, -0.03125D); GlStateManager.func_179114_b(headPitch, 0.0F, -1.0F, 0.0F); GlStateManager.func_179114_b(headYaw, -1.0F, 0.0F, 0.0F); for (PartModel part : (ArrayList)((HashMap)this.partsCache.get(Integer.valueOf(entity.func_145782_y()))).get(PartModel.EnumAttachPoint.HEAD)) renderPart(entity, part.getObjModel().toString(), part, matTexture, partialTicks, PartModel.EnumLimbSide.MIDDLE);  func_110776_a(matTexture); this.baseModel.renderPart("head"); GlStateManager.func_179121_F(); GlStateManager.func_179094_E(); GlStateManager.func_179137_b(0.20625D, 0.6875D, 0.0D); null = ((ArrayList)((HashMap)this.partsCache.get(Integer.valueOf(entity.func_145782_y()))).get(PartModel.EnumAttachPoint.ARMS)).iterator(); if (null.hasNext()) { PartModel part = (PartModel)null.next(); rrx = part.preRenderArmRotationX(entity, partialTicks, PartModel.EnumLimbSide.RIGHT, rrx); rry = part.preRenderArmRotationY(entity, partialTicks, PartModel.EnumLimbSide.RIGHT, rry); rrz = part.preRenderArmRotationZ(entity, partialTicks, PartModel.EnumLimbSide.RIGHT, rrz); }  GlStateManager.func_179114_b(rrx, 1.0F, 0.0F, 0.0F); GlStateManager.func_179114_b(rry, 0.0F, 1.0F, 0.0F); GlStateManager.func_179114_b(rrz, 0.0F, 0.0F, 1.0F); func_110776_a(matTexture); this.baseModel.renderPart("arm"); for (PartModel part : (ArrayList)((HashMap)this.partsCache.get(Integer.valueOf(entity.func_145782_y()))).get(PartModel.EnumAttachPoint.ARMS)) renderPart(entity, part.getObjModel().toString(), part, matTexture, partialTicks, PartModel.EnumLimbSide.RIGHT);  GlStateManager.func_179121_F(); GlStateManager.func_179094_E(); GlStateManager.func_179137_b(-0.20625D, 0.6875D, 0.0D); null = ((ArrayList)((HashMap)this.partsCache.get(Integer.valueOf(entity.func_145782_y()))).get(PartModel.EnumAttachPoint.ARMS)).iterator(); if (null.hasNext()) { PartModel part = (PartModel)null.next(); rlx = part.preRenderArmRotationX(entity, partialTicks, PartModel.EnumLimbSide.LEFT, rlx); rly = part.preRenderArmRotationY(entity, partialTicks, PartModel.EnumLimbSide.LEFT, rly); rlz = part.preRenderArmRotationZ(entity, partialTicks, PartModel.EnumLimbSide.LEFT, rlz); }  GlStateManager.func_179114_b(rlx, 1.0F, 0.0F, 0.0F); GlStateManager.func_179114_b(rly + 180.0F, 0.0F, 1.0F, 0.0F); GlStateManager.func_179114_b(rlz, 0.0F, 0.0F, -1.0F); func_110776_a(matTexture); this.baseModel.renderPart("arm"); for (PartModel part : (ArrayList)((HashMap)this.partsCache.get(Integer.valueOf(entity.func_145782_y()))).get(PartModel.EnumAttachPoint.ARMS))
/*     */       renderPart(entity, part.getObjModel().toString(), part, matTexture, partialTicks, PartModel.EnumLimbSide.LEFT);  GlStateManager.func_179121_F(); GlStateManager.func_179094_E(); GlStateManager.func_179137_b(0.09375D, 0.375D, 0.0D); f1 = MathHelper.func_76134_b(limbSwing * 0.6662F) * prevLimbSwing; GlStateManager.func_179114_b((float)Math.toDegrees(f1), 1.0F, 0.0F, 0.0F); for (PartModel part : (ArrayList)((HashMap)this.partsCache.get(Integer.valueOf(entity.func_145782_y()))).get(PartModel.EnumAttachPoint.LEGS))
/*     */       renderPart(entity, part.getObjModel().toString(), part, matTexture, partialTicks, PartModel.EnumLimbSide.RIGHT);  GlStateManager.func_179121_F(); GlStateManager.func_179094_E(); GlStateManager.func_179137_b(-0.09375D, 0.375D, 0.0D); f1 = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * prevLimbSwing; GlStateManager.func_179114_b((float)Math.toDegrees(f1), 1.0F, 0.0F, 0.0F); for (PartModel part : (ArrayList)((HashMap)this.partsCache.get(Integer.valueOf(entity.func_145782_y()))).get(PartModel.EnumAttachPoint.LEGS))
/* 343 */       renderPart(entity, part.getObjModel().toString(), part, matTexture, partialTicks, PartModel.EnumLimbSide.LEFT);  GlStateManager.func_179121_F(); GL11.glDisable(3042); GlStateManager.func_179094_E(); GlStateManager.func_179137_b(0.0D, 0.625D, 0.0D); GlStateManager.func_179114_b(90.0F - rz * 0.5F, 1.0F, 0.0F, 0.0F); drawHeldItem(entity); GlStateManager.func_179121_F(); } private void doRender(EntityThaumcraftGolem entity, double x, double y, double z, float p_76986_8_, float partialTicks) { if (MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Pre(entity, this, x, y, z)))
/* 344 */       return;  GlStateManager.func_179094_E();
/* 345 */     GlStateManager.func_179129_p();
/*     */     
/* 347 */     this.swingProgress = func_77040_d(entity, partialTicks);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 352 */       float f2 = func_77034_a(entity.field_70760_ar, entity.field_70761_aq, partialTicks);
/* 353 */       float f3 = func_77034_a(entity.field_70758_at, entity.field_70759_as, partialTicks);
/* 354 */       float f4 = f3 - f2;
/*     */ 
/*     */       
/* 357 */       if (entity.func_184218_aH() && entity.func_184187_bx() instanceof EntityLivingBase) {
/*     */         
/* 359 */         EntityLivingBase entitylivingbase1 = (EntityLivingBase)entity.func_184187_bx();
/* 360 */         f2 = func_77034_a(entitylivingbase1.field_70760_ar, entitylivingbase1.field_70761_aq, partialTicks);
/* 361 */         f4 = f3 - f2;
/* 362 */         float f5 = MathHelper.func_76142_g(f4);
/*     */         
/* 364 */         if (f5 < -85.0F)
/*     */         {
/* 366 */           f5 = -85.0F;
/*     */         }
/*     */         
/* 369 */         if (f5 >= 85.0F)
/*     */         {
/* 371 */           f5 = 85.0F;
/*     */         }
/*     */         
/* 374 */         f2 = f3 - f5;
/*     */         
/* 376 */         if (f5 * f5 > 2500.0F)
/*     */         {
/* 378 */           f2 += f5 * 0.2F;
/*     */         }
/*     */       } 
/*     */       
/* 382 */       float f9 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks;
/* 383 */       func_77039_a(entity, x, y, z);
/* 384 */       float f5 = func_77044_a(entity, partialTicks);
/* 385 */       func_77043_a(entity, f5, f2, partialTicks);
/* 386 */       GlStateManager.func_179091_B();
/* 387 */       func_77041_b(entity, partialTicks);
/* 388 */       float f7 = entity.field_184618_aE + (entity.field_70721_aZ - entity.field_184618_aE) * partialTicks;
/* 389 */       float f8 = entity.field_184619_aG - entity.field_70721_aZ * (1.0F - partialTicks);
/*     */       
/* 391 */       if (f7 > 1.0F)
/*     */       {
/* 393 */         f7 = 1.0F;
/*     */       }
/*     */       
/* 396 */       GlStateManager.func_179141_d();
/*     */ 
/*     */ 
/*     */       
/* 400 */       if (this.field_188301_f) {
/*     */         
/* 402 */         boolean flag = func_177088_c(entity);
/* 403 */         renderModel(entity, f8, f7, f5, f4, f9, 0.0625F, partialTicks);
/*     */         
/* 405 */         if (flag)
/*     */         {
/* 407 */           func_180565_e();
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 412 */         boolean flag = func_177090_c(entity, partialTicks);
/* 413 */         renderModel(entity, f8, f7, f5, f4, f9, 0.0625F, partialTicks);
/*     */         
/* 415 */         if (flag)
/*     */         {
/* 417 */           func_177091_f();
/*     */         }
/*     */         
/* 420 */         GlStateManager.func_179132_a(true);
/*     */         
/* 422 */         func_177093_a(entity, f8, f7, partialTicks, f5, f4, f9, 0.0625F);
/*     */       } 
/*     */       
/* 425 */       GlStateManager.func_179101_C();
/*     */     
/*     */     }
/* 428 */     catch (Exception exception) {
/*     */       
/* 430 */       logger.error("Couldn't render entity", exception);
/*     */     } 
/*     */     
/* 433 */     GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
/* 434 */     GlStateManager.func_179098_w();
/* 435 */     GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
/* 436 */     GlStateManager.func_179089_o();
/* 437 */     GlStateManager.func_179121_F();
/*     */     
/* 439 */     if (!this.field_188301_f)
/*     */     {
/* 441 */       func_177067_a(entity, x, y, z);
/*     */     }
/* 443 */     MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post(entity, this, x, y, z));
/*     */     
/* 445 */     func_110827_b(entity, x, y, z, p_76986_8_, partialTicks); }
/*     */   private void drawHeldItem(EntityThaumcraftGolem entity) { ItemStack itemstack = entity.func_184614_ca(); if (itemstack != null && !itemstack.func_190926_b()) { GlStateManager.func_179094_E(); Item item = itemstack.func_77973_b(); Minecraft minecraft = Minecraft.func_71410_x(); GlStateManager.func_179114_b(90.0F, -1.0F, 0.0F, 0.0F); GlStateManager.func_179139_a(0.375D, 0.375D, 0.375D); GlStateManager.func_179109_b(0.0F, 0.25F, -1.5F); if (!(item instanceof net.minecraft.item.ItemBlock))
/*     */         GlStateManager.func_179109_b(0.0F, -0.6F, 0.0F);  minecraft.func_175597_ag().func_178099_a(entity, itemstack, ItemCameraTransforms.TransformType.HEAD); GlStateManager.func_179121_F(); }  }
/*     */   private void renderPart(EntityThaumcraftGolem golem, String partName, PartModel part, ResourceLocation matTexture, float partialTicks, PartModel.EnumLimbSide side) { IModelCustom model = (IModelCustom)this.models.get(partName); if (model == null) { model = AdvancedModelLoader.loadModel(part.getObjModel()); if (model != null) { this.models.put(partName, model); } else { return; }  }  for (String op : model.getPartNames()) { GL11.glPushMatrix(); if (part.useMaterialTextureForObjectPart(op)) { func_110776_a(matTexture); } else { func_110776_a(part.getTexture()); }  part.preRenderObjectPart(op, golem, partialTicks, side); model.renderPart(op); part.postRenderObjectPart(op, golem, partialTicks, side); GL11.glPopMatrix(); }
/* 449 */      } private void createPartsCache(EntityThaumcraftGolem golem) { HashMap<PartModel.EnumAttachPoint, ArrayList<PartModel>> pl = new HashMap<PartModel.EnumAttachPoint, ArrayList<PartModel>>();
/* 450 */     pl.put(PartModel.EnumAttachPoint.BODY, new ArrayList());
/* 451 */     pl.put(PartModel.EnumAttachPoint.HEAD, new ArrayList());
/* 452 */     pl.put(PartModel.EnumAttachPoint.ARMS, new ArrayList());
/* 453 */     pl.put(PartModel.EnumAttachPoint.LEGS, new ArrayList());
/*     */     
/* 455 */     IGolemProperties props = golem.getProperties();
/*     */     
/* 457 */     if ((props.getHead()).model != null) {
/* 458 */       ((ArrayList)pl.get((props.getHead()).model.getAttachPoint())).add((props.getHead()).model);
/*     */     }
/*     */     
/* 461 */     if ((props.getArms()).model != null) {
/* 462 */       ((ArrayList)pl.get((props.getArms()).model.getAttachPoint())).add((props.getArms()).model);
/*     */     }
/*     */     
/* 465 */     if ((props.getLegs()).model != null) {
/* 466 */       ((ArrayList)pl.get((props.getLegs()).model.getAttachPoint())).add((props.getLegs()).model);
/*     */     }
/*     */     
/* 469 */     if ((props.getAddon()).model != null) {
/* 470 */       ((ArrayList)pl.get((props.getAddon()).model.getAttachPoint())).add((props.getAddon()).model);
/*     */     }
/*     */     
/* 473 */     this.partsCache.put(Integer.valueOf(golem.func_145782_y()), pl); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 479 */   public void func_76986_a(EntityLiving entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) { doRender((EntityThaumcraftGolem)entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 485 */   protected ResourceLocation func_110775_a(EntityLiving p_110775_1_) { return null; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\RenderThaumcraftGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */