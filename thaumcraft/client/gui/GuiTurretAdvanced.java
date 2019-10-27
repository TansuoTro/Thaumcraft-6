/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.gui.plugins.GuiToggleButton;
/*    */ import thaumcraft.common.container.ContainerTurretAdvanced;
/*    */ import thaumcraft.common.entities.construct.EntityTurretCrossbowAdvanced;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiTurretAdvanced
/*    */   extends GuiContainer
/*    */ {
/*    */   EntityTurretCrossbowAdvanced turret;
/*    */   
/*    */   public GuiTurretAdvanced(InventoryPlayer par1InventoryPlayer, World world, EntityTurretCrossbowAdvanced t) {
/* 26 */     super(new ContainerTurretAdvanced(par1InventoryPlayer, world, t));
/* 27 */     this.field_146999_f = 175;
/* 28 */     this.field_147000_g = 232;
/* 29 */     this.turret = t;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73866_w_() {
/* 34 */     super.func_73866_w_();
/*    */     
/* 36 */     this.field_146292_n.add(new GuiToggleButton(1, this.field_147003_i + 90, this.field_147009_r + 13, 8, 8, "button.turretfocus.1", new Runnable(this) {
/* 37 */             public void run() { GuiToggleButton.toggled = GuiTurretAdvanced.this.turret.getTargetAnimal(); } }));
/* 38 */     this.field_146292_n.add(new GuiToggleButton(2, this.field_147003_i + 90, this.field_147009_r + 27, 8, 8, "button.turretfocus.2", new Runnable(this) {
/* 39 */             public void run() { GuiToggleButton.toggled = GuiTurretAdvanced.this.turret.getTargetMob(); } }));
/* 40 */     this.field_146292_n.add(new GuiToggleButton(3, this.field_147003_i + 90, this.field_147009_r + 41, 8, 8, "button.turretfocus.3", new Runnable(this) {
/* 41 */             public void run() { GuiToggleButton.toggled = GuiTurretAdvanced.this.turret.getTargetPlayer(); } }));
/* 42 */     this.field_146292_n.add(new GuiToggleButton(4, this.field_147003_i + 90, this.field_147009_r + 55, 8, 8, "button.turretfocus.4", new Runnable(this) {
/* 43 */             public void run() { GuiToggleButton.toggled = GuiTurretAdvanced.this.turret.getTargetFriendly(); }
/*    */           }));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 49 */     func_146276_q_();
/* 50 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/* 51 */     func_191948_b(mouseX, mouseY);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_146979_b(int par1, int par2) {}
/*    */ 
/*    */   
/* 58 */   public static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_turret_advanced.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3) {
/* 65 */     this.field_146297_k.field_71446_o.func_110577_a(tex);
/* 66 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 67 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 68 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/* 69 */     GL11.glEnable(3042);
/* 70 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/* 72 */     int h = (int)(39.0F * this.turret.func_110143_aJ() / this.turret.func_110138_aP());
/* 73 */     func_73729_b(k + 30, l + 59, 192, 48, h, 6);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_146284_a(GuiButton button) throws IOException {
/* 78 */     if (button.field_146127_k == 1) {
/*    */       
/* 80 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
/*    */     
/*    */     }
/* 83 */     else if (button.field_146127_k == 2) {
/*    */       
/* 85 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 2);
/*    */     
/*    */     }
/* 88 */     else if (button.field_146127_k == 3) {
/*    */       
/* 90 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 3);
/*    */     
/*    */     }
/* 93 */     else if (button.field_146127_k == 4) {
/*    */       
/* 95 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 4);
/*    */     }
/*    */     else {
/*    */       
/* 99 */       super.func_146284_a(button);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiTurretAdvanced.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */