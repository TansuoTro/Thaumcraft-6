/*     */ package thaumcraft.client.lib.obj;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.resources.IResource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import scala.NotImplementedError;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MeshLoader
/*     */ {
/*  18 */   static final Set<String> unknownCommands = new HashSet();
/*     */   
/*     */   private MeshModel currentModel;
/*     */   
/*     */   private MeshPart currentPart;
/*     */   
/*     */   private MaterialLibrary currentMatLib;
/*     */   int firstIndex;
/*     */   int lastIndex;
/*     */   private String filePath;
/*     */   private String lastObjectName;
/*     */   
/*     */   private void addTexCoord(String line) {
/*  31 */     String[] args = line.split(" ");
/*     */     
/*  33 */     float x = Float.parseFloat(args[0]);
/*  34 */     float y = Float.parseFloat(args[1]);
/*     */     
/*  36 */     this.currentModel.addTexCoords(x, y);
/*     */   }
/*     */   
/*     */   private void addNormal(String line) {
/*  40 */     String[] args = line.split(" ");
/*     */     
/*  42 */     float x = Float.parseFloat(args[0]);
/*  43 */     float y = Float.parseFloat(args[1]);
/*     */ 
/*     */ 
/*     */     
/*  47 */     float z = args[2].equals("\\\\") ? (float)Math.sqrt((1.0F - x * x - y * y)) : Float.parseFloat(args[2]);
/*     */     
/*  49 */     this.currentModel.addNormal(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   private void addPosition(String line) {
/*  54 */     String[] args = line.split(" ");
/*     */     
/*  56 */     float x = Float.parseFloat(args[0]);
/*  57 */     float y = Float.parseFloat(args[1]);
/*  58 */     float z = Float.parseFloat(args[2]);
/*     */     
/*  60 */     this.currentModel.addPosition(x, y, z);
/*     */   }
/*     */   
/*     */   private void addFace(String line) {
/*  64 */     String[] args = line.split(" ");
/*     */     
/*  66 */     if (args.length < 3 || args.length > 4) {
/*  67 */       throw new NotImplementedError();
/*     */     }
/*  69 */     String[] p1 = args[0].split("/");
/*  70 */     String[] p2 = args[1].split("/");
/*  71 */     String[] p3 = args[2].split("/");
/*     */     
/*  73 */     int[] v1 = parseIndices(p1);
/*  74 */     int[] v2 = parseIndices(p2);
/*  75 */     int[] v3 = parseIndices(p3);
/*     */     
/*  77 */     if (args.length == 3) {
/*  78 */       this.currentPart.addTriangleFace(v1, v2, v3);
/*  79 */     } else if (args.length == 4) {
/*  80 */       String[] p4 = args[3].split("/");
/*  81 */       int[] v4 = parseIndices(p4);
/*     */ 
/*     */       
/*  84 */       this.currentPart.addQuadFace(v1, v2, v3, v4);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int[] parseIndices(String[] p1) {
/*  89 */     int[] indices = new int[p1.length];
/*  90 */     for (int i = 0; i < p1.length; i++) {
/*  91 */       indices[i] = Integer.parseInt(p1[i]) - 1;
/*     */     }
/*  93 */     return indices;
/*     */   }
/*     */   
/*     */   private void useMaterial(String matName) {
/*  97 */     Material mat = this.currentMatLib.get(matName);
/*     */     
/*  99 */     this.currentPart = new MeshPart();
/* 100 */     this.currentPart.name = this.lastObjectName;
/* 101 */     this.currentPart.material = mat;
/* 102 */     this.currentModel.addPart(this.currentPart);
/*     */   }
/*     */ 
/*     */   
/* 106 */   private void newObject(String line) { this.lastObjectName = line; }
/*     */ 
/*     */ 
/*     */   
/* 110 */   private void newGroup(String line) { this.lastObjectName = line; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadMaterialLibrary(ResourceLocation locOfParent, String path) throws IOException {
/* 116 */     String prefix = locOfParent.func_110623_a();
/* 117 */     int pp = prefix.lastIndexOf('/');
/* 118 */     prefix = (pp >= 0) ? prefix.substring(0, pp + 1) : "";
/*     */     
/* 120 */     ResourceLocation loc = new ResourceLocation(locOfParent.func_110624_b(), prefix + path);
/*     */     
/* 122 */     this.currentMatLib.loadFromStream(loc);
/*     */   }
/*     */   
/*     */   public MeshModel loadFromResource(ResourceLocation loc) throws IOException {
/* 126 */     IResource res = Minecraft.func_71410_x().func_110442_L().func_110536_a(loc);
/* 127 */     InputStreamReader lineStream = new InputStreamReader(res.func_110527_b(), Charsets.UTF_8);
/* 128 */     BufferedReader lineReader = new BufferedReader(lineStream);
/*     */     
/* 130 */     this.currentModel = new MeshModel();
/* 131 */     this.currentMatLib = new MaterialLibrary();
/*     */     
/*     */     while (true) {
/* 134 */       String currentLine = lineReader.readLine();
/* 135 */       if (currentLine == null) {
/*     */         break;
/*     */       }
/* 138 */       if (currentLine.length() == 0 || currentLine.startsWith("#")) {
/*     */         continue;
/*     */       }
/* 141 */       if (currentLine.startsWith("v  "))
/* 142 */         currentLine = currentLine.replaceFirst("v  ", "v "); 
/* 143 */       String[] fields = currentLine.split(" ", 2);
/* 144 */       String keyword = fields[0];
/* 145 */       String data = fields[1];
/*     */       
/* 147 */       if (keyword.equalsIgnoreCase("o")) {
/* 148 */         newObject(data); continue;
/* 149 */       }  if (keyword.equalsIgnoreCase("g")) {
/* 150 */         newGroup(data); continue;
/* 151 */       }  if (keyword.equalsIgnoreCase("mtllib")) {
/* 152 */         loadMaterialLibrary(loc, data); continue;
/* 153 */       }  if (keyword.equalsIgnoreCase("usemtl")) {
/* 154 */         useMaterial(data); continue;
/* 155 */       }  if (keyword.equalsIgnoreCase("v")) {
/* 156 */         addPosition(data); continue;
/* 157 */       }  if (keyword.equalsIgnoreCase("vn")) {
/* 158 */         addNormal(data); continue;
/* 159 */       }  if (keyword.equalsIgnoreCase("vt")) {
/* 160 */         addTexCoord(data); continue;
/* 161 */       }  if (keyword.equalsIgnoreCase("f")) {
/* 162 */         addFace(data); continue;
/*     */       } 
/* 164 */       if (!unknownCommands.contains(keyword)) {
/* 165 */         unknownCommands.add(keyword);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 171 */     return this.currentModel;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\obj\MeshLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */