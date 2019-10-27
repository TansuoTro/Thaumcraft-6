/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.client.renderers.models.block.ModelTubeValve;
/*    */ import thaumcraft.common.tiles.essentia.TileTubeOneway;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileTubeOnewayRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 20 */   private static final ResourceLocation TEX_VALVE = new ResourceLocation("thaumcraft", "textures/models/valve.png");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   private ModelTubeValve model = new ModelTubeValve();
/*    */ 
/*    */   
/* 28 */   EnumFacing fd = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderEntityAt(TileTubeOneway valve, double x, double y, double z, float fq) {
/* 33 */     func_147499_a(TEX_VALVE);
/* 34 */     if (valve.func_145831_w() != null && ThaumcraftApiHelper.getConnectableTile(valve
/* 35 */         .func_145831_w(), valve.func_174877_v(), valve.facing.func_176734_d()) == null) {
/*    */       return;
/*    */     }
/*    */     
/* 39 */     GL11.glPushMatrix();
/* 40 */     this.fd = valve.facing;
/* 41 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*    */     
/* 43 */     if (this.fd.func_96559_d() == 0) {
/* 44 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*    */     } else {
/* 46 */       GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 47 */       GL11.glRotatef(90.0F, this.fd.func_96559_d(), 0.0F, 0.0F);
/*    */     } 
/* 49 */     GL11.glRotatef(90.0F, this.fd.func_82601_c(), this.fd.func_96559_d(), this.fd.func_82599_e());
/*    */ 
/*    */     
/* 52 */     GL11.glPushMatrix();
/* 53 */     GL11.glColor3f(0.45F, 0.5F, 1.0F);
/* 54 */     GL11.glScaled(2.0D, 2.0D, 2.0D);
/* 55 */     GL11.glTranslated(0.0D, -0.3199999928474426D, 0.0D);
/* 56 */     this.model.renderRod();
/* 57 */     GL11.glPopMatrix();
/* 58 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 59 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 64 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 65 */     renderEntityAt((TileTubeOneway)te, x, y, z, partialTicks);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileTubeOnewayRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */