/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.container.ContainerFocusPouch;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiFocusPouch extends GuiContainer {
/*    */   private int blockSlot;
/*    */   ResourceLocation tex;
/*    */   
/*    */   public GuiFocusPouch(InventoryPlayer par1InventoryPlayer, World world, int x, int y, int z) {
/* 19 */     super(new ContainerFocusPouch(par1InventoryPlayer, world, x, y, z));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_focuspouch.png");
/*    */     this.blockSlot = par1InventoryPlayer.field_70461_c;
/*    */     this.field_146999_f = 175;
/*    */     this.field_147000_g = 232;
/*    */   } public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 30 */     func_146276_q_();
/* 31 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/* 32 */     func_191948_b(mouseX, mouseY);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_146979_b(int par1, int par2) {
/* 37 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 38 */     float t = this.field_73735_i;
/* 39 */     this.field_73735_i = 300.0F;
/* 40 */     GL11.glEnable(3042);
/* 41 */     func_73729_b(8 + this.blockSlot * 18, 209, 240, 0, 16, 16);
/* 42 */     GL11.glDisable(3042);
/* 43 */     this.field_73735_i = t;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 48 */   protected boolean func_146983_a(int par1) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3) {
/* 57 */     if (((ItemStack)this.field_146297_k.field_71439_g.field_71071_by.field_70462_a.get(this.blockSlot)).func_190926_b()) this.field_146297_k.field_71439_g.func_71053_j(); 
/* 58 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 59 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 60 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/* 61 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/* 62 */     GL11.glEnable(3042);
/* 63 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/* 64 */     GL11.glDisable(3042);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiFocusPouch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */