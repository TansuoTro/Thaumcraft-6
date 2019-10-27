/*    */ package thaumcraft.common.golems.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiGolemCraftButton
/*    */   extends GuiButton
/*    */ {
/* 14 */   public GuiGolemCraftButton(int buttonId, int x, int y) { super(buttonId, x, y, 24, 16, ""); }
/*    */ 
/*    */   
/* 17 */   static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_golembuilder.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_191745_a(Minecraft mc, int xx, int yy, float partialTicks) {
/* 22 */     if (this.field_146125_m) {
/*    */       
/* 24 */       FontRenderer fontrenderer = mc.field_71466_p;
/* 25 */       mc.func_110434_K().func_110577_a(tex);
/* 26 */       GlStateManager.func_179131_c(0.9F, 0.9F, 0.9F, 0.9F);
/* 27 */       this.field_146123_n = (xx >= this.field_146128_h && yy >= this.field_146129_i && xx < this.field_146128_h + this.field_146120_f && yy < this.field_146129_i + this.field_146121_g);
/* 28 */       int k = func_146114_a(this.field_146123_n);
/*    */       
/* 30 */       GlStateManager.func_179147_l();
/* 31 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 32 */       GlStateManager.func_179112_b(770, 771);
/*    */       
/* 34 */       if (this.field_146124_l && k == 2) {
/* 35 */         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*    */       }
/*    */       
/* 38 */       func_73729_b(this.field_146128_h, this.field_146129_i, 216, 64, 24, 16);
/*    */       
/* 40 */       if (!this.field_146124_l) {
/* 41 */         func_73729_b(this.field_146128_h, this.field_146129_i, 216, 40, 24, 16);
/*    */       }
/*    */       
/* 44 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*    */       
/* 46 */       func_146119_b(mc, xx, yy);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\gui\GuiGolemCraftButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */