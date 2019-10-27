/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.block.ModelBanner;
/*    */ import thaumcraft.common.tiles.misc.TileBanner;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileBannerRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 23 */   private ModelBanner model = new ModelBanner();
/* 24 */   private static final ResourceLocation TEX_CULT = new ResourceLocation("thaumcraft", "textures/models/banner_cultist.png");
/*    */   
/* 26 */   private static final ResourceLocation TEX_BLANK = new ResourceLocation("thaumcraft", "textures/models/banner_blank.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderTileEntityAt(TileBanner banner, double par2, double par4, double par6, float par8) {
/* 34 */     GL11.glPushMatrix();
/* 35 */     if (banner.getAspect() == null && banner.getColor() == -1) {
/* 36 */       func_147499_a(TEX_CULT);
/*    */     } else {
/* 38 */       func_147499_a(TEX_BLANK);
/*    */     } 
/* 40 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.5F, (float)par6 + 0.5F);
/* 41 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 42 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 44 */     if (banner.func_145831_w() != null) {
/* 45 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 46 */       float f2 = (banner.getBannerFacing() * 360) / 16.0F;
/* 47 */       GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
/*    */     } 
/*    */     
/* 50 */     if (!banner.getWall()) {
/* 51 */       this.model.renderPole();
/*    */     } else {
/* 53 */       GL11.glTranslated(0.0D, 1.0D, -0.4125D);
/*    */     } 
/*    */     
/* 56 */     this.model.renderBeam();
/*    */     
/* 58 */     if (banner.getColor() != -1) {
/* 59 */       Color c = new Color(banner.getColor());
/* 60 */       GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/*    */     } 
/*    */     
/* 63 */     this.model.renderTabs();
/*    */     
/* 65 */     EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/* 66 */     float f3 = (banner.func_174877_v().func_177958_n() * 7 + banner.func_174877_v().func_177956_o() * 9 + banner.func_174877_v().func_177952_p() * 13) + entityPlayerSP.field_70173_aa + par8;
/* 67 */     float rx = 0.02F - MathHelper.func_76126_a(f3 / 11.0F) * 0.02F;
/*    */     
/* 69 */     this.model.Banner.field_78795_f = rx;
/* 70 */     this.model.renderBanner();
/*    */     
/* 72 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 74 */     if (banner.getAspect() != null) {
/* 75 */       GL11.glPushMatrix();
/* 76 */       GL11.glTranslatef(0.0F, 0.0F, 0.05001F);
/* 77 */       GL11.glScaled(0.0375D, 0.0375D, 0.0375D);
/* 78 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 79 */       GL11.glRotatef(-rx * 57.295776F * 2.0F, 1.0F, 0.0F, 0.0F);
/*    */       
/* 81 */       UtilsFX.drawTag(-8, 0, banner.getAspect(), 0.0F, 0, 0.0D, 771, 0.75F, false);
/* 82 */       GL11.glPopMatrix();
/*    */     } 
/*    */ 
/*    */     
/* 86 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 92 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 93 */     renderTileEntityAt((TileBanner)te, x, y, z, partialTicks);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileBannerRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */