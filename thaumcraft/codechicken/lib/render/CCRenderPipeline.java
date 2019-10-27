/*     */ package thaumcraft.codechicken.lib.render;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ public class CCRenderPipeline
/*     */ {
/*     */   private class PipelineNode
/*     */   {
/*  36 */     public ArrayList<PipelineNode> deps = new ArrayList();
/*     */     public CCRenderState.IVertexOperation op;
/*     */     
/*     */     public void add() {
/*  40 */       if (this.op == null) {
/*     */         return;
/*     */       }
/*  43 */       for (int i = 0; i < this.deps.size(); i++)
/*  44 */         ((PipelineNode)this.deps.get(i)).add(); 
/*  45 */       this.deps.clear();
/*  46 */       CCRenderPipeline.this.sorted.add(this.op);
/*  47 */       this.op = null;
/*     */     }
/*     */     
/*     */     private PipelineNode() {} }
/*  51 */   private ArrayList<CCRenderState.VertexAttribute> attribs = new ArrayList();
/*  52 */   private ArrayList<CCRenderState.IVertexOperation> ops = new ArrayList();
/*  53 */   private ArrayList<PipelineNode> nodes = new ArrayList();
/*  54 */   private ArrayList<CCRenderState.IVertexOperation> sorted = new ArrayList();
/*     */   
/*     */   private PipelineNode loading;
/*     */   
/*     */   public void setPipeline(IVertexOperation... ops) {
/*  59 */     this.ops.clear();
/*  60 */     for (int i = 0; i < ops.length; i++)
/*  61 */       this.ops.add(ops[i]); 
/*  62 */     rebuild();
/*     */   }
/*     */   
/*     */   public void reset() {
/*  66 */     this.ops.clear();
/*  67 */     unbuild();
/*     */   }
/*     */   
/*     */   private void unbuild() {
/*  71 */     for (int i = 0; i < this.attribs.size(); i++)
/*  72 */       ((CCRenderState.VertexAttribute)this.attribs.get(i)).active = false; 
/*  73 */     this.attribs.clear();
/*  74 */     this.sorted.clear();
/*     */   }
/*     */   
/*     */   public void rebuild() {
/*  78 */     if (this.ops.isEmpty() || CCRenderState.model == null) {
/*     */       return;
/*     */     }
/*     */     
/*  82 */     while (this.nodes.size() < CCRenderState.operationCount())
/*  83 */       this.nodes.add(new PipelineNode(null)); 
/*  84 */     unbuild();
/*     */     
/*  86 */     if (CCRenderState.useNormals)
/*  87 */       addAttribute(CCRenderState.normalAttrib); 
/*  88 */     if (CCRenderState.baseColour != -1 || CCRenderState.alphaOverride >= 0) {
/*  89 */       addAttribute(CCRenderState.colourAttrib);
/*  90 */     } else if (CCRenderState.hasColour) {
/*  91 */       CCRenderState.setColour(-1);
/*     */     } 
/*  93 */     for (int i = 0; i < this.ops.size(); i++) {
/*  94 */       CCRenderState.IVertexOperation op = (CCRenderState.IVertexOperation)this.ops.get(i);
/*  95 */       this.loading = (PipelineNode)this.nodes.get(op.operationID());
/*  96 */       boolean loaded = op.load();
/*  97 */       if (loaded) {
/*  98 */         this.loading.op = op;
/*     */       }
/* 100 */       if (op instanceof CCRenderState.VertexAttribute)
/* 101 */         if (loaded) {
/* 102 */           this.attribs.add((CCRenderState.VertexAttribute)op);
/*     */         } else {
/* 104 */           ((CCRenderState.VertexAttribute)op).active = false;
/*     */         }  
/*     */     } 
/* 107 */     for (int i = 0; i < this.nodes.size(); i++) {
/* 108 */       ((PipelineNode)this.nodes.get(i)).add();
/*     */     }
/*     */   }
/*     */   
/* 112 */   public void addRequirement(int opRef) { this.loading.deps.add(this.nodes.get(opRef)); }
/*     */ 
/*     */   
/*     */   public void addDependency(CCRenderState.VertexAttribute attrib) {
/* 116 */     this.loading.deps.add(this.nodes.get(attrib.operationID()));
/* 117 */     addAttribute(attrib);
/*     */   }
/*     */   
/*     */   public void addAttribute(CCRenderState.VertexAttribute attrib) {
/* 121 */     if (!attrib.active) {
/* 122 */       this.ops.add(attrib);
/* 123 */       attrib.active = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void operate() {
/* 128 */     for (int i = 0; i < this.sorted.size(); i++)
/* 129 */       ((CCRenderState.IVertexOperation)this.sorted.get(i)).operate(); 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\render\CCRenderPipeline.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */