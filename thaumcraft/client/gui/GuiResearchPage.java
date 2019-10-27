/*      */ package thaumcraft.client.gui;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*      */ import net.minecraft.client.renderer.BufferBuilder;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.OpenGlHelper;
/*      */ import net.minecraft.client.renderer.RenderHelper;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import net.minecraft.client.util.ITooltipFlag;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.CraftingManager;
/*      */ import net.minecraft.item.crafting.IRecipe;
/*      */ import net.minecraft.item.crafting.Ingredient;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.NonNullList;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.util.text.TextComponentTranslation;
/*      */ import net.minecraft.util.text.TextFormatting;
/*      */ import net.minecraft.util.text.translation.I18n;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.WorldType;
/*      */ import net.minecraft.world.biome.Biome;
/*      */ import net.minecraftforge.common.crafting.IShapedRecipe;
/*      */ import net.minecraftforge.fml.relauncher.Side;
/*      */ import net.minecraftforge.fml.relauncher.SideOnly;
/*      */ import net.minecraftforge.oredict.OreDictionary;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import thaumcraft.api.ThaumcraftApi;
/*      */ import thaumcraft.api.ThaumcraftApiHelper;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*      */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*      */ import thaumcraft.api.crafting.CrucibleRecipe;
/*      */ import thaumcraft.api.crafting.IArcaneRecipe;
/*      */ import thaumcraft.api.crafting.InfusionRecipe;
/*      */ import thaumcraft.api.crafting.Part;
/*      */ import thaumcraft.api.crafting.RecipeMisc;
/*      */ import thaumcraft.api.crafting.ShapedArcaneRecipe;
/*      */ import thaumcraft.api.crafting.ShapelessArcaneRecipe;
/*      */ import thaumcraft.api.internal.CommonInternals;
/*      */ import thaumcraft.api.research.ResearchAddendum;
/*      */ import thaumcraft.api.research.ResearchCategories;
/*      */ import thaumcraft.api.research.ResearchCategory;
/*      */ import thaumcraft.api.research.ResearchEntry;
/*      */ import thaumcraft.api.research.ResearchStage;
/*      */ import thaumcraft.client.lib.UtilsFX;
/*      */ import thaumcraft.client.lib.events.HudHandler;
/*      */ import thaumcraft.common.config.ConfigRecipes;
/*      */ import thaumcraft.common.lib.SoundsTC;
/*      */ import thaumcraft.common.lib.crafting.InfusionEnchantmentRecipe;
/*      */ import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;
/*      */ import thaumcraft.common.lib.crafting.Matrix;
/*      */ import thaumcraft.common.lib.network.PacketHandler;
/*      */ import thaumcraft.common.lib.network.playerdata.PacketSyncProgressToServer;
/*      */ import thaumcraft.common.lib.utils.InventoryUtils;
/*      */ import thaumcraft.common.lib.utils.PosXY;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */ public class GuiResearchPage
/*      */   extends GuiScreen
/*      */ {
/*  101 */   public static LinkedList<ResourceLocation> history = new LinkedList();
/*      */   protected int paneWidth;
/*  103 */   protected int paneHeight; protected double guiMapX; protected double guiMapY; protected int mouseX; protected int mouseY; private ResearchEntry research; private int currentStage; int lastStage; boolean hold; private int page; private int maxPages; private int maxAspectPages; AspectList knownPlayerAspects; IPlayerKnowledge playerKnowledge; int rhash; float transX; float transY; float rotX; float rotY; float rotZ; long lastCheck; float pt; ResourceLocation tex1; ResourceLocation tex2; ResourceLocation tex3; ResourceLocation tex4; ResourceLocation dummyResearch; ResourceLocation dummyMap; ResourceLocation dummyFlask; ResourceLocation dummyChest; int hrx; int hry; static ResourceLocation shownRecipe; int recipePage; int recipePageMax; private long lastCycle; private boolean showingAspects; private boolean showingKnowledge; public void func_73866_w_() { this.rotX = 25.0F; this.rotY = -45.0F; } public void func_73863_a(int par1, int par2, float par3) { this.hasRecipePages = false; long nano = System.nanoTime(); if (nano > this.lastCheck) { parsePages(); if (this.hold) { this.lastCheck = nano + 250000000L; } else { this.lastCheck = nano + 2000000000L; }  if (this.currentStage > this.lastStage) this.hold = false;  }  this.pt = par3; func_146276_q_(); genResearchBackground(par1, par2, par3); int sw = (this.field_146294_l - this.paneWidth) / 2; int sh = (this.field_146295_m - this.paneHeight) / 2; if (!history.isEmpty()) { int mx = par1 - sw + 118; int my = par2 - sh + 190; if (mx >= 0 && my >= 0 && mx < 20 && my < 12) this.field_146297_k.field_71466_p.func_175063_a(I18n.func_74838_a("recipe.return"), par1, par2, 16777215);  }  } protected void genResearchBackground(int par1, int par2, float par3) { int sw = (this.field_146294_l - this.paneWidth) / 2; int sh = (this.field_146295_m - this.paneHeight) / 2; float var10 = (this.field_146294_l - this.paneWidth * 1.3F) / 2.0F; float var11 = (this.field_146295_m - this.paneHeight * 1.3F) / 2.0F; GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GL11.glPushMatrix(); GL11.glTranslatef(var10, var11, 0.0F); GL11.glScalef(1.3F, 1.3F, 1.0F); func_73729_b(0, 0, 0, 0, this.paneWidth, this.paneHeight); GL11.glPopMatrix(); this.reference.clear(); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); int current = 0; for (int a = 0; a < this.pages.size(); a++) { if ((current == this.page || current == this.page + 1) && current < this.maxPages) drawPage((Page)this.pages.get(a), current % 2, sw, sh - 10, par1, par2);  current++; if (current > this.page + 1) break;  }  GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); float bob = MathHelper.func_76126_a(this.field_146297_k.field_71439_g.field_70173_aa / 3.0F) * 0.2F + 0.1F; if (!history.isEmpty()) drawTexturedModalRectScaled(sw + 118, sh + 190, 38, 202, 20, 12, bob);  if (this.page > 0 && shownRecipe == null) drawTexturedModalRectScaled(sw - 16, sh + 190, 0, 184, 12, 8, bob);  if (this.page < this.maxPages - 2 && shownRecipe == null) drawTexturedModalRectScaled(sw + 262, sh + 190, 12, 184, 12, 8, bob);  if (this.tipText != null) { UtilsFX.drawCustomTooltip(this, this.field_146297_k.field_71466_p, this.tipText, par1, par2 + 12, 11); this.tipText = null; }  } private static int aspectsPage = 0; LinkedHashMap<ResourceLocation, ArrayList> recipeLists; LinkedHashMap<ResourceLocation, ArrayList> recipeOutputs; LinkedHashMap<ResourceLocation, ArrayList> drilldownLists; boolean hasRecipePages; boolean renderingCompound; private void drawPage(Page pageParm, int side, int x, int y, int mx, int my) { if (this.lastCycle < System.currentTimeMillis()) { this.cycle++; this.lastCycle = System.currentTimeMillis() + 1000L; if (cycleMultiblockLines && this.blockAccess != null) this.blockAccess.sliceLine++;  }  GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); if (this.page == 0 && side == 0) { func_73729_b(x + 4, y - 7, 24, 184, 96, 4); func_73729_b(x + 4, y + 10, 24, 184, 96, 4); int offset = this.field_146297_k.field_71466_p.func_78256_a(this.research.getLocalizedName()); this; if (offset <= 140) { this; this.field_146297_k.field_71466_p.func_78276_b(this.research.getLocalizedName(), x - 15 + '' / '\002' - offset / 2, y, 2105376); } else { this; float vv = 140.0F / offset; GL11.glPushMatrix(); this; GL11.glTranslatef((x - 15 + '' / '\002') - (offset / 2) * vv, y + 1.0F * vv, 0.0F); GL11.glScalef(vv, vv, vv); this.field_146297_k.field_71466_p.func_78276_b(this.research.getLocalizedName(), 0, 0, 2105376); GL11.glPopMatrix(); }  y += 28; }  GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); for (Object content : pageParm.contents) { if (content instanceof String) { GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); String ss = ((String)content).replace("~B", ""); this.field_146297_k.field_71466_p.func_78276_b(ss, x - 15 + side * 152, y - 6, 0); y += this.field_146297_k.field_71466_p.field_78288_b; if (((String)content).endsWith("~B")) y = (int)(y + this.field_146297_k.field_71466_p.field_78288_b * 0.66D);  continue; }  if (content instanceof PageImage) { PageImage pi = (PageImage)content; GL11.glPushMatrix(); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(pi.loc); this; int pad = (140 - pi.aw) / 2; GL11.glTranslatef((x - 15 + side * 152 + pad), (y - 5), 0.0F); GL11.glScalef(pi.scale, pi.scale, pi.scale); func_73729_b(0, 0, pi.x, pi.y, pi.w, pi.h); GL11.glPopMatrix(); y += pi.ah + 2; }  }  GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); if (this.playerKnowledge.isResearchComplete("FIRSTSTEPS")) { y = (this.field_146295_m - this.paneHeight) / 2 + 9; this.field_146297_k.field_71446_o.func_110577_a(this.tex1); int le = mouseInside(x - 48, y, 25, 16, mx, my) ? 0 : 3; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); drawPopupAt(x - 48, y, 25, 16, mx, my, "tc.aspect.name"); func_73729_b(x - 48 + le, y, 76, 232, 24 - le, 16); func_73729_b(x - 28, y, 100, 232, 4, 16); }  if (this.playerKnowledge.isResearchComplete("KNOWLEDGETYPES") && !this.research.getKey().equals("KNOWLEDGETYPES")) { y = (this.field_146295_m - this.paneHeight) / 2 + 32; this.field_146297_k.field_71446_o.func_110577_a(this.tex1); int le = mouseInside(x - 48, y, 25, 16, mx, my) ? 0 : 3; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); drawPopupAt(x - 48, y, 25, 16, mx, my, "tc.knowledge.name"); func_73729_b(x - 49 + le, y, 44, 232, 24 - le, 16); func_73729_b(x - 29, y, 100, 232, 4, 16); }  ResearchStage stage = this.research.getStages()[this.currentStage]; if (stage.getRecipes() != null) drawRecipeBookmarks(x, mx, my, stage);  if (this.page == 0 && side == 0 && !this.isComplete) drawRequirements(x, mx, my, stage);  if (this.playerKnowledge.isResearchComplete("KNOWLEDGETYPES") && this.research.getKey().equals("KNOWLEDGETYPES")) { this; drawKnowledges(x, (this.field_146295_m - this.paneHeight) / 2 - 16 + 210, mx, my, true); }  this.renderingCompound = false; if (this.showingAspects) { drawAspectsInsert(mx, my); } else if (this.showingKnowledge) { drawKnowledgesInsert(mx, my); } else if (shownRecipe != null) { drawRecipe(mx, my); } else if (stage.getWarp() > 0 && !this.isComplete) { int warp = stage.getWarp(); if (warp > 5) warp = 5;  GuiResearchBrowser.drawForbidden((x - 57), (y - 40)); String s = I18n.func_74838_a("tc.forbidden.level." + warp); this.field_146297_k.field_71466_p.func_78276_b(s, x - 56 - this.field_146297_k.field_71466_p.func_78256_a(s) / 2, y - 43, 11180543); String text = I18n.func_74838_a("tc.warp.warn"); drawPopupAt(x - 67, y - 50, 20, 20, mx, my, text.replaceAll("%n", s)); }  } private void drawKnowledgesInsert(int mx, int my) { this.allowWithPagePopup = true; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(this.tex4); int x = (this.field_146294_l - 256) / 2; int y = (this.field_146295_m - 256) / 2; GlStateManager.func_179097_i(); func_73729_b(x, y, 0, 0, 255, 255); GlStateManager.func_179126_j(); GL11.glPushMatrix(); drawKnowledges(x + 60, (this.field_146295_m - this.paneHeight) / 2 + 75, mx, my, false); GL11.glPopMatrix(); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); this.allowWithPagePopup = false; } private void drawKnowledges(int x, int y, int mx, int my, boolean inpage) { y -= 18; boolean drewSomething = false; int amt = 0; int par = 0; int tc = 0; int ka = ResearchCategories.researchCategories.values().size(); for (IPlayerKnowledge.EnumKnowledgeType type : IPlayerKnowledge.EnumKnowledgeType.values()) { int fc = 0; int hs = (int)(164.0F / ka); boolean b = false; for (ResearchCategory category : ResearchCategories.researchCategories.values()) { if (!type.hasFields() && category != null) continue;  amt = this.playerKnowledge.getKnowledge(type, category); par = this.playerKnowledge.getKnowledgeRaw(type, category) % type.getProgression(); if (amt > 0 || par > 0) { drewSomething = true; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); GL11.glPushMatrix(); this.field_146297_k.field_71446_o.func_110577_a(HudHandler.KNOW_TYPE[type.ordinal()]); GL11.glTranslatef((x - 10 + (inpage ? 18 : hs) * fc), (y - tc * (inpage ? 20 : 28)), 0.0F); GL11.glScaled(0.0625D, 0.0625D, 0.0625D); func_73729_b(0, 0, 0, 0, 255, 255); if (type.hasFields() && category != null) { GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.75F); this.field_146297_k.field_71446_o.func_110577_a(category.icon); GL11.glTranslatef(0.0F, 0.0F, 1.0F); GL11.glScaled(0.66D, 0.66D, 0.66D); func_73729_b(66, 66, 0, 0, 255, 255); }  GL11.glPopMatrix(); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); GL11.glTranslatef(0.0F, 0.0F, 5.0F); String s = "" + amt; int m = this.field_146297_k.field_71466_p.func_78256_a(s); this.field_146297_k.field_71466_p.func_175063_a(s, (x - 10 + 16 - m + (inpage ? 18 : hs) * fc), (y - tc * (inpage ? 20 : 28) + 8), 16777215); s = I18n.func_74838_a("tc.type." + type.toString().toLowerCase()); if (type.hasFields() && category != null) s = s + ": " + ResearchCategories.getCategoryName(category.key);  drawPopupAt(x - 10 + (inpage ? 18 : hs) * fc, y - tc * (inpage ? 20 : 28), mx, my, s); if (par > 0) { GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.75F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); int l = (int)(par / type.getProgression() * 16.0F); func_73729_b(x - 10 + (inpage ? 18 : hs) * fc, y + 17 - tc * (inpage ? 20 : 28), 0, 232, l, 2); func_73729_b(x - 10 + (inpage ? 18 : hs) * fc + l, y + 17 - tc * (inpage ? 20 : 28), l, 234, 16 - l, 2); }  GL11.glTranslatef(0.0F, 0.0F, -5.0F); fc++; b = true; }  }  if (b) tc++;  }  if (inpage && drewSomething) { GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); func_73729_b(x + 4, y - tc * (inpage ? 20 : 28) + 12, 24, 184, 96, 8); }  } private void drawRequirements(int x, int mx, int my, ResearchStage stage) { this; int y = (this.field_146295_m - this.paneHeight) / 2 - 16 + 210; GL11.glPushMatrix(); boolean b = false; if (stage.getResearch() != null) { y -= 18; b = true; int shift = 24; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.25F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); func_73729_b(x - 12, y - 1, 200, 232, 56, 16); drawPopupAt(x - 15, y, mx, my, "tc.need.research"); Object loc = null; if (this.hasResearch != null) { if (this.hasResearch.length != stage.getResearch().length) this.hasResearch = new boolean[stage.getResearch().length];  int ss = 18; if (stage.getResearch().length > 6) ss = 110 / stage.getResearch().length;  for (int a = 0; a < stage.getResearch().length; a++) { String key = stage.getResearch()[a]; loc = (stage.getResearchIcon()[a] != null) ? new ResourceLocation(stage.getResearchIcon()[a]) : this.dummyResearch; String text = I18n.func_74838_a("research." + key + ".text"); if (key.startsWith("!")) { String k = key.replaceAll("!", ""); Aspect as = (Aspect)Aspect.aspects.get(k); if (as != null) { loc = as; text = as.getName(); }  }  ResearchEntry re = ResearchCategories.getResearch(key); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); if (re != null && re.getIcons() != null) { int idx = (int)(System.currentTimeMillis() / 1000L % re.getIcons().length); loc = re.getIcons()[idx]; text = re.getLocalizedName(); } else if (key.startsWith("m_")) { loc = this.dummyMap; } else if (key.startsWith("c_")) { loc = this.dummyChest; } else if (key.startsWith("f_")) { loc = this.dummyFlask; } else { GlStateManager.func_179131_c(0.5F, 0.75F, 1.0F, 1.0F); }  GL11.glPushMatrix(); GL11.glEnable(3042); GL11.glBlendFunc(770, 771); if (loc instanceof Aspect) { this.field_146297_k.field_71446_o.func_110577_a(((Aspect)loc).getImage()); Color cc = new Color(((Aspect)loc).getColor()); GlStateManager.func_179131_c(cc.getRed() / 255.0F, cc.getGreen() / 255.0F, cc.getBlue() / 255.0F, 1.0F); UtilsFX.drawTexturedQuadFull((x - 15 + shift), y, this.field_73735_i); } else if (loc instanceof ResourceLocation) { this.field_146297_k.field_71446_o.func_110577_a((ResourceLocation)loc); UtilsFX.drawTexturedQuadFull((x - 15 + shift), y, this.field_73735_i); } else if (loc instanceof ItemStack) { RenderHelper.func_74520_c(); GL11.glDisable(2896); GL11.glEnable(32826); GL11.glEnable(2903); GL11.glEnable(2896); this.field_146296_j.func_180450_b(InventoryUtils.cycleItemStack(loc), x - 15 + shift, y); GL11.glDisable(2896); GL11.glDepthMask(true); GL11.glEnable(2929); }  GL11.glPopMatrix(); if (this.hasResearch[a]) { GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); GlStateManager.func_179097_i(); func_73729_b(x - 15 + shift + 8, y, 159, 207, 10, 10); GlStateManager.func_179126_j(); }  drawPopupAt(x - 15 + shift, y, mx, my, text); shift += ss; }  }  }  if (stage.getObtain() != null) { y -= 18; b = true; int shift = 24; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.25F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); func_73729_b(x - 12, y - 1, 200, 216, 56, 16); drawPopupAt(x - 15, y, mx, my, "tc.need.obtain"); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); if (this.hasItem != null) { if (this.hasItem.length != stage.getObtain().length) this.hasItem = new boolean[stage.getObtain().length];  int ss = 18; if (stage.getObtain().length > 6) ss = 110 / stage.getObtain().length;  for (int idx = 0; idx < stage.getObtain().length; idx++) { ItemStack stack = InventoryUtils.cycleItemStack(stage.getObtain()[idx], idx); drawStackAt(stack, x - 15 + shift, y, mx, my, true); if (this.hasItem[idx]) { GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); GlStateManager.func_179097_i(); func_73729_b(x - 15 + shift + 8, y, 159, 207, 10, 10); GlStateManager.func_179126_j(); }  shift += ss; }  }  }  if (stage.getCraft() != null) { y -= 18; b = true; int shift = 24; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.25F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); func_73729_b(x - 12, y - 1, 200, 200, 56, 16); drawPopupAt(x - 15, y, mx, my, "tc.need.craft"); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); if (this.hasCraft != null) { if (this.hasCraft.length != stage.getCraft().length) this.hasCraft = new boolean[stage.getCraft().length];  int ss = 18; if (stage.getCraft().length > 6) ss = 110 / stage.getCraft().length;  for (int idx = 0; idx < stage.getCraft().length; idx++) { ItemStack stack = InventoryUtils.cycleItemStack(stage.getCraft()[idx], idx); drawStackAt(stack, x - 15 + shift, y, mx, my, true); if (this.hasCraft[idx]) { GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); GlStateManager.func_179097_i(); func_73729_b(x - 15 + shift + 8, y, 159, 207, 10, 10); GlStateManager.func_179126_j(); }  shift += ss; }  }  }  if (stage.getKnow() != null) { y -= 18; b = true; int shift = 24; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.25F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); func_73729_b(x - 12, y - 1, 200, 184, 56, 16); drawPopupAt(x - 15, y, mx, my, "tc.need.know"); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); if (this.hasKnow != null) { if (this.hasKnow.length != stage.getKnow().length) this.hasKnow = new boolean[stage.getKnow().length];  int ss = 18; if (stage.getKnow().length > 6) ss = 110 / stage.getKnow().length;  for (int idx = 0; idx < stage.getKnow().length; idx++) { ResearchStage.Knowledge kn = stage.getKnow()[idx]; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); GL11.glPushMatrix(); this.field_146297_k.field_71446_o.func_110577_a(HudHandler.KNOW_TYPE[kn.type.ordinal()]); GL11.glTranslatef((x - 15 + shift), y, 0.0F); GL11.glScaled(0.0625D, 0.0625D, 0.0625D); func_73729_b(0, 0, 0, 0, 255, 255); if (kn.type.hasFields() && kn.category != null) { this.field_146297_k.field_71446_o.func_110577_a(kn.category.icon); GL11.glTranslatef(32.0F, 32.0F, 1.0F); GL11.glPushMatrix(); GL11.glScaled(0.75D, 0.75D, 0.75D); func_73729_b(0, 0, 0, 0, 255, 255); GL11.glPopMatrix(); }  GL11.glPopMatrix(); String am = "" + (!this.hasKnow[idx] ? TextFormatting.RED : "") + kn.amount; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); GL11.glPushMatrix(); GL11.glTranslatef((x - 15 + shift + 16 - this.field_146297_k.field_71466_p.func_78256_a(am) / 2), (y + 12), 5.0F); GL11.glScaled(0.5D, 0.5D, 0.5D); this.field_146297_k.field_71466_p.func_175063_a(am, 0.0F, 0.0F, 16777215); GL11.glPopMatrix(); if (this.hasKnow[idx]) { GL11.glPushMatrix(); GL11.glTranslatef(0.0F, 0.0F, 1.0F); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); func_73729_b(x - 15 + shift + 8, y, 159, 207, 10, 10); GL11.glPopMatrix(); }  String s = I18n.func_74838_a("tc.type." + kn.type.toString().toLowerCase()); if (kn.type.hasFields() && kn.category != null) s = s + ": " + ResearchCategories.getCategoryName(kn.category.key);  drawPopupAt(x - 15 + shift, y, mx, my, s); shift += ss; }  }  }  if (b) { y -= 12; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); func_73729_b(x + 4, y - 2, 24, 184, 96, 8); if (this.hasAllRequisites) { this.hrx = x + 20; this.hry = y - 6; if (this.hold) { String s = I18n.func_74838_a("tc.stage.hold"); int m = this.field_146297_k.field_71466_p.func_78256_a(s); this.field_146297_k.field_71466_p.func_175063_a(s, (x + 52) - m / 2.0F, (y - 4), 16777215); } else { if (mouseInside(this.hrx, this.hry, 64, 12, mx, my)) { GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); } else { GlStateManager.func_179131_c(0.8F, 0.8F, 0.9F, 1.0F); }  this.field_146297_k.field_71446_o.func_110577_a(this.tex1); func_73729_b(this.hrx, this.hry, 84, 216, 64, 12); String s = I18n.func_74838_a("tc.stage.complete"); int m = this.field_146297_k.field_71466_p.func_78256_a(s); this.field_146297_k.field_71466_p.func_175063_a(s, (x + 52) - m / 2.0F, (y - 4), 16777215); }  }  }  GL11.glPopMatrix(); } private void drawRecipeBookmarks(int x, int mx, int my, ResearchStage stage) { Random rng = new Random(this.rhash); GL11.glPushMatrix(); int y = (this.field_146295_m - this.paneHeight) / 2 - 8; this.allowWithPagePopup = true; if (this.recipeOutputs.size() > 0) { int space = Math.min(25, 200 / this.recipeOutputs.size()); for (ResourceLocation rk : this.recipeOutputs.keySet()) { List list = (List)this.recipeOutputs.get(rk); if (list != null && list.size() > 0) { int i = this.cycle % list.size(); if (list.get(i) == null) continue;  int sh = rng.nextInt(3); int le = rng.nextInt(3) + (mouseInside(x + 280, y - 1, 30, 16, mx, my) ? 0 : 3); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); if (rk.equals(shownRecipe)) { GlStateManager.func_179131_c(1.0F, 0.5F, 0.5F, 1.0F); } else { GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); }  func_73729_b(x + 280 + sh, y - 1, 120 + le, 232, 28, 16); func_73729_b(x + 280 + sh, y - 1, 116, 232, 4, 16); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); UtilsFX.hideStackOverlay = true; if (list.get(i) instanceof ItemStack) { drawStackAt((ItemStack)list.get(i), x + 287 + sh - le, y - 1, mx, my, false); } else if (list.get(i) instanceof Part[][][]) { BlueprintBlockAccess ba = (BlueprintBlockAccess)this.blockAccessIcons.get(rk); if (ba == null) this.blockAccessIcons.put(rk, ba = new BlueprintBlockAccess((Part[][][])list.get(i), true));  int h = (Part[][][])list.get(i).length; renderBluePrint(ba, x + 295 + sh - le, y + 6 + h, 4.0F, (Part[][][])list.get(i), -5000, -5000, null); }  UtilsFX.hideStackOverlay = false; y += space; }  }  }  this.allowWithPagePopup = false; GL11.glPopMatrix(); } private void generateRecipesLists(ResearchStage stage, ResearchAddendum[] addenda) { this.recipeLists.clear(); this.recipeOutputs.clear(); if (stage == null || stage.getRecipes() == null) return;  for (ResourceLocation rk : stage.getRecipes()) addRecipesToList(rk, this.recipeLists, this.recipeOutputs, rk);  if (addenda == null) return;  for (ResearchAddendum addendum : addenda) { if (addendum.getRecipes() != null && ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, addendum.getResearch())) for (ResourceLocation rk : addendum.getRecipes()) addRecipesToList(rk, this.recipeLists, this.recipeOutputs, rk);   }  } public GuiResearchPage(ResearchEntry research, ResourceLocation recipe, double x, double y) { this.paneWidth = 256;
/*  104 */     this.paneHeight = 181;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  113 */     this.mouseX = 0;
/*      */ 
/*      */     
/*  116 */     this.mouseY = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  121 */     this.currentStage = 0;
/*  122 */     this.lastStage = 0;
/*  123 */     this.hold = false;
/*  124 */     this.page = 0;
/*  125 */     this.maxPages = 0;
/*  126 */     this.maxAspectPages = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  133 */     this.rhash = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  239 */     this.transX = 0.0F;
/*  240 */     this.transY = 0.0F;
/*  241 */     this.rotX = 0.0F;
/*  242 */     this.rotY = 0.0F;
/*  243 */     this.rotZ = 0.0F;
/*      */ 
/*      */     
/*  246 */     this.lastCheck = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  285 */     this.tex1 = new ResourceLocation("thaumcraft", "textures/gui/gui_researchbook.png");
/*  286 */     this.tex2 = new ResourceLocation("thaumcraft", "textures/gui/gui_researchbook_overlay.png");
/*  287 */     this.tex3 = new ResourceLocation("thaumcraft", "textures/aspects/_back.png");
/*  288 */     this.tex4 = new ResourceLocation("thaumcraft", "textures/gui/paper.png");
/*  289 */     this.dummyResearch = new ResourceLocation("thaumcraft", "textures/aspects/_unknown.png");
/*  290 */     this.dummyMap = new ResourceLocation("thaumcraft", "textures/research/rd_map.png");
/*  291 */     this.dummyFlask = new ResourceLocation("thaumcraft", "textures/research/rd_flask.png");
/*  292 */     this.dummyChest = new ResourceLocation("thaumcraft", "textures/research/rd_chest.png");
/*      */ 
/*      */     
/*  295 */     this.hrx = 0;
/*  296 */     this.hry = 0;
/*      */ 
/*      */     
/*  299 */     this.recipePage = 0;
/*  300 */     this.recipePageMax = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  362 */     this.lastCycle = 0L;
/*  363 */     this.showingAspects = false;
/*  364 */     this.showingKnowledge = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  905 */     this.recipeLists = new LinkedHashMap();
/*  906 */     this.recipeOutputs = new LinkedHashMap();
/*  907 */     this.drilldownLists = new LinkedHashMap();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1077 */     this.renderingCompound = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1106 */     this.blockAccess = null;
/* 1107 */     this.blockAccessIcons = new HashMap();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1830 */     this.reference = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1994 */     this.cycle = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2005 */     this.allowWithPagePopup = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2057 */     this.tipText = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2091 */     this.pages = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2328 */     this.isComplete = false;
/* 2329 */     this.hasAllRequisites = false;
/* 2330 */     this.hasItem = null;
/* 2331 */     this.hasCraft = null;
/* 2332 */     this.hasResearch = null;
/* 2333 */     this.hasKnow = null;
/* 2334 */     this.hasStats = null;
/*      */     
/* 2336 */     this.keyCache = new HashMap(); this.research = research; this.guiMapX = x; this.guiMapY = y; this.field_146297_k = Minecraft.func_71410_x(); this.playerKnowledge = ThaumcraftCapabilities.getKnowledge(this.field_146297_k.field_71439_g); parsePages(); this.knownPlayerAspects = new AspectList(); for (Aspect a : Aspect.aspects.values()) { if (ThaumcraftCapabilities.knowsResearch(this.field_146297_k.field_71439_g, new String[] { "!" + a.getTag().toLowerCase() })) this.knownPlayerAspects.add(a, 1);  }  this.maxAspectPages = (this.knownPlayerAspects != null) ? MathHelper.func_76123_f(this.knownPlayerAspects.size() / 5.0F) : 0; this.page = 0; if (recipe != null) shownRecipe = recipe;  }
/*      */   private void addRecipesToList(ResourceLocation rk, LinkedHashMap<ResourceLocation, ArrayList> recipeLists2, LinkedHashMap<ResourceLocation, ArrayList> recipeOutputs2, ResourceLocation rkey) { Object recipe = CommonInternals.getCatalogRecipe(rk); if (recipe == null) recipe = CommonInternals.getCatalogRecipeFake(rk);  if (recipe == null) recipe = CraftingManager.func_193373_a(rk);  if (recipe == null) recipe = ConfigRecipes.recipeGroups.get(rk.toString());  if (recipe == null) return;  if (recipe instanceof ArrayList) { for (ResourceLocation rl : (ArrayList)recipe) addRecipesToList(rl, recipeLists2, recipeOutputs2, rk);  } else { if (!recipeLists2.containsKey(rkey)) { recipeLists2.put(rkey, new ArrayList()); recipeOutputs2.put(rkey, new ArrayList()); }  ArrayList list = (ArrayList)recipeLists2.get(rkey); ArrayList outputs = (ArrayList)recipeOutputs2.get(rkey); if (recipe instanceof ThaumcraftApi.BluePrint) { ThaumcraftApi.BluePrint r = (ThaumcraftApi.BluePrint)recipe; if (ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, new String[] { r.getResearch() })) { list.add(r); if (r.getDisplayStack() != null) { outputs.add(r.getDisplayStack()); } else { outputs.add(r.getParts()); }  }  } else if (recipe instanceof CrucibleRecipe) { CrucibleRecipe re = (CrucibleRecipe)recipe; ItemStack is = InventoryUtils.cycleItemStack(re.getCatalyst(), 0); if (is != null && !is.func_190926_b() && ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, new String[] { re.getResearch() })) { list.add(re); outputs.add(re.getRecipeOutput()); }  } else if (recipe instanceof InfusionRecipe) { InfusionRecipe re = (InfusionRecipe)recipe; ItemStack is = null; if (re instanceof InfusionEnchantmentRecipe) { is = InventoryUtils.cycleItemStack(re.getRecipeOutput(this.field_146297_k.field_71439_g, re.getRecipeInput().func_193365_a()[0].func_77946_l(), null), 0); } else if (re instanceof InfusionRunicAugmentRecipe) { NonNullList<Ingredient> il = ((InfusionRunicAugmentRecipe)re).getComponents(re.getRecipeInput().func_193365_a()[0]); List<ItemStack> cl = new ArrayList<ItemStack>(); for (Ingredient i : il) cl.add(i.func_193365_a()[0]);  is = InventoryUtils.cycleItemStack(re.getRecipeOutput(this.field_146297_k.field_71439_g, re.getRecipeInput().func_193365_a()[0].func_77946_l(), cl), 0); } else if (re.getRecipeOutput() instanceof ItemStack) { is = InventoryUtils.cycleItemStack(re.getRecipeOutput(), 0); } else { is = InventoryUtils.cycleItemStack(re.getRecipeInput()); if (is != null && !is.func_190926_b()) is = is.func_77946_l();  try { Object[] obj = (Object[])re.getRecipeOutput(); NBTBase tag = (NBTBase)obj[1]; is.func_77983_a((String)obj[0], tag); } catch (Exception exception) {} }  if (is != null && ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, new String[] { re.research })) { list.add(re); outputs.add(is); }  } else if (recipe instanceof IArcaneRecipe) { IArcaneRecipe re = (IArcaneRecipe)recipe; ItemStack is = InventoryUtils.cycleItemStack(re.func_77571_b(), 0); if (is != null && !is.func_190926_b() && ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, new String[] { re.getResearch() })) { list.add(re); outputs.add(re.func_77571_b()); }  } else if (recipe instanceof IRecipe) { IRecipe re = (IRecipe)recipe; list.add(re); outputs.add(re.func_77571_b()); } else if (recipe instanceof RecipeMisc) { RecipeMisc re = (RecipeMisc)recipe; list.add(re); outputs.add(re.getOutput()); }  }  }
/*      */   private void drawRecipe(int mx, int my) { this.allowWithPagePopup = true; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(this.tex4); int x = (this.field_146294_l - 256) / 2; int y = (this.field_146295_m - 256) / 2; GlStateManager.func_179097_i(); func_73729_b(x, y, 0, 0, 255, 255); GlStateManager.func_179126_j(); List list = (List)this.recipeLists.get(shownRecipe); if (list == null || list.size() == 0) list = (List)this.drilldownLists.get(shownRecipe);  if (list != null && list.size() > 0) { this.hasRecipePages = (list.size() > 1); this.recipePageMax = list.size() - 1; if (this.recipePage > this.recipePageMax) this.recipePage = this.recipePageMax;  Object recipe = list.get(this.recipePage % list.size()); if (recipe != null) if (recipe instanceof IArcaneRecipe) { drawArcaneCraftingPage(x + 128, y + 128, mx, my, (IArcaneRecipe)recipe); } else if (recipe instanceof IRecipe) { drawCraftingPage(x + 128, y + 128, mx, my, (IRecipe)recipe); } else if (recipe instanceof CrucibleRecipe) { drawCruciblePage(x + 128, y + 128, mx, my, (CrucibleRecipe)recipe); } else if (recipe instanceof InfusionRecipe) { drawInfusionPage(x + 128, y + 128, mx, my, (InfusionRecipe)recipe); } else if (recipe instanceof ThaumcraftApi.BluePrint) { drawCompoundCraftingPage(x + 128, y + 128, mx, my, (ThaumcraftApi.BluePrint)recipe); this.renderingCompound = true; }   if (this.hasRecipePages) { this.field_146297_k.field_71446_o.func_110577_a(this.tex1); float bob = MathHelper.func_76126_a(this.field_146297_k.field_71439_g.field_70173_aa / 3.0F) * 0.2F + 0.1F; if (this.recipePage > 0) drawTexturedModalRectScaled(x + 40, y + 232, 0, 184, 12, 8, bob);  if (this.recipePage < this.recipePageMax) drawTexturedModalRectScaled(x + 204, y + 232, 12, 184, 12, 8, bob);  }  }  this.allowWithPagePopup = false; }
/* 2339 */   static boolean cycleMultiblockLines = false; BlueprintBlockAccess blockAccess; HashMap<ResourceLocation, BlueprintBlockAccess> blockAccessIcons; ArrayList<List> reference; private int cycle; boolean allowWithPagePopup; List tipText; private static final int PAGEWIDTH = 140; private static final int PAGEHEIGHT = 210; private void drawCompoundCraftingPage(int x, int y, int mx, int my, ThaumcraftApi.BluePrint recipe) { if (recipe.getParts() == null) return;  if (this.blockAccess == null) this.blockAccess = new BlueprintBlockAccess(recipe.getParts(), false);  int ySize = recipe.getParts().length; int xSize = recipe.getParts()[0].length; int zSize = recipe.getParts()[0][0].length; String text = I18n.func_74838_a("recipe.type.construct"); int offset = this.field_146297_k.field_71466_p.func_78256_a(text); this.field_146297_k.field_71466_p.func_78276_b(text, x - offset / 2, y - 104, 5263440); int s = Math.max(Math.max(xSize, zSize), ySize) * 2; float scale = (38 - s); renderBluePrint(this.blockAccess, x, y, scale, recipe.getParts(), mx, my, recipe.getIngredientList()); this.field_146297_k.field_71446_o.func_110577_a(this.tex1); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, mouseInside(x + 80, y + 100, 8, 8, mx, my) ? 1.0F : 0.75F); func_73729_b(x + 80, y + 100, cycleMultiblockLines ? 168 : 160, 224, 8, 8); } private void renderBluePrint(BlueprintBlockAccess ba, int x, int y, float scale, Part[][][] blueprint, int mx, int my, ItemStack[] ingredients) { BlockRendererDispatcher blockRender = Minecraft.func_71410_x().func_175602_ab(); int ySize = blueprint.length; int xSize = blueprint[0].length; int zSize = blueprint[0][0].length; this.transX = (x - xSize / 2); this.transY = y - (float)Math.sqrt((ySize * ySize + xSize * xSize + zSize * zSize)) / 2.0F; GlStateManager.func_179091_B(); GlStateManager.func_179094_E(); RenderHelper.func_74518_a(); GlStateManager.func_179109_b(this.transX, this.transY, Math.max(ySize, Math.max(xSize, zSize))); GlStateManager.func_179152_a(scale, -scale, 1.0F); GlStateManager.func_179114_b(this.rotX, 1.0F, 0.0F, 0.0F); GlStateManager.func_179114_b(this.rotY, 0.0F, 1.0F, 0.0F); GlStateManager.func_179109_b(zSize / -2.0F, ySize / -2.0F, xSize / -2.0F); GlStateManager.func_179140_f(); if (Minecraft.func_71379_u()) { GlStateManager.func_179103_j(7425); } else { GlStateManager.func_179103_j(7424); }  this.field_146297_k.func_110434_K().func_110577_a(TextureMap.field_110575_b); ArrayList<ItemStack> blocks = new ArrayList<ItemStack>(); for (int h = 0; h < ySize; h++) { for (int l = 0; l < xSize; l++) { for (int w = 0; w < zSize; w++) { BlockPos pos = new BlockPos(l, h, w); if (!ba.func_175623_d(pos)) { GlStateManager.func_179109_b(l, h, w); GlStateManager.func_179109_b(-l, -h, -w); IBlockState state = ba.func_180495_p(pos); Tessellator tessellator = Tessellator.func_178181_a(); BufferBuilder buffer = tessellator.func_178180_c(); buffer.func_181668_a(7, DefaultVertexFormats.field_176600_a); boolean b = blockRender.func_175018_a(state, pos, ba, buffer); tessellator.func_78381_a(); try { if (!b && state.func_177230_c().hasTileEntity(state)) { TileEntity te = state.func_177230_c().createTileEntity(this.field_146297_k.field_71441_e, state); RenderHelper.func_74519_b(); int i = 250; int j = i % 65536; int k = i / 65536; OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j, k); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); TileEntityRendererDispatcher.field_147556_a.func_147549_a(te, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), this.field_146297_k.func_184121_ak()); }  } catch (Exception exception) {} }  }  }  }  GlStateManager.func_179121_F(); if (ingredients != null) for (int a = 0; a < ingredients.length; a++) { if (ingredients[a] != null && !ingredients[a].func_190926_b() && ingredients[a].func_77973_b() != null) { RenderHelper.func_74520_c(); GL11.glDisable(2896); GL11.glEnable(32826); GL11.glEnable(2903); GL11.glEnable(2896); drawStackAt(ingredients[a], x - 85 + a * 17, y + 90, mx, my, true); GL11.glDisable(2896); GL11.glDepthMask(true); GL11.glEnable(2929); }  }   RenderHelper.func_74518_a(); GlStateManager.func_179101_C(); GlStateManager.func_179147_l(); RenderHelper.func_74518_a(); } private void drawAspectsInsert(int mx, int my) { this.allowWithPagePopup = true; GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F); this.field_146297_k.field_71446_o.func_110577_a(this.tex4); int x = (this.field_146294_l - 256) / 2; int y = (this.field_146295_m - 256) / 2; GlStateManager.func_179097_i(); func_73729_b(x, y, 0, 0, 255, 255); GlStateManager.func_179126_j(); drawAspectPage(x + 60, y + 24, mx, my); this.allowWithPagePopup = false; } private void checkRequisites() { if (this.research.getStages() != null)
/* 2340 */     { this.isComplete = this.playerKnowledge.isResearchComplete(this.research.getKey());
/* 2341 */       while (this.currentStage >= this.research.getStages().length) {
/* 2342 */         this.currentStage--;
/*      */       }
/* 2344 */       if (this.currentStage < 0)
/*      */         return; 
/* 2346 */       this.hasAllRequisites = true;
/* 2347 */       this.hasItem = null;
/* 2348 */       this.hasCraft = null;
/* 2349 */       this.hasResearch = null;
/* 2350 */       this.hasKnow = null;
/*      */       
/* 2352 */       ResearchStage stage = this.research.getStages()[this.currentStage];
/*      */ 
/*      */       
/* 2355 */       Object[] o = stage.getObtain();
/* 2356 */       if (o != null) {
/* 2357 */         this.hasItem = new boolean[o.length];
/* 2358 */         for (int a = 0; a < o.length; a++) {
/*      */           
/* 2360 */           ItemStack ts = ItemStack.field_190927_a;
/* 2361 */           boolean ore = false;
/* 2362 */           if (o[a] instanceof ItemStack) {
/* 2363 */             ts = (ItemStack)o[a];
/*      */           } else {
/* 2365 */             NonNullList<ItemStack> nnl = OreDictionary.getOres((String)o[a]);
/* 2366 */             ts = (ItemStack)nnl.get(0);
/* 2367 */             ore = true;
/*      */           } 
/*      */           
/* 2370 */           this.hasItem[a] = InventoryUtils.isPlayerCarryingAmount(this.field_146297_k.field_71439_g, ts, ore);
/* 2371 */           if (!this.hasItem[a]) this.hasAllRequisites = false;
/*      */         
/*      */         } 
/*      */       } 
/*      */       
/* 2376 */       Object[] c = stage.getCraft();
/* 2377 */       if (c != null) {
/* 2378 */         this.hasCraft = new boolean[c.length];
/* 2379 */         for (int a = 0; a < c.length; a++) {
/* 2380 */           if (!this.playerKnowledge.isResearchKnown("[#]" + stage.getCraftReference()[a])) {
/* 2381 */             this.hasAllRequisites = false;
/* 2382 */             this.hasCraft[a] = false;
/*      */           } else {
/* 2384 */             this.hasCraft[a] = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2390 */       String[] r = stage.getResearch();
/* 2391 */       if (r != null) {
/* 2392 */         this.hasResearch = new boolean[r.length];
/* 2393 */         for (int a = 0; a < r.length; a++) {
/* 2394 */           if (!ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, new String[] { r[a] })) {
/* 2395 */             this.hasAllRequisites = false;
/* 2396 */             this.hasResearch[a] = false;
/*      */           } else {
/* 2398 */             this.hasResearch[a] = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2404 */       ResearchStage.Knowledge[] arrayOfKnowledge = stage.getKnow();
/* 2405 */       if (arrayOfKnowledge != null)
/* 2406 */       { this.hasKnow = new boolean[arrayOfKnowledge.length];
/* 2407 */         for (int a = 0; a < arrayOfKnowledge.length; a++)
/* 2408 */         { int pk = this.playerKnowledge.getKnowledge((arrayOfKnowledge[a]).type, (arrayOfKnowledge[a]).category);
/* 2409 */           if (pk < (arrayOfKnowledge[a]).amount)
/* 2410 */           { this.hasAllRequisites = false;
/* 2411 */             this.hasKnow[a] = false; }
/*      */           else
/* 2413 */           { this.hasKnow[a] = true; }  }  }  }  } private void drawAspectPage(int x, int y, int mx, int my) { if (this.knownPlayerAspects != null && this.knownPlayerAspects.size() > 0) { GL11.glPushMatrix(); int mposx = mx; int mposy = my; int count = -1; int start = aspectsPage * 5; for (Aspect aspect : this.knownPlayerAspects.getAspectsSortedByName()) { count++; if (count >= start) { if (count > start + 4) break;  if (aspect.getImage() != null) { int tx = x; int ty = y + count % 5 * 40; if (mposx >= tx && mposy >= ty && mposx < tx + 40 && mposy < ty + 40) { this.field_146297_k.field_71446_o.func_110577_a(this.tex3); GL11.glPushMatrix(); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GL11.glTranslated((x - 2), (y + count % 5 * 40 - 2), 0.0D); GL11.glScaled(2.0D, 2.0D, 0.0D); GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.5F); UtilsFX.drawTexturedQuadFull(0.0F, 0.0F, this.field_73735_i); GL11.glPopMatrix(); }  GL11.glPushMatrix(); GL11.glTranslated((x + 2), (y + 2 + count % 5 * 40), 0.0D); GL11.glScalef(1.5F, 1.5F, 1.5F); UtilsFX.drawTag(0, 0, aspect, 0.0F, 0, this.field_73735_i); GL11.glPopMatrix(); GL11.glPushMatrix(); GL11.glTranslated((x + 16), (y + 29 + count % 5 * 40), 0.0D); GL11.glScalef(0.5F, 0.5F, 0.5F); String text = aspect.getName(); int offset = this.field_146297_k.field_71466_p.func_78256_a(text) / 2; this.field_146297_k.field_71466_p.func_78276_b(text, -offset, 0, 5263440); GL11.glPopMatrix(); if (aspect.getComponents() != null) { GL11.glPushMatrix(); GL11.glTranslated((x + 60), (y + 4 + count % 5 * 40), 0.0D); GL11.glScalef(1.25F, 1.25F, 1.25F); if (this.playerKnowledge.isResearchKnown("!" + aspect.getComponents()[0].getTag().toLowerCase())) { UtilsFX.drawTag(0, 0, aspect.getComponents()[0], 0.0F, 0, this.field_73735_i); } else { this.field_146297_k.field_71446_o.func_110577_a(this.dummyResearch); GlStateManager.func_179131_c(0.8F, 0.8F, 0.8F, 1.0F); UtilsFX.drawTexturedQuadFull(0.0F, 0.0F, this.field_73735_i); }  GL11.glPopMatrix(); GL11.glPushMatrix(); GL11.glTranslated((x + 102), (y + 4 + count % 5 * 40), 0.0D); GL11.glScalef(1.25F, 1.25F, 1.25F); if (this.playerKnowledge.isResearchKnown("!" + aspect.getComponents()[1].getTag().toLowerCase())) { UtilsFX.drawTag(0, 0, aspect.getComponents()[1], 0.0F, 0, this.field_73735_i); } else { this.field_146297_k.field_71446_o.func_110577_a(this.dummyResearch); GlStateManager.func_179131_c(0.8F, 0.8F, 0.8F, 1.0F); UtilsFX.drawTexturedQuadFull(0.0F, 0.0F, this.field_73735_i); }  GL11.glPopMatrix(); if (this.playerKnowledge.isResearchKnown("!" + aspect.getComponents()[0].getTag().toLowerCase())) { text = aspect.getComponents()[0].getName(); offset = this.field_146297_k.field_71466_p.func_78256_a(text) / 2; GL11.glPushMatrix(); GL11.glTranslated((x + 22 + 50), (y + 29 + count % 5 * 40), 0.0D); GL11.glScalef(0.5F, 0.5F, 0.5F); this.field_146297_k.field_71466_p.func_78276_b(text, -offset, 0, 5263440); GL11.glPopMatrix(); }  if (this.playerKnowledge.isResearchKnown("!" + aspect.getComponents()[1].getTag().toLowerCase())) { text = aspect.getComponents()[1].getName(); offset = this.field_146297_k.field_71466_p.func_78256_a(text) / 2; GL11.glPushMatrix(); GL11.glTranslated((x + 22 + 92), (y + 29 + count % 5 * 40), 0.0D); GL11.glScalef(0.5F, 0.5F, 0.5F); this.field_146297_k.field_71466_p.func_78276_b(text, -offset, 0, 5263440); GL11.glPopMatrix(); }  this.field_146297_k.field_71466_p.func_78276_b("=", x + 9 + 32, y + 12 + count % 5 * 40, 10066329); this.field_146297_k.field_71466_p.func_78276_b("+", x + 10 + 79, y + 12 + count % 5 * 40, 10066329); } else { this.field_146297_k.field_71466_p.func_78276_b(I18n.func_74838_a("tc.aspect.primal"), x + 54, y + 12 + count % 5 * 40, 7829367); }  }  }  }  this.field_146297_k.field_71446_o.func_110577_a(this.tex1); float bob = MathHelper.func_76126_a(this.field_146297_k.field_71439_g.field_70173_aa / 3.0F) * 0.2F + 0.1F; if (aspectsPage > 0) drawTexturedModalRectScaled(x - 20, y + 208, 0, 184, 12, 8, bob);  if (aspectsPage < this.maxAspectPages - 1) drawTexturedModalRectScaled(x + 144, y + 208, 12, 184, 12, 8, bob);  GL11.glPopMatrix(); }  } private void drawArcaneCraftingPage(int x, int y, int mx, int my, IArcaneRecipe recipe) { GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GL11.glPushMatrix(); this.field_146297_k.field_71446_o.func_110577_a(this.tex2); GL11.glPushMatrix(); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GL11.glTranslatef(x, y, 0.0F); GL11.glScalef(2.0F, 2.0F, 1.0F); func_73729_b(-26, -26, 112, 15, 52, 52); func_73729_b(-8, -46, 20, 3, 16, 16); GL11.glPopMatrix(); GL11.glPushMatrix(); GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GL11.glTranslatef(x, y, 0.0F); GL11.glScalef(2.0F, 2.0F, 1.0F); func_73729_b(-6, 40, 68, 76, 12, 12); GL11.glPopMatrix(); String text = "" + recipe.getVis(); int offset = this.field_146297_k.field_71466_p.func_78256_a(text); this.field_146297_k.field_71466_p.func_78276_b(text, x - offset / 2, y + 90, 5263440); drawPopupAt(x - offset / 2 - 15, y + 75, 30, 30, mx, my, "wandtable.text1"); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); GL11.glTranslated(0.0D, 0.0D, 100.0D); drawStackAt(InventoryUtils.cycleItemStack(recipe.func_77571_b(), 0), x - 8, y - 84, mx, my, false); AspectList crystals = recipe.getCrystals(); if (crystals != null) { int a = 0; int sz = crystals.size(); for (Aspect aspect : crystals.getAspects()) { drawStackAt(InventoryUtils.cycleItemStack(ThaumcraftApiHelper.makeCrystal(aspect, crystals.getAmount(aspect)), a), x + 4 - sz * 10 + a * 20, y + 59, mx, my, true); a++; }  }  if (recipe != null && recipe instanceof ShapedArcaneRecipe) { text = I18n.func_74838_a("recipe.type.arcane"); offset = this.field_146297_k.field_71466_p.func_78256_a(text); this.field_146297_k.field_71466_p.func_78276_b(text, x - offset / 2, y - 104, 5263440); int rw = ((ShapedArcaneRecipe)recipe).getRecipeWidth(); int rh = ((ShapedArcaneRecipe)recipe).getRecipeHeight(); NonNullList<Ingredient> items = ((ShapedArcaneRecipe)recipe).func_192400_c(); for (int i = 0; i < rw && i < 3; i++) { for (int j = 0; j < rh && j < 3; j++) { if (items.get(i + j * rw) != null) drawStackAt(InventoryUtils.cycleItemStack(items.get(i + j * rw), i + j * rw), x - 40 + i * 32, y - 40 + j * 32, mx, my, true);  }  }  }  if (recipe != null && recipe instanceof ShapelessArcaneRecipe) { text = I18n.func_74838_a("recipe.type.arcane.shapeless"); offset = this.field_146297_k.field_71466_p.func_78256_a(text); this.field_146297_k.field_71466_p.func_78276_b(text, x - offset / 2, y - 104, 5263440); NonNullList<Ingredient> items = ((ShapelessArcaneRecipe)recipe).func_192400_c(); for (int i = 0; i < items.size() && i < 9; i++) { if (items.get(i) != null) drawStackAt(InventoryUtils.cycleItemStack(items.get(i), i), x - 40 + i % 3 * 32, y - 40 + i / 3 * 32, mx, my, true);  }  }  GL11.glPopMatrix(); } private void drawCraftingPage(int x, int y, int mx, int my, IRecipe recipe) { GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); if (recipe == null) return;  GL11.glPushMatrix(); this.field_146297_k.field_71446_o.func_110577_a(this.tex2); GL11.glPushMatrix(); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GL11.glTranslatef(x, y, 0.0F); GL11.glScalef(2.0F, 2.0F, 1.0F); func_73729_b(-26, -26, 60, 15, 51, 52); func_73729_b(-8, -46, 20, 3, 16, 16); GL11.glPopMatrix(); drawStackAt(InventoryUtils.cycleItemStack(recipe.func_77571_b(), 0), x - 8, y - 84, mx, my, false); if (recipe != null && recipe instanceof IShapedRecipe) { String text = I18n.func_74838_a("recipe.type.workbench"); int offset = this.field_146297_k.field_71466_p.func_78256_a(text); this.field_146297_k.field_71466_p.func_78276_b(text, x - offset / 2, y - 104, 5263440); int rw = ((IShapedRecipe)recipe).getRecipeWidth(); int rh = ((IShapedRecipe)recipe).getRecipeHeight(); NonNullList<Ingredient> items = recipe.func_192400_c(); for (int i = 0; i < rw && i < 3; i++) { for (int j = 0; j < rh && j < 3; j++) { if (items.get(i + j * rw) != null) drawStackAt(InventoryUtils.cycleItemStack(items.get(i + j * rw), i + j * rw), x - 40 + i * 32, y - 40 + j * 32, mx, my, true);  }  }  }  if (recipe != null && (recipe instanceof net.minecraft.item.crafting.ShapelessRecipes || recipe instanceof net.minecraftforge.oredict.ShapelessOreRecipe)) { String text = I18n.func_74838_a("recipe.type.workbenchshapeless"); int offset = this.field_146297_k.field_71466_p.func_78256_a(text); this.field_146297_k.field_71466_p.func_78276_b(text, x - offset / 2, y - 104, 5263440); NonNullList<Ingredient> items = recipe.func_192400_c(); for (int i = 0; i < items.size() && i < 9; i++) { if (items.get(i) != null) drawStackAt(InventoryUtils.cycleItemStack(items.get(i), i), x + -40 + i % 3 * 32, y - 40 + i / 3 * 32, mx, my, true);  }  }  GL11.glPopMatrix(); } private void drawCruciblePage(int x, int y, int mx, int my, CrucibleRecipe rc) { GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); if (rc != null) { GL11.glPushMatrix(); String text = I18n.func_74838_a("recipe.type.crucible"); int offset = this.field_146297_k.field_71466_p.func_78256_a(text); this.field_146297_k.field_71466_p.func_78276_b(text, x - offset / 2, y - 104, 5263440); this.field_146297_k.field_71446_o.func_110577_a(this.tex2); GL11.glPushMatrix(); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GL11.glTranslatef(x, y, 0.0F); GL11.glScalef(2.0F, 2.0F, 1.0F); func_73729_b(-28, -29, 0, 3, 56, 17); GL11.glTranslatef(0.0F, 32.0F, 0.0F); func_73729_b(-28, -44, 0, 20, 56, 48); GL11.glTranslatef(0.0F, -8.0F, 0.0F); func_73729_b(-25, -50, 100, 84, 11, 13); GL11.glPopMatrix(); int mposx = mx; int mposy = my; int total = 0; int rows = (rc.getAspects().size() - 1) / 3; int shift = (3 - rc.getAspects().size() % 3) * 10; int sx = x - 28; int sy = y + 8 - 10 * rows; for (Aspect tag : rc.getAspects().getAspectsSortedByName()) { int m = 0; if (total / 3 >= rows && (rows > 1 || rc.getAspects().size() < 3)) m = 1;  int vx = sx + total % 3 * 20 + shift * m; int vy = sy + total / 3 * 20; UtilsFX.drawTag(vx, vy, tag, rc.getAspects().getAmount(tag), 0, this.field_73735_i); total++; }  drawStackAt(rc.getRecipeOutput(), x - 8, y - 50, mx, my, false); drawStackAt(InventoryUtils.cycleItemStack(rc.getCatalyst(), 0), x - 64, y - 56, mx, my, true); total = 0; for (Aspect tag : rc.getAspects().getAspectsSortedByName()) { int m = 0; if (total / 3 >= rows && (rows > 1 || rc.getAspects().size() < 3)) m = 1;  int vx = sx + total % 3 * 20 + shift * m; int vy = sy + total / 3 * 20; if (mposx >= vx && mposy >= vy && mposx < vx + 16 && mposy < vy + 16) this.tipText = Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() });  total++; }  GL11.glPopMatrix(); }  } private void drawInfusionPage(int x, int y, int mx, int my, InfusionRecipe ri) { if (ri != null) { NonNullList<Ingredient> components = ri.getComponents(); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GL11.glPushMatrix(); AspectList aspects = ri.getAspects(); Object output = ri.getRecipeOutput(); if (ri instanceof InfusionRunicAugmentRecipe) { NonNullList<Ingredient> c = ((InfusionRunicAugmentRecipe)ri).getComponents(ri.getRecipeInput().func_193365_a()[0]); components = c; ArrayList<ItemStack> com = new ArrayList<ItemStack>(); for (Ingredient s : c) com.add(s.func_193365_a()[0]);  aspects = ri.getAspects(this.field_146297_k.field_71439_g, ri.getRecipeInput().func_193365_a()[0], com); output = ((InfusionRunicAugmentRecipe)ri).getRecipeOutput(this.field_146297_k.field_71439_g, ri.getRecipeInput().func_193365_a()[0], com); }  if (ri instanceof InfusionEnchantmentRecipe) { ArrayList<ItemStack> com = new ArrayList<ItemStack>(); for (Object s : components) { if (s instanceof ItemStack) com.add((ItemStack)s);  }  aspects = ri.getAspects(this.field_146297_k.field_71439_g, ri.getRecipeInput().func_193365_a()[0], com); output = ((InfusionEnchantmentRecipe)ri).getRecipeOutput(null, ri.getRecipeInput().func_193365_a()[0], com); }  String text = I18n.func_74838_a("recipe.type.infusion"); int offset = this.field_146297_k.field_71466_p.func_78256_a(text); this.field_146297_k.field_71466_p.func_78276_b(text, x - offset / 2, y - 104, 5263440); this.field_146297_k.field_71446_o.func_110577_a(this.tex2); GL11.glPushMatrix(); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GL11.glTranslatef(x, (y + 20), 0.0F); GL11.glScalef(2.0F, 2.0F, 1.0F); func_73729_b(-28, -56, 0, 3, 56, 17); GL11.glTranslatef(0.0F, 19.0F, 0.0F); func_73729_b(-28, -55, 200, 77, 60, 44); GL11.glPopMatrix(); int mposx = mx; int mposy = my; int total = 0; int rows = (aspects.size() - 1) / 5; int shift = (5 - aspects.size() % 5) * 10; int sx = x - 48; int sy = y + 50 - 10 * rows; for (Aspect tag : aspects.getAspectsSortedByName()) { int m = 0; if (total / 5 >= rows && (rows > 1 || aspects.size() < 5)) m = 1;  int vx = sx + total % 5 * 20 + shift * m; int vy = sy + total / 5 * 20; UtilsFX.drawTag(vx, vy, tag, aspects.getAmount(tag), 0, this.field_73735_i); total++; }  ItemStack idisp = null; if (output instanceof ItemStack) { idisp = InventoryUtils.cycleItemStack((ItemStack)output); } else { idisp = InventoryUtils.cycleItemStack(ri.getRecipeInput()).func_77946_l(); try { Object[] obj = (Object[])output; NBTBase tag = (NBTBase)obj[1]; idisp.func_77983_a((String)obj[0], tag); } catch (Exception exception) {} }  drawStackAt(idisp, x - 8, y - 85, mx, my, false); ItemStack rinp = InventoryUtils.cycleItemStack(ri.getRecipeInput()).func_77946_l(); drawStackAt(rinp, x - 8, y - 16, mx, my, true); GL11.glPushMatrix(); GL11.glTranslated(0.0D, 0.0D, 100.0D); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); int le = components.size(); ArrayList<PosXY> coords = new ArrayList<PosXY>(); float pieSlice = (360 / le); float currentRot = -90.0F; for (int a = 0; a < le; a++) { int xx = (int)(MathHelper.func_76134_b(currentRot / 180.0F * 3.1415927F) * 40.0F) - 8; int yy = (int)(MathHelper.func_76126_a(currentRot / 180.0F * 3.1415927F) * 40.0F) - 8; currentRot += pieSlice; coords.add(new PosXY(xx, yy)); }  ArrayList<ItemStack> cmps = new ArrayList<ItemStack>(); total = 0; sx = x; sy = y - 8; for (Ingredient ingredient : components) { int vx = sx + ((PosXY)coords.get(total)).x; int vy = sy + ((PosXY)coords.get(total)).y; ItemStack is = InventoryUtils.cycleItemStack(ingredient); drawStackAt(is.func_77946_l().func_77979_a(1), vx, vy, mx, my, true); total++; cmps.add(is.func_77946_l()); }  GL11.glPopMatrix(); int inst = Math.min(5, ri.getInstability(this.field_146297_k.field_71439_g, rinp, cmps) / 2); text = I18n.func_74838_a("tc.inst") + " " + I18n.func_74838_a("tc.inst." + inst); offset = this.field_146297_k.field_71466_p.func_78256_a(text); this.field_146297_k.field_71466_p.func_78276_b(text, x - offset / 2, y + 94, 5263440); total = 0; rows = (aspects.size() - 1) / 5; shift = (5 - aspects.size() % 5) * 10; sx = x - 48; sy = y + 50 - 10 * rows; for (Aspect tag : aspects.getAspectsSortedByName()) { int m = 0; if (total / 5 >= rows && (rows > 1 || aspects.size() < 5)) m = 1;  int vx = sx + total % 5 * 20 + shift * m; int vy = sy + total / 5 * 20; if (mposx >= vx && mposy >= vy && mposx < vx + 16 && mposy < vy + 16) this.tipText = Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() });  total++; }  GL11.glPopMatrix(); }  } protected void func_73869_a(char par1, int par2) throws IOException { if (par2 == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i() || par2 == 1) { history.clear(); if (shownRecipe != null || this.showingAspects || this.showingKnowledge) { shownRecipe = null; this.blockAccess = null; this.showingAspects = false; this.showingKnowledge = false; (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.4F, 1.1F); } else { this.field_146297_k.func_147108_a(new GuiResearchBrowser(this.guiMapX, this.guiMapY)); }  } else if (par2 == 203 || par2 == 200 || par2 == 201) { prevPage(); } else if (par2 == 205 || par2 == 208 || par2 == 209) { nextPage(); } else if (par2 == 14) { goBack(); } else { super.func_73869_a(par1, par2); }  }
/*      */   private void nextPage() { if (this.page < this.maxPages - 2) { this.page += 2; this.lastCycle = 0L; this.cycle = -1; (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.66F, 1.0F); }  }
/*      */   private void prevPage() { if (this.page >= 2) { this.page -= 2; this.lastCycle = 0L; this.cycle = -1; (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.66F, 1.0F); }  }
/*      */   private void goBack() { if (!history.isEmpty()) { (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.66F, 1.0F); shownRecipe = (ResourceLocation)history.pop(); this.blockAccess = null; } else { shownRecipe = null; }  }
/*      */   protected void func_73864_a(int par1, int par2, int par3) { checkRequisites(); int var4 = (this.field_146294_l - this.paneWidth) / 2; int var5 = (this.field_146295_m - this.paneHeight) / 2; int mx = par1 - this.hrx; int my = par2 - this.hry; if (shownRecipe == null && !this.hold && this.hasAllRequisites && mx >= 0 && my >= 0 && mx < 64 && my < 12) { PacketHandler.INSTANCE.sendToServer(new PacketSyncProgressToServer(this.research.getKey(), false, true, true)); (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.write, 0.66F, 1.0F); this.lastCheck = 0L; this.lastStage = this.currentStage; this.hold = true; this.keyCache.clear(); this.drilldownLists.clear(); }  if (this.knownPlayerAspects != null && this.playerKnowledge.isResearchComplete("FIRSTSTEPS")) { mx = par1 - var4 - 48; my = par2 - var5 + 8; if (mx >= 0 && my >= 0 && mx < 25 && my < 16) { shownRecipe = null; this.showingKnowledge = false; this.showingAspects = !this.showingAspects; this.blockAccess = null; history.clear(); if (aspectsPage > this.maxAspectPages) aspectsPage = 0;  (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.7F, 0.9F); }  }  if (this.playerKnowledge.isResearchComplete("KNOWLEDGETYPES") && !this.research.getKey().equals("KNOWLEDGETYPES")) { mx = par1 - var4 - 48; my = par2 - var5 + 31; if (mx >= 0 && my >= 0 && mx < 25 && my < 16) { shownRecipe = null; this.showingAspects = false; this.showingKnowledge = !this.showingKnowledge; this.blockAccess = null; history.clear(); (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.7F, 0.9F); }  }  mx = par1 - var4 + 205; my = par2 - var5 + 192; if (this.showingAspects && aspectsPage < this.maxAspectPages - 1 && mx >= 0 && my >= 0 && mx < 14 && my < 14) { aspectsPage++; (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.7F, 0.9F); }  mx = par1 - var4 + 38; my = par2 - var5 + 192; if (this.showingAspects && aspectsPage > 0 && mx >= 0 && my >= 0 && mx < 14 && my < 14) { aspectsPage--; (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.7F, 0.9F); }  if (this.recipeLists.size() > 0) { int aa = 0; int space = Math.min(25, 200 / this.recipeLists.size()); for (ResourceLocation rk : this.recipeLists.keySet()) { mx = par1 - var4 + 280; my = par2 - var5 - 8 + aa * space; if (mx >= 0 && my >= 0 && mx < 30 && my < 16) { if (rk.equals(shownRecipe)) { shownRecipe = null; } else { shownRecipe = rk; }  this.showingAspects = false; this.showingKnowledge = false; this.blockAccess = null; history.clear(); (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.7F, 0.9F); break; }  aa++; }  }  mx = par1 - var4 + 205; my = par2 - var5 + 192; if (this.hasRecipePages && this.recipePage < this.recipePageMax && mx >= 0 && my >= 0 && mx < 14 && my < 14) { this.recipePage++; (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.7F, 0.9F); }  mx = par1 - var4 + 38; my = par2 - var5 + 192; if (this.hasRecipePages && this.recipePage > 0 && mx >= 0 && my >= 0 && mx < 14 && my < 14) { this.recipePage--; (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.7F, 0.9F); }  mx = par1 - var4 + 261; my = par2 - var5 + 189; if (shownRecipe == null && mx >= 0 && my >= 0 && mx < 14 && my < 10) nextPage();  mx = par1 - var4 - 17; my = par2 - var5 + 189; if (shownRecipe == null && mx >= 0 && my >= 0 && mx < 14 && my < 10) prevPage();  mx = par1 - var4 + 118; my = par2 - var5 + 190; if (mx >= 0 && my >= 0 && mx < 20 && my < 12) goBack();  mx = par1 - var4 + 210; my = par2 - var5 + 190; if (this.renderingCompound && mx >= 0 && my >= 0 && mx < 10 && my < 10) { (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.clack, 0.66F, 1.0F); cycleMultiblockLines = !cycleMultiblockLines; }  if (this.reference.size() > 0) for (List coords : this.reference) { if (par1 >= ((Integer)coords.get(0)).intValue() && par2 >= ((Integer)coords.get(1)).intValue() && par1 < ((Integer)coords.get(0)).intValue() + 16 && par2 < ((Integer)coords.get(1)).intValue() + 16) { try { (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.page, 0.66F, 1.0F); } catch (Exception exception) {} if (shownRecipe != null) history.push(new ResourceLocation(shownRecipe.func_110624_b(), shownRecipe.func_110623_a()));  shownRecipe = (ResourceLocation)coords.get(2); this.recipePage = Integer.parseInt((String)coords.get(3)); if (!this.drilldownLists.containsKey(shownRecipe)) addRecipesToList(shownRecipe, this.drilldownLists, new LinkedHashMap(), shownRecipe);  this.blockAccess = null; break; }  }   try { super.func_73864_a(par1, par2, par3); } catch (IOException iOException) {} }
/*      */   public boolean func_73868_f() { return false; }
/*      */   void drawPopupAt(int x, int y, int mx, int my, String text) { if ((shownRecipe == null || this.allowWithPagePopup) && mx >= x && my >= y && mx < x + 16 && my < y + 16) { ArrayList<String> s = new ArrayList<String>(); s.add(I18n.func_74838_a(text)); this.tipText = s; }  }
/*      */   void drawPopupAt(int x, int y, int w, int h, int mx, int my, String text) { if ((shownRecipe == null || this.allowWithPagePopup) && mx >= x && my >= y && mx < x + w && my < y + h) { ArrayList<String> s = new ArrayList<String>(); s.add(I18n.func_74838_a(text)); this.tipText = s; }  }
/*      */   boolean mouseInside(int x, int y, int w, int h, int mx, int my) { return (mx >= x && my >= y && mx < x + w && my < y + h); }
/*      */   void drawStackAt(ItemStack itemstack, int x, int y, int mx, int my, boolean clickthrough) { UtilsFX.renderItemStack(this.field_146297_k, itemstack, x, y, null); if ((shownRecipe == null || this.allowWithPagePopup) && mx >= x && my >= y && mx < x + 16 && my < y + 16 && itemstack != null && !itemstack.func_190926_b() && itemstack.func_77973_b() != null) if (clickthrough) { List addtext = itemstack.func_82840_a(this.field_146297_k.field_71439_g, (Minecraft.func_71410_x()).field_71474_y.field_82882_x ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL); String ref = getCraftingRecipeKey(this.field_146297_k.field_71439_g, itemstack); if (ref != null) { String[] sr = ref.split(";", 2); if (sr != null && sr.length > 1) { ResourceLocation res = new ResourceLocation(sr[0]); if (res.func_110623_a().equals("UNKNOWN")) { addtext.add(TextFormatting.DARK_RED + "" + TextFormatting.ITALIC + I18n.func_74838_a("recipe.unknown")); } else { addtext.add(TextFormatting.BLUE + "" + TextFormatting.ITALIC + I18n.func_74838_a("recipe.clickthrough")); this.reference.add(Arrays.asList(new Comparable[] { Integer.valueOf(mx), Integer.valueOf(my), res, sr[1] })); }  }  }  this.tipText = addtext; } else { this.tipText = itemstack.func_82840_a(this.field_146297_k.field_71439_g, (Minecraft.func_71410_x()).field_71474_y.field_82882_x ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL); }   }
/* 2423 */   private int findRecipePage(ResourceLocation rk, ItemStack stack, int start) { Object recipe = CommonInternals.getCatalogRecipe(rk);
/* 2424 */     if (recipe == null) recipe = CommonInternals.getCatalogRecipeFake(rk); 
/* 2425 */     if (recipe == null) recipe = CraftingManager.func_193373_a(rk); 
/* 2426 */     if (recipe == null) recipe = ConfigRecipes.recipeGroups.get(rk.toString()); 
/* 2427 */     if (recipe == null) return -1;
/*      */     
/* 2429 */     if (recipe instanceof ArrayList) {
/* 2430 */       int g = 0;
/* 2431 */       for (ResourceLocation rl : (ArrayList)recipe) {
/* 2432 */         int q = findRecipePage(rl, stack, g);
/* 2433 */         if (q >= 0) return q; 
/* 2434 */         g++;
/*      */       } 
/*      */     } 
/*      */     
/* 2438 */     if (recipe instanceof CrucibleRecipe && (
/* 2439 */       (CrucibleRecipe)recipe).getRecipeOutput().func_77969_a(stack)) {
/* 2440 */       if (!ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, new String[] { ((CrucibleRecipe)recipe).getResearch() })) return -99; 
/* 2441 */       return start;
/*      */     } 
/*      */ 
/*      */     
/* 2445 */     if (recipe instanceof InfusionRecipe && (
/* 2446 */       (InfusionRecipe)recipe).getRecipeOutput() instanceof ItemStack && ((ItemStack)((InfusionRecipe)recipe)
/* 2447 */       .getRecipeOutput()).func_77969_a(stack)) {
/* 2448 */       if (!ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, new String[] { ((InfusionRecipe)recipe).getResearch() })) return -99; 
/* 2449 */       return start;
/*      */     } 
/*      */ 
/*      */     
/* 2453 */     if (recipe instanceof IRecipe && (
/* 2454 */       (IRecipe)recipe).func_77571_b().func_77969_a(stack)) {
/* 2455 */       if (recipe instanceof IArcaneRecipe && !ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, new String[] { ((IArcaneRecipe)recipe).getResearch() })) return -99; 
/* 2456 */       return start;
/*      */     } 
/*      */ 
/*      */     
/* 2460 */     if (recipe instanceof RecipeMisc && (
/* 2461 */       (RecipeMisc)recipe).getOutput().func_77969_a(stack)) {
/* 2462 */       return start;
/*      */     }
/*      */ 
/*      */     
/* 2466 */     return -1; } public void drawTexturedModalRectScaled(int par1, int par2, int par3, int par4, int par5, int par6, float scale) { GL11.glPushMatrix(); float var7 = 0.00390625F; float var8 = 0.00390625F; Tessellator var9 = Tessellator.func_178181_a(); GL11.glTranslatef(par1 + par5 / 2.0F, par2 + par6 / 2.0F, 0.0F); GL11.glScalef(1.0F + scale, 1.0F + scale, 1.0F); var9.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g); var9.func_178180_c().func_181662_b((-par5 / 2.0F), (par6 / 2.0F), this.field_73735_i).func_187315_a(((par3 + 0) * var7), ((par4 + par6) * var8)).func_181675_d(); var9.func_178180_c().func_181662_b((par5 / 2.0F), (par6 / 2.0F), this.field_73735_i).func_187315_a(((par3 + par5) * var7), ((par4 + par6) * var8)).func_181675_d(); var9.func_178180_c().func_181662_b((par5 / 2.0F), (-par6 / 2.0F), this.field_73735_i).func_187315_a(((par3 + par5) * var7), ((par4 + 0) * var8)).func_181675_d(); var9.func_178180_c().func_181662_b((-par5 / 2.0F), (-par6 / 2.0F), this.field_73735_i).func_187315_a(((par3 + 0) * var7), ((par4 + 0) * var8)).func_181675_d(); var9.func_78381_a(); GL11.glPopMatrix(); } private class Page {
/*      */     ArrayList contents = new ArrayList(); public Page copy() { Page p = new Page(GuiResearchPage.this); p.contents.addAll(this.contents); return p; } private Page() {} } private static final PageImage PILINE = PageImage.parse("thaumcraft:textures/gui/gui_researchbook.png:24:184:95:6:1"); private static final PageImage PIDIV = PageImage.parse("thaumcraft:textures/gui/gui_researchbook.png:28:192:140:6:1"); private ArrayList<Page> pages; boolean isComplete; boolean hasAllRequisites; boolean[] hasItem; boolean[] hasCraft; boolean[] hasResearch; boolean[] hasKnow; boolean[] hasStats; public HashMap<Integer, String> keyCache;
/*      */   private void parsePages() { checkRequisites(); this.pages.clear(); if (this.research.getStages() == null) return;  boolean complete = false; this.currentStage = ThaumcraftCapabilities.getKnowledge(this.field_146297_k.field_71439_g).getResearchStage(this.research.getKey()) - 1; while (this.currentStage >= this.research.getStages().length) { this.currentStage--; complete = true; }  if (this.currentStage < 0) this.currentStage = 0;  ResearchStage stage = this.research.getStages()[this.currentStage]; ResearchAddendum[] addenda = null; if (this.research.getAddenda() != null && complete) addenda = this.research.getAddenda();  generateRecipesLists(stage, addenda); String rawText = stage.getTextLocalized(); if (addenda != null) { int ac = 0; for (ResearchAddendum addendum : addenda) { if (ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, addendum.getResearch())) { ac++; TextComponentTranslation textComponentTranslation = new TextComponentTranslation("tc.addendumtext", new Object[] { Integer.valueOf(ac) }); rawText = rawText + "<PAGE>" + textComponentTranslation.func_150254_d() + "<BR>" + addendum.getTextLocalized(); }  }  }  rawText = rawText.replaceAll("<BR>", "~B\n\n"); rawText = rawText.replaceAll("<BR/>", "~B\n\n"); rawText = rawText.replaceAll("<LINE>", "~L"); rawText = rawText.replaceAll("<LINE/>", "~L"); rawText = rawText.replaceAll("<DIV>", "~D"); rawText = rawText.replaceAll("<DIV/>", "~D"); rawText = rawText.replaceAll("<PAGE>", "~P"); rawText = rawText.replaceAll("<PAGE/>", "~P"); ArrayList<PageImage> images = new ArrayList<PageImage>(); String[] imgSplit = rawText.split("<IMG>"); for (String s : imgSplit) { int i = s.indexOf("</IMG>"); if (i >= 0) { String clean = s.substring(0, i); PageImage pi = PageImage.parse(clean); if (pi == null) { rawText = rawText.replaceFirst(clean, "\n"); } else { images.add(pi); rawText = rawText.replaceFirst(clean, "~I"); }  }  }  rawText = rawText.replaceAll("<IMG>", ""); rawText = rawText.replaceAll("</IMG>", ""); List<String> firstPassText = new ArrayList<String>(); String[] temp = rawText.split("~P"); for (int a = 0; a < temp.length; a++) { String t = temp[a]; String[] temp1 = t.split("~D"); for (int x = 0; x < temp1.length; x++) { String t1 = temp1[x]; String[] temp2 = t1.split("~L"); for (int b = 0; b < temp2.length; b++) { String t2 = temp2[b]; String[] temp3 = t2.split("~I"); for (int c = 0; c < temp3.length; c++) { String t3 = temp3[c]; firstPassText.add(t3); if (c != temp3.length - 1) firstPassText.add("~I");  }  if (b != temp2.length - 1) firstPassText.add("~L");  }  if (x != temp1.length - 1) firstPassText.add("~D");  }  if (a != temp.length - 1) firstPassText.add("~P");  }  List<String> parsedText = new ArrayList<String>(); for (String s : firstPassText) { List<String> pt1 = this.field_146297_k.field_71466_p.func_78271_c(s, 140); for (String ln : pt1) parsedText.add(ln);  }  int lineHeight = this.field_146297_k.field_71466_p.field_78288_b; int heightRemaining = 182; int dividerSpace = 0; if (this.research.getKey().equals("KNOWLEDGETYPES")) { heightRemaining -= 2; int tc = 0; int amt = 0; for (IPlayerKnowledge.EnumKnowledgeType type : IPlayerKnowledge.EnumKnowledgeType.values()) { for (ResearchCategory category : ResearchCategories.researchCategories.values()) { if (!type.hasFields() && category != null) continue;  amt = this.playerKnowledge.getKnowledgeRaw(type, category); if (amt > 0) { tc++; break; }  }  }  heightRemaining -= 20 * tc; dividerSpace = 12; }  if (!this.isComplete) { if (stage.getCraft() != null) { heightRemaining -= 18; dividerSpace = 15; }  if (stage.getObtain() != null) { heightRemaining -= 18; dividerSpace = 15; }  if (stage.getKnow() != null) { heightRemaining -= 18; dividerSpace = 15; }  if (stage.getResearch() != null) { heightRemaining -= 18; dividerSpace = 15; }  }  heightRemaining -= dividerSpace; Page page1 = new Page(null); ArrayList<PageImage> tempImages = new ArrayList<PageImage>(); for (String line : parsedText) { if (line.contains("~I")) { if (!images.isEmpty()) tempImages.add(images.remove(0));  line = ""; }  if (line.contains("~L")) { tempImages.add(PILINE); line = ""; }  if (line.contains("~D")) { tempImages.add(PIDIV); line = ""; }  if (line.contains("~P")) { heightRemaining = 210; this.pages.add(page1.copy()); page1 = new Page(null); line = ""; }  if (!line.isEmpty()) { line = line.trim(); page1.contents.add(line); heightRemaining -= lineHeight; if (line.endsWith("~B")) heightRemaining = (int)(heightRemaining - lineHeight * 0.66D);  }  while (!tempImages.isEmpty() && heightRemaining >= ((PageImage)tempImages.get(0)).ah + 2) { heightRemaining -= ((PageImage)tempImages.get(0)).ah + 2; page1.contents.add(tempImages.remove(0)); }  if (heightRemaining < lineHeight && !page1.contents.isEmpty()) { heightRemaining = 210; this.pages.add(page1.copy()); page1 = new Page(null); }  }  if (!page1.contents.isEmpty()) this.pages.add(page1.copy());  page1 = new Page(null); heightRemaining = 210; while (!tempImages.isEmpty()) { if (heightRemaining < ((PageImage)tempImages.get(0)).ah + 2) { heightRemaining = 210; this.pages.add(page1.copy()); page1 = new Page(null); continue; }  heightRemaining -= ((PageImage)tempImages.get(0)).ah + 2; page1.contents.add(tempImages.remove(0)); }  if (!page1.contents.isEmpty()) this.pages.add(page1.copy());  this.rhash = this.research.getKey().hashCode() + this.currentStage * 50; this.maxPages = this.pages.size(); }
/*      */   private static class PageImage {
/*      */     int x; int y; int w; int h; int aw; int ah; float scale; ResourceLocation loc;
/*      */     public static PageImage parse(String text) { String[] s = text.split(":"); if (s.length != 7) return null;  try { PageImage pi = new PageImage(); pi.loc = new ResourceLocation(s[0], s[1]); pi.x = Integer.parseInt(s[2]); pi.y = Integer.parseInt(s[3]); pi.w = Integer.parseInt(s[4]); pi.h = Integer.parseInt(s[5]); pi.scale = Float.parseFloat(s[6]); pi.aw = (int)(pi.w * pi.scale); pi.ah = (int)(pi.h * pi.scale); if (pi.ah > 208 || pi.aw > 140) return null;  return pi; } catch (Exception exception) { return null; }  } }
/* 2472 */   private String getCraftingRecipeKey(EntityPlayer player, ItemStack stack) { int key = stack.serializeNBT().toString().hashCode();
/* 2473 */     if (this.keyCache.containsKey(Integer.valueOf(key))) {
/* 2474 */       return (String)this.keyCache.get(Integer.valueOf(key));
/*      */     }
/*      */     
/* 2477 */     for (ResearchCategory rcl : ResearchCategories.researchCategories.values()) {
/* 2478 */       for (ResearchEntry ri : rcl.research.values()) {
/* 2479 */         if (ri.getStages() == null)
/* 2480 */           continue;  for (int a = 0; a < ri.getStages().length; a++) {
/* 2481 */           ResearchStage stage = ri.getStages()[a];
/* 2482 */           if (stage.getRecipes() != null) {
/* 2483 */             for (ResourceLocation rec : stage.getRecipes()) {
/* 2484 */               int result = findRecipePage(rec, stack, 0);
/* 2485 */               if (result != -1) {
/* 2486 */                 String s = rec.toString();
/* 2487 */                 if (result == -99) {
/* 2488 */                   s = (new ResourceLocation("UNKNOWN")).toString();
/*      */                 } else {
/* 2490 */                   s = s + ";" + result;
/*      */                 } 
/* 2492 */                 this.keyCache.put(Integer.valueOf(key), s);
/* 2493 */                 return s;
/*      */               } 
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/* 2500 */     this.keyCache.put(Integer.valueOf(key), null);
/* 2501 */     return null; }
/*      */   
/*      */   public static class BlueprintBlockAccess implements IBlockAccess { private final Part[][][] data;
/*      */     private IBlockState[][][] structure;
/*      */     public int sliceLine;
/*      */     
/*      */     public BlueprintBlockAccess(Part[][][] data, boolean target) {
/* 2508 */       this.sliceLine = 0;
/*      */ 
/*      */ 
/*      */       
/* 2512 */       this.data = new Part[data.length][data[0].length][data[0][0].length];
/* 2513 */       for (int y = 0; y < data.length; y++) {
/* 2514 */         for (int x = 0; x < data[0].length; x++) {
/* 2515 */           for (int z = 0; z < data[0][0].length; z++)
/* 2516 */             this.data[y][x][z] = data[y][x][z]; 
/*      */         } 
/* 2518 */       }  this.structure = new IBlockState[data.length][data[0].length][data[0][0].length];
/* 2519 */       if (target)
/* 2520 */         for (int y = 0; y < this.data.length; y++) {
/* 2521 */           Matrix matrix = new Matrix(this.data[y]);
/* 2522 */           matrix.Rotate90DegRight(3);
/* 2523 */           this.data[y] = matrix.getMatrix();
/*      */         }  
/* 2525 */       for (int y = 0; y < data.length; y++) {
/* 2526 */         for (int x = 0; x < data[0].length; x++) {
/* 2527 */           for (int z = 0; z < data[0][0].length; z++)
/* 2528 */             this.structure[data.length - y - 1][x][z] = target ? convertTarget(x, y, z) : convert(x, y, z); 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private IBlockState convert(int x, int y, int z) {
/* 2534 */       if (this.data[y][x][z] == null || this.data[y][x][z].getSource() == null) return Blocks.field_150350_a.func_176223_P(); 
/* 2535 */       if (this.data[y][x][z].getSource() instanceof ItemStack && 
/* 2536 */         Block.func_149634_a(((ItemStack)this.data[y][x][z].getSource()).func_77973_b()) != null) {
/* 2537 */         return Block.func_149634_a(((ItemStack)this.data[y][x][z].getSource()).func_77973_b())
/* 2538 */           .func_176203_a(((ItemStack)this.data[y][x][z].getSource()).func_77952_i());
/*      */       }
/* 2540 */       if (this.data[y][x][z].getSource() instanceof Block) {
/* 2541 */         return ((Block)this.data[y][x][z].getSource()).func_176223_P();
/*      */       }
/* 2543 */       if (this.data[y][x][z].getSource() instanceof IBlockState) {
/* 2544 */         return (IBlockState)this.data[y][x][z].getSource();
/*      */       }
/* 2546 */       if (this.data[y][x][z].getSource() instanceof Material) {
/* 2547 */         if ((Material)this.data[y][x][z].getSource() == Material.field_151587_i) {
/* 2548 */           return Blocks.field_150353_l.func_176223_P();
/*      */         }
/* 2550 */         if ((Material)this.data[y][x][z].getSource() == Material.field_151586_h) {
/* 2551 */           return Blocks.field_150355_j.func_176223_P();
/*      */         }
/*      */       } 
/* 2554 */       return Blocks.field_150350_a.func_176223_P();
/*      */     }
/*      */ 
/*      */     
/*      */     private IBlockState convertTarget(int x, int y, int z) {
/* 2559 */       if (this.data[y][x][z] == null) return Blocks.field_150350_a.func_176223_P(); 
/* 2560 */       if (this.data[y][x][z].getTarget() == null) return convert(x, y, z); 
/* 2561 */       if (this.data[y][x][z].getTarget() instanceof ItemStack && 
/* 2562 */         Block.func_149634_a(((ItemStack)this.data[y][x][z].getTarget()).func_77973_b()) != null) {
/* 2563 */         return Block.func_149634_a(((ItemStack)this.data[y][x][z].getTarget()).func_77973_b())
/* 2564 */           .func_176203_a(((ItemStack)this.data[y][x][z].getTarget()).func_77952_i());
/*      */       }
/* 2566 */       if (this.data[y][x][z].getTarget() instanceof Block) {
/* 2567 */         return ((Block)this.data[y][x][z].getTarget()).func_176223_P();
/*      */       }
/* 2569 */       if (this.data[y][x][z].getTarget() instanceof IBlockState) {
/* 2570 */         return (IBlockState)this.data[y][x][z].getTarget();
/*      */       }
/* 2572 */       if (this.data[y][x][z].getTarget() instanceof Material) {
/* 2573 */         if ((Material)this.data[y][x][z].getTarget() == Material.field_151587_i) {
/* 2574 */           return Blocks.field_150353_l.func_176223_P();
/*      */         }
/* 2576 */         if ((Material)this.data[y][x][z].getTarget() == Material.field_151586_h) {
/* 2577 */           return Blocks.field_150355_j.func_176223_P();
/*      */         }
/*      */       } 
/* 2580 */       return Blocks.field_150350_a.func_176223_P();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Nullable
/* 2587 */     public TileEntity func_175625_s(BlockPos pos) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2593 */     public int func_175626_b(BlockPos pos, int lightValue) { return 15728880; }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public IBlockState func_180495_p(BlockPos pos) {
/* 2599 */       int x = pos.func_177958_n();
/* 2600 */       int y = pos.func_177956_o();
/* 2601 */       int z = pos.func_177952_p();
/*      */       
/* 2603 */       if (this.sliceLine > this.structure.length) this.sliceLine = 0;
/*      */       
/* 2605 */       if (y >= 0 && y < this.structure.length - this.sliceLine && 
/* 2606 */         x >= 0 && x < this.structure[y].length && 
/* 2607 */         z >= 0 && z < this.structure[y][x].length)
/*      */       {
/* 2609 */         return this.structure[y][x][z];
/*      */       }
/* 2611 */       return Blocks.field_150350_a.func_176223_P();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2617 */     public boolean func_175623_d(BlockPos pos) { return (func_180495_p(pos).func_177230_c() == Blocks.field_150350_a); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2624 */     public int func_175627_a(BlockPos pos, EnumFacing direction) { return 0; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2630 */     public WorldType func_175624_G() { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2636 */     public boolean isSideSolid(BlockPos pos, EnumFacing side, boolean _default) { return false; }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2641 */     public Biome func_180494_b(BlockPos pos) { return null; } }
/*      */ 
/*      */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiResearchPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */