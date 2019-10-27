/*     */ package thaumcraft.client.gui.plugins;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiHoverButton extends GuiButton {
/*     */   String description;
/*     */   GuiScreen screen;
/*     */   int color;
/*     */   Object tex;
/*     */   
/*  29 */   public GuiHoverButton(GuiScreen screen, int buttonId, int x, int y, int width, int height, String buttonText, String description, Object tex) { super(buttonId, x, y, width, height, buttonText);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     this.tex = null; this.description = description; this.tex = tex; this.screen = screen; this.color = 16777215; } public GuiHoverButton(GuiScreen screen, int buttonId, int x, int y, int width, int height, String buttonText, String description, Object tex, int color) { super(buttonId, x, y, width, height, buttonText); this.tex = null;
/*     */     this.description = description;
/*     */     this.tex = tex;
/*     */     this.screen = screen;
/*     */     this.color = color; } public void func_191745_a(Minecraft mc, int xx, int yy, float pt) {
/*  52 */     if (this.field_146125_m) {
/*     */       
/*  54 */       FontRenderer fontrenderer = mc.field_71466_p;
/*  55 */       Color c = new Color(this.color);
/*  56 */       GlStateManager.func_179131_c(0.9F * c.getRed() / 255.0F, 0.9F * c.getGreen() / 255.0F, 0.9F * c.getBlue() / 255.0F, 0.9F);
/*  57 */       this.field_146123_n = (xx >= this.field_146128_h - this.field_146120_f / 2 && yy >= this.field_146129_i - this.field_146121_g / 2 && xx < this.field_146128_h - this.field_146120_f / 2 + this.field_146120_f && yy < this.field_146129_i - this.field_146121_g / 2 + this.field_146121_g);
/*  58 */       int k = func_146114_a(this.field_146123_n);
/*     */       
/*  60 */       GlStateManager.func_179147_l();
/*  61 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/*  62 */       GlStateManager.func_179112_b(770, 771);
/*     */       
/*  64 */       if (k == 2) {
/*  65 */         GlStateManager.func_179131_c(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/*     */       }
/*     */       
/*  68 */       if (this.tex instanceof Aspect) {
/*  69 */         mc.func_110434_K().func_110577_a(((Aspect)this.tex).getImage());
/*  70 */         Color c2 = new Color(((Aspect)this.tex).getColor());
/*  71 */         if (k != 2) {
/*  72 */           GlStateManager.func_179131_c(c2.getRed() / 290.0F, c2.getGreen() / 290.0F, c2.getBlue() / 290.0F, 0.9F);
/*     */         } else {
/*  74 */           GlStateManager.func_179131_c(c2.getRed() / 255.0F, c2.getGreen() / 255.0F, c2.getBlue() / 255.0F, 1.0F);
/*  75 */         }  func_146110_a(this.field_146128_h - this.field_146120_f / 2, this.field_146129_i - this.field_146121_g / 2, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*     */       } 
/*     */       
/*  78 */       if (this.tex instanceof ResourceLocation) {
/*  79 */         mc.func_110434_K().func_110577_a((ResourceLocation)this.tex);
/*  80 */         func_146110_a(this.field_146128_h - this.field_146120_f / 2, this.field_146129_i - this.field_146121_g / 2, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*     */       } 
/*     */       
/*  83 */       if (this.tex instanceof TextureAtlasSprite) {
/*  84 */         func_175175_a(this.field_146128_h - this.field_146120_f / 2, this.field_146129_i - this.field_146121_g / 2, (TextureAtlasSprite)this.tex, 16, 16);
/*     */       }
/*     */       
/*  87 */       if (this.tex instanceof ItemStack) {
/*  88 */         this.field_73735_i -= 90.0F;
/*  89 */         UtilsFX.renderItemStackShaded(mc, (ItemStack)this.tex, this.field_146128_h - this.field_146120_f / 2, this.field_146129_i - this.field_146121_g / 2 - ((k == 2) ? 1 : 0), null, 1.0F);
/*  90 */         this.field_73735_i += 90.0F;
/*     */       } 
/*     */       
/*  93 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*  94 */       func_146119_b(mc, xx, yy);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_146111_b(int xx, int yy) {
/* 101 */     FontRenderer fontrenderer = (Minecraft.func_71410_x()).field_71466_p;
/* 102 */     this.field_73735_i += 90.0F;
/* 103 */     List<String> text = new ArrayList<String>();
/* 104 */     if (this.tex instanceof ItemStack) {
/* 105 */       text = ((ItemStack)this.tex).func_82840_a((Minecraft.func_71410_x()).field_71439_g, (Minecraft.func_71410_x()).field_71474_y.field_82882_x ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL);
/* 106 */       int qq = 0;
/* 107 */       for (String s : text) {
/* 108 */         if (s.endsWith(" " + TextFormatting.RESET)) {
/* 109 */           text = text.subList(0, qq);
/*     */           break;
/*     */         } 
/* 112 */         qq++;
/*     */       } 
/*     */     } else {
/* 115 */       text.add(this.field_146126_j);
/*     */     } 
/* 117 */     int m = 8;
/* 118 */     if (this.description != null) {
/* 119 */       m = 0;
/* 120 */       text.add("§o§9" + this.description);
/*     */     } 
/*     */     
/* 123 */     UtilsFX.drawCustomTooltip(this.screen, fontrenderer, text, xx + 4, yy + m, -99);
/* 124 */     this.field_73735_i -= 90.0F;
/* 125 */     RenderHelper.func_74518_a();
/* 126 */     GlStateManager.func_179140_f();
/* 127 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\plugins\GuiHoverButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */