/*    */ package thaumcraft.common.golems.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import thaumcraft.api.golems.seals.ISealEntity;
/*    */ 
/*    */ public class GuiGolemRedstoneButton
/*    */   extends GuiButton {
/*    */   ISealEntity seal;
/*    */   
/*    */   public GuiGolemRedstoneButton(int buttonId, int x, int y, int width, int height, ISealEntity seal) {
/* 16 */     super(buttonId, x, y, width, height, "");
/* 17 */     this.seal = seal;
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
/* 44 */       if (this.seal.isRedstoneSensitive()) {
/* 45 */         func_73729_b(this.field_146128_h, this.field_146129_i, 64, 136, 16, 16);
/*    */       } else {
/* 47 */         func_73729_b(this.field_146128_h, this.field_146129_i, 80, 136, 16, 16);
/*    */       } 
/*    */       
/* 50 */       if (k == 2) {
/* 51 */         this.field_73735_i += 90.0F;
/* 52 */         String s = this.seal.isRedstoneSensitive() ? I18n.func_74838_a("golem.prop.redon") : I18n.func_74838_a("golem.prop.redoff");
/* 53 */         func_73731_b(fontrenderer, s, this.field_146128_h - 2 - fontrenderer.func_78256_a(s), this.field_146129_i + 4, 16777215);
/* 54 */         this.field_73735_i -= 90.0F;
/*    */       } 
/* 56 */       func_146119_b(mc, xx, yy);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\gui\GuiGolemRedstoneButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */