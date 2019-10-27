/*    */ package thaumcraft.common.golems.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import thaumcraft.api.golems.seals.ISealConfigFilter;
/*    */ 
/*    */ public class GuiGolemBWListButton
/*    */   extends GuiButton {
/*    */   ISealConfigFilter filter;
/*    */   
/*    */   public GuiGolemBWListButton(int buttonId, int x, int y, int width, int height, ISealConfigFilter filter) {
/* 16 */     super(buttonId, x, y, width, height, "");
/* 17 */     this.filter = filter;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 22 */   static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_191745_a(Minecraft mc, int xx, int yy, float partialTicks) {
/* 27 */     if (this.field_146125_m) {
/*    */       
/* 29 */       FontRenderer fontrenderer = mc.field_71466_p;
/* 30 */       mc.func_110434_K().func_110577_a(tex);
/* 31 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 32 */       this.field_146123_n = (xx >= this.field_146128_h && yy >= this.field_146129_i && xx < this.field_146128_h + this.field_146120_f && yy < this.field_146129_i + this.field_146121_g);
/*    */       
/* 34 */       int k = func_146114_a(this.field_146123_n);
/* 35 */       if (k == 2) {
/* 36 */         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*    */       } else {
/* 38 */         GlStateManager.func_179131_c(0.9F, 0.9F, 0.9F, 0.9F);
/*    */       } 
/* 40 */       GlStateManager.func_179147_l();
/* 41 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 42 */       GlStateManager.func_179112_b(770, 771);
/*    */       
/* 44 */       if (this.filter.isBlacklist()) {
/* 45 */         func_73729_b(this.field_146128_h, this.field_146129_i, 0, 136, 16, 16);
/*    */       } else {
/* 47 */         func_73729_b(this.field_146128_h, this.field_146129_i, 16, 136, 16, 16);
/*    */       } 
/*    */ 
/*    */       
/* 51 */       if (k == 2) {
/* 52 */         func_73732_a(fontrenderer, I18n.func_74838_a(this.filter.isBlacklist() ? "button.bl" : "button.wl"), this.field_146128_h + 8, this.field_146129_i + 17, 16777215);
/*    */       }
/*    */       
/* 55 */       func_146119_b(mc, xx, yy);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\gui\GuiGolemBWListButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */