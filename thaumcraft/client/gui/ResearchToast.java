/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.gui.toasts.GuiToast;
/*    */ import net.minecraft.client.gui.toasts.IToast;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import thaumcraft.api.research.ResearchEntry;
/*    */ 
/*    */ public class ResearchToast
/*    */   implements IToast
/*    */ {
/*    */   ResearchEntry entry;
/*    */   private long firstDrawTime;
/*    */   private boolean newDisplay;
/*    */   ResourceLocation tex;
/*    */   
/*    */   public ResearchToast(ResearchEntry entry) {
/* 19 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/hud.png");
/*    */     this.entry = entry;
/*    */   }
/*    */   
/*    */   public IToast.Visibility func_193653_a(GuiToast toastGui, long delta) {
/* 24 */     if (this.newDisplay) {
/*    */       
/* 26 */       this.firstDrawTime = delta;
/* 27 */       this.newDisplay = false;
/*    */     } 
/*    */     
/* 30 */     toastGui.func_192989_b().func_110434_K().func_110577_a(this.tex);
/* 31 */     GlStateManager.func_179124_c(1.0F, 1.0F, 1.0F);
/* 32 */     toastGui.func_73729_b(0, 0, 0, 224, 160, 32);
/*    */     
/* 34 */     GuiResearchBrowser.drawResearchIcon(this.entry, 6, 8, 0.0F, false);
/*    */     
/* 36 */     (toastGui.func_192989_b()).field_71466_p.func_78276_b(I18n.func_74838_a("research.complete"), 30, 7, 10631665);
/* 37 */     String s = this.entry.getLocalizedName();
/* 38 */     float w = (toastGui.func_192989_b()).field_71466_p.func_78256_a(s);
/* 39 */     if (w > 124.0F) {
/* 40 */       w = 124.0F / w;
/* 41 */       GlStateManager.func_179094_E();
/* 42 */       GlStateManager.func_179109_b(30.0F, 18.0F, 0.0F);
/* 43 */       GlStateManager.func_179152_a(w, w, w);
/* 44 */       (toastGui.func_192989_b()).field_71466_p.func_78276_b(s, 0, 0, 16755465);
/* 45 */       GlStateManager.func_179121_F();
/*    */     } else {
/* 47 */       (toastGui.func_192989_b()).field_71466_p.func_78276_b(s, 30, 18, 16755465);
/*    */     } 
/* 49 */     return (delta - this.firstDrawTime < 5000L) ? IToast.Visibility.SHOW : IToast.Visibility.HIDE;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\ResearchToast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */