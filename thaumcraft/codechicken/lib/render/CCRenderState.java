/*     */ package thaumcraft.codechicken.lib.render;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import thaumcraft.codechicken.lib.colour.ColourRGBA;
/*     */ import thaumcraft.codechicken.lib.lighting.LC;
/*     */ import thaumcraft.codechicken.lib.lighting.LightMatrix;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ import thaumcraft.codechicken.lib.vec.Rotation;
/*     */ import thaumcraft.codechicken.lib.vec.Transformation;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CCRenderState
/*     */ {
/*     */   private static int nextOperationIndex;
/*     */   
/*  28 */   public static int registerOperation() { return nextOperationIndex++; }
/*     */ 
/*     */ 
/*     */   
/*  32 */   public static int operationCount() { return nextOperationIndex; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   private static ArrayList<VertexAttribute<?>> vertexAttributes = new ArrayList();
/*     */   private static int registerVertexAttribute(VertexAttribute<?> attr) {
/*  60 */     vertexAttributes.add(attr);
/*  61 */     return vertexAttributes.size() - 1;
/*     */   }
/*     */ 
/*     */   
/*  65 */   public static VertexAttribute<?> getAttribute(int index) { return (VertexAttribute)vertexAttributes.get(index); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class VertexAttribute<T>
/*     */     extends Object
/*     */     implements IVertexOperation
/*     */   {
/*  75 */     public final int attributeIndex = CCRenderState.registerVertexAttribute(this);
/*  76 */     private final int operationIndex = CCRenderState.registerOperation();
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean active = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public abstract T newArray(int param1Int);
/*     */ 
/*     */ 
/*     */     
/*  89 */     public int operationID() { return this.operationIndex; }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void arrayCopy(Object src, int srcPos, Object dst, int destPos, int length) {
/*  94 */     System.arraycopy(src, srcPos, dst, destPos, length);
/*  95 */     if (dst instanceof Copyable[]) {
/*  96 */       Object[] oa = (Object[])dst;
/*  97 */       Copyable[] c = (Copyable[])dst;
/*  98 */       for (int i = destPos; i < destPos + length; i++) {
/*  99 */         if (c[i] != null)
/* 100 */           oa[i] = c[i].copy(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public static <T> T copyOf(VertexAttribute<T> attr, T src, int length) {
/* 105 */     T dst = (T)attr.newArray(length);
/* 106 */     arrayCopy(src, 0, dst, 0, (Object[])src.length);
/* 107 */     return dst;
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
/* 133 */   public static VertexAttribute<Vector3[]> normalAttrib = new VertexAttribute<Vector3[]>()
/*     */     {
/*     */       private Vector3[] normalRef;
/*     */ 
/*     */       
/* 138 */       public Vector3[] newArray(int length) { return new Vector3[length]; }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean load() {
/* 143 */         this.normalRef = (Vector3[])CCRenderState.model.getAttributes(this);
/* 144 */         if (CCRenderState.model.hasAttribute(this)) {
/* 145 */           return (this.normalRef != null);
/*     */         }
/* 147 */         if (CCRenderState.model.hasAttribute(CCRenderState.sideAttrib)) {
/* 148 */           CCRenderState.pipeline.addDependency(CCRenderState.sideAttrib);
/* 149 */           return true;
/*     */         } 
/* 151 */         throw new IllegalStateException("Normals requested but neither normal or side attrutes are provided by the model");
/*     */       }
/*     */ 
/*     */       
/*     */       public void operate() {
/* 156 */         if (this.normalRef != null) {
/* 157 */           CCRenderState.setNormal(this.normalRef[CCRenderState.vertexIndex]);
/*     */         } else {
/* 159 */           CCRenderState.setNormal(Rotation.axes[CCRenderState.side]);
/*     */         } 
/*     */       }
/* 162 */     }; public static VertexAttribute<int[]> colourAttrib = new VertexAttribute<int[]>()
/*     */     {
/*     */       private int[] colourRef;
/*     */ 
/*     */       
/* 167 */       public int[] newArray(int length) { return new int[length]; }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean load() {
/* 172 */         this.colourRef = (int[])CCRenderState.model.getAttributes(this);
/* 173 */         return (this.colourRef != null || !CCRenderState.model.hasAttribute(this));
/*     */       }
/*     */ 
/*     */       
/*     */       public void operate() {
/* 178 */         if (this.colourRef != null) {
/* 179 */           CCRenderState.setColour(ColourRGBA.multiply(CCRenderState.baseColour, this.colourRef[CCRenderState.vertexIndex]));
/*     */         } else {
/* 181 */           CCRenderState.setColour(CCRenderState.baseColour);
/*     */         } 
/*     */       }
/* 184 */     }; public static VertexAttribute<int[]> lightingAttrib = new VertexAttribute<int[]>()
/*     */     {
/*     */       private int[] colourRef;
/*     */ 
/*     */       
/* 189 */       public int[] newArray(int length) { return new int[length]; }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean load() {
/* 194 */         if (!CCRenderState.computeLighting || !CCRenderState.useColour || !CCRenderState.model.hasAttribute(this)) {
/* 195 */           return false;
/*     */         }
/* 197 */         this.colourRef = (int[])CCRenderState.model.getAttributes(this);
/* 198 */         if (this.colourRef != null) {
/* 199 */           CCRenderState.pipeline.addDependency(CCRenderState.colourAttrib);
/* 200 */           return true;
/*     */         } 
/* 202 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 207 */       public void operate() { CCRenderState.setColour(ColourRGBA.multiply(CCRenderState.colour, this.colourRef[CCRenderState.vertexIndex])); }
/*     */     };
/*     */   
/* 210 */   public static VertexAttribute<int[]> sideAttrib = new VertexAttribute<int[]>()
/*     */     {
/*     */       private int[] sideRef;
/*     */ 
/*     */       
/* 215 */       public int[] newArray(int length) { return new int[length]; }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean load() {
/* 220 */         this.sideRef = (int[])CCRenderState.model.getAttributes(this);
/* 221 */         if (CCRenderState.model.hasAttribute(this)) {
/* 222 */           return (this.sideRef != null);
/*     */         }
/* 224 */         CCRenderState.pipeline.addDependency(CCRenderState.normalAttrib);
/* 225 */         return true;
/*     */       }
/*     */ 
/*     */       
/*     */       public void operate() {
/* 230 */         if (this.sideRef != null) {
/* 231 */           CCRenderState.side = this.sideRef[CCRenderState.vertexIndex];
/*     */         } else {
/* 233 */           CCRenderState.side = CCModel.findSide(CCRenderState.normal);
/*     */         } 
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 239 */   public static VertexAttribute<LC[]> lightCoordAttrib = new VertexAttribute<LC[]>() {
/*     */       private LC[] lcRef;
/* 241 */       private Vector3 vec = new Vector3();
/* 242 */       private Vector3 pos = new Vector3();
/*     */ 
/*     */ 
/*     */       
/* 246 */       public LC[] newArray(int length) { return new LC[length]; }
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean load() {
/* 251 */         this.lcRef = (LC[])CCRenderState.model.getAttributes(this);
/* 252 */         if (CCRenderState.model.hasAttribute(this)) {
/* 253 */           return (this.lcRef != null);
/*     */         }
/* 255 */         this.pos.set(CCRenderState.lightMatrix.pos.x, CCRenderState.lightMatrix.pos.y, CCRenderState.lightMatrix.pos.z);
/* 256 */         CCRenderState.pipeline.addDependency(CCRenderState.sideAttrib);
/* 257 */         CCRenderState.pipeline.addRequirement(Transformation.operationIndex);
/* 258 */         return true;
/*     */       }
/*     */ 
/*     */       
/*     */       public void operate() {
/* 263 */         if (this.lcRef != null) {
/* 264 */           CCRenderState.lc.set(this.lcRef[CCRenderState.vertexIndex]);
/*     */         } else {
/* 266 */           CCRenderState.lc.compute(this.vec.set(CCRenderState.vert.vec).sub(this.pos), CCRenderState.side);
/*     */         } 
/*     */       }
/*     */     };
/*     */   
/*     */   public static IVertexSource model;
/*     */   public static int firstVertexIndex;
/*     */   public static int lastVertexIndex;
/*     */   public static int vertexIndex;
/* 275 */   public static CCRenderPipeline pipeline = new CCRenderPipeline();
/*     */   
/*     */   public static int baseColour;
/*     */   
/*     */   public static int alphaOverride;
/*     */   public static boolean useNormals;
/*     */   public static boolean computeLighting;
/*     */   public static boolean useColour;
/* 283 */   public static LightMatrix lightMatrix = new LightMatrix();
/*     */ 
/*     */   
/* 286 */   public static Vertex5 vert = new Vertex5();
/*     */   public static boolean hasNormal;
/* 288 */   public static Vector3 normal = new Vector3();
/*     */   
/*     */   public static boolean hasColour;
/*     */   
/*     */   public static int colour;
/*     */   public static boolean hasBrightness;
/*     */   public static int brightness;
/*     */   public static int side;
/* 296 */   public static LC lc = new LC();
/*     */   
/*     */   public static void reset() {
/* 299 */     model = null;
/* 300 */     pipeline.reset();
/* 301 */     useNormals = hasNormal = hasBrightness = hasColour = false;
/* 302 */     useColour = computeLighting = true;
/* 303 */     baseColour = alphaOverride = -1;
/*     */   }
/*     */ 
/*     */   
/* 307 */   public static void setPipeline(IVertexOperation... ops) { pipeline.setPipeline(ops); }
/*     */ 
/*     */   
/*     */   public static void setPipeline(IVertexSource model, int start, int end, IVertexOperation... ops) {
/* 311 */     pipeline.reset();
/* 312 */     setModel(model, start, end);
/* 313 */     pipeline.setPipeline(ops);
/*     */   }
/*     */   
/*     */   public static void bindModel(IVertexSource model) {
/* 317 */     if (CCRenderState.model != model) {
/* 318 */       CCRenderState.model = model;
/* 319 */       pipeline.rebuild();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 324 */   public static void setModel(IVertexSource source) { setModel(source, 0, source.getVertices().length); }
/*     */ 
/*     */   
/*     */   public static void setModel(IVertexSource source, int start, int end) {
/* 328 */     bindModel(source);
/* 329 */     setVertexRange(start, end);
/*     */   }
/*     */   
/*     */   public static void setVertexRange(int start, int end) {
/* 333 */     firstVertexIndex = start;
/* 334 */     lastVertexIndex = end;
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
/*     */   public static void setNormal(double x, double y, double z) {
/* 368 */     hasNormal = true;
/* 369 */     normal.set(x, y, z);
/*     */   }
/*     */   
/*     */   public static void setNormal(Vector3 n) {
/* 373 */     hasNormal = true;
/* 374 */     normal.set(n);
/*     */   }
/*     */   
/*     */   public static void setColour(int c) {
/* 378 */     hasColour = true;
/* 379 */     colour = c;
/*     */   }
/*     */   
/*     */   public static void setBrightness(int b) {
/* 383 */     hasBrightness = true;
/* 384 */     brightness = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 392 */   public static void pullLightmap() { setBrightness((int)OpenGlHelper.lastBrightnessY << 16 | (int)OpenGlHelper.lastBrightnessX); }
/*     */ 
/*     */ 
/*     */   
/* 396 */   public static void pushLightmap() { OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (brightness & 0xFFFF), (brightness >>> 16)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setDynamic() {
/* 403 */     useNormals = true;
/* 404 */     computeLighting = false;
/*     */   }
/*     */ 
/*     */   
/* 408 */   public static void changeTexture(String texture) { changeTexture(new ResourceLocation(texture)); }
/*     */ 
/*     */ 
/*     */   
/* 412 */   public static void changeTexture(ResourceLocation texture) { (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 430 */   public static void draw() { Tessellator.func_178181_a().func_78381_a(); }
/*     */   
/*     */   public static interface IVertexSource {
/*     */     Vertex5[] getVertices();
/*     */     
/*     */     <T> T getAttributes(CCRenderState.VertexAttribute<T> param1VertexAttribute);
/*     */     
/*     */     boolean hasAttribute(CCRenderState.VertexAttribute<?> param1VertexAttribute);
/*     */     
/*     */     void prepareVertex();
/*     */   }
/*     */   
/*     */   public static interface IVertexOperation {
/*     */     boolean load();
/*     */     
/*     */     void operate();
/*     */     
/*     */     int operationID();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\render\CCRenderState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */