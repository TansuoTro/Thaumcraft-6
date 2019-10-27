/*     */ package thaumcraft.client.renderers.entity.construct;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderCultistPortalLesser
/*     */   extends Render
/*     */ {
/*     */   public RenderCultistPortalLesser(RenderManager renderManager) {
/*  26 */     super(renderManager);
/*  27 */     this.field_76989_e = 0.0F;
/*  28 */     this.field_76987_f = 0.0F;
/*     */   }
/*     */   
/*  31 */   public static final ResourceLocation portaltex = new ResourceLocation("thaumcraft", "textures/misc/cultist_portal.png");
/*     */ 
/*     */   
/*     */   public void renderPortal(EntityCultistPortalLesser portal, double px, double py, double pz, float par8, float f) {
/*  35 */     if (portal.isActive()) {
/*  36 */       long nt = System.nanoTime();
/*  37 */       long time = nt / 50000000L;
/*  38 */       float scaley = 1.4F;
/*  39 */       int e = (int)Math.min(50.0F, portal.activeCounter + f);
/*     */       
/*  41 */       if (portal.field_70737_aN > 0) {
/*  42 */         double d = Math.sin((portal.field_70737_aN * 72) * Math.PI / 180.0D);
/*  43 */         scaley = (float)(scaley - d / 4.0D);
/*  44 */         e = (int)(e + 6.0D * d);
/*     */       } 
/*     */       
/*  47 */       if (portal.pulse > 0) {
/*  48 */         double d = Math.sin((portal.pulse * 36) * Math.PI / 180.0D);
/*  49 */         scaley = (float)(scaley + d / 4.0D);
/*  50 */         e = (int)(e + 12.0D * d);
/*     */       } 
/*     */       
/*  53 */       float scale = e / 50.0F * 1.25F;
/*     */       
/*  55 */       py += (portal.field_70131_O / 2.0F);
/*     */       
/*  57 */       float m = (1.0F - portal.func_110143_aJ() / portal.func_110138_aP()) / 3.0F;
/*  58 */       float bob = MathHelper.func_76126_a(portal.activeCounter / (5.0F - 12.0F * m)) * m + m;
/*  59 */       float bob2 = MathHelper.func_76126_a(portal.activeCounter / (6.0F - 15.0F * m)) * m + m;
/*  60 */       float alpha = 1.0F - bob;
/*     */       
/*  62 */       scaley -= bob / 4.0F;
/*  63 */       scale -= bob2 / 3.0F;
/*     */       
/*  65 */       func_110776_a(portaltex);
/*  66 */       GL11.glPushMatrix();
/*     */       
/*  68 */       GL11.glEnable(3042);
/*  69 */       GL11.glBlendFunc(770, 771);
/*  70 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/*     */       
/*  72 */       if (Minecraft.func_71410_x().func_175606_aa() instanceof net.minecraft.entity.player.EntityPlayer) {
/*  73 */         GL11.glDepthMask(false);
/*  74 */         Tessellator tessellator = Tessellator.func_178181_a();
/*  75 */         float arX = ActiveRenderInfo.func_178808_b();
/*  76 */         float arZ = ActiveRenderInfo.func_178803_d();
/*  77 */         float arYZ = ActiveRenderInfo.func_178805_e();
/*  78 */         float arXY = ActiveRenderInfo.func_178807_f();
/*  79 */         float arXZ = ActiveRenderInfo.func_178809_c();
/*     */         
/*  81 */         tessellator.func_178180_c().func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/*  82 */         Vec3d v1 = new Vec3d((-arX - arYZ), -arXZ, (-arZ - arXY));
/*  83 */         Vec3d v2 = new Vec3d((-arX + arYZ), arXZ, (-arZ + arXY));
/*  84 */         Vec3d v3 = new Vec3d((arX + arYZ), arXZ, (arZ + arXY));
/*  85 */         Vec3d v4 = new Vec3d((arX - arYZ), -arXZ, (arZ - arXY));
/*  86 */         int frame = 15 - (int)time % 16;
/*  87 */         float f2 = frame / 16.0F;
/*  88 */         float f3 = f2 + 0.0625F;
/*  89 */         float f4 = 0.0F;
/*  90 */         float f5 = 1.0F;
/*  91 */         int i = 220;
/*  92 */         int j = i >> 16 & 0xFFFF;
/*  93 */         int k = i & 0xFFFF;
/*  94 */         tessellator.func_178180_c().func_181662_b(px + v1.field_72450_a * scale, py + v1.field_72448_b * scaley, pz + v1.field_72449_c * scale).func_187315_a(f3, f4).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_187314_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/*  95 */         tessellator.func_178180_c().func_181662_b(px + v2.field_72450_a * scale, py + v2.field_72448_b * scaley, pz + v2.field_72449_c * scale).func_187315_a(f3, f5).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_187314_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/*  96 */         tessellator.func_178180_c().func_181662_b(px + v3.field_72450_a * scale, py + v3.field_72448_b * scaley, pz + v3.field_72449_c * scale).func_187315_a(f2, f5).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_187314_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/*  97 */         tessellator.func_178180_c().func_181662_b(px + v4.field_72450_a * scale, py + v4.field_72448_b * scaley, pz + v4.field_72449_c * scale).func_187315_a(f2, f4).func_181666_a(1.0F, 1.0F, 1.0F, alpha).func_187314_a(j, k).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/*  98 */         tessellator.func_78381_a();
/*  99 */         GL11.glDepthMask(true);
/*     */       } 
/*     */       
/* 102 */       GL11.glDisable(32826);
/* 103 */       GL11.glDisable(3042);
/* 104 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) { renderPortal((EntityCultistPortalLesser)par1Entity, par2, par4, par6, par8, par9); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   protected ResourceLocation func_110775_a(Entity entity) { return portaltex; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\construct\RenderCultistPortalLesser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */