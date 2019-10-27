/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.block.ModelCentrifuge;
/*    */ import thaumcraft.common.tiles.essentia.TileCentrifuge;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileCentrifugeRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 19 */   private static final ResourceLocation TEX = new ResourceLocation("thaumcraft", "textures/models/centrifuge.png");
/*    */ 
/*    */ 
/*    */   
/* 23 */   private ModelCentrifuge model = new ModelCentrifuge();
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderEntityAt(TileCentrifuge cf, double x, double y, double z, float fq, int destroyStage) {
/* 28 */     func_147499_a(TEX);
/*    */     
/* 30 */     GL11.glPushMatrix();
/*    */     
/* 32 */     if (destroyStage >= 0) {
/*    */       
/* 34 */       func_147499_a(field_178460_a[destroyStage]);
/* 35 */       GlStateManager.func_179128_n(5890);
/* 36 */       GlStateManager.func_179094_E();
/* 37 */       GlStateManager.func_179152_a(4.0F, 4.0F, 1.0F);
/* 38 */       GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
/* 39 */       GlStateManager.func_179128_n(5888);
/*    */     } 
/*    */     
/* 42 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/* 43 */     this.model.renderBoxes();
/* 44 */     GL11.glRotated(cf.rotation, 0.0D, 1.0D, 0.0D);
/* 45 */     this.model.renderSpinnyBit();
/*    */     
/* 47 */     if (destroyStage >= 0) {
/*    */       
/* 49 */       GlStateManager.func_179128_n(5890);
/* 50 */       GlStateManager.func_179121_F();
/* 51 */       GlStateManager.func_179128_n(5888);
/*    */     } 
/*    */     
/* 54 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 61 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 62 */     renderEntityAt((TileCentrifuge)te, x, y, z, partialTicks, destroyStage);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileCentrifugeRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */