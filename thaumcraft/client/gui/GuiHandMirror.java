/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.container.ContainerHandMirror;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiHandMirror
/*    */   extends GuiContainer
/*    */ {
/* 18 */   int ci = 0;
/*    */   ResourceLocation tex;
/*    */   
/*    */   public GuiHandMirror(InventoryPlayer par1InventoryPlayer, World world, int x, int y, int z) {
/* 22 */     super(new ContainerHandMirror(par1InventoryPlayer, world, x, y, z));
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_handmirror.png");
/*    */     this.ci = par1InventoryPlayer.field_70461_c;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3) {
/* 53 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 54 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 55 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/* 56 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/* 57 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/* 58 */     GL11.glEnable(3042);
/* 59 */     GL11.glBlendFunc(770, 771);
/* 60 */     float t = this.field_73735_i;
/* 61 */     this.field_73735_i = 300.0F;
/* 62 */     func_73729_b(var5 + 8 + this.ci * 18, var6 + 142, 240, 0, 16, 16);
/* 63 */     this.field_73735_i = t;
/*    */   }
/*    */   
/*    */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*    */     func_146276_q_();
/*    */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*    */     func_191948_b(mouseX, mouseY);
/*    */   }
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer() {}
/*    */   
/*    */   protected boolean func_146983_a(int par1) { return false; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiHandMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */