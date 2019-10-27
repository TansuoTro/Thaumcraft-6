/*    */ package thaumcraft.codechicken.lib.render.uv;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.MathContext;
/*    */ import java.math.RoundingMode;
/*    */ import thaumcraft.codechicken.lib.math.MathHelper;
/*    */ import thaumcraft.codechicken.lib.vec.ITransformation;
/*    */ 
/*    */ public class UVTranslation
/*    */   extends UVTransformation {
/*    */   public double du;
/*    */   public double dv;
/*    */   
/*    */   public UVTranslation(double u, double v) {
/* 15 */     this.du = u;
/* 16 */     this.dv = v;
/*    */   }
/*    */ 
/*    */   
/*    */   public void apply(UV uv) {
/* 21 */     uv.u += this.du;
/* 22 */     uv.v += this.dv;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public UVTransformation at(UV point) { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public UVTransformation inverse() { return new UVTranslation(-this.du, -this.dv); }
/*    */ 
/*    */ 
/*    */   
/*    */   public UVTransformation merge(UVTransformation next) {
/* 37 */     if (next instanceof UVTranslation) {
/* 38 */       UVTranslation t = (UVTranslation)next;
/* 39 */       return new UVTranslation(this.du + t.du, this.dv + t.dv);
/*    */     } 
/*    */     
/* 42 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public boolean isRedundant() { return (MathHelper.between(-1.0E-5D, this.du, 1.0E-5D) && MathHelper.between(-1.0E-5D, this.dv, 1.0E-5D)); }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 53 */     return "UVTranslation(" + new BigDecimal(this.du, cont) + ", " + new BigDecimal(this.dv, cont) + ")";
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\rende\\uv\UVTranslation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */