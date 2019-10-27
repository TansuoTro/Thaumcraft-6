/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.tiles.crafting.TilePedestal;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TilePedestalRenderer
/*    */   extends TileEntitySpecialRenderer<TilePedestal>
/*    */ {
/*    */   public void render(TilePedestal ped, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 19 */     super.func_192841_a(ped, x, y, z, partialTicks, destroyStage, alpha);
/* 20 */     if (ped != null && !ped.getSyncedStackInSlot(0).func_190926_b()) {
/* 21 */       EntityItem entityitem = null;
/* 22 */       float ticks = (Minecraft.func_71410_x().func_175606_aa()).field_70173_aa + partialTicks;
/* 23 */       GL11.glPushMatrix();
/* 24 */       GL11.glTranslatef((float)x + 0.5F, (float)y + 0.75F, (float)z + 0.5F);
/* 25 */       GL11.glScaled(1.25D, 1.25D, 1.25D);
/* 26 */       GL11.glRotatef(ticks % 360.0F, 0.0F, 1.0F, 0.0F);
/* 27 */       ItemStack is = ped.getSyncedStackInSlot(0).func_77946_l();
/* 28 */       is.func_190920_e(1);
/* 29 */       entityitem = new EntityItem((Minecraft.func_71410_x()).field_71441_e, 0.0D, 0.0D, 0.0D, is);
/* 30 */       entityitem.field_70290_d = 0.0F;
/* 31 */       RenderManager rendermanager = Minecraft.func_71410_x().func_175598_ae();
/* 32 */       rendermanager.func_188391_a(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
/* 33 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TilePedestalRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */