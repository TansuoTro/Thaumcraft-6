/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.block.ModelTubeValve;
/*    */ import thaumcraft.common.tiles.essentia.TileTubeValve;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileTubeValveRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 17 */   private static final ResourceLocation TEX_VALVE = new ResourceLocation("thaumcraft", "textures/models/valve.png");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   private ModelTubeValve model = new ModelTubeValve();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderEntityAt(TileTubeValve valve, double x, double y, double z, float fq) {
/* 29 */     func_147499_a(TEX_VALVE);
/*    */     
/* 31 */     GL11.glPushMatrix();
/*    */     
/* 33 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*    */     
/* 35 */     if (valve.facing.func_96559_d() == 0) {
/* 36 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*    */     } else {
/* 38 */       GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 39 */       GL11.glRotatef(90.0F, valve.facing.func_96559_d(), 0.0F, 0.0F);
/*    */     } 
/* 41 */     GL11.glRotatef(90.0F, valve.facing.func_82601_c(), valve.facing.func_96559_d(), valve.facing.func_82599_e());
/*    */ 
/*    */     
/* 44 */     GL11.glRotated(-valve.rotation * 1.5D, 0.0D, 1.0D, 0.0D);
/* 45 */     GL11.glTranslated(0.0D, (-0.03F - valve.rotation / 360.0F * 0.09F), 0.0D);
/*    */     
/* 47 */     GL11.glPushMatrix();
/* 48 */     this.model.renderRing();
/* 49 */     GL11.glScaled(0.75D, 1.0D, 0.75D);
/* 50 */     this.model.renderRod();
/* 51 */     GL11.glPopMatrix();
/*    */     
/* 53 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 60 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 61 */     renderEntityAt((TileTubeValve)te, x, y, z, partialTicks);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileTubeValveRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */