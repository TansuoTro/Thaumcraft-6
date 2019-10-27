/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ 
/*    */ public class Rectangle4i
/*    */ {
/*    */   public int x;
/*    */   public int y;
/*    */   public int w;
/*    */   public int h;
/*    */   
/*    */   public Rectangle4i() {}
/*    */   
/*    */   public Rectangle4i(int x, int y, int w, int h) {
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/* 16 */     this.w = w;
/* 17 */     this.h = h;
/*    */   }
/*    */ 
/*    */   
/* 21 */   public int x1() { return this.x; }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public int y1() { return this.y; }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public int x2() { return this.x + this.w - 1; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public int y2() { return this.y + this.h - 1; }
/*    */ 
/*    */   
/*    */   public void set(int x, int y, int w, int h) {
/* 37 */     this.x = x;
/* 38 */     this.y = y;
/* 39 */     this.w = w;
/* 40 */     this.h = h;
/*    */   }
/*    */   
/*    */   public Rectangle4i offset(int dx, int dy) {
/* 44 */     this.x += dx;
/* 45 */     this.y += dy;
/* 46 */     return this;
/*    */   }
/*    */   
/*    */   public Rectangle4i include(int px, int py) {
/* 50 */     if (px < this.x) expand(px - this.x, 0); 
/* 51 */     if (px >= this.x + this.w) expand(px - this.x - this.w + 1, 0); 
/* 52 */     if (py < this.y) expand(0, py - this.y); 
/* 53 */     if (py >= this.y + this.h) expand(0, py - this.y - this.h + 1); 
/* 54 */     return this;
/*    */   }
/*    */   
/*    */   public Rectangle4i include(Rectangle4i r) {
/* 58 */     include(r.x, r.y);
/* 59 */     return include(r.x2(), r.y2());
/*    */   }
/*    */   
/*    */   public Rectangle4i expand(int px, int py) {
/* 63 */     if (px > 0) {
/* 64 */       this.w += px;
/*    */     } else {
/* 66 */       this.x += px;
/* 67 */       this.w -= px;
/*    */     } 
/* 69 */     if (py > 0) {
/* 70 */       this.h += py;
/*    */     } else {
/* 72 */       this.y += py;
/* 73 */       this.h -= py;
/*    */     } 
/* 75 */     return this;
/*    */   }
/*    */ 
/*    */   
/* 79 */   public boolean contains(int px, int py) { return (this.x <= px && px < this.x + this.w && this.y <= py && py < this.y + this.h); }
/*    */ 
/*    */ 
/*    */   
/* 83 */   public boolean intersects(Rectangle4i r) { return (r.x + r.w > this.x && r.x < this.x + this.w && r.y + r.h > this.y && r.y < this.y + this.h); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 90 */   public int area() { return this.w * this.h; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\Rectangle4i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */