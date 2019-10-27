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
/*    */ import thaumcraft.common.tiles.devices.TileRechargePedestal;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class TileRechargePedestalRenderer
/*    */   extends TileEntitySpecialRenderer<TileRechargePedestal>
/*    */ {
/*    */   public void render(TileRechargePedestal ped, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 20 */     super.func_192841_a(ped, x, y, z, partialTicks, destroyStage, alpha);
/*    */     
/* 22 */     if (ped != null && !ped.getSyncedStackInSlot(0).func_190926_b()) {
/* 23 */       EntityItem entityitem = null;
/* 24 */       float ticks = (Minecraft.func_71410_x().func_175606_aa()).field_70173_aa + partialTicks;
/* 25 */       GL11.glPushMatrix();
/* 26 */       GL11.glTranslatef((float)x + 0.5F, (float)y + 0.75F, (float)z + 0.5F);
/* 27 */       GL11.glScaled(1.5D, 1.5D, 1.5D);
/* 28 */       GL11.glRotatef(ticks % 360.0F, 0.0F, 1.0F, 0.0F);
/* 29 */       ItemStack is = ped.getSyncedStackInSlot(0).func_77946_l();
/* 30 */       is.func_190920_e(1);
/* 31 */       entityitem = new EntityItem((Minecraft.func_71410_x()).field_71441_e, 0.0D, 0.0D, 0.0D, is);
/* 32 */       entityitem.field_70290_d = 0.0F;
/* 33 */       RenderManager rendermanager = Minecraft.func_71410_x().func_175598_ae();
/* 34 */       rendermanager.func_188391_a(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
/* 35 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileRechargePedestalRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */