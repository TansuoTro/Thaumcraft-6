/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.tiles.crafting.TileCrucible;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileCrucibleRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 18 */   public void renderEntityAt(TileCrucible cr, double x, double y, double z, float fq) { if (cr.tank.getFluidAmount() > 0) renderFluid(cr, x, y, z);
/*    */      }
/*    */   
/*    */   public void renderFluid(TileCrucible cr, double x, double y, double z) {
/* 22 */     GL11.glPushMatrix();
/* 23 */     GL11.glTranslated(x, y + cr.getFluidHeight(), z + 1.0D);
/* 24 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 25 */     if (cr.tank.getFluidAmount() > 0) {
/* 26 */       TextureAtlasSprite icon = Minecraft.func_71410_x().func_175602_ab().func_175023_a().func_178122_a(Blocks.field_150355_j.func_176223_P());
/* 27 */       cr.getClass(); float recolor = cr.aspects.visSize() / 500.0F;
/* 28 */       if (recolor > 0.0F) recolor = 0.5F + recolor / 2.0F; 
/* 29 */       if (recolor > 1.0F) recolor = 1.0F; 
/* 30 */       UtilsFX.renderQuadFromIcon(icon, 1.0F, 1.0F - recolor / 3.0F, 1.0F - recolor, 1.0F - recolor / 2.0F, BlocksTC.crucible
/* 31 */           .func_185484_c(cr.func_145831_w().func_180495_p(cr.func_174877_v()), cr.func_145831_w(), cr.func_174877_v()), 771, 1.0F);
/*    */     } 
/*    */ 
/*    */     
/* 35 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 40 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 41 */     renderEntityAt((TileCrucible)te, x, y, z, partialTicks);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileCrucibleRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */