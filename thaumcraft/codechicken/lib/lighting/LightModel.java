/*     */ package thaumcraft.codechicken.lib.lighting;
/*     */ 
/*     */ import thaumcraft.codechicken.lib.render.CCRenderState;
/*     */ import thaumcraft.codechicken.lib.vec.Rotation;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ 
/*     */ public class LightModel
/*     */   implements CCRenderState.IVertexOperation {
/*   9 */   public static final int operationIndex = CCRenderState.registerOperation();
/*     */   public static class Light { public Vector3 ambient;
/*     */     
/*     */     public Light(Vector3 pos) {
/*  13 */       this.ambient = new Vector3();
/*  14 */       this.diffuse = new Vector3();
/*     */ 
/*     */ 
/*     */       
/*  18 */       this.position = pos.copy().normalize();
/*     */     }
/*     */     public Vector3 diffuse; public Vector3 position;
/*     */     public Light setDiffuse(Vector3 vec) {
/*  22 */       this.diffuse.set(vec);
/*  23 */       return this;
/*     */     }
/*     */     
/*     */     public Light setAmbient(Vector3 vec) {
/*  27 */       this.ambient.set(vec);
/*  28 */       return this;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public static LightModel standardLightModel = (new LightModel())
/*  35 */     .setAmbient(new Vector3(0.4D, 0.4D, 0.4D))
/*  36 */     .addLight((new Light(new Vector3(0.2D, 1.0D, -0.7D)))
/*  37 */       .setDiffuse(new Vector3(0.6D, 0.6D, 0.6D)))
/*  38 */     .addLight((new Light(new Vector3(-0.2D, 1.0D, 0.7D)))
/*  39 */       .setDiffuse(new Vector3(0.6D, 0.6D, 0.6D)));
/*     */ 
/*     */   
/*  42 */   private Vector3 ambient = new Vector3();
/*  43 */   private Light[] lights = new Light[8];
/*     */   private int lightCount;
/*     */   
/*     */   public LightModel addLight(Light light) {
/*  47 */     this.lights[this.lightCount++] = light;
/*  48 */     return this;
/*     */   }
/*     */   
/*     */   public LightModel setAmbient(Vector3 vec) {
/*  52 */     this.ambient.set(vec);
/*  53 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int apply(int colour, Vector3 normal) {
/*  62 */     Vector3 n_colour = this.ambient.copy();
/*  63 */     for (int l = 0; l < this.lightCount; l++) {
/*  64 */       Light light = this.lights[l];
/*  65 */       double n_l = light.position.dotProduct(normal);
/*  66 */       double f = (n_l > 0.0D) ? 1.0D : 0.0D;
/*  67 */       n_colour.x += light.ambient.x + f * light.diffuse.x * n_l;
/*  68 */       n_colour.y += light.ambient.y + f * light.diffuse.y * n_l;
/*  69 */       n_colour.z += light.ambient.z + f * light.diffuse.z * n_l;
/*     */     } 
/*     */     
/*  72 */     if (n_colour.x > 1.0D)
/*  73 */       n_colour.x = 1.0D; 
/*  74 */     if (n_colour.y > 1.0D)
/*  75 */       n_colour.y = 1.0D; 
/*  76 */     if (n_colour.z > 1.0D) {
/*  77 */       n_colour.z = 1.0D;
/*     */     }
/*  79 */     n_colour.multiply((colour >>> 24) / 255.0D, (colour >> 16 & 0xFF) / 255.0D, (colour >> 8 & 0xFF) / 255.0D);
/*  80 */     return (int)(n_colour.x * 255.0D) << 24 | (int)(n_colour.y * 255.0D) << 16 | (int)(n_colour.z * 255.0D) << 8 | colour & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean load() {
/*  85 */     CCRenderState.pipeline.addDependency(CCRenderState.normalAttrib);
/*  86 */     CCRenderState.pipeline.addDependency(CCRenderState.colourAttrib);
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void operate() { CCRenderState.setColour(apply(CCRenderState.colour, CCRenderState.normal)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public int operationID() { return operationIndex; }
/*     */ 
/*     */   
/*     */   public PlanarLightModel reducePlanar() {
/* 101 */     int[] colours = new int[6];
/* 102 */     for (int i = 0; i < 6; i++)
/* 103 */       colours[i] = apply(-1, Rotation.axes[i]); 
/* 104 */     return new PlanarLightModel(colours);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\lighting\LightModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */