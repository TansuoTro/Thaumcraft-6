/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.container.ContainerSpa;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.tiles.devices.TileSpa;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiSpa
/*     */   extends GuiContainer
/*     */ {
/*     */   private TileSpa spa;
/*     */   private float xSize_lo;
/*     */   private float ySize_lo;
/*     */   ResourceLocation tex;
/*     */   
/*     */   public GuiSpa(InventoryPlayer par1InventoryPlayer, TileSpa teSpa) {
/*  34 */     super(new ContainerSpa(par1InventoryPlayer, teSpa));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_spa.png");
/*     */     this.spa = teSpa;
/*     */   }
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3) {
/*  96 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  97 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*  98 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/*  99 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/* 101 */     GL11.glEnable(3042);
/* 102 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 104 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/* 106 */     if (this.spa.getMix()) {
/* 107 */       func_73729_b(k + 89, l + 35, 208, 16, 8, 8);
/*     */     } else {
/* 109 */       func_73729_b(k + 89, l + 35, 208, 32, 8, 8);
/*     */     } 
/*     */ 
/*     */     
/* 113 */     if (this.spa.tank.getFluidAmount() > 0) {
/* 114 */       FluidStack fluid = this.spa.tank.getFluid();
/* 115 */       if (fluid != null) {
/* 116 */         TextureAtlasSprite icon = func_175371_a(fluid.getFluid().getBlock());
/* 117 */         if (icon != null) {
/* 118 */           float bar = this.spa.tank.getFluidAmount() / this.spa.tank.getCapacity();
/* 119 */           renderFluid(icon, fluid.getFluid());
/* 120 */           this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 121 */           func_73729_b(k + 107, l + 15, 107, 15, 10, (int)(48.0F - 48.0F * bar));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     func_73729_b(k + 106, l + 11, 232, 0, 10, 55);
/*     */     
/* 128 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 133 */   private TextureAtlasSprite func_175371_a(Block p_175371_1_) { return Minecraft.func_71410_x().func_175602_ab().func_175023_a().func_178122_a(p_175371_1_.func_176223_P()); }
/*     */   public void func_73863_a(int par1, int par2, float par3) { func_146276_q_(); super.func_73863_a(par1, par2, par3); this.xSize_lo = par1; this.ySize_lo = par2; int baseX = this.field_147003_i; int baseY = this.field_147009_r; int mposx = par1 - baseX + 104; int mposy = par2 - baseY + 10; if (mposx >= 0 && mposy >= 0 && mposx < 10 && mposy < 55) { List list = new ArrayList(); FluidStack fluid = this.spa.tank.getFluid(); if (fluid != null) { list.add(fluid.getFluid().getLocalizedName(fluid)); list.add(fluid.amount + " mb"); drawHoveringText(list, par1, par2, this.field_146289_q); }  }  mposx = par1 - baseX + 88; mposy = par2 - baseY + 34; if (mposx >= 0 && mposy >= 0 && mposx < 10 && mposy < 10) { List list = new ArrayList(); if (this.spa.getMix()) { list.add(I18n.func_74838_a("text.spa.mix.true")); }
/*     */       else { list.add(I18n.func_74838_a("text.spa.mix.false")); }
/*     */        drawHoveringText(list, par1, par2, this.field_146289_q); }
/* 137 */      func_191948_b(par2, par2); } public void renderFluid(TextureAtlasSprite icon, Fluid fluid) { GL11.glPushMatrix();
/* 138 */     this.field_146297_k.field_71446_o.func_110577_a(TextureMap.field_110575_b);
/* 139 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 140 */     Color cc = new Color(fluid.getColor());
/* 141 */     GL11.glColor3f(cc.getRed() / 255.0F, cc.getGreen() / 255.0F, cc.getBlue() / 255.0F);
/* 142 */     for (int a = 0; a < 6; a++) {
/* 143 */       func_175175_a((this.field_147003_i + 107) * 2, (this.field_147009_r + 15) * 2 + a * 16, icon, 16, 16);
/*     */     }
/* 145 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 146 */     GL11.glPopMatrix(); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_73864_a(int mx, int my, int par3) throws IOException {
/* 151 */     super.func_73864_a(mx, my, par3);
/*     */     
/* 153 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 154 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */ 
/*     */     
/* 157 */     int var7 = mx - gx + 89;
/* 158 */     int var8 = my - gy + 35;
/*     */     
/* 160 */     if (var7 >= 0 && var8 >= 0 && var7 < 8 && var8 < 8) {
/*     */ 
/*     */       
/* 163 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
/* 164 */       playButtonClick();
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   private void playButtonClick() { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.clack, 0.4F, 1.0F); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiSpa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */