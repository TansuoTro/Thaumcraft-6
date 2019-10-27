/*    */ package thaumcraft.codechicken.lib.render.uv;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import thaumcraft.codechicken.lib.vec.ITransformation;
/*    */ 
/*    */ public class UVScale extends UVTransformation {
/*    */   double su;
/*    */   
/*    */   public UVScale(double scaleu, double scalev) {
/* 12 */     this.su = scaleu;
/* 13 */     this.sv = scalev;
/*    */   }
/*    */   double sv;
/*    */   
/* 17 */   public UVScale(double d) { this(d, d); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void apply(UV uv) {
/* 22 */     uv.u *= this.su;
/* 23 */     uv.v *= this.sv;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public UVTransformation inverse() { return new UVScale(1.0D / this.su, 1.0D / this.sv); }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 34 */     return "UVScale(" + new BigDecimal(this.su, cont) + ", " + new BigDecimal(this.sv, cont) + ")";
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\rende\\uv\UVScale.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */