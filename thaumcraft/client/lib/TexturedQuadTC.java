/*     */ package thaumcraft.client.lib;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.model.PositionTextureVertex;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ 
/*     */ public class TexturedQuadTC
/*     */ {
/*     */   public PositionTextureVertex[] vertexPositions;
/*     */   public int nVertices;
/*     */   
/*     */   public TexturedQuadTC(PositionTextureVertex[] vertices) {
/*  15 */     this.flipped = false;
/*     */ 
/*     */ 
/*     */     
/*  19 */     this.vertexPositions = vertices;
/*  20 */     this.nVertices = vertices.length;
/*     */   }
/*     */   private boolean invertNormal; private boolean flipped;
/*     */   
/*     */   public TexturedQuadTC(PositionTextureVertex[] vertices, int texcoordU1, int texcoordV1, int texcoordU2, int texcoordV2, float textureWidth, float textureHeight) {
/*  25 */     this(vertices);
/*  26 */     float f2 = 0.0F / textureWidth;
/*  27 */     float f3 = 0.0F / textureHeight;
/*  28 */     vertices[0] = vertices[0].func_78240_a(texcoordU2 / textureWidth - f2, texcoordV1 / textureHeight + f3);
/*  29 */     vertices[1] = vertices[1].func_78240_a(texcoordU1 / textureWidth + f2, texcoordV1 / textureHeight + f3);
/*  30 */     vertices[2] = vertices[2].func_78240_a(texcoordU1 / textureWidth + f2, texcoordV2 / textureHeight - f3);
/*  31 */     vertices[3] = vertices[3].func_78240_a(texcoordU2 / textureWidth - f2, texcoordV2 / textureHeight - f3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void flipFace() {
/*  37 */     this.flipped = true;
/*  38 */     PositionTextureVertex[] apositiontexturevertex = new PositionTextureVertex[this.vertexPositions.length];
/*     */     
/*  40 */     for (int i = 0; i < this.vertexPositions.length; i++)
/*     */     {
/*  42 */       apositiontexturevertex[i] = this.vertexPositions[this.vertexPositions.length - i - 1];
/*     */     }
/*     */     
/*  45 */     this.vertexPositions = apositiontexturevertex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(BufferBuilder renderer, float scale, int bright, int color, float alpha) {
/*  51 */     if (bright != -99) {
/*  52 */       renderer.func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/*     */     } else {
/*  54 */       renderer.func_181668_a(7, DefaultVertexFormats.field_181712_l);
/*     */     } 
/*  56 */     Color c = new Color(color);
/*     */     
/*  58 */     int aa = bright;
/*  59 */     int j = aa >> 16 & 0xFFFF;
/*  60 */     int k = aa & 0xFFFF;
/*     */     
/*  62 */     for (int i = 0; i < 4; i++) {
/*     */       
/*  64 */       PositionTextureVertex positiontexturevertex = this.vertexPositions[i];
/*  65 */       if (bright != -99) {
/*  66 */         renderer
/*  67 */           .func_181662_b(positiontexturevertex.field_78243_a.field_72450_a * scale, positiontexturevertex.field_78243_a.field_72448_b * scale, positiontexturevertex.field_78243_a.field_72449_c * scale)
/*  68 */           .func_187315_a(positiontexturevertex.field_78241_b, positiontexturevertex.field_78242_c)
/*  69 */           .func_181669_b(c.getRed(), c.getGreen(), c.getBlue(), (int)(alpha * 255.0F))
/*  70 */           .func_187314_a(j, k)
/*  71 */           .func_181663_c(0.0F, 0.0F, 1.0F)
/*  72 */           .func_181675_d();
/*     */       } else {
/*  74 */         renderer
/*  75 */           .func_181662_b(positiontexturevertex.field_78243_a.field_72450_a * scale, positiontexturevertex.field_78243_a.field_72448_b * scale, positiontexturevertex.field_78243_a.field_72449_c * scale)
/*  76 */           .func_187315_a(positiontexturevertex.field_78241_b, positiontexturevertex.field_78242_c)
/*  77 */           .func_181669_b(c.getRed(), c.getGreen(), c.getBlue(), (int)(alpha * 255.0F))
/*  78 */           .func_181663_c(0.0F, 0.0F, 1.0F)
/*  79 */           .func_181675_d();
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     Tessellator.func_178181_a().func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw(BufferBuilder renderer, float scale, int bright, int[] color, float[] alpha) {
/*  88 */     if (bright != -99) {
/*  89 */       renderer.func_181668_a(7, UtilsFX.VERTEXFORMAT_POS_TEX_CO_LM_NO);
/*     */     } else {
/*  91 */       renderer.func_181668_a(7, DefaultVertexFormats.field_181712_l);
/*     */     } 
/*  93 */     int aa = bright;
/*  94 */     int j = aa >> 16 & 0xFFFF;
/*  95 */     int k = aa & 0xFFFF;
/*     */     
/*  97 */     for (int i = 0; i < 4; i++) {
/*     */       
/*  99 */       int idx = this.flipped ? (3 - i) : i;
/* 100 */       Color c = new Color(color[idx]);
/* 101 */       PositionTextureVertex positiontexturevertex = this.vertexPositions[i];
/* 102 */       if (bright != -99) {
/* 103 */         renderer
/* 104 */           .func_181662_b(positiontexturevertex.field_78243_a.field_72450_a * scale, positiontexturevertex.field_78243_a.field_72448_b * scale, positiontexturevertex.field_78243_a.field_72449_c * scale)
/* 105 */           .func_187315_a(positiontexturevertex.field_78241_b, positiontexturevertex.field_78242_c)
/* 106 */           .func_181669_b(c.getRed(), c.getGreen(), c.getBlue(), (int)(alpha[idx] * 255.0F))
/* 107 */           .func_187314_a(j, k)
/* 108 */           .func_181663_c(0.0F, 0.0F, 1.0F)
/* 109 */           .func_181675_d();
/*     */       } else {
/* 111 */         renderer
/* 112 */           .func_181662_b(positiontexturevertex.field_78243_a.field_72450_a * scale, positiontexturevertex.field_78243_a.field_72448_b * scale, positiontexturevertex.field_78243_a.field_72449_c * scale)
/* 113 */           .func_187315_a(positiontexturevertex.field_78241_b, positiontexturevertex.field_78242_c)
/* 114 */           .func_181669_b(c.getRed(), c.getGreen(), c.getBlue(), (int)(alpha[idx] * 255.0F))
/* 115 */           .func_181663_c(0.0F, 0.0F, 1.0F)
/* 116 */           .func_181675_d();
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     Tessellator.func_178181_a().func_78381_a();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\TexturedQuadTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */