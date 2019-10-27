/*    */ package thaumcraft.common.lib.utils;
/*    */ 
/*    */ 
/*    */ public class PosXY
/*    */   implements Comparable
/*    */ {
/*    */   public int x;
/*    */   public int y;
/*    */   
/*    */   public PosXY() {}
/*    */   
/*    */   public PosXY(int x, int z) {
/* 13 */     this.x = x;
/* 14 */     this.y = z;
/*    */   }
/*    */ 
/*    */   
/*    */   public PosXY(PosXY c) {
/* 19 */     this.x = c.x;
/* 20 */     this.y = c.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 25 */     if (!(o instanceof PosXY))
/*    */     {
/* 27 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 31 */     PosXY chunkcoordinates = (PosXY)o;
/* 32 */     return (this.x == chunkcoordinates.x && this.y == chunkcoordinates.y);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public int hashCode() { return this.x + this.y << 8; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public int compareTo(PosXY c) { return (this.y == c.y) ? (this.x - c.x) : (this.y - c.y); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void set(int x, int z) {
/* 48 */     this.x = x;
/* 49 */     this.y = z;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDistanceSquared(int x, int z) {
/* 54 */     float f = (this.x - x);
/* 55 */     float f2 = (this.y - z);
/* 56 */     return f * f + f2 * f2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public float getDistanceSquaredToChunkCoordinates(PosXY c) { return getDistanceSquared(c.x, c.y); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   public String toString() { return "Pos{x=" + this.x + ", y=" + this.y + '}'; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 71 */   public int compareTo(Object o) { return compareTo((PosXY)o); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\li\\utils\PosXY.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */