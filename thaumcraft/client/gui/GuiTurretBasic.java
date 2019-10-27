/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.container.ContainerTurretBasic;
/*    */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiTurretBasic
/*    */   extends GuiContainer
/*    */ {
/*    */   EntityTurretCrossbow turret;
/*    */   ResourceLocation tex;
/*    */   
/*    */   public GuiTurretBasic(InventoryPlayer par1InventoryPlayer, World world, EntityTurretCrossbow t) {
/* 23 */     super(new ContainerTurretBasic(par1InventoryPlayer, world, t));
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
/* 41 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_turret_basic.png");
/*    */     this.field_146999_f = 175;
/*    */     this.field_147000_g = 232;
/*    */     this.turret = t;
/*    */   }
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3) {
/* 48 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 49 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 50 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 51 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/* 52 */     GL11.glEnable(3042);
/* 53 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/* 55 */     int h = (int)(39.0F * this.turret.func_110143_aJ() / this.turret.func_110138_aP());
/* 56 */     func_73729_b(k + 68, l + 59, 192, 48, h, 6);
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


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiTurretBasic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */