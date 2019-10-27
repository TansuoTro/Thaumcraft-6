/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.container.ContainerPech;
/*    */ import thaumcraft.common.entities.monster.EntityPech;
/*    */ import thaumcraft.common.lib.SoundsTC;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiPech
/*    */   extends GuiContainer
/*    */ {
/*    */   EntityPech pech;
/*    */   
/*    */   public GuiPech(InventoryPlayer par1InventoryPlayer, World world, EntityPech pech) {
/* 25 */     super(new ContainerPech(par1InventoryPlayer, world, pech));
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
/* 43 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_pech.png"); this.field_146999_f = 175;
/*    */     this.field_147000_g = 232;
/*    */     this.pech = pech;
/*    */   } ResourceLocation tex; public void func_73863_a(int mouseX, int mouseY, float partialTicks) { func_146276_q_();
/*    */     super.func_73863_a(mouseX, mouseY, partialTicks);
/* 48 */     func_191948_b(mouseX, mouseY); } protected void func_146976_a(float par1, int par2, int par3) { this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 49 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 50 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/* 51 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/* 52 */     GL11.glEnable(3042);
/* 53 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */ 
/*    */     
/* 56 */     if (this.pech.isValued(this.field_147002_h.func_75139_a(0).func_75211_c()) && 
/* 57 */       !this.field_147002_h.func_75139_a(0).func_75211_c().func_190926_b() && this.field_147002_h
/* 58 */       .func_75139_a(1).func_75211_c().func_190926_b() && this.field_147002_h
/* 59 */       .func_75139_a(2).func_75211_c().func_190926_b() && this.field_147002_h
/* 60 */       .func_75139_a(3).func_75211_c().func_190926_b() && this.field_147002_h
/* 61 */       .func_75139_a(4).func_75211_c().func_190926_b())
/*    */     {
/* 63 */       func_73729_b(var5 + 67, var6 + 24, 176, 0, 25, 25);
/*    */     }
/*    */     
/* 66 */     GL11.glDisable(3042); }
/*    */   
/*    */   protected void func_146979_b(int par1, int par2) {}
/*    */   
/*    */   protected void func_73864_a(int mx, int my, int par3) throws IOException {
/* 71 */     super.func_73864_a(mx, my, par3);
/*    */     
/* 73 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 74 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*    */ 
/*    */     
/* 77 */     int var7 = mx - gx + 67;
/* 78 */     int var8 = my - gy + 24;
/*    */     
/* 80 */     if (var7 >= 0 && var8 >= 0 && var7 < 25 && var8 < 25 && this.pech
/* 81 */       .isValued(this.field_147002_h.func_75139_a(0).func_75211_c()) && 
/* 82 */       !this.field_147002_h.func_75139_a(0).func_75211_c().func_190926_b() && this.field_147002_h
/* 83 */       .func_75139_a(1).func_75211_c().func_190926_b() && this.field_147002_h
/* 84 */       .func_75139_a(2).func_75211_c().func_190926_b() && this.field_147002_h
/* 85 */       .func_75139_a(3).func_75211_c().func_190926_b() && this.field_147002_h
/* 86 */       .func_75139_a(4).func_75211_c().func_190926_b()) {
/*    */ 
/*    */       
/* 89 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 0);
/*    */       
/* 91 */       playButton();
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 97 */   private void playButton() { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.pech_dice, 0.5F, 0.95F + (this.field_146297_k.func_175606_aa()).field_70170_p.field_73012_v.nextFloat() * 0.1F); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiPech.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */