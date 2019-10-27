/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.FMLClientHandler;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.block.ModelBoreBase;
/*    */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*    */ import thaumcraft.common.tiles.crafting.TilePatternCrafter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TilePatternCrafterRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 24 */   private ModelBoreBase model = new ModelBoreBase();
/*    */ 
/*    */ 
/*    */   
/* 28 */   private static final ResourceLocation TEX = new ResourceLocation("thaumcraft", "textures/blocks/pattern_crafter_modes.png");
/* 29 */   private static final ResourceLocation ICON = new ResourceLocation("thaumcraft", "textures/misc/gear_brass.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderTileEntityAt(TilePatternCrafter pc, double x, double y, double z, float fq) {
/* 34 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 35 */     int f = 3;
/* 36 */     if (pc.func_145831_w() != null) {
/* 37 */       f = BlockStateUtils.getFacing(pc.func_145832_p()).ordinal();
/*    */     }
/*    */     
/* 40 */     GL11.glPushMatrix();
/*    */     
/* 42 */     GL11.glTranslatef((float)x + 0.5F, (float)y + 0.75F, (float)z + 0.5F);
/*    */     
/* 44 */     switch (f) { case 5:
/* 45 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 46 */       case 4: GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 47 */       case 2: GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*    */         break; }
/*    */     
/* 50 */     GL11.glPushMatrix();
/* 51 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 52 */     GL11.glTranslatef(0.0F, 0.0F, -0.5F);
/*    */     
/* 54 */     UtilsFX.renderQuadCentered(TEX, 10, 1, pc.type, 0.5F, 1.0F, 1.0F, 1.0F, pc
/* 55 */         .func_145838_q().func_185484_c(pc.func_145831_w().func_180495_p(pc.func_174877_v()), pc.func_145831_w(), pc.func_174877_v()), 771, 1.0F);
/* 56 */     GL11.glPopMatrix();
/*    */     
/* 58 */     mc.field_71446_o.func_110577_a(ICON);
/*    */     
/* 60 */     GL11.glPushMatrix();
/* 61 */     GL11.glTranslatef(-0.2F, -0.40625F, 0.05F);
/* 62 */     GL11.glRotatef(-pc.rot % 360.0F, 0.0F, 0.0F, 1.0F);
/* 63 */     GL11.glScaled(0.5D, 0.5D, 1.0D);
/* 64 */     GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 65 */     UtilsFX.renderTextureIn3D(1.0F, 0.0F, 0.0F, 1.0F, 16, 16, 0.1F);
/* 66 */     GL11.glPopMatrix();
/*    */     
/* 68 */     GL11.glPushMatrix();
/* 69 */     GL11.glTranslatef(0.2F, -0.40625F, 0.05F);
/* 70 */     GL11.glRotatef(pc.rot % 360.0F, 0.0F, 0.0F, 1.0F);
/* 71 */     GL11.glScaled(0.5D, 0.5D, 1.0D);
/* 72 */     GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 73 */     UtilsFX.renderTextureIn3D(1.0F, 0.0F, 0.0F, 1.0F, 16, 16, 0.1F);
/* 74 */     GL11.glPopMatrix();
/*    */     
/* 76 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 81 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 82 */     renderTileEntityAt((TilePatternCrafter)te, x, y, z, partialTicks);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TilePatternCrafterRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */