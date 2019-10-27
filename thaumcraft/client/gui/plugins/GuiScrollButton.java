/*    */ package thaumcraft.client.gui.plugins;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class GuiScrollButton extends GuiButton {
/*    */   boolean minus;
/*    */   boolean vertical;
/*    */   
/*    */   public GuiScrollButton(int buttonId, int x, int y, int width, int height, boolean minus, boolean vertical) {
/* 14 */     super(buttonId, x, y, width, height, "");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 24 */     this.minus = false;
/* 25 */     this.vertical = false; this.minus = minus; this.vertical = vertical; } public GuiScrollButton(int buttonId, int x, int y, int width, int height, boolean minus) { super(buttonId, x, y, width, height, ""); this.minus = false; this.vertical = false;
/*    */     this.minus = minus; }
/* 27 */    static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_191745_a(Minecraft mc, int xx, int yy, float pt) {
/* 32 */     if (this.field_146125_m) {
/*    */       
/* 34 */       FontRenderer fontrenderer = mc.field_71466_p;
/* 35 */       mc.func_110434_K().func_110577_a(tex);
/* 36 */       GlStateManager.func_179131_c(0.9F, 0.9F, 0.9F, 0.9F);
/*    */       
/* 38 */       this.field_146123_n = (xx >= this.field_146128_h && yy >= this.field_146129_i && xx < this.field_146128_h + this.field_146120_f && yy < this.field_146129_i + this.field_146121_g);
/* 39 */       int k = func_146114_a(this.field_146123_n);
/* 40 */       if (k == 2) GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); 
/* 41 */       GlStateManager.func_179147_l();
/* 42 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 43 */       GlStateManager.func_179112_b(770, 771);
/*    */       
/* 45 */       func_73729_b(this.field_146128_h, this.field_146129_i, this.vertical ? 67 : (this.minus ? 20 : 30), this.vertical ? (this.minus ? 0 : 10) : 0, 10, 10);
/*    */ 
/*    */ 
/*    */       
/* 49 */       func_146119_b(mc, xx, yy);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\plugins\GuiScrollButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */