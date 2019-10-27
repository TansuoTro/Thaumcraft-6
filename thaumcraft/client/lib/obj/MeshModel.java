/*     */ package thaumcraft.client.lib.obj;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector2f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.block.model.FaceBakery;
/*     */ import net.minecraft.client.renderer.block.model.ModelManager;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import thaumcraft.codechicken.lib.vec.Rotation;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MeshModel
/*     */ {
/*     */   public List<Vector3f> positions;
/*     */   public List<Vector3f> normals;
/*     */   public List<Vector2f> texCoords;
/*  26 */   public List<MeshPart> parts = new ArrayList();
/*     */ 
/*     */   
/*     */   public MeshModel clone() {
/*  30 */     MeshModel mm = new MeshModel();
/*  31 */     mm.parts = new ArrayList();
/*     */     
/*  33 */     for (MeshPart mp : this.parts) mm.parts.add(mp); 
/*  34 */     if (this.positions != null) {
/*  35 */       mm.positions = new ArrayList();
/*  36 */       for (Vector3f mp : this.positions) mm.positions.add((Vector3f)mp.clone()); 
/*     */     } 
/*  38 */     if (this.normals != null) {
/*  39 */       mm.normals = new ArrayList();
/*  40 */       for (Vector3f mp : this.normals) mm.normals.add((Vector3f)mp.clone()); 
/*     */     } 
/*  42 */     if (this.texCoords != null) {
/*  43 */       mm.texCoords = new ArrayList();
/*  44 */       for (Vector2f mp : this.texCoords) mm.texCoords.add((Vector2f)mp.clone()); 
/*     */     } 
/*  46 */     return mm;
/*     */   }
/*     */   
/*     */   public void rotate(double d, Vector3 axis, Vector3 offset) {
/*  50 */     Rotation r = new Rotation(d, axis);
/*  51 */     List<Vector3f> p = new ArrayList<Vector3f>();
/*  52 */     for (Vector3f v : this.positions) {
/*  53 */       Vector3 vec = new Vector3(v.x, v.y, v.z);
/*  54 */       r.apply(vec);
/*  55 */       vec = vec.add(offset);
/*  56 */       p.add(new Vector3f((float)vec.x, (float)vec.y, (float)vec.z));
/*     */     } 
/*  58 */     this.positions = p;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPosition(float x, float y, float z) {
/*  63 */     if (this.positions == null)
/*  64 */       this.positions = new ArrayList(); 
/*  65 */     this.positions.add(new Vector3f(x, y, z));
/*     */   }
/*     */   
/*     */   public void addNormal(float x, float y, float z) {
/*  69 */     if (this.normals == null)
/*  70 */       this.normals = new ArrayList(); 
/*  71 */     this.normals.add(new Vector3f(x, y, z));
/*     */   }
/*     */   
/*     */   public void addTexCoords(float x, float y) {
/*  75 */     if (this.texCoords == null)
/*  76 */       this.texCoords = new ArrayList(); 
/*  77 */     this.texCoords.add(new Vector2f(x, y));
/*     */   }
/*     */ 
/*     */   
/*  81 */   public void addPart(MeshPart part) { this.parts.add(part); }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public void addPart(MeshPart part, int ti) { this.parts.add(new MeshPart(part, ti)); }
/*     */ 
/*     */   
/*     */   private int getColorValue(Vector3f color) {
/*  89 */     int r = (int)color.x;
/*  90 */     int g = (int)color.y;
/*  91 */     int b = (int)color.z;
/*  92 */     return 0xFF000000 | r << 16 | g << 8 | b;
/*     */   }
/*     */   
/*     */   public List<BakedQuad> bakeModel(ModelManager manager) {
/*  96 */     List<BakedQuad> bakeList = new ArrayList<BakedQuad>();
/*     */     
/*  98 */     for (int j = 0; j < this.parts.size(); j++) {
/*     */       
/* 100 */       MeshPart part = (MeshPart)this.parts.get(j);
/*     */       
/* 102 */       TextureAtlasSprite sprite = null;
/* 103 */       int color = -1;
/*     */       
/* 105 */       if (part.material != null) {
/* 106 */         if (part.material.DiffuseTextureMap != null) {
/* 107 */           sprite = manager.func_174952_b().func_110572_b(part.material.DiffuseTextureMap);
/* 108 */         } else if (part.material.AmbientTextureMap != null) {
/* 109 */           sprite = manager.func_174952_b().func_110572_b(part.material.AmbientTextureMap);
/*     */         } 
/* 111 */         if (part.material.DiffuseColor != null) {
/* 112 */           color = getColorValue(part.material.DiffuseColor);
/*     */         }
/*     */       } 
/*     */       
/* 116 */       for (int i = 0; i < part.indices.size(); i += 4) {
/* 117 */         BakedQuad quad = bakeQuad(part, i, sprite, color);
/* 118 */         bakeList.add(quad);
/*     */       } 
/*     */     } 
/* 121 */     return bakeList;
/*     */   }
/*     */   
/*     */   public List<BakedQuad> bakeModel(TextureAtlasSprite sprite) {
/* 125 */     List<BakedQuad> bakeList = new ArrayList<BakedQuad>();
/*     */     
/* 127 */     for (int j = 0; j < this.parts.size(); j++) {
/*     */       
/* 129 */       MeshPart part = (MeshPart)this.parts.get(j);
/*     */ 
/*     */       
/* 132 */       int color = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       for (int i = 0; i < part.indices.size(); i += 4) {
/* 146 */         BakedQuad quad = bakeQuad(part, i, sprite, color);
/* 147 */         bakeList.add(quad);
/*     */       } 
/*     */     } 
/* 150 */     return bakeList;
/*     */   }
/*     */   
/*     */   private BakedQuad bakeQuad(MeshPart part, int startIndex, TextureAtlasSprite sprite, int color) {
/* 154 */     int[] faceData = new int[28];
/* 155 */     for (int i = 0; i < 4; i++) {
/*     */       
/* 157 */       Vector3f position = new Vector3f(0.0F, 0.0F, 0.0F);
/* 158 */       Vector2f texCoord = new Vector2f(0.0F, 0.0F);
/* 159 */       int p = 0;
/* 160 */       int[] indices = (int[])part.indices.get(startIndex + i);
/*     */       
/* 162 */       if (this.positions != null) {
/* 163 */         position = (Vector3f)this.positions.get(indices[p++]);
/*     */       }
/* 165 */       if (this.normals != null) {
/* 166 */         p++;
/*     */       }
/* 168 */       if (this.texCoords != null) {
/* 169 */         texCoord = (Vector2f)this.texCoords.get(indices[p++]);
/*     */       }
/* 171 */       storeVertexData(faceData, i, position, texCoord, sprite, color);
/*     */     } 
/* 173 */     return new BakedQuad(faceData, part.name.contains("focus") ? 1 : part.tintIndex, 
/* 174 */         FaceBakery.func_178410_a(faceData), sprite, false, DefaultVertexFormats.field_176600_a);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void storeVertexData(int[] faceData, int storeIndex, Vector3f position, Vector2f faceUV, TextureAtlasSprite sprite, int shadeColor) {
/* 179 */     int l = storeIndex * 7;
/* 180 */     faceData[l + 0] = Float.floatToRawIntBits(position.x);
/* 181 */     faceData[l + 1] = Float.floatToRawIntBits(position.y);
/* 182 */     faceData[l + 2] = Float.floatToRawIntBits(position.z);
/* 183 */     faceData[l + 3] = shadeColor;
/* 184 */     if (sprite != null) {
/* 185 */       faceData[l + 4] = Float.floatToRawIntBits(sprite.func_94214_a((faceUV.x * 16.0F)));
/* 186 */       faceData[l + 5] = Float.floatToRawIntBits(sprite.func_94207_b((faceUV.y * 16.0F)));
/*     */     } else {
/* 188 */       faceData[l + 4] = Float.floatToRawIntBits(faceUV.x);
/* 189 */       faceData[l + 5] = Float.floatToRawIntBits(faceUV.y);
/*     */     } 
/* 191 */     faceData[l + 6] = 0;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\obj\MeshModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */