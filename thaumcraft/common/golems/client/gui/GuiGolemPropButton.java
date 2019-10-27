/*    */ package thaumcraft.common.golems.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*    */ 
/*    */ public class GuiGolemPropButton
/*    */   extends GuiButton {
/*    */   ISealConfigToggles.SealToggle prop;
/*    */   
/*    */   public GuiGolemPropButton(int buttonId, int x, int y, int width, int height, String buttonText, ISealConfigToggles.SealToggle prop) {
/* 16 */     super(buttonId, x, y, width, height, buttonText);
/* 17 */     this.prop = prop;
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
/* 34 */       GlStateManager.func_179147_l();
/* 35 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 36 */       GlStateManager.func_179112_b(770, 771);
/*    */       
/* 38 */       func_73729_b(this.field_146128_h - 2, this.field_146129_i - 2, 2, 18, 12, 12);
/*    */       
/* 40 */       if (this.prop.getValue()) {
/* 41 */         func_73729_b(this.field_146128_h - 2, this.field_146129_i - 2, 18, 18, 12, 12);
/*    */       }
/*    */       
/* 44 */       func_73731_b(fontrenderer, I18n.func_74838_a(this.field_146126_j), this.field_146128_h + 12, this.field_146129_i, 16777215);
/*    */       
/* 46 */       func_146119_b(mc, xx, yy);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\gui\GuiGolemPropButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */