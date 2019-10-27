/*    */ package thaumcraft.codechicken.lib.render;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import thaumcraft.codechicken.lib.render.uv.UV;
/*    */ import thaumcraft.codechicken.lib.render.uv.UVTransformation;
/*    */ import thaumcraft.codechicken.lib.util.Copyable;
/*    */ import thaumcraft.codechicken.lib.vec.Transformation;
/*    */ import thaumcraft.codechicken.lib.vec.Vector3;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Vertex5
/*    */   extends Object
/*    */   implements Copyable<Vertex5>
/*    */ {
/*    */   public Vector3 vec;
/*    */   public UV uv;
/*    */   
/* 21 */   public Vertex5() { this(new Vector3(), new UV()); }
/*    */ 
/*    */ 
/*    */   
/*    */   public Vertex5(Vector3 vert, UV uv) {
/* 26 */     this.vec = vert;
/* 27 */     this.uv = uv;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public Vertex5(Vector3 vert, double u, double v) { this(vert, new UV(u, v)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public Vertex5(double x, double y, double z, double u, double v) { this(new Vector3(x, y, z), new UV(u, v)); }
/*    */ 
/*    */ 
/*    */   
/*    */   public Vertex5 set(double x, double y, double z, double u, double v) {
/* 42 */     this.vec.set(x, y, z);
/* 43 */     this.uv.set(u, v);
/* 44 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vertex5 set(double x, double y, double z, double u, double v, int tex) {
/* 49 */     this.vec.set(x, y, z);
/* 50 */     this.uv.set(u, v, tex);
/* 51 */     return this;
/*    */   }
/*    */   
/*    */   public Vertex5 set(Vertex5 vert) {
/* 55 */     this.vec.set(vert.vec);
/* 56 */     this.uv.set(vert.uv);
/* 57 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 62 */   public Vertex5(Vertex5 vertex5) { this(vertex5.vec.copy(), vertex5.uv.copy()); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public Vertex5 copy() { return new Vertex5(this); }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 72 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 73 */     return "Vertex: (" + new BigDecimal(this.vec.x, cont) + ", " + new BigDecimal(this.vec.y, cont) + ", " + new BigDecimal(this.vec.z, cont) + ") (" + new BigDecimal(this.uv.u, cont) + ", " + new BigDecimal(this.uv.v, cont) + ")";
/*    */   }
/*    */ 
/*    */   
/*    */   public Vertex5 apply(Transformation t) {
/* 78 */     this.vec.apply(t);
/* 79 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vertex5 apply(UVTransformation t) {
/* 84 */     this.uv.apply(t);
/* 85 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\render\Vertex5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */