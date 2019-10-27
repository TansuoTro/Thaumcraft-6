/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.api.aspects.IEssentiaTransport;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.client.renderers.models.block.ModelBoreBase;
/*    */ import thaumcraft.common.tiles.essentia.TileAlembic;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileAlembicRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 22 */   private ModelBoreBase modelBore = new ModelBoreBase();
/*    */ 
/*    */ 
/*    */   
/* 26 */   private static final ResourceLocation TEX_LABEL = new ResourceLocation("thaumcraft", "textures/models/label.png");
/* 27 */   private static final ResourceLocation TEX_BORE = new ResourceLocation("thaumcraft", "textures/models/bore.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderTileEntityAt(TileAlembic tile, double x, double y, double z, float f) {
/* 34 */     if (tile.aspectFilter != null) {
/* 35 */       GL11.glPushMatrix();
/* 36 */       GL11.glBlendFunc(770, 771);
/* 37 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 38 */       GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
/* 39 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 40 */       switch (tile.facing) { case 5:
/* 41 */           GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/* 42 */         case 3: GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 43 */         case 2: GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
/*    */           break; }
/*    */       
/* 46 */       GL11.glPushMatrix();
/* 47 */       GL11.glTranslatef(0.0F, 0.5F, -0.376F);
/* 48 */       UtilsFX.renderQuadCentered(TEX_LABEL, 0.44F, 1.0F, 1.0F, 1.0F, -99, 771, 1.0F);
/* 49 */       GL11.glPopMatrix();
/*    */       
/* 51 */       GL11.glPushMatrix();
/* 52 */       GL11.glTranslatef(0.0F, 0.5F, -0.377F);
/* 53 */       GL11.glScaled(0.02D, 0.02D, 0.02D);
/* 54 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */       
/* 56 */       UtilsFX.drawTag(-8, -8, tile.aspectFilter);
/* 57 */       GL11.glPopMatrix();
/*    */       
/* 59 */       GL11.glPopMatrix();
/*    */     } 
/*    */     
/* 62 */     if (tile.func_145831_w() != null) {
/* 63 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 64 */       func_147499_a(TEX_BORE);
/* 65 */       for (EnumFacing dir : EnumFacing.field_176754_o) {
/* 66 */         if (tile.canOutputTo(dir)) {
/* 67 */           TileEntity te = ThaumcraftApiHelper.getConnectableTile(tile.func_145831_w(), tile.func_174877_v(), dir);
/* 68 */           if (te != null && ((IEssentiaTransport)te).canInputFrom(dir.func_176734_d())) {
/* 69 */             GL11.glPushMatrix();
/* 70 */             GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
/* 71 */             switch (dir.ordinal()) { case 0:
/* 72 */                 GL11.glTranslatef(-0.5F, 0.5F, 0.0F);
/* 73 */                 GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F); break;
/* 74 */               case 1: GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 75 */                 GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F); break;
/* 76 */               case 2: GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 77 */               case 3: GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 78 */               case 4: GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break;
/* 79 */               case 5: GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F); break; }
/*    */             
/* 81 */             this.modelBore.renderNozzle();
/* 82 */             GL11.glPopMatrix();
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 92 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 93 */     renderTileEntityAt((TileAlembic)te, x, y, z, partialTicks);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileAlembicRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */