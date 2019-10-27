/*      */ package thaumcraft.codechicken.lib.render;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.PrintWriter;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import thaumcraft.codechicken.lib.lighting.LC;
/*      */ import thaumcraft.codechicken.lib.lighting.LightModel;
/*      */ import thaumcraft.codechicken.lib.render.uv.UV;
/*      */ import thaumcraft.codechicken.lib.render.uv.UVTransformation;
/*      */ import thaumcraft.codechicken.lib.util.Copyable;
/*      */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*      */ import thaumcraft.codechicken.lib.vec.RedundantTransformation;
/*      */ import thaumcraft.codechicken.lib.vec.Rotation;
/*      */ import thaumcraft.codechicken.lib.vec.Transformation;
/*      */ import thaumcraft.codechicken.lib.vec.TransformationList;
/*      */ import thaumcraft.codechicken.lib.vec.Vector3;
/*      */ 
/*      */ public class CCModel extends Object implements CCRenderState.IVertexSource, Copyable<CCModel> {
/*      */   public final int vertexMode;
/*      */   public final int vp;
/*      */   public Vertex5[] verts;
/*      */   
/*      */   private static class PositionNormalEntry {
/*      */     public PositionNormalEntry(Vector3 position) {
/*   38 */       this.normals = new LinkedList();
/*      */ 
/*      */ 
/*      */       
/*   42 */       this.pos = position;
/*      */     }
/*      */     public Vector3 pos;
/*      */     public LinkedList<Vector3> normals;
/*      */     
/*   47 */     public boolean positionEqual(Vector3 v) { return (this.pos.x == v.x && this.pos.y == v.y && this.pos.z == v.z); }
/*      */ 
/*      */ 
/*      */     
/*      */     public PositionNormalEntry addNormal(Vector3 normal) {
/*   52 */       this.normals.add(normal);
/*   53 */       return this;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   60 */   public ArrayList<Object> attributes = new ArrayList();
/*      */ 
/*      */   
/*      */   protected CCModel(int vertexMode) {
/*   64 */     if (vertexMode != 7 && vertexMode != 4) {
/*   65 */       throw new IllegalArgumentException("Models must be GL_QUADS or GL_TRIANGLES");
/*      */     }
/*   67 */     this.vertexMode = vertexMode;
/*   68 */     this.vp = (vertexMode == 7) ? 4 : 3;
/*      */   }
/*      */ 
/*      */   
/*   72 */   public Vector3[] normals() { return (Vector3[])getAttributes(CCRenderState.normalAttrib); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   77 */   public Vertex5[] getVertices() { return this.verts; }
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T getAttributes(CCRenderState.VertexAttribute<T> attr) {
/*   82 */     if (attr.attributeIndex < this.attributes.size()) {
/*   83 */       return (T)this.attributes.get(attr.attributeIndex);
/*      */     }
/*   85 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*   90 */   public boolean hasAttribute(CCRenderState.VertexAttribute<?> attrib) { return (attrib.attributeIndex < this.attributes.size() && this.attributes.get(attrib.attributeIndex) != null); }
/*      */ 
/*      */ 
/*      */   
/*      */   public void prepareVertex() {}
/*      */ 
/*      */   
/*      */   public <T> T getOrAllocate(CCRenderState.VertexAttribute<T> attrib) {
/*   98 */     T array = (T)getAttributes(attrib);
/*   99 */     if (array == null) {
/*  100 */       while (this.attributes.size() <= attrib.attributeIndex)
/*  101 */         this.attributes.add(null); 
/*  102 */       this.attributes.set(attrib.attributeIndex, array = (T)attrib.newArray(this.verts.length));
/*      */     } 
/*  104 */     return array;
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
/*      */   public CCModel generateBox(int i, double x1, double y1, double z1, double w, double h, double d, double tx, double ty, double tw, double th, double f) {
/*  126 */     double x2 = x1 + w;
/*  127 */     double y2 = y1 + h;
/*  128 */     double z2 = z1 + d;
/*  129 */     x1 /= f; x2 /= f; y1 /= f; y2 /= f; z1 /= f; z2 /= f;
/*      */ 
/*      */     
/*  132 */     double u1 = (tx + d + w) / tw, v1 = (ty + d) / th;
/*  133 */     double u2 = (tx + d * 2.0D + w) / tw, v2 = ty / th;
/*  134 */     this.verts[i++] = new Vertex5(x1, y1, z2, u1, v2);
/*  135 */     this.verts[i++] = new Vertex5(x1, y1, z1, u1, v1);
/*  136 */     this.verts[i++] = new Vertex5(x2, y1, z1, u2, v1);
/*  137 */     this.verts[i++] = new Vertex5(x2, y1, z2, u2, v2);
/*      */ 
/*      */     
/*  140 */     u1 = (tx + d) / tw; v1 = (ty + d) / th;
/*  141 */     u2 = (tx + d + w) / tw; v2 = ty / th;
/*  142 */     this.verts[i++] = new Vertex5(x2, y2, z2, u2, v2);
/*  143 */     this.verts[i++] = new Vertex5(x2, y2, z1, u2, v1);
/*  144 */     this.verts[i++] = new Vertex5(x1, y2, z1, u1, v1);
/*  145 */     this.verts[i++] = new Vertex5(x1, y2, z2, u1, v2);
/*      */ 
/*      */     
/*  148 */     u1 = (tx + d + w) / tw; v1 = (ty + d) / th;
/*  149 */     u2 = (tx + d) / tw; v2 = (ty + d + h) / th;
/*  150 */     this.verts[i++] = new Vertex5(x1, y2, z1, u2, v1);
/*  151 */     this.verts[i++] = new Vertex5(x2, y2, z1, u1, v1);
/*  152 */     this.verts[i++] = new Vertex5(x2, y1, z1, u1, v2);
/*  153 */     this.verts[i++] = new Vertex5(x1, y1, z1, u2, v2);
/*      */ 
/*      */     
/*  156 */     u1 = (tx + d * 2.0D + w * 2.0D) / tw; v1 = (ty + d) / th;
/*  157 */     u2 = (tx + d * 2.0D + w) / tw; v2 = (ty + d + h) / th;
/*  158 */     this.verts[i++] = new Vertex5(x1, y2, z2, u1, v1);
/*  159 */     this.verts[i++] = new Vertex5(x1, y1, z2, u1, v2);
/*  160 */     this.verts[i++] = new Vertex5(x2, y1, z2, u2, v2);
/*  161 */     this.verts[i++] = new Vertex5(x2, y2, z2, u2, v1);
/*      */ 
/*      */     
/*  164 */     u1 = (tx + d) / tw; v1 = (ty + d) / th;
/*  165 */     u2 = tx / tw; v2 = (ty + d + h) / th;
/*  166 */     this.verts[i++] = new Vertex5(x1, y2, z2, u2, v1);
/*  167 */     this.verts[i++] = new Vertex5(x1, y2, z1, u1, v1);
/*  168 */     this.verts[i++] = new Vertex5(x1, y1, z1, u1, v2);
/*  169 */     this.verts[i++] = new Vertex5(x1, y1, z2, u2, v2);
/*      */ 
/*      */     
/*  172 */     u1 = (tx + d * 2.0D + w) / tw; v1 = (ty + d) / th;
/*  173 */     u2 = (tx + d + w) / tw; v2 = (ty + d + h) / th;
/*  174 */     this.verts[i++] = new Vertex5(x2, y1, z2, u1, v2);
/*  175 */     this.verts[i++] = new Vertex5(x2, y1, z1, u2, v2);
/*  176 */     this.verts[i++] = new Vertex5(x2, y2, z1, u2, v1);
/*  177 */     this.verts[i++] = new Vertex5(x2, y2, z2, u1, v1);
/*      */     
/*  179 */     return this;
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
/*  190 */   public CCModel generateBlock(int i, Cuboid6 bounds) { return generateBlock(i, bounds, 0); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  195 */   public CCModel generateBlock(int i, Cuboid6 bounds, int mask) { return generateBlock(i, bounds.min.x, bounds.min.y, bounds.min.z, bounds.max.x, bounds.max.y, bounds.max.z, mask); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  200 */   public CCModel generateBlock(int i, double x1, double y1, double z1, double x2, double y2, double z2) { return generateBlock(i, x1, y1, z1, x2, y2, z2, 0); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CCModel generateBlock(int i, double x1, double y1, double z1, double x2, double y2, double z2, int mask) {
/*  219 */     if ((mask & true) == 0) {
/*  220 */       double u1 = x1, v1 = z1;
/*  221 */       double u2 = x2, v2 = z2;
/*  222 */       this.verts[i++] = new Vertex5(x1, y1, z2, u1, v2);
/*  223 */       this.verts[i++] = new Vertex5(x1, y1, z1, u1, v1);
/*  224 */       this.verts[i++] = new Vertex5(x2, y1, z1, u2, v1);
/*  225 */       this.verts[i++] = new Vertex5(x2, y1, z2, u2, v2);
/*      */     } 
/*      */     
/*  228 */     if ((mask & 0x2) == 0) {
/*  229 */       double u1 = x1 + 2.0D, v1 = z1;
/*  230 */       double u2 = x2 + 2.0D, v2 = z2;
/*  231 */       this.verts[i++] = new Vertex5(x2, y2, z2, u2, v2);
/*  232 */       this.verts[i++] = new Vertex5(x2, y2, z1, u2, v1);
/*  233 */       this.verts[i++] = new Vertex5(x1, y2, z1, u1, v1);
/*  234 */       this.verts[i++] = new Vertex5(x1, y2, z2, u1, v2);
/*      */     } 
/*      */     
/*  237 */     if ((mask & 0x4) == 0) {
/*  238 */       double u1 = 1.0D - x1 + 4.0D, v1 = 1.0D - y2;
/*  239 */       double u2 = 1.0D - x2 + 4.0D, v2 = 1.0D - y1;
/*  240 */       this.verts[i++] = new Vertex5(x1, y1, z1, u1, v2);
/*  241 */       this.verts[i++] = new Vertex5(x1, y2, z1, u1, v1);
/*  242 */       this.verts[i++] = new Vertex5(x2, y2, z1, u2, v1);
/*  243 */       this.verts[i++] = new Vertex5(x2, y1, z1, u2, v2);
/*      */     } 
/*      */     
/*  246 */     if ((mask & 0x8) == 0) {
/*  247 */       double u1 = x1 + 6.0D, v1 = 1.0D - y2;
/*  248 */       double u2 = x2 + 6.0D, v2 = 1.0D - y1;
/*  249 */       this.verts[i++] = new Vertex5(x2, y1, z2, u2, v2);
/*  250 */       this.verts[i++] = new Vertex5(x2, y2, z2, u2, v1);
/*  251 */       this.verts[i++] = new Vertex5(x1, y2, z2, u1, v1);
/*  252 */       this.verts[i++] = new Vertex5(x1, y1, z2, u1, v2);
/*      */     } 
/*      */     
/*  255 */     if ((mask & 0x10) == 0) {
/*  256 */       double u1 = z1 + 8.0D, v1 = 1.0D - y2;
/*  257 */       double u2 = z2 + 8.0D, v2 = 1.0D - y1;
/*  258 */       this.verts[i++] = new Vertex5(x1, y1, z2, u2, v2);
/*  259 */       this.verts[i++] = new Vertex5(x1, y2, z2, u2, v1);
/*  260 */       this.verts[i++] = new Vertex5(x1, y2, z1, u1, v1);
/*  261 */       this.verts[i++] = new Vertex5(x1, y1, z1, u1, v2);
/*      */     } 
/*      */     
/*  264 */     if ((mask & 0x20) == 0) {
/*  265 */       double u1 = 1.0D - z1 + 10.0D, v1 = 1.0D - y2;
/*  266 */       double u2 = 1.0D - z2 + 10.0D, v2 = 1.0D - y1;
/*  267 */       this.verts[i++] = new Vertex5(x2, y1, z1, u1, v2);
/*  268 */       this.verts[i++] = new Vertex5(x2, y2, z1, u1, v1);
/*  269 */       this.verts[i++] = new Vertex5(x2, y2, z2, u2, v1);
/*  270 */       this.verts[i++] = new Vertex5(x2, y1, z2, u2, v2);
/*      */     } 
/*      */     
/*  273 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  278 */   public CCModel computeNormals() { return computeNormals(0, this.verts.length); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CCModel computeNormals(int start, int length) {
/*  290 */     if (length % this.vp != 0 || start % this.vp != 0) {
/*  291 */       throw new IllegalArgumentException("Cannot generate normals across polygons");
/*      */     }
/*  293 */     Vector3[] normals = (Vector3[])getOrAllocate(CCRenderState.normalAttrib); int k;
/*  294 */     for (k = 0; k < length; k += this.vp) {
/*      */       
/*  296 */       int i = k + start;
/*  297 */       Vector3 diff1 = (this.verts[i + 1]).vec.copy().subtract((this.verts[i]).vec);
/*  298 */       Vector3 diff2 = (this.verts[i + this.vp - 1]).vec.copy().subtract((this.verts[i]).vec);
/*  299 */       normals[i] = diff1.crossProduct(diff2).normalize();
/*  300 */       for (int d = 1; d < this.vp; d++) {
/*  301 */         normals[i + d] = normals[i].copy();
/*      */       }
/*      */     } 
/*  304 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CCModel computeLighting(LightModel light) {
/*  314 */     Vector3[] normals = normals();
/*  315 */     int[] colours = (int[])getAttributes(CCRenderState.colourAttrib);
/*  316 */     if (colours == null) {
/*  317 */       setColour(-1);
/*  318 */       colours = (int[])getAttributes(CCRenderState.colourAttrib);
/*      */     } 
/*  320 */     for (int k = 0; k < this.verts.length; k++)
/*  321 */       colours[k] = light.apply(colours[k], normals[k]); 
/*  322 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public CCModel setColour(int c) {
/*  327 */     int[] colours = (int[])getOrAllocate(CCRenderState.colourAttrib);
/*  328 */     Arrays.fill(colours, c);
/*  329 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CCModel computeLightCoords() {
/*  337 */     LC[] lcs = (LC[])getOrAllocate(CCRenderState.lightCoordAttrib);
/*  338 */     Vector3[] normals = normals();
/*  339 */     for (int i = 0; i < this.verts.length; i++)
/*  340 */       lcs[i] = (new LC()).compute((this.verts[i]).vec, normals[i]); 
/*  341 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CCModel smoothNormals() {
/*  350 */     ArrayList<PositionNormalEntry> map = new ArrayList<PositionNormalEntry>();
/*  351 */     Vector3[] normals = normals();
/*  352 */     for (int k = 0; k < this.verts.length; k++) {
/*      */       
/*  354 */       Vector3 vec = (this.verts[k]).vec;
/*  355 */       Iterator iterator = map.iterator(); while (true) { if (iterator.hasNext()) { PositionNormalEntry e = (PositionNormalEntry)iterator.next();
/*  356 */           if (e.positionEqual(vec)) {
/*      */             
/*  358 */             e.addNormal(normals[k]); break;
/*      */           } 
/*      */           continue; }
/*      */         
/*  362 */         map.add((new PositionNormalEntry(vec)).addNormal(normals[k])); break; }
/*      */     
/*      */     } 
/*  365 */     for (PositionNormalEntry e : map) {
/*      */       
/*  367 */       if (e.normals.size() <= 1) {
/*      */         continue;
/*      */       }
/*  370 */       Vector3 new_n = new Vector3();
/*  371 */       for (Vector3 n : e.normals) {
/*  372 */         new_n.add(n);
/*      */       }
/*  374 */       new_n.normalize();
/*  375 */       for (Vector3 n : e.normals) {
/*  376 */         n.set(new_n);
/*      */       }
/*      */     } 
/*  379 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public CCModel apply(Transformation t) {
/*  384 */     for (int k = 0; k < this.verts.length; k++) {
/*  385 */       this.verts[k].apply(t);
/*      */     }
/*  387 */     Vector3[] normals = normals();
/*  388 */     if (normals != null)
/*  389 */       for (int k = 0; k < normals.length; k++) {
/*  390 */         t.applyN(normals[k]);
/*      */       } 
/*  392 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public CCModel apply(UVTransformation uvt) {
/*  397 */     for (int k = 0; k < this.verts.length; k++) {
/*  398 */       this.verts[k].apply(uvt);
/*      */     }
/*  400 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public CCModel expand(int extraVerts) {
/*  405 */     int newLen = this.verts.length + extraVerts;
/*  406 */     this.verts = (Vertex5[])Arrays.copyOf(this.verts, newLen);
/*  407 */     for (int i = 0; i < this.attributes.size(); i++) {
/*  408 */       if (this.attributes.get(i) != null)
/*  409 */         this.attributes.set(i, CCRenderState.copyOf(CCRenderState.getAttribute(i), this.attributes.get(i), newLen)); 
/*      */     } 
/*  411 */     return this;
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
/*  453 */   public static CCModel quadModel(int numVerts) { return newModel(7, numVerts); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  458 */   public static CCModel triModel(int numVerts) { return newModel(4, numVerts); }
/*      */ 
/*      */ 
/*      */   
/*      */   public static CCModel newModel(int vertexMode, int numVerts) {
/*  463 */     CCModel model = newModel(vertexMode);
/*  464 */     model.verts = new Vertex5[numVerts];
/*  465 */     return model;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  470 */   public static CCModel newModel(int vertexMode) { return new CCModel(vertexMode); }
/*      */ 
/*      */ 
/*      */   
/*      */   public static double[] parseDoubles(String s, String token) {
/*  475 */     String[] as = s.split(token);
/*  476 */     double[] values = new double[as.length];
/*  477 */     for (int i = 0; i < as.length; i++)
/*  478 */       values[i] = Double.parseDouble(as[i]); 
/*  479 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  484 */   public static void illegalAssert(boolean b, String err) { if (!b) throw new IllegalArgumentException(err);
/*      */      }
/*      */ 
/*      */   
/*      */   public static void assertMatch(Matcher m, String s) {
/*  489 */     m.reset(s);
/*  490 */     illegalAssert(m.matches(), "Malformed line: " + s);
/*      */   }
/*      */   
/*  493 */   private static final Pattern vertPattern = Pattern.compile("v(?: ([\\d\\.+-]+))+");
/*  494 */   private static final Pattern uvwPattern = Pattern.compile("vt(?: ([\\d\\.+-]+))+");
/*  495 */   private static final Pattern normalPattern = Pattern.compile("vn(?: ([\\d\\.+-]+))+");
/*  496 */   private static final Pattern polyPattern = Pattern.compile("f(?: ((?:\\d*)(?:/\\d*)?(?:/\\d*)?))+");
/*  497 */   public static final Matcher vertMatcher = vertPattern.matcher("");
/*  498 */   public static final Matcher uvwMatcher = uvwPattern.matcher("");
/*  499 */   public static final Matcher normalMatcher = normalPattern.matcher("");
/*  500 */   public static final Matcher polyMatcher = polyPattern.matcher("");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<String, CCModel> parseObjModels(InputStream input, int vertexMode, Transformation coordSystem) throws IOException {
/*      */     RedundantTransformation redundantTransformation;
/*  512 */     if (coordSystem == null)
/*  513 */       redundantTransformation = new RedundantTransformation(); 
/*  514 */     int vp = (vertexMode == 7) ? 4 : 3;
/*      */     
/*  516 */     HashMap<String, CCModel> modelMap = new HashMap<String, CCModel>();
/*  517 */     ArrayList<Vector3> verts = new ArrayList<Vector3>();
/*  518 */     ArrayList<Vector3> uvs = new ArrayList<Vector3>();
/*  519 */     ArrayList<Vector3> normals = new ArrayList<Vector3>();
/*  520 */     ArrayList<int[]> polys = new ArrayList<int[]>();
/*  521 */     String modelName = "unnamed";
/*      */     
/*  523 */     BufferedReader reader = new BufferedReader(new InputStreamReader(input));
/*      */     
/*      */     String line;
/*  526 */     while ((line = reader.readLine()) != null) {
/*      */       
/*  528 */       line = line.replaceAll("\\s+", " ").trim();
/*  529 */       if (line.startsWith("#") || line.length() == 0) {
/*      */         continue;
/*      */       }
/*  532 */       if (line.startsWith("v ")) {
/*      */         
/*  534 */         assertMatch(vertMatcher, line);
/*  535 */         double[] values = parseDoubles(line.substring(2), " ");
/*  536 */         illegalAssert((values.length >= 3), "Vertices must have x, y and z components");
/*  537 */         Vector3 vert = new Vector3(values[0], values[1], values[2]);
/*  538 */         redundantTransformation.apply(vert);
/*  539 */         verts.add(vert);
/*      */         continue;
/*      */       } 
/*  542 */       if (line.startsWith("vt ")) {
/*      */         
/*  544 */         assertMatch(uvwMatcher, line);
/*  545 */         double[] values = parseDoubles(line.substring(3), " ");
/*  546 */         illegalAssert((values.length >= 2), "Tex Coords must have u, and v components");
/*  547 */         uvs.add(new Vector3(values[0], 1.0D - values[1], 0.0D));
/*      */         continue;
/*      */       } 
/*  550 */       if (line.startsWith("vn ")) {
/*      */         
/*  552 */         assertMatch(normalMatcher, line);
/*  553 */         double[] values = parseDoubles(line.substring(3), " ");
/*  554 */         illegalAssert((values.length >= 3), "Normals must have x, y and z components");
/*  555 */         Vector3 norm = (new Vector3(values[0], values[1], values[2])).normalize();
/*  556 */         redundantTransformation.applyN(norm);
/*  557 */         normals.add(norm);
/*      */         continue;
/*      */       } 
/*  560 */       if (line.startsWith("f ")) {
/*      */         
/*  562 */         assertMatch(polyMatcher, line);
/*  563 */         String[] av = line.substring(2).split(" ");
/*  564 */         illegalAssert((av.length >= 3), "Polygons must have at least 3 vertices");
/*  565 */         int[][] polyVerts = new int[av.length][3];
/*  566 */         for (int i = 0; i < av.length; i++) {
/*      */           
/*  568 */           String[] as = av[i].split("/");
/*  569 */           for (int p = 0; p < as.length; p++) {
/*  570 */             if (as[p].length() > 0)
/*  571 */               polyVerts[i][p] = Integer.parseInt(as[p]); 
/*      */           } 
/*  573 */         }  if (vp == 3) {
/*  574 */           triangulate(polys, polyVerts);
/*      */         } else {
/*  576 */           quadulate(polys, polyVerts);
/*      */         } 
/*  578 */       }  if (line.startsWith("g ")) {
/*      */         
/*  580 */         if (!polys.isEmpty()) {
/*      */           
/*  582 */           modelMap.put(modelName, createModel(verts, uvs, normals, vertexMode, polys));
/*  583 */           polys.clear();
/*      */         } 
/*  585 */         modelName = line.substring(2);
/*      */       } 
/*      */     } 
/*      */     
/*  589 */     if (!polys.isEmpty()) {
/*  590 */       modelMap.put(modelName, createModel(verts, uvs, normals, vertexMode, polys));
/*      */     }
/*  592 */     return modelMap;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void triangulate(List<int[]> polys, int[][] polyVerts) {
/*  597 */     for (int i = 2; i < polyVerts.length; i++) {
/*      */       
/*  599 */       polys.add(polyVerts[0]);
/*  600 */       polys.add(polyVerts[i]);
/*  601 */       polys.add(polyVerts[i - 1]);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void quadulate(List<int[]> polys, int[][] polyVerts) {
/*  607 */     if (polyVerts.length == 4) {
/*      */       
/*  609 */       polys.add(polyVerts[0]);
/*  610 */       polys.add(polyVerts[3]);
/*  611 */       polys.add(polyVerts[2]);
/*  612 */       polys.add(polyVerts[1]);
/*      */     }
/*      */     else {
/*      */       
/*  616 */       for (int i = 2; i < polyVerts.length; i++) {
/*      */         
/*  618 */         polys.add(polyVerts[0]);
/*  619 */         polys.add(polyVerts[i]);
/*  620 */         polys.add(polyVerts[i - 1]);
/*  621 */         polys.add(polyVerts[i - 1]);
/*      */       } 
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
/*  633 */   public static Map<String, CCModel> parseObjModels(ResourceLocation res) { return parseObjModels(res, 4, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<String, CCModel> parseObjModels(ResourceLocation res, Transformation coordSystem) {
/*      */     try {
/*  645 */       return parseObjModels(
/*  646 */           Minecraft.func_71410_x().func_110442_L().func_110536_a(res).func_110527_b(), 4, coordSystem);
/*      */     
/*      */     }
/*  649 */     catch (IOException e) {
/*      */       
/*  651 */       throw new RuntimeException("failed to load model: " + res, e);
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
/*      */   public static Map<String, CCModel> parseObjModels(ResourceLocation res, int vertexMode, Transformation coordSystem) {
/*      */     try {
/*  666 */       return parseObjModels(
/*  667 */           Minecraft.func_71410_x().func_110442_L().func_110536_a(res).func_110527_b(), vertexMode, coordSystem);
/*      */     
/*      */     }
/*  670 */     catch (Exception e) {
/*      */       
/*  672 */       throw new RuntimeException("failed to load model: " + res, e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static CCModel createModel(List<Vector3> verts, List<Vector3> uvs, List<Vector3> normals, int vertexMode, List<int[]> polys) {
/*  678 */     int vp = (vertexMode == 7) ? 4 : 3;
/*  679 */     if (polys.size() < vp || polys.size() % vp != 0) {
/*  680 */       throw new IllegalArgumentException("Invalid number of vertices for model: " + polys.size());
/*      */     }
/*  682 */     boolean hasNormals = ((int[])polys.get(0)[2] > 0);
/*  683 */     CCModel model = newModel(vertexMode, polys.size());
/*  684 */     if (hasNormals) {
/*  685 */       model.getOrAllocate(CCRenderState.normalAttrib);
/*      */     }
/*  687 */     for (int i = 0; i < polys.size(); i++) {
/*      */       
/*  689 */       int[] ai = (int[])polys.get(i);
/*  690 */       Vector3 vert = ((Vector3)verts.get(ai[0] - 1)).copy();
/*  691 */       Vector3 uv = (ai[1] <= 0) ? new Vector3() : ((Vector3)uvs.get(ai[1] - 1)).copy();
/*  692 */       if (((ai[2] > 0)) != hasNormals) {
/*  693 */         throw new IllegalArgumentException("Normals are an all or nothing deal here.");
/*      */       }
/*  695 */       model.verts[i] = new Vertex5(vert, uv.x, uv.y);
/*  696 */       if (hasNormals) {
/*  697 */         model.normals()[i] = ((Vector3)normals.get(ai[2] - 1)).copy();
/*      */       }
/*      */     } 
/*  700 */     return model;
/*      */   }
/*      */ 
/*      */   
/*      */   private static <T> int addIndex(List<T> list, T elem) {
/*  705 */     int i = list.indexOf(elem) + 1;
/*  706 */     if (i == 0) {
/*  707 */       list.add(elem);
/*  708 */       i = list.size();
/*      */     } 
/*  710 */     return i;
/*      */   }
/*      */ 
/*      */   
/*  714 */   private static String clean(double d) { return (d == (int)d) ? Integer.toString((int)d) : Double.toString(d); }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void exportObj(Map<String, CCModel> models, PrintWriter p) {
/*  719 */     List<Vector3> verts = new ArrayList<Vector3>();
/*  720 */     List<UV> uvs = new ArrayList<UV>();
/*  721 */     List<Vector3> normals = new ArrayList<Vector3>();
/*  722 */     List<int[]> polys = new ArrayList<int[]>();
/*  723 */     for (Map.Entry<String, CCModel> e : models.entrySet()) {
/*  724 */       p.println("g " + (String)e.getKey());
/*  725 */       CCModel m = (CCModel)e.getValue();
/*      */       
/*  727 */       int vStart = verts.size();
/*  728 */       int uStart = uvs.size();
/*  729 */       int nStart = normals.size();
/*  730 */       boolean hasNormals = (m.normals() != null);
/*  731 */       polys.clear();
/*      */       
/*  733 */       for (int i = 0; i < m.verts.length; i++) {
/*  734 */         int[] ia = new int[hasNormals ? 3 : 2];
/*  735 */         ia[0] = addIndex(verts, (m.verts[i]).vec);
/*  736 */         ia[1] = addIndex(uvs, (m.verts[i]).uv);
/*  737 */         if (hasNormals)
/*  738 */           ia[2] = addIndex(normals, m.normals()[i]); 
/*  739 */         polys.add(ia);
/*      */       } 
/*      */       
/*  742 */       if (vStart < verts.size()) {
/*  743 */         p.println();
/*  744 */         for (int i = vStart; i < verts.size(); i++) {
/*  745 */           Vector3 v = (Vector3)verts.get(i);
/*  746 */           p.format("v %s %s %s\n", new Object[] { clean(v.x), clean(v.y), clean(v.z) });
/*      */         } 
/*      */       } 
/*  749 */       if (uStart < uvs.size()) {
/*  750 */         p.println();
/*  751 */         for (int i = uStart; i < uvs.size(); i++) {
/*  752 */           UV uv = (UV)uvs.get(i);
/*  753 */           p.format("vt %s %s\n", new Object[] { clean(uv.u), clean(uv.v) });
/*      */         } 
/*      */       } 
/*  756 */       if (nStart < normals.size()) {
/*  757 */         p.println();
/*  758 */         for (int i = nStart; i < normals.size(); i++) {
/*  759 */           Vector3 n = (Vector3)normals.get(i);
/*  760 */           p.format("vn %s %s %s\n", new Object[] { clean(n.x), clean(n.y), clean(n.z) });
/*      */         } 
/*      */       } 
/*      */       
/*  764 */       p.println();
/*  765 */       for (int i = 0; i < polys.size(); i++) {
/*  766 */         if (i % m.vp == 0)
/*  767 */           p.format("f", new Object[0]); 
/*  768 */         int[] ia = (int[])polys.get(i);
/*  769 */         if (hasNormals) {
/*  770 */           p.format(" %d/%d/%d", new Object[] { Integer.valueOf(ia[0]), Integer.valueOf(ia[1]), Integer.valueOf(ia[2]) });
/*      */         } else {
/*  772 */           p.format(" %d/%d", new Object[] { Integer.valueOf(ia[0]), Integer.valueOf(ia[1]) });
/*  773 */         }  if (i % m.vp == m.vp - 1) {
/*  774 */           p.println();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CCModel shrinkUVs(double d) {
/*  785 */     for (int k = 0; k < this.verts.length; k += this.vp) {
/*      */       
/*  787 */       UV uv = new UV();
/*  788 */       for (int i = 0; i < this.vp; i++)
/*      */       {
/*  790 */         uv.add((this.verts[k + i]).uv);
/*      */       }
/*  792 */       uv.multiply(1.0D / this.vp);
/*  793 */       for (int i = 0; i < this.vp; i++) {
/*      */         
/*  795 */         Vertex5 vert = this.verts[k + i];
/*  796 */         vert.uv.u += ((vert.uv.u < uv.u) ? d : -d);
/*  797 */         vert.uv.v += ((vert.uv.v < uv.v) ? d : -d);
/*      */       } 
/*      */     } 
/*  800 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CCModel sidedCopy(int side1, int side2, Vector3 point) {
/*  811 */     CCModel model = newModel(this.vertexMode, this.verts.length);
/*  812 */     copy(this, 0, model, 0, model.verts.length);
/*  813 */     return model.apply((new TransformationList(new Transformation[] { (Transformation)Rotation.sideRotations[side1].inverse(), Rotation.sideRotations[side2] })).at(point));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copy(CCModel src, int srcpos, CCModel dst, int destpos, int length) {
/*  821 */     for (int k = 0; k < length; k++) {
/*  822 */       dst.verts[destpos + k] = src.verts[srcpos + k].copy();
/*      */     }
/*  824 */     for (int i = 0; i < src.attributes.size(); i++) {
/*  825 */       if (src.attributes.get(i) != null) {
/*  826 */         CCRenderState.arrayCopy(src.attributes.get(i), srcpos, dst.getOrAllocate(CCRenderState.getAttribute(i)), destpos, length);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void generateSidedModels(CCModel[] models, int side, Vector3 point) {
/*  837 */     for (int s = 0; s < 6; s++) {
/*      */       
/*  839 */       if (s != side)
/*      */       {
/*      */         
/*  842 */         models[s] = models[side].sidedCopy(side, s, point);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void generateSidedModelsH(CCModel[] models, int side, Vector3 point) {
/*  854 */     for (int s = 2; s < 6; s++) {
/*      */       
/*  856 */       if (s != side)
/*      */       {
/*      */         
/*  859 */         models[s] = models[side].sidedCopy(side, s, point);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  865 */   public CCModel backfacedCopy() { return generateBackface(this, 0, copy(), 0, this.verts.length); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CCModel generateBackface(CCModel src, int srcpos, CCModel dst, int destpos, int length) {
/*  874 */     int vp = src.vp;
/*  875 */     if (srcpos % vp != 0 || destpos % vp != 0 || length % vp != 0) {
/*  876 */       throw new IllegalArgumentException("Vertices do not align with polygons");
/*      */     }
/*  878 */     int[][] o = { { 0, 0 }, { 1, vp - 1 }, { 2, vp - 2 }, { 3, vp - 3 } };
/*  879 */     for (int i = 0; i < length; i++) {
/*      */       
/*  881 */       int b = i / vp * vp;
/*  882 */       int d = i % vp;
/*  883 */       int di = destpos + b + o[d][1];
/*  884 */       int si = srcpos + b + o[d][0];
/*  885 */       dst.verts[di] = src.verts[si].copy();
/*  886 */       for (int a = 0; a < src.attributes.size(); a++) {
/*  887 */         if (src.attributes.get(a) != null)
/*  888 */           CCRenderState.arrayCopy(src.attributes.get(a), si, dst.getOrAllocate(CCRenderState.getAttribute(a)), di, 1); 
/*      */       } 
/*  890 */       if (dst.normals() != null && dst.normals()[di] != null)
/*  891 */         dst.normals()[di].negate(); 
/*      */     } 
/*  893 */     return dst;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CCModel generateSidedParts(int side, Vector3 point) {
/*  902 */     if (this.verts.length % 6 * this.vp != 0)
/*  903 */       throw new IllegalArgumentException("Invalid number of vertices for sided part generation"); 
/*  904 */     int length = this.verts.length / 6;
/*      */     
/*  906 */     for (int s = 0; s < 6; s++) {
/*      */       
/*  908 */       if (s != side)
/*      */       {
/*      */         
/*  911 */         generateSidedPart(side, s, point, length * side, length * s, length);
/*      */       }
/*      */     } 
/*  914 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CCModel generateSidedPartsH(int side, Vector3 point) {
/*  923 */     if (this.verts.length % 4 * this.vp != 0)
/*  924 */       throw new IllegalArgumentException("Invalid number of vertices for sided part generation"); 
/*  925 */     int length = this.verts.length / 4;
/*      */     
/*  927 */     for (int s = 2; s < 6; s++) {
/*      */       
/*  929 */       if (s != side)
/*      */       {
/*      */         
/*  932 */         generateSidedPart(side, s, point, length * (side - 2), length * (s - 2), length);
/*      */       }
/*      */     } 
/*  935 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  943 */   public CCModel generateSidedPart(int side1, int side2, Vector3 point, int srcpos, int destpos, int length) { return apply((new TransformationList(new Transformation[] { (Transformation)Rotation.sideRotations[side1].inverse(), Rotation.sideRotations[side2] }, )).at(point), srcpos, destpos, length); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CCModel apply(Transformation t, int srcpos, int destpos, int length) {
/*  951 */     for (int k = 0; k < length; k++) {
/*      */       
/*  953 */       this.verts[destpos + k] = this.verts[srcpos + k].copy();
/*  954 */       (this.verts[destpos + k]).vec.apply(t);
/*      */     } 
/*      */     
/*  957 */     Vector3[] normals = normals();
/*  958 */     if (normals != null) {
/*  959 */       for (int k = 0; k < length; k++) {
/*  960 */         normals[destpos + k] = normals[srcpos + k].copy();
/*  961 */         t.applyN(normals[destpos + k]);
/*      */       } 
/*      */     }
/*  964 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public static CCModel combine(Collection<CCModel> models) {
/*  969 */     if (models.isEmpty()) {
/*  970 */       return null;
/*      */     }
/*  972 */     int numVerts = 0;
/*  973 */     int vertexMode = -1;
/*  974 */     for (CCModel model : models) {
/*      */       
/*  976 */       if (vertexMode == -1)
/*  977 */         vertexMode = model.vertexMode; 
/*  978 */       if (vertexMode != model.vertexMode) {
/*  979 */         throw new IllegalArgumentException("Cannot combine models with different vertex modes");
/*      */       }
/*  981 */       numVerts += model.verts.length;
/*      */     } 
/*      */     
/*  984 */     CCModel c_model = newModel(vertexMode, numVerts);
/*  985 */     int i = 0;
/*  986 */     for (CCModel model : models) {
/*      */       
/*  988 */       copy(model, 0, c_model, i, model.verts.length);
/*  989 */       i += model.verts.length;
/*      */     } 
/*      */     
/*  992 */     return c_model;
/*      */   }
/*      */ 
/*      */   
/*      */   public CCModel twoFacedCopy() {
/*  997 */     CCModel model = newModel(this.vertexMode, this.verts.length * 2);
/*  998 */     copy(this, 0, model, 0, this.verts.length);
/*  999 */     return generateBackface(model, 0, model, this.verts.length, this.verts.length);
/*      */   }
/*      */ 
/*      */   
/*      */   public CCModel copy() {
/* 1004 */     CCModel model = newModel(this.vertexMode, this.verts.length);
/* 1005 */     copy(this, 0, model, 0, this.verts.length);
/* 1006 */     return model;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vector3 collapse() {
/* 1014 */     Vector3 v = new Vector3();
/* 1015 */     for (Vertex5 vert : this.verts)
/* 1016 */       v.add(vert.vec); 
/* 1017 */     v.multiply(1.0D / this.verts.length);
/* 1018 */     return v;
/*      */   }
/*      */ 
/*      */   
/*      */   public CCModel zOffset(Cuboid6 offsets) {
/* 1023 */     for (int k = 0; k < this.verts.length; k++) {
/*      */       
/* 1025 */       Vertex5 vert = this.verts[k];
/* 1026 */       Vector3 normal = normals()[k];
/* 1027 */       switch (findSide(normal)) {
/*      */         
/*      */         case 0:
/* 1030 */           vert.vec.y += offsets.min.y;
/*      */           break;
/*      */         case 1:
/* 1033 */           vert.vec.y += offsets.max.y;
/*      */           break;
/*      */         case 2:
/* 1036 */           vert.vec.z += offsets.min.z;
/*      */           break;
/*      */         case 3:
/* 1039 */           vert.vec.z += offsets.max.z;
/*      */           break;
/*      */         case 4:
/* 1042 */           vert.vec.x += offsets.min.x;
/*      */           break;
/*      */         case 5:
/* 1045 */           vert.vec.x += offsets.max.x;
/*      */           break;
/*      */       } 
/*      */     } 
/* 1049 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int findSide(Vector3 normal) {
/* 1054 */     if (normal.y <= -0.99D) return 0; 
/* 1055 */     if (normal.y >= 0.99D) return 1; 
/* 1056 */     if (normal.z <= -0.99D) return 2; 
/* 1057 */     if (normal.z >= 0.99D) return 3; 
/* 1058 */     if (normal.x <= -0.99D) return 4; 
/* 1059 */     if (normal.x >= 0.99D) return 5; 
/* 1060 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Cuboid6 bounds() {
/* 1068 */     Vector3 vec1 = (this.verts[0]).vec;
/* 1069 */     Cuboid6 c = new Cuboid6(vec1.copy(), vec1.copy());
/* 1070 */     for (int i = 1; i < this.verts.length; i++)
/* 1071 */       c.enclose((this.verts[i]).vec); 
/* 1072 */     return c;
/*      */   }
/*      */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\render\CCModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */