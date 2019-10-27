/*     */ package thaumcraft.client.lib.obj;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.Dictionary;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Vector3f;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.resources.IResource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MaterialLibrary
/*     */   extends Dictionary<String, Material>
/*     */ {
/*  23 */   static final Set<String> unknownCommands = new HashSet();
/*     */   
/*  25 */   private final Dictionary<String, Material> materialLibrary = new Hashtable();
/*     */ 
/*     */ 
/*     */   
/*     */   private Material currentMaterial;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public int size() { return this.materialLibrary.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public boolean isEmpty() { return this.materialLibrary.isEmpty(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public Enumeration<String> keys() { return this.materialLibrary.keys(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public Enumeration<Material> elements() { return this.materialLibrary.elements(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public Material get(Object key) { return (Material)this.materialLibrary.get(key); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public Material put(String key, Material value) { return (Material)this.materialLibrary.put(key, value); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public Material remove(Object key) { return (Material)this.materialLibrary.remove(key); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadFromStream(ResourceLocation loc) throws IOException {
/*  69 */     IResource res = Minecraft.func_71410_x().func_110442_L().func_110536_a(loc);
/*  70 */     InputStreamReader lineStream = new InputStreamReader(res.func_110527_b(), Charsets.UTF_8);
/*  71 */     BufferedReader lineReader = new BufferedReader(lineStream);
/*     */     
/*     */     while (true) {
/*  74 */       String currentLine = lineReader.readLine();
/*  75 */       if (currentLine == null) {
/*     */         break;
/*     */       }
/*  78 */       if (currentLine.length() == 0 || currentLine.startsWith("#")) {
/*     */         continue;
/*     */       }
/*     */       
/*  82 */       String[] fields = currentLine.split(" ", 2);
/*  83 */       String keyword = fields[0];
/*  84 */       String data = fields[1];
/*     */       
/*  86 */       if (keyword.equalsIgnoreCase("newmtl")) {
/*  87 */         pushMaterial(data); continue;
/*  88 */       }  if (keyword.equalsIgnoreCase("Ka")) {
/*  89 */         this.currentMaterial.AmbientColor = parseVector3f(data); continue;
/*  90 */       }  if (keyword.equalsIgnoreCase("Kd")) {
/*  91 */         this.currentMaterial.DiffuseColor = parseVector3f(data); continue;
/*  92 */       }  if (keyword.equalsIgnoreCase("Ks")) {
/*  93 */         this.currentMaterial.SpecularColor = parseVector3f(data); continue;
/*  94 */       }  if (keyword.equalsIgnoreCase("Ns")) {
/*  95 */         this.currentMaterial.SpecularCoefficient = parseFloat(data); continue;
/*  96 */       }  if (keyword.equalsIgnoreCase("Tr")) {
/*  97 */         this.currentMaterial.Transparency = parseFloat(data); continue;
/*  98 */       }  if (keyword.equalsIgnoreCase("illum")) {
/*  99 */         this.currentMaterial.IlluminationModel = parseInt(data); continue;
/* 100 */       }  if (keyword.equalsIgnoreCase("map_Ka")) {
/* 101 */         this.currentMaterial.AmbientTextureMap = data;
/* 102 */         ResourceLocation resourceLocation = new ResourceLocation(data);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 107 */       if (keyword.equalsIgnoreCase("map_Kd")) {
/* 108 */         this.currentMaterial.DiffuseTextureMap = data;
/* 109 */         ResourceLocation resourceLocation = new ResourceLocation(data);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 114 */       if (keyword.equalsIgnoreCase("map_Ks")) {
/* 115 */         this.currentMaterial.SpecularTextureMap = data; continue;
/* 116 */       }  if (keyword.equalsIgnoreCase("map_Ns")) {
/* 117 */         this.currentMaterial.SpecularHighlightTextureMap = data; continue;
/* 118 */       }  if (keyword.equalsIgnoreCase("map_d")) {
/* 119 */         this.currentMaterial.AlphaTextureMap = data; continue;
/* 120 */       }  if (keyword.equalsIgnoreCase("map_bump")) {
/* 121 */         this.currentMaterial.BumpMap = data; continue;
/* 122 */       }  if (keyword.equalsIgnoreCase("bump")) {
/* 123 */         this.currentMaterial.BumpMap = data; continue;
/* 124 */       }  if (keyword.equalsIgnoreCase("disp")) {
/* 125 */         this.currentMaterial.DisplacementMap = data; continue;
/* 126 */       }  if (keyword.equalsIgnoreCase("decal")) {
/* 127 */         this.currentMaterial.StencilDecalMap = data; continue;
/*     */       } 
/* 129 */       if (!unknownCommands.contains(keyword)) {
/* 130 */         unknownCommands.add(keyword);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   private float parseFloat(String data) { return Float.parseFloat(data); }
/*     */ 
/*     */ 
/*     */   
/* 142 */   private int parseInt(String data) { return Integer.parseInt(data); }
/*     */ 
/*     */   
/*     */   private Vector3f parseVector3f(String data) {
/* 146 */     String[] parts = data.split(" ");
/* 147 */     return new Vector3f(
/* 148 */         Float.parseFloat(parts[0]), 
/* 149 */         Float.parseFloat(parts[1]), 
/* 150 */         Float.parseFloat(parts[2]));
/*     */   }
/*     */ 
/*     */   
/*     */   private void pushMaterial(String materialName) {
/* 155 */     this.currentMaterial = new Material(materialName);
/* 156 */     this.materialLibrary.put(this.currentMaterial.Name, this.currentMaterial);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\obj\MaterialLibrary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */