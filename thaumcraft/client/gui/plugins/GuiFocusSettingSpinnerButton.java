/*    */ package thaumcraft.client.gui.plugins;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.casters.NodeSetting;
/*    */ 
/*    */ 
/*    */ public class GuiFocusSettingSpinnerButton
/*    */   extends GuiButton
/*    */ {
/*    */   private NodeSetting setting;
/*    */   
/*    */   public GuiFocusSettingSpinnerButton(int buttonId, int x, int y, int width, NodeSetting ns) {
/* 17 */     super(buttonId, x, y, width, 10, "");
/* 18 */     this.setting = ns;
/*    */   }
/*    */   
/* 21 */   static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_191745_a(Minecraft mc, int xx, int yy, float pt) {
/* 26 */     if (this.field_146125_m) {
/*    */       
/* 28 */       FontRenderer fontrenderer = mc.field_71466_p;
/* 29 */       mc.func_110434_K().func_110577_a(tex);
/* 30 */       GlStateManager.func_179131_c(0.9F, 0.9F, 0.9F, 0.9F);
/*    */       
/* 32 */       this.field_146123_n = (xx >= this.field_146128_h && yy >= this.field_146129_i && xx < this.field_146128_h + this.field_146120_f + 10 && yy < this.field_146129_i + this.field_146121_g);
/* 33 */       int k = func_146114_a(this.field_146123_n);
/* 34 */       if (k == 2) GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); 
/* 35 */       GlStateManager.func_179147_l();
/* 36 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 37 */       GlStateManager.func_179112_b(770, 771);
/*    */       
/* 39 */       func_73729_b(this.field_146128_h, this.field_146129_i, 20, 0, 10, 10);
/*    */       
/* 41 */       func_73729_b(this.field_146128_h + this.field_146120_f, this.field_146129_i, 30, 0, 10, 10);
/*    */       
/* 43 */       String s = this.setting.getValueText();
/* 44 */       fontrenderer.func_175063_a(s, (this.field_146128_h + (this.field_146120_f + 10) / 2 - fontrenderer.func_78256_a(s) / 2), (this.field_146129_i + 1), 16777215);
/*    */       
/* 46 */       func_146119_b(mc, xx, yy);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
/* 53 */     if (this.field_146124_l && this.field_146125_m) {
/*    */       
/* 55 */       if (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + 10 && mouseY < this.field_146129_i + this.field_146121_g) {
/* 56 */         this.setting.decrement();
/* 57 */         return true;
/*    */       } 
/*    */ 
/*    */       
/* 61 */       if (mouseX >= this.field_146128_h + this.field_146120_f && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f + 10 && mouseY < this.field_146129_i + this.field_146121_g) {
/* 62 */         this.setting.increment();
/* 63 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 67 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\plugins\GuiFocusSettingSpinnerButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */