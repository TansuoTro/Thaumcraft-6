/*     */ package thaumcraft.client.gui.plugins;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ public class GuiSliderTC
/*     */   extends GuiButton {
/*  12 */   private float sliderPosition = 1.0F;
/*     */   
/*     */   public boolean isMouseDown;
/*     */   private final String name;
/*     */   private final float min;
/*     */   private float max;
/*     */   private final boolean vertical;
/*  19 */   static ResourceLocation tex = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png");
/*     */ 
/*     */   
/*     */   public GuiSliderTC(int idIn, int x, int y, int w, int h, String name, float min, float max, float defaultValue, boolean vertical) {
/*  23 */     super(idIn, x, y, w, h, "");
/*  24 */     this.name = name;
/*  25 */     this.min = min;
/*  26 */     this.max = max;
/*  27 */     this.sliderPosition = (defaultValue - min) / (max - min);
/*  28 */     this.vertical = vertical;
/*     */   }
/*     */ 
/*     */   
/*  32 */   public float getMax() { return this.max; }
/*     */ 
/*     */ 
/*     */   
/*  36 */   public float getMin() { return this.min; }
/*     */ 
/*     */   
/*     */   public void setMax(float max) {
/*  40 */     this.max = max;
/*  41 */     this.sliderPosition = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  46 */   public float getSliderValue() { return this.min + (this.max - this.min) * this.sliderPosition; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public void setSliderValue(float p_175218_1_, boolean p_175218_2_) { this.sliderPosition = (p_175218_1_ - this.min) / (this.max - this.min); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public float getSliderPosition() { return this.sliderPosition; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected int func_146114_a(boolean mouseOver) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146119_b(Minecraft mc, int mouseX, int mouseY) {
/*  75 */     if (this.field_146125_m) {
/*     */       
/*  77 */       if (this.isMouseDown) {
/*     */         
/*  79 */         if (this.vertical) {
/*  80 */           this.sliderPosition = (mouseY - this.field_146129_i + 4) / (this.field_146121_g - 8);
/*     */         } else {
/*  82 */           this.sliderPosition = (mouseX - this.field_146128_h + 4) / (this.field_146120_f - 8);
/*     */         } 
/*     */         
/*  85 */         if (this.sliderPosition < 0.0F)
/*     */         {
/*  87 */           this.sliderPosition = 0.0F;
/*     */         }
/*     */         
/*  90 */         if (this.sliderPosition > 1.0F)
/*     */         {
/*  92 */           this.sliderPosition = 1.0F;
/*     */         }
/*     */       } 
/*     */       
/*  96 */       mc.func_110434_K().func_110577_a(tex);
/*  97 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/*  99 */       if (this.vertical) {
/* 100 */         func_73729_b(this.field_146128_h, this.field_146129_i + (int)(this.sliderPosition * (this.field_146121_g - 8)), 20, 20, 8, 8);
/*     */       } else {
/* 102 */         func_73729_b(this.field_146128_h + (int)(this.sliderPosition * (this.field_146120_f - 8)), this.field_146129_i, 20, 20, 8, 8);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void setSliderPosition(float p_175219_1_) { this.sliderPosition = p_175219_1_; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
/* 119 */     if (super.func_146116_c(mc, mouseX, mouseY)) {
/*     */       
/* 121 */       if (this.vertical) {
/* 122 */         this.sliderPosition = (mouseY - this.field_146129_i + 4) / (this.field_146121_g - 8);
/*     */       } else {
/* 124 */         this.sliderPosition = (mouseX - this.field_146128_h + 4) / (this.field_146120_f - 8);
/*     */       } 
/* 126 */       if (this.sliderPosition < 0.0F)
/*     */       {
/* 128 */         this.sliderPosition = 0.0F;
/*     */       }
/*     */       
/* 131 */       if (this.sliderPosition > 1.0F)
/*     */       {
/* 133 */         this.sliderPosition = 1.0F;
/*     */       }
/* 135 */       this.isMouseDown = true;
/* 136 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public void func_146118_a(int mouseX, int mouseY) { this.isMouseDown = false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float pt) {
/* 163 */     if (this.field_146125_m) {
/*     */       
/* 165 */       mc.func_110434_K().func_110577_a(tex);
/* 166 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 167 */       this.field_146123_n = (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
/* 168 */       int i = func_146114_a(this.field_146123_n);
/* 169 */       GlStateManager.func_179147_l();
/* 170 */       GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/* 171 */       GlStateManager.func_187401_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */       
/* 173 */       GlStateManager.func_179094_E();
/* 174 */       if (this.vertical) {
/* 175 */         GlStateManager.func_179109_b((this.field_146128_h + 2), this.field_146129_i, 0.0F);
/* 176 */         GlStateManager.func_179152_a(1.0F, this.field_146121_g / 32.0F, 1.0F);
/* 177 */         func_73729_b(0, 0, 240, 176, 4, 32);
/*     */       } else {
/* 179 */         GlStateManager.func_179109_b(this.field_146128_h, (this.field_146129_i + 2), 0.0F);
/* 180 */         GlStateManager.func_179152_a(this.field_146120_f / 32.0F, 1.0F, 1.0F);
/* 181 */         func_73729_b(0, 0, 208, 176, 32, 4);
/*     */       } 
/* 183 */       GlStateManager.func_179121_F();
/*     */       
/* 185 */       func_146119_b(mc, mouseX, mouseY);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static interface FormatHelper {
/*     */     String getText(int param1Int, String param1String, float param1Float);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\plugins\GuiSliderTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */