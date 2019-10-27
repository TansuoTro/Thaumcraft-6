/*    */ package thaumcraft.codechicken.lib.render.uv;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import thaumcraft.codechicken.lib.util.Copyable;
/*    */ 
/*    */ 
/*    */ public class UV
/*    */   extends Object
/*    */   implements Copyable<UV>
/*    */ {
/*    */   public double u;
/*    */   public double v;
/*    */   public int tex;
/*    */   
/*    */   public UV() {}
/*    */   
/* 19 */   public UV(double u, double v) { this(u, v, 0); }
/*    */ 
/*    */   
/*    */   public UV(double u, double v, int tex) {
/* 23 */     this.u = u;
/* 24 */     this.v = v;
/* 25 */     this.tex = tex;
/*    */   }
/*    */ 
/*    */   
/* 29 */   public UV(UV uv) { this(uv.u, uv.v, uv.tex); }
/*    */ 
/*    */   
/*    */   public UV set(double u, double v, int tex) {
/* 33 */     this.u = u;
/* 34 */     this.v = v;
/* 35 */     this.tex = tex;
/* 36 */     return this;
/*    */   }
/*    */ 
/*    */   
/* 40 */   public UV set(double u, double v) { return set(u, v, this.tex); }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public UV set(UV uv) { return set(uv.u, uv.v, uv.tex); }
/*    */ 
/*    */ 
/*    */   
/* 48 */   public UV copy() { return new UV(this); }
/*    */ 
/*    */   
/*    */   public UV add(UV uv) {
/* 52 */     this.u += uv.u;
/* 53 */     this.v += uv.v;
/* 54 */     return this;
/*    */   }
/*    */   
/*    */   public UV multiply(double d) {
/* 58 */     this.u *= d;
/* 59 */     this.v *= d;
/* 60 */     return this;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 64 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 65 */     return "UV(" + new BigDecimal(this.u, cont) + ", " + new BigDecimal(this.v, cont) + ")";
/*    */   }
/*    */   
/*    */   public UV apply(UVTransformation t) {
/* 69 */     t.apply(this);
/* 70 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 75 */     if (!(o instanceof UV))
/* 76 */       return false; 
/* 77 */     UV uv = (UV)o;
/* 78 */     return (this.u == uv.u && this.v == uv.v);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\rende\\uv\UV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */