/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.container.ContainerSmelter;
/*    */ import thaumcraft.common.tiles.essentia.TileSmelter;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiSmelter
/*    */   extends GuiContainer
/*    */ {
/*    */   private TileSmelter furnaceInventory;
/*    */   ResourceLocation tex;
/*    */   
/*    */   public GuiSmelter(InventoryPlayer par1InventoryPlayer, TileSmelter par2TileEntityFurnace) {
/* 21 */     super(new ContainerSmelter(par1InventoryPlayer, par2TileEntityFurnace));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_smelter.png");
/*    */     this.furnaceInventory = par2TileEntityFurnace;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3) {
/* 47 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 48 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 49 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 50 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*    */     
/* 52 */     GL11.glEnable(3042);
/* 53 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */ 
/*    */     
/* 56 */     if (this.furnaceInventory.getBurnTimeRemainingScaled(20) > 0) {
/*    */       
/* 58 */       int i1 = this.furnaceInventory.getBurnTimeRemainingScaled(20);
/* 59 */       func_73729_b(k + 80, l + 26 + 20 - i1, 176, 20 - i1, 16, i1);
/*    */     } 
/*    */     
/* 62 */     int i1 = this.furnaceInventory.getCookProgressScaled(46);
/* 63 */     func_73729_b(k + 106, l + 13 + 46 - i1, 216, 46 - i1, 9, i1);
/*    */     
/* 65 */     i1 = this.furnaceInventory.getVisScaled(48);
/* 66 */     func_73729_b(k + 61, l + 12 + 48 - i1, 200, 48 - i1, 8, i1);
/*    */     
/* 68 */     func_73729_b(k + 60, l + 8, 232, 0, 10, 55);
/*    */   }
/*    */   
/*    */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*    */     func_146276_q_();
/*    */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*    */     func_191948_b(mouseX, mouseY);
/*    */   }
/*    */   
/*    */   protected void func_146979_b(int par1, int par2) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiSmelter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */