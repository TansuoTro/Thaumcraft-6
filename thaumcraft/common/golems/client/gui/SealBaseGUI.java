/*     */ package thaumcraft.common.golems.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.block.model.ModelManager;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.seals.ISealConfigFilter;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.ISealGui;
/*     */ import thaumcraft.client.gui.plugins.GuiHoverButton;
/*     */ import thaumcraft.client.gui.plugins.GuiPlusMinusButton;
/*     */ import thaumcraft.client.lib.CustomRenderItem;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class SealBaseGUI
/*     */   extends GuiContainer
/*     */ {
/*     */   ISealEntity seal;
/*     */   int middleX;
/*     */   int middleY;
/*  44 */   int category = -1;
/*     */   int[] categories;
/*     */   ResourceLocation tex;
/*     */   
/*     */   public SealBaseGUI(InventoryPlayer player, World world, ISealEntity seal) {
/*  49 */     super(new SealBaseContainer(player, world, seal));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png"); this.seal = seal; this.field_146999_f = 176; this.field_147000_g = 232; this.middleX = this.field_146999_f / 2; this.middleY = (this.field_147000_g - 72) / 2 - 8; if (seal.getSeal() instanceof ISealGui) {
/*     */       this.categories = ((ISealGui)seal.getSeal()).getGuiCategories();
/*     */     } else {
/*     */       this.categories = new int[] { 0, 4 };
/*     */     } 
/* 176 */   } private ModelManager getModelmanager() { return (Minecraft.func_71410_x()).field_175617_aL; } public void func_73866_w_() { super.func_73866_w_(); this.field_146296_j = new CustomRenderItem(); setupCategories(); } protected void func_146976_a(float par1, int mouseX, int mouseY) { int my, mx; GL11.glEnable(3042);
/* 177 */     GL11.glBlendFunc(770, 771);
/* 178 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 179 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 180 */     func_73729_b(this.field_147003_i + this.middleX - 80, this.field_147009_r + this.middleY - 80, 96, 0, 160, 160);
/* 181 */     func_73729_b(this.field_147003_i, this.field_147009_r + 143, 0, 167, 176, 89);
/*     */     
/* 183 */     func_73732_a(this.field_146289_q, I18n.func_74838_a("button.category." + this.category), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 64, 16777215);
/*     */ 
/*     */     
/* 186 */     GL11.glEnable(3042);
/* 187 */     GL11.glBlendFunc(770, 771);
/* 188 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 189 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 191 */     switch (this.category) {
/*     */       case 0:
/* 193 */         func_73729_b(this.field_147003_i + this.middleX + 17, this.field_147009_r + this.middleY + 3, 2, 18, 12, 12);
/* 194 */         if (this.seal.getColor() >= 1 && this.seal.getColor() <= 16) {
/* 195 */           Color c = new Color(EnumDyeColor.func_176764_b(this.seal.getColor() - 1).func_193350_e());
/* 196 */           GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/* 197 */           func_73729_b(this.field_147003_i + this.middleX + 20, this.field_147009_r + this.middleY + 6, 74, 31, 6, 6);
/* 198 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         } 
/*     */         
/* 201 */         mx = mouseX - this.field_147003_i;
/* 202 */         my = mouseY - this.field_147009_r;
/*     */         
/* 204 */         if (mx >= this.middleX + 5 && mx <= this.middleX + 41 && my >= this.middleY + 3 && my <= this.middleY + 15) {
/* 205 */           if (this.seal.getColor() >= 1 && this.seal.getColor() <= 16) {
/* 206 */             String s = "color." + EnumDyeColor.func_176764_b(this.seal.getColor() - 1).func_176610_l();
/* 207 */             String s2 = I18n.func_74838_a("golem.prop.color");
/* 208 */             s2 = s2.replace("%s", I18n.func_74838_a(s));
/* 209 */             func_73732_a(this.field_146289_q, s2, this.field_147003_i + this.middleX + 23, this.field_147009_r + this.middleY + 17, 16777215);
/*     */           } else {
/* 211 */             func_73732_a(this.field_146289_q, I18n.func_74838_a("golem.prop.colorall"), this.field_147003_i + this.middleX + 23, this.field_147009_r + this.middleY + 17, 16777215);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 216 */         func_73732_a(this.field_146289_q, 
/* 217 */             I18n.func_74838_a("golem.prop.priority"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 28, 12299007);
/* 218 */         func_73732_a(this.field_146289_q, "" + this.seal.getPriority(), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 16, 16777215);
/*     */ 
/*     */         
/* 221 */         if (this.seal.getOwner().equals(this.field_146297_k.field_71439_g.func_110124_au().toString()))
/* 222 */           func_73732_a(this.field_146289_q, 
/* 223 */               I18n.func_74838_a("golem.prop.owner"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY + 32, 12299007); 
/*     */         break;
/*     */       case 1:
/* 226 */         if (this.seal.getSeal() instanceof ISealConfigFilter) {
/* 227 */           int s = ((ISealConfigFilter)this.seal.getSeal()).getFilterSize();
/* 228 */           int sx = 16 + (s - 1) % 3 * 12;
/* 229 */           int sy = 16 + (s - 1) / 3 * 12;
/* 230 */           for (int a = 0; a < s; a++) {
/* 231 */             int x = a % 3;
/* 232 */             int y = a / 3;
/* 233 */             func_73729_b(this.field_147003_i + this.middleX + x * 24 - sx, this.field_147009_r + this.middleY + y * 24 - sy, 0, 56, 32, 32);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 238 */         func_73732_a(this.field_146289_q, I18n.func_74838_a("button.caption.y"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 24 - 9, 14540253);
/* 239 */         func_73732_a(this.field_146289_q, I18n.func_74838_a("button.caption.x"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 9, 14540253);
/* 240 */         func_73732_a(this.field_146289_q, I18n.func_74838_a("button.caption.z"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY + 24 - 9, 14540253);
/*     */         
/* 242 */         func_73732_a(this.field_146289_q, "" + this.seal.getArea().func_177956_o(), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 24, 16777215);
/* 243 */         func_73732_a(this.field_146289_q, "" + this.seal.getArea().func_177958_n(), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY, 16777215);
/* 244 */         func_73732_a(this.field_146289_q, "" + this.seal.getArea().func_177952_p(), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY + 24, 16777215);
/*     */         break;
/*     */       case 4:
/* 247 */         func_73732_a(this.field_146289_q, I18n.func_74838_a("button.caption.required"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY - 26, 14540253);
/* 248 */         func_73732_a(this.field_146289_q, I18n.func_74838_a("button.caption.forbidden"), this.field_147003_i + this.middleX, this.field_147009_r + this.middleY + 6, 14540253);
/*     */         break;
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146979_b(int mouseX, int mouseY) {
/* 258 */     RenderHelper.func_74518_a();
/* 259 */     Iterator iterator = this.field_146292_n.iterator();
/* 260 */     while (iterator.hasNext()) {
/*     */       
/* 262 */       GuiButton guibutton = (GuiButton)iterator.next();
/* 263 */       if (guibutton.func_146115_a()) {
/*     */         
/* 265 */         guibutton.func_146111_b(mouseX - this.field_147003_i, mouseY - this.field_147009_r);
/*     */         break;
/*     */       } 
/*     */     } 
/* 269 */     RenderHelper.func_74520_c();
/*     */   } private void setupCategories() { EnumGolemTrait[] tags; this.field_146292_n.clear(); int c = 0; float slice = 60.0F / this.categories.length; float start = -180.0F + (this.categories.length - 1) * slice / 2.0F; if (slice > 24.0F)
/*     */       slice = 24.0F;  if (slice < 12.0F)
/*     */       slice = 12.0F;  for (int cat : this.categories) { if (this.category < 0)
/*     */         this.category = cat;  if (this.categories.length > 1) { int xx = (int)(MathHelper.func_76134_b((start - c * slice) / 180.0F * 3.1415927F) * 86.0F); int yy = (int)(MathHelper.func_76126_a((start - c * slice) / 180.0F * 3.1415927F) * 86.0F); this.field_146292_n.add(new GuiGolemCategoryButton(c, this.field_147003_i + this.middleX + xx, this.field_147009_r + this.middleY + yy, 16, 16, "button.category." + cat, cat, (this.category == cat))); }  c++; }  int xx = (int)(MathHelper.func_76134_b((start - c * slice) / 180.0F * 3.1415927F) * 86.0F); int yy = (int)(MathHelper.func_76126_a((start - c * slice) / 180.0F * 3.1415927F) * 86.0F); this.field_146292_n.add(new GuiGolemRedstoneButton(27, this.field_147003_i + this.middleX + xx - 8, this.field_147009_r + this.middleY + yy - 8, 16, 16, this.seal)); switch (this.category) { case 0: this.field_146292_n.add(new GuiPlusMinusButton(80, this.field_147003_i + this.middleX - 5 - 14, this.field_147009_r + this.middleY - 17, 10, 10, true)); this.field_146292_n.add(new GuiPlusMinusButton(81, this.field_147003_i + this.middleX - 5 + 14, this.field_147009_r + this.middleY - 17, 10, 10, false)); this.field_146292_n.add(new GuiPlusMinusButton(82, this.field_147003_i + this.middleX + 18 - 12, this.field_147009_r + this.middleY + 4, 10, 10, true)); this.field_146292_n.add(new GuiPlusMinusButton(83, this.field_147003_i + this.middleX + 18 + 11, this.field_147009_r + this.middleY + 4, 10, 10, false)); this.field_146292_n.add(new GuiGolemLockButton(25, this.field_147003_i + this.middleX - 32, this.field_147009_r + this.middleY, 16, 16, this.seal)); break;case 1: if (this.seal.getSeal() instanceof ISealConfigFilter) { int s = ((ISealConfigFilter)this.seal.getSeal()).getFilterSize(); int sy = 16 + (s - 1) / 3 * 12; this.field_146292_n.add(new GuiGolemBWListButton(20, this.field_147003_i + this.middleX - 8, this.field_147009_r + this.middleY + (s - 1) / 3 * 24 - sy + 27, 16, 16, (ISealConfigFilter)this.seal.getSeal())); }  break;case 2: this.field_146292_n.add(new GuiPlusMinusButton(90, this.field_147003_i + this.middleX - 5 - 14, this.field_147009_r + this.middleY - 25, 10, 10, true)); this.field_146292_n.add(new GuiPlusMinusButton(91, this.field_147003_i + this.middleX - 5 + 14, this.field_147009_r + this.middleY - 25, 10, 10, false)); this.field_146292_n.add(new GuiPlusMinusButton(92, this.field_147003_i + this.middleX - 5 - 14, this.field_147009_r + this.middleY, 10, 10, true)); this.field_146292_n.add(new GuiPlusMinusButton(93, this.field_147003_i + this.middleX - 5 + 14, this.field_147009_r + this.middleY, 10, 10, false)); this.field_146292_n.add(new GuiPlusMinusButton(94, this.field_147003_i + this.middleX - 5 - 14, this.field_147009_r + this.middleY + 25, 10, 10, true)); this.field_146292_n.add(new GuiPlusMinusButton(95, this.field_147003_i + this.middleX - 5 + 14, this.field_147009_r + this.middleY + 25, 10, 10, false)); break;case 3: if (this.seal.getSeal() instanceof ISealConfigToggles) { ISealConfigToggles cp = (ISealConfigToggles)this.seal.getSeal(); int s = (cp.getToggles().length < 4) ? 8 : ((cp.getToggles().length < 6) ? 7 : ((cp.getToggles().length < 9) ? 6 : 5)); int h = (cp.getToggles().length - 1) * s; int w = 12; for (ISealConfigToggles.SealToggle prop : cp.getToggles()) { int ww = 12 + Math.min(100, this.field_146289_q.func_78256_a(I18n.func_74838_a(prop.getName()))); ww /= 2; if (ww > w)
/*     */               w = ww;  }  int p = 0; for (ISealConfigToggles.SealToggle prop : cp.getToggles()) { this.field_146292_n.add(new GuiGolemPropButton(30 + p, this.field_147003_i + this.middleX - w, this.field_147009_r + this.middleY - 5 - h + p * s * 2, 8, 8, prop.getName(), prop)); p++; }  }  break;
/* 275 */       case 4: tags = this.seal.getSeal().getRequiredTags(); if (tags != null && tags.length > 0) { int p = 0; for (EnumGolemTrait tag : tags) { this.field_146292_n.add(new GuiHoverButton(this, 500 + p, this.field_147003_i + this.middleX + p * 18 - (tags.length - 1) * 9, this.field_147009_r + this.middleY - 8, 16, 16, tag.getLocalizedName(), tag.getLocalizedDescription(), tag.icon)); p++; }  }  tags = this.seal.getSeal().getForbiddenTags(); if (tags != null && tags.length > 0) { int p = 0; for (EnumGolemTrait tag : tags) { this.field_146292_n.add(new GuiHoverButton(this, 600 + p, this.field_147003_i + this.middleX + p * 18 - (tags.length - 1) * 9, this.field_147009_r + this.middleY + 24, 16, 16, tag.getLocalizedName(), tag.getLocalizedDescription(), tag.icon)); p++; }  }  break; }  } protected boolean func_146983_a(int par1) { return false; } protected void func_146284_a(GuiButton button) throws IOException { if (button.field_146127_k < this.categories.length && this.categories[button.field_146127_k] != this.category) {
/* 276 */       this.category = this.categories[button.field_146127_k];
/* 277 */       ((SealBaseContainer)this.field_147002_h).category = this.category;
/* 278 */       ((SealBaseContainer)this.field_147002_h).setupCategories();
/* 279 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, button.field_146127_k);
/* 280 */       setupCategories();
/*     */ 
/*     */     
/*     */     }
/* 284 */     else if (this.category == 0 && button.field_146127_k == 25 && this.seal.getOwner().equals(this.field_146297_k.field_71439_g.func_110124_au().toString())) {
/* 285 */       this.seal.setLocked(!this.seal.isLocked());
/* 286 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, this.seal.isLocked() ? 25 : 26);
/*     */ 
/*     */     
/*     */     }
/* 290 */     else if (this.category == 1 && this.seal.getSeal() instanceof ISealConfigFilter && button.field_146127_k == 20) {
/* 291 */       ISealConfigFilter cp = (ISealConfigFilter)this.seal.getSeal();
/* 292 */       cp.setBlacklist(!cp.isBlacklist());
/* 293 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, cp.isBlacklist() ? 20 : 21);
/*     */ 
/*     */     
/*     */     }
/* 297 */     else if (this.category == 3 && this.seal.getSeal() instanceof ISealConfigToggles && button.field_146127_k >= 30 && button.field_146127_k < 30 + ((ISealConfigToggles)this.seal
/* 298 */       .getSeal()).getToggles().length) {
/* 299 */       ISealConfigToggles cp = (ISealConfigToggles)this.seal.getSeal();
/* 300 */       cp.setToggle(button.field_146127_k - 30, !cp.getToggles()[button.field_146127_k - 30].getValue());
/* 301 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, cp.getToggles()[button.field_146127_k - 30].getValue() ? button.field_146127_k : (button.field_146127_k + 30));
/*     */ 
/*     */     
/*     */     }
/* 305 */     else if (button.field_146127_k == 27 && this.seal.getOwner().equals(this.field_146297_k.field_71439_g.func_110124_au().toString())) {
/* 306 */       this.seal.setRedstoneSensitive(!this.seal.isRedstoneSensitive());
/* 307 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, this.seal.isRedstoneSensitive() ? 27 : 28);
/*     */     }
/*     */     else {
/*     */       
/* 311 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, button.field_146127_k);
/*     */     }  }
/*     */ 
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 316 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */     
/* 318 */     if (this.category == 1 && this.seal.getSeal() instanceof ISealConfigFilter && !((ISealConfigFilter)this.seal.getSeal()).isBlacklist()) {
/* 319 */       int x = this.field_147003_i;
/* 320 */       int y = this.field_147009_r;
/* 321 */       ISealConfigFilter cp = (ISealConfigFilter)this.seal.getSeal();
/* 322 */       int k = 240;
/* 323 */       int l = 240;
/* 324 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
/* 325 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 326 */       this.field_73735_i = 100.0F;
/* 327 */       for (int i1 = 0; i1 < cp.getFilterSize(); i1++) {
/*     */         
/* 329 */         Slot slot = (Slot)this.field_147002_h.field_75151_b.get(i1);
/* 330 */         if (slot.func_111238_b() && !slot.func_75211_c().func_190926_b()) {
/* 331 */           int i = slot.field_75223_e;
/* 332 */           int j = slot.field_75221_f;
/* 333 */           int sz = cp.getFilterSlotSize(i1);
/* 334 */           String s = String.valueOf(cp.getFilterSlotSize(i1));
/* 335 */           if (sz == 0) s = TextFormatting.GOLD.toString() + "*"; 
/* 336 */           GlStateManager.func_179140_f();
/* 337 */           GlStateManager.func_179097_i();
/* 338 */           GlStateManager.func_179084_k();
/* 339 */           this.field_146289_q.func_175063_a(s, (x + i + 19 - 2 - this.field_146289_q.func_78256_a(s)), (y + j + 6 + 3), 16777215);
/* 340 */           GlStateManager.func_179145_e();
/* 341 */           GlStateManager.func_179126_j();
/* 342 */           GlStateManager.func_179147_l();
/*     */         } 
/*     */       } 
/* 345 */       this.field_73735_i = 0.0F;
/* 346 */       RenderHelper.func_74518_a();
/* 347 */       RenderHelper.func_74520_c();
/* 348 */       RenderHelper.func_74519_b();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\gui\SealBaseGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */