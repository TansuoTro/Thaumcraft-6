/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.crafting.CrucibleRecipe;
/*    */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*    */ import thaumcraft.common.tiles.crafting.TileThaumatorium;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileThaumatoriumRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 25 */   EntityItem entityitem = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderTileEntityAt(TileThaumatorium tile, double par2, double par4, double par6, float par8) {
/* 32 */     EnumFacing facing = BlockStateUtils.getFacing(tile.func_145832_p());
/* 33 */     if (tile != null && tile.func_145831_w() != null && tile.recipeHash != null && tile.recipeHash.size() > 0) {
/* 34 */       int stack = (Minecraft.func_71410_x().func_175606_aa()).field_70173_aa / 20 % tile.recipeHash.size();
/* 35 */       CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(((Integer)tile.recipeHash.get(stack)).intValue());
/* 36 */       if (recipe != null) {
/* 37 */         GL11.glPushMatrix();
/* 38 */         GL11.glTranslatef((float)par2 + 0.5F + facing.func_82601_c() / 1.99F, (float)par4 + 1.125F, (float)par6 + 0.5F + facing.func_82599_e() / 1.99F);
/* 39 */         switch (facing.ordinal()) { case 5:
/* 40 */             GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F); break;
/* 41 */           case 4: GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F); break;
/* 42 */           case 2: GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); break; }
/*    */         
/* 44 */         GL11.glScaled(0.75D, 0.75D, 0.75D);
/*    */         
/* 46 */         ItemStack is = recipe.getRecipeOutput().func_77946_l();
/* 47 */         is.func_190920_e(1);
/* 48 */         this.entityitem = new EntityItem(tile.func_145831_w(), 0.0D, 0.0D, 0.0D, is);
/* 49 */         this.entityitem.field_70290_d = 0.0F;
/* 50 */         RenderManager rendermanager = Minecraft.func_71410_x().func_175598_ae();
/*    */         
/* 52 */         rendermanager.func_188391_a(this.entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
/* 53 */         GL11.glPopMatrix();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 61 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 62 */     renderTileEntityAt((TileThaumatorium)te, x, y, z, partialTicks);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileThaumatoriumRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */