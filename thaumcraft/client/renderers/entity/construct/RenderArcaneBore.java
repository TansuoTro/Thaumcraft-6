/*     */ package thaumcraft.client.renderers.entity.construct;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.models.entity.ModelArcaneBore;
/*     */ import thaumcraft.common.entities.construct.EntityArcaneBore;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ public class RenderArcaneBore
/*     */   extends RenderLiving
/*     */ {
/*     */   public RenderArcaneBore(RenderManager rm) {
/*  25 */     super(rm, new ModelArcaneBore(), 0.5F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     this.beam = new ResourceLocation("thaumcraft", "textures/misc/beam1.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_76986_a(EntityLiving entity, double x, double y, double z, float entityYaw, float partialTicks) {
/*  54 */     super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
/*     */     
/*  56 */     EntityArcaneBore bore = (EntityArcaneBore)entity;
/*     */     
/*  58 */     if (bore.clientDigging && bore.isActive() && bore.validInventory()) {
/*  59 */       Tessellator tessellator = Tessellator.func_178181_a();
/*     */       
/*  61 */       GL11.glPushMatrix();
/*  62 */       GL11.glDepthMask(false);
/*  63 */       GL11.glEnable(3042);
/*  64 */       GL11.glBlendFunc(770, 1);
/*     */       
/*  66 */       (Minecraft.func_71410_x()).field_71446_o.func_110577_a(UtilsFX.nodeTexture);
/*     */       
/*  68 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/*  69 */       int part = entity.field_70173_aa % 32;
/*     */ 
/*     */       
/*  72 */       Vec3d lv2 = new Vec3d(0.5D, 0.075D, 0.0D);
/*  73 */       Vec3d cv = new Vec3d(x, y + bore.func_70047_e(), z);
/*     */       
/*  75 */       lv2 = Utils.rotateAroundZ(lv2, bore.field_70125_A / 180.0F * 3.1415927F);
/*  76 */       lv2 = Utils.rotateAroundY(lv2, -((bore.field_70759_as + 90.0F) / 180.0F * 3.1415927F));
/*  77 */       cv = cv.func_178787_e(lv2);
/*     */       
/*  79 */       double beamLength = 5.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       GL11.glTranslated(cv.field_72450_a, cv.field_72448_b, cv.field_72449_c);
/*     */       
/*  91 */       GL11.glPushMatrix();
/*  92 */       UtilsFX.renderBillboardQuad(0.5D, 32, 32, 96 + part, 0.0F, 1.0F, 0.4F, 0.8F, 210);
/*  93 */       GL11.glPopMatrix();
/*     */ 
/*     */ 
/*     */       
/*  97 */       GL11.glPushMatrix();
/*  98 */       float var9 = 1.0F;
/*  99 */       float rot = (float)(bore.field_70170_p.field_73011_w.getWorldTime() % 72L * 5L) + 5.0F * partialTicks;
/* 100 */       float size = 1.0F;
/* 101 */       float op = 0.4F;
/*     */       
/* 103 */       (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam);
/*     */       
/* 105 */       GL11.glTexParameterf(3553, 10242, 10497.0F);
/* 106 */       GL11.glTexParameterf(3553, 10243, 10497.0F);
/* 107 */       GL11.glDisable(2884);
/*     */       
/* 109 */       float var11 = entity.field_70173_aa + partialTicks;
/* 110 */       var11 *= -1.0F;
/* 111 */       float var12 = -var11 * 0.2F - MathHelper.func_76141_d(-var11 * 0.1F);
/*     */       
/* 113 */       GL11.glEnable(3042);
/* 114 */       GL11.glBlendFunc(770, 1);
/* 115 */       GL11.glDepthMask(false);
/*     */ 
/*     */       
/* 118 */       float ry = bore.field_70126_B + (bore.field_70177_z - bore.field_70126_B) * partialTicks;
/* 119 */       float rp = bore.field_70127_C + (bore.field_70125_A - bore.field_70127_C) * partialTicks;
/* 120 */       GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 121 */       GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
/* 122 */       GL11.glRotatef(rp, -1.0F, 0.0F, 0.0F);
/*     */       
/* 124 */       double var44 = -0.15D * size;
/* 125 */       double var17 = 0.15D * size;
/* 126 */       double var44b = 0.0D;
/* 127 */       double var17b = 0.0D;
/* 128 */       int i = 200;
/* 129 */       int j = i >> 16 & 0xFFFF;
/* 130 */       int k = i & 0xFFFF;
/* 131 */       GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
/* 132 */       for (int t = 0; t < 3; t++) {
/*     */         
/* 134 */         double var29 = beamLength * size * var9;
/* 135 */         double var31 = 0.0D;
/* 136 */         double var33 = 1.0D;
/* 137 */         double var35 = (-1.0F + var12 + t / 3.0F);
/* 138 */         double var37 = beamLength * size * var9 + var35;
/*     */         
/* 140 */         GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/* 141 */         tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 142 */         tessellator.func_178180_c().func_181662_b(var44b, var29, 0.0D).func_187315_a(var33, var37).func_181666_a(0.0F, 1.0F, 0.4F, op).func_187314_a(j, k).func_181675_d();
/* 143 */         tessellator.func_178180_c().func_181662_b(var44, 0.0D, 0.0D).func_187315_a(var33, var35).func_181666_a(0.0F, 1.0F, 0.4F, op).func_187314_a(j, k).func_181675_d();
/* 144 */         tessellator.func_178180_c().func_181662_b(var17, 0.0D, 0.0D).func_187315_a(var31, var35).func_181666_a(0.0F, 1.0F, 0.4F, op).func_187314_a(j, k).func_181675_d();
/* 145 */         tessellator.func_178180_c().func_181662_b(var17b, var29, 0.0D).func_187315_a(var31, var37).func_181666_a(0.0F, 1.0F, 0.4F, op).func_187314_a(j, k).func_181675_d();
/* 146 */         Tessellator.func_178181_a().func_78381_a();
/*     */       } 
/* 148 */       GL11.glPopMatrix();
/*     */ 
/*     */       
/* 151 */       GL11.glBlendFunc(770, 771);
/* 152 */       GL11.glDisable(3042);
/* 153 */       GL11.glDepthMask(true);
/* 154 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/entity/arcanebore.png");
/*     */   ResourceLocation beam;
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity) { return rl; }
/*     */   
/*     */   protected float func_77040_d(EntityLivingBase livingBase, float partialTickTime) {
/*     */     livingBase.field_70761_aq = 0.0F;
/*     */     livingBase.field_70760_ar = 0.0F;
/*     */     return super.func_77040_d(livingBase, partialTickTime);
/*     */   }
/*     */   
/*     */   protected void func_77041_b(EntityLivingBase entitylivingbaseIn, float partialTickTime) {
/*     */     entitylivingbaseIn.field_70761_aq = 0.0F;
/*     */     entitylivingbaseIn.field_70760_ar = 0.0F;
/*     */     super.func_77041_b(entitylivingbaseIn, partialTickTime);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\construct\RenderArcaneBore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */