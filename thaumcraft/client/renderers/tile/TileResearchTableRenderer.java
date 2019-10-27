/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.block.ModelResearchTable;
/*    */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*    */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileResearchTableRenderer
/*    */   extends TileEntitySpecialRenderer<TileResearchTable>
/*    */ {
/* 20 */   private ModelResearchTable tableModel = new ModelResearchTable();
/*    */   
/* 22 */   private static final ResourceLocation TEX = new ResourceLocation("thaumcraft", "textures/blocks/research_table_model.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(TileResearchTable table, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 27 */     GL11.glPushMatrix();
/* 28 */     func_147499_a(TEX);
/* 29 */     GL11.glTranslatef((float)x + 0.5F, (float)y + 1.0F, (float)z + 0.5F);
/* 30 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 31 */     switch (BlockStateUtils.getFacing(table.func_145832_p())) { case EAST:
/* 32 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 33 */       case WEST: GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 34 */       case SOUTH: GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*    */         break; }
/*    */ 
/*    */     
/* 38 */     if (table.data != null) {
/* 39 */       this.tableModel.renderScroll(Aspect.ALCHEMY.getColor());
/*    */     }
/*    */     
/* 42 */     if (!table.getSyncedStackInSlot(0).func_190926_b() && table.getSyncedStackInSlot(0).func_77973_b() instanceof thaumcraft.api.items.IScribeTools) {
/* 43 */       this.tableModel.renderInkwell();
/* 44 */       GL11.glPushMatrix();
/* 45 */       GL11.glEnable(3042);
/* 46 */       GL11.glBlendFunc(770, 771);
/* 47 */       GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 48 */       GL11.glTranslated(-0.5D, 0.1D, 0.125D);
/* 49 */       GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/* 50 */       GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 51 */       UtilsFX.renderItemIn2D("thaumcraft:research/quill", 0.0625F);
/* 52 */       GL11.glDisable(3042);
/* 53 */       GL11.glPopMatrix();
/*    */     } 
/*    */     
/* 56 */     GL11.glPopMatrix();
/*    */     
/* 58 */     GL11.glPushMatrix();
/*    */ 
/*    */     
/* 61 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileResearchTableRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */