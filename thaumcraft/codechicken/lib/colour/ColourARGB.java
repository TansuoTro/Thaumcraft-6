/*    */ package thaumcraft.codechicken.lib.colour;
/*    */ 
/*    */ 
/*    */ public class ColourARGB
/*    */   extends Colour
/*    */ {
/*  7 */   public ColourARGB(int colour) { super(colour >> 16 & 0xFF, colour >> 8 & 0xFF, colour & 0xFF, colour >> 24 & 0xFF); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 12 */   public ColourARGB(int a, int r, int g, int b) { super(r, g, b, a); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public ColourARGB(ColourARGB colour) { super(colour); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public ColourARGB copy() { return new ColourARGB(this); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public int pack() { return pack(this); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public static int pack(Colour colour) { return (colour.a & 0xFF) << 24 | (colour.r & 0xFF) << 16 | (colour.g & 0xFF) << 8 | colour.b & 0xFF; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\colour\ColourARGB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */