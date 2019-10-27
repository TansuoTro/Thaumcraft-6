/*     */ package thaumcraft.client.lib.events;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.casters.ICaster;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.items.casters.ItemFocus;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.world.aura.AuraChunk;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HudHandler
/*     */ {
/*  44 */   final ResourceLocation HUD = new ResourceLocation("thaumcraft", "textures/gui/hud.png");
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   void renderHuds(Minecraft mc, float renderTickTime, EntityPlayer player, long time) {
/*  49 */     GL11.glPushMatrix();
/*  50 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/*  51 */     GL11.glClear(256);
/*  52 */     GL11.glMatrixMode(5889);
/*  53 */     GL11.glLoadIdentity();
/*  54 */     GL11.glOrtho(0.0D, sr.func_78327_c(), sr.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
/*  55 */     GL11.glMatrixMode(5888);
/*  56 */     GL11.glLoadIdentity();
/*  57 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*  58 */     int ww = sr.func_78326_a();
/*  59 */     int hh = sr.func_78328_b();
/*  60 */     GL11.glEnable(3042);
/*  61 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  63 */     renderHudsInGUI(mc, renderTickTime, player, time, ww, hh);
/*     */     
/*  65 */     if (mc.field_71415_G) if (mc.func_71382_s()) {
/*     */         
/*  67 */         mc.field_71446_o.func_110577_a(this.HUD);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  73 */         ItemStack handStack = player.func_184614_ca();
/*  74 */         boolean rC = false;
/*  75 */         boolean rT = false;
/*  76 */         boolean rS = false;
/*  77 */         int start = 0;
/*  78 */         for (int a = 0; a < 2; a++) {
/*  79 */           if (handStack != null && !handStack.func_190926_b()) {
/*  80 */             if (!rC && handStack.func_77973_b() instanceof ICaster) {
/*  81 */               renderCastingWandHud(mc, renderTickTime, player, time, handStack, start);
/*  82 */               rC = true;
/*  83 */               if (!ModConfig.CONFIG_GRAPHICS.dialBottom) start += 33;
/*     */             
/*     */             }
/*  86 */             else if (!rT && handStack.func_77973_b() instanceof thaumcraft.common.items.tools.ItemThaumometer) {
/*  87 */               renderThaumometerHud(mc, renderTickTime, player, time, ww, hh, start);
/*  88 */               rT = true;
/*  89 */               start += 80;
/*     */             
/*     */             }
/*  92 */             else if (!rS && handStack.func_77973_b() instanceof thaumcraft.common.items.tools.ItemSanityChecker) {
/*  93 */               renderSanityHud(mc, Float.valueOf(renderTickTime), player, time, start);
/*  94 */               rS = true;
/*  95 */               start += 75;
/*     */             } 
/*     */           }
/*  98 */           handStack = player.func_184592_cb();
/*     */         } 
/*     */       } 
/*     */ 
/*     */     
/* 103 */     GL11.glDisable(3042);
/* 104 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   void renderHudsInGUI(Minecraft mc, float renderTickTime, EntityPlayer player, long time, int ww, int hh) {
/* 109 */     if (this.kgFade > 0.0F) {
/* 110 */       renderKnowledgeGains(mc, renderTickTime, player, time, ww, hh);
/*     */     }
/*     */   }
/*     */   
/* 114 */   public LinkedBlockingQueue<KnowledgeGainTracker> knowledgeGainTrackers = new LinkedBlockingQueue();
/*     */   
/*     */   public static class KnowledgeGainTracker {
/*     */     IPlayerKnowledge.EnumKnowledgeType type;
/*     */     ResearchCategory category;
/*     */     int progress;
/*     */     
/*     */     public KnowledgeGainTracker(IPlayerKnowledge.EnumKnowledgeType type, ResearchCategory category, int progress, long seed) {
/* 122 */       this.sparks = false;
/*     */ 
/*     */       
/* 125 */       this.type = type;
/* 126 */       this.category = category;
/* 127 */       if (type == IPlayerKnowledge.EnumKnowledgeType.THEORY) progress += 10;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 132 */       this.progress = progress;
/* 133 */       this.max = progress;
/* 134 */       this.seed = seed;
/*     */     }
/*     */     int max; long seed;
/*     */     boolean sparks; }
/* 138 */   public static final ResourceLocation BOOK = new ResourceLocation("thaumcraft", "textures/items/thaumonomicon.png");
/* 139 */   public static final ResourceLocation[] KNOW_TYPE = { new ResourceLocation("thaumcraft", "textures/research/knowledge_theory.png"), new ResourceLocation("thaumcraft", "textures/research/knowledge_observation.png") };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   float kgFade = 0.0F;
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   void renderKnowledgeGains(Minecraft mc, float renderTickTime, EntityPlayer player, long time, int ww, int hh) {
/* 149 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, this.kgFade / 40.0F);
/* 150 */     mc.field_71446_o.func_110577_a(BOOK);
/* 151 */     UtilsFX.drawTexturedQuadFull((ww - 17), (hh - 17), -90.0D);
/*     */     
/* 153 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 155 */     LinkedBlockingQueue<KnowledgeGainTracker> temp = new LinkedBlockingQueue<KnowledgeGainTracker>();
/* 156 */     int a = 0;
/* 157 */     while (!this.knowledgeGainTrackers.isEmpty()) {
/*     */       
/* 159 */       KnowledgeGainTracker current = (KnowledgeGainTracker)this.knowledgeGainTrackers.poll();
/* 160 */       if (current != null) {
/*     */ 
/*     */         
/* 163 */         mc.field_71446_o.func_110577_a(KNOW_TYPE[current.type.ordinal()]);
/*     */         
/* 165 */         Random rand = new Random(current.seed);
/* 166 */         GL11.glPushMatrix();
/*     */         
/* 168 */         float s = 16.0F;
/* 169 */         float x = (ww / 4 + rand.nextInt(32));
/* 170 */         float y = (hh / 3 + rand.nextInt(32));
/* 171 */         float wot = 0.0F;
/*     */         
/* 173 */         if (current.progress < current.max * 0.66F) {
/* 174 */           float q = (current.progress - renderTickTime) / current.max * 0.66F;
/* 175 */           s *= q;
/* 176 */           float m = (float)Math.sin(q * Math.PI - 1.5707963267948966D) * 0.5F + 0.5F;
/* 177 */           y *= m;
/* 178 */           float d = (float)Math.sin(m * Math.PI * 0.5D);
/* 179 */           x *= d;
/*     */         } else {
/* 181 */           wot = (current.max - current.progress) + renderTickTime;
/* 182 */           float wot2 = wot / current.max * 0.33F;
/* 183 */           float m = (float)Math.sin(wot2 * Math.PI * 2.0D - 1.5707963267948966D) * 0.5F + 1.5F;
/* 184 */           if (wot2 < 0.5D) s *= wot2 * 2.0F; 
/* 185 */           s *= m;
/*     */         } 
/*     */         
/* 188 */         float xx = (ww - 12 + rand.nextInt(8)) - x;
/* 189 */         float yy = (hh - 12 + rand.nextInt(8)) - y;
/*     */         
/* 191 */         if (current.sparks && player.func_70681_au().nextInt((int)(1.0F + current.progress / current.max * 10.0F)) == 0) {
/* 192 */           float r = MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 255, 255) / 255.0F;
/* 193 */           float g = MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 189, 255) / 255.0F;
/* 194 */           float b = MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 64, 255) / 255.0F;
/* 195 */           FXDispatcher.INSTANCE.drawSimpleSparkleGui(player.field_70170_p.field_73012_v, xx + player.field_70170_p.field_73012_v
/* 196 */               .nextGaussian() * 5.0D, yy + player.field_70170_p.field_73012_v.nextGaussian() * 5.0D, player.field_70170_p.field_73012_v
/* 197 */               .nextGaussian(), player.field_70170_p.field_73012_v.nextGaussian(), 24.0F, r, g, b, player.field_70170_p.field_73012_v
/* 198 */               .nextInt(5), 0.9F, -1.0F);
/*     */         } 
/*     */         
/* 201 */         GL11.glTranslatef(xx, yy, (-80 + a));
/*     */         
/* 203 */         GL11.glRotatef((84 + rand.nextInt(12)), 0.0F, 0.0F, -1.0F);
/* 204 */         UtilsFX.renderQuadCentered(1, 1, 0, s, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/*     */         
/* 206 */         if (current.category != null) {
/* 207 */           mc.field_71446_o.func_110577_a(current.category.icon);
/* 208 */           GL11.glTranslatef(0.0F, 0.0F, 1.0F);
/* 209 */           UtilsFX.renderQuadCentered(1, 1, 0, s * 0.75F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/*     */         } 
/*     */         
/* 212 */         if (current.progress > current.max * 0.9F) {
/* 213 */           float wot3 = wot / current.max * 0.1F;
/* 214 */           float m2 = (float)Math.sin(wot3 * Math.PI * 2.0D - 1.5707963267948966D) * 0.25F + 0.25F;
/* 215 */           float size = 64.0F * m2;
/* 216 */           GL11.glRotatef(rand.nextInt(360), 0.0F, 0.0F, -1.0F);
/* 217 */           mc.field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/* 218 */           float r = MathHelper.func_76136_a(rand, 255, 255) / 255.0F;
/* 219 */           float g = MathHelper.func_76136_a(rand, 189, 255) / 255.0F;
/* 220 */           float b = MathHelper.func_76136_a(rand, 64, 255) / 255.0F;
/* 221 */           UtilsFX.renderQuadCentered(64, 64, 320 + rand.nextInt(16), size, r, g, b, 200, 1, 1.0F);
/*     */         } 
/*     */         
/* 224 */         if (current.progress < current.max * 0.1F) {
/* 225 */           float wot3 = 1.0F - (current.progress - renderTickTime) / current.max * 0.1F;
/* 226 */           float m2 = (float)Math.sin(wot3 * Math.PI * 2.0D - 1.5707963267948966D) * 0.25F + 0.25F;
/* 227 */           float size = 32.0F * m2;
/* 228 */           GL11.glRotatef(rand.nextInt(360), 0.0F, 0.0F, -1.0F);
/* 229 */           mc.field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/* 230 */           float r = MathHelper.func_76136_a(rand, 255, 255) / 255.0F;
/* 231 */           float g = MathHelper.func_76136_a(rand, 189, 255) / 255.0F;
/* 232 */           float b = MathHelper.func_76136_a(rand, 64, 255) / 255.0F;
/* 233 */           UtilsFX.renderQuadCentered(64, 64, 320 + rand.nextInt(16), size, r, g, b, 200, 1, 1.0F);
/*     */         } 
/*     */ 
/*     */         
/* 237 */         temp.offer(current);
/* 238 */         GL11.glPopMatrix();
/*     */       } 
/*     */ 
/*     */       
/* 242 */       a++;
/*     */     } 
/*     */     
/* 245 */     while (!temp.isEmpty()) {
/* 246 */       this.knowledgeGainTrackers.offer(temp.poll());
/*     */     }
/*     */   }
/*     */   
/* 250 */   public static AuraChunk currentAura = new AuraChunk(null, (short)0, 0.0F, 0.0F);
/*     */   
/* 252 */   private final float VISCON = 525.0F;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   void renderThaumometerHud(Minecraft mc, float partialTicks, EntityPlayer player, long time, int ww, int hh, int shifty) {
/* 256 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 258 */     float base = MathHelper.func_76131_a(currentAura.getBase() / 525.0F, 0.0F, 1.0F);
/* 259 */     float vis = MathHelper.func_76131_a(currentAura.getVis() / 525.0F, 0.0F, 1.0F);
/* 260 */     float flux = MathHelper.func_76131_a(currentAura.getFlux() / 525.0F, 0.0F, 1.0F);
/* 261 */     float count = (Minecraft.func_71410_x().func_175606_aa()).field_70173_aa + partialTicks;
/* 262 */     float count2 = (Minecraft.func_71410_x().func_175606_aa()).field_70173_aa / 3.0F + partialTicks;
/*     */     
/* 264 */     if (flux + vis > 1.0F) {
/* 265 */       float m = 1.0F / (flux + vis);
/* 266 */       base *= m;
/* 267 */       vis *= m;
/* 268 */       flux *= m;
/*     */     } 
/*     */     
/* 271 */     float start = 10.0F + (1.0F - vis) * 64.0F;
/* 272 */     GL11.glPushMatrix();
/* 273 */     GL11.glEnable(3042);
/* 274 */     GL11.glBlendFunc(770, 771);
/* 275 */     GL11.glTranslated(2.0D, shifty, 0.0D);
/* 276 */     if (vis > 0.0F) {
/* 277 */       GL11.glPushMatrix();
/* 278 */       GL11.glColor4f(0.7F, 0.4F, 0.9F, 1.0F);
/* 279 */       GL11.glTranslated(5.0D, start, 0.0D);
/* 280 */       GL11.glScaled(1.0D, vis, 1.0D);
/* 281 */       UtilsFX.drawTexturedQuad(0.0F, 0.0F, 88.0F, 56.0F, 8.0F, 64.0F, -90.0D);
/* 282 */       GL11.glPopMatrix();
/*     */       
/* 284 */       GL11.glPushMatrix();
/* 285 */       GL11.glBlendFunc(770, 1);
/* 286 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/* 287 */       GL11.glTranslated(5.0D, start, 0.0D);
/* 288 */       UtilsFX.drawTexturedQuad(0.0F, 0.0F, 96.0F, 56.0F + count % 64.0F, 8.0F, vis * 64.0F, -90.0D);
/* 289 */       GL11.glBlendFunc(770, 771);
/* 290 */       GL11.glPopMatrix();
/*     */       
/* 292 */       if (player.func_70093_af()) {
/* 293 */         GL11.glPushMatrix();
/* 294 */         GL11.glTranslated(16.0D, start, 0.0D);
/* 295 */         GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 296 */         String msg = this.secondsFormatter.format(currentAura.getVis());
/* 297 */         mc.field_71456_v.func_73731_b(mc.field_71466_p, msg, 0, 0, 15641343);
/* 298 */         GL11.glPopMatrix();
/* 299 */         mc.field_71446_o.func_110577_a(this.HUD);
/*     */       } 
/*     */     } 
/*     */     
/* 303 */     if (flux > 0.0F) {
/* 304 */       start = 10.0F + (1.0F - flux - vis) * 64.0F;
/*     */       
/* 306 */       GL11.glPushMatrix();
/* 307 */       GL11.glColor4f(0.25F, 0.1F, 0.3F, 1.0F);
/* 308 */       GL11.glTranslated(5.0D, start, 0.0D);
/* 309 */       GL11.glScaled(1.0D, flux, 1.0D);
/* 310 */       UtilsFX.drawTexturedQuad(0.0F, 0.0F, 88.0F, 56.0F, 8.0F, 64.0F, -90.0D);
/* 311 */       GL11.glPopMatrix();
/*     */       
/* 313 */       GL11.glPushMatrix();
/* 314 */       GL11.glBlendFunc(770, 1);
/* 315 */       GL11.glColor4f(0.7F, 0.4F, 1.0F, 0.5F);
/* 316 */       GL11.glTranslated(5.0D, start, 0.0D);
/* 317 */       UtilsFX.drawTexturedQuad(0.0F, 0.0F, 104.0F, 120.0F - count2 % 64.0F, 8.0F, flux * 64.0F, -90.0D);
/* 318 */       GL11.glBlendFunc(770, 771);
/* 319 */       GL11.glPopMatrix();
/*     */       
/* 321 */       if (player.func_70093_af()) {
/* 322 */         GL11.glPushMatrix();
/* 323 */         GL11.glTranslated(16.0D, (start - 4.0F), 0.0D);
/* 324 */         GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 325 */         String msg = this.secondsFormatter.format(currentAura.getFlux());
/* 326 */         mc.field_71456_v.func_73731_b(mc.field_71466_p, msg, 0, 0, 11145659);
/* 327 */         GL11.glPopMatrix();
/* 328 */         mc.field_71446_o.func_110577_a(this.HUD);
/*     */       } 
/*     */     } 
/*     */     
/* 332 */     GL11.glPushMatrix();
/* 333 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 334 */     UtilsFX.drawTexturedQuad(1.0F, 1.0F, 72.0F, 48.0F, 16.0F, 80.0F, -90.0D);
/* 335 */     GL11.glPopMatrix();
/*     */     
/* 337 */     start = 8.0F + (1.0F - base) * 64.0F;
/* 338 */     GL11.glPushMatrix();
/* 339 */     UtilsFX.drawTexturedQuad(2.0F, start, 117.0F, 61.0F, 14.0F, 5.0F, -90.0D);
/* 340 */     GL11.glPopMatrix();
/*     */     
/* 342 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   void renderSanityHud(Minecraft mc, Float partialTicks, EntityPlayer player, long time, int shifty) {
/* 348 */     GL11.glPushMatrix();
/* 349 */     GL11.glEnable(3042);
/* 350 */     GL11.glBlendFunc(770, 771);
/* 351 */     GL11.glTranslated(0.0D, shifty, 0.0D);
/* 352 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 353 */     UtilsFX.drawTexturedQuad(1.0F, 1.0F, 152.0F, 0.0F, 20.0F, 76.0F, -90.0D);
/*     */ 
/*     */     
/* 356 */     int p = ThaumcraftCapabilities.getWarp(player).get(IPlayerWarp.EnumWarpType.PERMANENT);
/* 357 */     int s = ThaumcraftCapabilities.getWarp(player).get(IPlayerWarp.EnumWarpType.NORMAL);
/* 358 */     int t = ThaumcraftCapabilities.getWarp(player).get(IPlayerWarp.EnumWarpType.TEMPORARY);
/* 359 */     float tw = (p + s + t);
/* 360 */     float mod = 1.0F;
/* 361 */     if (tw > 100.0F) {
/* 362 */       mod = 100.0F / tw;
/* 363 */       tw = 100.0F;
/*     */     } 
/* 365 */     int gap = (int)((100.0F - tw) / 100.0F * 48.0F);
/* 366 */     int wt = (int)(t / 100.0F * 48.0F * mod);
/* 367 */     int ws = (int)(s / 100.0F * 48.0F * mod);
/*     */     
/* 369 */     if (t > 0) {
/* 370 */       GL11.glPushMatrix();
/* 371 */       GL11.glColor4f(1.0F, 0.5F, 1.0F, 1.0F);
/* 372 */       UtilsFX.drawTexturedQuad(7.0F, (21 + gap), 200.0F, gap, 8.0F, (wt + gap), -90.0D);
/* 373 */       GL11.glPopMatrix();
/*     */     } 
/* 375 */     if (s > 0) {
/* 376 */       GL11.glPushMatrix();
/* 377 */       GL11.glColor4f(0.75F, 0.0F, 0.75F, 1.0F);
/* 378 */       UtilsFX.drawTexturedQuad(7.0F, (21 + wt + gap), 200.0F, (wt + gap), 8.0F, (wt + ws + gap), -90.0D);
/* 379 */       GL11.glPopMatrix();
/*     */     } 
/* 381 */     if (p > 0) {
/* 382 */       GL11.glPushMatrix();
/* 383 */       GL11.glColor4f(0.5F, 0.0F, 0.5F, 1.0F);
/* 384 */       UtilsFX.drawTexturedQuad(7.0F, (21 + wt + ws + gap), 200.0F, (wt + ws + gap), 8.0F, 48.0F, -90.0D);
/* 385 */       GL11.glPopMatrix();
/*     */     } 
/* 387 */     GL11.glPushMatrix();
/* 388 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 389 */     UtilsFX.drawTexturedQuad(1.0F, 1.0F, 176.0F, 0.0F, 20.0F, 76.0F, -90.0D);
/* 390 */     GL11.glPopMatrix();
/* 391 */     if (tw >= 100.0F) {
/* 392 */       GL11.glPushMatrix();
/* 393 */       GL11.glScaled(0.75D, 0.75D, 1.0D);
/* 394 */       GL11.glTranslated(mc.field_71439_g.func_70681_au().nextInt(2), mc.field_71439_g.func_70681_au().nextInt(2), 0.0D);
/* 395 */       UtilsFX.drawTexturedQuad(3.0F, 3.0F, 216.0F, 0.0F, 20.0F, 16.0F, -90.0D);
/* 396 */       GL11.glPopMatrix();
/*     */     } 
/* 398 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   void renderChargeMeters(Minecraft mc, float renderTickTime, EntityPlayer player, long time, int ww, int hh) {
/* 406 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 407 */     int start = 0;
/* 408 */     int total = 0;
/*     */   }
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
/* 490 */   long nextsync = 0L;
/* 491 */   DecimalFormat secondsFormatter = new DecimalFormat("#######.#");
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   void renderCastingWandHud(Minecraft mc, float partialTicks, EntityPlayer player, long time, ItemStack wandstack, int shifty) {
/* 496 */     ICaster wand = (ICaster)wandstack.func_77973_b();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 506 */     short short1 = 240;
/* 507 */     short short2 = 240;
/* 508 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, short1 / 1.0F, short2 / 1.0F);
/* 509 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 511 */     GL11.glPushMatrix();
/*     */     
/* 513 */     GL11.glTranslatef(0.0F, shifty, 0.0F);
/*     */     
/* 515 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/* 516 */     GL11.glClear(256);
/* 517 */     GL11.glMatrixMode(5889);
/* 518 */     GL11.glLoadIdentity();
/* 519 */     GL11.glOrtho(0.0D, sr.func_78327_c(), sr.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
/* 520 */     GL11.glMatrixMode(5888);
/* 521 */     GL11.glLoadIdentity();
/* 522 */     int l = sr.func_78328_b();
/*     */     
/* 524 */     int dailLocation = ModConfig.CONFIG_GRAPHICS.dialBottom ? (l - 32) : 0;
/*     */     
/* 526 */     GL11.glTranslatef(0.0F, dailLocation, -2000.0F);
/*     */     
/* 528 */     GL11.glEnable(3042);
/* 529 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 531 */     mc.field_71446_o.func_110577_a(this.HUD);
/*     */     
/* 533 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 535 */     GL11.glPushMatrix();
/* 536 */     GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 537 */     UtilsFX.drawTexturedQuad(0.0F, 0.0F, 0.0F, 0.0F, 64.0F, 64.0F, -90.0D);
/* 538 */     GL11.glPopMatrix();
/*     */     
/* 540 */     GL11.glTranslatef(16.0F, 16.0F, 0.0F);
/*     */     
/* 542 */     int max = currentAura.getBase();
/* 543 */     int amt = (int)currentAura.getVis();
/*     */     
/* 545 */     ItemFocus focus = (ItemFocus)wand.getFocus(wandstack);
/* 546 */     ItemStack focusStack = wand.getFocusStack(wandstack);
/*     */     
/* 548 */     GL11.glPushMatrix();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 553 */     GL11.glTranslatef(16.0F, -10.0F, 0.0F);
/* 554 */     GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 555 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 557 */     int loc = (int)(30.0F * amt / max);
/*     */     
/* 559 */     GL11.glPushMatrix();
/* 560 */     Color ac = new Color(Aspect.ENERGY.getColor());
/* 561 */     GL11.glColor4f(ac.getRed() / 255.0F, ac.getGreen() / 255.0F, ac.getBlue() / 255.0F, 0.8F);
/* 562 */     UtilsFX.drawTexturedQuad(-4.0F, (35 - loc), 104.0F, 0.0F, 8.0F, loc, -90.0D);
/* 563 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 564 */     GL11.glPopMatrix();
/*     */     
/* 566 */     GL11.glPushMatrix();
/* 567 */     UtilsFX.drawTexturedQuad(-8.0F, -3.0F, 72.0F, 0.0F, 16.0F, 42.0F, -90.0D);
/* 568 */     GL11.glPopMatrix();
/*     */     
/* 570 */     int sh = 0;
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
/* 583 */     if (player.func_70093_af()) {
/* 584 */       GL11.glPushMatrix();
/* 585 */       GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/* 586 */       String msg = this.secondsFormatter.format(amt);
/* 587 */       mc.field_71456_v.func_73731_b(mc.field_71466_p, msg, -32, -4, 16777215);
/* 588 */       GL11.glPopMatrix();
/*     */       
/* 590 */       if (focus != null && focus.getVisCost(focusStack) > 0.0F) {
/* 591 */         float mod = wand.getConsumptionModifier(wandstack, player, false);
/* 592 */         GL11.glPushMatrix();
/*     */         
/* 594 */         msg = this.secondsFormatter.format((focus.getVisCost(focusStack) * mod));
/* 595 */         mc.field_71456_v.func_73731_b(mc.field_71466_p, msg, -32 - mc.field_71456_v.func_175179_f().func_78256_a(msg) / 2, 32, 16777215);
/* 596 */         GL11.glPopMatrix();
/*     */       } 
/*     */       
/* 599 */       mc.field_71446_o.func_110577_a(this.HUD);
/*     */     } 
/*     */     
/* 602 */     GL11.glPopMatrix();
/*     */ 
/*     */     
/* 605 */     if (focus != null) {
/* 606 */       ItemStack pickedStack = wand.getPickedBlock(player.field_71071_by.func_70448_g());
/* 607 */       if (pickedStack != null && !pickedStack.func_190926_b()) {
/* 608 */         renderWandTradeHud(partialTicks, player, time, pickedStack);
/*     */       } else {
/* 610 */         GL11.glPushMatrix();
/* 611 */         GL11.glTranslatef(-24.0F, -24.0F, 90.0F);
/* 612 */         RenderHelper.func_74520_c();
/* 613 */         GL11.glDisable(2896);
/* 614 */         GL11.glEnable(32826);
/* 615 */         GL11.glEnable(2903);
/* 616 */         GL11.glEnable(2896);
/*     */         try {
/* 618 */           mc.func_175599_af().func_180450_b(wand.getFocusStack(wandstack), 16, 16);
/* 619 */         } catch (Exception exception) {}
/* 620 */         GL11.glDisable(2896);
/* 621 */         GL11.glPopMatrix();
/*     */       } 
/*     */     } 
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
/* 666 */     GL11.glDisable(3042);
/*     */     
/* 668 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 674 */   ItemStack lastItem = null;
/* 675 */   int lastCount = 0;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderWandTradeHud(float partialTicks, EntityPlayer player, long time, ItemStack picked) {
/* 679 */     if (picked == null)
/* 680 */       return;  Minecraft mc = Minecraft.func_71410_x();
/* 681 */     int amount = this.lastCount;
/* 682 */     if (this.lastItem == null || this.lastItem.func_190926_b() || player.field_71071_by.func_194015_p() > 0 || !picked.func_77969_a(this.lastItem)) {
/* 683 */       amount = 0;
/* 684 */       for (ItemStack is : player.field_71071_by.field_70462_a) {
/* 685 */         if (is != null && !is.func_190926_b() && is.func_77969_a(picked)) {
/* 686 */           amount += is.func_190916_E();
/*     */         }
/*     */       } 
/* 689 */       this.lastItem = picked;
/* 690 */       player.field_71071_by.func_70296_d();
/*     */     } 
/* 692 */     this.lastCount = amount;
/*     */     
/* 694 */     GL11.glPushMatrix();
/* 695 */     RenderHelper.func_74520_c();
/* 696 */     GL11.glDisable(2896);
/* 697 */     GL11.glEnable(32826);
/* 698 */     GL11.glEnable(2903);
/* 699 */     GL11.glEnable(2896);
/*     */     try {
/* 701 */       mc.func_175599_af().func_180450_b(picked, -8, -8);
/* 702 */     } catch (Exception exception) {}
/* 703 */     GL11.glDisable(2896);
/*     */     
/* 705 */     GL11.glPushMatrix();
/* 706 */     String am = "" + amount;
/* 707 */     int sw = mc.field_71466_p.func_78256_a(am);
/* 708 */     GL11.glTranslatef(0.0F, -mc.field_71466_p.field_78288_b, 500.0F);
/* 709 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 710 */     for (int a = -1; a <= 1; ) { for (int b = -1; b <= 1; b++)
/* 711 */       { if ((a == 0 || b == 0) && (a != 0 || b != 0))
/* 712 */           mc.field_71466_p.func_78276_b(am, a + 16 - sw, b + 24, 0);  }  a++; }
/* 713 */      mc.field_71466_p.func_78276_b(am, 16 - sw, 24, 16777215);
/* 714 */     GL11.glPopMatrix();
/*     */     
/* 716 */     GL11.glPopMatrix();
/*     */   }
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
/* 791 */   final ResourceLocation TAGBACK = new ResourceLocation("thaumcraft", "textures/aspects/_back.png");
/*     */   
/*     */   public void renderAspectsInGui(GuiContainer gui, EntityPlayer player, ItemStack stack, int sd, int sx, int sy) {
/* 794 */     AspectList tags = ThaumcraftCraftingManager.getObjectTags(stack);
/* 795 */     if (tags == null)
/* 796 */       return;  GL11.glPushMatrix();
/* 797 */     int x = 0;
/* 798 */     int y = 0;
/* 799 */     int index = 0;
/* 800 */     if (tags.size() > 0)
/* 801 */       for (Aspect tag : tags.getAspectsSortedByAmount()) {
/* 802 */         if (tag != null) {
/* 803 */           x = sx + index * 18;
/* 804 */           y = sy + sd - 16;
/* 805 */           UtilsFX.drawTag(x, y, tag, tags.getAmount(tag), 0, gui.field_73735_i);
/* 806 */           index++;
/*     */         } 
/* 808 */       }   GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isMouseOverSlot(Slot par1Slot, int par2, int par3, int par4, int par5) {
/* 817 */     int var4 = par4;
/* 818 */     int var5 = par5;
/* 819 */     par2 -= var4;
/* 820 */     par3 -= var5;
/* 821 */     return (par2 >= par1Slot.field_75223_e - 1 && par2 < par1Slot.field_75223_e + 16 + 1 && par3 >= par1Slot.field_75221_f - 1 && par3 < par1Slot.field_75221_f + 16 + 1);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\events\HudHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */