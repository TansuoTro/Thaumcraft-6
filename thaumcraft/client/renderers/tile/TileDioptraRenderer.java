/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.model.PositionTextureVertex;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.TexturedQuadTC;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileDioptra;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileDioptraRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  23 */   private final ResourceLocation gridTexture = new ResourceLocation("thaumcraft", "textures/misc/gridblock.png");
/*  24 */   private final ResourceLocation sideTexture = new ResourceLocation("thaumcraft", "textures/models/dioptra_side.png");
/*  25 */   private final float[] alphas = { 0.9F, 0.9F, 0.9F, 0.9F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/*  36 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/*     */     
/*  38 */     TileDioptra tco = (TileDioptra)te;
/*     */     
/*  40 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */ 
/*     */     
/*  43 */     GL11.glPushMatrix();
/*  44 */     GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
/*     */     
/*  46 */     GL11.glEnable(3042);
/*  47 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  49 */     float t = (this.field_147501_a.field_147551_g != null) ? (this.field_147501_a.field_147551_g.field_70173_aa + partialTicks) : 0.0F;
/*     */     
/*  51 */     float rc = 1.0F;
/*  52 */     float gc = 1.0F;
/*  53 */     float bc = 1.0F;
/*     */     
/*  55 */     if (BlockStateUtils.isEnabled(tco.func_145832_p())) {
/*  56 */       rc = MathHelper.func_76126_a(t / 12.0F) * 0.05F + 0.85F;
/*  57 */       gc = MathHelper.func_76126_a(t / 11.0F) * 0.05F + 0.9F;
/*  58 */       bc = MathHelper.func_76126_a(t / 10.0F) * 0.05F + 0.95F;
/*     */     } else {
/*  60 */       rc = MathHelper.func_76126_a(t / 12.0F) * 0.05F + 0.85F;
/*  61 */       gc = MathHelper.func_76126_a(t / 11.0F) * 0.05F + 0.45F;
/*  62 */       bc = MathHelper.func_76126_a(t / 10.0F) * 0.05F + 0.95F;
/*     */     } 
/*     */     
/*  65 */     GL11.glShadeModel(7425);
/*     */     
/*  67 */     GL11.glBlendFunc(770, 1);
/*  68 */     GL11.glPushMatrix();
/*  69 */     GL11.glTranslated(-0.495D, 0.501D, -0.495D);
/*  70 */     func_147499_a(this.gridTexture);
/*  71 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  72 */     GL11.glScaled(0.99D, 1.0D, 0.99D);
/*     */     
/*  74 */     for (int a = 0; a < 12; a++) {
/*  75 */       for (int b = 0; b < 12; b++) {
/*     */         
/*  77 */         int[] colors = calcColorMap(new float[] { 0.0F, 0.0F, 0.0F, 0.0F }, rc, gc, bc);
/*     */         
/*  79 */         double d3 = (a - 6);
/*  80 */         double d5 = (b - 6);
/*  81 */         double dis = Math.sqrt(d3 * d3 + d5 * d5);
/*  82 */         float s = MathHelper.func_76126_a((float)((tco.counter - dis * 10.0D) / 8.0D));
/*     */ 
/*     */         
/*  85 */         TexturedQuadTC quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex(a / 12.0F, tco.grid_amt[a + b * 13] / 96.0F, b / 12.0F, 0.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, tco.grid_amt[a + 1 + b * 13] / 96.0F, b / 12.0F, 1.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, tco.grid_amt[a + 1 + (b + 1) * 13] / 96.0F, (b + 1) / 12.0F, 1.0F, 0.0F), new PositionTextureVertex(a / 12.0F, tco.grid_amt[a + (b + 1) * 13] / 96.0F, (b + 1) / 12.0F, 0.0F, 0.0F) });
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  90 */         quad.flipFace();
/*  91 */         quad.draw(tessellator.func_178180_c(), 1.0F, (int)(200.0F + s * 15.0F), colors, this.alphas);
/*     */ 
/*     */ 
/*     */         
/*  95 */         if (a == 0) {
/*  96 */           quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex(0.0F, 0.0F, b / 12.0F, 0.0F, 1.0F), new PositionTextureVertex(0.0F, tco.grid_amt[b * 13] / 96.0F, b / 12.0F, 1.0F, 1.0F), new PositionTextureVertex(0.0F, tco.grid_amt[(b + 1) * 13] / 96.0F, (b + 1) / 12.0F, 1.0F, 0.0F), new PositionTextureVertex(0.0F, 0.0F, (b + 1) / 12.0F, 0.0F, 0.0F) });
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 101 */           quad.flipFace();
/* 102 */           quad.draw(tessellator.func_178180_c(), 1.0F, (int)(200.0F + s * 15.0F), colors, new float[] { 0.0F, 0.9F, 0.9F, 0.0F });
/*     */         } 
/*     */         
/* 105 */         if (a == 11) {
/* 106 */           quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex(1.0F, 0.0F, b / 12.0F, 0.0F, 1.0F), new PositionTextureVertex(1.0F, tco.grid_amt[a + 1 + b * 13] / 96.0F, b / 12.0F, 1.0F, 1.0F), new PositionTextureVertex(1.0F, tco.grid_amt[a + 1 + (b + 1) * 13] / 96.0F, (b + 1) / 12.0F, 1.0F, 0.0F), new PositionTextureVertex(1.0F, 0.0F, (b + 1) / 12.0F, 0.0F, 0.0F) });
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 111 */           quad.draw(tessellator.func_178180_c(), 1.0F, (int)(200.0F + s * 15.0F), colors, new float[] { 0.0F, 0.9F, 0.9F, 0.0F });
/*     */         } 
/*     */         
/* 114 */         if (b == 0) {
/* 115 */           quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex(a / 12.0F, 0.0F, 0.0F, 0.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, 0.0F, 0.0F, 1.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, tco.grid_amt[a + 1] / 96.0F, 0.0F, 1.0F, 0.0F), new PositionTextureVertex(a / 12.0F, tco.grid_amt[a] / 96.0F, 0.0F, 0.0F, 0.0F) });
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 120 */           quad.flipFace();
/* 121 */           quad.draw(tessellator.func_178180_c(), 1.0F, (int)(200.0F + s * 15.0F), colors, new float[] { 0.0F, 0.0F, 0.9F, 0.9F });
/*     */         } 
/*     */         
/* 124 */         if (b == 11) {
/* 125 */           quad = new TexturedQuadTC(new PositionTextureVertex[] { new PositionTextureVertex(a / 12.0F, 0.0F, 1.0F, 0.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, 0.0F, 1.0F, 1.0F, 1.0F), new PositionTextureVertex((a + 1) / 12.0F, tco.grid_amt[a + 1 + (b + 1) * 13] / 96.0F, 1.0F, 1.0F, 0.0F), new PositionTextureVertex(a / 12.0F, tco.grid_amt[a + (b + 1) * 13] / 96.0F, 1.0F, 0.0F, 0.0F) });
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 130 */           quad.draw(tessellator.func_178180_c(), 1.0F, (int)(200.0F + s * 15.0F), colors, new float[] { 0.0F, 0.0F, 0.9F, 0.9F });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     GL11.glPopMatrix();
/*     */     
/* 137 */     GL11.glPushMatrix();
/* 138 */     GlStateManager.func_179129_p();
/* 139 */     GL11.glTranslated(0.0D, 1.0D, 0.0D);
/* 140 */     GL11.glRotatef(270.0F, 0.0F, 0.0F, 1.0F);
/* 141 */     for (int q = 0; q < 4; q++) {
/* 142 */       GL11.glPushMatrix();
/* 143 */       GL11.glRotatef(90.0F * q, 1.0F, 0.0F, 0.0F);
/* 144 */       GL11.glTranslated(0.0D, 0.0D, -0.5D);
/* 145 */       UtilsFX.renderQuadCentered(this.sideTexture, 1.0F, rc, gc, bc, 220, 1, 0.8F);
/* 146 */       GL11.glPopMatrix();
/*     */     } 
/* 148 */     GlStateManager.func_179089_o();
/* 149 */     GL11.glPopMatrix();
/*     */     
/* 151 */     GL11.glShadeModel(7424);
/* 152 */     GL11.glDisable(3042);
/* 153 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   int[] calcColorMap(float[] fs, float r, float g, float b) {
/* 158 */     int[] colors = { 0, 0, 0, 0 };
/* 159 */     for (int a = 0; a < 4; a++) {
/* 160 */       float g1 = g;
/* 161 */       if (fs[a] > 0.0F) {
/* 162 */         float ll = 1.0F - fs[a];
/* 163 */         g1 *= ll;
/*     */       } 
/* 165 */       g1 = MathHelper.func_76131_a(g1, 0.0F, 1.0F);
/* 166 */       Color color1 = new Color(r * 0.8F, g1, b);
/* 167 */       colors[a] = color1.getRGB();
/*     */     } 
/* 169 */     return colors;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileDioptraRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */