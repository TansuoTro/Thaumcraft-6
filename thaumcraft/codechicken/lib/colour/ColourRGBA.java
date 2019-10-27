/*    */ package thaumcraft.codechicken.lib.colour;
/*    */ 
/*    */ public class ColourRGBA
/*    */   extends Colour {
/*  5 */   public ColourRGBA(int colour) { super(colour >> 24 & 0xFF, colour >> 16 & 0xFF, colour >> 8 & 0xFF, colour & 0xFF); }
/*    */ 
/*    */ 
/*    */   
/*  9 */   public ColourRGBA(double r, double g, double b, double a) { super((int)(255.0D * r), (int)(255.0D * g), (int)(255.0D * b), (int)(255.0D * a)); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public ColourRGBA(int r, int g, int b, int a) { super(r, g, b, a); }
/*    */ 
/*    */ 
/*    */   
/* 17 */   public ColourRGBA(ColourRGBA colour) { super(colour); }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public int pack() { return pack(this); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public Colour copy() { return new ColourRGBA(this); }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public static int pack(Colour colour) { return (colour.r & 0xFF) << 24 | (colour.g & 0xFF) << 16 | (colour.b & 0xFF) << 8 | colour.a & 0xFF; }
/*    */ 
/*    */   
/*    */   public static int multiply(int c1, int c2) {
/* 34 */     if (c1 == -1) return c2; 
/* 35 */     if (c2 == -1) return c1; 
/* 36 */     int r = ((c1 >>> 24) * (c2 >>> 24) & 0xFF00) << 16;
/* 37 */     int g = ((c1 >> 16 & 0xFF) * (c2 >> 16 & 0xFF) & 0xFF00) << 8;
/* 38 */     int b = (c1 >> 8 & 0xFF) * (c2 >> 8 & 0xFF) & 0xFF00;
/* 39 */     int a = (c1 & 0xFF) * (c2 & 0xFF) >> 8;
/* 40 */     return r | g | b | a;
/*    */   }
/*    */   
/*    */   public static int multiplyC(int c, float f) {
/* 44 */     int r = (int)((c >>> 24) * f);
/* 45 */     int g = (int)((c >> 16 & 0xFF) * f);
/* 46 */     int b = (int)((c >> 8 & 0xFF) * f);
/* 47 */     return r << 24 | g << 16 | b << 8 | c & 0xFF;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\colour\ColourRGBA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */