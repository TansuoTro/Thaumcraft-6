/*      */ package thaumcraft.client.gui;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.LinkedList;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.GuiButton;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.gui.GuiTextField;
/*      */ import net.minecraft.client.renderer.BufferBuilder;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.RenderHelper;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.CraftingManager;
/*      */ import net.minecraft.item.crafting.IRecipe;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.util.text.TextFormatting;
/*      */ import net.minecraft.util.text.translation.I18n;
/*      */ import net.minecraftforge.fml.client.FMLClientHandler;
/*      */ import net.minecraftforge.fml.relauncher.Side;
/*      */ import net.minecraftforge.fml.relauncher.SideOnly;
/*      */ import org.apache.commons.lang3.tuple.Pair;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*      */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*      */ import thaumcraft.api.casters.FocusEngine;
/*      */ import thaumcraft.api.casters.FocusNode;
/*      */ import thaumcraft.api.casters.IFocusElement;
/*      */ import thaumcraft.api.crafting.CrucibleRecipe;
/*      */ import thaumcraft.api.crafting.InfusionRecipe;
/*      */ import thaumcraft.api.internal.CommonInternals;
/*      */ import thaumcraft.api.research.ResearchCategories;
/*      */ import thaumcraft.api.research.ResearchCategory;
/*      */ import thaumcraft.api.research.ResearchEntry;
/*      */ import thaumcraft.api.research.ResearchStage;
/*      */ import thaumcraft.client.lib.UtilsFX;
/*      */ import thaumcraft.common.config.ConfigResearch;
/*      */ import thaumcraft.common.lib.SoundsTC;
/*      */ import thaumcraft.common.lib.research.ResearchManager;
/*      */ import thaumcraft.common.lib.utils.InventoryUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @SideOnly(Side.CLIENT)
/*      */ public class GuiResearchBrowser
/*      */   extends GuiScreen
/*      */ {
/*      */   private static int guiBoundsLeft;
/*      */   private static int guiBoundsTop;
/*      */   private static int guiBoundsRight;
/*      */   private static int guiBoundsBottom;
/*      */   protected int mouseX;
/*      */   protected int mouseY;
/*      */   protected float screenZoom;
/*      */   protected double curMouseX;
/*      */   protected double curMouseY;
/*      */   protected double guiMapX;
/*      */   protected double guiMapY;
/*      */   protected double tempMapX;
/*      */   protected double tempMapY;
/*      */   private int isMouseButtonDown;
/*   84 */   public static double lastX = -9999.0D;
/*   85 */   public static double lastY = -9999.0D;
/*      */   
/*      */   GuiResearchBrowser instance;
/*      */   
/*      */   private int screenX;
/*      */   
/*      */   private int screenY;
/*      */   
/*      */   private int startX;
/*      */   private int startY;
/*      */   long t;
/*      */   private LinkedList<ResearchEntry> research;
/*   97 */   static String selectedCategory = null; private ResearchEntry currentHighlight; private EntityPlayer player; long popuptime; String popupmessage; private GuiTextField searchField; private static boolean searching = false; private ArrayList<String> categoriesTC; private ArrayList<String> categoriesOther; public GuiResearchBrowser() { this.mouseX = 0; this.mouseY = 0; this.screenZoom = 1.0F; this.isMouseButtonDown = 0; this.instance = null; this.startX = 16; this.startY = 16; this.t = 0L;
/*      */     this.research = new LinkedList();
/*   99 */     this.currentHighlight = null;
/*  100 */     this.player = null;
/*  101 */     this.popuptime = 0L;
/*  102 */     this.popupmessage = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  124 */     this.categoriesTC = new ArrayList();
/*  125 */     this.categoriesOther = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  130 */     this.addonShift = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  256 */     this.invisible = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  340 */     this.searchResults = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  550 */     this.tx1 = new ResourceLocation("thaumcraft", "textures/gui/gui_research_browser.png"); this.curMouseX = this.guiMapX = this.tempMapX = lastX; this.curMouseY = this.guiMapY = this.tempMapY = lastY; this.player = (Minecraft.func_71410_x()).field_71439_g; this.instance = this; } public GuiResearchBrowser(double x, double y) { this.mouseX = 0; this.mouseY = 0; this.screenZoom = 1.0F; this.isMouseButtonDown = 0; this.instance = null; this.startX = 16; this.startY = 16; this.t = 0L; this.research = new LinkedList(); this.currentHighlight = null; this.player = null; this.popuptime = 0L; this.popupmessage = ""; this.categoriesTC = new ArrayList(); this.categoriesOther = new ArrayList(); this.addonShift = 0; this.invisible = new ArrayList(); this.searchResults = new ArrayList(); this.tx1 = new ResourceLocation("thaumcraft", "textures/gui/gui_research_browser.png"); this.curMouseX = this.guiMapX = this.tempMapX = x; this.curMouseY = this.guiMapY = this.tempMapY = y; this.player = (Minecraft.func_71410_x()).field_71439_g; this.instance = this; } static int catScrollPos = 0; static int catScrollMax = 0; public int addonShift; private ArrayList<String> invisible; ArrayList<Pair<String, SearchResult>> searchResults; ResourceLocation tx1; public void func_73866_w_() { updateResearch(); } public void updateResearch() { if (this.field_146297_k == null) this.field_146297_k = Minecraft.func_71410_x();  this.field_146292_n.clear(); this.field_146292_n.add(new GuiSearchButton(2, 1, this.field_146295_m - 17, 16, 16, I18n.func_74837_a("tc.search", new Object[0]))); Keyboard.enableRepeatEvents(true); this.searchField = new GuiTextField(0, this.field_146289_q, 20, 20, 89, this.field_146289_q.field_78288_b); this.searchField.func_146203_f(15); this.searchField.func_146185_a(true); this.searchField.func_146189_e(false); this.searchField.func_146193_g(16777215); if (searching) { this.searchField.func_146189_e(true); this.searchField.func_146205_d(false); this.searchField.func_146195_b(true); this.searchField.func_146180_a(""); updateSearch(); }  this.screenX = this.field_146294_l - 32; this.screenY = this.field_146295_m - 32; this.research.clear(); if (selectedCategory == null) { Collection cats = ResearchCategories.researchCategories.keySet(); selectedCategory = (String)cats.iterator().next(); }  int limit = (int)Math.floor(((this.screenY - 28) / 24.0F)); this.addonShift = 0; int count = 0; this.categoriesTC.clear(); this.categoriesOther.clear(); label71: for (String rcl : ResearchCategories.researchCategories.keySet()) { int rt = 0; int rco = 0; Collection col = (ResearchCategories.getResearchCategory(rcl)).research.values(); for (Object res : col) { rt++; if (!((ResearchEntry)res).hasMeta(ResearchEntry.EnumResearchMeta.AUTOUNLOCK) && ThaumcraftCapabilities.knowsResearch(this.player, new String[] { ((ResearchEntry)res).getKey() })) rco++;  }  int v = (int)(rco / rt * 100.0F); ResearchCategory rc = ResearchCategories.getResearchCategory(rcl); if (rc.researchKey != null && !ThaumcraftCapabilities.knowsResearchStrict(this.player, new String[] { rc.researchKey })) continue;  for (String tcc : ConfigResearch.TCCategories) { if (tcc.equals(rcl)) { this.categoriesTC.add(rcl); this.field_146292_n.add(new GuiCategoryButton(rc, rcl, false, 20 + this.categoriesTC.size(), 1, 10 + this.categoriesTC.size() * 24, 16, 16, I18n.func_74837_a("tc.research_category." + rcl, new Object[0]), v)); continue label71; }  }  count++; if (count > limit + catScrollPos || count - 1 < catScrollPos) continue;  this.categoriesOther.add(rcl); this.field_146292_n.add(new GuiCategoryButton(rc, rcl, true, 50 + this.categoriesOther.size(), this.field_146294_l - 17, 10 + this.categoriesOther.size() * 24, 16, 16, I18n.func_74837_a("tc.research_category." + rcl, new Object[0]), v)); }  if (count > limit || count < catScrollPos) { this.addonShift = (this.screenY - 28) % 24 / 2; this.field_146292_n.add(new GuiScrollButton(false, 3, this.field_146294_l - 14, 20, 10, 11, "")); this.field_146292_n.add(new GuiScrollButton(true, 4, this.field_146294_l - 14, this.screenY + 1, 10, 11, "")); }  catScrollMax = count - limit; if (selectedCategory == null || selectedCategory.equals("")) return;  Collection col = (ResearchCategories.getResearchCategory(selectedCategory)).research.values(); for (Object res : col) this.research.add((ResearchEntry)res);  guiBoundsLeft = 99999; guiBoundsTop = 99999; guiBoundsRight = -99999; guiBoundsBottom = -99999; for (ResearchEntry res : this.research) { if (res != null && isVisible(res)) { if (res.getDisplayColumn() * 24 - this.screenX + 48 < guiBoundsLeft) guiBoundsLeft = res.getDisplayColumn() * 24 - this.screenX + 48;  if (res.getDisplayColumn() * 24 - 24 > guiBoundsRight) guiBoundsRight = res.getDisplayColumn() * 24 - 24;  if (res.getDisplayRow() * 24 - this.screenY + 48 < guiBoundsTop)
/*      */           guiBoundsTop = res.getDisplayRow() * 24 - this.screenY + 48;  if (res.getDisplayRow() * 24 - 24 > guiBoundsBottom)
/*      */           guiBoundsBottom = res.getDisplayRow() * 24 - 24;  }  }  }
/*  553 */   private void genResearchBackgroundFixedPre(int par1, int par2, float par3, int locX, int locY) { this.field_73735_i = 0.0F;
/*  554 */     GL11.glDepthFunc(518);
/*  555 */     GL11.glPushMatrix();
/*  556 */     GL11.glTranslatef(0.0F, 0.0F, -200.0F);
/*  557 */     GlStateManager.func_179098_w();
/*  558 */     GlStateManager.func_179140_f();
/*  559 */     GlStateManager.func_179091_B();
/*  560 */     GlStateManager.func_179142_g(); }
/*      */   private boolean isVisible(ResearchEntry res) { if (ThaumcraftCapabilities.knowsResearch(this.player, new String[] { res.getKey() }))
/*      */       return true;  if (this.invisible.contains(res.getKey()) || (res.hasMeta(ResearchEntry.EnumResearchMeta.HIDDEN) && !canUnlockResearch(res)))
/*      */       return false;  if (res.getParents() == null && res.hasMeta(ResearchEntry.EnumResearchMeta.HIDDEN))
/*      */       return false;  if (res.getParents() != null)
/*      */       for (String r : res.getParents()) { ResearchEntry ri = ResearchCategories.getResearch(r); if (ri != null && !isVisible(ri)) {
/*      */           this.invisible.add(r); return false;
/*      */         }  }
/*      */         return true; }
/*      */   private boolean canUnlockResearch(ResearchEntry res) { return ResearchManager.doesPlayerHaveRequisites(this.player, res.getKey()); }
/*      */   public void func_146281_b() { lastX = this.guiMapX; lastY = this.guiMapY; Keyboard.enableRepeatEvents(false); super.func_146281_b(); } public void func_146280_a(Minecraft mc, int width, int height) { super.func_146280_a(mc, width, height); updateResearch(); if (lastX == -9999.0D || this.guiMapX > guiBoundsRight || this.guiMapX < guiBoundsLeft)
/*      */       this.guiMapX = this.tempMapX = ((guiBoundsLeft + guiBoundsRight) / 2);  if (lastY == -9999.0D || this.guiMapY > guiBoundsBottom || this.guiMapY < guiBoundsTop)
/*  572 */       this.guiMapY = this.tempMapY = ((guiBoundsBottom + guiBoundsTop) / 2);  } protected void genResearchBackgroundZoomable(int mx, int my, float par3, int locX, int locY) { GL11.glPushMatrix();
/*  573 */     GlStateManager.func_179147_l();
/*  574 */     GL11.glEnable(3042);
/*  575 */     GlStateManager.func_179112_b(770, 771);
/*  576 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  577 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  578 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a((ResearchCategories.getResearchCategory(selectedCategory)).background);
/*  579 */     drawTexturedModalRectWithDoubles((this.startX - 2) * this.screenZoom, (this.startY - 2) * this.screenZoom, locX / 2.0D, locY / 2.0D, ((this.screenX + 4) * this.screenZoom), ((this.screenY + 4) * this.screenZoom));
/*  580 */     if ((ResearchCategories.getResearchCategory(selectedCategory)).background2 != null) {
/*  581 */       (Minecraft.func_71410_x()).field_71446_o.func_110577_a((ResearchCategories.getResearchCategory(selectedCategory)).background2);
/*  582 */       drawTexturedModalRectWithDoubles((this.startX - 2) * this.screenZoom, (this.startY - 2) * this.screenZoom, locX / 1.5D, locY / 1.5D, ((this.screenX + 4) * this.screenZoom), ((this.screenY + 4) * this.screenZoom));
/*      */     } 
/*  584 */     GlStateManager.func_179084_k();
/*  585 */     GlStateManager.func_179092_a(516, 0.1F);
/*  586 */     GL11.glPopMatrix();
/*      */     
/*  588 */     GL11.glEnable(2929);
/*  589 */     GL11.glDepthFunc(515);
/*      */     
/*  591 */     this.field_146297_k.field_71446_o.func_110577_a(this.tx1);
/*      */ 
/*      */ 
/*      */     
/*  595 */     if (ThaumcraftCapabilities.getKnowledge(this.player).getResearchList() != null)
/*  596 */       for (int index = 0; index < this.research.size(); index++) {
/*      */         
/*  598 */         ResearchEntry source = (ResearchEntry)this.research.get(index);
/*      */ 
/*      */         
/*  601 */         if (source.getParents() != null && source.getParents().length > 0)
/*      */         {
/*  603 */           for (int a = 0; a < source.getParents().length; a++) {
/*  604 */             if (source.getParents()[a] != null && ResearchCategories.getResearch(source.getParentsClean()[a]) != null && 
/*  605 */               ResearchCategories.getResearch(source.getParentsClean()[a]).getCategory().equals(selectedCategory)) {
/*      */               
/*  607 */               ResearchEntry parent = ResearchCategories.getResearch(source.getParentsClean()[a]);
/*  608 */               if (parent.getSiblings() == null || !Arrays.asList(parent.getSiblings()).contains(source.getKey())) {
/*      */                 
/*  610 */                 boolean knowsParent = ThaumcraftCapabilities.knowsResearchStrict(this.player, new String[] { source.getParents()[a] });
/*      */                 
/*  612 */                 boolean b = (isVisible(source) && !source.getParents()[a].startsWith("~"));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  620 */                 if (b)
/*  621 */                   if (knowsParent) {
/*      */                     
/*  623 */                     drawLine(source.getDisplayColumn(), source.getDisplayRow(), parent
/*  624 */                         .getDisplayColumn(), parent.getDisplayRow(), 0.6F, 0.6F, 0.6F, locX, locY, 3.0F, true, source
/*  625 */                         .hasMeta(ResearchEntry.EnumResearchMeta.REVERSE));
/*      */                   }
/*  627 */                   else if (isVisible(parent)) {
/*  628 */                     drawLine(source.getDisplayColumn(), source.getDisplayRow(), parent
/*  629 */                         .getDisplayColumn(), parent.getDisplayRow(), 0.2F, 0.2F, 0.2F, locX, locY, 2.0F, true, source
/*  630 */                         .hasMeta(ResearchEntry.EnumResearchMeta.REVERSE));
/*      */                   }  
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*  636 */         if (source.getSiblings() != null && source.getSiblings().length > 0)
/*      */         {
/*  638 */           for (int a = 0; a < source.getSiblings().length; a++) {
/*  639 */             if (source.getSiblings()[a] != null && ResearchCategories.getResearch(source.getSiblings()[a]) != null && 
/*  640 */               ResearchCategories.getResearch(source.getSiblings()[a]).getCategory().equals(selectedCategory)) {
/*  641 */               ResearchEntry sibling = ResearchCategories.getResearch(source.getSiblings()[a]);
/*      */               
/*  643 */               boolean knowsSibling = ThaumcraftCapabilities.knowsResearchStrict(this.player, new String[] { sibling.getKey() });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  651 */               if (isVisible(source) && !source.getSiblings()[a].startsWith("~")) {
/*  652 */                 if (knowsSibling) {
/*      */                   
/*  654 */                   drawLine(sibling.getDisplayColumn(), sibling.getDisplayRow(), source
/*  655 */                       .getDisplayColumn(), source.getDisplayRow(), 0.3F, 0.3F, 0.4F, locX, locY, 1.0F, false, source
/*  656 */                       .hasMeta(ResearchEntry.EnumResearchMeta.REVERSE));
/*      */                 }
/*  658 */                 else if (isVisible(sibling)) {
/*  659 */                   drawLine(sibling.getDisplayColumn(), sibling.getDisplayRow(), source
/*  660 */                       .getDisplayColumn(), source.getDisplayRow(), 0.1875F, 0.1875F, 0.25F, locX, locY, 0.0F, false, source
/*  661 */                       .hasMeta(ResearchEntry.EnumResearchMeta.REVERSE));
/*      */                 } 
/*      */               }
/*      */             } 
/*      */           } 
/*      */         }
/*      */       }  
/*  668 */     this.currentHighlight = null;
/*      */     
/*  670 */     GL11.glEnable(32826);
/*  671 */     GL11.glEnable(2903);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  676 */     for (int var24 = 0; var24 < this.research.size(); var24++) {
/*      */       
/*  678 */       GL11.glEnable(3042);
/*  679 */       GL11.glBlendFunc(770, 771);
/*      */       
/*  681 */       ResearchEntry iconResearch = (ResearchEntry)this.research.get(var24);
/*  682 */       boolean hasWarp = false;
/*  683 */       if (iconResearch.getStages() != null)
/*  684 */         for (ResearchStage stage : iconResearch.getStages()) {
/*  685 */           if (stage.getWarp() > 0) {
/*  686 */             hasWarp = true;
/*      */             break;
/*      */           } 
/*      */         }  
/*  690 */       int var26 = iconResearch.getDisplayColumn() * 24 - locX;
/*  691 */       int var27 = iconResearch.getDisplayRow() * 24 - locY;
/*      */       
/*  693 */       if (var26 >= -24 && var27 >= -24 && var26 <= this.screenX * this.screenZoom && var27 <= this.screenY * this.screenZoom) {
/*      */ 
/*      */         
/*  696 */         int iconX = this.startX + var26;
/*  697 */         int iconY = this.startY + var27;
/*      */         
/*  699 */         if (isVisible(iconResearch)) {
/*      */           
/*  701 */           if (hasWarp) {
/*  702 */             drawForbidden((iconX + 8), (iconY + 8));
/*      */           }
/*      */           
/*  705 */           if (ThaumcraftCapabilities.getKnowledge(this.player).isResearchComplete(iconResearch.getKey())) {
/*      */             
/*  707 */             float var38 = 1.0F;
/*  708 */             GL11.glColor4f(var38, var38, var38, 1.0F);
/*      */           
/*      */           }
/*  711 */           else if (canUnlockResearch(iconResearch)) {
/*      */             
/*  713 */             float var38 = (float)Math.sin((Minecraft.func_71386_F() % 600L) / 600.0D * Math.PI * 2.0D) * 0.25F + 0.75F;
/*  714 */             GL11.glColor4f(var38, var38, var38, 1.0F);
/*      */           }
/*      */           else {
/*      */             
/*  718 */             float var38 = 0.3F;
/*  719 */             GL11.glColor4f(var38, var38, var38, 1.0F);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  726 */           this.field_146297_k.field_71446_o.func_110577_a(this.tx1);
/*      */           
/*  728 */           GL11.glEnable(2884);
/*  729 */           GL11.glEnable(3042);
/*  730 */           GL11.glBlendFunc(770, 771);
/*      */           
/*  732 */           if (iconResearch.hasMeta(ResearchEntry.EnumResearchMeta.ROUND)) {
/*      */             
/*  734 */             func_73729_b(iconX - 8, iconY - 8, 144, 48 + (iconResearch.hasMeta(ResearchEntry.EnumResearchMeta.HIDDEN) ? 32 : 0), 32, 32);
/*      */           } else {
/*  736 */             int ix = 80;
/*  737 */             int iy = 48;
/*  738 */             if (iconResearch.hasMeta(ResearchEntry.EnumResearchMeta.HIDDEN))
/*      */             {
/*  740 */               iy += 32;
/*      */             }
/*  742 */             if (iconResearch.hasMeta(ResearchEntry.EnumResearchMeta.HEX)) {
/*  743 */               ix += 32;
/*      */             }
/*  745 */             func_73729_b(iconX - 8, iconY - 8, ix, iy, 32, 32);
/*      */           } 
/*      */           
/*  748 */           if (iconResearch.hasMeta(ResearchEntry.EnumResearchMeta.SPIKY))
/*      */           {
/*  750 */             func_73729_b(iconX - 8, iconY - 8, 176, 48 + (iconResearch.hasMeta(ResearchEntry.EnumResearchMeta.HIDDEN) ? 32 : 0), 32, 32);
/*      */           }
/*      */           
/*  753 */           boolean bw = false;
/*  754 */           if (!canUnlockResearch(iconResearch)) {
/*      */             
/*  756 */             float var40 = 0.1F;
/*  757 */             GL11.glColor4f(var40, var40, var40, 1.0F);
/*      */             
/*  759 */             bw = true;
/*      */           } 
/*      */           
/*  762 */           if (ThaumcraftCapabilities.getKnowledge(this.player).hasResearchFlag(iconResearch.getKey(), IPlayerKnowledge.EnumResearchFlag.RESEARCH)) {
/*  763 */             GL11.glPushMatrix();
/*  764 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  765 */             GL11.glTranslatef((iconX - 9), (iconY - 9), 0.0F);
/*  766 */             GL11.glScaled(0.5D, 0.5D, 1.0D);
/*  767 */             func_73729_b(0, 0, 176, 16, 32, 32);
/*  768 */             GL11.glPopMatrix();
/*      */           } 
/*  770 */           if (ThaumcraftCapabilities.getKnowledge(this.player).hasResearchFlag(iconResearch.getKey(), IPlayerKnowledge.EnumResearchFlag.PAGE)) {
/*  771 */             GL11.glPushMatrix();
/*  772 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  773 */             GL11.glTranslatef((iconX - 9), (iconY + 9), 0.0F);
/*  774 */             GL11.glScaled(0.5D, 0.5D, 1.0D);
/*  775 */             func_73729_b(0, 0, 208, 16, 32, 32);
/*  776 */             GL11.glPopMatrix();
/*      */           } 
/*      */           
/*  779 */           drawResearchIcon(iconResearch, iconX, iconY, this.field_73735_i, bw);
/*      */ 
/*      */           
/*  782 */           if (!canUnlockResearch(iconResearch))
/*      */           {
/*      */             
/*  785 */             bw = false;
/*      */           }
/*      */           
/*  788 */           if (mx >= this.startX && my >= this.startY && mx < this.startX + this.screenX && my < this.startY + this.screenY && mx >= (iconX - 2) / this.screenZoom && mx <= (iconX + 18) / this.screenZoom && my >= (iconY - 2) / this.screenZoom && my <= (iconY + 18) / this.screenZoom)
/*      */           {
/*      */             
/*  791 */             this.currentHighlight = iconResearch;
/*      */           }
/*  793 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */         } 
/*      */       } 
/*  796 */     }  GL11.glDisable(2929); }
/*      */   protected void func_73869_a(char par1, int par2) throws IOException { if (searching && this.searchField.func_146201_a(par1, par2)) { updateSearch(); } else if (par2 == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i()) { this.field_146297_k.func_147108_a((GuiScreen)null); this.field_146297_k.func_71381_h(); }  super.func_73869_a(par1, par2); }
/*      */   private class SearchResult implements Comparable {
/*      */     String key;
/*  800 */     ResourceLocation recipe; boolean cat; private SearchResult(String key, ResourceLocation rec) { this.key = key; this.recipe = rec; this.cat = false; } private SearchResult(String key, ResourceLocation recipe, boolean cat) { this.key = key; this.recipe = recipe; this.cat = cat; } public int compareTo(Object arg0) { SearchResult arg = (SearchResult)arg0; int k = this.key.compareTo(arg.key); return (k == 0 && this.recipe != null && arg.recipe != null) ? this.recipe.compareTo(arg.recipe) : k; } } private void updateSearch() { this.searchResults.clear(); this.invisible.clear(); String s1 = this.searchField.func_146179_b().toLowerCase(); for (String cat : this.categoriesTC) { if (cat.toLowerCase().contains(s1)) this.searchResults.add(Pair.of(I18n.func_74837_a("tc.research_category." + cat, new Object[0]), new SearchResult(cat, null, true, null)));  }  for (String cat : this.categoriesOther) { if (cat.toLowerCase().contains(s1)) this.searchResults.add(Pair.of(I18n.func_74837_a("tc.research_category." + cat, new Object[0]), new SearchResult(cat, null, true, null)));  }  ArrayList<ResourceLocation> dupCheck = new ArrayList<ResourceLocation>(); for (String pre : ThaumcraftCapabilities.getKnowledge(this.player).getResearchList()) { ResearchEntry ri = ResearchCategories.getResearch(pre); if (ri == null || ri.getLocalizedName() == null) continue;  if (ri.getLocalizedName().toLowerCase().contains(s1)) this.searchResults.add(Pair.of(ri.getLocalizedName(), new SearchResult(pre, null, null)));  int stage = ThaumcraftCapabilities.getKnowledge(this.player).getResearchStage(pre); if (ri.getStages() != null) { int s = (ri.getStages().length - 1 < stage + 1) ? (ri.getStages().length - 1) : (stage + 1); ResearchStage page = ri.getStages()[s]; if (page != null && page.getRecipes() != null) for (ResourceLocation rec : page.getRecipes()) { if (!dupCheck.contains(rec)) { dupCheck.add(rec); Object recipeObject = CommonInternals.getCatalogRecipe(rec); if (recipeObject == null) recipeObject = CommonInternals.getCatalogRecipeFake(rec);  if (recipeObject == null) recipeObject = CraftingManager.func_193373_a(rec);  if (recipeObject != null) { ItemStack ro = null; if (recipeObject instanceof IRecipe) { ro = ((IRecipe)recipeObject).func_77571_b(); } else if (recipeObject instanceof InfusionRecipe && ((InfusionRecipe)recipeObject).getRecipeOutput() instanceof ItemStack) { ro = (ItemStack)((InfusionRecipe)recipeObject).getRecipeOutput(); } else if (recipeObject instanceof CrucibleRecipe) { ro = ((CrucibleRecipe)recipeObject).getRecipeOutput(); }  if (ro != null && !ro.func_190926_b() && ro.func_82833_r().toLowerCase().contains(s1)) this.searchResults.add(Pair.of(ro.func_82833_r(), new SearchResult(pre, rec, null)));  }  }  }   }  }  Collections.sort(this.searchResults); } public void func_73863_a(int mx, int my, float par3) { if (!searching) { if (Mouse.isButtonDown(0)) { if ((this.isMouseButtonDown == 0 || this.isMouseButtonDown == 1) && mx >= this.startX && mx < this.startX + this.screenX && my >= this.startY && my < this.startY + this.screenY) { if (this.isMouseButtonDown == 0) { this.isMouseButtonDown = 1; } else { this.guiMapX -= (mx - this.mouseX) * this.screenZoom; this.guiMapY -= (my - this.mouseY) * this.screenZoom; this.tempMapX = this.curMouseX = this.guiMapX; this.tempMapY = this.curMouseY = this.guiMapY; }  this.mouseX = mx; this.mouseY = my; }  if (this.tempMapX < guiBoundsLeft * this.screenZoom) this.tempMapX = guiBoundsLeft * this.screenZoom;  if (this.tempMapY < guiBoundsTop * this.screenZoom) this.tempMapY = guiBoundsTop * this.screenZoom;  if (this.tempMapX >= guiBoundsRight * this.screenZoom) this.tempMapX = (guiBoundsRight * this.screenZoom - 1.0F);  if (this.tempMapY >= guiBoundsBottom * this.screenZoom) this.tempMapY = (guiBoundsBottom * this.screenZoom - 1.0F);  } else { this.isMouseButtonDown = 0; }  int k = Mouse.getDWheel(); if (k < 0) { this.screenZoom += 0.25F; } else if (k > 0) { this.screenZoom -= 0.25F; }  this.screenZoom = MathHelper.func_76131_a(this.screenZoom, 1.0F, 2.0F); }  func_146276_q_(); this.t = System.nanoTime() / 50000000L; int locX = MathHelper.func_76128_c(this.curMouseX + (this.guiMapX - this.curMouseX) * par3); int locY = MathHelper.func_76128_c(this.curMouseY + (this.guiMapY - this.curMouseY) * par3); if (locX < guiBoundsLeft * this.screenZoom) locX = (int)(guiBoundsLeft * this.screenZoom);  if (locY < guiBoundsTop * this.screenZoom) locY = (int)(guiBoundsTop * this.screenZoom);  if (locX >= guiBoundsRight * this.screenZoom) locX = (int)(guiBoundsRight * this.screenZoom - 1.0F);  if (locY >= guiBoundsBottom * this.screenZoom) locY = (int)(guiBoundsBottom * this.screenZoom - 1.0F);  genResearchBackgroundFixedPre(mx, my, par3, locX, locY); if (!searching) { GL11.glPushMatrix(); GL11.glScalef(1.0F / this.screenZoom, 1.0F / this.screenZoom, 1.0F); genResearchBackgroundZoomable(mx, my, par3, locX, locY); GL11.glPopMatrix(); } else { this.searchField.func_146194_f(); int q = 0; for (Pair p : this.searchResults) { GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); SearchResult sr = (SearchResult)p.getRight(); int color = sr.cat ? 14527146 : ((sr.recipe == null) ? 14540253 : 11184861); if (sr.recipe != null) { this.field_146297_k.field_71446_o.func_110577_a(this.tx1); GL11.glPushMatrix(); GL11.glScaled(0.5D, 0.5D, 0.5D); func_73729_b(44, (32 + q * 10) * 2, 224, 48, 16, 16); GL11.glPopMatrix(); }  if (mx > 22 && mx < 18 + this.screenX && my >= 32 + q * 10 && my < 40 + q * 10) color = (sr.recipe == null) ? 16777215 : (sr.cat ? 16764108 : 13421823);  this.field_146289_q.func_78276_b((String)p.getLeft(), 32, 32 + q * 10, color); q++; if (32 + (q + 1) * 10 > this.screenY) { this.field_146289_q.func_78276_b(I18n.func_74837_a("tc.search.more", new Object[0]), 22, 34 + q * 10, 11184810); break; }  }  }  genResearchBackgroundFixedPost(mx, my, par3, locX, locY); if (this.popuptime > System.currentTimeMillis()) { ArrayList<String> text = new ArrayList<String>(); text.add(this.popupmessage); UtilsFX.drawCustomTooltip(this, this.field_146289_q, text, 10, 34, -99); }  } public void func_73876_c() { this.curMouseX = this.guiMapX; this.curMouseY = this.guiMapY; double var1 = this.tempMapX - this.guiMapX; double var3 = this.tempMapY - this.guiMapY; if (var1 * var1 + var3 * var3 < 4.0D) { this.guiMapX += var1; this.guiMapY += var3; } else { this.guiMapX += var1 * 0.85D; this.guiMapY += var3 * 0.85D; }  } public static void drawResearchIcon(ResearchEntry iconResearch, int iconX, int iconY, float zLevel, boolean bw) { if (iconResearch.getIcons() != null && iconResearch.getIcons().length > 0) {
/*  801 */       int idx = (int)(System.currentTimeMillis() / 1000L % iconResearch.getIcons().length);
/*  802 */       GL11.glPushMatrix();
/*  803 */       GL11.glEnable(3042);
/*  804 */       GL11.glBlendFunc(770, 771);
/*  805 */       if (iconResearch.getIcons()[idx] instanceof ResourceLocation) {
/*  806 */         (Minecraft.func_71410_x()).field_71446_o.func_110577_a((ResourceLocation)iconResearch.getIcons()[idx]);
/*  807 */         if (bw) GL11.glColor4f(0.2F, 0.2F, 0.2F, 1.0F); 
/*  808 */         int w = GL11.glGetTexLevelParameteri(3553, 0, 4096);
/*  809 */         int h = GL11.glGetTexLevelParameteri(3553, 0, 4097);
/*  810 */         if (h > w && h % w == 0) {
/*  811 */           int m = h / w;
/*  812 */           float q = 16.0F / m;
/*  813 */           float idx1 = (float)(System.currentTimeMillis() / 150L % m) * q;
/*  814 */           UtilsFX.drawTexturedQuadF(iconX, iconY, 0.0F, idx1, 16.0F, q, zLevel);
/*      */         }
/*  816 */         else if (w > h && w % h == 0) {
/*  817 */           int m = w / h;
/*  818 */           float q = 16.0F / m;
/*  819 */           float idx1 = (float)(System.currentTimeMillis() / 150L % m) * q;
/*  820 */           UtilsFX.drawTexturedQuadF(iconX, iconY, idx1, 0.0F, q, 16.0F, zLevel);
/*      */         } else {
/*  822 */           UtilsFX.drawTexturedQuadFull(iconX, iconY, zLevel);
/*      */         }
/*      */       
/*  825 */       } else if (iconResearch.getIcons()[idx] instanceof ItemStack) {
/*  826 */         RenderHelper.func_74520_c();
/*  827 */         GL11.glDisable(2896);
/*  828 */         GL11.glEnable(32826);
/*  829 */         GL11.glEnable(2903);
/*  830 */         GL11.glEnable(2896);
/*  831 */         Minecraft.func_71410_x().func_175599_af().func_180450_b(InventoryUtils.cycleItemStack(iconResearch.getIcons()[idx]), iconX, iconY);
/*  832 */         GL11.glDisable(2896);
/*  833 */         GL11.glDepthMask(true);
/*  834 */         GL11.glEnable(2929);
/*      */       }
/*  836 */       else if (iconResearch.getIcons()[idx] instanceof String && ((String)iconResearch.getIcons()[idx]).startsWith("focus")) {
/*  837 */         String k = ((String)iconResearch.getIcons()[idx]).replaceAll("focus:", "");
/*  838 */         IFocusElement fp = FocusEngine.getElement(k.trim());
/*  839 */         if (fp != null && fp instanceof FocusNode) {
/*  840 */           GuiFocalManipulator.drawPart((FocusNode)fp, iconX + 8, iconY + 8, 24.0F, bw ? 50 : 220, false);
/*      */         }
/*      */       } 
/*  843 */       GL11.glDisable(3042);
/*  844 */       GL11.glPopMatrix();
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void genResearchBackgroundFixedPost(int mx, int my, float par3, int locX, int locY) {
/*  852 */     this.field_146297_k.field_71446_o.func_110577_a(this.tx1);
/*  853 */     GL11.glEnable(3042);
/*  854 */     GL11.glBlendFunc(770, 771);
/*  855 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     
/*  857 */     int c = 16;
/*  858 */     while (c < this.field_146294_l - 16) {
/*  859 */       int p = 64;
/*  860 */       if (c + p > this.field_146294_l - 16) p = this.field_146294_l - 16 - c; 
/*  861 */       if (p > 0) {
/*  862 */         func_73729_b(c, -2, 48, 13, p, 22);
/*  863 */         func_73729_b(c, this.field_146295_m - 20, 48, 13, p, 22);
/*      */       } 
/*  865 */       c += 64;
/*      */     } 
/*      */     
/*  868 */     c = 16;
/*  869 */     while (c < this.field_146295_m - 16) {
/*  870 */       int p = 64;
/*  871 */       if (c + p > this.field_146295_m - 16) p = this.field_146295_m - 16 - c; 
/*  872 */       if (p > 0) {
/*  873 */         func_73729_b(-2, c, 13, 48, 22, p);
/*  874 */         func_73729_b(this.field_146294_l - 20, c, 13, 48, 22, p);
/*      */       } 
/*  876 */       c += 64;
/*      */     } 
/*      */     
/*  879 */     func_73729_b(-2, -2, 13, 13, 22, 22);
/*  880 */     func_73729_b(-2, this.field_146295_m - 20, 13, 13, 22, 22);
/*  881 */     func_73729_b(this.field_146294_l - 20, -2, 13, 13, 22, 22);
/*  882 */     func_73729_b(this.field_146294_l - 20, this.field_146295_m - 20, 13, 13, 22, 22);
/*      */     
/*  884 */     GL11.glPopMatrix();
/*      */ 
/*      */     
/*  887 */     this.field_73735_i = 0.0F;
/*  888 */     GL11.glDepthFunc(515);
/*  889 */     GL11.glDisable(2929);
/*  890 */     GL11.glEnable(3553);
/*      */     
/*  892 */     super.func_73863_a(mx, my, par3);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  897 */     if (this.currentHighlight != null) {
/*      */       
/*  899 */       ArrayList<String> text = new ArrayList<String>();
/*  900 */       text.add("§6" + this.currentHighlight.getLocalizedName());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  911 */       if (canUnlockResearch(this.currentHighlight)) {
/*      */         
/*  913 */         if (!ThaumcraftCapabilities.getKnowledge(this.player).isResearchComplete(this.currentHighlight.getKey()) && this.currentHighlight
/*  914 */           .getStages() != null) {
/*  915 */           int stage = ThaumcraftCapabilities.getKnowledge(this.player).getResearchStage(this.currentHighlight.getKey());
/*  916 */           if (stage > 0) {
/*  917 */             text.add("@@" + TextFormatting.AQUA + I18n.func_74838_a("tc.research.stage") + " " + stage + "/" + this.currentHighlight
/*  918 */                 .getStages().length + TextFormatting.RESET);
/*      */           } else {
/*  920 */             text.add("@@" + TextFormatting.GREEN + I18n.func_74838_a("tc.research.begin") + TextFormatting.RESET);
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/*  925 */         text.add("@@§c" + I18n.func_74838_a("tc.researchmissing"));
/*  926 */         int a = 0;
/*  927 */         for (String p : this.currentHighlight.getParents()) {
/*  928 */           if (!ThaumcraftCapabilities.knowsResearchStrict(this.player, new String[] { p })) {
/*  929 */             String s = "?";
/*      */             try {
/*  931 */               s = ResearchCategories.getResearch(this.currentHighlight.getParentsClean()[a]).getLocalizedName();
/*  932 */             } catch (Exception exception) {}
/*  933 */             text.add("@@§e - " + s);
/*      */           } 
/*  935 */           a++;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  940 */       if (ThaumcraftCapabilities.getKnowledge(this.player).hasResearchFlag(this.currentHighlight.getKey(), IPlayerKnowledge.EnumResearchFlag.RESEARCH)) {
/*  941 */         text.add("@@" + I18n.func_74838_a("tc.research.newresearch"));
/*      */       }
/*  943 */       if (ThaumcraftCapabilities.getKnowledge(this.player).hasResearchFlag(this.currentHighlight.getKey(), IPlayerKnowledge.EnumResearchFlag.PAGE)) {
/*  944 */         text.add("@@" + I18n.func_74838_a("tc.research.newpage"));
/*      */       }
/*  946 */       UtilsFX.drawCustomTooltip(this, this.field_146289_q, text, mx + 3, my - 3, -99);
/*      */     } 
/*      */     
/*  949 */     GlStateManager.func_179126_j();
/*  950 */     GlStateManager.func_179145_e();
/*  951 */     RenderHelper.func_74518_a();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_73864_a(int mx, int my, int par3) { // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokestatic currentTimeMillis : ()J
/*      */     //   4: lconst_1
/*      */     //   5: lsub
/*      */     //   6: putfield popuptime : J
/*      */     //   9: getstatic thaumcraft/client/gui/GuiResearchBrowser.searching : Z
/*      */     //   12: ifne -> 175
/*      */     //   15: aload_0
/*      */     //   16: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   19: ifnull -> 175
/*      */     //   22: aload_0
/*      */     //   23: getfield player : Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   26: iconst_1
/*      */     //   27: anewarray java/lang/String
/*      */     //   30: dup
/*      */     //   31: iconst_0
/*      */     //   32: aload_0
/*      */     //   33: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   36: invokevirtual getKey : ()Ljava/lang/String;
/*      */     //   39: aastore
/*      */     //   40: invokestatic knowsResearch : (Lnet/minecraft/entity/player/EntityPlayer;[Ljava/lang/String;)Z
/*      */     //   43: ifne -> 175
/*      */     //   46: aload_0
/*      */     //   47: aload_0
/*      */     //   48: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   51: invokespecial canUnlockResearch : (Lthaumcraft/api/research/ResearchEntry;)Z
/*      */     //   54: ifeq -> 175
/*      */     //   57: aload_0
/*      */     //   58: invokevirtual updateResearch : ()V
/*      */     //   61: getstatic thaumcraft/common/lib/network/PacketHandler.INSTANCE : Lnet/minecraftforge/fml/common/network/simpleimpl/SimpleNetworkWrapper;
/*      */     //   64: new thaumcraft/common/lib/network/playerdata/PacketSyncProgressToServer
/*      */     //   67: dup
/*      */     //   68: aload_0
/*      */     //   69: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   72: invokevirtual getKey : ()Ljava/lang/String;
/*      */     //   75: iconst_1
/*      */     //   76: invokespecial <init> : (Ljava/lang/String;Z)V
/*      */     //   79: invokevirtual sendToServer : (Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V
/*      */     //   82: aload_0
/*      */     //   83: getfield field_146297_k : Lnet/minecraft/client/Minecraft;
/*      */     //   86: new thaumcraft/client/gui/GuiResearchPage
/*      */     //   89: dup
/*      */     //   90: aload_0
/*      */     //   91: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   94: aconst_null
/*      */     //   95: aload_0
/*      */     //   96: getfield guiMapX : D
/*      */     //   99: aload_0
/*      */     //   100: getfield guiMapY : D
/*      */     //   103: invokespecial <init> : (Lthaumcraft/api/research/ResearchEntry;Lnet/minecraft/util/ResourceLocation;DD)V
/*      */     //   106: invokevirtual func_147108_a : (Lnet/minecraft/client/gui/GuiScreen;)V
/*      */     //   109: aload_0
/*      */     //   110: invokestatic currentTimeMillis : ()J
/*      */     //   113: ldc2_w 3000
/*      */     //   116: ladd
/*      */     //   117: putfield popuptime : J
/*      */     //   120: aload_0
/*      */     //   121: new net/minecraft/util/text/TextComponentTranslation
/*      */     //   124: dup
/*      */     //   125: ldc_w 'tc.research.popup'
/*      */     //   128: invokestatic func_74838_a : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   131: iconst_1
/*      */     //   132: anewarray java/lang/Object
/*      */     //   135: dup
/*      */     //   136: iconst_0
/*      */     //   137: new java/lang/StringBuilder
/*      */     //   140: dup
/*      */     //   141: invokespecial <init> : ()V
/*      */     //   144: ldc ''
/*      */     //   146: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   149: aload_0
/*      */     //   150: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   153: invokevirtual getLocalizedName : ()Ljava/lang/String;
/*      */     //   156: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   159: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   162: aastore
/*      */     //   163: invokespecial <init> : (Ljava/lang/String;[Ljava/lang/Object;)V
/*      */     //   166: invokevirtual func_150260_c : ()Ljava/lang/String;
/*      */     //   169: putfield popupmessage : Ljava/lang/String;
/*      */     //   172: goto -> 670
/*      */     //   175: aload_0
/*      */     //   176: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   179: ifnull -> 372
/*      */     //   182: aload_0
/*      */     //   183: getfield player : Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   186: iconst_1
/*      */     //   187: anewarray java/lang/String
/*      */     //   190: dup
/*      */     //   191: iconst_0
/*      */     //   192: aload_0
/*      */     //   193: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   196: invokevirtual getKey : ()Ljava/lang/String;
/*      */     //   199: aastore
/*      */     //   200: invokestatic knowsResearch : (Lnet/minecraft/entity/player/EntityPlayer;[Ljava/lang/String;)Z
/*      */     //   203: ifeq -> 372
/*      */     //   206: aload_0
/*      */     //   207: getfield player : Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   210: invokestatic getKnowledge : (Lnet/minecraft/entity/player/EntityPlayer;)Lthaumcraft/api/capabilities/IPlayerKnowledge;
/*      */     //   213: aload_0
/*      */     //   214: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   217: invokevirtual getKey : ()Ljava/lang/String;
/*      */     //   220: getstatic thaumcraft/api/capabilities/IPlayerKnowledge$EnumResearchFlag.RESEARCH : Lthaumcraft/api/capabilities/IPlayerKnowledge$EnumResearchFlag;
/*      */     //   223: invokeinterface clearResearchFlag : (Ljava/lang/String;Lthaumcraft/api/capabilities/IPlayerKnowledge$EnumResearchFlag;)Z
/*      */     //   228: pop
/*      */     //   229: aload_0
/*      */     //   230: getfield player : Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   233: invokestatic getKnowledge : (Lnet/minecraft/entity/player/EntityPlayer;)Lthaumcraft/api/capabilities/IPlayerKnowledge;
/*      */     //   236: aload_0
/*      */     //   237: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   240: invokevirtual getKey : ()Ljava/lang/String;
/*      */     //   243: getstatic thaumcraft/api/capabilities/IPlayerKnowledge$EnumResearchFlag.PAGE : Lthaumcraft/api/capabilities/IPlayerKnowledge$EnumResearchFlag;
/*      */     //   246: invokeinterface clearResearchFlag : (Ljava/lang/String;Lthaumcraft/api/capabilities/IPlayerKnowledge$EnumResearchFlag;)Z
/*      */     //   251: pop
/*      */     //   252: getstatic thaumcraft/common/lib/network/PacketHandler.INSTANCE : Lnet/minecraftforge/fml/common/network/simpleimpl/SimpleNetworkWrapper;
/*      */     //   255: new thaumcraft/common/lib/network/playerdata/PacketSyncResearchFlagsToServer
/*      */     //   258: dup
/*      */     //   259: aload_0
/*      */     //   260: getfield field_146297_k : Lnet/minecraft/client/Minecraft;
/*      */     //   263: getfield field_71439_g : Lnet/minecraft/client/entity/EntityPlayerSP;
/*      */     //   266: aload_0
/*      */     //   267: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   270: invokevirtual getKey : ()Ljava/lang/String;
/*      */     //   273: invokespecial <init> : (Lnet/minecraft/entity/player/EntityPlayer;Ljava/lang/String;)V
/*      */     //   276: invokevirtual sendToServer : (Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V
/*      */     //   279: aload_0
/*      */     //   280: getfield player : Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   283: invokestatic getKnowledge : (Lnet/minecraft/entity/player/EntityPlayer;)Lthaumcraft/api/capabilities/IPlayerKnowledge;
/*      */     //   286: aload_0
/*      */     //   287: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   290: invokevirtual getKey : ()Ljava/lang/String;
/*      */     //   293: invokeinterface getResearchStage : (Ljava/lang/String;)I
/*      */     //   298: istore #4
/*      */     //   300: iload #4
/*      */     //   302: iconst_1
/*      */     //   303: if_icmple -> 342
/*      */     //   306: iload #4
/*      */     //   308: aload_0
/*      */     //   309: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   312: invokevirtual getStages : ()[Lthaumcraft/api/research/ResearchStage;
/*      */     //   315: arraylength
/*      */     //   316: if_icmplt -> 342
/*      */     //   319: getstatic thaumcraft/common/lib/network/PacketHandler.INSTANCE : Lnet/minecraftforge/fml/common/network/simpleimpl/SimpleNetworkWrapper;
/*      */     //   322: new thaumcraft/common/lib/network/playerdata/PacketSyncProgressToServer
/*      */     //   325: dup
/*      */     //   326: aload_0
/*      */     //   327: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   330: invokevirtual getKey : ()Ljava/lang/String;
/*      */     //   333: iconst_0
/*      */     //   334: iconst_1
/*      */     //   335: iconst_0
/*      */     //   336: invokespecial <init> : (Ljava/lang/String;ZZZ)V
/*      */     //   339: invokevirtual sendToServer : (Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V
/*      */     //   342: aload_0
/*      */     //   343: getfield field_146297_k : Lnet/minecraft/client/Minecraft;
/*      */     //   346: new thaumcraft/client/gui/GuiResearchPage
/*      */     //   349: dup
/*      */     //   350: aload_0
/*      */     //   351: getfield currentHighlight : Lthaumcraft/api/research/ResearchEntry;
/*      */     //   354: aconst_null
/*      */     //   355: aload_0
/*      */     //   356: getfield guiMapX : D
/*      */     //   359: aload_0
/*      */     //   360: getfield guiMapY : D
/*      */     //   363: invokespecial <init> : (Lthaumcraft/api/research/ResearchEntry;Lnet/minecraft/util/ResourceLocation;DD)V
/*      */     //   366: invokevirtual func_147108_a : (Lnet/minecraft/client/gui/GuiScreen;)V
/*      */     //   369: goto -> 670
/*      */     //   372: getstatic thaumcraft/client/gui/GuiResearchBrowser.searching : Z
/*      */     //   375: ifeq -> 670
/*      */     //   378: iconst_0
/*      */     //   379: istore #4
/*      */     //   381: aload_0
/*      */     //   382: getfield searchResults : Ljava/util/ArrayList;
/*      */     //   385: invokevirtual iterator : ()Ljava/util/Iterator;
/*      */     //   388: astore #5
/*      */     //   390: aload #5
/*      */     //   392: invokeinterface hasNext : ()Z
/*      */     //   397: ifeq -> 670
/*      */     //   400: aload #5
/*      */     //   402: invokeinterface next : ()Ljava/lang/Object;
/*      */     //   407: checkcast org/apache/commons/lang3/tuple/Pair
/*      */     //   410: astore #6
/*      */     //   412: aload #6
/*      */     //   414: invokevirtual getRight : ()Ljava/lang/Object;
/*      */     //   417: checkcast thaumcraft/client/gui/GuiResearchBrowser$SearchResult
/*      */     //   420: astore #7
/*      */     //   422: iload_1
/*      */     //   423: bipush #22
/*      */     //   425: if_icmple -> 644
/*      */     //   428: iload_1
/*      */     //   429: bipush #18
/*      */     //   431: aload_0
/*      */     //   432: getfield screenX : I
/*      */     //   435: iadd
/*      */     //   436: if_icmpge -> 644
/*      */     //   439: iload_2
/*      */     //   440: bipush #32
/*      */     //   442: iload #4
/*      */     //   444: bipush #10
/*      */     //   446: imul
/*      */     //   447: iadd
/*      */     //   448: if_icmplt -> 644
/*      */     //   451: iload_2
/*      */     //   452: bipush #40
/*      */     //   454: iload #4
/*      */     //   456: bipush #10
/*      */     //   458: imul
/*      */     //   459: iadd
/*      */     //   460: if_icmpge -> 644
/*      */     //   463: aload_0
/*      */     //   464: getfield player : Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   467: iconst_1
/*      */     //   468: anewarray java/lang/String
/*      */     //   471: dup
/*      */     //   472: iconst_0
/*      */     //   473: aload #7
/*      */     //   475: getfield key : Ljava/lang/String;
/*      */     //   478: aastore
/*      */     //   479: invokestatic knowsResearch : (Lnet/minecraft/entity/player/EntityPlayer;[Ljava/lang/String;)Z
/*      */     //   482: ifeq -> 531
/*      */     //   485: aload #7
/*      */     //   487: getfield cat : Z
/*      */     //   490: ifne -> 531
/*      */     //   493: aload_0
/*      */     //   494: getfield field_146297_k : Lnet/minecraft/client/Minecraft;
/*      */     //   497: new thaumcraft/client/gui/GuiResearchPage
/*      */     //   500: dup
/*      */     //   501: aload #7
/*      */     //   503: getfield key : Ljava/lang/String;
/*      */     //   506: invokestatic getResearch : (Ljava/lang/String;)Lthaumcraft/api/research/ResearchEntry;
/*      */     //   509: aload #7
/*      */     //   511: getfield recipe : Lnet/minecraft/util/ResourceLocation;
/*      */     //   514: aload_0
/*      */     //   515: getfield guiMapX : D
/*      */     //   518: aload_0
/*      */     //   519: getfield guiMapY : D
/*      */     //   522: invokespecial <init> : (Lthaumcraft/api/research/ResearchEntry;Lnet/minecraft/util/ResourceLocation;DD)V
/*      */     //   525: invokevirtual func_147108_a : (Lnet/minecraft/client/gui/GuiScreen;)V
/*      */     //   528: goto -> 670
/*      */     //   531: aload_0
/*      */     //   532: getfield categoriesTC : Ljava/util/ArrayList;
/*      */     //   535: aload #7
/*      */     //   537: getfield key : Ljava/lang/String;
/*      */     //   540: invokevirtual contains : (Ljava/lang/Object;)Z
/*      */     //   543: ifne -> 561
/*      */     //   546: aload_0
/*      */     //   547: getfield categoriesOther : Ljava/util/ArrayList;
/*      */     //   550: aload #7
/*      */     //   552: getfield key : Ljava/lang/String;
/*      */     //   555: invokevirtual contains : (Ljava/lang/Object;)Z
/*      */     //   558: ifeq -> 644
/*      */     //   561: iconst_0
/*      */     //   562: putstatic thaumcraft/client/gui/GuiResearchBrowser.searching : Z
/*      */     //   565: aload_0
/*      */     //   566: getfield searchField : Lnet/minecraft/client/gui/GuiTextField;
/*      */     //   569: iconst_0
/*      */     //   570: invokevirtual func_146189_e : (Z)V
/*      */     //   573: aload_0
/*      */     //   574: getfield searchField : Lnet/minecraft/client/gui/GuiTextField;
/*      */     //   577: iconst_1
/*      */     //   578: invokevirtual func_146205_d : (Z)V
/*      */     //   581: aload_0
/*      */     //   582: getfield searchField : Lnet/minecraft/client/gui/GuiTextField;
/*      */     //   585: iconst_0
/*      */     //   586: invokevirtual func_146195_b : (Z)V
/*      */     //   589: aload_0
/*      */     //   590: pop
/*      */     //   591: aload #7
/*      */     //   593: getfield key : Ljava/lang/String;
/*      */     //   596: putstatic thaumcraft/client/gui/GuiResearchBrowser.selectedCategory : Ljava/lang/String;
/*      */     //   599: aload_0
/*      */     //   600: invokevirtual updateResearch : ()V
/*      */     //   603: aload_0
/*      */     //   604: aload_0
/*      */     //   605: getstatic thaumcraft/client/gui/GuiResearchBrowser.guiBoundsLeft : I
/*      */     //   608: getstatic thaumcraft/client/gui/GuiResearchBrowser.guiBoundsRight : I
/*      */     //   611: iadd
/*      */     //   612: iconst_2
/*      */     //   613: idiv
/*      */     //   614: i2d
/*      */     //   615: dup2_x1
/*      */     //   616: putfield tempMapX : D
/*      */     //   619: putfield guiMapX : D
/*      */     //   622: aload_0
/*      */     //   623: aload_0
/*      */     //   624: getstatic thaumcraft/client/gui/GuiResearchBrowser.guiBoundsBottom : I
/*      */     //   627: getstatic thaumcraft/client/gui/GuiResearchBrowser.guiBoundsTop : I
/*      */     //   630: iadd
/*      */     //   631: iconst_2
/*      */     //   632: idiv
/*      */     //   633: i2d
/*      */     //   634: dup2_x1
/*      */     //   635: putfield tempMapY : D
/*      */     //   638: putfield guiMapY : D
/*      */     //   641: goto -> 670
/*      */     //   644: iinc #4, 1
/*      */     //   647: bipush #32
/*      */     //   649: iload #4
/*      */     //   651: iconst_1
/*      */     //   652: iadd
/*      */     //   653: bipush #10
/*      */     //   655: imul
/*      */     //   656: iadd
/*      */     //   657: aload_0
/*      */     //   658: getfield screenY : I
/*      */     //   661: if_icmple -> 667
/*      */     //   664: goto -> 670
/*      */     //   667: goto -> 390
/*      */     //   670: aload_0
/*      */     //   671: iload_1
/*      */     //   672: iload_2
/*      */     //   673: iload_3
/*      */     //   674: invokespecial func_73864_a : (III)V
/*      */     //   677: goto -> 682
/*      */     //   680: astore #4
/*      */     //   682: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #956	-> 0
/*      */     //   #957	-> 9
/*      */     //   #958	-> 51
/*      */     //   #959	-> 57
/*      */     //   #960	-> 61
/*      */     //   #961	-> 82
/*      */     //   #962	-> 109
/*      */     //   #963	-> 120
/*      */     //   #964	-> 153
/*      */     //   #966	-> 175
/*      */     //   #967	-> 206
/*      */     //   #968	-> 229
/*      */     //   #969	-> 252
/*      */     //   #970	-> 279
/*      */     //   #971	-> 300
/*      */     //   #972	-> 319
/*      */     //   #974	-> 342
/*      */     //   #975	-> 369
/*      */     //   #976	-> 372
/*      */     //   #977	-> 378
/*      */     //   #978	-> 381
/*      */     //   #979	-> 412
/*      */     //   #980	-> 422
/*      */     //   #981	-> 463
/*      */     //   #982	-> 493
/*      */     //   #983	-> 528
/*      */     //   #985	-> 531
/*      */     //   #986	-> 561
/*      */     //   #987	-> 565
/*      */     //   #988	-> 573
/*      */     //   #989	-> 581
/*      */     //   #990	-> 589
/*      */     //   #991	-> 599
/*      */     //   #992	-> 603
/*      */     //   #993	-> 622
/*      */     //   #994	-> 641
/*      */     //   #997	-> 644
/*      */     //   #998	-> 647
/*      */     //   #999	-> 664
/*      */     //   #1001	-> 667
/*      */     //   #1005	-> 670
/*      */     //   #1006	-> 677
/*      */     //   #1007	-> 682
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   300	69	4	stage	I
/*      */     //   422	245	7	sr	Lthaumcraft/client/gui/GuiResearchBrowser$SearchResult;
/*      */     //   412	255	6	p	Lorg/apache/commons/lang3/tuple/Pair;
/*      */     //   381	289	4	q	I
/*      */     //   0	683	0	this	Lthaumcraft/client/gui/GuiResearchBrowser;
/*      */     //   0	683	1	mx	I
/*      */     //   0	683	2	my	I
/*      */     //   0	683	3	par3	I
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   670	677	680	java/io/IOException }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_146284_a(GuiButton button) throws IOException {
/* 1013 */     if (button.field_146127_k == 2) {
/* 1014 */       selectedCategory = "";
/* 1015 */       searching = true;
/* 1016 */       this.searchField.func_146189_e(true);
/* 1017 */       this.searchField.func_146205_d(false);
/* 1018 */       this.searchField.func_146195_b(true);
/* 1019 */       this.searchField.func_146180_a("");
/* 1020 */       updateSearch();
/*      */     } 
/*      */     
/* 1023 */     if (button.field_146127_k == 3) { this; if (catScrollPos > 0) {
/* 1024 */         catScrollPos--;
/* 1025 */         updateResearch();
/*      */       }  }
/*      */     
/* 1028 */     this; this; if (button.field_146127_k == 4 && catScrollPos < catScrollMax) {
/* 1029 */       catScrollPos++;
/* 1030 */       updateResearch();
/*      */     } 
/*      */     
/* 1033 */     this; if (button.field_146127_k >= 20 && button instanceof GuiCategoryButton && ((GuiCategoryButton)button).key != selectedCategory) {
/*      */       
/* 1035 */       searching = false;
/* 1036 */       this.searchField.func_146189_e(false);
/* 1037 */       this.searchField.func_146205_d(true);
/* 1038 */       this.searchField.func_146195_b(false);
/*      */       
/* 1040 */       this; selectedCategory = ((GuiCategoryButton)button).key;
/* 1041 */       updateResearch();
/* 1042 */       this.guiMapX = this.tempMapX = ((guiBoundsLeft + guiBoundsRight) / 2);
/* 1043 */       this.guiMapY = this.tempMapY = ((guiBoundsBottom + guiBoundsTop) / 2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1049 */   private void playButtonClick() { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.clack, 0.4F, 1.0F); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1059 */   public boolean func_73868_f() { return false; }
/*      */   
/*      */   private void drawLine(int x, int y, int x2, int y2, float r, float g, float b, int locX, int locY, float zMod, boolean arrow, boolean flipped) {
/*      */     int yy, xx, ym, xm, yd, xd;
/* 1063 */     float zt = this.field_73735_i;
/*      */     
/* 1065 */     this.field_73735_i += zMod;
/*      */     
/* 1067 */     boolean bigCorner = false;
/*      */ 
/*      */ 
/*      */     
/* 1071 */     if (flipped) {
/* 1072 */       xd = Math.abs(x2 - x);
/* 1073 */       yd = Math.abs(y2 - y);
/* 1074 */       xm = (xd == 0) ? 0 : ((x2 - x > 0) ? -1 : 1);
/* 1075 */       ym = (yd == 0) ? 0 : ((y2 - y > 0) ? -1 : 1);
/* 1076 */       if (xd > 1 && yd > 1) {
/* 1077 */         bigCorner = true;
/*      */       }
/* 1079 */       xx = x2 * 24 - 4 - locX + this.startX;
/* 1080 */       yy = y2 * 24 - 4 - locY + this.startY;
/*      */     } else {
/* 1082 */       xd = Math.abs(x - x2);
/* 1083 */       yd = Math.abs(y - y2);
/* 1084 */       xm = (xd == 0) ? 0 : ((x - x2 > 0) ? -1 : 1);
/* 1085 */       ym = (yd == 0) ? 0 : ((y - y2 > 0) ? -1 : 1);
/* 1086 */       if (xd > 1 && yd > 1) {
/* 1087 */         bigCorner = true;
/*      */       }
/* 1089 */       xx = x * 24 - 4 - locX + this.startX;
/* 1090 */       yy = y * 24 - 4 - locY + this.startY;
/*      */     } 
/*      */     
/* 1093 */     GL11.glPushMatrix();
/* 1094 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 1095 */     GL11.glEnable(3042);
/* 1096 */     GL11.glBlendFunc(770, 771);
/* 1097 */     GL11.glColor4f(r, g, b, 1.0F);
/*      */     
/* 1099 */     if (arrow) {
/* 1100 */       if (flipped)
/* 1101 */       { int xx3 = x * 24 - 8 - locX + this.startX;
/* 1102 */         int yy3 = y * 24 - 8 - locY + this.startY;
/* 1103 */         if (xm < 0) { func_73729_b(xx3, yy3, 160, 112, 32, 32); }
/* 1104 */         else if (xm > 0) { func_73729_b(xx3, yy3, 128, 112, 32, 32); }
/* 1105 */         else if (ym > 0) { func_73729_b(xx3, yy3, 64, 112, 32, 32); }
/* 1106 */         else if (ym < 0) { func_73729_b(xx3, yy3, 96, 112, 32, 32); }
/*      */          }
/* 1108 */       else if (ym < 0) { func_73729_b(xx - 4, yy - 4, 64, 112, 32, 32); }
/* 1109 */       else if (ym > 0) { func_73729_b(xx - 4, yy - 4, 96, 112, 32, 32); }
/* 1110 */       else if (xm > 0) { func_73729_b(xx - 4, yy - 4, 160, 112, 32, 32); }
/* 1111 */       else if (xm < 0) { func_73729_b(xx - 4, yy - 4, 128, 112, 32, 32); }
/*      */     
/*      */     }
/*      */     
/* 1115 */     int v = 1;
/* 1116 */     int h = 0;
/* 1117 */     for (; v < yd - (bigCorner ? 1 : 0); v++) {
/* 1118 */       func_73729_b(xx + xm * 24 * h, yy + ym * 24 * v, 0, 228, 24, 24);
/*      */     }
/*      */     
/* 1121 */     if (bigCorner) {
/* 1122 */       if (xm < 0 && ym > 0) func_73729_b(xx + xm * 24 * h - 24, yy + ym * 24 * v, 0, 180, 48, 48); 
/* 1123 */       if (xm > 0 && ym > 0) func_73729_b(xx + xm * 24 * h, yy + ym * 24 * v, 48, 180, 48, 48); 
/* 1124 */       if (xm < 0 && ym < 0) func_73729_b(xx + xm * 24 * h - 24, yy + ym * 24 * v - 24, 96, 180, 48, 48); 
/* 1125 */       if (xm > 0 && ym < 0) func_73729_b(xx + xm * 24 * h, yy + ym * 24 * v - 24, 144, 180, 48, 48); 
/*      */     } else {
/* 1127 */       if (xm < 0 && ym > 0) func_73729_b(xx + xm * 24 * h, yy + ym * 24 * v, 48, 228, 24, 24); 
/* 1128 */       if (xm > 0 && ym > 0) func_73729_b(xx + xm * 24 * h, yy + ym * 24 * v, 72, 228, 24, 24); 
/* 1129 */       if (xm < 0 && ym < 0) func_73729_b(xx + xm * 24 * h, yy + ym * 24 * v, 96, 228, 24, 24); 
/* 1130 */       if (xm > 0 && ym < 0) func_73729_b(xx + xm * 24 * h, yy + ym * 24 * v, 120, 228, 24, 24);
/*      */     
/*      */     } 
/* 1133 */     v += (bigCorner ? 1 : 0);
/* 1134 */     h += (bigCorner ? 2 : 1);
/* 1135 */     for (; h < xd; h++) {
/* 1136 */       func_73729_b(xx + xm * 24 * h, yy + ym * 24 * v, 24, 228, 24, 24);
/*      */     }
/* 1138 */     GL11.glBlendFunc(770, 771);
/* 1139 */     GL11.glDisable(3042);
/* 1140 */     GL11.glAlphaFunc(516, 0.1F);
/* 1141 */     GL11.glPopMatrix();
/*      */     
/* 1143 */     this.field_73735_i = zt;
/*      */   }
/*      */   
/*      */   public static void drawForbidden(double x, double y) {
/* 1147 */     int count = (FMLClientHandler.instance().getClient()).field_71439_g.field_70173_aa;
/* 1148 */     GL11.glPushMatrix();
/* 1149 */     GL11.glTranslated(x, y, 0.0D);
/* 1150 */     UtilsFX.renderQuadCentered(UtilsFX.nodeTexture, 32, 32, 160 + count % 32, 90.0F, 0.33F, 0.0F, 0.44F, 220, 1, 0.9F);
/* 1151 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1152 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public void drawTexturedModalRectWithDoubles(float xCoord, float yCoord, double minU, double minV, double maxU, double maxV) {
/* 1157 */     float f2 = 0.00390625F;
/* 1158 */     float f3 = 0.00390625F;
/* 1159 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1160 */     BufferBuilder BufferBuilder = tessellator.func_178180_c();
/* 1161 */     BufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
/* 1162 */     BufferBuilder.func_181662_b((xCoord + 0.0F), yCoord + maxV, this.field_73735_i).func_187315_a((minU + 0.0D) * f2, (minV + maxV) * f3).func_181675_d();
/* 1163 */     BufferBuilder.func_181662_b(xCoord + maxU, yCoord + maxV, this.field_73735_i).func_187315_a((minU + maxU) * f2, (minV + maxV) * f3).func_181675_d();
/* 1164 */     BufferBuilder.func_181662_b(xCoord + maxU, (yCoord + 0.0F), this.field_73735_i).func_187315_a((minU + maxU) * f2, (minV + 0.0D) * f3).func_181675_d();
/* 1165 */     BufferBuilder.func_181662_b((xCoord + 0.0F), (yCoord + 0.0F), this.field_73735_i).func_187315_a((minU + 0.0D) * f2, (minV + 0.0D) * f3).func_181675_d();
/* 1166 */     tessellator.func_78381_a();
/*      */   }
/*      */   
/*      */   private class GuiCategoryButton
/*      */     extends GuiButton {
/*      */     ResearchCategory rc;
/*      */     String key;
/*      */     boolean flip;
/*      */     int completion;
/*      */     
/*      */     public GuiCategoryButton(ResearchCategory rc, String key, boolean flip, int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_, int completion) {
/* 1177 */       super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
/* 1178 */       this.rc = rc;
/* 1179 */       this.key = key;
/* 1180 */       this.flip = flip;
/* 1181 */       this.completion = completion;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1186 */     public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) { return (this.field_146124_l && this.field_146125_m && mouseX >= this.field_146128_h && mouseY >= this.field_146129_i + GuiResearchBrowser.this.addonShift && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g + GuiResearchBrowser.this.addonShift); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
/* 1192 */       if (this.field_146125_m) {
/*      */         
/* 1194 */         this.field_146123_n = (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i + GuiResearchBrowser.this.addonShift && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g + GuiResearchBrowser.this.addonShift);
/* 1195 */         GlStateManager.func_179147_l();
/* 1196 */         GlStateManager.func_179120_a(770, 771, 1, 0);
/* 1197 */         GlStateManager.func_179112_b(770, 771);
/* 1198 */         mc.field_71446_o.func_110577_a(GuiResearchBrowser.this.tx1);
/* 1199 */         GL11.glPushMatrix();
/* 1200 */         if (!GuiResearchBrowser.selectedCategory.equals(this.key)) {
/* 1201 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */         } else {
/* 1203 */           GL11.glColor4f(0.6F, 1.0F, 1.0F, 1.0F);
/*      */         } 
/* 1205 */         func_73729_b(this.field_146128_h - 3, this.field_146129_i - 3 + GuiResearchBrowser.this.addonShift, 13, 13, 22, 22);
/*      */ 
/*      */ 
/*      */         
/* 1209 */         GL11.glPopMatrix();
/* 1210 */         GL11.glPushMatrix();
/* 1211 */         mc.field_71446_o.func_110577_a(this.rc.icon);
/* 1212 */         if (GuiResearchBrowser.selectedCategory.equals(this.key) || this.field_146123_n) {
/* 1213 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */         } else {
/* 1215 */           GL11.glColor4f(0.66F, 0.66F, 0.66F, 0.8F);
/*      */         } 
/* 1217 */         UtilsFX.drawTexturedQuadFull(this.field_146128_h, (this.field_146129_i + GuiResearchBrowser.this.addonShift), -80.0D);
/* 1218 */         GL11.glPopMatrix();
/*      */         
/* 1220 */         mc.field_71446_o.func_110577_a(GuiResearchBrowser.this.tx1);
/*      */         
/* 1222 */         boolean nr = false;
/* 1223 */         boolean np = false;
/*      */         
/* 1225 */         for (String rk : this.rc.research.keySet()) {
/* 1226 */           if (ThaumcraftCapabilities.knowsResearch(GuiResearchBrowser.this.player, new String[] { rk })) {
/* 1227 */             if (!nr && ThaumcraftCapabilities.getKnowledge(GuiResearchBrowser.this.player).hasResearchFlag(rk, IPlayerKnowledge.EnumResearchFlag.RESEARCH)) {
/* 1228 */               nr = true;
/*      */             }
/* 1230 */             if (!np && ThaumcraftCapabilities.getKnowledge(GuiResearchBrowser.this.player).hasResearchFlag(rk, IPlayerKnowledge.EnumResearchFlag.PAGE)) {
/* 1231 */               np = true;
/*      */             }
/* 1233 */             if (nr && np)
/*      */               break; 
/*      */           } 
/* 1236 */         }  if (nr) {
/* 1237 */           GL11.glPushMatrix();
/* 1238 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.7F);
/* 1239 */           GL11.glTranslated((this.field_146128_h - 2), (this.field_146129_i + GuiResearchBrowser.this.addonShift - 2), 0.0D);
/* 1240 */           GL11.glScaled(0.25D, 0.25D, 1.0D);
/* 1241 */           func_73729_b(0, 0, 176, 16, 32, 32);
/* 1242 */           GL11.glPopMatrix();
/*      */         } 
/* 1244 */         if (np) {
/* 1245 */           GL11.glPushMatrix();
/* 1246 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.7F);
/* 1247 */           GL11.glTranslated((this.field_146128_h - 2), (this.field_146129_i + GuiResearchBrowser.this.addonShift + 9), 0.0D);
/* 1248 */           GL11.glScaled(0.25D, 0.25D, 1.0D);
/* 1249 */           func_73729_b(0, 0, 208, 16, 32, 32);
/* 1250 */           GL11.glPopMatrix();
/*      */         } 
/*      */         
/* 1253 */         if (this.field_146123_n) {
/* 1254 */           String dp = this.field_146126_j + " (" + this.completion + "%)";
/* 1255 */           func_73731_b(mc.field_71466_p, dp, !this.flip ? (this.field_146128_h + 22) : (GuiResearchBrowser.this
/* 1256 */               .screenX + 9 - mc.field_71466_p.func_78256_a(dp)), this.field_146129_i + 4 + GuiResearchBrowser.this.addonShift, 16777215);
/*      */           
/* 1258 */           int t = 9;
/* 1259 */           if (nr) {
/* 1260 */             func_73731_b(mc.field_71466_p, I18n.func_74838_a("tc.research.newresearch"), !this.flip ? (this.field_146128_h + 22) : (GuiResearchBrowser.this
/* 1261 */                 .screenX + 9 - mc.field_71466_p.func_78256_a(I18n.func_74838_a("tc.research.newresearch"))), this.field_146129_i + 4 + t + GuiResearchBrowser.this.addonShift, 16777215);
/*      */             
/* 1263 */             t += 9;
/*      */           } 
/* 1265 */           if (np) {
/* 1266 */             func_73731_b(mc.field_71466_p, I18n.func_74838_a("tc.research.newpage"), !this.flip ? (this.field_146128_h + 22) : (GuiResearchBrowser.this
/* 1267 */                 .screenX + 9 - mc.field_71466_p.func_78256_a(I18n.func_74838_a("tc.research.newpage"))), this.field_146129_i + 4 + t + GuiResearchBrowser.this.addonShift, 16777215);
/*      */           }
/*      */         } 
/* 1270 */         func_146119_b(mc, mouseX, mouseY);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private class GuiScrollButton
/*      */     extends GuiButton {
/*      */     boolean flip;
/*      */     
/*      */     public GuiScrollButton(boolean flip, int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
/* 1280 */       super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
/* 1281 */       this.flip = flip;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
/* 1287 */       if (this.field_146125_m) {
/*      */         
/* 1289 */         this.field_146123_n = (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
/* 1290 */         GlStateManager.func_179147_l();
/* 1291 */         GlStateManager.func_179120_a(770, 771, 1, 0);
/* 1292 */         GlStateManager.func_179112_b(770, 771);
/* 1293 */         mc.field_71446_o.func_110577_a(GuiResearchBrowser.this.tx1);
/* 1294 */         GL11.glPushMatrix();
/* 1295 */         if (this.field_146123_n) {
/* 1296 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */         } else {
/* 1298 */           GL11.glColor4f(0.7F, 0.7F, 0.7F, 1.0F);
/*      */         } 
/* 1300 */         func_73729_b(this.field_146128_h, this.field_146129_i, 51, this.flip ? 71 : 55, 10, 11);
/* 1301 */         GL11.glPopMatrix();
/* 1302 */         func_146119_b(mc, mouseX, mouseY);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private class GuiSearchButton
/*      */     extends GuiButton
/*      */   {
/* 1310 */     public GuiSearchButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) { super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
/* 1317 */       if (this.field_146125_m) {
/*      */         
/* 1319 */         this.field_146123_n = (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
/* 1320 */         GlStateManager.func_179147_l();
/* 1321 */         GlStateManager.func_179120_a(770, 771, 1, 0);
/* 1322 */         GlStateManager.func_179112_b(770, 771);
/* 1323 */         mc.field_71446_o.func_110577_a(GuiResearchBrowser.this.tx1);
/* 1324 */         GL11.glPushMatrix();
/* 1325 */         if (this.field_146123_n) {
/* 1326 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */         } else {
/* 1328 */           GL11.glColor4f(0.8F, 0.8F, 0.8F, 1.0F);
/* 1329 */         }  func_73729_b(this.field_146128_h, this.field_146129_i, 160, 16, 16, 16);
/* 1330 */         GL11.glPopMatrix();
/* 1331 */         if (this.field_146123_n) {
/* 1332 */           func_73731_b(mc.field_71466_p, this.field_146126_j, this.field_146128_h + 19, this.field_146129_i + 4, 16777215);
/*      */         }
/* 1334 */         func_146119_b(mc, mouseX, mouseY);
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiResearchBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */