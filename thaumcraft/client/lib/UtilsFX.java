/*      */ package thaumcraft.client.lib;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.gui.ScaledResolution;
/*      */ import net.minecraft.client.model.PositionTextureVertex;
/*      */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*      */ import net.minecraft.client.renderer.BufferBuilder;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.OpenGlHelper;
/*      */ import net.minecraft.client.renderer.RenderHelper;
/*      */ import net.minecraft.client.renderer.RenderItem;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.math.Vec3d;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.client.fx.ParticleEngine;
/*      */ import thaumcraft.common.config.ModConfig;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class UtilsFX
/*      */ {
/*   40 */   public static final ResourceLocation nodeTexture = new ResourceLocation("thaumcraft", "textures/misc/auranodes.png");
/*      */   
/*   42 */   public static final VertexFormat VERTEXFORMAT_POS_TEX_CO_LM_NO = (new VertexFormat())
/*   43 */     .func_181721_a(DefaultVertexFormats.field_181713_m)
/*   44 */     .func_181721_a(DefaultVertexFormats.field_181715_o)
/*   45 */     .func_181721_a(DefaultVertexFormats.field_181714_n)
/*   46 */     .func_181721_a(DefaultVertexFormats.field_181716_p)
/*   47 */     .func_181721_a(DefaultVertexFormats.field_181717_q)
/*   48 */     .func_181721_a(DefaultVertexFormats.field_181718_r);
/*      */   
/*      */   public static final String[] colorNames = { 
/*   51 */       "White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };
/*      */ 
/*      */   
/*      */   public static final String[] colorCodes = { 
/*   55 */       "§f", "§6", "§d", "§9", "§e", "§a", "§d", "§8", "§7", "§b", "§5", "§9", "§4", "§2", "§c", "§8" };
/*      */ 
/*      */   
/*      */   public static final int[] colors = { 
/*   59 */       15790320, 15435844, 12801229, 6719955, 14602026, 4312372, 14188952, 4408131, 10526880, 2651799, 8073150, 2437522, 5320730, 3887386, 11743532, 1973019 };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   64 */   public static float sysPartialTicks = 0.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderFacingQuad(double px, double py, double pz, int gridX, int gridY, int frame, float scale, int color, float alpha, int blend, float partialTicks) {
/*   99 */     if (Minecraft.func_71410_x().func_175606_aa() instanceof EntityPlayer) {
/*  100 */       Tessellator tessellator = Tessellator.func_178181_a();
/*  101 */       BufferBuilder wr = tessellator.func_178180_c();
/*  102 */       float arX = ActiveRenderInfo.func_178808_b();
/*  103 */       float arZ = ActiveRenderInfo.func_178803_d();
/*  104 */       float arYZ = ActiveRenderInfo.func_178805_e();
/*  105 */       float arXY = ActiveRenderInfo.func_178807_f();
/*  106 */       float arXZ = ActiveRenderInfo.func_178809_c();
/*      */       
/*  108 */       EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().func_175606_aa();
/*  109 */       double iPX = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * partialTicks;
/*  110 */       double iPY = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * partialTicks;
/*  111 */       double iPZ = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * partialTicks;
/*      */       
/*  113 */       GlStateManager.func_179094_E();
/*  114 */       GL11.glEnable(3042);
/*  115 */       GL11.glBlendFunc(770, blend);
/*  116 */       GlStateManager.func_179092_a(516, 0.003921569F);
/*  117 */       GlStateManager.func_179132_a(false);
/*      */       
/*  119 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       
/*  121 */       GL11.glTranslated(-iPX, -iPY, -iPZ);
/*      */       
/*  123 */       Vec3d v1 = new Vec3d((-arX * scale - arYZ * scale), (-arXZ * scale), (-arZ * scale - arXY * scale));
/*  124 */       Vec3d v2 = new Vec3d((-arX * scale + arYZ * scale), (arXZ * scale), (-arZ * scale + arXY * scale));
/*  125 */       Vec3d v3 = new Vec3d((arX * scale + arYZ * scale), (arXZ * scale), (arZ * scale + arXY * scale));
/*  126 */       Vec3d v4 = new Vec3d((arX * scale - arYZ * scale), (-arXZ * scale), (arZ * scale - arXY * scale));
/*      */       
/*  128 */       int xm = frame % gridX;
/*  129 */       int ym = frame / gridY;
/*      */       
/*  131 */       float f1 = xm / gridX;
/*  132 */       float f2 = f1 + 1.0F / gridX;
/*  133 */       float f3 = ym / gridY;
/*  134 */       float f4 = f3 + 1.0F / gridY;
/*      */       
/*  136 */       TexturedQuadTC quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex((float)px + (float)v1.field_72450_a, (float)py + (float)v1.field_72448_b, (float)pz + (float)v1.field_72449_c, f2, f4), new PositionTextureVertex((float)px + (float)v2.field_72450_a, (float)py + (float)v2.field_72448_b, (float)pz + (float)v2.field_72449_c, f2, f3), new PositionTextureVertex((float)px + (float)v3.field_72450_a, (float)py + (float)v3.field_72448_b, (float)pz + (float)v3.field_72449_c, f1, f3), new PositionTextureVertex((float)px + (float)v4.field_72450_a, (float)py + (float)v4.field_72448_b, (float)pz + (float)v4.field_72449_c, f1, f4) });
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  141 */       quad.draw(tessellator.func_178180_c(), 1.0F, 220, color, alpha);
/*      */       
/*  143 */       GlStateManager.func_179132_a(true);
/*  144 */       GL11.glBlendFunc(770, 771);
/*  145 */       GL11.glDisable(3042);
/*  146 */       GlStateManager.func_179092_a(516, 0.1F);
/*      */       
/*  148 */       GlStateManager.func_179121_F();
/*      */     } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawTexturedQuad(float par1, float par2, float par3, float par4, float par5, float par6, double zLevel) {
/*  276 */     float var7 = 0.00390625F;
/*  277 */     float var8 = 0.00390625F;
/*  278 */     Tessellator var9 = Tessellator.func_178181_a();
/*  279 */     var9.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
/*  280 */     var9.func_178180_c().func_181662_b((par1 + 0.0F), (par2 + par6), zLevel).func_187315_a(((par3 + 0.0F) * var7), ((par4 + par6) * var8)).func_181675_d();
/*  281 */     var9.func_178180_c().func_181662_b((par1 + par5), (par2 + par6), zLevel).func_187315_a(((par3 + par5) * var7), ((par4 + par6) * var8)).func_181675_d();
/*  282 */     var9.func_178180_c().func_181662_b((par1 + par5), (par2 + 0.0F), zLevel).func_187315_a(((par3 + par5) * var7), ((par4 + 0.0F) * var8)).func_181675_d();
/*  283 */     var9.func_178180_c().func_181662_b((par1 + 0.0F), (par2 + 0.0F), zLevel).func_187315_a(((par3 + 0.0F) * var7), ((par4 + 0.0F) * var8)).func_181675_d();
/*  284 */     var9.func_78381_a();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawTexturedQuadF(float par1, float par2, float par3, float par4, float par5, float par6, double zLevel) {
/*  289 */     float d = 0.0625F;
/*  290 */     Tessellator var9 = Tessellator.func_178181_a();
/*  291 */     var9.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
/*  292 */     var9.func_178180_c().func_181662_b((par1 + 0.0F), (par2 + 16.0F), zLevel).func_187315_a(((par3 + 0.0F) * d), ((par4 + par6) * d)).func_181675_d();
/*  293 */     var9.func_178180_c().func_181662_b((par1 + 16.0F), (par2 + 16.0F), zLevel).func_187315_a(((par3 + par5) * d), ((par4 + par6) * d)).func_181675_d();
/*  294 */     var9.func_178180_c().func_181662_b((par1 + 16.0F), (par2 + 0.0F), zLevel).func_187315_a(((par3 + par5) * d), ((par4 + 0.0F) * d)).func_181675_d();
/*  295 */     var9.func_178180_c().func_181662_b((par1 + 0.0F), (par2 + 0.0F), zLevel).func_187315_a(((par3 + 0.0F) * d), ((par4 + 0.0F) * d)).func_181675_d();
/*  296 */     var9.func_78381_a();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawTexturedQuadFull(float par1, float par2, double zLevel) {
/*  301 */     Tessellator var9 = Tessellator.func_178181_a();
/*  302 */     var9.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
/*  303 */     var9.func_178180_c().func_181662_b((par1 + 0.0F), (par2 + 16.0F), zLevel).func_187315_a(0.0D, 1.0D).func_181675_d();
/*  304 */     var9.func_178180_c().func_181662_b((par1 + 16.0F), (par2 + 16.0F), zLevel).func_187315_a(1.0D, 1.0D).func_181675_d();
/*  305 */     var9.func_178180_c().func_181662_b((par1 + 16.0F), (par2 + 0.0F), zLevel).func_187315_a(1.0D, 0.0D).func_181675_d();
/*  306 */     var9.func_178180_c().func_181662_b((par1 + 0.0F), (par2 + 0.0F), zLevel).func_187315_a(0.0D, 0.0D).func_181675_d();
/*  307 */     var9.func_78381_a();
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
/*      */   public static void renderItemInGUI(int x, int y, int z, ItemStack stack) {
/*  328 */     Minecraft mc = Minecraft.func_71410_x();
/*      */     try {
/*  330 */       GlStateManager.func_179094_E();
/*  331 */       RenderHelper.func_74520_c();
/*  332 */       GlStateManager.func_179140_f();
/*  333 */       GlStateManager.func_179091_B();
/*  334 */       GlStateManager.func_179142_g();
/*  335 */       GlStateManager.func_179145_e();
/*  336 */       (mc.func_175599_af()).field_77023_b = z;
/*  337 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/*  338 */       mc.func_175599_af().func_180450_b(stack, x, y);
/*  339 */       (mc.func_175599_af()).field_77023_b = 0.0F;
/*  340 */       GlStateManager.func_179140_f();
/*  341 */       GlStateManager.func_179121_F();
/*  342 */       GlStateManager.func_179145_e();
/*  343 */       GlStateManager.func_179126_j();
/*  344 */       RenderHelper.func_74519_b();
/*  345 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderQuadCentered(ResourceLocation texture, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
/*  351 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
/*  352 */     renderQuadCentered(1, 1, 0, scale, red, green, blue, brightness, blend, opacity);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderQuadCentered(ResourceLocation texture, int gridX, int gridY, int frame, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
/*  357 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
/*  358 */     renderQuadCentered(gridX, gridY, frame, scale, red, green, blue, brightness, blend, opacity);
/*      */   }
/*      */ 
/*      */   
/*  362 */   public static void renderQuadCentered() { renderQuadCentered(1, 1, 0, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderQuadCentered(int gridX, int gridY, int frame, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
/*  368 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  369 */     boolean blendon = GL11.glIsEnabled(3042);
/*  370 */     GL11.glEnable(3042);
/*  371 */     GL11.glBlendFunc(770, blend);
/*      */     
/*  373 */     int xm = frame % gridX;
/*  374 */     int ym = frame / gridY;
/*      */     
/*  376 */     float f1 = xm / gridX;
/*  377 */     float f2 = f1 + 1.0F / gridX;
/*  378 */     float f3 = ym / gridY;
/*  379 */     float f4 = f3 + 1.0F / gridY;
/*      */ 
/*      */     
/*  382 */     Color c = new Color(red, green, blue);
/*      */     
/*  384 */     TexturedQuadTC quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex(-0.5F, 0.5F, 0.0F, f2, f4), new PositionTextureVertex(0.5F, 0.5F, 0.0F, f2, f3), new PositionTextureVertex(0.5F, -0.5F, 0.0F, f1, f3), new PositionTextureVertex(-0.5F, -0.5F, 0.0F, f1, f4) });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  390 */     quad.draw(tessellator.func_178180_c(), scale, brightness, c.getRGB(), opacity);
/*      */     
/*  392 */     GL11.glBlendFunc(770, 771);
/*  393 */     if (!blendon) GL11.glDisable(3042);
/*      */   
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderQuadFromIcon(TextureAtlasSprite icon, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
/*  399 */     boolean blendon = GL11.glIsEnabled(3042);
/*  400 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(TextureMap.field_110575_b);
/*      */     
/*  402 */     Tessellator tessellator = Tessellator.func_178181_a();
/*      */     
/*  404 */     float f1 = icon.func_94212_f();
/*  405 */     float f2 = icon.func_94206_g();
/*  406 */     float f3 = icon.func_94209_e();
/*  407 */     float f4 = icon.func_94210_h();
/*      */     
/*  409 */     GL11.glScalef(scale, scale, scale);
/*  410 */     GL11.glEnable(3042);
/*  411 */     GL11.glBlendFunc(770, blend);
/*      */     
/*  413 */     GL11.glColor4f(red, green, blue, opacity);
/*      */     
/*  415 */     if (brightness > -1) {
/*  416 */       tessellator.func_178180_c().func_181668_a(7, VERTEXFORMAT_POS_TEX_CO_LM_NO);
/*      */     } else {
/*  418 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181712_l);
/*      */     } 
/*  420 */     int j = brightness >> 16 & 0xFFFF;
/*  421 */     int k = brightness & 0xFFFF;
/*      */     
/*  423 */     tessellator.func_178180_c().func_181662_b(0.0D, 0.0D, 0.0D).func_187315_a(f1, f4).func_181666_a(red, green, blue, opacity);
/*  424 */     if (brightness > -1) tessellator.func_178180_c().func_187314_a(j, k); 
/*  425 */     tessellator.func_178180_c().func_181663_c(0.0F, 0.0F, 1.0F);
/*  426 */     tessellator.func_178180_c().func_181675_d();
/*      */     
/*  428 */     tessellator.func_178180_c().func_181662_b(1.0D, 0.0D, 0.0D).func_187315_a(f3, f4).func_181666_a(red, green, blue, opacity);
/*  429 */     if (brightness > -1) tessellator.func_178180_c().func_187314_a(j, k); 
/*  430 */     tessellator.func_178180_c().func_181663_c(0.0F, 0.0F, 1.0F);
/*  431 */     tessellator.func_178180_c().func_181675_d();
/*      */     
/*  433 */     tessellator.func_178180_c().func_181662_b(1.0D, 1.0D, 0.0D).func_187315_a(f3, f2).func_181666_a(red, green, blue, opacity);
/*  434 */     if (brightness > -1) tessellator.func_178180_c().func_187314_a(j, k); 
/*  435 */     tessellator.func_178180_c().func_181663_c(0.0F, 0.0F, 1.0F);
/*  436 */     tessellator.func_178180_c().func_181675_d();
/*      */     
/*  438 */     tessellator.func_178180_c().func_181662_b(0.0D, 1.0D, 0.0D).func_187315_a(f1, f2).func_181666_a(red, green, blue, opacity);
/*  439 */     if (brightness > -1) tessellator.func_178180_c().func_187314_a(j, k); 
/*  440 */     tessellator.func_178180_c().func_181663_c(0.0F, 0.0F, 1.0F);
/*  441 */     tessellator.func_178180_c().func_181675_d();
/*      */     
/*  443 */     tessellator.func_78381_a();
/*  444 */     GlStateManager.func_179112_b(770, 771);
/*  445 */     if (!blendon) GL11.glDisable(3042);
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  452 */   public static void drawTag(int x, int y, Aspect aspect, float amount, int bonus, double z, int blend, float alpha) { drawTag(x, y, aspect, amount, bonus, z, blend, alpha, false); }
/*      */ 
/*      */ 
/*      */   
/*  456 */   public static void drawTag(int x, int y, Aspect aspect, float amt, int bonus, double z) { drawTag(x, y, aspect, amt, bonus, z, 771, 1.0F, false); }
/*      */ 
/*      */ 
/*      */   
/*  460 */   public static void drawTag(int x, int y, Aspect aspect) { drawTag(x, y, aspect, 0.0F, 0, 0.0D, 771, 1.0F, true); }
/*      */ 
/*      */   
/*  463 */   static DecimalFormat myFormatter = new DecimalFormat("#######.##");
/*      */ 
/*      */   
/*  466 */   public static void drawTag(int x, int y, Aspect aspect, float amount, int bonus, double z, int blend, float alpha, boolean bw) { drawTag(x, y, aspect, amount, bonus, z, blend, alpha, bw); }
/*      */ 
/*      */   
/*      */   public static void drawTag(double x, double y, Aspect aspect, float amount, int bonus, double z, int blend, float alpha, boolean bw) {
/*  470 */     if (aspect == null)
/*  471 */       return;  boolean blendon = GL11.glIsEnabled(3042);
/*  472 */     Minecraft mc = Minecraft.func_71410_x();
/*  473 */     boolean isLightingEnabled = GL11.glIsEnabled(2896);
/*      */ 
/*      */     
/*  476 */     Color color = new Color(aspect.getColor());
/*  477 */     GL11.glPushMatrix();
/*  478 */     GL11.glDisable(2896);
/*  479 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  480 */     GL11.glEnable(3042);
/*  481 */     GL11.glBlendFunc(770, blend);
/*      */     
/*  483 */     GL11.glPushMatrix();
/*      */     
/*  485 */     mc.field_71446_o.func_110577_a(aspect.getImage());
/*  486 */     if (!bw) {
/*  487 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, alpha);
/*      */     } else {
/*  489 */       GL11.glColor4f(0.1F, 0.1F, 0.1F, alpha * 0.8F);
/*      */     } 
/*      */     
/*  492 */     Tessellator var9 = Tessellator.func_178181_a();
/*      */     
/*  494 */     var9.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181709_i);
/*  495 */     if (!bw) {
/*  496 */       var9.func_178180_c().func_181662_b(x + 0.0D, y + 16.0D, z).func_187315_a(0.0D, 1.0D).func_181666_a(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, alpha).func_181675_d();
/*  497 */       var9.func_178180_c().func_181662_b(x + 16.0D, y + 16.0D, z).func_187315_a(1.0D, 1.0D).func_181666_a(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, alpha).func_181675_d();
/*  498 */       var9.func_178180_c().func_181662_b(x + 16.0D, y + 0.0D, z).func_187315_a(1.0D, 0.0D).func_181666_a(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, alpha).func_181675_d();
/*  499 */       var9.func_178180_c().func_181662_b(x + 0.0D, y + 0.0D, z).func_187315_a(0.0D, 0.0D).func_181666_a(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, alpha).func_181675_d();
/*      */     } else {
/*  501 */       var9.func_178180_c().func_181662_b(x + 0.0D, y + 16.0D, z).func_187315_a(0.0D, 1.0D).func_181666_a(0.1F, 0.1F, 0.1F, alpha * 0.8F).func_181675_d();
/*  502 */       var9.func_178180_c().func_181662_b(x + 16.0D, y + 16.0D, z).func_187315_a(1.0D, 1.0D).func_181666_a(0.1F, 0.1F, 0.1F, alpha * 0.8F).func_181675_d();
/*  503 */       var9.func_178180_c().func_181662_b(x + 16.0D, y + 0.0D, z).func_187315_a(1.0D, 0.0D).func_181666_a(0.1F, 0.1F, 0.1F, alpha * 0.8F).func_181675_d();
/*  504 */       var9.func_178180_c().func_181662_b(x + 0.0D, y + 0.0D, z).func_187315_a(0.0D, 0.0D).func_181666_a(0.1F, 0.1F, 0.1F, alpha * 0.8F).func_181675_d();
/*      */     } 
/*  506 */     var9.func_78381_a();
/*      */     
/*  508 */     GL11.glPopMatrix();
/*      */     
/*  510 */     if (amount > 0.0F) {
/*  511 */       GL11.glPushMatrix();
/*  512 */       float q = 0.5F;
/*  513 */       if (!ModConfig.CONFIG_GRAPHICS.largeTagText) {
/*  514 */         GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  515 */         q = 1.0F;
/*      */       } 
/*  517 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  518 */       String am = myFormatter.format(amount);
/*  519 */       int sw = mc.field_71466_p.func_78256_a(am);
/*      */       
/*  521 */       for (EnumFacing e : EnumFacing.field_176754_o) {
/*  522 */         mc.field_71466_p.func_175065_a(am, (32 - sw + (int)x * 2) * q + e.func_82601_c(), (32 - mc.field_71466_p.field_78288_b + (int)y * 2) * q + e.func_82599_e(), 0, false);
/*      */       }
/*      */       
/*  525 */       mc.field_71466_p.func_175065_a(am, (32 - sw + (int)x * 2) * q, (32 - mc.field_71466_p.field_78288_b + (int)y * 2) * q, 16777215, false);
/*  526 */       GL11.glPopMatrix();
/*      */     } 
/*      */     
/*  529 */     if (bonus > 0) {
/*  530 */       GL11.glPushMatrix();
/*  531 */       mc.field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/*  532 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  533 */       int px = 16 * mc.field_71439_g.field_70173_aa % 16;
/*  534 */       drawTexturedQuad(((int)x - 4), ((int)y - 4), px, 80.0F, 16.0F, 16.0F, z);
/*  535 */       if (bonus > 1) {
/*  536 */         float q = 0.5F;
/*  537 */         if (!ModConfig.CONFIG_GRAPHICS.largeTagText) {
/*  538 */           GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  539 */           q = 1.0F;
/*      */         } 
/*  541 */         String am = "" + bonus;
/*  542 */         int sw = mc.field_71466_p.func_78256_a(am) / 2;
/*  543 */         GL11.glTranslated(0.0D, 0.0D, -1.0D);
/*  544 */         mc.field_71466_p.func_175063_a(am, (8 - sw + (int)x * 2) * q, (15 - mc.field_71466_p.field_78288_b + (int)y * 2) * q, 16777215);
/*      */       } 
/*  546 */       GL11.glPopMatrix();
/*      */     } 
/*  548 */     GlStateManager.func_179112_b(770, 771);
/*  549 */     if (!blendon) {
/*  550 */       GL11.glDisable(3042);
/*      */     }
/*  552 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  553 */     GL11.glAlphaFunc(516, 0.1F);
/*  554 */     if (isLightingEnabled) {
/*  555 */       GL11.glEnable(2896);
/*      */     }
/*      */ 
/*      */     
/*  559 */     GL11.glPopMatrix();
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
/*      */ 
/*      */ 
/*      */   
/*  593 */   public static void drawCustomTooltip(GuiScreen gui, FontRenderer fr, List textList, int x, int y, int subTipColor) { drawCustomTooltip(gui, fr, textList, x, y, subTipColor, false); }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawCustomTooltip(GuiScreen gui, FontRenderer fr, List textList, int x, int y, int subTipColor, boolean ignoremouse) {
/*  598 */     if (!textList.isEmpty()) {
/*      */       
/*  600 */       Minecraft mc = Minecraft.func_71410_x();
/*  601 */       ScaledResolution scaledresolution = new ScaledResolution(mc);
/*  602 */       int sf = scaledresolution.func_78325_e();
/*  603 */       GlStateManager.func_179101_C();
/*  604 */       RenderHelper.func_74518_a();
/*  605 */       GlStateManager.func_179140_f();
/*  606 */       GlStateManager.func_179097_i();
/*  607 */       int max = 240;
/*  608 */       int mx = Mouse.getEventX();
/*  609 */       boolean flip = false;
/*  610 */       if (!ignoremouse && (max + 24) * sf + mx > mc.field_71443_c) {
/*  611 */         max = (mc.field_71443_c - mx) / sf - 24;
/*  612 */         if (max < 120) {
/*  613 */           max = 240;
/*  614 */           flip = true;
/*      */         } 
/*      */       } 
/*      */       
/*  618 */       int widestLineWidth = 0;
/*  619 */       Iterator textLineEntry = textList.iterator();
/*  620 */       boolean b = false;
/*  621 */       while (textLineEntry.hasNext()) {
/*      */         
/*  623 */         String textLine = (String)textLineEntry.next();
/*  624 */         if (fr.func_78256_a(textLine) > max) {
/*  625 */           b = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  630 */       if (b) {
/*  631 */         List tl = new ArrayList();
/*  632 */         for (Object o : textList) {
/*  633 */           String textLine = (String)o;
/*  634 */           List tl2 = fr.func_78271_c(textLine, textLine.startsWith("@@") ? (max * 2) : max);
/*  635 */           for (Object o2 : tl2) {
/*  636 */             String textLine2 = ((String)o2).trim();
/*  637 */             if (textLine.startsWith("@@")) textLine2 = "@@" + textLine2; 
/*  638 */             tl.add(textLine2);
/*      */           } 
/*      */         } 
/*  641 */         textList = tl;
/*      */       } 
/*      */       
/*  644 */       Iterator textLines = textList.iterator();
/*      */       
/*  646 */       int totalHeight = -2;
/*      */       
/*  648 */       while (textLines.hasNext()) {
/*      */         
/*  650 */         String textLine = (String)textLines.next();
/*  651 */         int lineWidth = fr.func_78256_a(textLine);
/*  652 */         if (textLine.startsWith("@@") && !fr.func_82883_a())
/*      */         {
/*  654 */           lineWidth /= 2;
/*      */         }
/*  656 */         if (lineWidth > widestLineWidth)
/*      */         {
/*  658 */           widestLineWidth = lineWidth;
/*      */         }
/*  660 */         totalHeight += ((textLine.startsWith("@@") && !fr.func_82883_a()) ? 7 : 10);
/*      */       } 
/*      */       
/*  663 */       int sX = x + 12;
/*  664 */       int sY = y - 12;
/*      */       
/*  666 */       if (textList.size() > 1)
/*      */       {
/*  668 */         totalHeight += 2;
/*      */       }
/*      */       
/*  671 */       if (sY + totalHeight > scaledresolution.func_78328_b()) sY = scaledresolution.func_78328_b() - totalHeight - 5;
/*      */       
/*  673 */       if (flip) sX -= widestLineWidth + 24;
/*      */       
/*  675 */       (Minecraft.func_71410_x().func_175599_af()).field_77023_b = 300.0F;
/*  676 */       int var10 = -267386864;
/*  677 */       drawGradientRect(sX - 3, sY - 4, sX + widestLineWidth + 3, sY - 3, var10, var10);
/*  678 */       drawGradientRect(sX - 3, sY + totalHeight + 3, sX + widestLineWidth + 3, sY + totalHeight + 4, var10, var10);
/*  679 */       drawGradientRect(sX - 3, sY - 3, sX + widestLineWidth + 3, sY + totalHeight + 3, var10, var10);
/*  680 */       drawGradientRect(sX - 4, sY - 3, sX - 3, sY + totalHeight + 3, var10, var10);
/*  681 */       drawGradientRect(sX + widestLineWidth + 3, sY - 3, sX + widestLineWidth + 4, sY + totalHeight + 3, var10, var10);
/*  682 */       int var11 = 1347420415;
/*  683 */       int var12 = (var11 & 0xFEFEFE) >> 1 | var11 & 0xFF000000;
/*  684 */       drawGradientRect(sX - 3, sY - 3 + 1, sX - 3 + 1, sY + totalHeight + 3 - 1, var11, var12);
/*  685 */       drawGradientRect(sX + widestLineWidth + 2, sY - 3 + 1, sX + widestLineWidth + 3, sY + totalHeight + 3 - 1, var11, var12);
/*  686 */       drawGradientRect(sX - 3, sY - 3, sX + widestLineWidth + 3, sY - 3 + 1, var11, var11);
/*  687 */       drawGradientRect(sX - 3, sY + totalHeight + 2, sX + widestLineWidth + 3, sY + totalHeight + 3, var12, var12);
/*      */       
/*  689 */       for (int i = 0; i < textList.size(); i++) {
/*      */         
/*  691 */         GL11.glPushMatrix();
/*  692 */         GL11.glTranslatef(sX, sY, 0.0F);
/*      */         
/*  694 */         String tl = (String)textList.get(i);
/*  695 */         boolean shift = false;
/*      */         
/*  697 */         GL11.glPushMatrix();
/*  698 */         if (tl.startsWith("@@") && !fr.func_82883_a()) {
/*  699 */           sY += 7;
/*  700 */           GL11.glScalef(0.5F, 0.5F, 1.0F);
/*  701 */           shift = true;
/*      */         } else {
/*  703 */           sY += 10;
/*      */         } 
/*  705 */         tl = tl.replaceAll("@@", "");
/*      */         
/*  707 */         if (subTipColor != -99) {
/*  708 */           if (i == 0) {
/*      */             
/*  710 */             tl = "§" + Integer.toHexString(subTipColor) + tl;
/*      */           }
/*      */           else {
/*      */             
/*  714 */             tl = "§7" + tl;
/*      */           } 
/*      */         }
/*  717 */         GL11.glTranslated(0.0D, 0.0D, 301.0D);
/*  718 */         fr.func_175063_a(tl, 0.0F, shift ? 3.0F : 0.0F, -1);
/*  719 */         GL11.glPopMatrix();
/*      */         
/*  721 */         if (i == 0)
/*      */         {
/*  723 */           sY += 2;
/*      */         }
/*      */         
/*  726 */         GL11.glPopMatrix();
/*      */       } 
/*      */       
/*  729 */       (Minecraft.func_71410_x().func_175599_af()).field_77023_b = 0.0F;
/*  730 */       GlStateManager.func_179145_e();
/*  731 */       GlStateManager.func_179126_j();
/*  732 */       RenderHelper.func_74519_b();
/*  733 */       GlStateManager.func_179091_B();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6) {
/*  742 */     boolean blendon = GL11.glIsEnabled(3042);
/*  743 */     float var7 = (par5 >> 24 & 0xFF) / 255.0F;
/*  744 */     float var8 = (par5 >> 16 & 0xFF) / 255.0F;
/*  745 */     float var9 = (par5 >> 8 & 0xFF) / 255.0F;
/*  746 */     float var10 = (par5 & 0xFF) / 255.0F;
/*  747 */     float var11 = (par6 >> 24 & 0xFF) / 255.0F;
/*  748 */     float var12 = (par6 >> 16 & 0xFF) / 255.0F;
/*  749 */     float var13 = (par6 >> 8 & 0xFF) / 255.0F;
/*  750 */     float var14 = (par6 & 0xFF) / 255.0F;
/*  751 */     GL11.glDisable(3553);
/*  752 */     GL11.glEnable(3042);
/*  753 */     GL11.glDisable(3008);
/*  754 */     GL11.glBlendFunc(770, 771);
/*  755 */     GL11.glShadeModel(7425);
/*  756 */     Tessellator var15 = Tessellator.func_178181_a();
/*  757 */     var15.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/*  758 */     var15.func_178180_c().func_181662_b(par3, par2, 300.0D).func_181666_a(var8, var9, var10, var7).func_181675_d();
/*  759 */     var15.func_178180_c().func_181662_b(par1, par2, 300.0D).func_181666_a(var8, var9, var10, var7).func_181675_d();
/*  760 */     var15.func_178180_c().func_181662_b(par1, par4, 300.0D).func_181666_a(var12, var13, var14, var11).func_181675_d();
/*  761 */     var15.func_178180_c().func_181662_b(par3, par4, 300.0D).func_181666_a(var12, var13, var14, var11).func_181675_d();
/*  762 */     var15.func_78381_a();
/*  763 */     GL11.glShadeModel(7424);
/*  764 */     GlStateManager.func_179112_b(770, 771);
/*  765 */     if (!blendon) GL11.glDisable(3042); 
/*  766 */     GL11.glEnable(3008);
/*  767 */     GL11.glEnable(3553);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderBillboardQuad(double scale) {
/* 1026 */     GL11.glPushMatrix();
/* 1027 */     rotateToPlayer();
/* 1028 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1029 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181710_j);
/* 1030 */     tessellator.func_178180_c().func_181662_b(-scale, -scale, 0.0D).func_187315_a(0.0D, 0.0D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 1031 */     tessellator.func_178180_c().func_181662_b(-scale, scale, 0.0D).func_187315_a(0.0D, 1.0D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 1032 */     tessellator.func_178180_c().func_181662_b(scale, scale, 0.0D).func_187315_a(1.0D, 1.0D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 1033 */     tessellator.func_178180_c().func_181662_b(scale, -scale, 0.0D).func_187315_a(1.0D, 0.0D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 1034 */     tessellator.func_78381_a();
/* 1035 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   public static void renderBillboardQuad(double scale, int gridX, int gridY, int frame) {
/* 1039 */     GL11.glPushMatrix();
/* 1040 */     rotateToPlayer();
/*      */     
/* 1042 */     int xm = frame % gridX;
/* 1043 */     int ym = frame / gridY;
/*      */     
/* 1045 */     float f1 = xm / gridX;
/* 1046 */     float f2 = f1 + 1.0F / gridX;
/* 1047 */     float f3 = ym / gridY;
/* 1048 */     float f4 = f3 + 1.0F / gridY;
/*      */     
/* 1050 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1051 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181710_j);
/* 1052 */     tessellator.func_178180_c().func_181662_b(-scale, -scale, 0.0D).func_187315_a(f2, f4).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 1053 */     tessellator.func_178180_c().func_181662_b(-scale, scale, 0.0D).func_187315_a(f2, f3).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 1054 */     tessellator.func_178180_c().func_181662_b(scale, scale, 0.0D).func_187315_a(f1, f3).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 1055 */     tessellator.func_178180_c().func_181662_b(scale, -scale, 0.0D).func_187315_a(f1, f4).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 1056 */     tessellator.func_78381_a();
/* 1057 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderBillboardQuad(double scale, int gridX, int gridY, int frame, float r, float g, float b, float a, int bright) {
/* 1062 */     GL11.glPushMatrix();
/* 1063 */     rotateToPlayer();
/*      */     
/* 1065 */     int xm = frame % gridX;
/* 1066 */     int ym = frame / gridY;
/*      */     
/* 1068 */     float f1 = xm / gridX;
/* 1069 */     float f2 = f1 + 1.0F / gridX;
/* 1070 */     float f3 = ym / gridY;
/* 1071 */     float f4 = f3 + 1.0F / gridY;
/* 1072 */     int j = bright >> 16 & 0xFFFF;
/* 1073 */     int k = bright & 0xFFFF;
/* 1074 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1075 */     tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181704_d);
/* 1076 */     tessellator.func_178180_c().func_181662_b(-scale, -scale, 0.0D).func_187315_a(f2, f4).func_181666_a(r, g, b, a).func_187314_a(j, k).func_181675_d();
/* 1077 */     tessellator.func_178180_c().func_181662_b(-scale, scale, 0.0D).func_187315_a(f2, f3).func_181666_a(r, g, b, a).func_187314_a(j, k).func_181675_d();
/* 1078 */     tessellator.func_178180_c().func_181662_b(scale, scale, 0.0D).func_187315_a(f1, f3).func_181666_a(r, g, b, a).func_187314_a(j, k).func_181675_d();
/* 1079 */     tessellator.func_178180_c().func_181662_b(scale, -scale, 0.0D).func_187315_a(f1, f4).func_181666_a(r, g, b, a).func_187314_a(j, k).func_181675_d();
/* 1080 */     tessellator.func_78381_a();
/* 1081 */     GL11.glPopMatrix();
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
/*      */   public static void rotateToPlayer() {
/* 1103 */     GL11.glRotatef(-(Minecraft.func_71410_x().func_175598_ae()).field_78735_i, 0.0F, 1.0F, 0.0F);
/* 1104 */     GL11.glRotatef((Minecraft.func_71410_x().func_175598_ae()).field_78732_j, 1.0F, 0.0F, 0.0F);
/*      */   }
/*      */   
/*      */   public static boolean hideStackOverlay = false;
/*      */   
/*      */   public static boolean renderItemStack(Minecraft mc, ItemStack itm, int x, int y, String txt) {
/* 1110 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 1111 */     RenderItem itemRender = mc.func_175599_af();
/* 1112 */     boolean isLightingEnabled = GL11.glIsEnabled(2896);
/*      */     
/* 1114 */     boolean rc = false;
/*      */     
/* 1116 */     if (itm != null && !itm.func_190926_b()) {
/* 1117 */       rc = true;
/* 1118 */       boolean isRescaleNormalEnabled = GL11.glIsEnabled(32826);
/* 1119 */       GL11.glPushMatrix();
/* 1120 */       GL11.glTranslatef(0.0F, 0.0F, 32.0F);
/* 1121 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1122 */       GL11.glEnable(32826);
/* 1123 */       GL11.glEnable(2896);
/* 1124 */       short short1 = 240;
/* 1125 */       short short2 = 240;
/* 1126 */       RenderHelper.func_74520_c();
/* 1127 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, short1 / 1.0F, short2 / 1.0F);
/* 1128 */       itemRender.func_180450_b(itm, x, y);
/* 1129 */       if (!hideStackOverlay) itemRender.func_180453_a(mc.field_71466_p, itm, x, y, txt); 
/* 1130 */       GL11.glPopMatrix();
/* 1131 */       if (isRescaleNormalEnabled) {
/* 1132 */         GL11.glEnable(32826);
/*      */       } else {
/* 1134 */         GL11.glDisable(32826);
/*      */       } 
/*      */     } 
/*      */     
/* 1138 */     if (isLightingEnabled) {
/* 1139 */       GL11.glEnable(2896);
/*      */     } else {
/* 1141 */       GL11.glDisable(2896);
/*      */     } 
/*      */     
/* 1144 */     return rc;
/*      */   }
/*      */   
/*      */   public static boolean renderItemStackShaded(Minecraft mc, ItemStack itm, int x, int y, String txt, float shade) {
/* 1148 */     GlStateManager.func_179131_c(shade, shade, shade, shade);
/* 1149 */     RenderItem itemRender = mc.func_175599_af();
/* 1150 */     boolean isLightingEnabled = GL11.glIsEnabled(2896);
/*      */     
/* 1152 */     boolean rc = false;
/*      */     
/* 1154 */     if (itm != null && !itm.func_190926_b()) {
/* 1155 */       rc = true;
/* 1156 */       boolean isRescaleNormalEnabled = GL11.glIsEnabled(32826);
/* 1157 */       GL11.glPushMatrix();
/* 1158 */       GL11.glTranslatef(0.0F, 0.0F, 32.0F);
/* 1159 */       GlStateManager.func_179131_c(shade, shade, shade, shade);
/* 1160 */       GL11.glEnable(32826);
/* 1161 */       GL11.glEnable(2896);
/* 1162 */       short short1 = 240;
/* 1163 */       short short2 = 240;
/* 1164 */       RenderHelper.func_74520_c();
/* 1165 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, short1 / 1.0F, short2 / 1.0F);
/* 1166 */       itemRender.func_180450_b(itm, x, y);
/* 1167 */       itemRender.func_180453_a(mc.field_71466_p, itm, x, y, txt);
/* 1168 */       GL11.glPopMatrix();
/* 1169 */       if (isRescaleNormalEnabled) {
/* 1170 */         GL11.glEnable(32826);
/*      */       } else {
/* 1172 */         GL11.glDisable(32826);
/*      */       } 
/*      */     } 
/*      */     
/* 1176 */     if (isLightingEnabled) {
/* 1177 */       GL11.glEnable(2896);
/*      */     } else {
/* 1179 */       GL11.glDisable(2896);
/*      */     } 
/* 1181 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 1182 */     return rc;
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
/* 1208 */   public static void drawBeam(Vector S, Vector E, Vector P, float width, int bright) { drawBeam(S, E, P, width, bright, 1.0F, 1.0F, 1.0F, 1.0F); }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawBeam(Vector S, Vector E, Vector P, float width, int bright, float r, float g, float b, float a) {
/* 1213 */     Vector PS = Sub(S, P);
/* 1214 */     Vector SE = Sub(E, S);
/*      */     
/* 1216 */     Vector normal = Cross(PS, SE);
/* 1217 */     normal = normal.normalize();
/*      */     
/* 1219 */     Vector half = Mul(normal, width);
/* 1220 */     Vector p1 = Add(S, half);
/* 1221 */     Vector p2 = Sub(S, half);
/* 1222 */     Vector p3 = Add(E, half);
/* 1223 */     Vector p4 = Sub(E, half);
/*      */     
/* 1225 */     drawQuad(Tessellator.func_178181_a(), p1, p3, p4, p2, bright, r, g, b, a);
/*      */   }
/*      */   
/*      */   public static void drawQuad(Tessellator tessellator, Vector p1, Vector p2, Vector p3, Vector p4, int bright, float r, float g, float b, float a) {
/* 1229 */     int j = bright >> 16 & 0xFFFF;
/* 1230 */     int k = bright & 0xFFFF;
/* 1231 */     tessellator.func_178180_c().func_181662_b(p1.getX(), p1.getY(), p1.getZ()).func_187315_a(0.0D, 0.0D).func_187314_a(j, k).func_181666_a(r, g, b, a).func_181675_d();
/* 1232 */     tessellator.func_178180_c().func_181662_b(p2.getX(), p2.getY(), p2.getZ()).func_187315_a(1.0D, 0.0D).func_187314_a(j, k).func_181666_a(r, g, b, a).func_181675_d();
/* 1233 */     tessellator.func_178180_c().func_181662_b(p3.getX(), p3.getY(), p3.getZ()).func_187315_a(1.0D, 1.0D).func_187314_a(j, k).func_181666_a(r, g, b, a).func_181675_d();
/* 1234 */     tessellator.func_178180_c().func_181662_b(p4.getX(), p4.getY(), p4.getZ()).func_187315_a(0.0D, 1.0D).func_187314_a(j, k).func_181666_a(r, g, b, a).func_181675_d();
/*      */   }
/*      */   
/*      */   public static class Vector {
/*      */     public final float x;
/*      */     public final float y;
/*      */     public final float z;
/*      */     
/*      */     public Vector(float x, float y, float z) {
/* 1243 */       this.x = x;
/* 1244 */       this.y = y;
/* 1245 */       this.z = z;
/*      */     }
/*      */ 
/*      */     
/* 1249 */     public float getX() { return this.x; }
/*      */ 
/*      */ 
/*      */     
/* 1253 */     public float getY() { return this.y; }
/*      */ 
/*      */ 
/*      */     
/* 1257 */     public float getZ() { return this.z; }
/*      */ 
/*      */ 
/*      */     
/* 1261 */     public float norm() { return (float)Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z)); }
/*      */ 
/*      */     
/*      */     public Vector normalize() {
/* 1265 */       float n = norm();
/* 1266 */       return new Vector(this.x / n, this.y / n, this.z / n);
/*      */     }
/*      */   }
/*      */   
/*      */   private static Vector Cross(Vector a, Vector b) {
/* 1271 */     float x = a.y * b.z - a.z * b.y;
/* 1272 */     float y = a.z * b.x - a.x * b.z;
/* 1273 */     float z = a.x * b.y - a.y * b.x;
/* 1274 */     return new Vector(x, y, z);
/*      */   }
/*      */ 
/*      */   
/* 1278 */   public static Vector Sub(Vector a, Vector b) { return new Vector(a.x - b.x, a.y - b.y, a.z - b.z); }
/*      */ 
/*      */   
/* 1281 */   private static Vector Add(Vector a, Vector b) { return new Vector(a.x + b.x, a.y + b.y, a.z + b.z); }
/*      */ 
/*      */   
/* 1284 */   private static Vector Mul(Vector a, float f) { return new Vector(a.x * f, a.y * f, a.z * f); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1289 */   public static void renderItemIn2D(String sprite, float thickness) { renderItemIn2D(Minecraft.func_71410_x().func_147117_R().func_110572_b(sprite), thickness); }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderItemIn2D(TextureAtlasSprite icon, float thickness) {
/* 1294 */     GL11.glPushMatrix();
/* 1295 */     float f1 = icon.func_94212_f();
/* 1296 */     float f2 = icon.func_94206_g();
/* 1297 */     float f3 = icon.func_94209_e();
/* 1298 */     float f4 = icon.func_94210_h();
/* 1299 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(TextureMap.field_110575_b);
/*      */     
/* 1301 */     renderTextureIn3D(f1, f2, f3, f4, 16, 16, thickness);
/*      */     
/* 1303 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   public static void renderTextureIn3D(float maxu, float maxv, float minu, float minv, int width, int height, float thickness) {
/* 1307 */     Tessellator tess = Tessellator.func_178181_a();
/* 1308 */     BufferBuilder wr = tess.func_178180_c();
/* 1309 */     wr.func_181668_a(7, DefaultVertexFormats.field_181710_j);
/* 1310 */     wr.func_181662_b(0.0D, 0.0D, 0.0D).func_187315_a(maxu, minv).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
/* 1311 */     wr.func_181662_b(1.0D, 0.0D, 0.0D).func_187315_a(minu, minv).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
/* 1312 */     wr.func_181662_b(1.0D, 1.0D, 0.0D).func_187315_a(minu, maxv).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
/* 1313 */     wr.func_181662_b(0.0D, 1.0D, 0.0D).func_187315_a(maxu, maxv).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
/* 1314 */     tess.func_78381_a();
/* 1315 */     wr.func_181668_a(7, DefaultVertexFormats.field_181710_j);
/* 1316 */     wr.func_181662_b(0.0D, 1.0D, (0.0F - thickness)).func_187315_a(maxu, maxv).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/* 1317 */     wr.func_181662_b(1.0D, 1.0D, (0.0F - thickness)).func_187315_a(minu, maxv).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/* 1318 */     wr.func_181662_b(1.0D, 0.0D, (0.0F - thickness)).func_187315_a(minu, minv).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/* 1319 */     wr.func_181662_b(0.0D, 0.0D, (0.0F - thickness)).func_187315_a(maxu, minv).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
/* 1320 */     tess.func_78381_a();
/* 1321 */     float f5 = 0.5F * (maxu - minu) / width;
/* 1322 */     float f6 = 0.5F * (minv - maxv) / height;
/* 1323 */     wr.func_181668_a(7, DefaultVertexFormats.field_181710_j);
/*      */ 
/*      */     
/*      */     int k;
/*      */     
/* 1328 */     for (k = 0; k < width; k++) {
/*      */       
/* 1330 */       float f7 = k / width;
/* 1331 */       float f8 = maxu + (minu - maxu) * f7 - f5;
/* 1332 */       wr.func_181662_b(f7, 0.0D, (0.0F - thickness)).func_187315_a(f8, minv).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
/* 1333 */       wr.func_181662_b(f7, 0.0D, 0.0D).func_187315_a(f8, minv).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
/* 1334 */       wr.func_181662_b(f7, 1.0D, 0.0D).func_187315_a(f8, maxv).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
/* 1335 */       wr.func_181662_b(f7, 1.0D, (0.0F - thickness)).func_187315_a(f8, maxv).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
/*      */     } 
/*      */     
/* 1338 */     tess.func_78381_a();
/* 1339 */     wr.func_181668_a(7, DefaultVertexFormats.field_181710_j);
/*      */ 
/*      */     
/* 1342 */     for (k = 0; k < width; k++) {
/*      */       
/* 1344 */       float f7 = k / width;
/* 1345 */       float f8 = maxu + (minu - maxu) * f7 - f5;
/* 1346 */       float f9 = f7 + 1.0F / width;
/* 1347 */       wr.func_181662_b(f9, 1.0D, (0.0F - thickness)).func_187315_a(f8, maxv).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
/* 1348 */       wr.func_181662_b(f9, 1.0D, 0.0D).func_187315_a(f8, maxv).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
/* 1349 */       wr.func_181662_b(f9, 0.0D, 0.0D).func_187315_a(f8, minv).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
/* 1350 */       wr.func_181662_b(f9, 0.0D, (0.0F - thickness)).func_187315_a(f8, minv).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
/*      */     } 
/*      */     
/* 1353 */     tess.func_78381_a();
/* 1354 */     wr.func_181668_a(7, DefaultVertexFormats.field_181710_j);
/*      */     
/* 1356 */     for (k = 0; k < height; k++) {
/*      */       
/* 1358 */       float f7 = k / height;
/* 1359 */       float f8 = minv + (maxv - minv) * f7 - f6;
/* 1360 */       float f9 = f7 + 1.0F / height;
/* 1361 */       wr.func_181662_b(0.0D, f9, 0.0D).func_187315_a(maxu, f8).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 1362 */       wr.func_181662_b(1.0D, f9, 0.0D).func_187315_a(minu, f8).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 1363 */       wr.func_181662_b(1.0D, f9, (0.0F - thickness)).func_187315_a(minu, f8).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/* 1364 */       wr.func_181662_b(0.0D, f9, (0.0F - thickness)).func_187315_a(maxu, f8).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
/*      */     } 
/*      */     
/* 1367 */     tess.func_78381_a();
/* 1368 */     wr.func_181668_a(7, DefaultVertexFormats.field_181710_j);
/*      */     
/* 1370 */     for (k = 0; k < height; k++) {
/*      */       
/* 1372 */       float f7 = k / height;
/* 1373 */       float f8 = minv + (maxv - minv) * f7 - f6;
/* 1374 */       wr.func_181662_b(1.0D, f7, 0.0D).func_187315_a(minu, f8).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
/* 1375 */       wr.func_181662_b(0.0D, f7, 0.0D).func_187315_a(maxu, f8).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
/* 1376 */       wr.func_181662_b(0.0D, f7, (0.0F - thickness)).func_187315_a(maxu, f8).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
/* 1377 */       wr.func_181662_b(1.0D, f7, (0.0F - thickness)).func_187315_a(minu, f8).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
/*      */     } 
/*      */     
/* 1380 */     tess.func_78381_a();
/*      */   }
/*      */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\UtilsFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */