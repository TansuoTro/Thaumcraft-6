/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.client.renderers.models.block.ModelTubeValve;
/*    */ import thaumcraft.common.tiles.essentia.TileTubeBuffer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileTubeBufferRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 19 */   private static final ResourceLocation TEX_VALVE = new ResourceLocation("thaumcraft", "textures/models/valve.png");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   private ModelTubeValve model = new ModelTubeValve();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderEntityAt(TileTubeBuffer buffer, double x, double y, double z, float fq) {
/* 31 */     func_147499_a(TEX_VALVE);
/*    */     
/* 33 */     if (buffer.func_145831_w() != null) {
/* 34 */       for (EnumFacing dir : EnumFacing.field_82609_l) {
/*    */         
/* 36 */         if (buffer.chokedSides[dir.ordinal()] != 0 && buffer.openSides[dir.ordinal()] && 
/* 37 */           ThaumcraftApiHelper.getConnectableTile(buffer.func_145831_w(), buffer.func_174877_v(), dir) != null) {
/*    */ 
/*    */           
/* 40 */           GL11.glPushMatrix();
/* 41 */           GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*    */           
/* 43 */           if (dir.func_176734_d().func_96559_d() == 0) {
/* 44 */             GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*    */           } else {
/* 46 */             GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 47 */             GL11.glRotatef(90.0F, dir.func_176734_d().func_96559_d(), 0.0F, 0.0F);
/*    */           } 
/* 49 */           GL11.glRotatef(90.0F, dir.func_176734_d().func_82601_c(), dir.func_176734_d().func_96559_d(), dir.func_176734_d().func_82599_e());
/*    */           
/* 51 */           GL11.glPushMatrix();
/*    */           
/* 53 */           if (buffer.chokedSides[dir.ordinal()] == 2) {
/* 54 */             GL11.glColor3f(1.0F, 0.3F, 0.3F);
/*    */           } else {
/* 56 */             GL11.glColor3f(0.3F, 0.3F, 1.0F);
/*    */           } 
/*    */           
/* 59 */           GL11.glScaled(2.0D, 1.0D, 2.0D);
/*    */           
/* 61 */           GL11.glTranslated(0.0D, -0.5D, 0.0D);
/* 62 */           this.model.renderRod();
/* 63 */           GL11.glPopMatrix();
/* 64 */           GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 65 */           GL11.glPopMatrix();
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 72 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 73 */     renderEntityAt((TileTubeBuffer)te, x, y, z, partialTicks);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileTubeBufferRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */